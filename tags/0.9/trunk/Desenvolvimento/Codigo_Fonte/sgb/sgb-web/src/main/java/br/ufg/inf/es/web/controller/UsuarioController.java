package br.ufg.inf.es.web.controller;

import br.ufg.inf.es.base.validation.ValidationException;
import br.ufg.inf.es.integracao.UsuarioService;
import br.ufg.inf.es.model.Usuario;
import br.ufg.inf.es.model.UsuarioPerfil;
import br.ufg.inf.es.web.controller.form.UsuarioForm;
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
    /**
     * Atributo coleção de usuários selecionados
     */
    private Collection<Usuario> usuarioSelecionado = new ArrayList<Usuario>();

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
     * Método responsável obter a coleção de usuários selecionados.
     *
     * @return
     */
    public Collection<Usuario> getUsuarioSelecionado() {
        return usuarioSelecionado;
    }

    /**
     * Método responsável por setar uma nova coleção de usuários selecionados.
     *
     * @param usuarioSelecionado
     */
    public void setUsuarioSelecionado(Collection<Usuario> usuarioSelecionado) {
        this.usuarioSelecionado = usuarioSelecionado;
    }

    /**
     * Método responsável por adicionar um usuário na Coleção de Usuários
     * Selecionados.
     *
     * @param usuario
     */
    public void selecionaUsuario(Usuario usuario) {
        if (usuarioSelecionado.contains(usuario)) {
            usuarioSelecionado.remove(usuario);

        } else {
            this.usuarioSelecionado.add(usuario);
        }
    }

    /**
     * Método responsável por validar se pode haver uma exclusão ou não,
     * verificando se há a necessidade de exibir o dialog de confirmação.
     */
    public void prepararExclusao() {

        if (this.getForm().getUsuariosSelecionados().length == 0) {
            this.getForm().setExibirDialogExclusao(Boolean.FALSE);
            this.addWarningMessage("Nenhum usuário foi selecionado!");

        } else {
            this.getForm().setExibirDialogExclusao(Boolean.TRUE);
        }
    }

    /**
     * Método responsável por remover os usuários selecionados.
     */
    public void removerUsuarioSelecionados() {
        this.usuarioSelecionado.clear();
        this.usuarioSelecionado.addAll(Arrays.asList(this.getForm().getUsuariosSelecionados()));

        for (Usuario u : this.usuarioSelecionado) {
            if (u.getNome().equalsIgnoreCase("administrador")) {
                this.addWarningMessage("Não é possível remover o usuário administrador");
                this.getForm().setExibirDialogExclusao(Boolean.FALSE);
                return;
            }
        }

        try {
            this.service.getDAO().removeAll(usuarioSelecionado);
            this.addSuccessMessage("arquitetura.msg.sucesso");

            openInitialView();
        } catch (ConstraintViolationException cve) {
            this.addWarningMessage("O usuário está vinculado a outros registros. Não é possível remove-lo.");
        } finally {
            this.getForm().setExibirDialogExclusao(Boolean.FALSE);
        }
    }

    /**
     * Método responsável iniciar os dados do form.
     */
    @Override
    public void initData() {
        this.getForm().setExibirDialogExclusao(Boolean.FALSE);
        this.getForm().setTabelaUsuarios(new ArrayList<Usuario>());
        this.getForm().setPerfis(Arrays.asList(UsuarioPerfil.values()));
        this.getForm().getTabelaUsuarios().addAll(this.getService().list());
    }

    /**
     * Método responsável por editar um usuário
     *
     * @return string de navegação
     */
    public String editarUsuario() {

        super.edit();

        return super.openInitialPage();
    }

    /**
     * Método responsável por criar um novo usuário
     *
     * @return String de navegação para página inicial.
     */
    public String criarUsuario() {

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

    /**
     * Seleciona todos os usuários da grid
     */
    public void selecionaTodos() {
        if (this.usuarioSelecionado.size() == this.getForm().getTabelaUsuarios().size()) {
            this.usuarioSelecionado.clear();

        }
        if (!this.usuarioSelecionado.isEmpty()) {
            this.usuarioSelecionado.clear();
            this.usuarioSelecionado.addAll(this.getForm().getTabelaUsuarios());
            
        } else {
            this.usuarioSelecionado.addAll(this.getForm().getTabelaUsuarios());
        }
    }
    
    public Usuario getUsuarioLogado() {
        String nome = ((HttpServletRequest) FacesContext.getCurrentInstance().
                getExternalContext().getRequest()).getUserPrincipal().getName();
        return this.getService().findUsuarioByEmail(nome); 
    }
}