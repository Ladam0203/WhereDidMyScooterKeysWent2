package ui.console;

public class ConsoleApp {
    public static void main(String[] args) {
        ConsoleController cc = new ConsoleController();
        cc.newGame();
        while (true)
        {
            cc.execute();
        }
    }
}
