package br.ufg.inf.es.web.controller;

import br.ufg.inf.es.integracao.LivroService;
import br.ufg.inf.es.integracao.cotacao.CotadorBuscape;
import br.ufg.inf.es.integracao.cotacao.CotadorGoogleShop;
import br.ufg.inf.es.integracao.cotacao.ResultadoCotacao;
import br.ufg.inf.es.model.CotacoesLivro;
import br.ufg.inf.es.model.Livro;
import br.ufg.inf.es.web.controller.form.LivroForm;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collection;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 *
 * @author Marquete
 */
@Component
@Scope("session")
public class TemplateController extends SGBController<Livro, LivroForm, LivroService> {

    @Autowired
    private LivroForm form;
    @Autowired
    private LivroService service;
    private Collection<Livro> livros;

    public TemplateController() {
        form = new LivroForm();
        service = new LivroService();
        livros = new ArrayList<Livro>();
    }

    @Autowired
    public LivroForm getForm() {
        return form;
    }

    public void setForm(LivroForm form) {
        this.form = form;
    }

    public LivroService getService() {
        return service;
    }

    public void setService(LivroService service) {
        this.service = service;
    }

    public Collection<Livro> getLivros() {
        return livros;
    }

    public void setLivros(Collection<Livro> livros) {
        this.livros = livros;
    }

    public Collection<Livro> completeLivro(String query) {

        Livro livroSelecionado = this.getForm().getLivroSelecionado();

        if (livroSelecionado == null) {

            livros = this.service.searchByAttributes(query, "titulo");

        } else {

            livros = new ArrayList<Livro>();
        }

        return livros;
    }

    public void handleUnselectLivro(UnselectEvent event) {

        this.getForm().setLivroSelecionado(null);
    }

    public void addLivroOnSelect(SelectEvent event) {

        Livro livroSelecionado = (Livro) event.getObject();
        this.getForm().setLivroSelecionado(livroSelecionado);

    }

    public String getValorMedio() {

        double mediaCotacoes = 0;

        if (this.getForm().getEntity().isEstrangeiro() == false) {
            mediaCotacoes = getValorMedioNacional();
        } else {
            mediaCotacoes = getValorMedioEstrangeiro();
        }

        if (mediaCotacoes > 0) {
            DecimalFormat decimalFormat = new DecimalFormat(); 
            decimalFormat.setMaximumFractionDigits(2); 
            return decimalFormat.format(mediaCotacoes);
        } else {
            return "Indisponível.";
        }

    }

    private double getValorMedioNacional() {

        CotadorBuscape cotadorBuscape = new CotadorBuscape();
        double somaCotacoesNacionais = 0;
        double valorMedioNacional = 0;

        Livro livro = (Livro) this.getForm().getEntity();
        ArrayList<ResultadoCotacao> cotacoesBuscape =
                new ArrayList<ResultadoCotacao>(cotadorBuscape.buscarOfertas(livro.getIsbn13()));

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

        Livro livro = (Livro) this.getForm().getEntity();
        ArrayList<ResultadoCotacao> cotacoesGoogleShop =
                new ArrayList<ResultadoCotacao>(cotadorGoogleShop.buscarOfertas(livro.getIsbn13()));

        if (cotacoesGoogleShop.size() > 0) {

            for (int i = 0; i < cotacoesGoogleShop.size(); i++) {
                somaCotacoesEstrageiras += Double.valueOf(cotacoesGoogleShop.get(i).getOfertaLivro().getPrecoLivro());
            }
            valorMedioEstrangeiro = somaCotacoesEstrageiras / cotacoesGoogleShop.size();

        }

        return valorMedioEstrangeiro;
    }
}
