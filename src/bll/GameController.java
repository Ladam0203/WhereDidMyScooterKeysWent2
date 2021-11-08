package bll;

import dal.GameDataAccess;
import db.GameDataModel;
import enums.Command;

public abstract class GameController {
    public GameDataAccess dataAccess;
    private GameLogic logic;
    private Parser parser;

    public GameController()
    {
        GameDataModel dataModel = new GameDataModel(); //after init, it won't have access, only through dataAccess
        dataAccess = new GameDataAccess(dataModel);
        logic = new GameLogic(dataModel);
        parser = new Parser();
    }

    public void newGame()
    {
        pushOutput(logic.newGame());
    }

    public void execute()
    {
        String input = pullInput();
        String[] args = input.split("\\s"); // default split on white space
        Command command = parser.getCommand(args[0]);
        try {
            pushOutput(command.action(logic, args));
            if(command == Command.QUIT) exitEnvironment();
        }
        catch (NullPointerException e) { displayException("Unknown command!"); }
        catch(Exception e) { displayException(e.toString()); } //Handle null when there is an unknown command
    }

    public abstract void displayException(String error);
    public abstract String pullInput();
    public abstract void pushOutput(String output);
    public abstract void exitEnvironment();
}
