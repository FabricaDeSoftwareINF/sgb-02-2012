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

    /** Campo serialVersionUID*/
    private static final long serialVersionUID = -4085032132966091074L;

    /** Campo pubKey*/
    private PublicKey pubKey;
    
    /** Campo privKey*/
    private PrivateKey privKey;

	/**
	 * Obtém o valor do campo <code>pubKey</code>
	 *
	 * @return {@link PublicKey}
	 */
	public PublicKey getPubKey() {
		
		return pubKey;
	}

	/**
	 * Define o campo <code>pubKey</code>.
	 *
	 * @param pubKey 
	 */
	public void setPubKey(PublicKey pubKey) {
		
		this.pubKey = pubKey;
	}

	/**
	 * Obtém o valor do campo <code>privKey</code>
	 *
	 * @return {@link PrivateKey}
	 */
	public PrivateKey getPrivKey() {
		
		return privKey;
	}

	/**
	 * Define o campo <code>privKey</code>.
	 *
	 * @param privKey 
	 */
	public void setPrivKey(PrivateKey privKey) {
	
		this.privKey = privKey;
	}
}
