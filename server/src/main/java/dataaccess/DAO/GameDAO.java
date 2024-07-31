package dataaccess.DAO;

import dataaccess.SQLParent;

public class GameDAO extends SQLParent {

    private final String[] createStatement =
            new String[]{"""
                    CREATE TABLE IF NOT EXISTS Games
                    (
                        gameID SERIAL,
                        whiteUsername VARCHAR(256),
                        blackUsername VARCHAR(256),
                        gameName VARCHAR(256) NOT NULL,
                        chessGame JSON NOT NULL,
                        PRIMARY KEY (gameID)
                    );
            """
            };

    private final String tableName = "Games";

    public GameDAO() {
    }
}