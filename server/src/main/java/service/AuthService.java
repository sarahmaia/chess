package service;

import dataaccess.exceptions.UnauthorizedException;
import dataaccess.DAO.MemoryAuthDAO;
import model.AuthData;
import model.UserData;

public class AuthService {
    private final MemoryAuthDAO authDAO;

    public AuthService(MemoryAuthDAO authDAO) {
        this.authDAO = authDAO;
    }

    public AuthData loginUser(UserData user) throws UnauthorizedException {
        return authDAO.createAuth(user.username());
    }

    public void logoutUser(String authToken) throws UnauthorizedException {
        AuthData authData = authDAO.getAuthByToken(authToken);
        if (authData == null) {
            throw new UnauthorizedException("unauthorized");
        }
        else {
            authDAO.deleteAuth(authToken);
        }
    }

    public AuthData getUserByAuthToken(String authToken) throws UnauthorizedException {
        return authDAO.getAuthByToken(authToken);
    }

    public void validateAuthToken(String authToken) throws UnauthorizedException {
        AuthData authData = authDAO.getAuthByToken(authToken);
        if (authData == null || !authData.authToken().equals(authToken)) {
            throw new UnauthorizedException("unauthorized");
        }
    }

    public void clear() {
        authDAO.clear();
    }
}