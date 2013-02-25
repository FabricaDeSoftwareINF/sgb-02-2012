/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufg.inf.es.base.validation;

import java.util.ArrayList;
import java.util.Collection;
import org.junit.AfterClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.BeforeClass;

/**
 *
 * @author usuario
 */
public class ValidationTest {
    
   
    /**
     * Test of validate method, of class Validation.
     */
    @Test
    public void testValidate() throws Exception {
        Object object = "";
        Validation instance = new ValidationImpl();
        instance.validate(object);
        assertTrue(true);
    }

    /**
     * Test of isInvalid method, of class Validation.
     */
    @Test
    public void testIsInvalid_String() {
        String value = "a";
        Validation instance = new ValidationImpl();
        boolean expResult = false;
        boolean result = instance.isInvalid(value);
        assertEquals(expResult, result);
        
    }
     @Test
    public void testIsInvalid_StringInvalid() {
        String value = "";
        Validation instance = new ValidationImpl();
        boolean expResult = true;
        boolean result = instance.isInvalid(value);
        assertEquals(expResult, result);
        
    }
      @Test
    public void testIsInvalid_StringNull() {
        String value = null;
        Validation instance = new ValidationImpl();
        boolean expResult = true;
        boolean result = instance.isInvalid(value);
        assertEquals(expResult, result);
      }

    /**
     * Test of isInvalid method, of class Validation.
     */
    @Test
    public void testIsInvalid_CollectionInvalid() {
      
        Collection value = null;
        Validation instance = new ValidationImpl();
        boolean expResult = true;
        boolean result = instance.isInvalid(value);
        assertEquals(expResult, result);
        
    }
    @Test
    public void testIsInvalid_CollectionValid() {
      
        Collection value = new ArrayList();
        value.add("a");
        Validation instance = new ValidationImpl();
        boolean expResult = false;
        boolean result = instance.isInvalid(value);
        assertEquals(expResult, result);
        
    }
      @Test
    public void testIsInvalid_CollectionValidEmpty() {
      
        Collection value = new ArrayList();
        
        Validation instance = new ValidationImpl();
        boolean expResult = true;
        boolean result = instance.isInvalid(value);
        assertEquals(expResult, result);
        
    }

    /**
     * Test of isInvalid method, of class Validation.
     */
    @Test
    public void testIsInvalid_Object() {
      
        Object value = null;
        Validation instance = new ValidationImpl();
        boolean expResult = true;
        boolean result = instance.isInvalid(value);
        assertEquals(expResult, result);
        
    }
     @Test
    public void testIsInvalid_ObjectValid() {
      
        Object value = "b";
        Validation instance = new ValidationImpl();
        boolean expResult = false;
        boolean result = instance.isInvalid(value);
        assertEquals(expResult, result);
        
    }

    public class ValidationImpl extends Validation {

       

        @Override
        public void validate(Object object) throws ValidationException {
            
        }
    }
}
