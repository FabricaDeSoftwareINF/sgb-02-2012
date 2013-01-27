/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufg.inf.es.integracao.exportacaodados.planilha;

import br.ufg.inf.es.model.ItemPlanilha;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
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
 *
 * @author Bruno Marquete da Silva
 */
@Component
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class ExportacaoPlanilhaService {

    HSSFWorkbook wb;
    HSSFSheet planilhaNacionais;
    HSSFSheet planilhaEstrangeiros;

    public void gerarPlanilhaXLS(List<ItemPlanilha> linhasPlanilhaNacionais,
            List<ItemPlanilha> linhasPlanilhaEstrangeiros) {

        FileOutputStream stream;

        try {

            wb = new HSSFWorkbook();
            planilhaNacionais = wb.createSheet("Títulos Nacionais");
            planilhaEstrangeiros = wb.createSheet("Títulos Estrangeiros");
            planilhaNacionais = popularPlanilha(planilhaNacionais, linhasPlanilhaNacionais);
            planilhaEstrangeiros = popularPlanilha(planilhaEstrangeiros, linhasPlanilhaEstrangeiros);
            stream = new FileOutputStream("planilha.xls");
            wb.write(stream);

        } catch (FileNotFoundException ex) {
            Logger.getLogger(ExportacaoPlanilhaService.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ExportacaoPlanilhaService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private HSSFSheet popularPlanilha(HSSFSheet planilha, List<ItemPlanilha> linhasPlanilha) {

        gerarCabecalho(planilha, linhasPlanilha);

        int i;
        for (i = 0; i < linhasPlanilha.size(); i++) {

            HSSFRow linha = planilhaNacionais.createRow(i + 1);
            linha.createCell((short) 0).setCellValue(String.valueOf(linhasPlanilha.get(i).getNumItem()));
            linha.createCell((short) 1).setCellValue(linhasPlanilha.get(i).getNomeAutor());
            linha.createCell((short) 2).setCellValue(linhasPlanilha.get(i).getTituloObra());
            linha.createCell((short) 3).setCellValue(linhasPlanilha.get(i).getEdicao());
            linha.createCell((short) 4).setCellValue(linhasPlanilha.get(i).getEditora());
            linha.createCell((short) 5).setCellValue(linhasPlanilha.get(i).getLocal());
            linha.createCell((short) 6).setCellValue(linhasPlanilha.get(i).getAno());
            linha.createCell((short) 7).setCellValue(linhasPlanilha.get(i).getColecao());
            linha.createCell((short) 8).setCellValue(linhasPlanilha.get(i).getVolume());
            linha.createCell((short) 9).setCellValue(linhasPlanilha.get(i).getMatriculaSophiaConselheiro());
            linha.createCell((short) 10).setCellValue(linhasPlanilha.get(i).getCursoDestino().toString());
            linha.createCell((short) 11).setCellValue(linhasPlanilha.get(i).getUnidadeMedida());
            linha.createCell((short) 12).setCellValue("R$ ".concat(String.valueOf(linhasPlanilha.get(i).getValorMedioUnitario())));
            linha.createCell((short) 13).setCellValue("R$ ".concat(String.valueOf(linhasPlanilha.get(i).getValorMedioUnitario())));
            linha.createCell((short) 14).setCellValue("R$ ".concat(String.valueOf(linhasPlanilha.get(i).getValorMedioUnitario())));
            linha.createCell((short) 15).setCellValue("R$ ".concat(String.valueOf(linhasPlanilha.get(i).getValorMedioUnitario())));
            double valorTotal = linhasPlanilha.get(i).getValorMedioUnitario() * linhasPlanilha.get(i).getQuantExemplares();
            linha.createCell((short) 16).setCellValue("R$ ".concat(String.valueOf(valorTotal)));
            linha.createCell((short) 17).setCellValue(linhasPlanilha.get(i).getAreaConhecimento());
            linha.createCell((short) 18).setCellValue(linhasPlanilha.get(i).getCursoDestino());

        }

        gerarRodape(planilha, linhasPlanilha);

        return planilhaNacionais;
    }

    private HSSFSheet gerarCabecalho(HSSFSheet planilha, List<ItemPlanilha> linhasPlanilha) {

        HSSFRow linha = planilha.createRow(0);
        linha.createCell((short) 0).setCellValue("Itens");
        linha.createCell((short) 1).setCellValue("Nome do Autor");
        linha.createCell((short) 2).setCellValue("Título da Obra");
        linha.createCell((short) 3).setCellValue("Edição");
        linha.createCell((short) 4).setCellValue("Editora");
        linha.createCell((short) 5).setCellValue("Local");
        linha.createCell((short) 6).setCellValue("Ano");
        linha.createCell((short) 7).setCellValue("Coleção (S/N)");
        linha.createCell((short) 8).setCellValue("Volume");
        linha.createCell((short) 9).setCellValue("Matrícula Sophia do Solicitante (conselheiro)");
        linha.createCell((short) 10).setCellValue("Curso de Destino");
        linha.createCell((short) 11).setCellValue("Unidade de Medida (l, m, Kg, pct, cx, ...)");
        linha.createCell((short) 12).setCellValue("Valor Unitário Orçamento 1 em Real R$");
        linha.createCell((short) 13).setCellValue("Valor Unitário Orçamento 2 em Real R$");
        linha.createCell((short) 14).setCellValue("Valor Unitário Orçamento 3 em Real R$");
        linha.createCell((short) 15).setCellValue("Valor Médio Uniário em Real R$");
        linha.createCell((short) 16).setCellValue("Valor Total em Real R$");
        linha.createCell((short) 17).setCellValue("Quantidade de Exemplares");
        linha.createCell((short) 18).setCellValue("Área do Conhecimento");

        return planilha;
    }

    private HSSFSheet gerarRodape(HSSFSheet planilha, List<ItemPlanilha> linhasPlanilha) {

        HSSFRow linha = planilha.createRow(linhasPlanilha.size() + 1);
        linha.createCell((short) 0).setCellValue("TOTAL");
        linha.createCell((short) 1).setCellValue(" ");
        linha.createCell((short) 2).setCellValue(" ");
        linha.createCell((short) 3).setCellValue(" ");
        linha.createCell((short) 4).setCellValue(" ");
        linha.createCell((short) 5).setCellValue(" ");
        linha.createCell((short) 6).setCellValue(" ");
        linha.createCell((short) 7).setCellValue(" ");
        linha.createCell((short) 8).setCellValue(" ");
        linha.createCell((short) 9).setCellValue(" ");
        linha.createCell((short) 10).setCellValue(" ");
        linha.createCell((short) 11).setCellValue(" ");
        double valorMedioUnitarioTotal = obterValorMedioUnitarioGeral(linhasPlanilha);
        linha.createCell((short) 12).setCellValue(String.valueOf(valorMedioUnitarioTotal));
        linha.createCell((short) 13).setCellValue(String.valueOf(valorMedioUnitarioTotal));
        linha.createCell((short) 14).setCellValue(String.valueOf(valorMedioUnitarioTotal));
        linha.createCell((short) 15).setCellValue(String.valueOf(valorMedioUnitarioTotal));
        linha.createCell((short) 16).setCellValue(String.valueOf(obterValorTotalGeral(linhasPlanilha)));
        linha.createCell((short) 17).setCellValue(" ");
        linha.createCell((short) 18).setCellValue(" ");

        return planilha;

    }

    private double obterValorMedioUnitarioGeral(List<ItemPlanilha> linhasPlanilha) {

        double valorMedioUnitarioGeral = 0.0;

        int i;
        for (i = 0; i < linhasPlanilha.size(); i++) {
            valorMedioUnitarioGeral += linhasPlanilha.get(i).getValorMedioUnitario();
        }

        return valorMedioUnitarioGeral;

    }

    private double obterValorTotalGeral(List<ItemPlanilha> linhasPlanilha) {

        double valorTotalGeral = 0.0;

        int i;
        for (i = 0; i < linhasPlanilha.size(); i++) {
            valorTotalGeral += linhasPlanilha.get(i).getValorMedioUnitario()
                    * linhasPlanilha.get(i).getQuantExemplares();
        }

        return valorTotalGeral;

    }

    public void gerarPlanilhaCSV(List<ItemPlanilha> linhasPlanilha) {

        List<Map> planilhaListMap = obterListMap(linhasPlanilha);
        String separadorCSV = ",";
        
        try {
            
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream("planilha.csv"), "UTF-8"));
           
            for (Map itemListMap : planilhaListMap) {
              
                StringBuffer linha = new StringBuffer();
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
                
                bw.write(linha.toString());
                bw.newLine();
            }
            
            bw.flush();
            bw.close();
            
        } catch (UnsupportedEncodingException e) {
        } catch (FileNotFoundException e) {
        } catch (IOException e) {
        }

    }
    
    private List<Map> obterListMap(List<ItemPlanilha> linhasPlanilha) {
        
        List<Map> planilhaListMap = new ArrayList<Map>();
        
        int k;
        for (k = 0; k < linhasPlanilha.size(); k++) {
           
            Map item = new HashMap();
           
            item.put(0, linhasPlanilha.get(k).getNumItem());
            item.put(1, linhasPlanilha.get(k).getNomeAutor());
            item.put(2, linhasPlanilha.get(k).getTituloObra());
            item.put(3, linhasPlanilha.get(k).getEdicao());
            item.put(4, linhasPlanilha.get(k).getEditora());
            item.put(5, linhasPlanilha.get(k).getLocal());
            item.put(6, linhasPlanilha.get(k).getAno());
            item.put(7, linhasPlanilha.get(k).getColecao());
            item.put(8, linhasPlanilha.get(k).getVolume());
            item.put(9, linhasPlanilha.get(k).getMatriculaSophiaConselheiro());
            item.put(10, linhasPlanilha.get(k).getCursoDestino());
            item.put(11, linhasPlanilha.get(k).getUnidadeMedida());
            item.put(12, linhasPlanilha.get(k).getValorMedioUnitario());
            item.put(13, linhasPlanilha.get(k).getQuantExemplares());
            item.put(14, linhasPlanilha.get(k).getAreaConhecimento());
            
           planilhaListMap.add(item);
        }
        
        return planilhaListMap;
    }
}
