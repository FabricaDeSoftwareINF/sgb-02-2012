package br.ufg.inf.es.integracao.importacaodados;

import java.lang.reflect.Type;
import java.util.Collection;

import br.ufg.inf.es.model.Livro;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

/**
 *
 * @author vinicius
 */
public class ImportacaoLivro implements ImportacaoStrategy<Livro> {

    public Collection<Livro> importarDados(String url) {
        Conexao conexao = new Conexao();
        String resposta = conexao.httpGet(url, null);
        return livroParser(resposta);
    }

    private Collection<Livro> livroParser(String jsonCurso) {
        Gson gson = new Gson();
        Type type = new TypeToken<Collection<Livro>>() {}.getType();
        return gson.fromJson(jsonCurso, type);
    }

}
