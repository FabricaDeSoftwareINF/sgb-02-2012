package br.ufg.inf.es.integracao.importacaodados;

import br.ufg.inf.es.model.Bibliografia;
import br.ufg.inf.es.model.Curso;
import br.ufg.inf.es.model.Disciplina;
import br.ufg.inf.es.model.Livro;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.Collection;

/**
 *
 * @author vinicius
 */
public class ImportacaoLivro implements ImportacaoStrategy<Livro> {

    public Collection<Livro> importarDados(String url) {
        Conexao conexao = new Conexao();
        String resposta = conexao.httpGet(url, null);
        Collection<Livro> livros = livroParser(resposta);
        return livros;
    }

    private Collection<Livro> livroParser(String jsonCurso) {
        Gson gson = new Gson();
        Type type = new TypeToken<Collection<Livro>>() {}.getType();
        return gson.fromJson(jsonCurso, type);
    }

}
