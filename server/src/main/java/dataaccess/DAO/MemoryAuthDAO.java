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

    public void clear() {
        auths.clear();
    }

}
