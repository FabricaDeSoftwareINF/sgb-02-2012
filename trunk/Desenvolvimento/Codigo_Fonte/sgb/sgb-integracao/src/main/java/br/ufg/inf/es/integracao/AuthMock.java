package br.ufg.inf.es.integracao;

import br.ufg.inf.es.base.service.Auth;
import br.ufg.inf.es.base.util.SgbCryptography;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Cézar Augusto Ferreira
 */
public class AuthMock implements Auth {

    private static class User {

        private static final int VALOR_HASH = 5;
    
        private static final int SALTO = 71;
        
        private String login;
        
        private String password;

        public User(String login, String password) {
            this.login = login;
            this.password = password;
        }

        public String getLogin() {
            return login;
        }

        public void setLogin(String login) {
            this.login = login;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
        
        @Override
        public int hashCode() {
            int hash = User.VALOR_HASH;
            
            hash = User.SALTO * hash + (this.login != null ? this.login.hashCode() : 0);
            
            hash = User.SALTO * hash + (this.password != null ? this.password.hashCode() : 0);
            
            return hash;
        }

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
    
    private static final String SENHA_PADRAO = "123";
    
    private static final Map<User, Collection<String>> USERS;

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

    public Collection<String> login(String user, String password) {

        return USERS.get(new User(user, password));
    }
}
