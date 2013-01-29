
package br.ufg.inf.es.integracao.exportacaodados.planilha;

import br.ufg.inf.es.model.ItemPlanilha;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe executável para geração de arquivos de exportação do tipo XLS e CSV.
 * @author Bruno Marquete
 */
public class TesteExportacaoPlanilhaService {
    
    private static ExportacaoPlanilhaService exportador = new ExportacaoPlanilhaService();

    public static void main(String args[]) {

        List<ItemPlanilha> planilhaXLS = new ArrayList<ItemPlanilha>();

        int i;
        int numItens = 10;
        for (i = 1; i <= numItens; i++) {

            ItemPlanilha item = new ItemPlanilha();

            item.setNumItem(i);
            item.setNomeAutor("Autor " + i);
            item.setTituloObra("Obra " + i);
            item.setEdicao("1");
            item.setEditora("Editora " + i);
            item.setAno("2010");
            item.setCursoDestino("Engenharia de Software");
            double valorMedioUnitario = 30.0;
            item.setValorMedioUnitario(valorMedioUnitario);
            int quantExemplares = 5;
            item.setQuantExemplares(quantExemplares);
            item.setAreaConhecimento("Ciências Extatas e da Terra");

            planilhaXLS.add(item);

        }

        exportador.gerarPlanilhaXLS(planilhaXLS, planilhaXLS);
        exportador.gerarPlanilhaCSV(planilhaXLS);

    }

}
