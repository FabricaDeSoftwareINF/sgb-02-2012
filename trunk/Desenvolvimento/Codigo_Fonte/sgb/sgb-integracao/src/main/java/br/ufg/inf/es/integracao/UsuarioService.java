package br.ufg.inf.es.integracao;

import br.ufg.inf.es.base.validation.ValidationException;
import br.ufg.inf.es.integracao.annotations.RNG001;
import br.ufg.inf.es.integracao.annotations.RNG002;
import br.ufg.inf.es.integracao.annotations.RNG003;
import br.ufg.inf.es.model.Perfil;
import br.ufg.inf.es.model.Usuario;
import br.ufg.inf.es.model.UsuarioPerfil;
import br.ufg.inf.es.persistencia.PerfilDAO;
import br.ufg.inf.es.persistencia.UsuarioDAO;
import br.ufg.inf.es.persistencia.Usuario_PerfilDAO;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
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
    private Usuario_PerfilDAO usuario_perfilDao;
    @Autowired
    private PerfilDAO perfilDao;

    @Override
    public UsuarioDAO getDAO() {

        return this.dao;
    }

    public void setDao(UsuarioDAO dao) {

        this.dao = dao;
    }

    public Usuario_PerfilDAO getUsuario_perfilDao() {
        return usuario_perfilDao;
    }

    public void setUsuario_perfilDao(Usuario_PerfilDAO usuario_perfilDao) {
        this.usuario_perfilDao = usuario_perfilDao;
    }

    @Override
    @RNG001
    @RNG002
    @RNG003
    public Long insert(Usuario entity) throws ValidationException {

        entity.setDataCadastro(new Date());

        entity.setStatus(true);

        Long id = super.insert(entity);

        for (Perfil perfil : entity.getPerfil()) {
            UsuarioPerfil usuarioPerfil = new UsuarioPerfil();
            usuarioPerfil.setId_usuario(id);
            usuarioPerfil.setId_perfil(perfil.getId());

            usuario_perfilDao.insert(usuarioPerfil);
        }

        return id;
    }

    @Override
    @RNG001
    @RNG002
    @RNG003
    public void update(Usuario entity) throws ValidationException {

        usuario_perfilDao.removeAll(usuario_perfilDao.list(entity.getId()));

        entity.setStatus(true);

        super.update(entity);

        for (Perfil perfil : entity.getPerfil()) {
            UsuarioPerfil usuarioPerfil = new UsuarioPerfil();
            usuarioPerfil.setId_perfil(perfil.getId());
            usuarioPerfil.setId_usuario(entity.getId());

            usuario_perfilDao.insert(usuarioPerfil);
        }
    }

    @Override
    public void remove(Usuario entity) throws ValidationException {

        usuario_perfilDao.removeAll(usuario_perfilDao.list(entity.getId()));

        super.remove(entity);
    }

    @Override
    public void removeAll(Collection<Usuario> collectionEntities) throws ValidationException {

        for (Usuario usuario : collectionEntities) {
            UsuarioPerfil usuarioPerfil = new UsuarioPerfil();
            Collection<UsuarioPerfil> colUsuarioPerfil = usuario_perfilDao.list(usuario.getId());

            usuario_perfilDao.removeAll(colUsuarioPerfil);
        }

        super.removeAll(collectionEntities);
    }

    @Override
    public Collection<Usuario> search(Usuario entity) {

        Collection<Usuario> colUsuario = super.search(entity);

        for (Usuario usuario : colUsuario) {

            Collection<UsuarioPerfil> colUsuarioPerfil = usuario_perfilDao.list(usuario.getId());
            HashSet<Perfil> hsPerfil = new HashSet<Perfil>();

            for (UsuarioPerfil usuPerfil : colUsuarioPerfil) {
                Perfil perfil = perfilDao.find(usuPerfil.getId_perfil());
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

            Collection<UsuarioPerfil> colUsuarioPerfil = usuario_perfilDao.list(usuario.getId());
            HashSet<Perfil> hsPerfil = new HashSet<Perfil>();

            for (UsuarioPerfil usuPerfil : colUsuarioPerfil) {
                Perfil perfil = perfilDao.find(usuPerfil.getId_perfil());
                hsPerfil.add(perfil);
            }
            usuario.setPerfil(hsPerfil);
        }

        return colUsuario;
    }

    @Override
    public void refresh(Usuario entity) {

        UsuarioPerfil usuarioPerfil = new UsuarioPerfil();
        usuarioPerfil.setId_usuario(entity.getId());
        Collection<UsuarioPerfil> colUsuarioPerfil = usuario_perfilDao.search(usuarioPerfil);
        HashSet<Perfil> hsPerfil = new HashSet<Perfil>();
        for (UsuarioPerfil usuPerfil : colUsuarioPerfil) {
            Perfil perfil = perfilDao.find(usuPerfil.getId_perfil());
            hsPerfil.add(perfil);
        }
        entity.setPerfil(hsPerfil);

    }

    public Collection<Perfil> carreguePerfis(Usuario usuario) {
        return usuario_perfilDao.listPerfis(usuario.getId());
    }

    public Usuario authUser(String user, String password) {
        return dao.findUserByEmailAndPassword(user, password);
    }
}