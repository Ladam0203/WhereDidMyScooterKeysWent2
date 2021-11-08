package be;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

public class Player {
    private Area currentArea;
    private Stack<Area> path;
    private List<Item> inventory;
    private double inventoryCapacity = 5;

    public Player()
    {
        this.path = new Stack<>();
        this.inventory = new ArrayList<>();
    }

    public Area getArea() {
        return currentArea;
    }

    public void setArea(Area currentArea) {
        this.currentArea = currentArea;
    }

    public double getInventoryCapacity()
    {
        return inventoryCapacity;
    }

    public Stack<Area> getPath() {
        return path;
    }

    public Item getItem(String name)
    {
        return inventory.stream().filter(item -> item.getName().equals(name)).findFirst().orElse(null);
    }

    public void addItem(Item item)
    {
        inventory.add(item);
    }

    public void removeItem(Item item)
    {
        inventory.remove(item);
    }

    public double getInventoryWeight()
    {
        return inventory.stream().mapToDouble(Item::getWeight).sum();
    }

    public double getInventorySpace()
    {
        return inventoryCapacity - inventory.stream().mapToDouble(Item::getWeight).sum();
    }

    public void addToPath(Area area)
    {
        path.add(area);
    }

    public List<Item> getInventory()
    {
        return Collections.unmodifiableList(inventory);
    }
}
