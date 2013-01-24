package br.ufg.inf.es.integracao.exportacaodados;

import br.ufg.inf.es.model.Livro;
import java.io.ByteArrayOutputStream;
import java.util.List;
import org.marc4j.MarcStreamWriter;
import org.marc4j.marc.DataField;
import org.marc4j.marc.Record;
import org.marc4j.marc.Subfield;
import org.marc4j.marc.impl.ControlFieldImpl;
import org.marc4j.marc.impl.DataFieldImpl;
import org.marc4j.marc.impl.LeaderImpl;
import org.marc4j.marc.impl.RecordImpl;
import org.marc4j.marc.impl.SubfieldImpl;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 *
 * @author vinicius
 */
@Component
@Scope(BeanDefinition.SCOPE_SINGLETON)
public class MarcParser {
    
    public String livroToMarc(Livro livro) {
        Record record = new RecordImpl();
        record.setLeader(new LeaderImpl("00714cam a2200205 a 4500"));
        
        List<DataField> dataFields = record.getDataFields();
        
        DataField titulo = new DataFieldImpl("245", ' ', ' ');
        titulo.addSubfield(new SubfieldImpl('a', livro.getTitulo()));
        
        dataFields.add(titulo);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        MarcStreamWriter writer = new MarcStreamWriter(baos);
        writer.write(record);
        
        return baos.toString();
    }
}
