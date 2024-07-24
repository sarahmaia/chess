package dataaccess.DAO;

import model.AuthData;
import model.UserData;
import java.util.HashMap;
import java.util.Map;

public class MemoryAuthDAO {
    private final Map<String, AuthData> auths = new HashMap<>();

    public MemoryAuthDAO() {}

    public AuthData createAuth(String username) {
        AuthData auth = new AuthData(AuthData.createToken(), username);
        auths.put(username, auth);
        return auth;
    }

    public AuthData getAuth(String username) {
        return auths.get(username);
    }

    public AuthData getAuthByToken(String token) {
        for (AuthData auth : auths.values()) {
            if (auth.authToken().equals(token)) {
                return auth;
            }
        }
        return null;
    }

    public void deleteAuth(String authToken) {
        AuthData auth = getAuthByToken(authToken);
        String username = auth.username();
        auths.remove(username);
    }

    public void clear() {
        auths.clear();
    }

}
