package br.ufg.inf.es.integracao.importacaodados;

import br.ufg.inf.es.enuns.EnumTipoBibliografia;
import br.ufg.inf.es.model.Autor;
import br.ufg.inf.es.model.Bibliografia;
import br.ufg.inf.es.model.Curso;
import br.ufg.inf.es.model.Disciplina;
import br.ufg.inf.es.model.Livro;
import br.ufg.inf.es.model.importacaobibliografia.BibliografiaImportada;
import br.ufg.inf.es.model.importacaobibliografia.CursoImportado;
import br.ufg.inf.es.model.importacaobibliografia.DisciplinaImportada;
import br.ufg.inf.es.model.importacaobibliografia.LivroImportado;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.http.HttpException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import br.ufg.inf.es.persistencia.*;

/**
 *
 * @author Vinícius
 */
@Component
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class ImportacaoDadosServiceImpl implements ImportacaoDadosService {
   
    @Autowired
    private AutorDAO autorDao;
    @Autowired
    private CursoDAO cursoDao;
    @Autowired
    private DisciplinaDAO disciplinaDao;
    @Autowired
    private LivroDAO livroDao;
    @Autowired
    private EditoraDAO editoraDao;
    
    private Client client;
    private String urlServico;
    private Gson jsonParser;
    private static final int OK_STATUS = 200;
    
    public ImportacaoDadosServiceImpl() {
        client = Client.create();
        jsonParser = new Gson();
    }

//    private String httpGet(Map<String, String> parametros) {
//        String url = urlServico;
//        if (parametros != null) {
//            url += "?";
//            for (String parametro : parametros.keySet()) {
//                url += parametro + "=" + parametros.get(parametro) + "&";
//            }
//        }
//        WebResource webResource = client
//                .resource(urlServico);
//
//        ClientResponse response = webResource.accept("application/json")
//                .get(ClientResponse.class);
//
//        if (response.getStatus() != 200) {
//            throw new RuntimeException("Failed : HTTP error code : "
//                    + response.getStatus());
//        }
//        String output = response.getEntity(String.class);
//        return output;
//    }
    private String httpGet(String parametro) throws HttpException {
        StringBuilder urlParametro = new StringBuilder(this.urlServico);
        
        WebResource webResource = webResource = client
                .resource(urlParametro.toString());
        if (parametro != null && (!parametro.isEmpty())) {
            if (urlServico.substring(urlServico.length() - 1, urlServico.length()).equals("/")) {
                webResource = client
                        .resource(urlParametro.append(parametro).toString());
            } else {
                webResource = client
                        .resource(urlParametro.append("/").append(parametro).toString());
            }
        }
        ClientResponse response = webResource.accept("application/json")
                .get(ClientResponse.class);
        
        if (response.getStatus() != ImportacaoDadosServiceImpl.OK_STATUS) {
            
            throw new HttpException("Failed : HTTP error code : "
                    + response.getStatus());
        }
        return response.getEntity(String.class);
    }
    
    public String getUrlServico() {
        return urlServico;
    }
    
    public void setUrlServico(String urlServico) {
        
        this.urlServico = urlServico;
    }
    
    public void importarCurso(Curso curso) {
        try {
            
            String bibliografiaJson = httpGet(curso.getNome());
            Type cursoType = new TypeToken<CursoImportado>() {
            }.getType();
            CursoImportado cursoImportado =
                    jsonParser.fromJson(bibliografiaJson, cursoType);
            Curso cursoImporta = getCursoModel(cursoImportado);

            //TODO implementa Diff dos cursos.
        } catch (HttpException he) {
            
            Logger.getAnonymousLogger().log(Level.SEVERE, he.getMessage());
        }
    }
    
    public void importarCurso(String nome) {
        try {
            String bibliografiaJson = httpGet(nome);
            
            Type cursoType = new TypeToken<CursoImportado>() {
            }.getType();
            CursoImportado cursoImportado =
                    jsonParser.fromJson(bibliografiaJson, cursoType);
            Curso curso = getCursoModel(cursoImportado);
            cursoDao.insert(curso);
        } catch (HttpException ex) {http://localhost:4567/cursos/cursos
            Logger.getLogger(ImportacaoDadosServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void importarCursos() {
        try {
            String bibliografiaJson = httpGet(null);
            
            Type cursosType = new TypeToken<Collection<CursoImportado>>() {
            }.getType();
            Collection<CursoImportado> listCursoImportado =
                    jsonParser.fromJson(bibliografiaJson, cursosType);
            for (CursoImportado cursoImportado : listCursoImportado) {
                Curso curso = getCursoModel(cursoImportado);
                cursoDao.insert(curso);
            }
        } catch (HttpException ex) {
            Logger.getLogger(ImportacaoDadosServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void importarDisciplinas(Disciplina disciplina) {
        try {
            String bibliografiaJson = httpGet(disciplina.getNome());
            Type listaDisciplinaType = new TypeToken<List<DisciplinaImportada>>() {
            }.getType();
            List<DisciplinaImportada> disciplinaImportadas =
                    jsonParser.fromJson(bibliografiaJson, listaDisciplinaType);
            for (DisciplinaImportada disciplinaImportada : disciplinaImportadas) {
                Disciplina disciplinaModel = new Disciplina();
                disciplinaModel.setNome(disciplinaImportada.getNome());
                disciplinaModel.setCodigo(disciplinaImportada.getCodigo());
                disciplinaModel.setBibliografias(getBibliografiaModel(disciplinaImportada.getBibliografia()));
                disciplinaDao.insert(disciplinaModel);
            }
        } catch (HttpException ex) {
            Logger.getLogger(ImportacaoDadosServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     *
     */
    public void importarDisciplinas() {
        try {
            String bibliografiaJson = httpGet(null);
            Type listaDisciplinaType = new TypeToken<List<DisciplinaImportada>>() {
            }.getType();
            List<DisciplinaImportada> disciplinaImportadas =
                    jsonParser.fromJson(bibliografiaJson, listaDisciplinaType);
            for (DisciplinaImportada disciplinaImportada : disciplinaImportadas) {
                Disciplina disciplinaModel = new Disciplina();
                disciplinaModel.setNome(disciplinaImportada.getNome());
                disciplinaModel.setCodigo(disciplinaImportada.getCodigo());
                disciplinaModel.setBibliografias(getBibliografiaModel(disciplinaImportada.getBibliografia()));
                disciplinaDao.insert(disciplinaModel);
            }
        } catch (HttpException ex) {
            Logger.getLogger(ImportacaoDadosServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void importarLivros(Livro livro) {
        try {
            String bibliografiaJson = httpGet(livro.getTitulo());
            Type listaLivroType = new TypeToken<List<LivroImportado>>() {
            }.getType();
            List<LivroImportado> livroImportados =
                    jsonParser.fromJson(bibliografiaJson, listaLivroType);
            for (LivroImportado livroImportado : livroImportados) {
                Livro livroModel = getlivroModel(livroImportado);
                livroDao.insert(livroModel);
            }
            
            
        } catch (HttpException ex) {
            Logger.getLogger(ImportacaoDadosServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void importarLivros() {
        try {
            String bibliografiaJson = httpGet(null);
            Type listaLivroType = new TypeToken<List<LivroImportado>>() {
            }.getType();
            List<LivroImportado> livroImportados =
                    jsonParser.fromJson(bibliografiaJson, listaLivroType);
            for (LivroImportado livroImportado : livroImportados) {
                Livro livroModel = getlivroModel(livroImportado);
                livroDao.insert(livroModel);
            }
        } catch (HttpException ex) {
            Logger.getLogger(ImportacaoDadosServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public AutorDAO getAutorDAO() {
        return autorDao;
    }
    
    public void setAutorDAO(AutorDAO autorDAO) {
        this.autorDao = autorDAO;
    }
    
    public CursoDAO getCursoDAO() {
        return cursoDao;
    }
    
    public void setCursoDAO(CursoDAO cursoDAO) {
        this.cursoDao = cursoDAO;
    }
    
    public DisciplinaDAO getDisciplinaDAO() {
        return disciplinaDao;
    }
    
    public void setDisciplinaDAO(DisciplinaDAO disciplinaDAO) {
        this.disciplinaDao = disciplinaDAO;
    }
    
    public LivroDAO getLivroDAO() {
        return livroDao;
    }
    
    public void setLivroDAO(LivroDAO livroDAO) {
        this.livroDao = livroDAO;
    }
    
    public EditoraDAO getEditoraDAO() {
        return editoraDao;
    }
    
    public void setEditoraDAO(EditoraDAO editoraDAO) {
        this.editoraDao = editoraDAO;
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
    
    private Curso getCursoModel(CursoImportado cursoImportado) {
        Curso curso = new Curso();
        curso.setNome(cursoImportado.getNome());
        curso.setVagas(cursoImportado.getVagas());
        curso.setDisciplinas(getDisciplinasModel(cursoImportado.getDisciplinas()));
        
        return curso;
    }
    
    private Collection<Curso> getCursosModel(Collection<CursoImportado> cursoImportados) {
        Collection<Curso> cursos = new ArrayList<Curso>();
        
        if (cursoImportados != null && (!cursoImportados.isEmpty())) {
            
            for (CursoImportado cursoImportado : cursoImportados) {
                Curso curso = getCursoModel(cursoImportado);
                cursos.add(curso);
            }
        }
        
        return cursos;
    }
    
    private Collection<Disciplina> getDisciplinasModel(Collection<DisciplinaImportada> disciplinaImportadas) {
        Collection<Disciplina> disciplinas = new ArrayList<Disciplina>();
        
        for (DisciplinaImportada disciplinaImportada : disciplinaImportadas) {
            Disciplina disciplina = new Disciplina();
            disciplina.setCodigo(disciplinaImportada.getCodigo());
            disciplina.setNome(disciplinaImportada.getNome());
            
            if (disciplinaImportada.getBibliografia() != null) {
                disciplina.setBibliografias(
                        getBibliografiaModel(disciplinaImportada.getBibliografia()));
            }
            disciplinas.add(disciplina);
        }
        
        return disciplinas;
    }
    
    private Collection<Bibliografia> getBibliografiaModel(BibliografiaImportada bibliografiaImportada) {
        Collection<Bibliografia> bibliografias = new ArrayList<Bibliografia>();
        
        if (bibliografiaImportada.getBasica() != null
                && (!bibliografiaImportada.getBasica().isEmpty())) {
            EnumTipoBibliografia basica = EnumTipoBibliografia.BASICA;
            for (Livro livro : getLivrosModel(bibliografiaImportada.getBasica())) {
                Bibliografia bibliografia = new Bibliografia();
                bibliografia.setTipo(basica);                
                bibliografia.setLivro(livro);                
                bibliografias.add(bibliografia);
            }
        }
        
        if (bibliografiaImportada.getComplementar() != null
                && (!bibliografiaImportada.getComplementar().isEmpty())) {
            EnumTipoBibliografia complementar = EnumTipoBibliografia.COMPLEMENTAR;
            for (Livro livro : getLivrosModel(bibliografiaImportada.getComplementar())) {
                Bibliografia bibliografia = new Bibliografia();
                bibliografia.setTipo(complementar);
                bibliografia.setLivro(livro);
                
                bibliografias.add(bibliografia);
            }
        }
        
        if (bibliografiaImportada.getSugerida() != null
                && (!bibliografiaImportada.getSugerida().isEmpty())) {
            EnumTipoBibliografia sugerida = EnumTipoBibliografia.SUGERIDA;
            for (Livro livro : getLivrosModel(bibliografiaImportada.getSugerida())) {
                Bibliografia bibliografia = new Bibliografia();
                bibliografia.setTipo(sugerida);
                bibliografia.setLivro(livro);
                
                bibliografias.add(bibliografia);
            }
        }
        
        return bibliografias;
    }
    
    private Collection<Livro> getLivrosModel(List<LivroImportado> livroImportados) {
        Collection<Livro> livros = null;
        
        if (livroImportados != null && (!livroImportados.isEmpty())) {
            livros = new ArrayList<Livro>();
            
            for (LivroImportado livroImportado : livroImportados) {
                Livro livro = getlivroModel(livroImportado);
                livros.add(livro);
            }
        }
        
        return livros;
    }
    
    private Livro getlivroModel(LivroImportado livroImportado) {
        Livro livro = new Livro();
        
        if (livroImportado != null) {
            livro.setIsbn10(livroImportado.getIsbn10());
            livro.setIsbn13(livroImportado.getIsbn13());
            livro.setEdicao(livroImportado.getEdicao());
            livro.setTitulo(livroImportado.getTitulo());
            
            if (livroImportado.getAutor() != null
                    && (!livroImportado.getAutor().equals(""))) {
                Autor autor = new Autor();
                autor.setNome(livroImportado.getAutor());
                
                Collection<Autor> autores = new ArrayList<Autor>();
                autores.add(autor);
                
                livro.setAutores(autores);
            }
        }
        return livro;
    }
    
    private Collection<Disciplina> getDisciplinaDiff(Collection<Disciplina> disciplinas,
            Collection<DisciplinaImportada> disciplinaImportadas) {

        //TODO implementar o verificador de diferenças entre as listas
        return null;
    }
    
    private Collection<Bibliografia> getBibliografiaDiff(Collection<Bibliografia> bibliografias,
            BibliografiaImportada bibliografiaImportada) {

        //TODO implementar o verificador de diferenças entre as listas    
        return null;
    }
}
