package br.ufg.inf.es.persistencia.biblioteca;

import br.ufg.inf.es.base.util.UtilObjeto;
import br.ufg.inf.es.base.util.cripto.CriptoGeneric;
import br.ufg.inf.es.base.validation.ValidationException;
import br.ufg.inf.es.model.biblioteca.DBBibliotecaConfig;
import br.ufg.inf.es.model.biblioteca.LivroBiblioteca;
import java.io.Serializable;
import java.nio.charset.Charset;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javassist.NotFoundException;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * DAO para a entidade LivroBiblioteca
 *
 * @author igor
 */
@Component
@Transactional(rollbackFor = ValidationException.class)
public class LivrosBibliotecaDAO implements Serializable {

    /**
     * Campo connection
     */
    private Connection connection;
    /**
     * Campo dbConfig
     */
    private DBBibliotecaConfig dbConfig;
    /**
     * Campo bBibliotecaConfigDAO
     */
    @Autowired
    private DBBibliotecaConfigDAO bBibliotecaConfigDAO;
    private String tabelaBiblioteca;
    private String colunaId;
    private String colunaTitulo;
    private String colunaISBN;
    private String colunaAno;
    private String colunaEdicao;
    private String colunaEditora;
    private String colunaAutor;
    private String colunaQuantidade;

    @PostConstruct
    public void initData() {
        this.dbConfig = bBibliotecaConfigDAO.getBibliotecaCfg();
        tabelaBiblioteca = this.dbConfig.getTabela();
        colunaId = this.dbConfig.getCampoIdLivroBiblioteca();
        colunaTitulo = this.dbConfig.getCampoTituloLivro();
        colunaISBN = this.dbConfig.getCampoIsbnLivro();
        colunaAno = this.dbConfig.getCampoAnoLivro();
        colunaEdicao = this.dbConfig.getCampoEdicao();
        colunaEditora = this.dbConfig.getCampoEditora();
        colunaAutor = this.dbConfig.getCampoAutor();
        colunaQuantidade = this.dbConfig.getCampoQuantidadeLivro();
    }

    /**
     * Método que cria a conexão do banco de dados.
     *
     * @throws NotFoundException
     * @throws SQLException
     */
    private void createConnection() throws NotFoundException, SQLException {

        if (dbConfig == null) {
            throw new NotFoundException("Instância da classe DBBibliotecaConfig "
                    + "passada como parâmetro não instanciada.");

        }

        byte[] senha = new CriptoGeneric().decriptografa(dbConfig.getPasswordDataBase());

        if (senha != null) {
            String pass = new String(senha, Charset.forName("UTF-8"));


            this.connection = Conecta.getSessionConnection(dbConfig.getDriver(),
                    dbConfig.getUrl(), dbConfig.getPorta(), dbConfig.getNameDataBase(),
                    dbConfig.getUserDataBase(), pass);
            if (this.connection == null) {
                StringBuilder exception = new StringBuilder();
                exception.append("Conexão não estabelecida com o servidor: ");
                exception.append(dbConfig.getUrl());
                exception.append(":");
                exception.append(dbConfig.getPorta());
                throw new SQLException(exception.toString());
            }
        }
    }

    /**
     * Método que obtém os livros pelo título.
     *
     * @param titulo
     * @return List<LivroBiblioteca>
     * @throws NotFoundException
     * @throws SQLException
     */
    public List<LivroBiblioteca> getLivrosBibliotecaTitulo(String titulo) throws
            NotFoundException, SQLException {

        List<LivroBiblioteca> livros = null;

        createConnection();

        initData();

        StringBuilder sb = new StringBuilder();
        sb.append("SELECT ").
                append(colunaId).append(", ").
                append(colunaTitulo).append(", ").
                append(colunaISBN).append(",").
                append(colunaAno).append(", ").
                append(colunaEdicao).append(", ").
                append(colunaEditora).append(", ").
                append(colunaAutor).append(", ").
                append(colunaQuantidade).
                append(" FROM ").
                append(tabelaBiblioteca).
                append(" WHERE ").
                append("UPPER( ").
                append(colunaTitulo).
                append(") ").
                append("LIKE '%").
                append(titulo.toUpperCase()).
                append("%'");

        PreparedStatement stant = null;

        ResultSet result = null;

        try {

            if (connection == null) {
                return new ArrayList<LivroBiblioteca>();
            }
            
            stant = this.connection.prepareStatement(sb.toString());

            result = stant.executeQuery();

            if (result.next()) {
                livros = new ArrayList<LivroBiblioteca>();
                do {
                    LivroBiblioteca livroBiblioteca = new LivroBiblioteca();

                    livroBiblioteca.setId(result.getBigDecimal(colunaId).longValue());
                    livroBiblioteca.setNome(result.getString(colunaTitulo));
                    livroBiblioteca.setIsbn(result.getString(colunaISBN));
                    livroBiblioteca.setAno(result.getString(colunaAno));
                    livroBiblioteca.setEdicao(result.getString(colunaEdicao));
                    livroBiblioteca.setEditora(result.getString(colunaEditora));
                    livroBiblioteca.setAutor(result.getString(colunaAutor));
                    livroBiblioteca.setQuantidade(result.getInt(colunaQuantidade));

                    livros.add(livroBiblioteca);
                } while (result.next());
            }

        } catch (SQLException sql) {

            Logger.getAnonymousLogger().log(Level.SEVERE, sql.getMessage());

        } finally {

            if (UtilObjeto.isReferencia(result)) {

                result.close();
            }

            if (UtilObjeto.isReferencia(stant)) {

                stant.close();
            }

            if (this.connection != null && !this.connection.isClosed()) {
                this.connection.close();
            }
        }

        return livros;
    }

