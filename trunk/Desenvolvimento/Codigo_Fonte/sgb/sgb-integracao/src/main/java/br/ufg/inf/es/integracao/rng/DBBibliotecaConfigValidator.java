package br.ufg.inf.es.integracao.rng;

import br.ufg.inf.es.base.validation.Validation;
import br.ufg.inf.es.base.validation.ValidationException;
import br.ufg.inf.es.model.biblioteca.DBBibliotecaConfig;
import org.springframework.stereotype.Component;

/**
 *
 * @author igor
 */
@Component
public class DBBibliotecaConfigValidator extends Validation<DBBibliotecaConfig> {

    /**
     * Método Responsável por validar os campos da entidade de configuração de
     * comunicação com o sistema da biblioteca.
     *
     * @param object a ser validado
     * @throws ValidationException
     */
    public void validate(DBBibliotecaConfig object) throws ValidationException {

        if (isInvalid(object.getDriver()))  {
            throw new ValidationException("parametros.biblioteca.combobox.RNG018.tipoBanco");

        } else if (isInvalid(object.getUrl())) {
            throw new ValidationException("parametros.biblioteca.label.RNG018.urlService");

        } else if (isInvalid(object.getPorta())) {
            throw new ValidationException("parametros.biblioteca.label.RNG018.porta");

        } else if (isInvalid(object.getNameDataBase())) {
            throw new ValidationException("parametros.biblioteca.label.RNG018.nomeBancoDados");

        } else if (isInvalid(object.getUserDataBase())) {
            throw new ValidationException("parametros.biblioteca.label.RNG018.nomeUsuario");

        } else if (isInvalid(object.getPasswordDataBase())) {
            throw new ValidationException("parametros.biblioteca.label.RNG018.senhaUsuario");

        } else if (isInvalid(object.getTabela())) {
            throw new ValidationException("parametros.biblioteca.label.RNG018.tabela");

        } else if (isInvalid(object.getCampoIdLivroBiblioteca())) {
            throw new ValidationException("parametros.biblioteca.label.RNG018.campoCodigoLivro");

        } else if (isInvalid(object.getCampoTituloLivro())) {
            throw new ValidationException("parametros.biblioteca.label.RNG018.campoTituloLivro");

        } else if (isInvalid(object.getCampoIsbnLivro())) {
            throw new ValidationException("parametros.biblioteca.label.RNG018.campoISBNLivro");

        } else if (isInvalid(object.getCampoAnoLivro())) {
            throw new ValidationException("parametros.biblioteca.label.RNG018.campoAnoLivro");

        } else if (isInvalid(object.getCampoEdicao())) {
            throw new ValidationException("parametros.biblioteca.label.RNG018.campoEdicaoLivro");

        } else if (isInvalid(object.getCampoEditora())) {
            throw new ValidationException("parametros.biblioteca.label.RNG018.campoEditoraLivro");

        } else if (isInvalid(object.getCampoAutor())) {
            throw new ValidationException("parametros.biblioteca.label.RNG018.campoAutorLivro");

        } else if (isInvalid(object.getCampoQuantidadeLivro())) {
            throw new ValidationException("parametros.biblioteca.label.RNG018.campoQuantidadeLivro");
        }
    }
}