package br.ufg.inf.es.integracao.rng;

import br.ufg.inf.es.base.validation.Validation;
import br.ufg.inf.es.base.validation.ValidationException;
import br.ufg.inf.es.model.Livro;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.stereotype.Component;

/**
 *
 * @author vinicius
 */
@Component
public class LivroValidator implements Validation<Livro> {

    public static final String RNG002_TITULO = "label.RNG02.titulo";
    public static final String RNG002_ISBN11 = "label.RNG02.titulo";
    public static final String RNG002_ISBN13 = "label.RNG02.titulo";
    public static final String RNG002_EDICAO = "label.RNG02.titulo";
    public static final String RNG002_EDITORA = "label.RNG02.titulo";
    public static final String RNG002_AUTOR = "label.RNG02.titulo";
    
    private SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy");

    public void validate(Livro livro) throws ValidationException {
        try {
            dateFormatter.parse(livro.getAno().toString());
        } catch (ParseException ex) {
            throw new ValidationException(ex.getMessage());
        }
        verifyString(livro.getTitulo(), RNG002_TITULO);
        verifyString(livro.getIsbn11(), RNG002_ISBN11);
        verifyString(livro.getIsbn13(), RNG002_ISBN13);
        verifyString(livro.getEdicao(), RNG002_EDICAO);
    }

    private void verifyString(String value, String message)
            throws ValidationException {
        if (value == null || value.isEmpty()) {
            throw new ValidationException(message);
        }
    }
}
