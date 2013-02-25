package br.ufg.inf.es.integracao;

import br.ufg.inf.es.model.Usuario;
import org.apache.commons.mail.HtmlEmail;
import org.apache.commons.mail.EmailException;
import org.junit.Test;
import static org.mockito.Mockito.*;

/**
 *
 * Testes da servico de email
 *
 * @author Victor Carvalho
 */
public class EmailServiceTest {

    /**
     * Test of enviarNovaSenha method, of class EmailService.
     */
    @Test
    public void testEnviarNovaSenhaDeveLancarExcecaoEmCasoDeFalha() throws EmailException {
        Usuario usuario = new Usuario();
        usuario.setEmail("email");
        usuario.setNome("nome");
        usuario.setSenha("senha");

        HtmlEmail emailMock = mock(HtmlEmail.class);
        when(emailMock.send()).thenThrow(new EmailException());

        EmailService.enviarNovaSenha(usuario);
    }
}