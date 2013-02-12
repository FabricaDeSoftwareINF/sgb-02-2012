package br.ufg.inf.es.integracao;

import br.ufg.inf.es.base.service.Auth;
import br.ufg.inf.es.base.util.SgbCryptography;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Classe que define um mock para autenticação.
 * @author Cézar Augusto Ferreira
 */
public class AuthMock implements Auth {

    /**
     * Classe responsável por definir um Usuário.
     *
     */
    private static class User {

        /** Campo VALOR_HASH*/
        private static final int VALOR_HASH = 5;
    
        /** Campo SALTO*/
        private static final int SALTO = 71;
        
        /** Campo login*/
        private String login;
        
        /** Campo password*/
        private String password;

        /**
         * Construtor desta classe.
         * @param login
         * @param password
         */
        public User(String login, String password) {
            this.login = login;
            this.password = password;
        }
        
        /**
		 * Obtém o valor do campo <code>login</code>
		 *
		 * @return {@link String}
		 */
		public String getLogin() {
			return this.login;
		}

		/**
		 * Define o campo <code>login</code>.
		 *
		 * @param login 
		 */
		public void setLogin(String login) {
			this.login = login;
		}

		/**
		 * Obtém o valor do campo <code>password</code>
		 *
		 * @return {@link String}
		 */
		public String getPassword() {
			return this.password;
		}

		/**
		 * Define o campo <code>password</code>.
		 *
		 * @param password 
		 */
		public void setPassword(String password) {
			this.password = password;
		}

		/** 
		 * {@inheritDoc} 
		 */
		@Override
        public int hashCode() {
            int hash = User.VALOR_HASH;
            
            hash = User.SALTO * hash + (this.login != null ? this.login.hashCode() : 0);
            
            hash = User.SALTO * hash + (this.password != null ? this.password.hashCode() : 0);
            
            return hash;
        }

        /** 
         * {@inheritDoc} 
         */
        @Override
        public boolean equals(Object obj) {
            if (obj == null) {
                return false;
            }
            if (getClass() != obj.getClass()) {
                return false;
            }
            final User other = (User) obj;
            if ((this.login == null) ? (other.login != null) : !this.login.equals(other.login)) {
                return false;
            }
            if ((this.password == null) ? (other.password != null) : !this.password.equals(other.password)) {
                return false;
            }
            return true;
        }
    }
    
    /** Campo SENHA_PADRAO*/
    private static final String SENHA_PADRAO = "123";
    
    /** Campo USERS*/
    private static final Map<User, Collection<String>> USERS;

    /**
     * Método que adiciona um usuário e suas permissões.
     *
     * @param user
     * @param roles
     */
    private static void addUser(User user, String... roles) {

        USERS.put(user, Arrays.asList(roles));
    }

    static {

        USERS = new HashMap<User, Collection<String>>();
        
        SgbCryptography cryptography = new SgbCryptography();
        
        String passEncrypt =  cryptography.encrypt(AuthMock.SENHA_PADRAO);
        
        addUser(new User("professor",passEncrypt), "ROLE_PROFESSOR");

        addUser(new User("conselheiro", passEncrypt), "ROLE_CONSELHEIRO");

        addUser(new User("admin",passEncrypt), "ROLE_ADMIN");

        addUser(new User("all",passEncrypt), "ROLE_ADMIN", "ROLE_PROFESSOR", "ROLE_CONSELHEIRO");
    }

    /** 
     * {@inheritDoc} 
     */
    public Collection<String> login(String user, String password) {

        return USERS.get(new User(user, password));
    }
}
