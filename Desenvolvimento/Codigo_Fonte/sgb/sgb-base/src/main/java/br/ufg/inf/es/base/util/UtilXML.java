package br.ufg.inf.es.base.util;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

/**
 * Classe utilitária para arquivos Xml.
 * 
 * @author Victor Ribairo de Carvalho
 */
public class UtilXML extends UtilFile {

    /**
     * Atributo xStream.
     */
    private static XStream xStream;

    /**
     * Método para converter objetos para xml.
     * @param objeto Objeto a ser convertido.
     * @param mapaDeAlias
     * @return String contendo o xml.
     */
    public static String convertaObjetoParaXML(Object objeto, Map<Class, String> mapaDeAlias) {
        
        definaXStream(mapaDeAlias);
        
        return xStream.toXML(objeto);
    }

    /**
     *  Converte um objeto em xml e o salva em um arquivo.
     * 
     * @param objeto Objeto a ser convertido.
     * @param mapaDeAlias Alias do xml.
     * @param caminho Caminho onde o arquivo será salvo.
     * @return <code>true</code> se o arquivo foi convertido com sucesso.
     */
    public static boolean convertaObjetoParaXML(Object objeto, Map<Class, String> mapaDeAlias, String caminho) {
        
        String xml = convertaObjetoParaXML(objeto, mapaDeAlias);

        try {
            
            FileWriter fw = new FileWriter(caminho, false);
            
            fw.write(xml);
            
            fw.close();
            
            return true;
            
        } catch (IOException io) {
            
            return false;
        }
    }

    /**
     * Converte um arquivo Xml para um objeto.
     * 
     * @param caminho Caminho para o arquivo xml.
     * @param mapaDeAlias Alias do xml.
     * @return Objeto convertido.
     */
    public static Object convertaXMLEmObjeto(String caminho, Map<Class, String> mapaDeAlias) {
        
        definaXStream(mapaDeAlias);
                  
        String xml = obtenhaConteudoDoArquivo(caminho);
            
        return xStream.fromXML(xml);           
    }

    /**
     * Método para iniciar o objeto XStream.
     * 
     * @param mapaDeAlias Alias do xml.
     */
    private static void definaXStream(Map<Class, String> mapaDeAlias) {
        
        xStream = new XStream(new DomDriver());
        
        if (mapaDeAlias != null) {
            
            for (Map.Entry<Class, String> entry : mapaDeAlias.entrySet()) {
                
                xStream.alias(entry.getValue(), entry.getKey());
            }
        }
    }
}
