package br.ufg.inf.es.web.controller;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collection;

import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import br.ufg.inf.es.integracao.LivroService;
import br.ufg.inf.es.integracao.cotacao.CotadorBuscape;
import br.ufg.inf.es.integracao.cotacao.CotadorGoogleShop;
import br.ufg.inf.es.integracao.cotacao.ResultadoCotacao;
import br.ufg.inf.es.model.Autor;
import br.ufg.inf.es.model.ItemListaCotacao;
import br.ufg.inf.es.model.Livro;
import br.ufg.inf.es.web.controller.form.TemplateForm;

/**
 *
 * @author Marquete
 */
@Component
@Scope("request")
public class TemplateController extends SGBController<Livro, TemplateForm, LivroService> {

    @Autowired
    private TemplateForm form;
    @Autowired
    private LivroService service;
    
    private ItemListaCotacao itemListaCotacao;

    public TemplateController() {
        form = new TemplateForm();
        service = new LivroService();
    }

    @Autowired
    public TemplateForm getForm() {
        return form;
    }

    public void setForm(TemplateForm form) {
        this.form = form;
    }

    public LivroService getService() {
        return service;
    }

    public void setService(LivroService service) {
        this.service = service;
    }

    public Collection<Livro> completeLivro(String query) {
        return this.service.searchByAttributes(query, "titulo");
    }

    public void handleUnselectLivro(UnselectEvent event) {

        this.getForm().setLivroSelecionado(null);
    }

    public void addLivroOnSelect(SelectEvent event) {
        Livro livroSelecionado = (Livro) event.getObject();
        Collection<Autor> autores = this.getService().getDAO().getAutores(livroSelecionado.getId());
        livroSelecionado.setAutores(autores);
        this.getForm().setEntity(livroSelecionado);
        ItemListaCotacao itemListaCotacao = new ItemListaCotacao();
        itemListaCotacao.setLivro(livroSelecionado);
        itemListaCotacao.setValorMedio(getValorMedio());
        this.itemListaCotacao = itemListaCotacao;
    }

    public Collection<Autor> buscaAutores() {
        Livro livro = this.getForm().getEntity();
        Collection<Autor> autores = new ArrayList<Autor>();
        if (livro != null) {
            autores = this.getService().getDAO().getAutores(livro.getId());
        }
        return autores;
    }

    public double getValorMedio() {

        double mediaCotacoes = 0;

        try {
            if (!this.getForm().getEntity().isEstrangeiro()) {
                mediaCotacoes = getValorMedioNacional();
            } else {
                mediaCotacoes = getValorMedioEstrangeiro();
            }
        } catch (Exception e) {            
            this.addErrorMessage("Conexão com os serviços de cotação indisponível!");
            return -1.0;
        }

        if (mediaCotacoes > 0) {
            return mediaCotacoes;
        }
        return Math.max(0.0, mediaCotacoes);
    }

    private double getValorMedioNacional() {

        CotadorBuscape cotadorBuscape = new CotadorBuscape();
        double somaCotacoesNacionais = 0;
        double valorMedioNacional = 0;

        Livro livroParaCotacao = (Livro) this.getForm().getEntity();
        ArrayList<ResultadoCotacao> cotacoesBuscape =
                new ArrayList<ResultadoCotacao>(cotadorBuscape.buscarOfertas(livroParaCotacao.getIsbn13()));

        if (cotacoesBuscape.size() > 0) {

            for (int i = 0; i < cotacoesBuscape.size(); i++) {
                somaCotacoesNacionais += Double.valueOf(cotacoesBuscape.get(i).getOfertaLivro().getPrecoLivro());
            }
            valorMedioNacional = somaCotacoesNacionais / cotacoesBuscape.size();

        }

        return valorMedioNacional;
    }

    private double getValorMedioEstrangeiro() {

        CotadorGoogleShop cotadorGoogleShop = new CotadorGoogleShop();
        double somaCotacoesEstrageiras = 0;
        double valorMedioEstrangeiro = 0;

        Livro livroParaCotacao = (Livro) this.getForm().getEntity();
        ArrayList<ResultadoCotacao> cotacoesGoogleShop =
                new ArrayList<ResultadoCotacao>(cotadorGoogleShop.buscarOfertas(livroParaCotacao.getIsbn13()));

        if (cotacoesGoogleShop.size() > 0) {

            for (int i = 0; i < cotacoesGoogleShop.size(); i++) {
                somaCotacoesEstrageiras += Double.valueOf(cotacoesGoogleShop.get(i).getOfertaLivro().getPrecoLivro());
            }
            valorMedioEstrangeiro = somaCotacoesEstrageiras / cotacoesGoogleShop.size();

        }

        return valorMedioEstrangeiro;
    }

    public ItemListaCotacao getItemListaCotacao() {
        return itemListaCotacao;
    }
    
}
