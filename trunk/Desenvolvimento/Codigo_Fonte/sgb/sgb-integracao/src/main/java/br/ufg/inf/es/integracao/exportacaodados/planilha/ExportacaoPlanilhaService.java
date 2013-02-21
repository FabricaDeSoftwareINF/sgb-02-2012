package br.ufg.inf.es.integracao.exportacaodados.planilha;

import br.ufg.inf.es.model.exportacaodados.planilha.ItemPlanilha;
import br.ufg.inf.es.model.exportacaodados.planilha.Planilha;
import java.io.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.Region;
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
    private static final short COLUNA0 = 0;
    private static final short COLUNA1 = 1;
    private static final short COLUNA2 = 2;
    private static final short COLUNA3 = 3;
    private static final short COLUNA4 = 4;
    private static final short COLUNA5 = 5;
    private static final short COLUNA6 = 6;
    private static final short COLUNA7 = 7;
    private static final short COLUNA8 = 8;
    private static final short COLUNA9 = 9;
    private static final short COLUNA10 = 10;
    private static final short COLUNA11 = 11;
    private static final short COLUNA12 = 12;
    private static final short COLUNA13 = 13;
    private static final short COLUNA14 = 14;
    private static final short COLUNA15 = 15;
    private static final short COLUNA16 = 16;
    private static final short COLUNA17 = 17;
    private static final short COLUNA18 = 18;

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
    /**
     * Gera planilha em formato XLS com base numa planilha dos livros nacionais
     * e outra dos títulos estrangeiros. Uma planilha contém uma lista de
     * ItemPlanilha. Cada ItemPlanilha é uma linha da planilha com suas devidas
     * colunas.
     *
     * @param nacionais Planilha de livros nacionais.
     * @param estrangeiros Planilha de livros estrangeiros.
     * @return Array de bytes correspondente ao arquivo XLS gerado.
     */
    public byte[] gerarPlanilhaXLS(Planilha planilhaNacionais, Planilha planilhaEstrangeiros) {

        FileOutputStream outputStream;

        try {

            HSSFSheet planilhaNacionaisPOI;
            HSSFSheet planilhaEstrangeirosPOI;
            geradorPlanilha = new HSSFWorkbook();

            planilhaNacionaisPOI = geradorPlanilha.createSheet("Títulos Nacionais");
            planilhaEstrangeirosPOI = geradorPlanilha.createSheet("Títulos Estrangeiros");

            popularPlanilhaPOI(planilhaNacionaisPOI, planilhaNacionais);
            popularPlanilhaPOI(planilhaEstrangeirosPOI, planilhaEstrangeiros);

            ajustarTamanhoCelulas(planilhaNacionaisPOI, ItemPlanilha.getNumColunas());
            ajustarTamanhoCelulas(planilhaEstrangeirosPOI, ItemPlanilha.getNumColunas());

            //Mescla colunas da primeira linha das planilhas (título do cabeçalho)
            short linhaOrigemDestino = 0;
            short colunaOrigem = 0;
            short colunaDestino = (short) (ItemPlanilha.getNumColunas() - 1);
            Region regiao = new Region(linhaOrigemDestino, colunaOrigem, linhaOrigemDestino, colunaDestino);
            planilhaNacionaisPOI.addMergedRegion(regiao);
            planilhaEstrangeirosPOI.addMergedRegion(regiao);
            planilhaNacionaisPOI.getRow(0).getCell(0).setCellValue(planilhaNacionais.getTituloCabecalho());
            planilhaEstrangeirosPOI.getRow(0).getCell(0).setCellValue(planilhaEstrangeiros.getTituloCabecalho());

            //Escreve o arquivo no disco rígido temporariamente
            outputStream = new FileOutputStream("planilha.xls");
            geradorPlanilha.write(outputStream);

            //Lê o arquivo do disco rígido e transforma-o em um array de bytes
            File file = new File("planilha.xls");
            byte[] arrayBytes = getBytes(file);

            //Exlclui o arquivo temporário do disco rígido
            file.delete();

            return arrayBytes;

        } catch (FileNotFoundException ex) {
            Logger.getLogger(ExportacaoPlanilhaService.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } catch (IOException ex) {
            Logger.getLogger(ExportacaoPlanilhaService.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    /**
     * *
     * Retorna o array de bystes de um arquivo.
     *
     * @param file Objeto File a ser convertido em array de bytes.
     * @return Array de bytes do arquivo repassado.
     */
    private byte[] getBytes(File file) {

        int tamanho = (int) file.length();

        byte[] buffer = new byte[tamanho];
        FileInputStream inputStream = null;

        try {
            inputStream = new FileInputStream(file);
            inputStream.read(buffer, 0, tamanho);

        } catch (FileNotFoundException ex) {
            Logger.getLogger(ExportacaoPlanilhaService.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ExportacaoPlanilhaService.class.getName()).log(Level.SEVERE, null, ex);
        }

        return buffer;

    }

    /**
     * Popula objeto planilha da API Jakarta POI com base na planilha do projeto
     * SGB devidamente populada.
     *
     * @param planilhaPOI Objeto de planilha da API Jakarta POI a ser populado.
     * @param planilha Objeto planilha do projeto SGB devidamente populado.
     * @return Objeto de planilha da API Jakarta POI devidamente populado.
     */
    private HSSFSheet popularPlanilhaPOI(HSSFSheet planilhaPOI, Planilha planilha) {

        String strSimboloReal = "R$ ";
        gerarCabecalho(planilhaPOI, planilha);

        int i;
        for (i = 0; i < planilha.getLinhasPlanilha().size(); i++) {

            HSSFRow linha = planilhaPOI.createRow(i + 2);
            linha.createCell(COLUNA0).setCellValue(new HSSFRichTextString(String.valueOf(planilha.getLinhasPlanilha().get(i).getNumItem())));
            linha.createCell(COLUNA1).setCellValue(new HSSFRichTextString(planilha.getLinhasPlanilha().get(i).getNomeAutor()));
            linha.createCell(COLUNA2).setCellValue(new HSSFRichTextString(planilha.getLinhasPlanilha().get(i).getTituloObra()));
            linha.createCell(COLUNA3).setCellValue(new HSSFRichTextString(planilha.getLinhasPlanilha().get(i).getEdicao()));
            linha.createCell(COLUNA4).setCellValue(new HSSFRichTextString(planilha.getLinhasPlanilha().get(i).getEditora()));
            linha.createCell(COLUNA5).setCellValue(new HSSFRichTextString(planilha.getLinhasPlanilha().get(i).getLocal()));
            linha.createCell(COLUNA6).setCellValue(new HSSFRichTextString(planilha.getLinhasPlanilha().get(i).getAno()));
            linha.createCell(COLUNA7).setCellValue(new HSSFRichTextString(planilha.getLinhasPlanilha().get(i).getColecao()));
            linha.createCell(COLUNA8).setCellValue(new HSSFRichTextString(planilha.getLinhasPlanilha().get(i).getVolume()));
            linha.createCell(COLUNA9).setCellValue(new HSSFRichTextString(planilha.getLinhasPlanilha().get(i).getMatriculaSophiaConselheiro()));
            linha.createCell(COLUNA10).setCellValue(new HSSFRichTextString(planilha.getLinhasPlanilha().get(i).getCursoDestino().toString()));
            linha.createCell(COLUNA11).setCellValue(new HSSFRichTextString(planilha.getLinhasPlanilha().get(i).getUnidadeMedida()));
            linha.createCell(COLUNA12).setCellValue(new HSSFRichTextString(strSimboloReal.concat(String.valueOf(planilha.getLinhasPlanilha().get(i).getValorMedioUnitario()))));
            linha.createCell(COLUNA13).setCellValue(new HSSFRichTextString(strSimboloReal.concat(String.valueOf(planilha.getLinhasPlanilha().get(i).getValorMedioUnitario()))));
            linha.createCell(COLUNA14).setCellValue(new HSSFRichTextString(strSimboloReal.concat(String.valueOf(planilha.getLinhasPlanilha().get(i).getValorMedioUnitario()))));
            linha.createCell(COLUNA15).setCellValue(new HSSFRichTextString(strSimboloReal.concat(String.valueOf(planilha.getLinhasPlanilha().get(i).getValorMedioUnitario()))));
            double valorTotal = planilha.getLinhasPlanilha().get(i).getValorMedioUnitario() * planilha.getLinhasPlanilha().get(i).getQuantExemplares();
            linha.createCell(COLUNA16).setCellValue(new HSSFRichTextString(strSimboloReal.concat(String.valueOf(valorTotal))));
            linha.createCell(COLUNA17).setCellValue(new HSSFRichTextString(String.valueOf(planilha.getLinhasPlanilha().get(i).getQuantExemplares())));
            linha.createCell(COLUNA18).setCellValue(new HSSFRichTextString(planilha.getLinhasPlanilha().get(i).getAreaConhecimento()));

        }

        gerarRodape(planilhaPOI, planilha);

        return planilhaPOI;
    }

    /**
     * Gera o cabeçalho de um objeto de planilha com os devidos nomes das
     * COLUNAs.
     *
     * @param planilhaPOI Objeto planilha da API Jakarta POI a ter o cabeçalho
     * gerado.
     * @param planilha Objeto planilha do projeto SGB devidamente populada.
     * @return Objeto planiha da API Jarkarta POI com o cabeçalho devidamente
     * gerado.
     */
    private HSSFSheet gerarCabecalho(HSSFSheet planilhaPOI, Planilha planilha) {

        //Cria o estiloCabecalho a ser aplicado nas células do cabeçalho
        HSSFCellStyle estiloCabecalho = geradorPlanilha.createCellStyle();
        estiloCabecalho.setBorderTop(HSSFCellStyle.BORDER_MEDIUM);
        estiloCabecalho.setBorderLeft(HSSFCellStyle.BORDER_MEDIUM);
        estiloCabecalho.setBorderRight(HSSFCellStyle.BORDER_MEDIUM);
        estiloCabecalho.setBorderBottom(HSSFCellStyle.BORDER_MEDIUM);
        HSSFFont fonte = geradorPlanilha.createFont();
        fonte.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        estiloCabecalho.setFont(fonte);

        //Cria a primeira linha do cabeçalho
        HSSFRow linha1 = planilhaPOI.createRow(0);
        montarCelula(linha1, estiloCabecalho, COLUNA0, planilha.getTituloCabecalho());

        //Cria a segunda linha do cabeçalho
        HSSFRow linha2 = planilhaPOI.createRow(1);

        //Cria as células da linha do cabeçalho, aplicando o estiloCabecalho
        montarCelula(linha2, estiloCabecalho, COLUNA0, "Item");
        montarCelula(linha2, estiloCabecalho, COLUNA1, "Nome do Autor");
        montarCelula(linha2, estiloCabecalho, COLUNA2, "Título da Obra");
        montarCelula(linha2, estiloCabecalho, COLUNA3, "Edição");
        montarCelula(linha2, estiloCabecalho, COLUNA4, "Editora");
        montarCelula(linha2, estiloCabecalho, COLUNA5, "Local");
        montarCelula(linha2, estiloCabecalho, COLUNA6, "Ano");
        montarCelula(linha2, estiloCabecalho, COLUNA7, "Coleção (S/N)");
        montarCelula(linha2, estiloCabecalho, COLUNA8, "Volume");
        montarCelula(linha2, estiloCabecalho, COLUNA9, "Matrícula Sophia do Solicitante (conselheiro)");
        montarCelula(linha2, estiloCabecalho, COLUNA10, "Curso de Destino");
        montarCelula(linha2, estiloCabecalho, COLUNA11, "Unidade de Medida (l, m, Kg, pct, cx, ...)");
        montarCelula(linha2, estiloCabecalho, COLUNA12, "Valor Unitário Orçamento 1 em Real R$");
        montarCelula(linha2, estiloCabecalho, COLUNA13, "Valor Unitário Orçamento 2 em Real R$");
        montarCelula(linha2, estiloCabecalho, COLUNA14, "Valor Unitário Orçamento 3 em Real R$");
        montarCelula(linha2, estiloCabecalho, COLUNA15, "Valor Médio Uniário em Real R$");
        montarCelula(linha2, estiloCabecalho, COLUNA16, "Valor Total em Real R$");
        montarCelula(linha2, estiloCabecalho, COLUNA17, "Quantidade de Exemplares");
        montarCelula(linha2, estiloCabecalho, COLUNA18, "Área do Conhecimento");

        return planilhaPOI;
    }

    /**
     * Monta e retorna um objeto do tipo célula de planilha.
     *
     * @param linha Linha na qual a célula será criada e vinculada.
     * @param estiloCabecalho Estilo a ser aplicado na célula.
     * @param numColuna Número da coluna da célula na linha repassada.
     * @param valorCelula Valor a ser adicionado na célula.
     * @return Objeto célula montado de acordo com os atributos repassados.
     */
    private HSSFCell montarCelula(HSSFRow linha, HSSFCellStyle estilo, short numColuna, String valorCelula) {

        HSSFCell celula = linha.createCell(numColuna);
        celula.setCellValue(new HSSFRichTextString(valorCelula));
        celula.setCellStyle(estilo);

        return celula;
    }

    /**
     * Gera o rodapé de um objeto de planilha da API Jakarta POI com os devidos
     * subtotais calculados.
     *
     * @param planilhaPOI Objeto de planilha da API Jakarta POI a ter o rodapé
     * gerado.
     * @param planilha Objeto planilha do projeto SGB devidamente populado.
     * @return Objeto planilha da API Jakarta POI com o rodapé devidamente
     * gerado.
     */
    private HSSFSheet gerarRodape(HSSFSheet planilhaPOI, Planilha planilha) {

        //Cria o estiloCabecalho a ser aplicado nas células do rodapé
        HSSFCellStyle estiloRodape = geradorPlanilha.createCellStyle();
        HSSFFont fonte = geradorPlanilha.createFont();
        fonte.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        estiloRodape.setFont(fonte);

        //Cria a linha do rodapé
        HSSFRow linha = planilhaPOI.createRow(planilha.getLinhasPlanilha().size() + 2);

        //Cria as células da linha do rodapé, aplicando o estiloCabecalho
        montarCelula(linha, estiloRodape, COLUNA0, "TOTAL");
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
        double valorMedioUnitarioTotal = obterValorMedioUnitarioGeral(planilha.getLinhasPlanilha());
        montarCelula(linha, estiloRodape, COLUNA12, String.valueOf(valorMedioUnitarioTotal));
        montarCelula(linha, estiloRodape, COLUNA13, String.valueOf(valorMedioUnitarioTotal));
        montarCelula(linha, estiloRodape, COLUNA14, String.valueOf(valorMedioUnitarioTotal));
        montarCelula(linha, estiloRodape, COLUNA15, String.valueOf(valorMedioUnitarioTotal));
        montarCelula(linha, estiloRodape, COLUNA16, String.valueOf(obterValorTotalGeral(planilha.getLinhasPlanilha())));
        linha.createCell(COLUNA17).setCellValue(new HSSFRichTextString(""));
        linha.createCell(COLUNA18).setCellValue(new HSSFRichTextString(""));

        return planilhaPOI;

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
     * @param planilhaPOI Objeto planilha da API Jarkarta POI que terá suas
     * células ajustadas.
     * @return Objeto planilha da API Jakarta POI com tamanho de células
     * devidamente ajustadas.
     */
    private HSSFSheet ajustarTamanhoCelulas(HSSFSheet planilhaPOI, short numColunas) {

        short i;
        for (i = 0; i <= numColunas; i++) {
            planilhaPOI.autoSizeColumn(i);
        }

        return planilhaPOI;

    }

    /**
     * *
     * Gera planilha em formato CSV com base numa planilha. Uma planilha tem uma
     * lista de ItemPlanilha. Cada ItemPlanilha é uma linha da planilha com suas
     * devidas COLUNAs.
     *
     * @param planilha Ojeto planilha.
     * @return Array de bytes correspondente ao arquivo CSV gerado.
     */
    public byte[] gerarPlanilhaCSV(Planilha planilha) {

        List<Map> planilhaListMap = obterListMap(planilha.getLinhasPlanilha());
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

            File file = new File("planilha.csv");
            byte[] arrayBytes = getBytes(file);
            file.delete();

            return arrayBytes;

        } catch (UnsupportedEncodingException e) {
            Logger.getLogger(ExportacaoPlanilhaService.class.getName()).log(Level.SEVERE, null, e);
            return null;
        } catch (FileNotFoundException e) {
            Logger.getLogger(ExportacaoPlanilhaService.class.getName()).log(Level.SEVERE, null, e);
            return null;
        } catch (IOException e) {
            Logger.getLogger(ExportacaoPlanilhaService.class.getName()).log(Level.SEVERE, null, e);
            return null;
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