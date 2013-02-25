package br.ufg.inf.es.base.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Classe com operacoes uteis em arquivos
 *
 * @author Victor Ribairo de Carvalho
 */
public class UtilFile {

    /** Campo CHARSET*/
    private static final String CHARSET = "UTF-8";

    /**
     * Construtor da classe UtilFile.
     */
    protected UtilFile() {
    }

    /**
     * Método para criação de arquivos.
     *
     * @param pathArquivo Caminho para a geração do arquivo
     * @return O <code>File</code> gerado.
     */
    public static File crieArquivo(String pathArquivo) {

        File f = null;

        try {

            f = new File(pathArquivo);

            boolean arquivoCriado = f.createNewFile();

            if (!arquivoCriado) {
                
                throw new IOException();
            }

        } catch (IOException ex) {

            Logger.getLogger(UtilFile.class.getName()).log(Level.SEVERE, null, ex);
        }

        return f;
    }

    /**
     * Método responsável por ler o conteúdo de um arquivo.
     *
     * @param caminho Caminho do arquivo a ser lido.
     * @return Conteúdo do arquivo.
     */
    public static String obtenhaConteudoDoArquivo(String caminho) {

        String conteudo;

        try {

            FileInputStream stream = new FileInputStream(caminho);

            BufferedReader reader = new BufferedReader(new InputStreamReader(stream, UtilFile.CHARSET));

            StringBuilder stringBuilder = new StringBuilder();

            String linha;

            while ((linha = reader.readLine()) != null) {

                stringBuilder.append(linha).append("\n");
            }

            reader.close();

            conteudo = stringBuilder.toString().trim();

        } catch (IOException ex) {

            conteudo = "";
        }

        return conteudo;
    }
}
