package br.ufg.inf.es.web.controller;

import br.ufg.inf.es.base.validation.ValidationException;
import br.ufg.inf.es.integracao.UsuarioService;
import br.ufg.inf.es.model.Usuario;
import br.ufg.inf.es.model.UsuarioPerfil;
import br.ufg.inf.es.web.controller.form.UsuarioForm;
import br.ufg.inf.es.web.datamodel.UsuarioDataModel;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Classe responsável por realizar o controle de acões e dados relativos ao
 * usuário.
 *
 * @author Cézar Augusto Ferreira, Cássio Augusto Silva de Freitas, Luã
 * Silvério, Diogo Ribeiro, Victor
 */
@Component
@Scope("session")
public class UsuarioController
        extends SGBController<Usuario, UsuarioForm, UsuarioService> {

    /**
     * Atributo form
     */
    @Autowired
    private UsuarioForm form;
    /**
     * Atributo service
     */
    @Autowired
    private UsuarioService service;
    
    @Override
    public String openInsertPage() {
        this.getForm().setUsuariosSelecionados(new ArrayList<Usuario>());
        this.getForm().setEntity(new Usuario());
        return super.openInsertPage();
    }
    
    @Override
    public String openInitialPage() {
        List<Usuario> usuarios = new ArrayList<Usuario>(this.getService().list());
        this.getForm().setUserDataModel(new UsuarioDataModel(usuarios));
        return super.openInitialPage();
    }
    
    
    /**
     * Método responsável por obter o formulário de usuário
     *
     * @return
     * <code>UsuarioForm</code>
     */
    @Override
    public UsuarioForm getForm() {
        return this.form;
    }

    /**
     * Método responsável por obter o Service de Usuário
     *
     * @return
     * <code>UsuarioService</code>
     */
    @Override
    public UsuarioService getService() {
        return this.service;
    }

    /**
     * Método responsável por buscar os perfis de acesso ao sistema
     *
     * @return
     * <code>Lista de Perfil</code>
     */
    public List<UsuarioPerfil> getPerfil() {
        return new ArrayList<UsuarioPerfil>(Arrays.asList(UsuarioPerfil.values()));
    }

    /**
     * Método responsável por remover os usuários selecionados.
     */
    public void removerUsuarioSelecionados() {
        Collection<Usuario> usuarios = this.getForm().getUsuariosSelecionados();
        for (Usuario u : usuarios) {
            if (u.getNome().equalsIgnoreCase("administrador")) {
                this.addWarningMessage("Não é possível remover o usuário administrador");
                return;
            }
        }
        try {
            this.service.getDAO().removeAll(usuarios);
            this.addSuccessMessage("arquitetura.msg.sucesso");

            openInitialView();
        } catch (ConstraintViolationException cve) {
            this.addWarningMessage("O usuário está vinculado a outros registros. Não é possível remove-lo.");
        }
    }

    /**
     * Método responsável iniciar os dados do form.
     */
    @Override
    public void initData() {
        this.getForm().setPerfis(Arrays.asList(UsuarioPerfil.values()));        
    }

    /**
     * Método responsável por editar um usuário
     *
     * @return string de navegação
     */
    public String editarUsuario() throws ValidationException{

        super.edit();

        return super.openInitialPage();
    }

    /**
     * Método responsável por criar um novo usuário
     *
     * @return String de navegação para página inicial.
     */
    public String criarUsuario() throws ValidationException{

        super.insert();

        return super.openInitialPage();
    }

    /**
     * Método responsável por retornar string de para pagina de recuperação de
     * senha
     *
     * @return string de navegação para a página de recuperação de senha.
     */
    public String janelaRecuperarSenha() {

        return this.getRootNavigation() + "recuperarSenha";

    }

    /**
     * Método responsável recuperar a senha
     *
     * @return String de navegação para página de login.
     */
    public String recuperarSenha() {
        String email = this.getParameterFromRequest("email");
        try {
            service.recuperarSenha(email);
        } catch (ValidationException ex) {
            this.addErrorMessage("esqueciasenha.emailnaocadastrado");
            return null;
        }
        return "/login.xhtml";
    }

    public Usuario getUsuarioLogado() {
        String nome = ((HttpServletRequest) FacesContext.getCurrentInstance().
                getExternalContext().getRequest()).getUserPrincipal().getName();
        return this.getService().findUsuarioByEmail(nome); 
    }
}