package bll;

import be.*;
import db.GameDataModel;
import enums.Command;

import java.util.stream.Collectors;

public class GameLogic {
    private GameDataModel dataModel;

    private Player player;

    public GameLogic(GameDataModel dataModel)
    {
        this.dataModel = dataModel;
        player = dataModel.getPlayer(); //Easier access of the player
    }

    public String newGame() {
        Area hall = new Area("Hall", "No man's land");
        Area myRoom = new Area("My room", "'Functional' is the most accurate adjective");

        Key myRoomKey = new Key("my-rooms-key", "Bob put a lock on my door...", 0);

        hall.setExit(new Exit("north", myRoom, myRoomKey));
        myRoom.setExit(new Exit("south", hall, null));

        hall.addItem(myRoomKey);
        hall.addItem(new Item("umbrella", "a must-have in Denmark", 1, true));

        myRoom.addItem(new Item("fanny-pack", "I would never go anywhere without this", 0.5, true));

        dataModel.getAreas().add(hall);
        dataModel.getAreas().add(myRoom);

        dataModel.getPlayer().setArea(hall);

        return "Hey, welcome to 'Where Did My Scooter Keys Went?' (2.0) where the goal is to help Adam find his scooter keys! Type " + Command.HELP + " if you feel a bit lost!";
    }

    public String go(String direction) {
        Exit exit = player.getArea().getExit(direction);

        if (exit == null)
            return "There is no such exit";
        if (exit.requiresKey() && !player.getInventory().contains(exit.requiredKey()))
            return "This exit is locked and you don't have the key!";
        player.addToPath(player.getArea());
        player.setArea(exit.toArea());
        return player.getArea().getLongDescription();
    }

    public String look() {
        return player.getArea().getLongDescription();
    }

    public String observe(String itemName) {
        Item item = player.getArea().getItem(itemName) == null ? player.getItem(itemName) : null;
        return item == null ? "There is no such item in your reach!" : item.getLongDescription();
    }

    public String take(String itemName) {
        Item item = player.getArea().getItem(itemName);
        if (item == null)
        {
            return "There is no such item in this room!";
        }
        if (!item.canBePickedUp())
        {
            return "You cannot pick up this item!";
        }
        if (item.getWeight() > player.getInventorySpace())
        {
            return "You cannot carry this too!";
        }
        player.getArea().removeItem(item);
        player.addItem(item);
        return "You have picked up " + item.getName();
    }

    public String drop(String itemName) {
        Item item = player.getItem(itemName);
        if (item == null)
        {
            return "You don't have that item on you!";
        }
        player.removeItem(item);
        player.getArea().addItem(item);
        return "You have dropped your " + item.getName();
    }

    public String inventory() {
        return "Inventory (" + player.getInventoryCapacity() + "kg/" + player.getInventoryWeight() + "kg):" +
                (player.getInventory().size() != 0 ?
                        "\n" + player.getInventory().stream().map(Item::getName).collect(Collectors.joining("\n"))
                        : ""
                );
    }
}
