package br.ufg.inf.es.web.bootstrap;

import br.ufg.inf.es.enuns.EnumTipoBibliografia;
import br.ufg.inf.es.model.*;
import java.util.Arrays;
import java.util.Collection;
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
    
    private static SessionFactory sessionFactory;
    private static Autor autor;
    private static Editora editora;
    private static Curso curso;

    /**
     * Cria alguns dados para a verificação da lista de livros necessários para
     * a cotação
     *
     * @param sessionFactory Session factory inicializada no bootstrap
     * @see Bootstrap
     */
    public static void crieBibliografias(SessionFactory _sessionFactory) {
        sessionFactory = _sessionFactory;
        crieAutorEditoraECurso();

        Disciplina disciplina1 = crieDisciplina("int", "integracao");
        Disciplina disciplina2 = crieDisciplina("int2", "integracao2");

        Livro livro1 = crieLivro("1402044062", "9781402044069");
        Livro livro2 = crieLivro("1118508734", "9781118508732");
        Livro livro3 = crieLivro("1118097599", "9781118097595");
        Livro livro4 = crieLivro("1118130812", "9781118130810");

        crieBibliografia(disciplina1, livro1);
        crieBibliografia(disciplina1, livro2);
        crieBibliografia(disciplina2, livro3);
        crieBibliografia(disciplina2, livro4);
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
