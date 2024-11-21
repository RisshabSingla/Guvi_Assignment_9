package adventureGame;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Game game = new Game(scanner);

        System.out.println("\n\nWelcome to the Text Adventure Game!");
        System.out.println("Type 'help' to see the list of commands.");

        game.start();

        scanner.close();
    }
}

