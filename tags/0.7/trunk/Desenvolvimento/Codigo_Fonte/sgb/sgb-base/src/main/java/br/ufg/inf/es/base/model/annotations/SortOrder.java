package br.ufg.inf.es.base.model.annotations;

/**
 * @author Cézar Augusto Ferreira
 */
public enum SortOrder {
    
    ASC("order by %s ASC"),
    
    DESC("order by %s DESC");
    
    private String hql;

    private SortOrder(String hql) {
        
        this.hql = hql;
    }

    public String getHql(String property) {
        
        return String.format(hql, property);
    }
}
