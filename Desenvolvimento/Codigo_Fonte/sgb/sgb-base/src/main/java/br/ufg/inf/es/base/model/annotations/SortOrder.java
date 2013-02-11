package br.ufg.inf.es.base.model.annotations;

/**
 * Enum que define os tipos de ordenação.
 * 
 * @author Cézar Augusto Ferreira
 */
public enum SortOrder {
    
    /** Campo ASC*/
    ASC("order by %s ASC"),
    
    /** Campo DESC*/
    DESC("order by %s DESC");
    
    /** Campo hql*/
    private String hql;

    /**
     * Construtor desta classe.
     * @param hql
     */
    private SortOrder(String hql) {
        
        this.hql = hql;
    }

    /**
     * Obtém o valor do campo hql de acordo com a propriedade passada.
     *
     * @param property
     * @return O valor formatado da hql.
     */
    public String getHql(String property) {
        
        return String.format(hql, property);
    }
}
