
package br.ufg.inf.es.web.converters;

import org.mockito.Mockito;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Marco Aurélio Camargo Oliveira
 */
public class AutorConverterTest {
    
    public AutorConverterTest() {
    }
    
    FacesContext facesContext = Mockito.mock(FacesContext.class);
    UIComponent uiComponent = Mockito.mock(UIComponent.class);
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getAsObject method, of class AutorConverter.
     */
    @Test
    public void testGetAsObject() {
       
    }

    /**
     * Test of getAsString method, of class AutorConverter.
     */
    @Test
    public void testGetAsString() {
       
    }
}
