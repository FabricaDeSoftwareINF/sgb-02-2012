/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufg.inf.es.integracao;

import br.ufg.inf.es.base.validation.ValidationException;
import br.ufg.inf.es.integracao.annotations.RNG006;
import br.ufg.inf.es.model.Curso;
import br.ufg.inf.es.model.Disciplina;
import br.ufg.inf.es.persistencia.DisciplinaDAO;
import java.util.ArrayList;
import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

/**
 *
 * @author cezar
 */
@Component
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class DisciplinaService extends GenericService<Disciplina> {

    @Autowired
    private DisciplinaDAO dao;

    @Override
    public DisciplinaDAO getDAO() {
        return this.dao;
    }
    
    private Curso cursoSelecionado = new Curso();

    public void setDao(DisciplinaDAO dao) {

        this.dao = dao;
    }

    @Override
    @RNG006
    public Long insert(Disciplina entidade) throws ValidationException {
        
        return this.getDAO().insert(entidade);
    }
     public Curso getCursoSelecionado() {
        return cursoSelecionado;
    }

    public void setCursoSelecionado(Curso cursoSelecionado) {
        this.cursoSelecionado = cursoSelecionado;
    }
    public Collection<Disciplina> complete(String query) {  
        Collection<Disciplina> results = new ArrayList<Disciplina>();
        Disciplina d = new Disciplina();
        d.setCurso(getCursoSelecionado());
          
        for(Disciplina disciplina : this.dao.search(d)){
            if(disciplina.getNome().contains(query)){
                results.add(disciplina);
            }
        }
          
        return results;  
    }  
    
}