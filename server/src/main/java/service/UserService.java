package service;

import dataaccess.exceptions.*;
import dataaccess.DAO.MemoryUserDAO;
import model.*;

public class UserService {
    private final MemoryUserDAO userDAO;

    public UserService(MemoryUserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public UserData createUser(UserData user) throws UserExistsException, BadRequestException {
        UserData checkUsername = userDAO.getUser(user.username());

        if (checkUsername != null) {
            throw new UserExistsException("already exists");
        }
        else if (user.password() == null || user.password().isEmpty()) {
            throw new BadRequestException("bad request");
        }
        else {
            userDAO.createUser(user);
            return userDAO.getUser(user.username());
        }
    }

    public void validateUser(UserData user) throws UnauthorizedException {
        String hashedPassword = MemoryUserDAO.hashPassword(user.password());
        UserData hashedUser = new UserData(user.username(), hashedPassword, user.email());
        UserData validateUser = userDAO.getUser(hashedUser.username());

        if (validateUser == null || !validateUser.password().equals(hashedUser.password())) {
            throw new UnauthorizedException("unauthorized");
        }
    }

    public void clear() {
        userDAO.clear();
    }

}