package br.ufg.inf.es.model.importacaobibliografia;

import java.util.List;

/**
 *
 * @author Vinícius
 */
public class BibliografiaImportada {
    
    private List<LivroImportado> basica;
    private List<LivroImportado> complementar;
    private List<LivroImportado> sugerida;

    public BibliografiaImportada() {
    }

    public List<LivroImportado> getBasica() {
        return basica;
    }

    public void setBasica(List<LivroImportado> basica) {
        this.basica = basica;
    }

    public List<LivroImportado> getComplementar() {
        return complementar;
    }

    public void setComplementar(List<LivroImportado> complementar) {
        this.complementar = complementar;
    }

    public List<LivroImportado> getSugerida() {
        return sugerida;
    }

    public void setSugerida(List<LivroImportado> sugerida) {
        this.sugerida = sugerida;
    }

    @Override
    public String toString() {
        return "BibliografiaImportada{" + "basica=" + basica 
                + ", complementar=" + complementar + ", sugerida=" 
                + sugerida + '}';
    }
    
    
}
