package br.ufg.inf.es.integracao.exportacaodados.planilha;

import br.ufg.inf.es.model.ItemPlanilha;
import java.io.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
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

    private final short COLUNA0 = 0;
    private final short COLUNA1 = 1;
    private final short COLUNA2 = 2;
    private final short COLUNA3 = 3;
    private final short COLUNA4 = 4;
    private final short COLUNA5 = 5;
    private final short COLUNA6 = 6;
    private final short COLUNA7 = 7;
    private short COLUNA8 = 8;
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

            HSSFWorkbook geradorPlanilha;
            HSSFSheet planilhaNacionais;
            HSSFSheet planilhaEstrangeiros;

            geradorPlanilha = new HSSFWorkbook();
            planilhaNacionais = geradorPlanilha.createSheet("Títulos Nacionais");
            planilhaEstrangeiros = geradorPlanilha.createSheet("Títulos Estrangeiros");
            popularPlanilha(planilhaNacionais, linhasPlanilhaNacionais);
            popularPlanilha(planilhaEstrangeiros, linhasPlanilhaEstrangeiros);
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
            linha.createCell(COLUNA0).setCellValue(String.valueOf(linhasPlanilha.get(i).getNumItem()));
            linha.createCell(COLUNA1).setCellValue(linhasPlanilha.get(i).getNomeAutor());
            linha.createCell(COLUNA2).setCellValue(linhasPlanilha.get(i).getTituloObra());
            linha.createCell(COLUNA3).setCellValue(linhasPlanilha.get(i).getEdicao());
            linha.createCell(COLUNA4).setCellValue(linhasPlanilha.get(i).getEditora());
            linha.createCell(COLUNA5).setCellValue(linhasPlanilha.get(i).getLocal());
            linha.createCell(COLUNA6).setCellValue(linhasPlanilha.get(i).getAno());
            linha.createCell(COLUNA7).setCellValue(linhasPlanilha.get(i).getColecao());
            linha.createCell(COLUNA8).setCellValue(linhasPlanilha.get(i).getVolume());
            linha.createCell(COLUNA9).setCellValue(linhasPlanilha.get(i).getMatriculaSophiaConselheiro());
            linha.createCell(COLUNA10).setCellValue(linhasPlanilha.get(i).getCursoDestino().toString());
            linha.createCell(COLUNA11).setCellValue(linhasPlanilha.get(i).getUnidadeMedida());
            linha.createCell(COLUNA12).setCellValue(strSimboloReal.concat(String.valueOf(linhasPlanilha.get(i).getValorMedioUnitario())));
            linha.createCell(COLUNA13).setCellValue(strSimboloReal.concat(String.valueOf(linhasPlanilha.get(i).getValorMedioUnitario())));
            linha.createCell(COLUNA14).setCellValue(strSimboloReal.concat(String.valueOf(linhasPlanilha.get(i).getValorMedioUnitario())));
            linha.createCell(COLUNA15).setCellValue(strSimboloReal.concat(String.valueOf(linhasPlanilha.get(i).getValorMedioUnitario())));
            double valorTotal = linhasPlanilha.get(i).getValorMedioUnitario() * linhasPlanilha.get(i).getQuantExemplares();
            linha.createCell(COLUNA16).setCellValue(strSimboloReal.concat(String.valueOf(valorTotal)));
            linha.createCell(COLUNA17).setCellValue(linhasPlanilha.get(i).getQuantExemplares());
            linha.createCell(COLUNA18).setCellValue(linhasPlanilha.get(i).getAreaConhecimento());
            

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

        HSSFRow linha = planilha.createRow(0);
        linha.createCell(COLUNA0).setCellValue("Itens");
        linha.createCell(COLUNA1).setCellValue("Nome do Autor");
        linha.createCell(COLUNA2).setCellValue("Título da Obra");
        linha.createCell(COLUNA3).setCellValue("Edição");
        linha.createCell(COLUNA4).setCellValue("Editora");
        linha.createCell(COLUNA5).setCellValue("Local");
        linha.createCell(COLUNA6).setCellValue("Ano");
        linha.createCell(COLUNA7).setCellValue("Coleção (S/N)");
        linha.createCell(COLUNA8).setCellValue("Volume");
        linha.createCell(COLUNA9).setCellValue("Matrícula Sophia do Solicitante (conselheiro)");
        linha.createCell(COLUNA10).setCellValue("Curso de Destino");
        linha.createCell(COLUNA11).setCellValue("Unidade de Medida (l, m, Kg, pct, cx, ...)");
        linha.createCell(COLUNA12).setCellValue("Valor Unitário Orçamento 1 em Real R$");
        linha.createCell(COLUNA13).setCellValue("Valor Unitário Orçamento 2 em Real R$");
        linha.createCell(COLUNA14).setCellValue("Valor Unitário Orçamento 3 em Real R$");
        linha.createCell(COLUNA15).setCellValue("Valor Médio Uniário em Real R$");
        linha.createCell(COLUNA16).setCellValue("Valor Total em Real R$");
        linha.createCell(COLUNA17).setCellValue("Quantidade de Exemplares");
        linha.createCell(COLUNA18).setCellValue("Área do Conhecimento");

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

        HSSFRow linha = planilha.createRow(linhasPlanilha.size() + 1);
        linha.createCell(COLUNA0).setCellValue("TOTAL");
        linha.createCell(COLUNA1).setCellValue(" ");
        linha.createCell(COLUNA2).setCellValue(" ");
        linha.createCell(COLUNA3).setCellValue(" ");
        linha.createCell(COLUNA4).setCellValue(" ");
        linha.createCell(COLUNA5).setCellValue(" ");
        linha.createCell(COLUNA6).setCellValue(" ");
        linha.createCell(COLUNA7).setCellValue(" ");
        linha.createCell(COLUNA8).setCellValue(" ");
        linha.createCell(COLUNA9).setCellValue(" ");
        linha.createCell(COLUNA10).setCellValue(" ");
        linha.createCell(COLUNA11).setCellValue(" ");
        double valorMedioUnitarioTotal = obterValorMedioUnitarioGeral(linhasPlanilha);
        linha.createCell(COLUNA12).setCellValue(String.valueOf(valorMedioUnitarioTotal));
        linha.createCell(COLUNA13).setCellValue(String.valueOf(valorMedioUnitarioTotal));
        linha.createCell(COLUNA14).setCellValue(String.valueOf(valorMedioUnitarioTotal));
        linha.createCell(COLUNA15).setCellValue(String.valueOf(valorMedioUnitarioTotal));
        linha.createCell(COLUNA16).setCellValue(String.valueOf(obterValorTotalGeral(linhasPlanilha)));
        linha.createCell(COLUNA17).setCellValue(" ");
        linha.createCell(COLUNA18).setCellValue(" ");

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
