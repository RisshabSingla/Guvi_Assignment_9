package adventureGame;

import java.util.HashMap;
import java.util.Map;

public class Room {
    private String name;
    private String description;
    private Map<String, Room> exits;
    private String item;
    private NPC npc;
    private Enemy enemy;

    public Room(String name, String description) {
        this.name = name;
        this.description = description;
        this.exits = new HashMap<>();
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public void addExit(String direction, Room room) {
        exits.put(direction, room);
    }

    public Room getExit(String direction) {
        return exits.get(direction);
    }

    public void setItem(String item) {
        this.item = item;
    }

    public void setNPC(NPC npc) {
        this.npc = npc;
    }

    public void setEnemy(Enemy enemy) {
        this.enemy = enemy;
    }

    public boolean hasNPC() {
        return npc != null;
    }

    public boolean hasEnemy() {
        return enemy != null;
    }

    public Enemy getEnemy() {
        return enemy;
    }

    public void pickupItem(Player player) {
        if (item != null) {
            System.out.println("You picked up: " + item);
            player.addItem(item);
            item = null;
        } else {
            System.out.println("There's nothing to pick up here.");
        }
    }

    public void interactWithNPC(Player player) {
        if (npc != null) {
            npc.interact(player);
        } else {
            System.out.println("There's no one to talk to here.");
        }
    }

    public void handleCombat(Player player, String action) {
        if (enemy != null) {
            if (enemy.getHealth() > 0) {
                enemy.fight(player, action);
            } else {
                System.out.println("The " + enemy.getName() + " is already defeated.");
            }
        } else {
            System.out.println("There's nothing to fight here.");
        }
    }

}
