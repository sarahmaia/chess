package server;

import dataaccess.DAO.*;
import spark.*;
import server.handlers.*;
import service.*;

public class Server {
    private final GameService gameService;
    private final UserService userService;
    private final AuthService authService;

    public Server() {
        MemoryAuthDAO authDao = new MemoryAuthDAO();
        MemoryGameDAO gameDao = new MemoryGameDAO();
        MemoryUserDAO userDao = new MemoryUserDAO();
        this.gameService = new GameService(gameDao);
        this.userService = new UserService(userDao);
        this.authService = new AuthService(authDao);
    }

    public int run(int desiredPort) {
        Spark.port(desiredPort);
        Spark.staticFiles.location("web");

        Spark.post("/user", (req, res) -> new RegisterHandler().register(req, res, userService, authService));
        Spark.post("/session", (req, res) -> new LoginHandler().login(req, res, userService, authService));
        Spark.post("/game", (req, res) -> new CreateGameHandler().createGame(req, res, gameService, authService));
        Spark.get("/game", (req, res) -> new ListGamesHandler().listGames(req, res, gameService, authService));
        Spark.delete("/session", (req, res) -> new LogoutHandler().logout(req, res, authService));
        Spark.delete("/db", (req, res) -> new ClearHandler().clear(res, userService, gameService, authService));
        Spark.put("/game", (req, res) -> new JoinGameHandler().joinGame(req, res, gameService, authService));
        Spark.init();
        Spark.awaitInitialization();
        return Spark.port();
    }

    public void stop() {
        Spark.stop();
        Spark.awaitStop();
    }
}