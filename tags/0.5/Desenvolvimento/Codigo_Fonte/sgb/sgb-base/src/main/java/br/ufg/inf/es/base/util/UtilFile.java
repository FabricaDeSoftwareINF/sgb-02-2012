package br.ufg.inf.es.base.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Classe com operacoes uteis em arquivos
 * 
 * @author Victor Ribairo de Carvalho
 */
public class UtilFile {

    public static File crieArquivo(String pathArquivo) {
        File f = null;
        try {
            f = new File(pathArquivo);
            f.createNewFile();
        } catch (IOException ex) {
            Logger.getLogger(UtilFile.class.getName()).log(Level.SEVERE, null, ex);
        }
        return f;
    }

    public static String obtenhaConteudoDoArquivo(String caminho) {
        String conteudo;
        try {
            BufferedReader br = new BufferedReader(new FileReader(caminho));
            StringBuilder sb = new StringBuilder();

            String linha;
            while ((linha = br.readLine()) != null) {
                sb.append(linha).append("\n");
            }

            br.close();
            conteudo = sb.toString().trim();
        } catch (Exception ex) {
            conteudo = "";
        }
        return conteudo;
    }
}
