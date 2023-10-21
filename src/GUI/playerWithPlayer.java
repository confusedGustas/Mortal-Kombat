package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class playerWithPlayer {
    public playerWithPlayer() {
        JPanel cardPanel = initialScreen.cardPanel;
        JPanel playerWithPlayerScreen = (JPanel) cardPanel.getComponent(1);
        playerWithPlayerScreen.setLayout(null);

        JButton Player1ButtonA = new JButton("M");
        JButton Player1ButtonS = new JButton("S");
        JButton Player1ButtonD = new JButton("Sw");

        JButton Player2ButtonJ = new JButton("M");
        JButton Player2ButtonK = new JButton("S");
        JButton Player2ButtonL = new JButton("Sw");

        Player1ButtonA.setBounds(70, 100, 70, 30);
        Player1ButtonS.setBounds(140, 100, 70, 30);
        Player1ButtonD.setBounds(210, 100, 70, 30);

        Player2ButtonJ.setBounds(420, 100, 70, 30);
        Player2ButtonK.setBounds(490, 100, 70, 30);
        Player2ButtonL.setBounds(560, 100, 70, 30);

        playerWithPlayerScreen.add(Player1ButtonA);
        playerWithPlayerScreen.add(Player1ButtonS);
        playerWithPlayerScreen.add(Player1ButtonD);

        playerWithPlayerScreen.add(Player2ButtonJ);
        playerWithPlayerScreen.add(Player2ButtonK);
        playerWithPlayerScreen.add(Player2ButtonL);

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
