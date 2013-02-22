package br.ufg.inf.es.web.bootstrap;

import br.ufg.inf.es.base.persistence.biblioteca.DBDriver;
import br.ufg.inf.es.enuns.EnumTipoBibliografia;
import br.ufg.inf.es.model.*;
import br.ufg.inf.es.model.biblioteca.DBBibliotecaConfig;

import java.nio.charset.Charset;
import java.util.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 * Insere alguns dados no banco para agilizar os testes de aceitacao
 *
 * @author victor
 */
public class BibliografiaBootstrap {

    private static final int VAGAS = 60;
    private static final long ANO = 2001;
    private static final int QUANTIDADE1 = 5;
    private static final int QUANTIDADE2 = 6;
    private static final double VALOR1 = 100;
    private static final double VALOR2 = 80;
    private static final double VALOR3 = 180;
    private static final double VALOR4 = 200;
    private static Livro livro1;
    private static Livro livro2;
    private static Livro livro3;
    private static Livro livro4;
    private static SessionFactory sessionFactory;
    private static Autor autor;
    private static Editora editora;
    private static Curso curso;
    private static Livraria livraria1;
    private static Livraria livraria2;
    private static ListaCotacao listaCotacao;
    private static List<CotacoesLivro> listaCotacoesLivro;
    private static DBBibliotecaConfig dbBibliotecaConfig;
    
    private BibliografiaBootstrap(){
    	
    }

    /**
     * Cria alguns dados para a verificação da lista de livros necessários para
     * a cotação
     *
     * @param sessionFactory Session factory inicializada no bootstrap
     * @see Bootstrap
     */
    public static void crieBibliografias(SessionFactory fabricaSessoes) {
        sessionFactory = fabricaSessoes;
        crieAutorEditoraECurso();

        Disciplina disciplina1 = crieDisciplina("int", "integracao");
        Disciplina disciplina2 = crieDisciplina("int2", "integracao2");

        livro1 = crieLivro("1402044062", "9781402044069");
        livro2 = crieLivro("1118508734", "9781118508732");
        livro3 = crieLivro("1118097599", "9781118097595");
        livro4 = crieLivro("1118130812", "9781118130810");

        crieBibliografia(disciplina1, livro1);
        crieBibliografia(disciplina1, livro2);
        crieBibliografia(disciplina2, livro3);
        crieBibliografia(disciplina2, livro4);

        crieListaCotacao();
        crieConfiguracaoBibliotecaBD();
    }

    private static void crieListaCotacao() {

        listaCotacao = new ListaCotacao();
        listaCotacao.setNome("Lista de Cotações 1");
        listaCotacao.setDataRealizada(Calendar.getInstance().getTime());
        listaCotacao.setCotacoesLivro(listaCotacoesLivro);
        crieCotacoesLivro();
        listaCotacao.setId(salve(listaCotacao));

    }
    
    private static void crieConfiguracaoBibliotecaBD() {

        dbBibliotecaConfig = new DBBibliotecaConfig();
        dbBibliotecaConfig.setCampoAnoLivro("ANO");
        dbBibliotecaConfig.setCampoAutor("AUTOR");
        dbBibliotecaConfig.setCampoEdicao("EDICAO");
        dbBibliotecaConfig.setCampoEditora("EDITORA");
        dbBibliotecaConfig.setCampoIdLivroBiblioteca("CODIGO");
        dbBibliotecaConfig.setCampoIsbnLivro("ISBN");
        dbBibliotecaConfig.setCampoQuantidadeLivro("CODIGO");
        dbBibliotecaConfig.setCampoTituloLivro("TITULO");
        dbBibliotecaConfig.setDriver(DBDriver.MySQL);
        dbBibliotecaConfig.setNameDataBase("obras");
        dbBibliotecaConfig.setPasswordDataBase("12345678".getBytes(Charset.forName("UTF-8")));
        dbBibliotecaConfig.setPorta("3306");
        dbBibliotecaConfig.setUrl("localhost");
        dbBibliotecaConfig.setUserDataBase("root");
        salve(dbBibliotecaConfig);

    }

