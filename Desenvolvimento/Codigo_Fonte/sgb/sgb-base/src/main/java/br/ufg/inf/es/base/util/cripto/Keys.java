package br.ufg.inf.es.base.util.cripto;

import java.io.Serializable;
import java.security.PrivateKey;
import java.security.PublicKey;

/**
 * Classe que representa as chaves de encritação e decritação (chave privada e 
 * chave pública)
 * @author igor
 */
public class Keys implements Serializable{

    private static final long serialVersionUID = -4085032132966091074L;

    private PublicKey pubKey;
    private PrivateKey privKey;

    public PublicKey getPubKey() {
        return pubKey;
    }
    public void setPubKey(PublicKey pubKey) {
        this.pubKey = pubKey;
    }
    public PrivateKey getPrivKey() {
        return privKey;
    }
    public void setPrivKey(PrivateKey privKey) {
        this.privKey = privKey;
    }
}
