package Entities;

public class Player {
    private final String name;
    private int HP;
    private final int M = 35;
    private final int S = 25;
    private final int Sw = 20;

    public Player(String playerName) {
        name = playerName;
        HP = 3000;
    }

    public String getName() {
        return name;
    }

    public int getHP() {
        return HP;
    }

    private void applyDamage(int damage) {
        HP -= damage;
    }

    public void attackM() {
        applyDamage(M);
    }

    public void attackS() {
        applyDamage(S);
    }

    public void attackSw() {
        applyDamage(Sw);
    }
}