    /**
     * Método que obtém os livros da biblioteca pelo título e pelo isbn.
     *
     * @param titulo
     * @param isbn
     * @return List<LivroBiblioteca>
     * @throws NotFoundException
     * @throws SQLException
     */
    public List<LivroBiblioteca> getLivrosBibliotecaTitulo(String titulo, String isbn)
            throws NotFoundException, SQLException {

        List<LivroBiblioteca> livros = null;

        createConnection();

        initData();

        StringBuilder sb = new StringBuilder();
        sb.append("SELECT ").
                append(colunaId).append(", ").
                append(colunaTitulo).append(", ").
                append(colunaISBN).append(",").
                append(colunaAno).append(", ").
                append(colunaEdicao).append(", ").
                append(colunaEditora).append(", ").
                append(colunaAutor).append(", ").
                append(colunaQuantidade).
                append(" FROM ").
                append(tabelaBiblioteca).
                append(" WHERE ").
                append(" WHERE ").
                append("UPPER( ").
                append(colunaTitulo).
                append(") ").
                append("LIKE '%").
                append(titulo.toUpperCase()).
                append("%'").
                append(" AND ").
                append(colunaISBN).
                append(" = ").
                append(isbn);

        PreparedStatement stant = null;

        ResultSet result = null;

        try {
            
            if (connection == null) {
                return new ArrayList<LivroBiblioteca>();
            }

            stant = this.connection.prepareStatement(sb.toString());

            result = stant.executeQuery();
            if (result.next()) {
                livros = new ArrayList<LivroBiblioteca>();
                do {
                    LivroBiblioteca livroBiblioteca = new LivroBiblioteca();

                    livroBiblioteca.setId(result.getBigDecimal(colunaId).longValue());
                    livroBiblioteca.setNome(result.getString(colunaTitulo));
                    livroBiblioteca.setIsbn(result.getString(colunaISBN));
                    livroBiblioteca.setAno(result.getString(colunaAno));
                    livroBiblioteca.setEdicao(result.getString(colunaEdicao));
                    livroBiblioteca.setEditora(result.getString(colunaEditora));
                    livroBiblioteca.setAutor(result.getString(colunaAutor));
                    livroBiblioteca.setQuantidade(result.getInt(colunaQuantidade));

                    livros.add(livroBiblioteca);
                } while (result.next());
            }

        } catch (SQLException sql) {

            Logger.getAnonymousLogger().log(Level.SEVERE, sql.getMessage(), sql);
        } finally {

            if (UtilObjeto.isReferencia(result)) {

                result.close();
            }

            if (UtilObjeto.isReferencia(stant)) {

                stant.close();
            }

            if (this.connection != null && !this.connection.isClosed()) {
                this.connection.close();
            }

        }

        return livros;
    }

