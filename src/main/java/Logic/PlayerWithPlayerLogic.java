package Logic;

import Entities.Player;
import GUI.CurrentGameHistory;
import GUI.PlayerWithPlayerGame;
import GUI.PlayerWithPlayerInput;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import java.io.*;
import java.util.*;

public class PlayerWithPlayerLogic {
    private static Player player1;
    private static Player player2;

    public static void initializationPlayers() {
        player1 = PlayerWithPlayerInput.player1;
        player2 = PlayerWithPlayerInput.player2;
    }
    public static void playerAttack(Player player, String attack) {
        player.setAttackHistory(attack);
        if (player.getOpponent().getHP() <= player.getAttackPower(attack)) {
            endGame(player1, player2);
        } else {
            player.getOpponent().attack(attack);
        }
    }

    private static void endGame(Player player1, Player player2) {
        PlayerWithPlayerGame.displayWinner(player1);
        CurrentGameHistory.setText(player1.getAttackHistory(), player2.getAttackHistory(), player1, player2);
        jsonBuilder(player2);
    }


    private static void jsonBuilder(Player player) {
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

        if (Objects.equals(player1.getName(), player2.getName())) {
            newJsonObject.put("Player 1: " + player1.getName() + " attack history: ",
                    String.valueOf(player1.getAttackHistory()));
            newJsonObject.put("Player 2: " + player2.getName() + " attack history: ",
                    String.valueOf(player2.getAttackHistory()));
        } else {
            newJsonObject.put(player1.getName() + " attack history: ",
                    String.join(", ", player1.getAttackHistory()));
            newJsonObject.put(player2.getName() + " attack history: ",
                    String.join(", ", player2.getAttackHistory()));
        }
        newJsonObject.put("Winner: ", player.getName());
        jsonArray.add(newJsonObject);

        try {
            prettyPrinter.writeValue(jsonFile, jsonArray);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}