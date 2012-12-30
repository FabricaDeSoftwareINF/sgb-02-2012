package br.ufg.inf.es.base.util;

import junit.framework.TestCase;
import org.junit.Test;

/**
 * Classe responsável por testar a classe UtilObjeto.
 * @author Allan Vieira Ribeiro
 */
public class UtilObjetoTest extends TestCase {
    
    /**
     * Teste para o método UtilObjeto.isReferencia(Object o).
     */
    @Test
    public void testIsReferencia(){
        
        String texto = "Texto";
        
        assertEquals(UtilObjeto.isReferencia(texto), true);        
    }
    
    /**
     * Teste para o método UtilObjeto.isReferencia(Object o).
     */
    @Test
    public void testIsReferenciaVazia(){
        
        String texto = null;
        
        assertEquals(UtilObjeto.isReferencia(texto), false);
    }
    
}
