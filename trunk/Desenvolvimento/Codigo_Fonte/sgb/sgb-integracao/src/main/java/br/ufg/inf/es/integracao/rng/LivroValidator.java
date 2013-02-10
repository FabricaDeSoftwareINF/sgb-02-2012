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
public class LivroValidator extends Validation<Livro> {

    public static final String RNG002_TITULO = "label.RNG02.titulo";
    public static final String RNG002_ISBN10 = "label.RNG02.isbn10";
    public static final String RNG002_ISBN13 = "label.RNG02.isbn13";
    public static final String RNG002_EDICAO = "label.RNG02.edicao";
    public static final String RNG002_EDITORA = "label.RNG02.editora";
    public static final String RNG002_AUTOR = "label.RNG02.autor";
    public static final String RNG002_ANO = "label.RNG02.ano";
    private SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy");

    public void validate(Livro livro) throws ValidationException {
        verifiqueAno(livro.getAno());
        verifyCollection(livro.getAutores(), RNG002_AUTOR);
        verifyObject(livro.getEditora(), RNG002_EDITORA);
        verifyString(livro.getTitulo(), RNG002_TITULO);
        verifyString(livro.getEdicao(), RNG002_EDICAO);

        ISBNValidator isbnValidator = ISBNValidator.getInstance();
        if (isInvalid(livro.getIsbn10())
                || !isbnValidator.isValidISBN10(livro.getIsbn10())) {
            throwsValidationException(RNG002_ISBN10);
        }
        if (isInvalid(livro.getIsbn13())
                || !isbnValidator.isValidISBN13(livro.getIsbn13())) {
            throwsValidationException(RNG002_ISBN13);
        }
    }

    private void verifiqueAno(Long ano) throws ValidationException {
        ValidationException ve = new ValidationException(RNG002_ANO);
        if (isInvalid(ano)) {
            throw ve;
        }

        try {
            Date date = dateFormatter.parse(ano.toString());
            if (date.after(new Date())) {
                throw ve;
            }
        } catch (ParseException ex) {
            throw new ValidationException(ex.getMessage());
        }
    }

    private void verifyString(String value, String message)
            throws ValidationException {
        if (isInvalid(value)) {
            throw new ValidationException(message);
        }
    }

    private void verifyCollection(Collection value, String message)
            throws ValidationException {
        if (isInvalid(value)) {
            throw new ValidationException(message);
        }
    }

    private void verifyObject(Object value, String message)
            throws ValidationException {
        if (isInvalid(value)) {
            throw new ValidationException(message);
        }
    }

    private void throwsValidationException(String mensagem) throws ValidationException {
        throw new ValidationException(mensagem);
    }
}
