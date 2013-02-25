package br.ufg.inf.es.web.converters.stringconverters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import org.junit.*;
import static org.junit.Assert.*;

/**
 *
 * @author victor
 */
public class String20ConverterTest {
    
    private StringConverter stringConverter;
    private FacesContext facesContext = null;
    private UIComponent component = null;

    @Before
    public void setUp() {
        stringConverter = new String20Converter();
    }

    @Test
    public void testGetAsString() {
        String string10 = "a";
        for (int i = 0; i < 20; i++) {
            string10 += "a";
        }
        String result = stringConverter.getAsString(facesContext, component, string10);
        assertEquals("aaaaaaaaaaaaaaaaaaaa<br/>a", result);
    }
}