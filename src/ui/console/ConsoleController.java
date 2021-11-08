package ui.console;

import bll.GameController;

import java.util.Scanner;

public class ConsoleController extends GameController {
    private Scanner scanner = new Scanner(System.in);


    @Override
    public void displayException(String error) {
        System.out.println(error.toString());
    }

    @Override
    public String pullInput() {
        System.out.print(">");
        return scanner.nextLine();
    }

    @Override
    public void pushOutput(String output) {
        System.out.println(output);
    }

    @Override
    public void exitEnvironment() {
        System.exit(0);
    }
}
