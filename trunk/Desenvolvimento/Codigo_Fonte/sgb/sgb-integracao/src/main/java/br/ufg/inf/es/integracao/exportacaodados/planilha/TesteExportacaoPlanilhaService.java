package br.ufg.inf.es.integracao.exportacaodados.planilha;

import br.ufg.inf.es.model.ItemPlanilha;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe executável para geração de arquivos de exportação do tipo XLS e CSV.
 *
 * @author Bruno Marquete
 */
public class TesteExportacaoPlanilhaService {

    private static ExportacaoPlanilhaService exportador = new ExportacaoPlanilhaService();
    private static List<ItemPlanilha> planilha = new ArrayList<ItemPlanilha>();

    public static void main(String args[]) {

        int i;
        final int NUM_ITENS = 10;
        for (i = 1; i <= NUM_ITENS; i++) {

            ItemPlanilha item = new ItemPlanilha();

            item.setNumItem(i);
            item.setNomeAutor("Autor " + i);
            item.setTituloObra("Obra " + i);
            item.setEdicao("1");
            item.setEditora("Editora " + i);
            item.setAno("2010");
            item.setCursoDestino("Engenharia de Software");
            final double VALOR_MEDIO_UNITARIO = 30.0;
            item.setValorMedioUnitario(VALOR_MEDIO_UNITARIO);
            final int QUANT_EXEMPLARES = 5;
            item.setQuantExemplares(QUANT_EXEMPLARES);
            item.setAreaConhecimento("Ciências Extatas e da Terra");

            planilha.add(item);

        }

        exportador.gerarPlanilhaXLS(planilha, planilha);
        exportador.gerarPlanilhaCSV(planilha);

    }
}
