package br.ufg.inf.es.web.datamodel;

import br.ufg.inf.es.model.Livro;
import java.util.Comparator;
import org.primefaces.model.SortOrder;

public class LivroLazySorter implements Comparator<Livro> {

    private String sortField;
    
    private SortOrder sortOrder;
    
    public LivroLazySorter(String sortField, SortOrder sortOrder) {
        this.sortField = sortField;
        this.sortOrder = sortOrder;
    }

    public int compare(Livro car1, Livro car2) {
        try {
            Object value1 = Livro.class.getField(this.sortField).get(car1);
            Object value2 = Livro.class.getField(this.sortField).get(car2);

            int value = ((Comparable)value1).compareTo(value2);
            
            return SortOrder.ASCENDING.equals(sortOrder) ? value : -1 * value;
        }
        catch(Exception e) {
            throw new RuntimeException();
        }
    }
}

