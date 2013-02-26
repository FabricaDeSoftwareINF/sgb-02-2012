package br.ufg.inf.es.web.controller;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
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

import br.ufg.inf.es.base.persistence.biblioteca.DBDriver;
import br.ufg.inf.es.base.util.cripto.CriptoGeneric;
import br.ufg.inf.es.base.validation.ValidationException;
import br.ufg.inf.es.integracao.DBBibliotecaConfigService;
import br.ufg.inf.es.model.biblioteca.DBBibliotecaConfig;
import br.ufg.inf.es.model.biblioteca.LivroBiblioteca;
import br.ufg.inf.es.persistencia.biblioteca.LivrosBibliotecaDAO;
import br.ufg.inf.es.web.controller.form.BibliotecaConfigForm;
import java.nio.charset.Charset;

/**
 * Controlador da página de parâmetros de Integração com a Biblioteca
 *
 * @author igor
 */
@Component
@Scope("session")
public class BibliotecaConfigController extends SGBController<DBBibliotecaConfig, 
        BibliotecaConfigForm, DBBibliotecaConfigService> {

    @Autowired
    private BibliotecaConfigForm form;
    
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
            form.setDriver(config.getDriver());
            form.setEntity(config);

        } else {
            form.setDriver(DBDriver.Oracle);
        }

        if (this.form.getEntity().getPasswordDataBase() != null) {
            byte[] pass = new CriptoGeneric().decriptografa(
                    this.form.getEntity().getPasswordDataBase());
            if (pass != null) {  
                this.password = new String(pass, Charset.forName("UTF-8"));
            }
        }
        return "BibliotecaConfigController/initialPage";
    }

    public void setForm(BibliotecaConfigForm form) {
        this.form = form;
    }

    @Override
    public BibliotecaConfigForm getForm() {
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
        this.form = new BibliotecaConfigForm();
    }

    public String salvarDBBibliotecaConfig() {

        try {
            if (this.service.getBibliotecaCfg() == null || service.getBibliotecaCfg().getId() == 0l) {
                this.form.getEntity().setDriver(this.form.getDriver());
                this.form.getEntity().setPasswordDataBase(new CriptoGeneric().criptografa(password));
                this.service.insert(this.form.getEntity());
                this.addSuccessMessage("arquitetura.msg.sucesso");
            } else {
                this.form.getEntity().setDriver(this.form.getDriver());
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

            String livros = "<Não foi encontrado nenhum registro>";

            if (livrosBiblioteca != null) {
                livros = "";
                
                StringBuilder livroStrBuilder = new StringBuilder();
                
                for (LivroBiblioteca livroBc : livrosBiblioteca) {
                    
                    livroStrBuilder.append("Livro: ");
                    
                    livroStrBuilder.append(livroBc.getNome());
                    
                    livroStrBuilder.append(", ISBN: ");
                    
                    livroStrBuilder.append(livroBc.getIsbn());
                    
                    livroStrBuilder.append(", Qtda.: ");
                    
                    livroStrBuilder.append(livroBc.getQuantidade());
                    
                    livroStrBuilder.append("\n");
                }
                livros = livroStrBuilder.toString();
            }
            
            //Grava o objeto cliente no arquivo
            OutputStreamWriter out = new OutputStreamWriter (new FileOutputStream ("livros.txt"), "UTF-8");
            out.write(livros);
            PrintWriter pw = new PrintWriter (out); 
            out.flush();
            out.close();
            pw.flush();
            pw.close();

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
