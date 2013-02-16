package br.ufg.inf.es.web.controller;

import br.ufg.inf.es.integracao.ListaCotacaoService;
import br.ufg.inf.es.integracao.exportacaodados.planilha.ExportacaoPlanilhaService;
import br.ufg.inf.es.model.Cotacao;
import br.ufg.inf.es.model.ListaCotacao;
import br.ufg.inf.es.model.exportacaodados.planilha.ItemPlanilha;
import br.ufg.inf.es.web.controller.form.ListaCotacaoForm;
import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.List;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Controlador para entidade Lista de Cotações.
 *
 * @author Bruno Marquete da Silva
 */
@Component
@Scope("session")
public class ListaCotacaoController extends SGBController<ListaCotacao, ListaCotacaoForm, ListaCotacaoService> {

    @Autowired
    private ListaCotacaoForm form;
    @Autowired
    private ListaCotacaoService service;
    
    private StreamedContent arquivoXLS;
    private StreamedContent arquivoCSVNacionais;
    private StreamedContent arquivoCSVEstrangeiros;
    
    ExportacaoPlanilhaService exportador = new ExportacaoPlanilhaService();
    byte[] arrayBytes;
    ByteArrayInputStream stream;

    @Override
    public void initData() {
        this.getForm().setTabelaListaCotacoes(new ArrayList<ListaCotacao>());
        this.getForm().getTabelaListaCotacoes().addAll(this.getService().list());
    }

    @Override
    public ListaCotacaoForm getForm() {
        return form;
    }

    @Override
    public ListaCotacaoService getService() {
        return service;
    }

    public StreamedContent getArquivoCSVEstrangeiros() {
        return arquivoCSVEstrangeiros;
    }

    public StreamedContent getArquivoCSVNacionais() {
        return arquivoCSVNacionais;
    }

    public StreamedContent getArquivoXLS() {
        return arquivoXLS;
    }

    /***
     * Permite exportar a lista de cotações para o formato XLS, disponibilizando-a
     * para download. São inclusos tantos os livros nacionais quanto estrangeiros,
     * cada qual em uma planilha do arquivo.
     */
    public void exportarXLS() {

        List<ItemPlanilha> linhasPlanilhaNacionais = new ArrayList<ItemPlanilha>();
        List<ItemPlanilha> linhasPlanilhaEstrangeiros = new ArrayList<ItemPlanilha>();

        for (int i = 0; i < form.getEntity().getCotacoes().size(); i++) {

            ItemPlanilha itemPlanilha = montarItemPlanilha(form.getEntity().getCotacoes().get(i), i + 1);

            if (form.getEntity().getCotacoes().get(i).getLivro().isEstrangeiro() == false) {
                linhasPlanilhaNacionais.add(itemPlanilha);
            } else {
                linhasPlanilhaEstrangeiros.add(itemPlanilha);
            }

            arrayBytes = exportador.gerarPlanilhaXLS(linhasPlanilhaNacionais, linhasPlanilhaEstrangeiros);
            stream = new ByteArrayInputStream(arrayBytes);

            this.arquivoXLS = new DefaultStreamedContent(stream, "application/vnd.ms-excel", "planilha.xls");

        }

    }

    /***
     * Permite exportar os livros nacioanais da lista de cotações para o formato
     * CSV, disponibilizando-os para download.
     */
    public void exportarCSVNacionais() {

        List<ItemPlanilha> linhasPlanilhaNacionais = new ArrayList<ItemPlanilha>();

        for (int i = 0; i < form.getEntity().getCotacoes().size(); i++) {

            if (form.getEntity().getCotacoes().get(i).getLivro().isEstrangeiro() == false) {

                ItemPlanilha itemPlanilha = montarItemPlanilha(form.getEntity().getCotacoes().get(i), i + 1);
                linhasPlanilhaNacionais.add(itemPlanilha);
            }

        }

        arrayBytes = exportador.gerarPlanilhaCSV(linhasPlanilhaNacionais);
        stream = new ByteArrayInputStream(arrayBytes);
        arquivoCSVNacionais = new DefaultStreamedContent(stream, "text/csv", "planilha.csv");

    }

    /***
     * Permite exportar os livros estrangeiros da lista de cotações para o formato
     * CSV, disponibilizando-os para download.
     */
    public void exportarCSVEstrangeiros() {

        List<ItemPlanilha> linhasPlanilhaEstrangeiros = new ArrayList<ItemPlanilha>();

        for (int i = 0; i < form.getEntity().getCotacoes().size(); i++) {

            if (form.getEntity().getCotacoes().get(i).getLivro().isEstrangeiro() == true) {

                ItemPlanilha itemPlanilha = montarItemPlanilha(form.getEntity().getCotacoes().get(i), i + 1);
                linhasPlanilhaEstrangeiros.add(itemPlanilha);
            }

        }

        arrayBytes = exportador.gerarPlanilhaCSV(linhasPlanilhaEstrangeiros);
        stream = new ByteArrayInputStream(arrayBytes);
        arquivoCSVEstrangeiros = new DefaultStreamedContent(stream, "text/csv", "planilha.csv");

    }

    /***
     * Monta um objeto ItemPlanilha com base num objeto Cotacao.
     * @param cotacao
     * Cotacao a ser convertida em um item de planilha.
     * @param numItem
     * Número do item de planilha, identificador único desta.
     * @return 
     * Objteo ItemPlanilha devidamente montado.
     */
    private ItemPlanilha montarItemPlanilha(Cotacao cotacao, int numItem) {

        final String AREA_CONHECIMENTO = "Ciências Exatas e da Terra";
        ItemPlanilha itemPlanilha = new ItemPlanilha();
        itemPlanilha.setNumItem(numItem);
        itemPlanilha.setTituloObra(cotacao.getLivro().getTitulo());
        itemPlanilha.setNomeAutor(cotacao.getLivro().getAutoresAsString());
        itemPlanilha.setEdicao(cotacao.getLivro().getEdicao());
        itemPlanilha.setEditora(cotacao.getLivro().getEditora().getNome());
        itemPlanilha.setAno(cotacao.getLivro().getAno().toString());
        itemPlanilha.setCursoDestino("Engenharia de Software");
        itemPlanilha.setValorMedioUnitario(cotacao.getValor());
        itemPlanilha.setQuantExemplares(cotacao.getQuantidade());
        itemPlanilha.setAreaConhecimento(AREA_CONHECIMENTO);

        return itemPlanilha;

    }
}