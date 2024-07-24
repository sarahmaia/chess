package service;

import dataaccess.DataAccessException;
import dataaccess.exceptions.*;
import dataaccess.DAO.MemoryGameDAO;
import model.GameData;
import java.util.Collection;

public class GameService {
    private final MemoryGameDAO gameDAO;

    public GameService(MemoryGameDAO gameDAO) {
        this.gameDAO = gameDAO;
    }

    public GameData createGame(GameData gameData) throws BadRequestException {
        if (gameData.gameName() == null || gameData.gameName().isEmpty()) {
            throw new BadRequestException("missing name");
        }
        else {
            return gameDAO.createGame(gameData.gameName());
        }
    }

    public Collection<GameData> listAllGames() throws DataAccessException {
        return gameDAO.readAllGames();
    }

    public GameData joinGame(int gameID, String joinedColor, String username) throws UserExistsException, BadRequestException {
        GameData gameToJoin = gameDAO.getGame(gameID);

        if (gameToJoin == null) {
            throw new BadRequestException("bad request");
        }

        if ( joinedColor.equals("WHITE") && (gameToJoin.whiteUsername() == null || gameToJoin.whiteUsername().isEmpty())) {
            gameDAO.updateGame(
                    new GameData(
                            gameID,
                            username,
                            gameToJoin.blackUsername(),
                            gameToJoin.gameName(),
                            gameToJoin.game()
                    )
            );
        } else if (joinedColor.equals("BLACK") && (gameToJoin.blackUsername() == null || gameToJoin.blackUsername().isEmpty())) {
            gameDAO.updateGame(
                    new GameData(
                            gameID,
                            gameToJoin.whiteUsername(),
                            username,
                            gameToJoin.gameName(),
                            gameToJoin.game()
                    )
            );
        }
        else {
            throw new UserExistsException("already taken");
        }
        return gameDAO.getGame(gameID);
    }

    public void clearGames() {
        gameDAO.clear();
    }
}