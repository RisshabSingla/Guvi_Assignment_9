package adventureGame;

public class NPC {
    private String name;
    private String dialogue;
    private String item;

    public NPC(String name, String dialogue, String item) {
        this.name = name;
        this.dialogue = dialogue;
        this.item = item;
    }

    public void interact(Player player) {
        System.out.println(name + ": " + dialogue);
        player.addItem(item);
    }
}
