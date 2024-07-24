package server.handlers;

import service.UserService;
import spark.Request;
import spark.Response;
import com.google.gson.Gson;
import dataaccess.exceptions.UnauthorizedException;

public class LogoutHandler {
    private final Gson serializer = new Gson();
    private final ErrorHandler errorHandler = new ErrorHandler();

    public Object logout(Request request, Response response, UserService userService) {

        response.type("application/json");
        try {
            String authToken = request.headers("Authorization");
            userService.logoutUser(authToken);
            response.status(200);
            return serializer.toJson(new Object());
        }
        catch (UnauthorizedException e) {
            return errorHandler.handleError(e, response, 401);
        }
        catch (Exception e) {
            return errorHandler.handleError(e, response, 500);
        }
}