    /**
     * Método que traz todos os livros da biblioteca.
     *
     * @return
     * @throws NotFoundException
     * @throws SQLException
     */
    public List<LivroBiblioteca> getLivroBibliotecaAll() throws NotFoundException,
            SQLException {
        List<LivroBiblioteca> livros = null;

        createConnection();

        initData();

        StringBuilder sb = new StringBuilder();
        sb.append("SELECT ").
                append(colunaId).append(", ").
                append(colunaTitulo).append(", ").
                append(colunaISBN).append(",").
                append(colunaAno).append(", ").
                append(colunaEdicao).append(", ").
                append(colunaEditora).append(", ").
                append(colunaAutor).append(", ").
                append(colunaQuantidade).
                append(" FROM ").
                append(tabelaBiblioteca);

        PreparedStatement stant = null;

        ResultSet result = null;

        try {
            
            if (connection == null) {
                return new ArrayList<LivroBiblioteca>();
            }
            
            stant = this.connection.prepareStatement(sb.toString());

            result = stant.executeQuery();

            if (result.next()) {
                livros = new ArrayList<LivroBiblioteca>();
                do {
                    LivroBiblioteca livroBiblioteca = new LivroBiblioteca();

                    livroBiblioteca.setId(result.getBigDecimal(colunaId).longValue());
                    livroBiblioteca.setNome(result.getString(colunaTitulo));
                    livroBiblioteca.setIsbn(result.getString(colunaISBN));
                    livroBiblioteca.setAno(result.getString(colunaAno));
                    livroBiblioteca.setEdicao(result.getString(colunaEdicao));
                    livroBiblioteca.setEditora(result.getString(colunaEditora));
                    livroBiblioteca.setAutor(result.getString(colunaAutor));
                    livroBiblioteca.setQuantidade(result.getInt(colunaQuantidade));

                    livros.add(livroBiblioteca);
                } while (result.next());
            }

        } catch (SQLException sql) {

            Logger.getAnonymousLogger().log(Level.SEVERE, sql.getMessage(), sql);

        } finally {

            if (UtilObjeto.isReferencia(result)) {

                result.close();
            }

            if (UtilObjeto.isReferencia(stant)) {

                stant.close();
            }

            if (this.connection != null && !this.connection.isClosed()) {
                this.connection.close();
            }
        }

        return livros;
    }

    /**
     * Método que obtém o livro da biblioteca pelo id.
     *
     * @param titulo
     * @param isbn
     * @return List<LivroBiblioteca>
     * @throws NotFoundException
     * @throws SQLException
     */
    public LivroBiblioteca getLivroBibliotecaCodigo(Long idLivro)
            throws NotFoundException, SQLException {

        LivroBiblioteca livro = null;

        createConnection();

        initData();

        StringBuilder sb = new StringBuilder();
        sb.append("SELECT ").
                append(colunaId).append(", ").
                append(colunaTitulo).append(", ").
                append(colunaISBN).append(",").
                append(colunaAno).append(", ").
                append(colunaEdicao).append(", ").
                append(colunaEditora).append(", ").
                append(colunaAutor).append(", ").
                append(colunaQuantidade).
                append(" FROM ").
                append(tabelaBiblioteca).
                append(" WHERE ").
                append(colunaId).append(" = ").append(idLivro);

        PreparedStatement stant = null;

        ResultSet result = null;

        try {
            
            if (connection == null) {
                return new LivroBiblioteca();
            }

            stant = this.connection.prepareStatement(sb.toString());

            result = stant.executeQuery();
            if (result.next()) {
                livro = new LivroBiblioteca();

                livro.setId(result.getBigDecimal(colunaId).longValue());
                livro.setNome(result.getString(colunaTitulo));
                livro.setIsbn(result.getString(colunaISBN));
                livro.setAno(result.getString(colunaAno));
                livro.setEdicao(result.getString(colunaEdicao));
                livro.setEditora(result.getString(colunaEditora));
                livro.setAutor(result.getString(colunaAutor));
                livro.setQuantidade(result.getInt(colunaQuantidade));


            }

        } catch (SQLException sql) {

            Logger.getAnonymousLogger().log(Level.SEVERE, sql.getMessage(), sql);
        } finally {

            if (UtilObjeto.isReferencia(result)) {

                result.close();
            }

            if (UtilObjeto.isReferencia(stant)) {

                stant.close();
            }

            if (this.connection != null && !this.connection.isClosed()) {
                this.connection.close();
            }

        }

        return livro;
    }

