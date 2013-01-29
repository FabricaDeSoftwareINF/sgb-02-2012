
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
 * @author Bruno Marquete da Silva
 */
@Component
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class ExportacaoPlanilhaService {

    private HSSFWorkbook geradorPlanilha;
    private HSSFSheet planilhaNacionais;
    private HSSFSheet planilhaEstrangeiros;
    private short coluna0 = 0;
    private short coluna1 = 1;
    private short coluna2 = 2;
    private short coluna3 = 3;
    private short coluna4 = 4;
    private short coluna5 = 5;
    private short coluna6 = 6;
    private short coluna7 = 7;
    private short coluna8 = 8;
    private short coluna9 = 9;
    private short coluna10 = 10;
    private short coluna11 = 11;
    private short coluna12 = 12;
    private short coluna13 = 13;
    private short coluna14 = 14;
    private short coluna15 = 15;
    private short coluna16 = 16;
    private short coluna17 = 17;
    private short coluna18 = 18;

    /**
     * Gera planilha em formato XLS com base numa lista de itens de planilha dos 
     * livros nacionais e outra dos títulos estrangeiros. Cada ItemPlanilha é
     * uma linha da planilha com suas devidas colunas.
     * @param linhasPlanilhaNacionais
     * Lista de itens da planilha de livros nacionais.
     * @param linhasPlanilhaEstrangeiros 
     * Lista de itens da planilha de livros nacionais.
     */
    public void gerarPlanilhaXLS(List<ItemPlanilha> linhasPlanilhaNacionais,
            List<ItemPlanilha> linhasPlanilhaEstrangeiros) {

        FileOutputStream stream;

        try {

            geradorPlanilha = new HSSFWorkbook();
            planilhaNacionais = geradorPlanilha.createSheet("Títulos Nacionais");
            planilhaEstrangeiros = geradorPlanilha.createSheet("Títulos Estrangeiros");
            planilhaNacionais = popularPlanilha(planilhaNacionais, linhasPlanilhaNacionais);
            planilhaEstrangeiros = popularPlanilha(planilhaEstrangeiros, linhasPlanilhaEstrangeiros);
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
     * @param planilha
     * Objeto de planilha a ser populado.
     * @param linhasPlanilha
     * Lista de itens de planilha.
     * @return 
     * Objeto de planilha devidamente populado.
     */
    private HSSFSheet popularPlanilha(HSSFSheet planilha, List<ItemPlanilha> linhasPlanilha) {
        
        String strSimboloReal = "R$ ";
        gerarCabecalho(planilha);

        int i;
        for (i = 0; i < linhasPlanilha.size(); i++) {

            HSSFRow linha = planilhaNacionais.createRow(i + 1);
            linha.createCell(coluna0).setCellValue(String.valueOf(linhasPlanilha.get(i).getNumItem()));
            linha.createCell(coluna1).setCellValue(linhasPlanilha.get(i).getNomeAutor());
            linha.createCell(coluna2).setCellValue(linhasPlanilha.get(i).getTituloObra());
            linha.createCell(coluna3).setCellValue(linhasPlanilha.get(i).getEdicao());
            linha.createCell(coluna4).setCellValue(linhasPlanilha.get(i).getEditora());
            linha.createCell(coluna5).setCellValue(linhasPlanilha.get(i).getLocal());
            linha.createCell(coluna6).setCellValue(linhasPlanilha.get(i).getAno());
            linha.createCell(coluna7).setCellValue(linhasPlanilha.get(i).getColecao());
            linha.createCell(coluna8).setCellValue(linhasPlanilha.get(i).getVolume());
            linha.createCell(coluna9).setCellValue(linhasPlanilha.get(i).getMatriculaSophiaConselheiro());
            linha.createCell(coluna10).setCellValue(linhasPlanilha.get(i).getCursoDestino().toString());
            linha.createCell(coluna11).setCellValue(linhasPlanilha.get(i).getUnidadeMedida());
            linha.createCell(coluna12).setCellValue(strSimboloReal.concat(String.valueOf(linhasPlanilha.get(i).getValorMedioUnitario())));
            linha.createCell(coluna13).setCellValue(strSimboloReal.concat(String.valueOf(linhasPlanilha.get(i).getValorMedioUnitario())));
            linha.createCell(coluna14).setCellValue(strSimboloReal.concat(String.valueOf(linhasPlanilha.get(i).getValorMedioUnitario())));
            linha.createCell(coluna15).setCellValue(strSimboloReal.concat(String.valueOf(linhasPlanilha.get(i).getValorMedioUnitario())));
            double valorTotal = linhasPlanilha.get(i).getValorMedioUnitario() * linhasPlanilha.get(i).getQuantExemplares();
            linha.createCell(coluna16).setCellValue(strSimboloReal.concat(String.valueOf(valorTotal)));
            linha.createCell(coluna17).setCellValue(linhasPlanilha.get(i).getAreaConhecimento());
            linha.createCell(coluna18).setCellValue(linhasPlanilha.get(i).getCursoDestino());

        }

        gerarRodape(planilha, linhasPlanilha);

        return planilhaNacionais;
    }

    /**
     * Gera o cabeçalho de um objeto de planilha com os devidos nomes das colunas.
     * @param planilha
     * Objeto planilha a ter o cabeçalho gerado.
     * @return 
     * Objeto planiha com o cabeçalho devidamente gerado.
     */
    private HSSFSheet gerarCabecalho(HSSFSheet planilha) {

        HSSFRow linha = planilha.createRow(0);
        linha.createCell(coluna0).setCellValue("Itens");
        linha.createCell(coluna1).setCellValue("Nome do Autor");
        linha.createCell(coluna2).setCellValue("Título da Obra");
        linha.createCell(coluna3).setCellValue("Edição");
        linha.createCell(coluna4).setCellValue("Editora");
        linha.createCell(coluna5).setCellValue("Local");
        linha.createCell(coluna6).setCellValue("Ano");
        linha.createCell(coluna7).setCellValue("Coleção (S/N)");
        linha.createCell(coluna8).setCellValue("Volume");
        linha.createCell(coluna9).setCellValue("Matrícula Sophia do Solicitante (conselheiro)");
        linha.createCell(coluna10).setCellValue("Curso de Destino");
        linha.createCell(coluna11).setCellValue("Unidade de Medida (l, m, Kg, pct, cx, ...)");
        linha.createCell(coluna12).setCellValue("Valor Unitário Orçamento 1 em Real R$");
        linha.createCell(coluna13).setCellValue("Valor Unitário Orçamento 2 em Real R$");
        linha.createCell(coluna14).setCellValue("Valor Unitário Orçamento 3 em Real R$");
        linha.createCell(coluna15).setCellValue("Valor Médio Uniário em Real R$");
        linha.createCell(coluna16).setCellValue("Valor Total em Real R$");
        linha.createCell(coluna17).setCellValue("Quantidade de Exemplares");
        linha.createCell(coluna18).setCellValue("Área do Conhecimento");

        return planilha;
    }

    /**
     * Gera o rodapé de um objeto de planilha com os devidos subtotais calculados.
     * @param planilha
     * Objeto de planilha a ter o rodapé gerado.
     * @param linhasPlanilha
     * Lista de itens da planilha a ter o rodapé calculado.
     * @return 
     * Objeto planilha com o rodapé devidamente gerado.
     */
    private HSSFSheet gerarRodape(HSSFSheet planilha, List<ItemPlanilha> linhasPlanilha) {

        HSSFRow linha = planilha.createRow(linhasPlanilha.size() + 1);
        linha.createCell(coluna0).setCellValue("TOTAL");
        linha.createCell(coluna1).setCellValue(" ");
        linha.createCell(coluna2).setCellValue(" ");
        linha.createCell(coluna3).setCellValue(" ");
        linha.createCell(coluna4).setCellValue(" ");
        linha.createCell(coluna5).setCellValue(" ");
        linha.createCell(coluna6).setCellValue(" ");
        linha.createCell(coluna7).setCellValue(" ");
        linha.createCell(coluna8).setCellValue(" ");
        linha.createCell(coluna9).setCellValue(" ");
        linha.createCell(coluna10).setCellValue(" ");
        linha.createCell(coluna11).setCellValue(" ");
        double valorMedioUnitarioTotal = obterValorMedioUnitarioGeral(linhasPlanilha);
        linha.createCell(coluna12).setCellValue(String.valueOf(valorMedioUnitarioTotal));
        linha.createCell(coluna13).setCellValue(String.valueOf(valorMedioUnitarioTotal));
        linha.createCell(coluna14).setCellValue(String.valueOf(valorMedioUnitarioTotal));
        linha.createCell(coluna15).setCellValue(String.valueOf(valorMedioUnitarioTotal));
        linha.createCell(coluna16).setCellValue(String.valueOf(obterValorTotalGeral(linhasPlanilha)));
        linha.createCell(coluna17).setCellValue(" ");
        linha.createCell(coluna18).setCellValue(" ");

        return planilha;

    }

    /**
     * Retorna o valor médio unitário geral de uma lista de itens de planilha.
     * O valor médio unitário geral é a soma dos valores médios de todos os
     * livros contidos na planilha.
     * @param linhasPlanilha
     * Lista de itens da planilha a ter o valor médio unitário geral calculado.
     * @return 
     * Valor médio unitário geral da planilha.
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
     * @param linhasPlanilha
     * Lista de itens da planilha a ter o valor total geral calculado.
     * @return 
     * Valor total geral da planilha.
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
     * Cada ItemPlanilha é uma linha da planilha com suas devidas colunas.
     * @param linhasPlanilha 
     * Lista de itens da planilha.
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
     * @param linhasPlanilha
     * Lista de itens de planilha.
     * @return 
     * Objeto do tipo ListMap preenchido com os dados da lista de itens de 
     * planilha.
     */
    private List<Map> obterListMap(List<ItemPlanilha> linhasPlanilha) {

        List<Map> planilhaListMap = new ArrayList<Map>();

        int i;
        for (i = 0; i < linhasPlanilha.size(); i++) {

            Map item = new HashMap();

            item.put(coluna0, linhasPlanilha.get(i).getNumItem());
            item.put(coluna1, linhasPlanilha.get(i).getNomeAutor());
            item.put(coluna2, linhasPlanilha.get(i).getTituloObra());
            item.put(coluna3, linhasPlanilha.get(i).getEdicao());
            item.put(coluna4, linhasPlanilha.get(i).getEditora());
            item.put(coluna5, linhasPlanilha.get(i).getLocal());
            item.put(coluna6, linhasPlanilha.get(i).getAno());
            item.put(coluna7, linhasPlanilha.get(i).getColecao());
            item.put(coluna8, linhasPlanilha.get(i).getVolume());
            item.put(coluna9, linhasPlanilha.get(i).getMatriculaSophiaConselheiro());
            item.put(coluna10, linhasPlanilha.get(i).getCursoDestino());
            item.put(coluna11, linhasPlanilha.get(i).getUnidadeMedida());
            item.put(coluna12, linhasPlanilha.get(i).getValorMedioUnitario());
            item.put(coluna13, linhasPlanilha.get(i).getQuantExemplares());
            item.put(coluna14, linhasPlanilha.get(i).getAreaConhecimento());

            planilhaListMap.add(item);
        }

        return planilhaListMap;
    }
}
