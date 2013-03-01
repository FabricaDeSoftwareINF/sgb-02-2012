package br.ufg.inf.es.web.controller;

import br.ufg.inf.es.base.validation.ValidationException;
import br.ufg.inf.es.integracao.ListaCotacaoService;
import br.ufg.inf.es.integracao.LivroService;
import br.ufg.inf.es.web.datamodel.ListaCotacaoDataModel;
import br.ufg.inf.es.integracao.exportacaodados.planilha.ExportacaoPlanilhaService;
import br.ufg.inf.es.model.Autor;
import br.ufg.inf.es.model.Bibliografia;
import br.ufg.inf.es.model.ItemListaCotacao;
import br.ufg.inf.es.model.ListaCotacao;
import br.ufg.inf.es.model.Livro;
import br.ufg.inf.es.model.exportacaodados.planilha.ItemPlanilha;
import br.ufg.inf.es.model.exportacaodados.planilha.Planilha;
import br.ufg.inf.es.web.controller.form.ListaCotacaoForm;
import java.io.ByteArrayInputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
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
    @Autowired
    private LivroService livroService;
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
    public String openInitialPage() {
        this.getService().getDAO().closeSession();
        List<ListaCotacao> listas = new ArrayList(this.getService().
                listByUser(usuarioController.getUsuarioLogado()));
        ListaCotacaoDataModel lista = new ListaCotacaoDataModel(listas);
        this.getForm().setListaCotacaoDataModel(lista);
        return super.openInitialPage();
    }

    @Override
    public String openViewPage(ListaCotacao listaCotacao) {
        Collection<ItemListaCotacao> itens = listaCotacao.getItensListaCotacao();
        if (itens != null) {
            for (ItemListaCotacao item : itens) {
                Livro livro = item.getLivro();
                Collection<Autor> autores = this.livroService.getDAO().getAutores(livro.getId());
                Collection<Bibliografia> bibliografias = this.livroService.getDAO().getBibliografia(livro.getId());
                livro.setAutores(autores);
                livro.setBibliografias(bibliografias);
            }
        }
        this.getForm().setEntity(listaCotacao);
        return super.openViewPage();
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

    public String gerarListaOtimizada() {

        List<ItemListaCotacao> listaOtimizada = this.getService().gerarListaOtimizada(this.getForm().getValorOrcamento(), this.getForm().getEntity(), this.getForm().isTipoOtimizacao());

        this.getForm().setListaOtimizada(listaOtimizada);

        exibirTotalListaOtimizada();

        return this.getRootNavigation() + "listaOtimizada";
    }

    public String removerListasSelecionadas() {
        if (!this.form.getListasSelecionadas().isEmpty()) {

            try {
                this.service.getDAO().removeAll(this.form.getListasSelecionadas());
                List<ListaCotacao> listas = new ArrayList(this.getService().
                        listByUser(usuarioController.getUsuarioLogado()));
                ListaCotacaoDataModel lista = new ListaCotacaoDataModel(listas);
                this.getForm().setListaCotacaoDataModel(lista);
            } catch (ConstraintViolationException cve) {
                cve.printStackTrace();
                this.addWarningMessage(cve.getMessage());
            } catch (Exception e) {
                e.printStackTrace();
                this.addWarningMessage(e.getMessage());
            }
            this.form.setListasSelecionadas(new ArrayList<ListaCotacao>());
        } else {
            this.addWarningMessage("arquitetura.msg.nenhumitemselecionado");
        }
        return super.openInitialPage();
    }

    public String editarListaCotacao() {
        try {
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
    public void templateExportarXLS(Collection<ItemListaCotacao> cotacoes) {

        Planilha planilhaNacionais = new Planilha();
        Planilha planilhaEstrangeiros = new Planilha();
        List<ItemListaCotacao> cotacoesLivro = new ArrayList(cotacoes);
        StringBuilder builder = new StringBuilder();

        //Monta o título do cabeçalho da planilha
        SimpleDateFormat formatadorData = new SimpleDateFormat("yyyy");
        String anoCotacao = formatadorData.format(form.getEntity().getDataRealizada());
        String nomeAutor = this.getForm().getEntity().getUser().getNome() == null
                ? "" : this.getForm().getEntity().getUser().getNome();
        String sobreNomeAutor = this.getForm().getEntity().getUser().getSobrenome() == null
                ? "" : this.getForm().getEntity().getUser().getSobrenome();

        String tituloPlanilhaNacionais = builder.append("LISTA DOS TÍTULOS NACIONAIS A SEREM ADQUIRIDOS EM ").
                append(anoCotacao).append(" - BIBLIOTECA CENTRAL ").append("/ ").append(nomeAutor.toUpperCase()).
                append(" ").append(sobreNomeAutor.toUpperCase()).toString();
        builder = new StringBuilder();

        String tituloPlanilhaEstrangeiros = builder.append("LISTA DOS TÍTULOS ESTRANGEIROS A SEREM ADQUIRIDOS EM ").
                append(anoCotacao).append(" - BIBLIOTECA CENTRAL ").append("/ ").append(nomeAutor.toUpperCase()).
                append(" ").append(sobreNomeAutor.toUpperCase()).toString();

        planilhaNacionais.setTituloCabecalho(tituloPlanilhaNacionais);
        planilhaEstrangeiros.setTituloCabecalho(tituloPlanilhaEstrangeiros);

        for (int i = 0; i < cotacoesLivro.size(); i++) {

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

    public void exportarXLS() {

        this.templateExportarXLS(this.getForm().getEntity().getItensListaCotacao());
    }

    public void exportarListaOtimizadaXLS() {

        this.templateExportarXLS(this.getForm().getListaOtimizada());
    }

    /**
     * *
     * Permite exportar os livros nacionais da lista de cotações para o formato
     * CSV, disponibilizando-os para download.
     */
    public void templateExportarCSV(Collection<ItemListaCotacao> cotacoes, boolean isNacional) {

        Planilha planilha = new Planilha();
        List<ItemListaCotacao> cotacoesLivro = new ArrayList(cotacoes);

        for (int i = 0; i < cotacoesLivro.size(); i++) {

            boolean isEstrangeiro = cotacoesLivro.get(i).getLivro().isEstrangeiro();

            if ((isNacional && !isEstrangeiro) || (!isNacional && isEstrangeiro)) {

                ItemPlanilha itemPlanilha = montarItemPlanilha(cotacoesLivro.get(i), i + 1);
                planilha.getLinhasPlanilha().add(itemPlanilha);
            }

        }

        arrayBytes = exportador.gerarPlanilhaCSV(planilha);
        stream = new ByteArrayInputStream(arrayBytes);

        if (isNacional) {
            arquivoCSVNacionais = new DefaultStreamedContent(stream, "text/csv", "planilhaNacionais.csv");
        } else {
            arquivoCSVEstrangeiros = new DefaultStreamedContent(stream, "text/csv", "planilhaEstrangeiros.csv");
        }

    }

    public void exportarCSVNacionais() {

        this.templateExportarCSV(this.getForm().getEntity().getItensListaCotacao(), true);
    }

    public void exportarListaOtimizadaCSVNacionais() {

        this.templateExportarCSV(this.getForm().getListaOtimizada(), true);
    }

    /**
     * *
     * Permite exportar os livros estrangeiros da lista de cotações para o
     * formato CSV, disponibilizando-os para download.
     */
    public void exportarCSVEstrangeiros() {

        this.templateExportarCSV(this.getForm().getEntity().getItensListaCotacao(), false);
    }

    public void exportarListaOtimizadaCSVEstrangeiros() {

        this.templateExportarCSV(this.getForm().getListaOtimizada(), false);
    }

    /**
     * *
     * Monta um objeto ItemPlanilha com base num objeto Cotacao.
     *
     * @param cotacao Cotacao a ser convertida em um item de planilha.
     * @param numItem Número do item de planilha, identificador único desta.
     * @return Objteo ItemPlanilha devidamente montado.
     */
    private ItemPlanilha montarItemPlanilha(ItemListaCotacao cotacoesLivro, int numItem) {

        StringBuilder nomeAutor = new StringBuilder();
        StringBuilder nomeCurso = new StringBuilder();
        int indice = 0;
        ItemPlanilha itemPlanilha = new ItemPlanilha();
        itemPlanilha.setNumItem(numItem);
        itemPlanilha.setTituloObra(cotacoesLivro.getLivro().getTitulo());

        List<Autor> autores = new ArrayList<Autor>(cotacoesLivro.getLivro().getAutores());

        if (autores != null && !autores.isEmpty()) {
            nomeAutor.append(autores.get(indice).getSobrenome().toUpperCase()).append(",").
                    append(autores.get(indice).getNome());
        } else {
            nomeAutor.append("");
        }

        itemPlanilha.setNomeAutor(nomeAutor.toString());

        itemPlanilha.setEdicao(cotacoesLivro.getLivro().getEdicao());
        itemPlanilha.setEditora(cotacoesLivro.getLivro().getEditora().getNome());
        itemPlanilha.setAno(cotacoesLivro.getLivro().getAno().toString());

        List<Bibliografia> bibliografias = new ArrayList<Bibliografia>(cotacoesLivro.getLivro().getBibliografias());
        if (bibliografias != null && !bibliografias.isEmpty()) {
            nomeCurso = new StringBuilder();
            nomeCurso.append(bibliografias.get(indice).getDisciplina().getCurso().getNome());
        } else {
            nomeCurso.append("");
        }


        itemPlanilha.setCursoDestino(nomeCurso.toString());

        itemPlanilha.setValorMedioUnitario(cotacoesLivro.getValorMedio());
        itemPlanilha.setQuantExemplares(cotacoesLivro.getQuantidadeAComprar());
        itemPlanilha.setAreaConhecimento(AREA_CONHECIMENTO);

        return itemPlanilha;

    }

    public void atualizaTotal() {

        this.getForm().getEntity().getValor();
    }

    public void exibirTotalListaOtimizada() {

        ListaCotacao lc = new ListaCotacao();

        lc.setItensListaCotacao(this.getForm().getListaOtimizada());

        this.getForm().setValorTotalListaOtimizada(lc.getValor());
    }
}
