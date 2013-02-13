package br.ufg.inf.es.base.util;

/**
 * Classe utilitária de métodos comuns aos objetos.
 * 
 * @author Cézar Augusto Ferreira
 */
public class UtilObjeto {
    
    /**
     * Construtor da classe
     */
    protected UtilObjeto() {
    }
    
    /**
     * Método responsável por verificar se o objeto não foi instanciado.
     * 
     * @param o Objeto a ser verificado.
     * @return Se o objeto já foi instanciado,
     */
    public static boolean isReferencia(Object o) {
        
        return o != null;
    }
}
