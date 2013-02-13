package br.ufg.inf.es.integracao;

import br.ufg.inf.es.base.util.SgbCryptography;
import java.util.ArrayList;
import java.util.Collection;
import org.junit.*;
import static org.junit.Assert.*;

/**
 *
 * @author Alunoinf_2
 */
public class AuthMockTest {

    /**
     * Teste de login
     */
    @Test
    public void testLogin() {
        String user = "professor";

        AuthMock instance = new AuthMock();
        SgbCryptography cryptography = new SgbCryptography();
        String password = cryptography.encrypt("123");
        Collection<String> expResult = new ArrayList();
        expResult.add("ROLE_PROFESSOR");
        Collection<String> result = instance.login(user, password);
        assertEquals(expResult, result);
    }
}
