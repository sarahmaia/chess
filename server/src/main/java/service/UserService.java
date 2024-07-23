package service;

import dataaccess.exceptions.*;
import dataaccess.DAO.MemoryAuthDAO;
import dataaccess.DAO.MemoryUserDAO;
import model.*;

public class UserService {
    private final MemoryUserDAO userDAO;
    private final MemoryAuthDAO authDAO;

    public UserService(MemoryUserDAO userDAO, MemoryAuthDAO authDAO) {
        this.userDAO = userDAO;
        this.authDAO = authDAO;
    }

    public AuthData createUser(UserData user) throws UserExistsException, BadRequestException {
        UserData checkUsername = userDAO.getUser(user.username());

        if (checkUsername != null) {
            throw new UserExistsException("already exists");
        }
        else if (user.password() == null || user.password().isEmpty()) {
            throw new BadRequestException("bad request");
        }
        else {
            userDAO.createUser(user);
            return authDAO.createAuth(user.username());
        }
    }

    public void clear() {
        userDAO.clear();
        authDAO.clear();
    }

}