    private static void crieCotacoesLivro() {

        listaCotacoesLivro = new ArrayList<CotacoesLivro>();
        crieLivrarias();

        CotacoesLivro cotacaoLivro1 = new CotacoesLivro();
        cotacaoLivro1.setLivro(livro1);
        cotacaoLivro1.setQuantidade(QUANTIDADE1);
        cotacaoLivro1.setValorMedio((VALOR1 + VALOR2) / 2d);
        List<Cotacao> cotacoes1 = new ArrayList<Cotacao>();
        Cotacao cotacao1 = new Cotacao();
        cotacao1.setLivraria(livraria1);
        cotacao1.setValor(VALOR1);
        cotacao1.setId(salve(cotacao1));
        cotacoes1.add(cotacao1);
        Cotacao cotacao2 = new Cotacao();
        cotacao2.setLivraria(livraria2);
        cotacao2.setValor(VALOR2);
        cotacao2.setId(salve(cotacao2));
        cotacoes1.add(cotacao2);
        cotacaoLivro1.setCotacoes(cotacoes1);
        cotacaoLivro1.setId(salve(cotacaoLivro1));

        CotacoesLivro cotacaoLivro2 = new CotacoesLivro();
        cotacaoLivro2.setLivro(livro2);
        cotacaoLivro2.setQuantidade(QUANTIDADE2);
        cotacaoLivro2.setValorMedio((VALOR3 + VALOR4) / 2d);
        List<Cotacao> cotacoes2 = new ArrayList<Cotacao>();
        Cotacao cotacao3 = new Cotacao();
        cotacao3.setLivraria(livraria1);
        cotacao3.setValor(VALOR3);
        cotacao3.setId(salve(cotacao3));
        cotacoes2.add(cotacao3);
        Cotacao cotacao4 = new Cotacao();
        cotacao4.setLivraria(livraria2);
        cotacao4.setValor(VALOR4);
        cotacao4.setId(salve(cotacao4));
        cotacoes2.add(cotacao4);
        cotacaoLivro2.setCotacoes(cotacoes2);
        cotacaoLivro2.setId(salve(cotacaoLivro2));

        listaCotacoesLivro.add(cotacaoLivro1);
        listaCotacoesLivro.add(cotacaoLivro2);

    }

    private static void crieAutorEditoraECurso() {
        autor = new Autor();
        autor.setNome("Joao");
        autor.setSobrenome("Silva");
        autor.setId(salve(autor));

        editora = new Editora();
        editora.setNome("UFG");
        editora.setId(salve(editora));

        curso = new Curso();
        curso.setNome("Engenharia de software");
        curso.setVagas(BibliografiaBootstrap.VAGAS);
        curso.setId(salve(curso));
    }

    private static void crieLivrarias() {
        livraria1 = new Livraria();
        livraria1.setNome("Siciliano");
        livraria1.setSite("www.siciliano.com.br");
        livraria1.setId(salve(livraria1));

        livraria2 = new Livraria();
        livraria2.setNome("Cultura");
        livraria2.setSite("www.cultura.com.br");
        livraria2.setId(salve(livraria2));
    }

    private static Bibliografia crieBibliografia(Disciplina disciplina, Livro livro) {
        Bibliografia bibliografia = new Bibliografia();
        bibliografia.setTipo(EnumTipoBibliografia.BASICA);
        bibliografia.setLivro(livro);
        bibliografia.setDisciplina(disciplina);
        bibliografia.setId(salve(bibliografia));

        Collection<Bibliografia> bibliografias = disciplina.getBibliografias();
        if (bibliografias != null && !bibliografias.isEmpty()) {
            bibliografias.add(bibliografia);
        }
        disciplina.setBibliografias(bibliografias);
        update(disciplina);

        return bibliografia;
    }

    private static Livro crieLivro(String isbn10, String isbn13) {
        Livro livro = new Livro();
        livro.setIsbn10(isbn10);
        livro.setIsbn13(isbn13);
        livro.setTitulo("Livro " + isbn10);
        livro.setAutores(Arrays.asList(autor));
        livro.setEdicao("primeira edicao");
        livro.setEditora(editora);
        livro.setAno(BibliografiaBootstrap.ANO);
        livro.setId(salve(livro));
        return livro;
    }

    private static Disciplina crieDisciplina(String codigo, String nome) {
        Disciplina disciplina = new Disciplina();
        disciplina.setCodigo(codigo);
        disciplina.setNome(nome);
        disciplina.setCurso(curso);
        disciplina.setId(salve(disciplina));

        curso.setDisciplinas(Arrays.asList(disciplina));
        update(curso);

        return disciplina;
    }

    private static Long salve(Object entidade) {
        Session session = getSession();
        Long id = (Long) session.save(entidade);
        session.flush();
        session.close();
        return id;
    }

    private static void update(Object entidade) {
        Session session = getSession();
        session.merge(entidade);
        session.flush();
        session.close();
    }

    private static Session getSession() {
        return sessionFactory.openSession();
    }
}
