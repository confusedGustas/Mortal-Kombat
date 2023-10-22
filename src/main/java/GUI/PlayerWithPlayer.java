package GUI;

import Entities.Player;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class PlayerWithPlayer {
    public static void PlayerWithPlayerInitialization() {
        JPanel cardPanel = InitialScreen.cardPanel;
        JPanel playerWithPlayerScreen = (JPanel) cardPanel.getComponent(1);
        playerWithPlayerScreen.setLayout(null);

        Player player1 = PlayerWithPlayerName.player1;
        Player player2 = PlayerWithPlayerName.player2;

        JButton Player1ButtonA = new JButton("M");
        JButton Player1ButtonS = new JButton("S");
        JButton Player1ButtonD = new JButton("Sw");

        JButton Player2ButtonJ = new JButton("M");
        JButton Player2ButtonK = new JButton("S");
        JButton Player2ButtonL = new JButton("Sw");

        JLabel player1Name = new JLabel(player1.getName());
        JLabel player2Name = new JLabel(player2.getName());
        JButton InitialScreenButton = new JButton("End Game");

        InitialScreenButton.addActionListener(e -> {
            InitialScreen.cardLayout.show(InitialScreen.cardPanel, "initial");
            PlayerWithPlayerName.player1 = null;
            PlayerWithPlayerName.player2 = null;
            player1Name.setText("");
            player2Name.setText("");
            player1Name.repaint();
            player2Name.repaint();
            PlayerWithPlayerName.error.setVisible(false);
        });

        InitialScreenButton.setBounds(10, 10, 140, 20);
        Player1ButtonA.setBounds(70, 100, 70, 30);
        Player1ButtonS.setBounds(140, 100, 70, 30);
        Player1ButtonD.setBounds(210, 100, 70, 30);

        Player2ButtonJ.setBounds(420, 100, 70, 30);
        Player2ButtonK.setBounds(490, 100, 70, 30);
        Player2ButtonL.setBounds(560, 100, 70, 30);

        player1Name.setBounds(140, 50, 100, 20);
        player2Name.setBounds(490, 50, 100, 20);

        playerWithPlayerScreen.add(InitialScreenButton);

        playerWithPlayerScreen.add(Player1ButtonA);
        playerWithPlayerScreen.add(Player1ButtonS);
        playerWithPlayerScreen.add(Player1ButtonD);

        playerWithPlayerScreen.add(Player2ButtonJ);
        playerWithPlayerScreen.add(Player2ButtonK);
        playerWithPlayerScreen.add(Player2ButtonL);

        playerWithPlayerScreen.add(player1Name);
        playerWithPlayerScreen.add(player2Name);

        Player1ButtonA.setEnabled(false);
        Player1ButtonS.setEnabled(false);
        Player1ButtonD.setEnabled(false);
        Player2ButtonJ.setEnabled(false);
        Player2ButtonK.setEnabled(false);
        Player2ButtonL.setEnabled(false);

        playerWithPlayerScreen.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                int key = e.getKeyCode();

                if (key == KeyEvent.VK_A) {
                    Player1ButtonA.doClick();
                    Player1ButtonAAttack();
                } else if (key == KeyEvent.VK_S) {
                    Player1ButtonS.doClick();
                    Player1ButtonSAttack();
                } else if (key == KeyEvent.VK_D) {
                    Player1ButtonD.doClick();
                    Player1ButtonDAttack();
                } else if (key == KeyEvent.VK_J) {
                    Player2ButtonJ.doClick();
                    Player1ButtonJAttack();
                } else if (key == KeyEvent.VK_K) {
                    Player2ButtonK.doClick();
                    Player1ButtonKAttack();
                } else if (key == KeyEvent.VK_L) {
                    Player2ButtonL.doClick();
                    Player1ButtonLAttack();
                }
            }
        });

        playerWithPlayerScreen.setFocusable(true);
        playerWithPlayerScreen.requestFocusInWindow();

        cardPanel.revalidate();
        cardPanel.repaint();
    }

    public static void Player1ButtonAAttack() {

    }

    public static void Player1ButtonSAttack() {

    }

    public static void Player1ButtonDAttack() {

    }

    public static void Player1ButtonJAttack() {

    }

    public static void Player1ButtonKAttack() {

    }

    public static void Player1ButtonLAttack() {

    }
}
