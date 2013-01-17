/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufg.inf.es.integracao;

import java.util.ArrayList;
import java.util.Collection;
import org.junit.*;
import static org.junit.Assert.*;

/**
 *
 * @author Alunoinf_2
 */
public class AuthMockTest {
    @Test
    public void testLogin() {
        String user = "professor";
        String password = "123";
        AuthMock instance = new AuthMock();
        Collection<String> expResult = new ArrayList();
        expResult.add("ROLE_PROFESSOR");
        Collection<String> result = instance.login(user, password);
        assertEquals(expResult, result);
    }
}
