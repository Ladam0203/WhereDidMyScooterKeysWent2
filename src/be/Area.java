package be;

import java.util.*;
import java.util.stream.Collectors;

public class Area {
    private String name;
    private String description;
    private List<Exit> exits;
    private List<Item> items;

    public Area(String name, String description)
    {
        this.name = name;
        this.description = description;
        exits = new ArrayList<>();
        items = new ArrayList<>();
    }

    public void setExit(Exit exit)
    {
        exits.add(exit);
    }
    public void addItem(Item item)
    {
        items.add(item);
    }

    /**
     * @return The description of the room.
     */
    public String getName()
    {
        return name;
    }

    public List<Exit> getExits()
    {
        return exits;
    }

    public List<Item> getItems()
    {
        return Collections.unmodifiableList(items);
    }

    public Exit getExit(String direction)
    {
        return exits.stream().filter(exit -> exit.direction().equals(direction)).findFirst().orElse(null);
    }

    public Item getItem(String name) { return items.stream().filter(item -> item.getName().equals(name)).findFirst().orElse(null);}

    public void removeItem(Item item)
    {
        items.remove(item);
    }

    public String getExitString()
    {
        return "Exits: " + String.join(" ", exits.stream().map(Exit::direction).toList());
    }

    public String getItemString()
    {
        if (items.size() != 0)
        {
            return "Items: " + items.stream().map(Item::getName).collect(Collectors.joining(" "));
        }
        return "No items in this room";
    }

    public String getLongDescription()
    {
        return "[" + getName() + "]\n" + getDescription()+ "\n" + getExitString() + "\n" + getItemString();
    }

    public String getDescription() {
        return description;
    }
}
