/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufg.inf.es.integracao.cotacao;

import java.util.HashMap;
import java.util.Map;
import org.junit.*;
import static org.junit.Assert.*;

/**
 *
 * @author usuario
 */
public class CotadorGoogleShopTest {
    
   

   

  

    /**
     * Test of GerarUrlDeBusca method, of class CotadorGoogleShop.
     */
    @Test
    public void testGerarUrlDeBusca() {
        
       
        CotadorGoogleShop instance = new CotadorGoogleShop();
        
        String result = instance.gerarUrlDeBusca("9780470447628");
        assertNotNull(result);
        
    }
}
