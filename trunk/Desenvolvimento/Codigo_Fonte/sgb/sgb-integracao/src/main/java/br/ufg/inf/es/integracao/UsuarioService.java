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
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Random;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

/**
 * @author Cézar Augusto Ferreira
 */
@Component
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class UsuarioService extends GenericService<Usuario> {

    @Autowired
    private UsuarioDAO dao;
    @Autowired
    private UsuarioPerfilDAO usuarioPerfilDao;
    @Autowired
    private PerfilDAO perfilDao;
    
    private SgbCryptography cryptography = new SgbCryptography();

    @Override
    public UsuarioDAO getDAO() {

        return this.dao;
    }

    public void setDao(UsuarioDAO dao) {

        this.dao = dao;
    }

    public UsuarioPerfilDAO getUsuarioPerfilDao() {
        return usuarioPerfilDao;
    }

    public void setUsuarioPerfilDao(UsuarioPerfilDAO usuarioPerfilDao) {
        this.usuarioPerfilDao = usuarioPerfilDao;
    }

    @Override
    @RNG001
    @RNG002
    @RNG003
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

    @Override
    public void remove(Usuario entity) throws ValidationException {

        usuarioPerfilDao.removeAll(usuarioPerfilDao.list(entity.getId()));

        super.remove(entity);
    }

    @Override
    public void removeAll(Collection<Usuario> collectionEntities) throws ValidationException {

        for (Usuario usuario : collectionEntities) {
           
            Collection<UsuarioPerfil> colUsuarioPerfil = usuarioPerfilDao.list(usuario.getId());

            usuarioPerfilDao.removeAll(colUsuarioPerfil);
        }

        super.removeAll(collectionEntities);
    }

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

    public Collection<Perfil> carreguePerfis(Usuario usuario) {
        return usuarioPerfilDao.listPerfis(usuario.getId());
    }

    public Usuario authUser(String user, String password) {
        return dao.findUserByEmailAndPassword(user, password);
    }

    public void recuperarSenha(String emailUsuario) {
        Usuario usuario = dao.findUserByEmail(emailUsuario);
        
        if(usuario == null) return;
        
        String letters = "abcdefghjkmnpqrstuvwxyzABCDEFGHJKMNPQRSTUVWXYZ23456789_-";

        Integer passwordLength = 8;
        String newPass = "";
        for (int i=0; i<passwordLength; i++){
            int index = (int)(new Random().nextDouble()*letters.length());
            newPass += letters.substring(index, index+1);
        }
        
        usuario.setSenha(newPass);
        EmailService.enviarNovaSenha(usuario);
        usuario.setSenha(cryptography.encrypt(newPass));
        dao.update(usuario);
        dao.update(usuario);
    }
}