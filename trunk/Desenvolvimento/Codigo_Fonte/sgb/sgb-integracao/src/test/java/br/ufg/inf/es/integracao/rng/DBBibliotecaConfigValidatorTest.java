package br.ufg.inf.es.integracao.rng;

import br.ufg.inf.es.base.persistence.biblioteca.DBDriver;
import br.ufg.inf.es.base.validation.ValidationException;
import br.ufg.inf.es.model.biblioteca.DBBibliotecaConfig;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * Testes para o validador da entidade DBBibliotecaConfig
 * 
 * @author Victor Carvalho
 */
public class DBBibliotecaConfigValidatorTest {

    private DBBibliotecaConfigValidator validator;
    private DBBibliotecaConfig object;
    private final byte[] SENHA = "senha".getBytes();

    @Before
    public void init() {
        validator = new DBBibliotecaConfigValidator();
        object = new DBBibliotecaConfig();
    }

    @Test(expected = ValidationException.class)
    public void objetoSemODriverDeveLancarExcecao() throws ValidationException {
        validator.validate(object);
    }

    @Test(expected = ValidationException.class)
    public void objetoSemUrlServiceDeveLancarExcecao() throws ValidationException {
        object.setDriver(DBDriver.Oracle);

        validator.validate(object);
    }

    @Test(expected = ValidationException.class)
    public void objetoComUrlServiceEmBrancoDeveLancarExcecao() throws ValidationException {
        object.setDriver(DBDriver.Oracle);
        object.setUrl("  ");

        validator.validate(object);
    }

    @Test(expected = ValidationException.class)
    public void objetoSemPortaDeveLancarExcecao() throws ValidationException {
        object.setDriver(DBDriver.Oracle);
        object.setUrl("url");

        validator.validate(object);
    }

    @Test(expected = ValidationException.class)
    public void objetoComPortaEmBrancoDeveLancarExcecao() throws ValidationException {
        object.setDriver(DBDriver.Oracle);
        object.setUrl("url");
        object.setPorta("  ");

        validator.validate(object);
    }

    @Test(expected = ValidationException.class)
    public void objetoSemNomeBancoDeveLancarExcecao() throws ValidationException {
        object.setDriver(DBDriver.Oracle);
        object.setUrl("url");
        object.setPorta("porta");

        validator.validate(object);
    }

    @Test(expected = ValidationException.class)
    public void objetoComNomeBancoEmBrancoDeveLancarExcecao() throws ValidationException {
        object.setDriver(DBDriver.Oracle);
        object.setUrl("url");
        object.setPorta("porta");
        object.setNameDataBase("  ");
        validator.validate(object);
    }

    @Test(expected = ValidationException.class)
    public void objetoSemUsuarioDeveLancarExcecao() throws ValidationException {
        object.setDriver(DBDriver.Oracle);
        object.setUrl("url");
        object.setPorta("porta");
        object.setNameDataBase("banco");

        validator.validate(object);
    }

    @Test(expected = ValidationException.class)
    public void objetoComUsuarioEmBrancoDeveLancarExcecao() throws ValidationException {
        object.setDriver(DBDriver.Oracle);
        object.setUrl("url");
        object.setPorta("porta");
        object.setNameDataBase("banco");
        object.setUserDataBase("  ");

        validator.validate(object);
    }

    @Test(expected = ValidationException.class)
    public void objetoSemSenhaDeveLancarExcecao() throws ValidationException {
        object.setDriver(DBDriver.Oracle);
        object.setUrl("url");
        object.setPorta("porta");
        object.setNameDataBase("banco");
        object.setUserDataBase("usuario");

        validator.validate(object);
    }

    @Test(expected = ValidationException.class)
    public void objetoSemTabelaDeveLancarExcecao() throws ValidationException {
        object.setDriver(DBDriver.Oracle);
        object.setUrl("url");
        object.setPorta("porta");
        object.setNameDataBase("banco");
        object.setUserDataBase("user");
        object.setPasswordDataBase(SENHA);

        validator.validate(object);
    }

    @Test(expected = ValidationException.class)
    public void objetoComTabelaEmBrancoDeveLancarExcecao() throws ValidationException {
        object.setDriver(DBDriver.Oracle);
        object.setUrl("url");
        object.setPorta("porta");
        object.setNameDataBase("banco");
        object.setUserDataBase("user");
        object.setPasswordDataBase(SENHA);
        object.setTabela("  ");

        validator.validate(object);
    }

