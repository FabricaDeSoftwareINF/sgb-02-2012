package br.ufg.inf.es.integracao;

import br.ufg.inf.es.base.service.Auth;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Cézar Augusto Ferreira
 */
public class AuthMock implements Auth {

    private static class User {

        String login;
        String password;

        public User(String login, String password) {
            this.login = login;
            this.password = password;
        }

        @Override
        public int hashCode() {
            int hash = 7;
            hash = 59 * hash + (this.login != null ? this.login.hashCode() : 0);
            hash = 59 * hash + (this.password != null ? this.password.hashCode() : 0);
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
    
    private static final Map<User, Collection<String>> users;

    private static void addUser(User user, String... roles) {

        users.put(user, Arrays.asList(roles));
    }

    static {

        users = new HashMap<User, Collection<String>>();

        addUser(new User("professor", "123"), "ROLE_PROFESSOR");

        addUser(new User("conselheiro", "123"), "ROLE_CONSELHEIRO");

        addUser(new User("admin", "123"), "ROLE_ADMIN");

        addUser(new User("all", "123"), "ROLE_ADMIN", "ROLE_PROFESSOR", "ROLE_CONSELHEIRO");
    }

    public Collection<String> login(String user, String password) {

        return users.get(new User(user, password));
    }
}
