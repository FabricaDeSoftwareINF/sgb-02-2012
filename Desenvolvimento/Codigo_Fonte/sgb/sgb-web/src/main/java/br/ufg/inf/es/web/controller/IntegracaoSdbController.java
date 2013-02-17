package br.ufg.inf.es.web.controller;

import br.ufg.inf.es.base.validation.ValidationException;
import br.ufg.inf.es.integracao.ParametrosService;
import br.ufg.inf.es.integracao.importacaodados.ImportacaoDadosServiceImpl;
import br.ufg.inf.es.model.Parametros;
import br.ufg.inf.es.model.importacaobibliografia.MetodoImportacao;
import br.ufg.inf.es.model.importacaobibliografia.TipoEntidade;
import br.ufg.inf.es.web.controller.form.ParametrosForm;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 *
 * @author igor
 */
@Component
@Scope
public class IntegracaoSdbController extends 
        SGBController<Parametros, ParametrosForm, ParametrosService> {

    @Autowired
    private ParametrosForm form;
    @Autowired
    private ParametrosService service;
    private String urlService;
    
    private MetodoImportacao metodoImportacao;
    private TipoEntidade tipoEntidade;
    
    private ImportacaoDadosServiceImpl importacaoSipa = new ImportacaoDadosServiceImpl();

    @Override
    public ParametrosForm getForm() {
        return this.form;
    }

    @Override
    public ParametrosService getService() {
        return this.service;
    }

    @Override
    public String openViewPage() {
        try {
            Parametros parametros = service.find();
            this.getForm().setEntity(parametros);
            if (parametros.getUrlSeviceBibliografico() != null && 
                    (!parametros.getUrlSeviceBibliografico().equals(""))){
                this.urlService = parametros.getUrlSeviceBibliografico();
            }

        } catch (Exception e) {
        }
        return "IntegracaoSdbController/initialPage";
    }

    public String getUrlService() {
        return urlService;
    }

    public void setUrlService(String urlService) {
        this.urlService = urlService;
    }

    public String importar() {
        
        save();
        addSuccessMessage("Importação realizada com sucesso!");
        return "";
    }
    
    
    public void save() {
        try {
            Parametros parametro = this.getForm().getEntity();
            if (urlService != null && (!urlService.equals(""))){
                parametro.setUrlSeviceBibliografico(urlService);
            }
            if (parametro.getId() == null){
                Hibernate.initialize(parametro);
                getService().insert(parametro);
            } else {
                Hibernate.initialize(parametro);
                getService().update(parametro);
            }
        } catch (ValidationException ex) {
            addWarningMessage(ex.getKeyMessage());
        }
        
        importaGet();
    }
    
    public String voltar() {
        return "/index.jsf";

    }
    
    public MetodoImportacao[] getMetodoImportacaoSelect(){        
        return MetodoImportacao.values();
    }
    
    public TipoEntidade[] getTipoEntidadeSelect(){        
        return TipoEntidade.values();
    }

    public MetodoImportacao getMetodoImportacao() {
        return metodoImportacao;
    }

    public void setMetodoImportacao(MetodoImportacao metodoImportacao) {
        this.metodoImportacao = metodoImportacao;
    }

    public TipoEntidade getTipoEntidade() {
        return tipoEntidade;
    }

    public void setTipoEntidade(TipoEntidade tipoEntidade) {
        this.tipoEntidade = tipoEntidade;
    }
    
    private void importaGet(){
        this.importacaoSipa.setUrlServico(urlService);
        if (tipoEntidade.equals(TipoEntidade.Cursos)){
            importacaoSipa.importarCursos();
            
        } else if (tipoEntidade.equals(TipoEntidade.Disciplinas)){
            importacaoSipa.importarDisciplinas();
            
        } else if (tipoEntidade.equals(TipoEntidade.Livros)){
            importacaoSipa.importarLivros();
        } 
    }   
}
