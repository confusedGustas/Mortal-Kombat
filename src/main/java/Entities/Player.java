package Entities;

public class Player {
    public final String name;
    public static int HP, M, S, Sw;

    public Player(String PlayerName) {
        this.name = PlayerName;
        HP = 3000;
        M = 35;
        S = 25;
        Sw = 20;
    }

    public String getName() {
        return name;
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
