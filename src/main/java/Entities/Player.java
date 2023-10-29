package Entities;

public class Player {
    private final String name;
    private int HP;
    private static final int M = 35;
    private static final int S = 25;
    private static final int Sw = 20;

    public Player(String playerName) {
        name = playerName;
        HP = 100;
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

    public int getM() {
        return M;
    }

    public int getS() {
        return S;
    }

    public int getSw() {
        return Sw;
    }

    public void setHP(int HP) {
        this.HP = HP;
    }
}