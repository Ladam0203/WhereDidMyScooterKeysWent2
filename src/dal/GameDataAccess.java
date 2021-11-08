package dal;

import be.Exit;
import be.Item;
import be.Player;
import db.GameDataModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class GameDataAccess {
    GameDataModel dataModel;

    Player player;

    public GameDataAccess(GameDataModel dataModel)
    {
        this.dataModel = dataModel;
        player = dataModel.getPlayer(); //easier access
    }

    public String areaName()
    {
        return player.getArea().getName();
    }

    public ObservableList<Exit> exits() {
        return FXCollections.observableList(player.getArea().getExits());
    }

    public ObservableList<Item> areaItems() {
        return FXCollections.observableList(player.getArea().getItems());
    }

    public ObservableList<Item> inventory() {
        return FXCollections.observableList(player.getInventory());
    }

    public double inventoryCapacity() {
        return player.getInventoryCapacity();
    }

    public double inventoryWeight() {
        return player.getInventoryWeight();
    }

    public String areaDescription() {
        return player.getArea().getDescription();
    }
}