    @Test(expected = ValidationException.class)
    public void objetoSemCampoTituloLivroDeveLancarExcecao() throws ValidationException {
        object.setDriver(DBDriver.Oracle);
        object.setUrl("url");
        object.setPorta("porta");
        object.setNameDataBase("banco");
        object.setUserDataBase("user");
        object.setPasswordDataBase(SENHA);
        object.setTabela("tabela");

        validator.validate(object);
    }

    @Test(expected = ValidationException.class)
    public void objetoComCampoTituloLivroEmBrancoDeveLancarExcecao() throws ValidationException {
        object.setDriver(DBDriver.Oracle);
        object.setUrl("url");
        object.setPorta("porta");
        object.setNameDataBase("banco");
        object.setUserDataBase("user");
        object.setPasswordDataBase(SENHA);
        object.setTabela("tabela");
        object.setCampoTituloLivro("  ");

        validator.validate(object);
    }

    @Test(expected = ValidationException.class)
    public void objetoSemCampoIdLivroDeveLancarExcecao() throws ValidationException {
        object.setDriver(DBDriver.Oracle);
        object.setUrl("url");
        object.setPorta("porta");
        object.setNameDataBase("banco");
        object.setUserDataBase("user");
        object.setPasswordDataBase(SENHA);
        object.setTabela("tabela");
        object.setCampoTituloLivro("livro");

        validator.validate(object);
    }

    @Test(expected = ValidationException.class)
    public void objetoComCampoIdLivroEmBrancoDeveLancarExcecao() throws ValidationException {
        object.setDriver(DBDriver.Oracle);
        object.setUrl("url");
        object.setPorta("porta");
        object.setNameDataBase("banco");
        object.setUserDataBase("user");
        object.setPasswordDataBase(SENHA);
        object.setTabela("tabela");
        object.setCampoTituloLivro("livro");
        object.setCampoIdLivroBiblioteca("  ");

        validator.validate(object);
    }

    @Test(expected = ValidationException.class)
    public void objetoSemCampoAnoLivroDeveLancarExcecao() throws ValidationException {
        object.setDriver(DBDriver.Oracle);
        object.setUrl("url");
        object.setPorta("porta");
        object.setNameDataBase("banco");
        object.setUserDataBase("user");
        object.setPasswordDataBase(SENHA);
        object.setTabela("tabela");
        object.setCampoTituloLivro("livro");
        object.setCampoIdLivroBiblioteca("idlivro");

        validator.validate(object);
    }

    @Test(expected = ValidationException.class)
    public void objetoComCampoAnoLivroEmBrancoDeveLancarExcecao() throws ValidationException {
        object.setDriver(DBDriver.Oracle);
        object.setUrl("url");
        object.setPorta("porta");
        object.setNameDataBase("banco");
        object.setUserDataBase("user");
        object.setPasswordDataBase(SENHA);
        object.setTabela("tabela");
        object.setCampoTituloLivro("livro");
        object.setCampoIdLivroBiblioteca("idlivro");
        object.setCampoAnoLivro("  ");

        validator.validate(object);
    }

    @Test(expected = ValidationException.class)
    public void objetoSemCampoLivroDeveLancarExcecao() throws ValidationException {
        object.setDriver(DBDriver.Oracle);
        object.setUrl("url");
        object.setPorta("porta");
        object.setNameDataBase("banco");
        object.setUserDataBase("user");
        object.setPasswordDataBase(SENHA);
        object.setTabela("tabela");
        object.setCampoTituloLivro("livro");
        object.setCampoIdLivroBiblioteca("idlivro");
        object.setCampoAnoLivro("ano");

        validator.validate(object);
    }

    @Test(expected = ValidationException.class)
    public void objetoComCampoEdicaoEmBrancoDeveLancarExcecao() throws ValidationException {
        object.setDriver(DBDriver.Oracle);
        object.setUrl("url");
        object.setPorta("porta");
        object.setNameDataBase("banco");
        object.setUserDataBase("user");
        object.setPasswordDataBase(SENHA);
        object.setTabela("tabela");
        object.setCampoTituloLivro("livro");
        object.setCampoIdLivroBiblioteca("idlivro");
        object.setCampoAnoLivro("ano");
        object.setCampoEdicao("  ");

        validator.validate(object);
    }

    @Test(expected = ValidationException.class)
    public void objetoSemCampoEditoraDeveLancarExcecao() throws ValidationException {
        object.setDriver(DBDriver.Oracle);
        object.setUrl("url");
        object.setPorta("porta");
        object.setNameDataBase("banco");
        object.setUserDataBase("user");
        object.setPasswordDataBase(SENHA);
        object.setTabela("tabela");
        object.setCampoTituloLivro("livro");
        object.setCampoIdLivroBiblioteca("idlivro");
        object.setCampoAnoLivro("ano");
        object.setCampoEdicao("edicao");

        validator.validate(object);
    }

