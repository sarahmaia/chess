package server.handlers;

import com.google.gson.Gson;
import dataaccess.exceptions.UnauthorizedException;
import model.AuthData;
import model.UserData;
import service.UserService;
import spark.Request;
import spark.Response;

public class LoginHandler {
    private final Gson serializer = new Gson();
    private final ErrorHandler errorHandler = new ErrorHandler();

    public Object login(Request req, Response response, UserService userService) {
        response.type("application/json");
        try {
            UserData user = serializer.fromJson(req.body(), UserData.class);
            AuthData auth = userService.loginUser(user);
            response.status(200);
            return serializer.toJson(auth);
        }
        catch (UnauthorizedException e) {
            return errorHandler.handleError(e, response, 401);
        }
        catch (Exception e) {
            return errorHandler.handleError(e, response, 500);
        }
    }
}