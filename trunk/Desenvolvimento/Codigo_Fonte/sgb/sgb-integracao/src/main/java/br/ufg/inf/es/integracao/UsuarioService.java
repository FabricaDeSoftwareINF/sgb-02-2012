package br.ufg.inf.es.integracao;

import br.ufg.inf.es.base.util.SgbCryptography;
import br.ufg.inf.es.base.validation.ValidationException;
import br.ufg.inf.es.integracao.annotations.RNG001;
import br.ufg.inf.es.integracao.annotations.RNG002;
import br.ufg.inf.es.integracao.annotations.RNG003;
import br.ufg.inf.es.model.Perfil;
import br.ufg.inf.es.model.Usuario;
import br.ufg.inf.es.model.UsuarioPerfil;
import br.ufg.inf.es.persistencia.PerfilDAO;
import br.ufg.inf.es.persistencia.UsuarioDAO;
import br.ufg.inf.es.persistencia.UsuarioPerfilDAO;
import java.security.SecureRandom;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
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
     * Atributo UsuarioPerfilDAO
     */
    @Autowired
    private UsuarioPerfilDAO usuarioPerfilDao;
    /**
     * Atributo PerfilDAO
     */
    @Autowired
    private PerfilDAO perfilDao;
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

    @Override
    @RNG001
    @RNG002
    @RNG003
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

        Long id = super.insert(entity);

        for (Perfil perfil : entity.getPerfil()) {

            UsuarioPerfil usuarioPerfil = new UsuarioPerfil();

            usuarioPerfil.setIdUsuario(id);

            usuarioPerfil.setIdPerfil(perfil.getId());

            usuarioPerfilDao.insert(usuarioPerfil);
        }

        return id;
    }

    @Override
    @RNG001
    @RNG002
    @RNG003
    /**
     * Método responsável por atualizar uma entidade;
     *
     * @param entity
     * @throws ValidationException
     */
    public void update(Usuario entity) throws ValidationException {

        usuarioPerfilDao.removeAll(usuarioPerfilDao.list(entity.getId()));

        entity.setStatus(true);

        String passEncrypted = cryptography.encrypt(entity.getSenha());

        entity.setSenha(passEncrypted);

        super.update(entity);

        for (Perfil perfil : entity.getPerfil()) {

            UsuarioPerfil usuarioPerfil = new UsuarioPerfil();

            usuarioPerfil.setIdPerfil(perfil.getId());

            usuarioPerfil.setIdUsuario(entity.getId());

            usuarioPerfilDao.insert(usuarioPerfil);
        }
    }

    /**
     * Método responsável por remover uma entidade.
     * @param entity
     * @throws ValidationException 
     */
    @Override
    public void remove(Usuario entity) throws ValidationException {

        usuarioPerfilDao.removeAll(usuarioPerfilDao.list(entity.getId()));

        super.remove(entity);
    }

    /**
     * Método responsável por remover uma coleção de entidades.
     * @param collectionEntities
     * @throws ValidationException 
     */
    @Override
    public void removeAll(Collection<Usuario> collectionEntities) throws ValidationException {

        for (Usuario usuario : collectionEntities) {

            Collection<UsuarioPerfil> colUsuarioPerfil = usuarioPerfilDao.list(usuario.getId());

            usuarioPerfilDao.removeAll(colUsuarioPerfil);
        }

        super.removeAll(collectionEntities);
    }
    
    /**
     * Método responsável por pesquisar uma entidade Usuário.
     */

    @Override
    public Collection<Usuario> search(Usuario entity) {

        Collection<Usuario> colUsuario = super.search(entity);

        for (Usuario usuario : colUsuario) {

            Collection<UsuarioPerfil> colUsuarioPerfil = usuarioPerfilDao.list(usuario.getId());
            HashSet<Perfil> hsPerfil = new HashSet<Perfil>();

            for (UsuarioPerfil usuPerfil : colUsuarioPerfil) {
                Perfil perfil = perfilDao.find(usuPerfil.getIdPerfil());
                hsPerfil.add(perfil);
            }
            usuario.setPerfil(hsPerfil);
        }

        return colUsuario;
    }
    
    /**
     * Método responsável por listar numa coleção todas as entidades do tipo usuário
     * @return coleção de usuário
     */

    @Override
    public Collection<Usuario> list() {

        Collection<Usuario> colUsuario = super.list();

        for (Usuario usuario : colUsuario) {

            Collection<UsuarioPerfil> colUsuarioPerfil = usuarioPerfilDao.list(usuario.getId());

            HashSet<Perfil> hsPerfil = new HashSet<Perfil>();

            for (UsuarioPerfil usuPerfil : colUsuarioPerfil) {

                Perfil perfil = perfilDao.find(usuPerfil.getIdPerfil());

                hsPerfil.add(perfil);
            }
            usuario.setPerfil(hsPerfil);
        }

        return colUsuario;
    }
    
    /**
     * Método responsável por sincronizar os dados da entidade Usuário com 
     * demais dependencias de entidades 
     * 
     * @param entity 
     */

    @Override
    public void refresh(Usuario entity) {

        UsuarioPerfil usuarioPerfil = new UsuarioPerfil();

        usuarioPerfil.setIdUsuario(entity.getId());

        Collection<UsuarioPerfil> colUsuarioPerfil = usuarioPerfilDao.search(usuarioPerfil);

        HashSet<Perfil> hsPerfil = new HashSet<Perfil>();

        for (UsuarioPerfil usuPerfil : colUsuarioPerfil) {

            Perfil perfil = perfilDao.find(usuPerfil.getIdPerfil());

            hsPerfil.add(perfil);
        }

        entity.setPerfil(hsPerfil);

    }

    /**
     * Método responsável por carregar os perfis de um determinado usuário
     * @param usuario
     * @return <code>Coleção de Perfil</code>
     */
    public Collection<Perfil> carreguePerfis(Usuario usuario) {

        return usuarioPerfilDao.listPerfis(usuario.getId());
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
    public void recuperarSenha(String emailUsuario) {

        Usuario usuario = dao.findUserByEmail(emailUsuario);

        if (usuario == null) {

            return;
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

    /**
     * Método responsável por setar um dao para entidade Perfil
     *
     * @param perfilDao
     */
    public void setPerfilDao(PerfilDAO perfilDao) {
        this.perfilDao = perfilDao;
    }

    /**
     * Método responsável por obter o dao da entidade UsuarioPerfil
     */
    public UsuarioPerfilDAO getUsuarioPerfilDao() {

        return this.usuarioPerfilDao;
    }

    /**
     * Método responsável por setar um dao da classe
     * <code>UsuárioPerfilDao</code>
     *
     * @param <code>usuarioPerfilDao</code>
     */
    public void setUsuarioPerfilDao(UsuarioPerfilDAO usuarioPerfilDao) {
        this.usuarioPerfilDao = usuarioPerfilDao;
    }
}