package service;

import dataaccess.exceptions.*
import dataaccess.DAO.*;
import model.AuthData;
import model.GameData;

public class GameService {
    private final MemoryGameDAO gameDAO;
    private final MemoryAuthDAO authDAO;

    public GameService(MemoryGameDAO userDAO, MemoryAuthDAO authDAO) {
        this.gameDAO = userDAO;
        this.authDAO = authDAO;
    }

    public GameData createGame(GameData gameData, String authToken) throws UnauthorizedException, BadRequestException {
        AuthData authentication = authDAO.getAuth(authToken);
        if (authentication == null) {
            throw new UnauthorizedException("unauthorized");
        }
        if (gameData.gameName() == null || gameData.gameName().isEmpty()) {
            throw new BadRequestException("missing name");
        }
        else {
            gameDAO.createGame(gameData.gameName());
            return gameData;
        }
    }

    public void clearGames() {
        gameDAO.clear();
    }
}
