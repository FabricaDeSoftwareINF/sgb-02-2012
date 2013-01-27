package br.ufg.inf.es.web.controller;

import java.io.Serializable;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Cézar Augusto Ferreira
 */
public class BaseController implements Serializable {

    public void addSuccessMessage(final String keyMessage) {

        this.showMessage(FacesMessage.SEVERITY_INFO, keyMessage);
    }

    public void addWarningMessage(final String keyMessage) {

        this.showMessage(FacesMessage.SEVERITY_WARN, keyMessage);
    }

    public void addErrorMessage(final String keyMessage) {

        this.showMessage(FacesMessage.SEVERITY_ERROR, keyMessage);
    }

    private void showMessage(final Severity severityMessage, final String keyMessage) {

        String message = keyMessage;

        if (this.getBundle().containsKey(keyMessage)) {

            message = this.getBundle().getString(keyMessage);
        }

        this.getFacesContext().addMessage("", new FacesMessage(severityMessage, message, ""));
    }

    public boolean hasMessage() {

        return this.getFacesContext().getMessages().hasNext();
    }

    public HttpServletRequest getRequest() {

        return ((HttpServletRequest) this.getFacesContext().getExternalContext().getRequest());
    }

    public HttpServletResponse getResponse() {

        return ((HttpServletResponse) this.getFacesContext().getExternalContext().getResponse());
    }

    public FacesContext getFacesContext() {

        return FacesContext.getCurrentInstance();
    }

    public <T> String getParameterFromRequest(final String parameter) {

        return this.getRequest().getParameter(parameter);
    }

    public <T> T getFromSessao(final String key, final Class<T> clazz) {

        try {

            return ((T) this.getFacesContext().getExternalContext().getSessionMap().get(key));

        } catch (final Exception e) {

            Logger.getLogger(this.getClass().getSimpleName()).log(Level.SEVERE, e.getMessage());
        }

        return null;
    }

    public ResourceBundle getBundle() {

        return ResourceBundle.getBundle("resources.messages");
    }
}