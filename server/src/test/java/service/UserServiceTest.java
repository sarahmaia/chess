package service;

import dataaccess.exceptions.*;
import dataaccess.DAO.*;
import model.UserData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class UserServiceTest {
    private MemoryUserDAO memoryUserDAO;
    private MemoryGameDAO memoryGameDAO;
    private MemoryAuthDAO memoryAuthDAO;
    private UserService userService;

    @BeforeEach
    void setUp() {
        memoryUserDAO = new MemoryUserDAO();
        memoryGameDAO = new MemoryGameDAO();
        memoryAuthDAO = new MemoryAuthDAO();
        userService = new UserService(memoryUserDAO);
    }

    @Test
    void createValidUser() throws UserExistsException, BadRequestException, UnauthorizedException {
        UserData userData = new UserData("bob", "password", "email@email.com");
        userService.createUser(userData);
        userService.validateUser(userData);
    }

    @Test
    void createInvalidUser() throws UserExistsException, BadRequestException {
        UserData userData = new UserData("bob", null, "email@email.com");
        assertThrows(BadRequestException.class, () -> userService.createUser(userData));
    }

    @Test
    void createDuplicateUser() throws UserExistsException, BadRequestException, UnauthorizedException {
        UserData userData = new UserData("bob", "password", "email@email.com");
        userService.createUser(userData);
        assertThrows(UserExistsException.class, () -> userService.createUser(userData));
    }

    @Test
    void clear() throws UserExistsException, BadRequestException, UnauthorizedException {
        UserData userData = new UserData("bob", "password", "email@email.com");
        userService.createUser(userData);
        userService.clear();
        assertThrows(UnauthorizedException.class, () -> userService.validateUser(userData));
    }
}