package GUI;

import Entities.Player;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class PlayerWithPlayer {
    private static final JProgressBar hpProgressBarPlayer1 = new JProgressBar();
    private static final JProgressBar hpProgressBarPlayer2 = new JProgressBar();
    private static final JPanel cardPanel = InitialScreen.cardPanel;
    private static final JPanel playerWithPlayerScreen = (JPanel) cardPanel.getComponent(1);
    private static final JButton Player1ButtonA = new JButton("M");
    private static final JButton Player1ButtonS = new JButton("S");
    private static final JButton Player1ButtonD = new JButton("Sw");
    private static final JButton Player2ButtonJ = new JButton("M");
    private static final JButton Player2ButtonK = new JButton("S");
    private static final JButton Player2ButtonL = new JButton("Sw");
    private static final JLabel player1Name = new JLabel();
    private static final JLabel player2Name = new JLabel();
    private static final JButton InitialScreenButton = new JButton("End Game");
    private static Player player1 = null;
    private static Player player2 = null;
    public static void PlayerWithPlayerGame() {
        hpProgressBarPlayer1.setStringPainted(true);
        hpProgressBarPlayer2.setStringPainted(true);
        playerWithPlayerScreen.setLayout(null);

        player1 = PlayerWithPlayerName.player1;
        player2 = PlayerWithPlayerName.player2;
        player1Name.setText(player1.getName());
        player2Name.setText(player2.getName());

        player1Name.setHorizontalAlignment(JLabel.CENTER);
        player2Name.setHorizontalAlignment(JLabel.CENTER);

        InitialScreenButton.addActionListener(e -> {
            InitialScreen.cardLayout.show(InitialScreen.cardPanel, "initial");
            player1 = null;
            player2 = null;
            player1Name.setText("");
            player2Name.setText("");
            player1Name.repaint();
            player2Name.repaint();
            PlayerWithPlayerName.error.setVisible(false);
        });

        hpProgressBarPlayer1.setBounds(78, 75, 196, 20);
        hpProgressBarPlayer2.setBounds(428, 75, 196, 20);

        InitialScreenButton.setBounds(10, 10, 140, 20);
        Player1ButtonA.setBounds(70, 100, 70, 30);
        Player1ButtonS.setBounds(140, 100, 70, 30);
        Player1ButtonD.setBounds(210, 100, 70, 30);

        Player2ButtonJ.setBounds(420, 100, 70, 30);
        Player2ButtonK.setBounds(490, 100, 70, 30);
        Player2ButtonL.setBounds(560, 100, 70, 30);

        player1Name.setBounds(127, 50, 100, 20);
        player2Name.setBounds(477, 50, 100, 20);

        playerWithPlayerScreen.add(InitialScreenButton);

        playerWithPlayerScreen.add(Player1ButtonA);
        playerWithPlayerScreen.add(Player1ButtonS);
        playerWithPlayerScreen.add(Player1ButtonD);

        playerWithPlayerScreen.add(Player2ButtonJ);
        playerWithPlayerScreen.add(Player2ButtonK);
        playerWithPlayerScreen.add(Player2ButtonL);

        playerWithPlayerScreen.add(player1Name);
        playerWithPlayerScreen.add(player2Name);

        playerWithPlayerScreen.add(hpProgressBarPlayer1);
        playerWithPlayerScreen.add(hpProgressBarPlayer2);

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
