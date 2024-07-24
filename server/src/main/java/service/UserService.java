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

        else {
                userDAO.createUser(user);
                return userDAO.getUser(user.username());
            }
        }

        public void validateUser(UserData user) throws UnauthorizedException {
            String hashedPassword = MemoryUserDAO.hashPassword(user.password());
            UserData hashedUser = new UserData(user.username(), hashedPassword, user.email());
        }

        public void clear() {
            userDAO.clear();
        }

    }