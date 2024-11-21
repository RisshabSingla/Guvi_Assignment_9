package adventureGame;

public class Enemy {
    private String name;
    private int health;

    public Enemy(String name, int health) {
        this.name = name;
        this.health = health;
    }

    public int getHealth() {
        return Math.max(0, health);
    }

    public void fight(Player player, String action) {
        if (action.equals("attack")) {
            health -= 20;
            System.out.println("You attack the " + name + ". Its health is now " + getHealth() + ".");
            if (health <= 0) {
                System.out.println("You defeated the " + name + "!");
                return;
            }
            player.takeDamage(10);
        } else if (action.equals("run")) {
            System.out.println("You ran away from the " + name + ".");
        }
    }

    public String getName() {
        return this.name;
    }
}

