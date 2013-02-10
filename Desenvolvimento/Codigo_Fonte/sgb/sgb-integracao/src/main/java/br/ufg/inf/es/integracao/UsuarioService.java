package br.ufg.inf.es.integracao;

import br.ufg.inf.es.base.util.SgbCryptography;
import br.ufg.inf.es.base.validation.ValidationException;
import br.ufg.inf.es.model.Usuario;
import br.ufg.inf.es.persistencia.PerfilDAO;
import br.ufg.inf.es.persistencia.UsuarioDAO;
import java.security.SecureRandom;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

/**
 * Classe de serviços relativos a US03 de Manter Usuário
 *
 * @author Cézar Augusto Ferreira
 */
@Component
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class UsuarioService extends GenericService<Usuario> {

    /**
     * Atributo UsuarioDAO
     */
    @Autowired
    private UsuarioDAO dao;

    /**
     * Constante GERADOR_RANDOM
     */
    private static final SecureRandom GERADOR_RANDOM = new SecureRandom();
    /**
     * Constante PASSWORD_LENCHT
     */
    private static final Integer PASSWORD_LENGHT = 8;
    /**
     * Atributo cryptography
     */
    private SgbCryptography cryptography = new SgbCryptography();
    
    /**
     * Método responsável por inserir uma entidade Usuário
     *
     * @param entity
     * @return id da Entidade persistida.
     * @throws ValidationException
     */
    public Long insert(Usuario entity) throws ValidationException {

        entity.setDataCadastro(new Date());

        String oldPass = entity.getSenha();

        String passEncrypted = cryptography.encrypt(oldPass);

        entity.setSenha(passEncrypted);

        entity.setStatus(true);

        return super.insert(entity);
    }
    
    /**
     * Método responsável por autenticar os dados informados para o Usuário
     * @param user
     * @param password
     * @return usuário
     */

    public Usuario authUser(String user, String password) {

        return dao.findUserByEmailAndPassword(user, password);
    }

    /**
     * Método responsável por recuperar a senha do usuário de acordo com seu email cadastrado
     * @param emailUsuario 
     */
    public void recuperarSenha(String emailUsuario) throws ValidationException {

        Usuario usuario = dao.findUserByEmail(emailUsuario);

        if (usuario == null) {

            throw new ValidationException("E-mail não cadastrado");
        }

        String letters = "abcdefghjkmnpqrstuvwxyzABCDEFGHJKMNPQRSTUVWXYZ23456789_-";

        String newPass;

        StringBuilder newPasswordBuilder = new StringBuilder();

        for (int i = 0; i < UsuarioService.PASSWORD_LENGHT; i++) {

            int index = (int) (UsuarioService.GERADOR_RANDOM.nextDouble() * letters.length());

            newPasswordBuilder.append(letters.substring(index, index + 1));
        }

        newPass = newPasswordBuilder.toString();

        usuario.setSenha(newPass);
       
        EmailService.enviarNovaSenha(usuario);
        
        usuario.setSenha(cryptography.encrypt(newPass));
        
        dao.update(usuario);
        
        dao.update(usuario);
    }

    /**
     * Método Responsável por obter o usuário DAO
     *
     * @return
     * <code>UsuarioDAO</code>
     */
    @Override
    public UsuarioDAO getDAO() {

        return this.dao;
    }

    /**
     * Método para setar uma Classe dao ao service
     *
     * @param dao
     */
    public void setDao(UsuarioDAO dao) {
        this.dao = dao;
    }

    /**
     * Método responsável por setar um novo valor para entidade de Criptografia
     *
     * @param cryptography
     */
    public void setCryptography(SgbCryptography cryptography) {
        this.cryptography = cryptography;
    }

}