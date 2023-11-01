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
    private static final LinkedList<String> player1History = new LinkedList<>();
    private static final LinkedList<String> player2History = new LinkedList<>();

    public static void initializationPlayers() {
        player1 = PlayerWithPlayerInput.player1;
        player2 = PlayerWithPlayerInput.player2;
    }
    public static void player1AttackM() {
        player1History.add("M");
        if (player2.getHP() <= player1.getM()) {
            endGamePlayer1();
        } else {
            player2.attackM();
        }
    }

    public static void player1AttackS() {
        player1History.add("S");
        if (player2.getHP() <= player1.getS()) {
            endGamePlayer1();
        } else {
            player2.attackS();
        }
    }

    public static void player1AttackSw() {
        player1History.add("Sw");
        if (player2.getHP() <= player1.getSw()) {
            endGamePlayer1();
        } else {
            player2.attackSw();
        }
    }

    public static void player2AttackM() {
        player2History.add("M");
        if (player1.getHP() <= player2.getM()) {
            endGamePlayer2();
        } else {
            player1.attackM();
        }
    }

    public static void player2AttackS() {
        player2History.add("S");
        if (player1.getHP() <= player2.getS()) {
            endGamePlayer2();
        } else {
            player1.attackS();
        }
    }

    public static void player2AttackSw() {
        player2History.add("Sw");
        if (player1.getHP() <= player2.getSw()) {
            endGamePlayer2();
        } else {
            player1.attackSw();
        }
    }

    private static void endGamePlayer1() {
        PlayerWithPlayerGame.displayWinnerPlayer1(PlayerWithPlayerLogic.player1);
        CurrentGameHistory.setText(player1History, player2History, player2, player1);
        jsonBuilder(player1);
    }

    private static void endGamePlayer2() {
        PlayerWithPlayerGame.displayWinnerPlayer2(PlayerWithPlayerLogic.player2);
        CurrentGameHistory.setText(player1History, player2History, player1, player2);
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
                    String.valueOf(player1History));
            newJsonObject.put("Player 2: " + player2.getName() + " attack history: ",
                    String.valueOf(player2History));
        } else {
            newJsonObject.put(player1.getName() + " attack history: ",
                    String.join(", ", player1History));
            newJsonObject.put(player2.getName() + " attack history: ",
                    String.join(", ", player1History));
        }
        newJsonObject.put("Winner: ", player.getName());
        jsonArray.add(newJsonObject);

        try {
            prettyPrinter.writeValue(jsonFile, jsonArray);

            // Clear list for the next player
            player1History.clear();
            player2History.clear();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}