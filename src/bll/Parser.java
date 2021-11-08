package bll;

import enums.Command;

import java.util.Arrays;
import java.util.List;

public class Parser {
    List<Command> commands;
    public Parser()
    {
        commands = Arrays.stream(Command.values()).toList(); //Is array enuff?
    }

    /*
    Find a command of a keyword, if there is no command like that, returns null
     */
    public Command getCommand(String keyword)
    {
        return commands.stream().filter(command -> command.toString().equals(keyword)).findFirst().orElse(null);
    }
}
