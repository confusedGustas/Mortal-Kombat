package Entities;

public class Player {
    public final String name;
    private int HP;
    private final int M, S, Sw;

    public Player(String playerName) {
        this.name = playerName;
        this.HP = 3000;
        this.M = 35;
        this.S = 25;
        this.Sw = 20;
    }

    public String getName() {
        return name;
    }

    public void setHP(int HP) {
        this.HP = HP;
    }

    public int getHP() {
        return HP;
    }

    public void AttackM() {
        HP -= M;
    }

    public void AttackS() {
        HP -= S;
    }

    public void AttackSw() {
        HP -= Sw;
    }
}