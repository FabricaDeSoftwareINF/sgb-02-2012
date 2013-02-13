package br.ufg.inf.es.web.controller;

import br.ufg.inf.es.base.util.cripto.CriptoGeneric;
import br.ufg.inf.es.base.validation.ValidationException;
import br.ufg.inf.es.integracao.DBBibliotecaConfigService;
import br.ufg.inf.es.model.biblioteca.DBBibliotecaConfig;
import br.ufg.inf.es.model.biblioteca.LivroBiblioteca;
import br.ufg.inf.es.persistencia.biblioteca.LivrosBibliotecaDAO;
import br.ufg.inf.es.web.controller.form.DBBibliotecaConfigForm;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.nio.charset.Charset;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javassist.NotFoundException;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Controlador da página de parâmetros de Integração com a Biblioteca
 *
 * @author igor
 */
@Component
@Scope("session")
public class BibliotecaConfigController extends SGBController<DBBibliotecaConfig, DBBibliotecaConfigForm, DBBibliotecaConfigService> {

    @Autowired
    private DBBibliotecaConfigForm form;
    
    @Autowired
    private DBBibliotecaConfigService service;
    
    private StreamedContent file;
    
    private String tituloTeste;
    
    @Autowired
    private LivrosBibliotecaDAO livroDAO;
    
    private String password;

    @Override
    public String openInitialPage() {
        DBBibliotecaConfig config = service.getBibliotecaCfg();
        if (config != null) {

            form.setEntity(config);

        }

        if (this.form.getEntity().getPasswordDataBase() != null) {
            this.password = new String(new CriptoGeneric().decriptografa(
                    this.form.getEntity().getPasswordDataBase()), Charset.forName("UTF-8"));
        }
        return "BibliotecaConfigController/initialPage";
    }

    public void setForm(DBBibliotecaConfigForm form) {
        this.form = form;
    }

    @Override
    public DBBibliotecaConfigForm getForm() {
        return form;
    }

    public void setService(DBBibliotecaConfigService service) {
        this.service = service;
    }

    @Override
    public DBBibliotecaConfigService getService() {
        return service;
    }

    public void limpar() {
        this.form = new DBBibliotecaConfigForm();
    }

    public String salvarDBBibliotecaConfig() {

        try {
            if (this.getForm().getEntity() == null
                    && this.getForm().getEntity().getId() == null
                    && this.getForm().getEntity().getId() == 0) {
                this.form.getEntity().setPasswordDataBase(new CriptoGeneric().criptografa(password));
                this.service.insert(this.form.getEntity());
                this.addSuccessMessage("arquitetura.msg.sucesso");
            } else {
                this.form.getEntity().setPasswordDataBase(new CriptoGeneric().criptografa(password));
                this.service.editar(this.form.getEntity());
                this.addSuccessMessage("arquitetura.msg.sucesso");
            }
        } catch (ValidationException ex) {

            this.addWarningMessage(ex.getKeyMessage());

        }
        return super.openSearchPage();

    }

    public String editarDBBibliotecaConfig() {

        super.edit();

        return super.openInitialPage();
    }

    public void prepararExclusao() {

        if (this.getForm().getEntity() != null
                && this.getForm().getEntity().getDriver() != null) {
            this.getForm().setExibirDialogExclusao(Boolean.TRUE);
        } else {
            this.getForm().setExibirDialogExclusao(Boolean.FALSE);
            this.addWarningMessage("arquitetura.msg.nenhumregistroencontrado");
        }
    }

    public void remover() {
        this.service.getDAO().remove(form.getEntity());
        this.addSuccessMessage("arquitetura.msg.sucesso");
    }

    public String voltar() {
        return "/index.jsf";

    }

    /**
     * Método que serializa as chaves e salva.
     */
    private void geraArquivo(List<LivroBiblioteca> livrosBiblioteca) {
        try {
            FileOutputStream arquivoGrav = new FileOutputStream("livros.txt");
            ObjectOutputStream objGravar = new ObjectOutputStream(arquivoGrav);

            String livros = "<Não foi encontrado nenhum registro>";

            if (livrosBiblioteca != null) {
                livros = "";
                
                StringBuilder livroStrBuilder = new StringBuilder();
                
                for (LivroBiblioteca livroBc : livrosBiblioteca) {
                    
                    livroStrBuilder.append(livros);
                    
                    livroStrBuilder.append("Livro: ");
                    
                    livroStrBuilder.append(livroBc.getNome());
                    
                    livroStrBuilder.append(", ISBN: ");
                    
                    livroStrBuilder.append(livroBc.getIsbn());
                    
                    livroStrBuilder.append(", Qtda.: ");
                    
                    livroStrBuilder.append(livroBc.getQuantidade());
                    
                    livroStrBuilder.append("\n");
                    
                    livros = livroStrBuilder.toString();
                }
            }

            //Grava o objeto cliente no arquivo
            objGravar.writeObject(livros);
            objGravar.flush();
            objGravar.close();
            arquivoGrav.flush();
            arquivoGrav.close();

        } catch (Exception e) {
            
            Logger.getAnonymousLogger().log(Level.SEVERE, e.getMessage());
        }
    }

    public String executaTeste() {
        if (this.form.getEntity() != null) {
            try {
                geraArquivo(livroDAO.getLivrosBibliotecaTitulo(getTituloTeste()));

                InputStream stream;
                try {
                    stream = new FileInputStream("livros.txt");
                    file = new DefaultStreamedContent(stream, "text/txt", "livros.txt");
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(BibliotecaConfigController.class.getName()).log(Level.SEVERE, null, ex);
                }
            } catch (NotFoundException ex) {
                this.addWarningMessage("arquitetura.msg.notfound");
            } catch (SQLException ex) {
                StringBuilder exception = new StringBuilder();
                exception.append("arquitetura.msg.connectionexception");
                exception.append("\n");
                exception.append(ex.getMessage());
                this.addWarningMessage("arquitetura.msg.connectionexception");
            }
        }
        return super.openSearchPage();
    }

    public String getTituloTeste() {
        return tituloTeste;
    }

    public void setTituloTeste(String tituloTeste) {
        this.tituloTeste = tituloTeste;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public StreamedContent getFile() {
        return file;
    }

    public void setFile(StreamedContent file) {
        this.file = file;
    }
}