    public Collection<LivroBiblioteca> search(LivroBiblioteca entidade) {

        Collection<LivroBiblioteca> livroBibliotecas = new ArrayList<LivroBiblioteca>();

        if (UtilObjeto.isReferencia(entidade.getId())) {
            try {
                livroBibliotecas.add(getLivroBibliotecaCodigo(entidade.getId()));
            } catch (NotFoundException ex) {
                Logger.getLogger(LivrosBibliotecaDAO.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(LivrosBibliotecaDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        if (UtilObjeto.isReferencia(entidade.getNome()) && !entidade.getNome().isEmpty()) {
            try {
                livroBibliotecas = getLivrosBibliotecaTitulo(entidade.getNome());
            } catch (NotFoundException ex) {
                Logger.getLogger(LivrosBibliotecaDAO.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(LivrosBibliotecaDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return livroBibliotecas;
    }

    /**
     * Método que obtém os livros pelo isbn.
     *
     * @param isbn
     * @return List<LivroBiblioteca>
     * @throws NotFoundException
     * @throws SQLException
     */
    public List<LivroBiblioteca> getLivrosBibliotecaIsbn(String isbn) throws
            NotFoundException, SQLException {

        List<LivroBiblioteca> livros = null;

        createConnection();

        initData();

        StringBuilder sb = new StringBuilder();
        sb.append("SELECT ").
                append(colunaId).append(", ").
                append(colunaTitulo).append(", ").
                append(colunaISBN).append(",").
                append(colunaAno).append(", ").
                append(colunaEdicao).append(", ").
                append(colunaEditora).append(", ").
                append(colunaAutor).append(", ").
                append(colunaQuantidade).
                append(" FROM ").
                append(tabelaBiblioteca).
                append(" WHERE ").
                append(colunaISBN).
                append(" = ").
                append(isbn.toUpperCase());

        PreparedStatement stant = null;

        ResultSet result = null;

        try {
            
            if (connection == null) {
                return new ArrayList<LivroBiblioteca>();
            }

            stant = this.connection.prepareStatement(sb.toString());

            result = stant.executeQuery();

            if (result.next()) {
                livros = new ArrayList<LivroBiblioteca>();
                do {
                    LivroBiblioteca livroBiblioteca = new LivroBiblioteca();

                    livroBiblioteca.setId(result.getBigDecimal(colunaId).longValue());
                    livroBiblioteca.setNome(result.getString(colunaTitulo));
                    livroBiblioteca.setIsbn(result.getString(colunaISBN));
                    livroBiblioteca.setAno(result.getString(colunaAno));
                    livroBiblioteca.setEdicao(result.getString(colunaEdicao));
                    livroBiblioteca.setEditora(result.getString(colunaEditora));
                    livroBiblioteca.setAutor(result.getString(colunaAutor));
                    livroBiblioteca.setQuantidade(result.getInt(colunaQuantidade));

                    livros.add(livroBiblioteca);
                } while (result.next());
            }

        } catch (SQLException sql) {

            Logger.getAnonymousLogger().log(Level.SEVERE, sql.getMessage());

        } finally {

            if (UtilObjeto.isReferencia(result)) {

                result.close();
            }

            if (UtilObjeto.isReferencia(stant)) {

                stant.close();
            }

            if (this.connection != null && !this.connection.isClosed()) {
                this.connection.close();
            }
        }

        return livros;
    }

    /**
     * Método que obtém os livros pelo autor.
     *
     * @param autor
     * @return List<LivroBiblioteca>
     * @throws NotFoundException
     * @throws SQLException
     */
    public List<LivroBiblioteca> getLivrosBibliotecaAutor(String autor) throws
            NotFoundException, SQLException {

        List<LivroBiblioteca> livros = null;

        createConnection();

        initData();

        StringBuilder sb = new StringBuilder();
        sb.append("SELECT ").
                append(colunaId).append(", ").
                append(colunaTitulo).append(", ").
                append(colunaISBN).append(",").
                append(colunaAno).append(", ").
                append(colunaEdicao).append(", ").
                append(colunaEditora).append(", ").
                append(colunaAutor).append(", ").
                append(colunaQuantidade).
                append(" FROM ").
                append(tabelaBiblioteca).
                append(" WHERE ").
                append("UPPER( ").
                append(colunaAutor).
                append(") ").
                append(" LIKE '%").
                append(autor.toUpperCase()).
                append("%'");

        PreparedStatement stant = null;

        ResultSet result = null;

        try {
            
            if (connection == null) {
                return new ArrayList<LivroBiblioteca>();
            }

            stant = this.connection.prepareStatement(sb.toString());

            result = stant.executeQuery();

            if (result.next()) {
                livros = new ArrayList<LivroBiblioteca>();
                do {
                    LivroBiblioteca livroBiblioteca = new LivroBiblioteca();

                    livroBiblioteca.setId(result.getBigDecimal(colunaId).longValue());
                    livroBiblioteca.setNome(result.getString(colunaTitulo));
                    livroBiblioteca.setIsbn(result.getString(colunaISBN));
                    livroBiblioteca.setAno(result.getString(colunaAno));
                    livroBiblioteca.setEdicao(result.getString(colunaEdicao));
                    livroBiblioteca.setEditora(result.getString(colunaEditora));
                    livroBiblioteca.setAutor(result.getString(colunaAutor));
                    livroBiblioteca.setQuantidade(result.getInt(colunaQuantidade));

                    livros.add(livroBiblioteca);
                } while (result.next());
            }

        } catch (SQLException sql) {

            Logger.getAnonymousLogger().log(Level.SEVERE, sql.getMessage());

        } finally {

            if (UtilObjeto.isReferencia(result)) {

                result.close();
            }

            if (UtilObjeto.isReferencia(stant)) {

                stant.close();
            }
            if (this.connection != null && !this.connection.isClosed()) {
                this.connection.close();
            }
        }

        return livros;
    }
}
