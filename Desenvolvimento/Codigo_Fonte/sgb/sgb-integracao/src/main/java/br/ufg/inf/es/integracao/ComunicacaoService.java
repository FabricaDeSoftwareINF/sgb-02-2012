package br.ufg.inf.es.integracao;

import br.ufg.inf.es.base.util.cripto.CriptoGeneric;
import br.ufg.inf.es.base.validation.ValidationException;
import br.ufg.inf.es.integracao.annotations.RNG001Parametros;
import br.ufg.inf.es.model.Comunicacao;
import br.ufg.inf.es.model.Usuario;
import br.ufg.inf.es.persistencia.ComunicacaoDAO;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.mail.HtmlEmail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

/**
 * Classe Service para a entidade Comunicacao
 *
 * @author igor
 */
@Component
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class ComunicacaoService extends GenericService<Comunicacao> {

    /**
     * Campo dao
     */
    @Autowired
    private ComunicacaoDAO dao;

    /**
     * {@inheritDoc}
     */
    @Override
    public ComunicacaoDAO getDAO() {

        return this.dao;
    }

    /**
     * Define um novo dao para o servico
     */
    public void setDAO(ComunicacaoDAO dao) {
        this.dao = dao;
    }

    /**
     * Método responsável por buscar os parâmetros de comunicação do sistema
     *
     * @author Igor
     * @return objeto de parâmetros de comunicação
     */
    public Comunicacao getComunicacao() {

        return this.getDAO().getComunicacao();

    }

    /**
     * Método responsável por realizar a inserção dos parâmetros de comunicação
     *
     * @author Igor
     * @param comunicacao instancia das classe dos parâmetros de comunicação
     * @return id da nova entidade
     * @throws ValidationException validação da instancia do objeto de
     * coinfiguração de comunicação
     */
    @Override
    @RNG001Parametros
    public Long insert(Comunicacao comunicacao) throws ValidationException {

        return this.getDAO().insert(comunicacao);
    }

    /**
     * Método reponsável por realizar a edição dos parâmetros de comunicação
     *
     * @author Igor
     * @param entidade instância da classe dos parâmetros de comunicação
     * @throws ValidationException validação da instancia do objeto de
     * coinfiguração de comunicação
     */
    @RNG001Parametros
    public void editar(Comunicacao entidade) throws ValidationException {

        this.getDAO().update(entidade);
    }
    
    public void enviarNovaSenha(Usuario usuario){
        HtmlEmail email = new HtmlEmail();
        Properties properties = new Properties();
        
        Comunicacao comunicacao = getDAO().getComunicacao();
        
        if (comunicacao != null) {
            try {
                //properties.load(EmailService.class.getResourceAsStream("/resources/email.properties"));

                String server = comunicacao.getService();//properties.getProperty("email.server");
                Boolean usarSsl = comunicacao.isSsl();//Boolean.valueOf(properties.getProperty("email.ssl"));
                Boolean usarTsl = comunicacao.isTsl();//Boolean.valueOf(properties.getProperty("email.tsl"));
                String porta = comunicacao.getPort();//properties.getProperty("email.port");
                String emailUsuario = comunicacao.getUsuario();//properties.getProperty("email.usuario");

                String senhaDecript = new String(new CriptoGeneric().decriptografa(comunicacao.getSenha()));

                String senha = senhaDecript;//properties.getProperty("email.senha");

                email.setAuthentication(emailUsuario, senha);
                email.setSmtpPort(Integer.parseInt(porta));
                email.setHostName(server);
                email.setSSL(usarSsl);
                email.setTLS(usarTsl);
                email.setSubject("Nova Senha");
                email.setFrom(usuario.getEmail());
                email.addTo(usuario.getEmail(), usuario.getNome());
                email.setMsg("Nova senha: " + usuario.getSenha());
                email.setDebug(true);
                email.send();

            } catch (Exception ex) {
                Logger.getAnonymousLogger().log(Level.SEVERE, ex.getLocalizedMessage());
            }
        }
    }
}
