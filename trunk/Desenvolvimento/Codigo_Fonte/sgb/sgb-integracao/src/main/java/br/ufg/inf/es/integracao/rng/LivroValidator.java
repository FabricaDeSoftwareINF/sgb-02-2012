package br.ufg.inf.es.integracao.rng;

import br.ufg.inf.es.base.validation.Validation;
import br.ufg.inf.es.base.validation.ValidationException;
import br.ufg.inf.es.model.Livro;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import org.apache.commons.validator.routines.ISBNValidator;
import org.springframework.stereotype.Component;

/**
 *
 * @author vinicius
 */
@Component
public class LivroValidator implements Validation<Livro> {

    public static final String RNG002_TITULO = "label.RNG02.titulo";
    public static final String RNG002_ISBN10 = "label.RNG02.isbn10";
    public static final String RNG002_ISBN13 = "label.RNG02.isbn13";
    public static final String RNG002_EDICAO = "label.RNG02.edicao";
    public static final String RNG002_EDITORA = "label.RNG02.editora";
    public static final String RNG002_AUTOR = "label.RNG02.autor";
    public static final String RNG002_ANO = "label.RNG02.ano";
    
    private SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy");

    public void validate(Livro livro) throws ValidationException {
        try {
            Date date = dateFormatter.parse(livro.getAno().toString());
            if (date.after(new Date())) {
                throw new ValidationException(RNG002_ANO);
            }
        } catch (ParseException ex) {
            throw new ValidationException(ex.getMessage());
        }
        verifyCollection(livro.getAutores(), RNG002_AUTOR);
        verifyObject(livro.getEditora(), RNG002_EDITORA);
        ISBNValidator isbnValidator = ISBNValidator.getInstance();
        if (!isbnValidator.isValidISBN10(livro.getIsbn10())) {
            throwsValidationException(RNG002_ISBN10);
        }
        if (!isbnValidator.isValidISBN13(livro.getIsbn13())) {
            throwsValidationException(RNG002_ISBN13);
        }
        verifyString(livro.getTitulo(), RNG002_TITULO);
        verifyString(livro.getEdicao(), RNG002_EDICAO);
    }

    private void verifyString(String value, String message)
            throws ValidationException {
        if (value == null || value.isEmpty()) {
            throw new ValidationException(message);
        }
    }
    
    private void verifyCollection(Collection value, String message)
            throws ValidationException {
        if (value == null || value.isEmpty()) {
            throw new ValidationException(message);
        }
    }
    
    private void verifyObject(Object value, String message)
            throws ValidationException {
        if (value == null) {
            throw new ValidationException(message);
        }
    }
    
    private void throwsValidationException(String mensagem) throws ValidationException {
        throw new ValidationException(mensagem);    
    }
    
}
