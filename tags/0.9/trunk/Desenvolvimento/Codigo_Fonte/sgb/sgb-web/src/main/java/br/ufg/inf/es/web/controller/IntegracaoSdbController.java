package br.ufg.inf.es.web.controller;

import br.ufg.inf.es.base.validation.ValidationException;
import br.ufg.inf.es.integracao.importacaodados.ImportacaoLivrosServiceImpl;
import br.ufg.inf.es.integracao.importacaodados.ImportacaoDadosService;
import br.ufg.inf.es.integracao.importacaodados.ImportacaoLivro;
import br.ufg.inf.es.model.Livro;
import br.ufg.inf.es.model.importacaobibliografia.TipoEntidade;
import br.ufg.inf.es.web.controller.form.IntegracaoSdbForm;
import br.ufg.inf.es.web.datamodel.LivroDataModel;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
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
        SGBController<Livro, IntegracaoSdbForm, ImportacaoLivrosServiceImpl> {

    @Autowired
    private IntegracaoSdbForm form;
    @Autowired
    private ImportacaoLivrosServiceImpl service;
    private String urlService;
    
    private LivroDataModel livrosEncontrados;
    private Collection<Livro> livrosSelecionados;
    
    private TipoEntidade tipoEntidade;

    @Override
    public IntegracaoSdbForm getForm() {
        return this.form;
    }

    @Override
    public ImportacaoLivrosServiceImpl getService() {
        return this.service;
    }

    @Override
    public String openViewPage() {
        this.getForm().clearInsertData();
        this.livrosEncontrados = new LivroDataModel();
        this.livrosSelecionados = new ArrayList<Livro>();
        return super.openViewPage();
    }

    public String getUrlService() {
        return urlService;
    }

    public void setUrlService(String urlService) {
        this.urlService = urlService;
    }

    public String importar() {
        importaGet();
        return "";
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
        ImportacaoDadosService<Livro> importacaoSipa = new ImportacaoLivrosServiceImpl();
        if (tipoEntidade.equals(TipoEntidade.Livros)){
            importacaoSipa.setImportacaoStrategy(new ImportacaoLivro());
            List<Livro> livros = new ArrayList<Livro>(importacaoSipa.importar(urlService));
            livrosEncontrados = new LivroDataModel(livros);
        }
    }   

    public LivroDataModel getLivrosEncontrados() {
        return livrosEncontrados;
    }

    public void setLivrosEncontrados(LivroDataModel cursosEncontrados) {
        this.livrosEncontrados = cursosEncontrados;
    }

    public Collection<Livro> getLivrosSelecionados() {
        return livrosSelecionados;
    }

    public void setLivrosSelecionados(Collection<Livro> livrosSelecionados) {
        this.livrosSelecionados = livrosSelecionados;
    }
    
    public String salvarLivros() {
        if (livrosSelecionados == null) {
            return null;
        }
        for (Livro livro : livrosSelecionados) {
            try {
                this.getService().salvarLivro(livro);
            } catch (ValidationException ex) {
                this.addErrorMessage("Não foi possível inserir o livro: " + livro);
            }
        }
        return null;
    }
    
}
