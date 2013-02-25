package br.ufg.inf.es.model;

import org.junit.*;
import static org.junit.Assert.*;

/**
 *
 * @author Victor Carvalho
 */
public class AbstractEntityModelTest {

    @Before
    public void setUp() {
    }

    /**
     * Test of getId method, of class AbstractEntityModel.
     */
    @Test
    public void testGetId() {
        Long id = Long.MIN_VALUE;
        AbstractEntityModel instance = new AbstractEntityModel();
        instance.setId(id);
        Long result = instance.getId();
        assertEquals(id, result);
    }

    /**
     * Test of hashCode method, of class AbstractEntityModel.
     */
    @Test
    public void testHashCode() {
        AbstractEntityModel instance = new AbstractEntityModel();
        int result = instance.hashCode();
        assertTrue(result > 0);
    }

    @Test
    public void testHashCodeNaoNulo() {
        AbstractEntityModel instance = new AbstractEntityModel();
        instance.setId(1L);
        int result = instance.hashCode();
        assertTrue(result > 0);
    }

    /**
     * Test of equals method, of class AbstractEntityModel.
     */
    @Test
    public void testEquals() {
        AbstractEntityModel instance = new AbstractEntityModel();
        AbstractEntityModel instance2 = new AbstractEntityModel();
        boolean expResult = true;
        boolean result = instance.equals(instance2);
        assertEquals(expResult, result);
    }

    @Test
    public void testEqualsNaoNulo() {
        AbstractEntityModel instance = new AbstractEntityModel();
        instance.setId(1L);
        AbstractEntityModel instance2 = new AbstractEntityModel();
        boolean expResult = false;
        boolean result = instance.equals(instance2);
        assertEquals(expResult, result);
    }

    @Test
    public void testEqualsNaoNulo2() {
        AbstractEntityModel instance = new AbstractEntityModel();

        AbstractEntityModel instance2 = new AbstractEntityModel();
        instance2.setId(1L);
        boolean expResult = false;
        boolean result = instance.equals(instance2);
        assertEquals(expResult, result);
    }

    @Test
    public void testEqualsNaoNulo3() {
        AbstractEntityModel instance = new AbstractEntityModel();
        instance.setId(2L);
        AbstractEntityModel instance2 = new AbstractEntityModel();
        instance2.setId(1L);
        boolean expResult = false;
        boolean result = instance.equals(instance2);
        assertEquals(expResult, result);
    }

    @Test
    public void testEqualsNaoNulo4() {
        AbstractEntityModel instance = new AbstractEntityModel();
        instance.setId(1L);
        AbstractEntityModel instance2 = new AbstractEntityModel();
        instance2.setId(1L);
        boolean expResult = true;
        boolean result = instance.equals(instance2);
        assertEquals(expResult, result);
    }

    /**
     * Test of equals method, of class AbstractEntityModel.
     */
    @Test
    public void testEqualsParaClassesDiferentes() {
        AbstractEntityModel instance = new AbstractEntityModel();
        Object instance2 = new Object();
        boolean expResult = false;
        boolean result = instance.equals(instance2);
        assertEquals(expResult, result);
    }

    /**
     * Test of equals method, of class AbstractEntityModel.
     */
    @Test
    public void testEqualsParaObjetoNulo() {
        AbstractEntityModel instance = new AbstractEntityModel();
        boolean expResult = false;
        assertEquals(expResult, instance.equals(null));
    }

    /**
     * Test of equals method, of class AbstractEntityModel.
     */
    @Test
    public void testEqualsParaObjetoComIdDiferente() {
        AbstractEntityModel instance = new AbstractEntityModel();
        instance.setId(Long.MIN_VALUE);
        AbstractEntityModel instance2 = new AbstractEntityModel();
        instance2.setId(Long.MAX_VALUE);
        boolean expResult = false;
        assertEquals(expResult, instance.equals(instance2));
    }

    /**
     * Test of isNew method, of class AbstractEntityModel.
     */
    @Test
    public void testIsNew() {
        AbstractEntityModel instance = new AbstractEntityModel();
        boolean expResult = true;
        boolean result = instance.isNew();
        assertEquals(expResult, result);
    }

    @Test
    public void testIsNewFalse() {
        AbstractEntityModel instance = new AbstractEntityModel();
        instance.setId(1L);
        boolean expResult = false;
        boolean result = instance.isNew();
        assertEquals(expResult, result);
    }
}
