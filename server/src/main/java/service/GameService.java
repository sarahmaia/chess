package service;

import dataaccess.DAO.MemoryAuthDAO;
import dataaccess.DAO.MemoryGameDAO;

public class GameService {
    private final MemoryGameDAO gameDAO;
    private final MemoryAuthDAO authDAO;

    public GameService(MemoryGameDAO userDAO, MemoryAuthDAO authDAO) {
        this.gameDAO = userDAO;
        this.authDAO = authDAO;
    }
}
