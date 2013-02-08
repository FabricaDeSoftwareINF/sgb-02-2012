package br.ufg.inf.es.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 *
 * @author igor
 */
@Entity
@Table(name = "COMUNICACAO_PARAMETROS")
public class Comunicacao extends AbstractEntityModel{
    
    @Column(name="service", nullable=false)
    private String service;
    
    @Column(name="protocolo_ssl", nullable=false)
    private boolean ssl = false;
    
    @Column(name="protocolo_tsl", nullable=false)
    private boolean tsl = false;
    
    @Column(name="port", nullable=false)
    private String port;
    
    @Column(name="usuario", nullable=false)
    private String usuario;
    
    @Column(name="senha", length=300,nullable=false)
    private byte[] senha;

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public boolean isSsl() {
        return ssl;
    }

    public void setSsl(boolean ssl) {
        this.ssl = ssl;
    }

    public boolean isTsl() {
        return tsl;
    }

    public void setTsl(boolean tsl) {
        this.tsl = tsl;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public byte[] getSenha() {
        return senha;
    }

    public void setSenha(byte[] senha) {
        this.senha = senha;
    }
}
