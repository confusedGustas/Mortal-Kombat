package Logic;

import Entities.Player;
import GUI.PlayerWithPlayerGame;
import GUI.PlayerWithPlayerInput;

public class PlayerWithPlayerLogic {
    private static Player player1;
    private static Player player2;

    public static void initializationPlayers() {
        player1 = PlayerWithPlayerInput.player1;
        player2 = PlayerWithPlayerInput.player2;
    }
    public static void player1AttackM() {
        System.out.println(player1.getName());
        if (player2.getHP() <= player1.getM()) {
            endGamePlayer1();
        } else {
            player2.attackM();
        }
    }

    public static void player1AttackS() {
        if (player2.getHP() <= player1.getS()) {
            endGamePlayer1();
        } else {
            player2.attackS();
        }
    }

    public static void player1AttackSw() {
        if (player2.getHP() <= player1.getSw()) {
            endGamePlayer1();
        } else {
            player2.attackSw();
        }
    }

    public static void player2AttackM() {
        System.out.println(player1.getName());
        if (player1.getHP() <= player2.getM()) {
            endGamePlayer2();
        } else {
            player1.attackM();
        }
    }

    public static void player2AttackS() {
        if (player1.getHP() <= player2.getS()) {
            endGamePlayer2();
        } else {
            player1.attackS();
        }
    }

    public static void player2AttackSw() {
        if (player1.getHP() <= player2.getSw()) {
            endGamePlayer2();
        } else {
            player1.attackSw();
        }
    }

    private static void endGamePlayer1() {
        PlayerWithPlayerGame.displayWinnerPlayer1(PlayerWithPlayerLogic.player1);
    }

    private static void endGamePlayer2() {
        PlayerWithPlayerGame.displayWinnerPlayer2(PlayerWithPlayerLogic.player2);
    }
}
