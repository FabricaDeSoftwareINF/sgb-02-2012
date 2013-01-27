/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufg.inf.es.integracao;

import br.ufg.inf.es.model.Parametros;
import br.ufg.inf.es.persistencia.ParametrosDAO;
import java.math.BigDecimal;
import org.junit.*;
import static org.junit.Assert.*;
import org.mockito.Mockito;

/**
 *
 * @author alunoufg
 */
public class ParametrosServiceTest {
    
  
    /**
     * Test of getDAO method, of class ParametrosService.
     */
    @Test
    public void testGetDAO() {
        
        ParametrosService instance = new ParametrosService();
       
      
        
        ParametrosDAO result = instance.getDAO();
        assertNull(result);
        
    }

    /**
     * Test of obtenhaValorFrete method, of class ParametrosService.
     */
    @Test
    public void testObtenhaValorFrete() throws Exception {
        
        ParametrosService instance = Mockito.mock(ParametrosService.class);  
        Mockito.when(instance.obtenhaValorFrete()).thenReturn(BigDecimal.ONE);
     
        BigDecimal result = instance.obtenhaValorFrete();
        assertEquals(BigDecimal.ONE, result);
        
    }

    /**
     * Test of obtenhaParametroMEC method, of class ParametrosService.
     */
    @Test
    public void testObtenhaParametroMEC() throws Exception {
        
        ParametrosService instance = Mockito.mock(ParametrosService.class);  
        Mockito.when(instance.obtenhaParametroMEC()).thenReturn(0);
        Integer result = instance.obtenhaParametroMEC();
        assertEquals(new Integer(0), result);
        
    }

    /**
     * Test of find method, of class ParametrosService.
     */
    @Test
    public void testFind() throws Exception {
        
        ParametrosService instance = Mockito.mock(ParametrosService.class);  
        Parametros p = new Parametros();
        p.setId(1L);
        Mockito.when(instance.find()).thenReturn(p);
        
        Parametros result = instance.find();
        assertEquals(p, result);
    }

    /**
     * Test of save method, of class ParametrosService.
     */
    @Test
    public void testSave() throws Exception {
        
        Parametros entity = new Parametros();
        ParametrosService instance = Mockito.mock(ParametrosService.class);
        
        instance.save(entity);
        
    }
}
