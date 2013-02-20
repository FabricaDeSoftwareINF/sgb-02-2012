package br.ufg.inf.es.web.controller;

import br.ufg.inf.es.base.validation.ValidationException;
import br.ufg.inf.es.integracao.ParametrosService;
import br.ufg.inf.es.integracao.importacaodados.ImportacaoDadosServiceImpl;
import br.ufg.inf.es.integracao.importacaodados.ImportacaoDadosService;
import br.ufg.inf.es.integracao.importacaodados.ImportacaoLivro;
import br.ufg.inf.es.model.Livro;
import br.ufg.inf.es.model.Parametros;
import br.ufg.inf.es.model.importacaobibliografia.TipoEntidade;
import br.ufg.inf.es.web.controller.form.ParametrosForm;
import java.util.Collection;
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
    
    private Collection<Livro> livrosEncontrados;
    
    private TipoEntidade tipoEntidade;

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

    public TipoEntidade[] getTipoEntidadeSelect(){        
        return TipoEntidade.values();
    }
    
    public TipoEntidade getTipoEntidade() {
        return tipoEntidade;
    }

    public void setTipoEntidade(TipoEntidade tipoEntidade) {
        this.tipoEntidade = tipoEntidade;
    }
    
    private void importaGet(){
        ImportacaoDadosService<Livro> importacaoSipa = new ImportacaoDadosServiceImpl<Livro>();
        if (tipoEntidade.equals(TipoEntidade.Livros)){
            importacaoSipa.setImportacaoStrategy(new ImportacaoLivro());
            livrosEncontrados = importacaoSipa.importar(urlService);
        }
    }   

    public Collection<Livro> getLivrosEncontrados() {
        return livrosEncontrados;
    }

    public void setLivrosEncontrados(Collection<Livro> cursosEncontrados) {
        this.livrosEncontrados = cursosEncontrados;
    }
    
}
