package adventureGame;

import java.util.*;

public class Game {
    private Player player;
    private Map<String, Room> rooms;
    private Scanner scanner;

    public Game(Scanner scanner) {
        this.scanner = scanner;
        this.player = new Player();
        this.rooms = initializeRooms();
        player.setCurrentRoom(rooms.get("Entrance"));
    }

    public void start() {
        showWelcomeMessage();

        while (true) {
            Room currentRoom = player.getCurrentRoom();
            System.out.println("\nYou are in: " + currentRoom.getName());
            System.out.println(currentRoom.getDescription());

            if (currentRoom.hasNPC()) {
                System.out.println("There is an NPC here. You can 'talk' to them.");
            }

            if (currentRoom.hasEnemy()) {
                System.out.println("A monster is here with " + currentRoom.getEnemy().getHealth() + " health!");
            }

            System.out.print("> ");
            String command = scanner.nextLine().toLowerCase();

            if (command.equals("help")) {
                showHelp();
            } else if (command.startsWith("go ")) {
                move(command.substring(3).trim());
            } else if (command.equals("check inventory")) {
                player.showInventory();
            } else if (command.equals("talk")) {
                currentRoom.interactWithNPC(player);
            } else if (command.equals("pick up")) {
                currentRoom.pickupItem(player);
            } else if (command.equals("use potion")) {
                player.usePotion();
            } else if (command.startsWith("attack") || command.startsWith("run")) {
                currentRoom.handleCombat(player, command);
            } else if (command.equals("quit")) {
                System.out.println("Thanks for playing!");
                break;
            } else {
                System.out.println("Unknown command. Type 'help' for a list of valid commands.");
            }

            if (player.getHealth() <= 0) {
                System.out.println("Game Over! You have been defeated.");
                break;
            }

            if (player.hasTreasure()) {
                System.out.println("Congratulations! You found the treasure and won the game!");
                break;
            }
        }
    }

    private void showWelcomeMessage() {
        System.out.println("Welcome to the Text Adventure Game!");
        System.out.println("Your goal is to navigate the world, collect items, and find the treasure.");
        System.out.println("\nAvailable Commands:");
        showHelp();
    }

    private void showHelp() {
        System.out.println("Commands:");
        System.out.println(" - go [north/south/east/west]: Move between rooms.");
        System.out.println(" - check inventory: View items in your inventory.");
        System.out.println(" - talk: Interact with an NPC if available.");
        System.out.println(" - pick up: Pick up an item in the room.");
        System.out.println(" - use potion: Heal yourself using a potion.");
        System.out.println(" - attack: Fight an enemy if encountered.");
        System.out.println(" - run: Flee from a fight.");
        System.out.println(" - quit: Exit the game.");
    }

    private void move(String direction) {
        Room currentRoom = player.getCurrentRoom();
        Room nextRoom = currentRoom.getExit(direction);

        if (currentRoom.hasEnemy() && currentRoom.getEnemy().getHealth() > 0) {
            System.out.println("You cannot leave until you defeat the " + currentRoom.getEnemy().getName() + "!");
            return;
        }

        if (nextRoom != null) {
            player.setCurrentRoom(nextRoom);
        } else {
            System.out.println("You can't go that way!");
        }
    }

    private Map<String, Room> initializeRooms() {
        Map<String, Room> roomMap = new HashMap<>();

        Room entrance = new Room("Entrance", "You see paths leading north and east.");
        Room forest = new Room("Forest", "You see a key lying on the ground.");
        Room dungeon = new Room("Dungeon", "A monster is lurking here!");
        Room treasureRoom = new Room("Treasure Room", "A shining treasure chest awaits!");

        entrance.addExit("north", forest);
        entrance.addExit("east", dungeon);

        forest.addExit("south", entrance);
        forest.setItem("key");
        forest.setNPC(new NPC("Old Man", "Here, take this potion.", "potion"));

        dungeon.addExit("west", entrance);
        dungeon.addExit("north", treasureRoom);
        dungeon.setEnemy(new Enemy("Monster", 50));

        treasureRoom.addExit("south", dungeon);

        roomMap.put("Entrance", entrance);
        roomMap.put("Forest", forest);
        roomMap.put("Dungeon", dungeon);
        roomMap.put("Treasure Room", treasureRoom);

        return roomMap;
    }
}
