package br.ufg.inf.es.web.bootstrap;

import br.ufg.inf.es.model.*;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 *
 * @author victor
 */
public class BibliografiaBootstrap {

    /**
     * Cria alguns dados para a verificação da lista de livros necessários para
     * a cotação
     *
     * @param sessionFactory Session factory inicializada no bootstrap
     * @see Bootstrap
     */
    public static void crieBibliografias(SessionFactory sessionFactory) {
        if (existeBibliografiaCadastrada(sessionFactory)) {
            return;
        }
        Curso curso = crieCurso(sessionFactory);

        Disciplina disciplina1 = crieDisciplina(sessionFactory, "int", "integracao", curso);
        Disciplina disciplina2 = crieDisciplina(sessionFactory, "int2", "integracao2", curso);

        Editora editora = crieEditora(sessionFactory);
        Autor autor = crieAutor(sessionFactory);

        Livro livro1 = crieLivro(sessionFactory, "12345671", editora, autor);
        Livro livro2 = crieLivro(sessionFactory, "12345672", editora, autor);
        Livro livro3 = crieLivro(sessionFactory, "12345673", editora, autor);
        Livro livro4 = crieLivro(sessionFactory, "12345674", editora, autor);

        Bibliografia bibliografia1 = obtenhaBibliografia(sessionFactory, disciplina1, livro1);
        Bibliografia bibliografia2 = obtenhaBibliografia(sessionFactory, disciplina1, livro2);
        Bibliografia bibliografia3 = obtenhaBibliografia(sessionFactory, disciplina2, livro3);
        Bibliografia bibliografia4 = obtenhaBibliografia(sessionFactory, disciplina2, livro4);

        List bibliografiasDisciplina1 = new ArrayList();
        bibliografiasDisciplina1.add(bibliografia1);
        bibliografiasDisciplina1.add(bibliografia2);
        disciplina1.setBibliografias(bibliografiasDisciplina1);
        salve(sessionFactory, disciplina1);

        List bibliografiasDisciplina2 = new ArrayList();
        bibliografiasDisciplina2.add(bibliografia3);
        bibliografiasDisciplina2.add(bibliografia4);
        disciplina1.setBibliografias(bibliografiasDisciplina2);
        salve(sessionFactory, disciplina2);
    }

    private static boolean existeBibliografiaCadastrada(SessionFactory sessionFactory) {
        Criteria criteria = sessionFactory.openSession().createCriteria(Bibliografia.class);
        List<Bibliografia> bibliografias = criteria.list();

        for (Bibliografia b : bibliografias) {
            if (b.getLivro().getIsbn11() == "12345678" || b.getLivro().getIsbn11() == "87654321") {
                return true;
            }
        }
        return false;
    }

    private static Autor crieAutor(SessionFactory sessionFactory) {
        Autor autor = new Autor();
        autor.setNome("Joao");
        autor.setSobrenome("Silva");
        salve(sessionFactory, autor);
        return autor;
    }

    private static Editora crieEditora(SessionFactory sessionFactory) {
        Editora editora = new Editora();
        editora.setNome("UFG");
        salve(sessionFactory, editora);
        return editora;
    }

    private static Bibliografia obtenhaBibliografia(SessionFactory sessionFactory, Disciplina disciplina, Livro livro) {
        Bibliografia bibliografia = new Bibliografia();
        bibliografia.setTipo("basica");
        bibliografia.setLivro(livro);
        bibliografia.setDisciplina(disciplina);
        salve(sessionFactory, bibliografia);

        return bibliografia;

    }

    private static Livro crieLivro(SessionFactory sessionFactory, String isbn,
            Editora editora, Autor autor) {
        Livro livro = new Livro();
        livro.setIsbn11(isbn);
        livro.setTitulo("Livro " + isbn);
        List<Autor> autores = new ArrayList<Autor>();
        autores.add(autor);
        livro.setAutores(autores);

        livro.setEdicao("primeira edicao");
        livro.setEditora(editora);

        salve(sessionFactory, livro);
        return livro;
    }

    private static Curso crieCurso(SessionFactory sessionFactory) {
        Curso curso = new Curso();
        curso.setNome("Engenharia de software");
        curso.setVagas(60);

        salve(sessionFactory, curso);
        return curso;
    }

    private static Disciplina crieDisciplina(SessionFactory sessionFactory,
            String codigo, String nome, Curso curso) {
        Disciplina disciplina = new Disciplina();
        disciplina.setCodigo(codigo);
        disciplina.setNome(nome);
        disciplina.setCurso(curso);

        salve(sessionFactory, disciplina);

        return disciplina;
    }

    private static void salve(SessionFactory sessionFactory, Object objeto) {
        Session session = sessionFactory.openSession();
        session.save(objeto);
        session.flush();
        session.close();
    }
}
