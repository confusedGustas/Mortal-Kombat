package Logic;

import Entities.Player;
import GUI.CurrentGameHistory;
import GUI.PlayerWithPlayerGame;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import java.io.*;
import java.util.*;

public class PlayerWithPlayerLogic {
    public static void playerAttack(Player player, String attack) {
        player.setAttackHistory(attack);
        if (player.getOpponent().getHP() <= player.getAttackPower(attack)) {
            endGame(player, player.getOpponent());
        } else {
            player.getOpponent().attack(attack);
        }
    }

    private static void endGame(Player playerWinner, Player playerLoser) {
        PlayerWithPlayerGame.displayWinner(playerWinner);
        CurrentGameHistory.setText(playerWinner.getAttackHistory(), playerLoser.getAttackHistory(),
                playerWinner, playerLoser);
        jsonBuilder(playerWinner, playerLoser);
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