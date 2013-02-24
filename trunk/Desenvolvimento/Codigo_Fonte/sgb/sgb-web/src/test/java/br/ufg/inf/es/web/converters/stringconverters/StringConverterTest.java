package br.ufg.inf.es.web.converters.stringconverters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import org.junit.*;
import static org.junit.Assert.*;

/**
 *
 * @author victor
 */
public class StringConverterTest {

    private StringConverter stringConverter;
    private FacesContext facesContext = null;
    private UIComponent component = null;

    @Before
    public void setUp() {
        stringConverter = new StringConverterImpl();
    }

    /**
     * Test of getAsObject method, of class StringConverter.
     */
    @Test
    public void testGetAsObjectWhenValueIsNull() {
        String value = "";
        Object result = stringConverter.getAsObject(facesContext, component, value);
        assertNull(result);
    }

    /**
     * Test of getAsObject method, of class StringConverter.
     */
    @Test
    public void testGetAsObjectWhenValueIsAValidString() {
        String value = "valid string";
        Object result = stringConverter.getAsObject(facesContext, component, value);
        assertEquals(value, result);
    }

    /**
     * Test of getAsString method, of class StringConverter.
     */
    @Test
    public void testGetAsStringWhenValueIsNull() {
        String expResult = "";
        String result = stringConverter.getAsString(facesContext, component, null);
        assertEquals(expResult, result);
    }

    /**
     * Test of getAsString method, of class StringConverter.
     */
    @Test
    public void testGetAsStringWhenValueIsEmpty() {
        String expResult = "";
        String result = stringConverter.getAsString(facesContext, component, "");
        assertEquals(expResult, result);
    }

    /**
     * Test of getAsString method, of class StringConverter.
     */
    @Test
    public void testGetAsStringWhenValueLessThenLimit() {
        String expResult = "value less then 25";
        String result = stringConverter.getAsString(facesContext, component, expResult);
        assertEquals(expResult, result);
    }

    /**
     * Test of getAsString method, of class StringConverter.
     */
    @Test
    public void testGetAsStringWhenValueIsBiggerThenLimit() {
        String value = "valor maior que tamanho 25";

        String expectedValue = "valor maior que tamanho 2<br/>5 valor maior que "
                + "tamanho<br/> 25 valor maior que taman<br/>ho 25 valor maior "
                + "que tam<br/>anho 25 valor maior que t<br/>amanho 25 valor "
                + "maior que<br/> tamanho 25 valor maior q<br/>ue tamanho 25 "
                + "valor maior<br/> que tamanho 25 valor mai<br/>or que tamanho "
                + "25 valor m<br/>aior que tamanho 25 valor<br/> maior que "
                + "tamanho 25 val<br/>or maior que tamanho 25 v<br/>alor maior "
                + "que tamanho 25<br/> valor maior que tamanho <br/>25 valor "
                + "maior que tamanh<br/>o 25 valor maior que tama<br/>nho 25 "
                + "valor maior que ta<br/>manho 25 valor maior que <br/>tamanho "
                + "25 valor maior qu<br/>e tamanho 25 valor maior <br/>que "
                + "tamanho 25 valor maio<br/>r que tamanho 25 valor ma<br/>ior "
                + "que tamanho 25 valor <br/>maior que tamanho 25 valo<br/>r "
                + "maior que tamanho 25 va<br/>lor maior que tamanho 25 <br/>valor "
                + "maior que tamanho 2<br/>5 valor maior que tamanho<br/> 25 valor "
                + "maior que taman<br/>ho 25 valor maior que tam<br/>anho 25 valor "
                + "maior que t<br/>amanho 25 valor maior que<br/> tamanho 25 valor "
                + "maior q<br/>ue tamanho 25";

        for (int i = 0; i < 5; i++) {
            value += " " + value;
        }

        String result = stringConverter.getAsString(facesContext, component, value);
        assertEquals(expectedValue, result);
    }

    public class StringConverterImpl extends StringConverter {

        public StringConverterImpl() {
            super(25);
        }
    }
}
