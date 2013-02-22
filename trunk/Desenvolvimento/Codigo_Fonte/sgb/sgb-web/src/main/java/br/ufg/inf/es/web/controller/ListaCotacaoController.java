package br.ufg.inf.es.web.controller;

import br.ufg.inf.es.base.validation.ValidationException;
import br.ufg.inf.es.integracao.ListaCotacaoService;
import br.ufg.inf.es.integracao.exportacaodados.planilha.ExportacaoPlanilhaService;
import br.ufg.inf.es.model.Autor;
import br.ufg.inf.es.model.Bibliografia;
import br.ufg.inf.es.model.CotacoesLivro;
import br.ufg.inf.es.model.ListaCotacao;
import br.ufg.inf.es.model.exportacaodados.planilha.ItemPlanilha;
import br.ufg.inf.es.model.exportacaodados.planilha.Planilha;
import br.ufg.inf.es.web.controller.form.ListaCotacaoForm;
import java.io.ByteArrayInputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.exception.ConstraintViolationException;
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
    private ExportacaoPlanilhaService exportador = new ExportacaoPlanilhaService();
    private byte[] arrayBytes;
    private ByteArrayInputStream stream;
    private static final String AREA_CONHECIMENTO = "Ciências Exatas e da Terra";
    @Autowired
    private UsuarioController usuarioController = new UsuarioController();

    @Override
    public void initData() {
        this.getForm().setTabelaListaCotacoes(new ArrayList<ListaCotacao>());
        this.getForm().getTabelaListaCotacoes().addAll(this.getService().
                listByUser(usuarioController.getUsuarioLogado()));
    }

    @Override
    public ListaCotacaoForm getForm() {
        return form;
    }

    @Override
    public ListaCotacaoService getService() {
        return service;
    }

    public void selecionaLista(ListaCotacao listaSelecionada) {
        if (this.form.getListasSelecionadas().contains(listaSelecionada)) {
            this.form.getListasSelecionadas().remove(listaSelecionada);
        } else {
            this.form.getListasSelecionadas().add(listaSelecionada);
        }
    }

    public String removerListasSelecionadas() {

        if (!this.form.getListasSelecionadas().isEmpty()) {

            try {
                this.service.getDAO().removeAll(this.form.getListasSelecionadas());
            } catch (ConstraintViolationException cve) {
                this.addWarningMessage(cve.getMessage());
            } catch (Exception e) {
                this.addWarningMessage(e.getMessage());
            }

            this.form.setListasSelecionadas(new ArrayList<ListaCotacao>());
            this.form.setCollectionEntities(this.service.getDAO().list());

        } else {
            this.addWarningMessage("arquitetura.msg.nenhumitemselecionado");
        }

        return super.openInitialPage();
    }

    public String editarListaCotacao() {
        try {
            this.getForm().getCollectionEntities().remove(this.getForm().getEntity());
            this.getService().editar(this.getForm().getEntity());
            addSuccessMessage(EditoraController.KEY_MSG_SUCESSO);
        } catch (ValidationException ve) {
            this.getService().getDAO().closeSession();
            addWarningMessage(ve.getKeyMessage());
        }
        return super.openInitialPage();
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

    /**
     * *
     * Permite exportar a lista de cotações para o formato XLS,
     * disponibilizando-a para download. São inclusos tantos os livros nacionais
     * quanto estrangeiros, cada qual em uma planilha do arquivo.
     */
    public void exportarXLS() {

        Planilha planilhaNacionais = new Planilha();
        Planilha planilhaEstrangeiros = new Planilha();
        List<CotacoesLivro> cotacoesLivro = new ArrayList(form.getEntity().getCotacoesLivro());
        StringBuilder builder = new StringBuilder();

        //Monta o título do cabeçalho da planilha
        SimpleDateFormat formatadorData = new SimpleDateFormat("yyyy");
        String anoCotacao = formatadorData.format(form.getEntity().getDataRealizada());
        String nomeAutor = this.getForm().getEntity().getUser().getNome();
        String sobreNomeAutor = this.getForm().getEntity().getUser().getSobrenome();

        String tituloPlanilhaNacionais = builder.append("LISTA DOS TÍTULOS NACIONAIS A SEREM ADQUIRIDOS EM ").
                append(anoCotacao).append(" - BIBLIOTECA CENTRAL ").append("/ ").append(nomeAutor.toUpperCase()).
                append(" ").append(sobreNomeAutor.toUpperCase()).toString();
        builder = new StringBuilder();

        String tituloPlanilhaEstrangeiros = builder.append("LISTA DOS TÍTULOS ESTRANGEIROS A SEREM ADQUIRIDOS EM ").
                append(anoCotacao).append(" - BIBLIOTECA CENTRAL ").append("/ ").append(nomeAutor.toUpperCase()).
                append(" ").append(sobreNomeAutor.toUpperCase()).toString();

        planilhaNacionais.setTituloCabecalho(tituloPlanilhaNacionais);
        planilhaEstrangeiros.setTituloCabecalho(tituloPlanilhaEstrangeiros);

        for (int i = 0; i < form.getEntity().getCotacoesLivro().size(); i++) {

            ItemPlanilha itemPlanilha = montarItemPlanilha(cotacoesLivro.get(i), i + 1);

            boolean isEstrangeiro = cotacoesLivro.get(i).getLivro().isEstrangeiro();
            if (!isEstrangeiro) {
                planilhaNacionais.getLinhasPlanilha().add(itemPlanilha);
            } else {
                planilhaEstrangeiros.getLinhasPlanilha().add(itemPlanilha);
            }

            arrayBytes = exportador.gerarPlanilhaXLS(planilhaNacionais, planilhaEstrangeiros);
            stream = new ByteArrayInputStream(arrayBytes);

            this.arquivoXLS = new DefaultStreamedContent(stream, "application/vnd.ms-excel", "planilha.xls");

        }

    }

    /**
     * *
     * Permite exportar os livros nacionais da lista de cotações para o formato
     * CSV, disponibilizando-os para download.
     */
    public void exportarCSVNacionais() {

        Planilha planilhaNacionais = new Planilha();
        List<CotacoesLivro> cotacoesLivro = new ArrayList(form.getEntity().getCotacoesLivro());

        for (int i = 0; i < form.getEntity().getCotacoesLivro().size(); i++) {

            boolean isEstrangeiro = cotacoesLivro.get(i).getLivro().isEstrangeiro();
            if (!isEstrangeiro) {

                ItemPlanilha itemPlanilha = montarItemPlanilha(cotacoesLivro.get(i), i + 1);
                planilhaNacionais.getLinhasPlanilha().add(itemPlanilha);
            }

        }

        arrayBytes = exportador.gerarPlanilhaCSV(planilhaNacionais);
        stream = new ByteArrayInputStream(arrayBytes);
        arquivoCSVNacionais = new DefaultStreamedContent(stream, "text/csv", "planilha.csv");

    }

    /**
     * *
     * Permite exportar os livros estrangeiros da lista de cotações para o
     * formato CSV, disponibilizando-os para download.
     */
    public void exportarCSVEstrangeiros() {

        Planilha planilhaEstrangeiros = new Planilha();
        List<CotacoesLivro> cotacoesLivro = new ArrayList(form.getEntity().getCotacoesLivro());

        for (int i = 0; i < form.getEntity().getCotacoesLivro().size(); i++) {

            boolean isEstrangeiro = cotacoesLivro.get(i).getLivro().isEstrangeiro();
            if (isEstrangeiro) {

                ItemPlanilha itemPlanilha = montarItemPlanilha(cotacoesLivro.get(i), i + 1);
                planilhaEstrangeiros.getLinhasPlanilha().add(itemPlanilha);
            }

        }

        arrayBytes = exportador.gerarPlanilhaCSV(planilhaEstrangeiros);
        stream = new ByteArrayInputStream(arrayBytes);
        arquivoCSVEstrangeiros = new DefaultStreamedContent(stream, "text/csv", "planilha.csv");

    }

    /**
     * *
     * Monta um objeto ItemPlanilha com base num objeto Cotacao.
     *
     * @param cotacao Cotacao a ser convertida em um item de planilha.
     * @param numItem Número do item de planilha, identificador único desta.
     * @return Objteo ItemPlanilha devidamente montado.
     */
    private ItemPlanilha montarItemPlanilha(CotacoesLivro cotacoesLivro, int numItem) {

        StringBuilder nomeAutor = new StringBuilder();
        ItemPlanilha itemPlanilha = new ItemPlanilha();
        itemPlanilha.setNumItem(numItem);
        itemPlanilha.setTituloObra(cotacoesLivro.getLivro().getTitulo());

        List<Autor> autores = new ArrayList<Autor>(cotacoesLivro.getLivro().getAutores());
        for (int i = 0; i < autores.size(); i++) {

            nomeAutor.append(autores.get(i).getSobrenome().toUpperCase()).append(",").
                    append(autores.get(i).getNome()).append(System.getProperty("line.separator"));

        }
        itemPlanilha.setNomeAutor(nomeAutor.toString());

        itemPlanilha.setEdicao(cotacoesLivro.getLivro().getEdicao());
        itemPlanilha.setEditora(cotacoesLivro.getLivro().getEditora().getNome());
        itemPlanilha.setAno(cotacoesLivro.getLivro().getAno().toString());

        String cursoDestino = "";
        List<Bibliografia> bibliografias = new ArrayList<Bibliografia>(cotacoesLivro.getLivro().getBibliografias());
        for (int i = 0; i < bibliografias.size(); i++) {

            nomeAutor = new StringBuilder();
            nomeAutor.append(bibliografias.get(i).getDisciplina().getCurso().getNome()).
                    append(System.getProperty("line.separator"));

            cursoDestino += nomeAutor.toString();

        }
        itemPlanilha.setCursoDestino(cursoDestino);

        itemPlanilha.setValorMedioUnitario(cotacoesLivro.getValorMedio());
        itemPlanilha.setQuantExemplares(cotacoesLivro.getQuantidade());
        itemPlanilha.setAreaConhecimento(AREA_CONHECIMENTO);

        return itemPlanilha;

    }
}
