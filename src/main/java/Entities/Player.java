package Entities;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;

public class Player {
    private Player opponent;
    private final String name;
    private int HP = 1200;
    private static final int M = 35;
    private static final int S = 25;
    private static final int Sw = 20;
    private static int MMSw;
    private static int SSwS;
    private static int SSS;
    private static int SMSw;
    private static final LinkedList<String> attackHistory = new LinkedList<>();

    public Player(String playerName) {
        name = playerName;

        try {
            String jsonFilePath = System.getProperty("user.dir") + "/src/main/java/Misc/Combo.json";
            File jsonFile = new File(jsonFilePath);
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(jsonFile);

            JsonNode comboAttacks = rootNode.get("ComboAttacks");

            if (comboAttacks != null && comboAttacks.isArray()) {
                MMSw = comboAttacks.get(0).get("Damage").asInt();
                SSwS = comboAttacks.get(1).get("Damage").asInt();
                SSS = comboAttacks.get(2).get("Damage").asInt();
                SMSw = comboAttacks.get(3).get("Damage").asInt();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
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

    public void setHP(int HP) {
        this.HP = HP;
    }

    public void setAttackHistory(String Attack) {
        attackHistory.add(Attack);
    }

    public LinkedList<String> getAttackHistory() {
        return attackHistory;
    }

    public int getAttackPower(String attack) {
        switch (attack) {
            case "M" -> {
                return M;
            } case "S" -> {
                return S;
            } case "Sw" -> {
                return Sw;
            } case "MMSw" -> {
                return MMSw;
            } case "SSwS" -> {
                return SSwS;
            } case "SSS" -> {
                return SSS;
            } case "SMSw" -> {
                return SMSw;
            } default -> throw new IllegalArgumentException("Invalid attack type: " + attack);
        }
    }

    public void attack(String attackType) {
        opponent.applyDamage(getAttackPower(attackType));
        setAttackHistory(attackType);
    }

    public void setOpponent(Player opponent) {
        this.opponent = opponent;
    }

    public Player getOpponent() {
        return opponent;
    }
}