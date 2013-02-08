package br.ufg.inf.es.persistencia.biblioteca;

import br.ufg.inf.es.base.util.cripto.CriptoGeneric;
import br.ufg.inf.es.base.validation.ValidationException;
import br.ufg.inf.es.model.biblioteca.DBBibliotecaConfig;
import br.ufg.inf.es.model.biblioteca.LivroBiblioteca;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.jsf.FacesContextUtils;

/**
 *
 * @author igor
 */
@Repository
@Transactional(rollbackFor = ValidationException.class)
public class LivrosBibliotecaDAO {

    private Connection connection;
    private DBBibliotecaConfig dbConfig;
    @Autowired
    private DBBibliotecaConfigDAO bBibliotecaConfigDAO;

    private void createConnection() throws NotFoundException, SQLException {

        this.dbConfig = bBibliotecaConfigDAO.getBibliotecaCfg();

        if (dbConfig == null) {
            throw new NotFoundException("Instância da classe DBBibliotecaConfig "
                    + "passada como parâmetro não instanciada.");

        }

        String pass = new String(new CriptoGeneric().decriptografa(dbConfig.getPasswordDataBase()));

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

    public List<LivroBiblioteca> getLivrosBibliotecaTitulo(String titulo) throws
            NotFoundException, SQLException {
        List<LivroBiblioteca> livros = null;

        createConnection();
        String tabelaBiblioteca = this.dbConfig.getTabela().toUpperCase();
        
        String colunaId = this.dbConfig.getCampoIdLivroBiblioteca().toUpperCase();
        String colunaTitulo = this.dbConfig.getCampoTituloLivro().toUpperCase();
        String colunaISBN = this.dbConfig.getCampoIsbnLivro().toUpperCase();
        String colunaAno = this.dbConfig.getCampoAnoLivro().toUpperCase();
        String colunaEdicao = this.dbConfig.getCampoEdicao().toUpperCase();
        String colunaEditora = this.dbConfig.getCampoEditora().toUpperCase();
        String colunaAutor = this.dbConfig.getCampoAutor().toUpperCase();
        String colunaQuantidade = this.dbConfig.getCampoQuantidadeLivro().toUpperCase();

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
                append(colunaTitulo).append(" LIKE '%").append(titulo.toUpperCase()).
                    append("%'");

        PreparedStatement stant = this.connection.prepareStatement(sb.toString());

        ResultSet result = stant.executeQuery();
        if (result.next()) {
            livros = new ArrayList<LivroBiblioteca>();
            do {
                LivroBiblioteca livroBiblioteca = new LivroBiblioteca();
                
                livroBiblioteca.setId(result.getBigDecimal(colunaId).longValue());
                livroBiblioteca.setNome(result.getString(colunaTitulo));
                livroBiblioteca.setIsbn(result.getString(colunaISBN));
                livroBiblioteca.setAno(result.getString(colunaAno));
                livroBiblioteca.setEdicao(result.getInt(colunaEdicao));
                livroBiblioteca.setEditora(result.getString(colunaEditora));
                livroBiblioteca.setAutor(result.getString(colunaAutor));
                livroBiblioteca.setQuantidade(result.getInt(colunaQuantidade));
                
                livros.add(livroBiblioteca);
            } while (result.next());
        }

        result.close();
        stant.close();
        this.connection.close();

        return livros;
    }

    public List<LivroBiblioteca> getLivrosBibliotecaTitulo(String titulo, String isbn)
            throws NotFoundException, SQLException {
        List<LivroBiblioteca> livros = null;
        createConnection();
        String tabelaBiblioteca = this.dbConfig.getTabela().toUpperCase();
        
        String colunaId = this.dbConfig.getCampoIdLivroBiblioteca().toUpperCase();
        String colunaTitulo = this.dbConfig.getCampoTituloLivro().toUpperCase();
        String colunaISBN = this.dbConfig.getCampoIsbnLivro().toUpperCase();
        String colunaAno = this.dbConfig.getCampoAnoLivro().toUpperCase();
        String colunaEdicao = this.dbConfig.getCampoEdicao().toUpperCase();
        String colunaEditora = this.dbConfig.getCampoEditora().toUpperCase();
        String colunaAutor = this.dbConfig.getCampoAutor().toUpperCase();
        String colunaQuantidade = this.dbConfig.getCampoQuantidadeLivro().toUpperCase();
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
                append(colunaTitulo).append(" LIKE '%").
                append(titulo.toUpperCase()).append("%' AND ").
                append(colunaISBN).append(" = ").append(isbn);

        PreparedStatement stant = this.connection.prepareStatement(sb.toString());

        ResultSet result = stant.executeQuery();
        if (result.next()) {
            livros = new ArrayList<LivroBiblioteca>();
            do {
                LivroBiblioteca livroBiblioteca = new LivroBiblioteca();

                livroBiblioteca.setId(result.getBigDecimal(colunaId).longValue());
                livroBiblioteca.setNome(result.getString(colunaTitulo));
                livroBiblioteca.setIsbn(result.getString(colunaISBN));
                livroBiblioteca.setAno(result.getString(colunaAno));
                livroBiblioteca.setEdicao(result.getInt(colunaEdicao));
                livroBiblioteca.setEditora(result.getString(colunaEditora));
                livroBiblioteca.setAutor(result.getString(colunaAutor));
                livroBiblioteca.setQuantidade(result.getInt(colunaQuantidade));

                livros.add(livroBiblioteca);
            } while (result.next());
        }

        result.close();
        stant.close();
        this.connection.close();

        return livros;
    }

    public List<LivroBiblioteca> getLivroBibliotecaAll() throws NotFoundException,
            SQLException {
        List<LivroBiblioteca> livros = null;

        createConnection();
        String tabelaBiblioteca = this.dbConfig.getTabela().toUpperCase();
        
        String colunaId = this.dbConfig.getCampoIdLivroBiblioteca().toUpperCase();
        String colunaTitulo = this.dbConfig.getCampoTituloLivro().toUpperCase();
        String colunaISBN = this.dbConfig.getCampoIsbnLivro().toUpperCase();
        String colunaAno = this.dbConfig.getCampoAnoLivro().toUpperCase();
        String colunaEdicao = this.dbConfig.getCampoEdicao().toUpperCase();
        String colunaEditora = this.dbConfig.getCampoEditora().toUpperCase();
        String colunaAutor = this.dbConfig.getCampoAutor().toUpperCase();
        String colunaQuantidade = this.dbConfig.getCampoQuantidadeLivro().toUpperCase();
        
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

        PreparedStatement stant = this.connection.prepareStatement(sb.toString());

        ResultSet result = stant.executeQuery();
        if (result.next()) {
            livros = new ArrayList<LivroBiblioteca>();
            do {
                LivroBiblioteca livroBiblioteca = new LivroBiblioteca();

                livroBiblioteca.setId(result.getBigDecimal(colunaId).longValue());
                livroBiblioteca.setNome(result.getString(colunaTitulo));
                livroBiblioteca.setIsbn(result.getString(colunaISBN));
                livroBiblioteca.setAno(result.getString(colunaAno));
                livroBiblioteca.setEdicao(result.getInt(colunaEdicao));
                livroBiblioteca.setEditora(result.getString(colunaEditora));
                livroBiblioteca.setAutor(result.getString(colunaAutor));
                livroBiblioteca.setQuantidade(result.getInt(colunaQuantidade));
                
                livros.add(livroBiblioteca);
            } while (result.next());
        }

        result.close();
        stant.close();
        this.connection.close();
        
        return livros;
    }
}
