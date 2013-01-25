/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufg.inf.es.integracao.exportacaodados.planilha;

import br.ufg.inf.es.model.ItemPlanilha;
import java.util.*;

/**
 *
 * @author MarqueteHome
 */
public class TesteExportacaoPlanilhaService {

    public static void main(String args[]) {

        ExportacaoPlanilhaService exportador = new ExportacaoPlanilhaService();
        List<ItemPlanilha> planilhaXLS = new ArrayList<ItemPlanilha>();

        int i;
        for (i = 1; i <= 3; i++) {

            ItemPlanilha item = new ItemPlanilha();

            item.setNumItem(i);
            item.setNomeAutor("Autor " + i);
            item.setTituloObra("Obra " + i);
            item.setEdicao("1");
            item.setEditora("Editora " + i);
            item.setAno("2010");
            item.setCursoDestino("Engenharia de Software");
            item.setValorMedioUnitario(30.0);
            item.setQuantExemplares(5);
            item.setAreaConhecimento("Ciências Extatas e da Terra");

            planilhaXLS.add(item);

        }

        exportador.gerarPlanilhaXLS(planilhaXLS, planilhaXLS);
        exportador.gerarPlanilhaCSV(planilhaXLS);

    }

}
