
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
        System.out.println("getAsObject");
        FacesContext facesContext = null;
        UIComponent component = null;
        String value = "";
        AutorConverter instance = new AutorConverter();
        Object expResult = null;
        Object result = instance.getAsObject(facesContext, component, value);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAsString method, of class AutorConverter.
     */
    @Test
    public void testGetAsString() {
        System.out.println("getAsString");
        FacesContext context = null;
        UIComponent component = null;
        Object value = null;
        AutorConverter instance = new AutorConverter();
        String expResult = "";
        String result = instance.getAsString(context, component, value);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
}
