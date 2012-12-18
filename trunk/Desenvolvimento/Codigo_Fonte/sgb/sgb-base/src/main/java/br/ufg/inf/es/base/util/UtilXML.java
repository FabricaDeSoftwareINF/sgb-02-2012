package br.ufg.inf.es.base.util;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.Map;

/**
 *
 * @author Victor Ribairo de Carvalho
 */
public class UtilXML extends UtilFile {

    private static XStream xStream;

    public static String convertaObjetoParaXML(Object objeto, Map<Class, String> mapaDeAlias) {
        definaXStream(mapaDeAlias);
        return xStream.toXML(objeto);
    }

    public static boolean convertaObjetoParaXML(Object objeto, Map<Class, String> mapaDeAlias, String caminho) {
        String xml = convertaObjetoParaXML(objeto, mapaDeAlias);

        try {
            FileOutputStream out = new FileOutputStream(caminho, false);
            PrintWriter pw = new PrintWriter(out, false);
            pw.print(xml);
            pw.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static Object convertaXMLEmObjeto(String caminho, Map<Class, String> mapaDeAlias) {
        definaXStream(mapaDeAlias);
        try {
            String xml = obtenhaConteudoDoArquivo(caminho);
            return xStream.fromXML(xml);
        } catch (Exception ex) {
            return null;
        }
    }

    private static void definaXStream(Map<Class, String> mapaDeAlias) {
        xStream = new XStream(new DomDriver());
        if (mapaDeAlias != null) {
            for (Map.Entry<Class, String> entry : mapaDeAlias.entrySet()) {
                xStream.alias(entry.getValue(), entry.getKey());
            }
        }
    }
}
