package intf;

import bll.GameLogic;

public interface Action {
    String action(GameLogic logic, String[] args);
}
