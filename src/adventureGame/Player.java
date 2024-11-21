package adventureGame;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private Room currentRoom;
    private List<String> inventory;
    private int health;
    private boolean hasTreasure;

    public Player() {
        this.inventory = new ArrayList<>();
        this.health = 100;
        this.hasTreasure = false;
    }

    public Room getCurrentRoom() {
        return currentRoom;
    }

    public void setCurrentRoom(Room currentRoom) {
        this.currentRoom = currentRoom;

        if (currentRoom.getName().equals("Treasure Room")) {
            hasTreasure = true;
        }
    }

    public void showInventory() {
        if (inventory.isEmpty()) {
            System.out.println("Your inventory is empty.");
        } else {
            System.out.println("Inventory: " + inventory);
        }
    }

    public List<String> getInventory() {
        return inventory;
    }

    public void addItem(String item) {
        inventory.add(item);
    }

    public void usePotion() {
        if (inventory.contains("potion")) {
            System.out.println("You use a potion and regain 20 health.");
            health = Math.min(100, health + 20);
            inventory.remove("potion");
        } else {
            System.out.println("You don't have a potion to use.");
        }
    }

    public int getHealth() {
        return health;
    }

    public void takeDamage(int damage) {
        health -= damage;
        System.out.println("You took " + damage + " damage. Health: " + health);
    }

    public boolean hasTreasure() {
        return hasTreasure;
    }
}
