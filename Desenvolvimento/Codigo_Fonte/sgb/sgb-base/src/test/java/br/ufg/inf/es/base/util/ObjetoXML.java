package br.ufg.inf.es.base.util;

/**
 * Classe utilizada para testes de conversao de objetos em xml e de conteudo xml
 * em objetos.
 *
 * @author Victor R Carvalho
 */
public class ObjetoXML {

    private String propriedade1 = "valor1";
    private String propriedade2 = "valor2";

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ObjetoXML other = (ObjetoXML) obj;
        if ((this.propriedade1 == null) ? (other.propriedade1 != null) : !this.propriedade1.equals(other.propriedade1)) {
            return false;
        }
        if ((this.propriedade2 == null) ? (other.propriedade2 != null) : !this.propriedade2.equals(other.propriedade2)) {
            return false;
        }
        return true;
    }
}