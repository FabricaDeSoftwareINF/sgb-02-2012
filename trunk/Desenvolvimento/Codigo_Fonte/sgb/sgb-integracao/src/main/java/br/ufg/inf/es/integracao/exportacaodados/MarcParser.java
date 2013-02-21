package br.ufg.inf.es.integracao.exportacaodados;

import br.ufg.inf.es.model.Livro;
import java.io.ByteArrayOutputStream;
import java.io.Serializable;
import java.util.List;
import org.marc4j.MarcStreamWriter;
import org.marc4j.marc.DataField;
import org.marc4j.marc.Record;
import org.marc4j.marc.impl.DataFieldImpl;
import org.marc4j.marc.impl.LeaderImpl;
import org.marc4j.marc.impl.RecordImpl;
import org.marc4j.marc.impl.SubfieldImpl;

/**
 * Fornecer servi&ccedil;os que fazem o parse da entidade <code>Livro</code>
 * para o formato <code>MARC</code>
 * @author vinicius
 */
public class MarcParser implements Serializable {
    
    /**
     * Realiza o parser de um livro para o formato MARC 21. Apenas as 
     * informações básicas são adicionadas ao MARC. Uma descrição detalhada
     * dos campos e subcampos deste formato está disponível em: 
     * http://www.loc.gov/marc/bibliographic/ecbdhome.html
     * @param livro
     * @return 
     */
    public static String livroToMarc(Livro livro) throws Exception {
        Record record = new RecordImpl();
        record.setLeader(new LeaderImpl("00714cam a2200205 a 4500"));
        
        List<DataField> dataFields = record.getDataFields();
        
        DataField isbn = new DataFieldImpl("020", ' ', ' ');
        isbn.addSubfield(new SubfieldImpl('a', livro.getIsbn13()));
        
        DataField authors = new DataFieldImpl("100", ' ', ' ');
        authors.setIndicator1('1');
        String autores = livro.getAutores().toString().
                replace("[", "").replace("]", "");
        authors.addSubfield(new SubfieldImpl('a', autores));
        
        DataField uniformTitle = new DataFieldImpl("130", ' ', ' ');
        uniformTitle.setIndicator1('0');
        uniformTitle.addSubfield(new SubfieldImpl('a', livro.getTitulo()));
        
        DataField title = new DataFieldImpl("245", ' ', ' ');
        title.addSubfield(new SubfieldImpl('a', livro.getTitulo()));
        
        DataField edition = new DataFieldImpl("250", ' ', ' ');
        edition.addSubfield(new SubfieldImpl('a', livro.getEdicao()));
        
        DataField imprint = new DataFieldImpl("260", ' ', ' ');
        imprint.addSubfield(new SubfieldImpl('b', livro.getEditora().getNome()));
        imprint.addSubfield(new SubfieldImpl('c', livro.getAno().toString()));
        
        dataFields.add(isbn);
        dataFields.add(authors);
        dataFields.add(uniformTitle);
        dataFields.add(title);
        dataFields.add(edition);
        dataFields.add(imprint);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        MarcStreamWriter writer = new MarcStreamWriter(baos);
        writer.write(record);
        
        return baos.toString();
    }
}
