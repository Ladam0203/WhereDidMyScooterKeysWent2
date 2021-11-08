package enums;

import intf.Action;
import bll.GameLogic;

import java.util.Arrays;
import java.util.stream.Collectors;

public enum Command implements Action {
    GO("go", 1) {
        @Override
        public String action(GameLogic logic, String[] args) {
            if (args[1] == null)
                return "Go where?";
            return logic.go(args[1]);
        }
    },
    HELP("help", 0) {
        @Override
        public String action(GameLogic logic, String[] args) {
            return "I don't blame you, Adam also tends to forget things. Your commands are:\n" + Arrays.stream(Command.values()).map(Command::toString).collect(Collectors.joining(" "));
        }
    },
    LOOK("look", 0) {
        @Override
        public String action(GameLogic logic, String[] args) {
            return logic.look();
        }
    },
    OBSERVE("observe", 1) {
        @Override
        public String action(GameLogic logic, String[] args) {
            if (args[1] == null)
                return "Observe what?";
            return logic.observe(args[1]);
        }
    },
    QUIT("quit", 0) {
        @Override
        public String action(GameLogic logic, String[] args) {
            return "Thank you for playing!";
        }
    },
    TAKE("take", 1) {
        @Override
        public String action(GameLogic logic, String[] args) {
            return logic.take(args[1]);
        }
    },
    DROP("drop", 1) {
        @Override
        public String action(GameLogic logic, String[] args) {
            return logic.drop(args[1]);
        }
    },
    INVENTORY("inventory", 0) {
        @Override
        public String action(GameLogic logic, String[] args) {
            return logic.inventory();
        }
    };

    private String keyword;
    private int numArgs;

    Command(String keyword, int numArgs) {
        this.keyword = keyword;
        this.numArgs = numArgs;
    }

    @Override
    public String toString() {
        return keyword;
    }
}
