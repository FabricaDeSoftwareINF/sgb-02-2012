package br.ufg.inf.es.integracao.importacaodados;

import br.ufg.inf.es.integracao.AutorService;
import br.ufg.inf.es.integracao.CursoService;
import br.ufg.inf.es.integracao.DisciplinaService;
import br.ufg.inf.es.integracao.EditoraService;
import br.ufg.inf.es.integracao.LivroService;
import br.ufg.inf.es.model.Curso;
import br.ufg.inf.es.model.Disciplina;
import br.ufg.inf.es.model.Livro;
import br.ufg.inf.es.model.importacaobibliografia.BibliografiaImportada;
import br.ufg.inf.es.model.importacaobibliografia.CursoImportado;
import br.ufg.inf.es.model.importacaobibliografia.LivroImportado;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Vinícius
 */
public class ImportacaoDadosServiceImpl implements ImportacaoDadosService {

    private AutorService autorService;
    private CursoService cursoService;
    private DisciplinaService disciplinaService;
    private LivroService livroService;
    private EditoraService editoraService;
    private Client client;
    private String urlServico;
    private Gson jsonParser;

    public ImportacaoDadosServiceImpl() {
        Client client = Client.create();
        jsonParser = new Gson();
    }

    private String httpGet(Map<String, String> parametros) {
        String url = urlServico;
        if (parametros != null) {
            url += "?";
            for (String parametro : parametros.keySet()) {
                url += parametro + "=" + parametros.get(parametro) + "&";
            }
        }
        WebResource webResource = client
                .resource(urlServico);

        ClientResponse response = webResource.accept("application/json")
                .get(ClientResponse.class);

        if (response.getStatus() != 200) {
            throw new RuntimeException("Failed : HTTP error code : "
                    + response.getStatus());
        }
        String output = response.getEntity(String.class);
        return output;
    }

    public String getUrlServico() {
        return urlServico;
    }

    public void setUrlServico(String urlServico) {
        System.out.println("URL = " + urlServico);
        this.urlServico = urlServico;
    }

    public void importarBibliografia(Curso curso) {
        String bibliografiaJson = httpGet(null);
        Type bibliografiaType = new TypeToken<BibliografiaImportada>() {}.getType();
        BibliografiaImportada bibliografia =
                jsonParser.fromJson(bibliografiaJson, bibliografiaType);
        Collection<Disciplina> disciplinas = curso.getDisciplinas();
    }

    public void importarCurso(String nome) {
        String bibliografiaJson = httpGet(null);
        Type cursoType = new TypeToken<CursoImportado>() {
        }.getType();
        CursoImportado cursoImportado =
                jsonParser.fromJson(bibliografiaJson, cursoType);
        Curso curso = new Curso();
        curso.setNome(nome);
        curso.setVagas(cursoImportado.getVagas());
        cursoService.getDAO().insert(curso);
    }

    public void importarCursos() {
        String bibliografiaJson = httpGet(null);
        Type listaCursoType = new TypeToken<List<CursoImportado>>() {
        }.getType();
        List<CursoImportado> cursosImportado =
                jsonParser.fromJson(bibliografiaJson, listaCursoType);
        for (CursoImportado cursoImportado : cursosImportado) {
            Curso curso = new Curso();
            curso.setNome(cursoImportado.getNome());
            curso.setVagas(cursoImportado.getVagas());
            cursoService.getDAO().insert(curso);
        }
    }

    public void importarLivros(Disciplina Disciplina) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void importarLivros() {
        String livrosJson = httpGet(null);
        Type listaLivroType = new TypeToken<List<LivroImportado>>() {}.getType();
        List<LivroImportado> livrosImportado =
                jsonParser.fromJson(livrosJson, listaLivroType);
        for (LivroImportado livroImportado : livrosImportado) {
            Livro livro = new Livro();
            livro.setIsbn10(livroImportado.getIsbn10());
            livro.setIsbn13(livroImportado.getIsbn13());
            livro.setEdicao(livroImportado.getEdicao());
            livro.setTitulo(livroImportado.getTitulo());
            livroService.getDAO().insert(livro);
        }
    }

    public void importarEditoras() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void importarAutores() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public AutorService getAutorService() {
        return autorService;
    }

    public void setAutorService(AutorService autorService) {
        this.autorService = autorService;
    }

    public CursoService getCursoService() {
        return cursoService;
    }

    public void setCursoService(CursoService cursoService) {
        this.cursoService = cursoService;
    }

    public DisciplinaService getDisciplinaService() {
        return disciplinaService;
    }

    public void setDisciplinaService(DisciplinaService disciplinaService) {
        this.disciplinaService = disciplinaService;
    }

    public LivroService getLivroService() {
        return livroService;
    }

    public void setLivroService(LivroService livroService) {
        this.livroService = livroService;
    }

    public EditoraService getEditoraService() {
        return editoraService;
    }

    public void setEditoraService(EditoraService editoraService) {
        this.editoraService = editoraService;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Gson getJsonParser() {
        return jsonParser;
    }

    public void setJsonParser(Gson jsonParser) {
        this.jsonParser = jsonParser;
    }
    
}