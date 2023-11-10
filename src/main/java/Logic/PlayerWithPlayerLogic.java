package Logic;

import Entities.Player;
import GUI.PlayerWithPlayerGame;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import java.io.*;
import java.util.*;
import java.util.logging.LoggingPermission;

public class PlayerWithPlayerLogic {
    private static final LinkedList<Player> ObjectComboAttackHistory = new LinkedList<>();

    public static void playerAttack(Player player, String attack) {
        player.setAttackHistory(attack);
        viewAttackHistory(player, attack);
        if (player.getOpponent().getHP() <= player.getAttackPower(attack)) {
            endGame(player, player.getOpponent());
        } else {
            player.getOpponent().attack(attack);

            player.setComboHistory(attack);
            ObjectComboAttackHistory.add(player);
            comboAttack();
        }
    }

    private static void viewAttackHistory(Player player, String attack) {
        PlayerWithPlayerGame.textArea.append(player.getName() + " Attack: " + attack + " - " +
                player.getAttackPower(attack) + "\n");
    }

    private static void endGame(Player playerWinner, Player playerLoser) {
        PlayerWithPlayerGame.displayWinner(playerWinner);
        jsonBuilder(playerWinner, playerLoser);
    }

    private static void comboAttack() {
        if (ObjectComboAttackHistory.size() == 3) {
            Player firstObj = ObjectComboAttackHistory.getFirst();

            if (ObjectComboAttackHistory.stream().distinct().count() == 1) {
                String combo = String.join("", firstObj.getComboHistory());
                int firstObjHP = firstObj.getOpponent().getHP();
                switch (combo) {
                    case "MMSw" -> executeComboAttack(firstObj.getOpponent(), "MMSw");
                    case "SSwS" -> executeComboAttack(firstObj.getOpponent(),"SSwS");
                    case "SSS" -> executeComboAttack(firstObj.getOpponent(),"SSS");
                    case "SMSw" -> executeComboAttack(firstObj.getOpponent(),"SMSw");
                }

                if (firstObjHP > firstObj.getOpponent().getHP()) {
                    ObjectComboAttackHistory.clear();
                    firstObj.clearComboHistory();
                    firstObj.setAttackHistory(combo);
                    viewAttackHistory(firstObj, combo);
                } else {
                    ObjectComboAttackHistory.removeFirst();
                    firstObj.removeFirstComboHistory();
                }
            } else {
                ObjectComboAttackHistory.removeFirst();
                firstObj.removeFirstComboHistory();
            }
        }
    }

    private static void executeComboAttack(Player opponent, String attack) {
        if (opponent.getHP() - opponent.getAttackPower(attack) <= 0) {
            endGame(opponent.getOpponent(), opponent);
        } else {
            opponent.attack(attack);
        }
    }

    public static void jsonBuilder(Player playerWinner, Player playerLoser) {
        String jsonFilePath = System.getProperty("user.dir") + "/src/main/java/Misc/History.json";
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectWriter prettyPrinter = objectMapper.writerWithDefaultPrettyPrinter();

        File jsonFile = new File(jsonFilePath);
        ArrayNode jsonArray;

        if (jsonFile.exists() && jsonFile.length() > 0) {
            try {
                jsonArray = (ArrayNode) objectMapper.readTree(jsonFile);
            } catch (IOException e) {
                e.printStackTrace();
                return;
            }
        } else {
            jsonArray = objectMapper.createArrayNode();
        }

        ObjectNode newJsonObject = objectMapper.createObjectNode();

        if (Objects.equals(playerWinner.getName(), playerWinner.getOpponent().getName())) {
            newJsonObject.put("Player 1: " + playerWinner.getName() + " attack history: ",
                    String.valueOf(playerWinner.getAttackHistory()));
            newJsonObject.put("Player 2: " + playerLoser.getName() + " attack history: ",
                    String.valueOf(playerLoser.getAttackHistory()));
        } else {
            newJsonObject.put(playerWinner.getName() + " attack history: ",
                    String.join(", ", playerWinner.getAttackHistory()));
            newJsonObject.put(playerLoser.getName() + " attack history: ",
                    String.join(", ", playerLoser.getAttackHistory()));
        }

        if (playerWinner.getHP() > 0 && playerLoser.getHP() > 0) {
            newJsonObject.put("Winner: ", "none, game ended");
        } else {
            newJsonObject.put("Winner: ", playerWinner.getName());
        }
        jsonArray.add(newJsonObject);

        try {
            prettyPrinter.writeValue(jsonFile, jsonArray);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}