package br.ufg.inf.es.web.controller;

import br.ufg.inf.es.integracao.ListaCotacaoService;
import br.ufg.inf.es.integracao.exportacaodados.planilha.ExportacaoPlanilhaService;
import br.ufg.inf.es.model.Cotacao;
import br.ufg.inf.es.model.ItemPlanilha;
import br.ufg.inf.es.model.ListaCotacao;
import br.ufg.inf.es.web.controller.form.ListaCotacaoForm;
import java.io.ByteArrayInputStream;
import java.io.FileOutputStream;
import java.nio.charset.Charset;
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
    private StreamedContent arquivoCSV;

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

    public StreamedContent getArquivoCSV() {
        return arquivoCSV;
    }

    public StreamedContent getArquivoXLS() {
        return arquivoXLS;
    }

    public void exportarXLS(ListaCotacao listaCotacao) {

        ExportacaoPlanilhaService exportador = new ExportacaoPlanilhaService();
        List<ItemPlanilha> linhasPlanilhaNacionais = new ArrayList<ItemPlanilha>();
        List<ItemPlanilha> linhasPlanilhaEstrangeiros = new ArrayList<ItemPlanilha>();

        for (int i = 0; i < listaCotacao.getCotacoes().size(); i++) {

            ItemPlanilha itemPlanilha = montarItemPlanilha(listaCotacao.getCotacoes().get(i), i + 1);

            if (listaCotacao.getCotacoes().get(i).getLivro().isEstrangeiro() == false) {
                linhasPlanilhaNacionais.add(itemPlanilha);
            } else {
                linhasPlanilhaEstrangeiros.add(itemPlanilha);
            }

            FileOutputStream out = exportador.gerarPlanilhaXLS(linhasPlanilhaNacionais, linhasPlanilhaEstrangeiros);
            ByteArrayInputStream stream = new ByteArrayInputStream(out.toString().getBytes(Charset.forName("UTF-8")));

            this.arquivoXLS = new DefaultStreamedContent(stream, "application/vnd.ms-excel", "planilha.xls");

        }
    }

    public void exportarCSV(ListaCotacao listaCotacao, boolean nacionais, boolean estrangeiros) {

        ExportacaoPlanilhaService exportador = new ExportacaoPlanilhaService();
        String strCSV = "";

        if (nacionais == true && estrangeiros == false) {

            List<ItemPlanilha> linhasPlanilhaNacionais = new ArrayList<ItemPlanilha>();

            for (int i = 0; i < listaCotacao.getCotacoes().size(); i++) {

                if (listaCotacao.getCotacoes().get(i).getLivro().isEstrangeiro() == false) {

                    ItemPlanilha itemPlanilha = montarItemPlanilha(listaCotacao.getCotacoes().get(i), i + 1);
                    linhasPlanilhaNacionais.add(itemPlanilha);
                }

                strCSV = exportador.gerarPlanilhaCSV(linhasPlanilhaNacionais);

            }

        } else if (nacionais == false && estrangeiros == true) {

            List<ItemPlanilha> linhasPlanilhaEstrangeiros = new ArrayList<ItemPlanilha>();

            for (int i = 0; i < listaCotacao.getCotacoes().size(); i++) {

                if (listaCotacao.getCotacoes().get(i).getLivro().isEstrangeiro() == true) {

                    ItemPlanilha itemPlanilha = montarItemPlanilha(listaCotacao.getCotacoes().get(i), i + 1);
                    linhasPlanilhaEstrangeiros.add(itemPlanilha);
                }

                strCSV = exportador.gerarPlanilhaCSV(linhasPlanilhaEstrangeiros);

            }
        }

        ByteArrayInputStream stream = new ByteArrayInputStream(strCSV.getBytes(Charset.forName("UTF-8")));
        arquivoCSV = new DefaultStreamedContent(stream, "text/csv", "planilha.csv");
    }

    private ItemPlanilha montarItemPlanilha(Cotacao cotacao, int numItem) {

        final String AREA_CONHECIMENTO = "Ciências Estas e da Terra";
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