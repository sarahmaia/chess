package dataaccess.DAO;

import dataaccess.SQLParent;

public class AuthDAO extends SQLParent {

    private final String[] createStatement =
            new String[]{"""
                    CREATE TABLE IF NOT EXISTS Authentication
                    (
                      authToken VARCHAR(256) NOT NULL,
                      username VARCHAR(50) NOT NULL,
                      PRIMARY KEY (authToken)
                    );
            """
            };

    private final String tableName = "Authentication";

    public AuthDAO() {

    }
}
