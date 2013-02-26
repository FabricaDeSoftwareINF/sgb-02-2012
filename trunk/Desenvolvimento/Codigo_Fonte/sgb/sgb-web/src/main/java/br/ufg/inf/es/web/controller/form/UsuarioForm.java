package br.ufg.inf.es.web.controller.form;

import br.ufg.inf.es.model.Usuario;
import br.ufg.inf.es.model.UsuarioPerfil;
import br.ufg.inf.es.web.datamodel.UsuarioDataModel;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Classe de formulário para armazenar os dados das páginas
 *
 * @author Cézar Augusto Ferreira, Cássio Augusto Silva de Freitas
 */
@Component
@Scope("session")
public class UsuarioForm extends GenericForm<Usuario> {

    /**
     * Atributo tabelaUsuarios
     *
     * @author Cássio Augusto Silva de Freitas
     */
    private Collection<Usuario> tabelaUsuarios;
    private Collection<UsuarioPerfil> perfis;
    /**
     * atributo userDataModel
     */
    private UsuarioDataModel userDataModel;
    /**
     * atributo usuariosSlecionados
     */
    private Usuario[] usuariosSelecionados;
    private Boolean exibirDialogExclusao;

    /**
     * obtem a flag para exibir o dialog de remocao
     */
    public Boolean getExibirDialogExclusao() {
        return exibirDialogExclusao;
    }

    /**
     * define um valor booleano para a flag do dialog de exclusao
     *
     * @param exibirDialogExclusao
     */
    public void setExibirDialogExclusao(Boolean exibirDialogExclusao) {
        this.exibirDialogExclusao = exibirDialogExclusao;
    }

    /**
     * Método getTabelaUsuarios()
     *
     * @author Cássio Augusto Silva de Freitas
     * @return
     */
    public Collection<Usuario> getTabelaUsuarios() {
        return tabelaUsuarios;
    }

    public Collection<UsuarioPerfil> getPerfis() {
        return perfis;
    }

    public void setPerfis(Collection<UsuarioPerfil> perfis) {
        this.perfis = perfis;
    }

    /**
     * Método setgetTabelaUsuarios()
     *
     * @author Cássio Augusto Silva de Freitas
     * @param tabelaUsuarios
     */
    public void setTabelaUsuarios(Collection<Usuario> tabelaUsuarios) {
        this.tabelaUsuarios = tabelaUsuarios;
    }

    public UsuarioDataModel getUserDataModel() {

        List<Usuario> usuarios = new ArrayList<Usuario>(this.getTabelaUsuarios());

        userDataModel = new UsuarioDataModel(usuarios);

        return userDataModel;
    }

    public void setUserDataModel(UsuarioDataModel userDataModel) {
        this.userDataModel = userDataModel;
    }

    public Usuario[] getUsuariosSelecionados() {

        Usuario[] retorno = null;

        if (this.usuariosSelecionados != null) {

            retorno = this.usuariosSelecionados.clone();
        }

        return retorno;
    }

    public void setUsuariosSelecionados(Usuario[] usuariosSelecionados) {

        if (usuariosSelecionados != null) {

            this.usuariosSelecionados = (Usuario[]) usuariosSelecionados.clone();

        }
    }
}