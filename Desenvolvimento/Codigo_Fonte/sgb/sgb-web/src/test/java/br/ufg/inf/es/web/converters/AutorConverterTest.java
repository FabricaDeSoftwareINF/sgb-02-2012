package br.ufg.inf.es.web.converters;

import br.ufg.inf.es.model.Autor;
import br.ufg.inf.es.persistencia.AutorDAO;
import java.util.ArrayList;
import java.util.Collection;
import org.mockito.Mockito;
import javax.faces.component.UIComponent;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.springframework.web.context.WebApplicationContext;

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
        String value = "1%&A%&B%&";
        AutorConverter instance = new AutorConverter();
        Autor expResult = new Autor();
        expResult.setNome("A");
        expResult.setSobrenome("B");
        expResult.setId(1L);
        ExternalContext externalContext = Mockito.mock(ExternalContext.class);
        ServletContext servletContext = Mockito.mock(ServletContext.class);
        Mockito.when(facesContext.getExternalContext()).thenReturn(externalContext);
        WebApplicationContext context = Mockito.mock(WebApplicationContext.class);
        instance.setWebApplicationContext(context);
        AutorDAO autorDao = Mockito.mock(AutorDAO.class);
        
        Collection<Autor> collection = new ArrayList();
        collection.add(expResult);
        Mockito.when(autorDao.search(expResult)).thenReturn(collection);
        
        Mockito.when(context.getBean(AutorDAO.class)).thenReturn(autorDao);
        Mockito.when(externalContext.getContext()).thenReturn(servletContext);
        


        Object result = instance.getAsObject(facesContext, uiComponent, value);
        assertEquals(expResult, result);

    }

    /**
     * Test of getAsString method, of class AutorConverter.
     */
    @Test
    public void testGetAsString() {
        Autor value = new Autor();
        value.setNome("A");
        value.setSobrenome("B");
        value.setId(1L);


        AutorConverter instance = new AutorConverter();
        String expResult = "1%&A%&B%&";
        String result = instance.getAsString(facesContext, uiComponent, value);
        assertEquals(expResult, result);

    }
}