    @Test(expected = ValidationException.class)
    public void objetoComCampoEditoraEmBrancoDeveLancarExcecao() throws ValidationException {
        object.setDriver(DBDriver.Oracle);
        object.setUrl("url");
        object.setPorta("porta");
        object.setNameDataBase("banco");
        object.setUserDataBase("user");
        object.setPasswordDataBase(SENHA);
        object.setTabela("tabela");
        object.setCampoTituloLivro("livro");
        object.setCampoIdLivroBiblioteca("idlivro");
        object.setCampoAnoLivro("ano");
        object.setCampoEdicao("edicao");
        object.setCampoEditora("  ");

        validator.validate(object);
    }

    @Test(expected = ValidationException.class)
    public void objetoSemCampoAutorDeveLancarExcecao() throws ValidationException {
        object.setDriver(DBDriver.Oracle);
        object.setUrl("url");
        object.setPorta("porta");
        object.setNameDataBase("banco");
        object.setUserDataBase("user");
        object.setPasswordDataBase(SENHA);
        object.setTabela("tabela");
        object.setCampoTituloLivro("livro");
        object.setCampoIdLivroBiblioteca("idlivro");
        object.setCampoAnoLivro("ano");
        object.setCampoEdicao("edicao");
        object.setCampoEditora("editora");

        validator.validate(object);
    }

    @Test(expected = ValidationException.class)
    public void objetoComCampAutorEmBrancoDeveLancarExcecao() throws ValidationException {
        object.setDriver(DBDriver.Oracle);
        object.setUrl("url");
        object.setPorta("porta");
        object.setNameDataBase("banco");
        object.setUserDataBase("user");
        object.setPasswordDataBase(SENHA);
        object.setTabela("tabela");
        object.setCampoTituloLivro("livro");
        object.setCampoIdLivroBiblioteca("idlivro");
        object.setCampoAnoLivro("ano");
        object.setCampoEdicao("edicao");
        object.setCampoEditora("editora");
        object.setCampoAutor("   ");

        validator.validate(object);
    }

    @Test(expected = ValidationException.class)
    public void objetoSemCampoQuantidadeLivroDeveLancarExcecao() throws ValidationException {
        object.setDriver(DBDriver.Oracle);
        object.setUrl("url");
        object.setPorta("porta");
        object.setNameDataBase("banco");
        object.setUserDataBase("user");
        object.setPasswordDataBase(SENHA);
        object.setTabela("tabela");
        object.setCampoTituloLivro("livro");
        object.setCampoIdLivroBiblioteca("idlivro");
        object.setCampoAnoLivro("ano");
        object.setCampoEdicao("edicao");
        object.setCampoEditora("editora");
        object.setCampoAutor("autor");

        validator.validate(object);
    }

    @Test(expected = ValidationException.class)
    public void objetoComCamQuantidadeLivroEmBrancoDeveLancarExcecao() throws ValidationException {
        object.setDriver(DBDriver.Oracle);
        object.setUrl("url");
        object.setPorta("porta");
        object.setNameDataBase("banco");
        object.setUserDataBase("user");
        object.setPasswordDataBase(SENHA);
        object.setTabela("tabela");
        object.setCampoTituloLivro("livro");
        object.setCampoIdLivroBiblioteca("idlivro");
        object.setCampoAnoLivro("ano");
        object.setCampoEdicao("edicao");
        object.setCampoEditora("editora");
        object.setCampoAutor("autor");
        object.setCampoQuantidadeLivro(" ");

        validator.validate(object);
    }

    @Test
    public void objetValidoNaoDeveLancarExcecao() throws ValidationException {
        object.setDriver(DBDriver.Oracle);
        object.setUrl("url");
        object.setPorta("porta");
        object.setNameDataBase("banco");
        object.setUserDataBase("user");
        object.setPasswordDataBase(SENHA);
        object.setTabela("tabela");
        object.setCampoTituloLivro("livro");
        object.setCampoIdLivroBiblioteca("idlivro");
        object.setCampoAnoLivro("ano");
        object.setCampoEdicao("edicao");
        object.setCampoEditora("editora");
        object.setCampoAutor("autor");
        object.setCampoQuantidadeLivro("2");

        validator.validate(object);
    }
}