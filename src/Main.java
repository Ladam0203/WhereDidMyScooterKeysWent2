import ui.console.ConsoleApp;
import ui.console.ConsoleController;
import ui.javafx.JavaFXApp;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String mode;
        System.out.println("Would you like to start the application in gui or cli view?");
        mode = scanner.nextLine();
        switch (mode) {
            case "cli" -> ConsoleApp.main(args);
            case "gui" -> JavaFXApp.main(args);
        }
    }
}
