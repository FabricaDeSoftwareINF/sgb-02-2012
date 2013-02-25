package br.ufg.inf.es.integracao.importacaodados;

import br.ufg.inf.es.base.validation.ValidationException;
import br.ufg.inf.es.model.Autor;
import br.ufg.inf.es.model.Editora;
import br.ufg.inf.es.model.Livro;
import br.ufg.inf.es.persistencia.*;
import java.util.ArrayList;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import java.util.Collection;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Vinícius
 */
@Component
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class ImportacaoLivrosServiceImpl extends ImportacaoDadosService<Livro> {

    private ImportacaoStrategy<Livro> importacaoStrategy;
    @Autowired
    private LivroDAO dao;
    @Autowired
    private EditoraDAO editoraDao;
    @Autowired
    private AutorDAO autorDao;

    public Collection<Livro> importar(String urlServico) {
        return importacaoStrategy.importarDados(urlServico);
    }

    public void setImportacaoStrategy(ImportacaoStrategy<Livro> strategy) {
        this.importacaoStrategy = strategy;
    }

    @Override
    public LivroDAO getDAO() {
        return dao;
    }

    public void salvarLivro(Livro livro) throws ValidationException {
        try {
            Collection<Livro> livros = this.dao.buscaLivroPorTitulo(livro.getTitulo());
            if (livros != null && livros.isEmpty()) {
                List<Editora> editoras = new ArrayList<Editora>(editoraDao.search(livro.getEditora()));
                if (editoras.contains(livro.getEditora())) {
                    livro.setEditora(editoras.get(0));
                } else {
                    this.editoraDao.save(livro.getEditora());
                }
                for (Autor autor : livro.getAutores()) {
                    List<Autor> autores = new ArrayList<Autor>(autorDao.search(autor));
                    if (autores == null || autores.isEmpty()) {
                        autorDao.insert(autor);
                    } else {
                        livro.getAutores().remove(autor);
                        livro.getAutores().add(autores.get(0));
                    }
                }
                dao.save(livro);
            }
        } catch (Exception e) {
            throw new ValidationException("Não foi possível salvar o livro");
        }
    }
}
