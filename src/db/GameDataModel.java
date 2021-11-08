package db;

import be.Area;
import be.Player;
import enums.Command;

import java.util.ArrayList;
import java.util.List;

public class GameDataModel {
    protected Player player;
    protected List<Area> areas;

    public GameDataModel()
    {
        player = new Player();
        areas = new ArrayList<>();
    }

    public Player getPlayer() {
        return player;
    }

    public List<Area> getAreas() {
        return areas;
    }

}
