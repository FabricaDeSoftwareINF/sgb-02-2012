package br.ufg.inf.es.integracao;

import br.ufg.inf.es.base.validation.ValidationException;
import br.ufg.inf.es.integracao.annotations.RNG002Livro;
import br.ufg.inf.es.model.Livro;
import br.ufg.inf.es.persistencia.LivroDAO;
import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

/**
 * Classe Service para a classe Livro.
 *
 * @author cezar
 */
@Component
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class LivroService extends GenericService<Livro> {

    /**
     * Campo dao
     */
    @Autowired
    private LivroDAO dao;

    /**
     * {@inheritDoc}
     */
    @Override
    public LivroDAO getDAO() {
        return this.dao;
    }

    /**
     * Método que define o DAO do Livro.
     *
     * @param dao
     */
    public void setDao(LivroDAO dao) {

        this.dao = dao;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @RNG002Livro
    public Long insert(Livro entity) throws ValidationException {
        Long id = 0l;
        try {
            String isbn10 = entity.getIsbn10();
            if (isbn10 != null && isbn10.isEmpty()) {
                entity.setIsbn10(null);
            }
            id = super.insert(entity);
        } catch (org.hibernate.exception.ConstraintViolationException e) {
            String message = messageToProperty(e.getCause().getMessage());
            throw new ValidationException(message);
        }
        return id;
    }

    /**
     * Método que realiza a formatação de uma string para uma chave do
     * properties.
     *
     * @param message
     * @return
     */
    protected String messageToProperty(String message) {
        String[] msg = message.split(" ");
        String prefix = msg[0].toLowerCase();
        String sufix = msg[msg.length - 1].replace("'", "").toLowerCase();
        return prefix.concat(".").concat(sufix);
    }

    /**
     * {@inheritDoc}
     */
    @RNG002Livro
    public void update(Livro entity) throws ValidationException {
        try {
            String isbn10 = entity.getIsbn10();
            if (isbn10 != null && isbn10.isEmpty()) {
                entity.setIsbn10(null);
            }
            super.update(entity);
        } catch (org.hibernate.exception.ConstraintViolationException e) {
            String message = messageToProperty(e.getCause().getMessage());
            throw new ValidationException(message);
        }
    }

    @Override
    public void removeAll(Collection<Livro> livros) throws ValidationException {
        try {
            super.removeAll(livros);
        } catch (org.hibernate.exception.ConstraintViolationException e) {
            throw new ValidationException("cadastro.livro.remocao.dependencia");
        }
    }
    
}