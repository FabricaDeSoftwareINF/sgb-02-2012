package br.ufg.inf.es.integracao.exportacaodados.planilha;

import br.ufg.inf.es.model.ItemPlanilha;
import java.io.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.poi.hssf.usermodel.*;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

/**
 * Classe de serviços de exportação da planilha de cotação.
 *
 * @author Bruno Marquete da Silva
 */
@Component
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class ExportacaoPlanilhaService {

    private HSSFWorkbook geradorPlanilha;
    private final short COLUNA0 = 0;
    private final short COLUNA1 = 1;
    private final short COLUNA2 = 2;
    private final short COLUNA3 = 3;
    private final short COLUNA4 = 4;
    private final short COLUNA5 = 5;
    private final short COLUNA6 = 6;
    private final short COLUNA7 = 7;
    private final short COLUNA8 = 8;
    private final short COLUNA9 = 9;
    private final short COLUNA10 = 10;
    private final short COLUNA11 = 11;
    private final short COLUNA12 = 12;
    private final short COLUNA13 = 13;
    private final short COLUNA14 = 14;
    private final short COLUNA15 = 15;
    private final short COLUNA16 = 16;
    private final short COLUNA17 = 17;
    private final short COLUNA18 = 18;

    /**
     * Gera planilha em formato XLS com base numa lista de itens de planilha dos
     * livros nacionais e outra dos títulos estrangeiros. Cada ItemPlanilha é
     * uma linha da planilha com suas devidas COLUNAs.
     *
     * @param linhasPlanilhaNacionais Lista de itens da planilha de livros
     * nacionais.
     * @param linhasPlanilhaEstrangeiros Lista de itens da planilha de livros
     * nacionais.
     */
    public void gerarPlanilhaXLS(List<ItemPlanilha> linhasPlanilhaNacionais,
            List<ItemPlanilha> linhasPlanilhaEstrangeiros) {

        FileOutputStream stream;

        try {

            HSSFSheet planilhaNacionais;
            HSSFSheet planilhaEstrangeiros;
            geradorPlanilha = new HSSFWorkbook();

            planilhaNacionais = geradorPlanilha.createSheet("Títulos Nacionais");
            planilhaEstrangeiros = geradorPlanilha.createSheet("Títulos Estrangeiros");

            popularPlanilha(planilhaNacionais, linhasPlanilhaNacionais);
            popularPlanilha(planilhaEstrangeiros, linhasPlanilhaEstrangeiros);

            ajustarTamanhoCelulas(planilhaNacionais);
            ajustarTamanhoCelulas(planilhaEstrangeiros);

            stream = new FileOutputStream("planilha.xls");
            geradorPlanilha.write(stream);

        } catch (FileNotFoundException ex) {
            Logger.getLogger(ExportacaoPlanilhaService.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ExportacaoPlanilhaService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Popula objeto planilha com base numa lista de itens de planilha.
     *
     * @param planilha Objeto de planilha a ser populado.
     * @param linhasPlanilha Lista de itens de planilha.
     * @return Objeto de planilha devidamente populado.
     */
    private HSSFSheet popularPlanilha(HSSFSheet planilha, List<ItemPlanilha> linhasPlanilha) {

        String strSimboloReal = "R$ ";
        gerarCabecalho(planilha);

        int i;
        for (i = 0; i < linhasPlanilha.size(); i++) {

            HSSFRow linha = planilha.createRow(i + 1);
            linha.createCell(COLUNA0).setCellValue(new HSSFRichTextString(String.valueOf(linhasPlanilha.get(i).getNumItem())));
            linha.createCell(COLUNA1).setCellValue(new HSSFRichTextString(linhasPlanilha.get(i).getNomeAutor()));
            linha.createCell(COLUNA2).setCellValue(new HSSFRichTextString(linhasPlanilha.get(i).getTituloObra()));
            linha.createCell(COLUNA3).setCellValue(new HSSFRichTextString(linhasPlanilha.get(i).getEdicao()));
            linha.createCell(COLUNA4).setCellValue(new HSSFRichTextString(linhasPlanilha.get(i).getEditora()));
            linha.createCell(COLUNA5).setCellValue(new HSSFRichTextString(linhasPlanilha.get(i).getLocal()));
            linha.createCell(COLUNA6).setCellValue(new HSSFRichTextString(linhasPlanilha.get(i).getAno()));
            linha.createCell(COLUNA7).setCellValue(new HSSFRichTextString(linhasPlanilha.get(i).getColecao()));
            linha.createCell(COLUNA8).setCellValue(new HSSFRichTextString(linhasPlanilha.get(i).getVolume()));
            linha.createCell(COLUNA9).setCellValue(new HSSFRichTextString(linhasPlanilha.get(i).getMatriculaSophiaConselheiro()));
            linha.createCell(COLUNA10).setCellValue(new HSSFRichTextString(linhasPlanilha.get(i).getCursoDestino().toString()));
            linha.createCell(COLUNA11).setCellValue(new HSSFRichTextString(linhasPlanilha.get(i).getUnidadeMedida()));
            linha.createCell(COLUNA12).setCellValue(new HSSFRichTextString(strSimboloReal.concat(String.valueOf(linhasPlanilha.get(i).getValorMedioUnitario()))));
            linha.createCell(COLUNA13).setCellValue(new HSSFRichTextString(strSimboloReal.concat(String.valueOf(linhasPlanilha.get(i).getValorMedioUnitario()))));
            linha.createCell(COLUNA14).setCellValue(new HSSFRichTextString(strSimboloReal.concat(String.valueOf(linhasPlanilha.get(i).getValorMedioUnitario()))));
            linha.createCell(COLUNA15).setCellValue(new HSSFRichTextString(strSimboloReal.concat(String.valueOf(linhasPlanilha.get(i).getValorMedioUnitario()))));
            double valorTotal = linhasPlanilha.get(i).getValorMedioUnitario() * linhasPlanilha.get(i).getQuantExemplares();
            linha.createCell(COLUNA16).setCellValue(new HSSFRichTextString(strSimboloReal.concat(String.valueOf(valorTotal))));
            linha.createCell(COLUNA17).setCellValue(new HSSFRichTextString(String.valueOf(linhasPlanilha.get(i).getQuantExemplares())));
            linha.createCell(COLUNA18).setCellValue(new HSSFRichTextString(linhasPlanilha.get(i).getAreaConhecimento()));

        }

        gerarRodape(planilha, linhasPlanilha);

        return planilha;
    }

    /**
     * Gera o cabeçalho de um objeto de planilha com os devidos nomes das
     * COLUNAs.
     *
     * @param planilha Objeto planilha a ter o cabeçalho gerado.
     * @return Objeto planiha com o cabeçalho devidamente gerado.
     */
    private HSSFSheet gerarCabecalho(HSSFSheet planilha) {

        //Cria o estilo a ser aplicado nas células do cabeçalho
        HSSFCellStyle estilo = geradorPlanilha.createCellStyle();
        estilo.setBorderTop(HSSFCellStyle.BORDER_MEDIUM);
        estilo.setBorderLeft(HSSFCellStyle.BORDER_MEDIUM);
        estilo.setBorderRight(HSSFCellStyle.BORDER_MEDIUM);
        estilo.setBorderBottom(HSSFCellStyle.BORDER_MEDIUM);
        HSSFFont fonte = geradorPlanilha.createFont();
        fonte.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        estilo.setFont(fonte);

        //Cria a linha do cabeçalho
        HSSFRow linha = planilha.createRow(0);

        //Cria as células da linha do cabeçalho, aplicando o estilo
        HSSFCell celula0 = linha.createCell(COLUNA0);
        celula0.setCellValue(new HSSFRichTextString("Item"));
        celula0.setCellStyle(estilo);

        HSSFCell celula1 = linha.createCell(COLUNA1);
        celula1.setCellValue(new HSSFRichTextString("Nome do Autor"));
        celula1.setCellStyle(estilo);

        HSSFCell celula2 = linha.createCell(COLUNA2);
        celula2.setCellValue(new HSSFRichTextString("Título da Obra"));
        celula2.setCellStyle(estilo);

        HSSFCell celula3 = linha.createCell(COLUNA3);
        celula3.setCellValue(new HSSFRichTextString("Edição"));
        celula3.setCellStyle(estilo);

        HSSFCell celula4 = linha.createCell(COLUNA4);
        celula4.setCellValue(new HSSFRichTextString("Editora"));
        celula4.setCellStyle(estilo);

        HSSFCell celula5 = linha.createCell(COLUNA5);
        celula5.setCellValue(new HSSFRichTextString("Local"));
        celula5.setCellStyle(estilo);

        HSSFCell celula6 = linha.createCell(COLUNA6);
        celula6.setCellValue(new HSSFRichTextString("Ano"));
        celula6.setCellStyle(estilo);

        HSSFCell celula7 = linha.createCell(COLUNA7);
        celula7.setCellValue(new HSSFRichTextString("Coleção (S/N)"));
        celula7.setCellStyle(estilo);

        HSSFCell celula8 = linha.createCell(COLUNA8);
        celula8.setCellValue(new HSSFRichTextString("Volume"));
        celula8.setCellStyle(estilo);

        HSSFCell celula9 = linha.createCell(COLUNA9);
        celula9.setCellValue(new HSSFRichTextString("Matrícula Sophia do Solicitante (conselheiro)"));
        celula9.setCellStyle(estilo);

        HSSFCell celula10 = linha.createCell(COLUNA10);
        celula10.setCellValue(new HSSFRichTextString("Curso de Destino"));
        celula10.setCellStyle(estilo);

        HSSFCell celula11 = linha.createCell(COLUNA11);
        celula11.setCellValue(new HSSFRichTextString("Unidade de Medida (l, m, Kg, pct, cx, ...)"));
        celula11.setCellStyle(estilo);

        HSSFCell celula12 = linha.createCell(COLUNA12);
        celula12.setCellValue(new HSSFRichTextString("Valor Unitário Orçamento 1 em Real R$"));
        celula12.setCellStyle(estilo);

        HSSFCell celula13 = linha.createCell(COLUNA13);
        celula13.setCellValue(new HSSFRichTextString("Valor Unitário Orçamento 2 em Real R$"));
        celula13.setCellStyle(estilo);

        HSSFCell celula14 = linha.createCell(COLUNA14);
        celula14.setCellValue(new HSSFRichTextString("Valor Unitário Orçamento 3 em Real R$"));
        celula14.setCellStyle(estilo);

        HSSFCell celula15 = linha.createCell(COLUNA15);
        celula15.setCellValue(new HSSFRichTextString("Valor Médio Uniário em Real R$"));
        celula15.setCellStyle(estilo);

        HSSFCell celula16 = linha.createCell(COLUNA16);
        celula16.setCellValue(new HSSFRichTextString("Valor Total em Real R$"));
        celula16.setCellStyle(estilo);

        HSSFCell celula17 = linha.createCell(COLUNA17);
        celula17.setCellValue(new HSSFRichTextString("Quantidade de Exemplares"));
        celula17.setCellStyle(estilo);

        HSSFCell celula18 = linha.createCell(COLUNA18);
        celula18.setCellValue(new HSSFRichTextString("Área do Conhecimento"));
        celula18.setCellStyle(estilo);

        return planilha;
    }

    /**
     * Gera o rodapé de um objeto de planilha com os devidos subtotais
     * calculados.
     *
     * @param planilha Objeto de planilha a ter o rodapé gerado.
     * @param linhasPlanilha Lista de itens da planilha a ter o rodapé
     * calculado.
     * @return Objeto planilha com o rodapé devidamente gerado.
     */
    private HSSFSheet gerarRodape(HSSFSheet planilha, List<ItemPlanilha> linhasPlanilha) {

        //Cria o estilo a ser aplicado nas células do rodapé
        HSSFCellStyle estilo = geradorPlanilha.createCellStyle();
        HSSFFont fonte = geradorPlanilha.createFont();
        fonte.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        estilo.setFont(fonte);

        //Cria a linha do rodapé
        HSSFRow linha = planilha.createRow(linhasPlanilha.size() + 1);

        //Cria as células da linha do rodapé, aplicando o estilo
        HSSFCell celula0 = linha.createCell(COLUNA0);
        celula0.setCellValue("TOTAL");

        linha.createCell(COLUNA1).setCellValue(new HSSFRichTextString(""));
        linha.createCell(COLUNA2).setCellValue(new HSSFRichTextString(""));
        linha.createCell(COLUNA3).setCellValue(new HSSFRichTextString(""));
        linha.createCell(COLUNA4).setCellValue(new HSSFRichTextString(""));
        linha.createCell(COLUNA5).setCellValue(new HSSFRichTextString(""));
        linha.createCell(COLUNA6).setCellValue(new HSSFRichTextString(""));
        linha.createCell(COLUNA7).setCellValue(new HSSFRichTextString(""));
        linha.createCell(COLUNA8).setCellValue(new HSSFRichTextString(""));
        linha.createCell(COLUNA9).setCellValue(new HSSFRichTextString(""));
        linha.createCell(COLUNA10).setCellValue(new HSSFRichTextString(""));
        linha.createCell(COLUNA11).setCellValue(new HSSFRichTextString(""));

        double valorMedioUnitarioTotal = obterValorMedioUnitarioGeral(linhasPlanilha);

        HSSFCell celula12 = linha.createCell(COLUNA12);
        celula12.setCellValue(new HSSFRichTextString(String.valueOf(valorMedioUnitarioTotal)));
        celula12.setCellStyle(estilo);

        HSSFCell celula13 = linha.createCell(COLUNA13);
        celula13.setCellValue(new HSSFRichTextString(String.valueOf(valorMedioUnitarioTotal)));
        celula13.setCellStyle(estilo);

        HSSFCell celula14 = linha.createCell(COLUNA14);
        celula14.setCellValue(new HSSFRichTextString(String.valueOf(valorMedioUnitarioTotal)));
        celula14.setCellStyle(estilo);

        HSSFCell celula15 = linha.createCell(COLUNA15);
        celula15.setCellValue(new HSSFRichTextString(String.valueOf(valorMedioUnitarioTotal)));
        celula15.setCellStyle(estilo);

        HSSFCell celula16 = linha.createCell(COLUNA16);
        celula16.setCellValue(new HSSFRichTextString(String.valueOf(obterValorTotalGeral(linhasPlanilha))));
        celula16.setCellStyle(estilo);

        linha.createCell(COLUNA17).setCellValue(new HSSFRichTextString(""));
        linha.createCell(COLUNA18).setCellValue(new HSSFRichTextString(""));

        return planilha;

    }

    /**
     * Retorna o valor médio unitário geral de uma lista de itens de planilha. O
     * valor médio unitário geral é a soma dos valores médios de todos os livros
     * contidos na planilha.
     *
     * @param linhasPlanilha Lista de itens da planilha a ter o valor médio
     * unitário geral calculado.
     * @return Valor médio unitário geral da planilha.
     */
    private double obterValorMedioUnitarioGeral(List<ItemPlanilha> linhasPlanilha) {

        double valorMedioUnitarioGeral = 0.0;

        int i;
        for (i = 0; i < linhasPlanilha.size(); i++) {
            valorMedioUnitarioGeral += linhasPlanilha.get(i).getValorMedioUnitario();
        }

        return valorMedioUnitarioGeral;

    }

    /**
     * Retorna o valor total geral de uma lista de itens de planilha. O valor
     * total geral é o valor da compra com base no preço médio de cada livro e
     * quantidade de cada livro.
     *
     * @param linhasPlanilha Lista de itens da planilha a ter o valor total
     * geral calculado.
     * @return Valor total geral da planilha.
     */
    private double obterValorTotalGeral(List<ItemPlanilha> linhasPlanilha) {

        double valorTotalGeral = 0.0;

        int i;
        for (i = 0; i < linhasPlanilha.size(); i++) {
            valorTotalGeral += linhasPlanilha.get(i).getValorMedioUnitario()
                    * linhasPlanilha.get(i).getQuantExemplares();
        }

        return valorTotalGeral;

    }

    /**
     * Ajusta o a tamanho das células de uma planilha para caber o conteúdo que
     * possuem.
     *
     * @param planilha Objeto planilha que terá suas células ajustadas.
     * @return Objeto planilha com tamanho de células devidamente ajustadas.
     */
    private HSSFSheet ajustarTamanhoCelulas(HSSFSheet planilha) {

        final short NUM_COLUNAS = 19;
        short i;
        for (i = 0; i <= NUM_COLUNAS; i++) {
            planilha.autoSizeColumn(i);
        }

        return planilha;

    }

    /**
     * Gera planilha em formato CSV com base numa lista de itens de planilha.
     * Cada ItemPlanilha é uma linha da planilha com suas devidas COLUNAs.
     *
     * @param linhasPlanilha Lista de itens da planilha.
     */
    public void gerarPlanilhaCSV(List<ItemPlanilha> linhasPlanilha) {

        List<Map> planilhaListMap = obterListMap(linhasPlanilha);
        String separadorCSV = ",";

        try {

            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream("planilha.csv"), "UTF-8"));

            for (Map itemListMap : planilhaListMap) {

                StringBuilder linha = new StringBuilder();
                Iterator iterador = itemListMap.values().iterator();

                while (iterador.hasNext()) {
                    Object value = iterador.next();

                    if (value != null) {
                        linha.append(value.toString());
                    }

                    if (iterador.hasNext()) {
                        linha.append(separadorCSV);
                    }
                }

                writer.write(linha.toString());
                writer.newLine();
            }

            writer.flush();
            writer.close();

        } catch (UnsupportedEncodingException e) {
            Logger.getLogger(ExportacaoPlanilhaService.class.getName()).log(Level.SEVERE, null, e);
        } catch (FileNotFoundException e) {
            Logger.getLogger(ExportacaoPlanilhaService.class.getName()).log(Level.SEVERE, null, e);
        } catch (IOException e) {
            Logger.getLogger(ExportacaoPlanilhaService.class.getName()).log(Level.SEVERE, null, e);
        }

    }

    /**
     * Converte uma lista de itens de planilha em um objeto do tipo ListMap,
     * devidamente preenchido.
     *
     * @param linhasPlanilha Lista de itens de planilha.
     * @return Objeto do tipo ListMap preenchido com os dados da lista de itens
     * de planilha.
     */
    private List<Map> obterListMap(List<ItemPlanilha> linhasPlanilha) {

        List<Map> planilhaListMap = new ArrayList<Map>();

        int i;
        for (i = 0; i < linhasPlanilha.size(); i++) {

            Map item = new HashMap();

            item.put(COLUNA0, linhasPlanilha.get(i).getNumItem());
            item.put(COLUNA1, linhasPlanilha.get(i).getNomeAutor());
            item.put(COLUNA2, linhasPlanilha.get(i).getTituloObra());
            item.put(COLUNA3, linhasPlanilha.get(i).getEdicao());
            item.put(COLUNA4, linhasPlanilha.get(i).getEditora());
            item.put(COLUNA5, linhasPlanilha.get(i).getLocal());
            item.put(COLUNA6, linhasPlanilha.get(i).getAno());
            item.put(COLUNA7, linhasPlanilha.get(i).getColecao());
            item.put(COLUNA8, linhasPlanilha.get(i).getVolume());
            item.put(COLUNA9, linhasPlanilha.get(i).getMatriculaSophiaConselheiro());
            item.put(COLUNA10, linhasPlanilha.get(i).getCursoDestino());
            item.put(COLUNA11, linhasPlanilha.get(i).getUnidadeMedida());
            item.put(COLUNA12, linhasPlanilha.get(i).getValorMedioUnitario());
            item.put(COLUNA13, linhasPlanilha.get(i).getQuantExemplares());
            item.put(COLUNA14, linhasPlanilha.get(i).getAreaConhecimento());

            planilhaListMap.add(item);
        }

        return planilhaListMap;
    }
}
