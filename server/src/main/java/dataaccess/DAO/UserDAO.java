package dataaccess.DAO;

import dataaccess.SQLParent;

public class UserDAO extends SQLParent {

    private final String[] createStatement =
            new String[]{"""
                    CREATE TABLE IF NOT EXISTS User
                    (
                      username VARCHAR(50) NOT NULL,
                        password VARCHAR(64) DEFAULT NULL,
                        email VARCHAR(256) DEFAULT NULL,
                        PRIMARY KEY (username)
                    );
            """
            };

    private final String tableName = "User";

    public IntUserDAO() {
    }
}