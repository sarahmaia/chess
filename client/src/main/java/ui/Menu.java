package ui;

import help.ServerFacade;
import model.GameData;

import java.util.HashMap;
import java.util.Map;

public class Menu {

    private enum UserState {
        LOGGED_OUT
    }

    private final ServerFacade server;
    private GameData gameData;
    private GameData[] gameList;
    private Map<Integer, Integer> gameIdMap = new HashMap<>();

    private String username;
    private String authToken;
    private UserState currentState = UserState.LOGGED_OUT;

    private String failure = "Couldn't process your command: ";

    public Menu(String hostname, int portNumber) {
        server = new ServerFacade(hostname, portNumber);
    }
}