package GUI;

import Entities.Player;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class PlayerWithPlayer {
    public PlayerWithPlayer() {
        JPanel cardPanel = InitialScreen.cardPanel;
        JPanel playerWithPlayerScreen = (JPanel) cardPanel.getComponent(1);
        playerWithPlayerScreen.setLayout(null);

        Player player1 = PlayerWithPlayerName.player1;
        Player player2 = PlayerWithPlayerName.player1;

        JButton Player1ButtonA = new JButton("M");
        JButton Player1ButtonS = new JButton("S");
        JButton Player1ButtonD = new JButton("Sw");

        JButton Player2ButtonJ = new JButton("M");
        JButton Player2ButtonK = new JButton("S");
        JButton Player2ButtonL = new JButton("Sw");

        JLabel player1Name = new JLabel(player1.getName());
        JLabel player2Name = new JLabel(player2.getName());

        Player1ButtonA.setBounds(70, 100, 70, 30);
        Player1ButtonS.setBounds(140, 100, 70, 30);
        Player1ButtonD.setBounds(210, 100, 70, 30);

        Player2ButtonJ.setBounds(420, 100, 70, 30);
        Player2ButtonK.setBounds(490, 100, 70, 30);
        Player2ButtonL.setBounds(560, 100, 70, 30);

        player1Name.setBounds(140, 50, 100, 20);
        player2Name.setBounds(490, 50, 100, 20);

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
                    System.out.println("A");
                } else if (key == KeyEvent.VK_S) {
                    Player1ButtonS.doClick();
                    System.out.println("S");
                } else if (key == KeyEvent.VK_D) {
                    Player1ButtonD.doClick();
                    System.out.println("D");
                } else if (key == KeyEvent.VK_J) {
                    Player2ButtonJ.doClick();
                    System.out.println("J");
                } else if (key == KeyEvent.VK_K) {
                    Player2ButtonK.doClick();
                    System.out.println("K");
                } else if (key == KeyEvent.VK_L) {
                    Player2ButtonL.doClick();
                    System.out.println("L");
                }
            }
        });

        playerWithPlayerScreen.setFocusable(true);
        playerWithPlayerScreen.requestFocusInWindow();

        cardPanel.revalidate();
        cardPanel.repaint();
    }
}
