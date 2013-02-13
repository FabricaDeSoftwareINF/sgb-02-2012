/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufg.inf.es.model;

import java.util.Date;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 *
 * @author alunoufg
 */
public class UsuarioTest {

    @Test
    public void testGetSenha() {

        Usuario instance = new Usuario();
        instance.setSenha("123");
        String expResult = "123";
        String result = instance.getSenha();
        assertEquals(expResult, result);

    }

    /**
     * Test of setSenha method, of class Usuario.
     */
    @Test
    public void testSetSenha() {

        String senha = "123";
        Usuario instance = new Usuario();
        instance.setSenha(senha);
        assertEquals(senha, instance.getSenha());
    }

    /**
     * Test of getEmail method, of class Usuario.
     */
    @Test
    public void testGetEmail() {

        Usuario instance = new Usuario();
        instance.setEmail("usuario@email.com");
        String expResult = "usuario@email.com";
        String result = instance.getEmail();
        assertEquals(expResult, result);

    }

    /**
     * Test of setEmail method, of class Usuario.
     */
    @Test
    public void testSetEmail() {
        String email = "usuario@email.com";
        Usuario instance = new Usuario();
        instance.setEmail(email);
        assertEquals(email, instance.getEmail());
    }

    /**
     * Test of getNome method, of class Usuario.
     */
    @Test
    public void testGetNome() {

        Usuario instance = new Usuario();
        String expResult = "nome";
        instance.setNome(expResult);
        String result = instance.getNome();
        assertEquals(expResult, result);

    }

    /**
     * Test of setNome method, of class Usuario.
     */
    @Test
    public void testSetNome() {

        String nome = "usuario";
        Usuario instance = new Usuario();
        instance.setNome(nome);
        assertEquals(nome, instance.getNome());
    }

    /**
     * Test of getSobrenome method, of class Usuario.
     */
    @Test
    public void testGetSobrenome() {

        Usuario instance = new Usuario();
        String expResult = "usuario";
        instance.setSobrenome(expResult);
        String result = instance.getSobrenome();
        assertEquals(expResult, result);

    }

    /**
     * Test of setSobrenome method, of class Usuario.
     */
    @Test
    public void testSetSobrenome() {

        String sobrenome = "usuario";
        Usuario instance = new Usuario();
        instance.setSobrenome(sobrenome);
        String result = instance.getSobrenome();
        assertEquals(sobrenome, result);
    }

    /**
     * Test of getDataCadastro method, of class Usuario.
     */
    @Test
    public void testGetDataCadastro() {

        Usuario instance = new Usuario();
        Date expResult = new Date();
        instance.setDataCadastro(expResult);
        Date result = instance.getDataCadastro();
        assertEquals(expResult, result);

    }

    /**
     * Test of setDataCadastro method, of class Usuario.
     */
    @Test
    public void testSetDataCadastro() {

        Date dataCadastro = new Date();
        Usuario instance = new Usuario();
        instance.setDataCadastro(dataCadastro);
        Date result = instance.getDataCadastro();
        assertEquals(dataCadastro, result);
    }

    /**
     * Test of getStatus method, of class Usuario.
     */
    @Test
    public void testGetStatus() {

        Usuario instance = new Usuario();
        boolean expResult = false;
        instance.setStatus(expResult);
        boolean result = instance.getStatus();
        assertEquals(expResult, result);

    }

    /**
     * Test of setStatus method, of class Usuario.
     */
    @Test
    public void testSetStatus() {

        boolean status = false;
        Usuario instance = new Usuario();
        instance.setStatus(status);

        assertEquals(status, instance.getStatus());
    }

    /**
     * Test of getConfirmacaoEmail method, of class Usuario.
     */
    @Test
    public void testGetConfirmacaoEmail() {

        Usuario instance = new Usuario();
        String expResult = "usuario@mail.com";
        instance.setConfirmacaoEmail(expResult);
        String result = instance.getConfirmacaoEmail();
        assertEquals(expResult, result);

    }

    /**
     * Test of setConfirmacaoEmail method, of class Usuario.
     */
    @Test
    public void testSetConfirmacaoEmail() {

        String confirmacaoEmail = "usuario@email.com";
        Usuario instance = new Usuario();
        instance.setConfirmacaoEmail(confirmacaoEmail);

        assertEquals(confirmacaoEmail, instance.getConfirmacaoEmail());
    }
}
