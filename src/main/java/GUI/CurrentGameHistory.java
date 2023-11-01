package GUI;

import Entities.Player;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;

public class CurrentGameHistory {
    private static final JButton InitialScreenButton = new JButton("Back");
    private static final JPanel cardPanel = InitialScreen.getCardPanel();
    private static final CardLayout cardLayout  = InitialScreen.getCardLayout();
    private static final JPanel currentGameHistoryDisplay = (JPanel) cardPanel.getComponent(5);
    private static final JTextArea textArea = new JTextArea();
    private static final JScrollPane scrollPane = new JScrollPane(textArea);

    public static void initializationCurrentGameHistory() {
        textArea.setEditable(false);
        textArea.setBackground(Color.WHITE);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        currentGameHistoryDisplay.setLayout(null);

        setBounds();
        addObjects();
    }

    private static void setBounds() {
        scrollPane.setBounds(10, 50, 680, 300);
        InitialScreenButton.setBounds(10, 10, 140, 20);
    }

    private static void addObjects() {
        currentGameHistoryDisplay.add(scrollPane);
        currentGameHistoryDisplay.add(InitialScreenButton);
        InitialScreenButton.addActionListener(e -> cardLayout.show(cardPanel, "initial"));
    }

    public static void setText(
            LinkedList<String> player1History, LinkedList<String> player2History, Player player1, Player winner) {
        textArea.append(player1.getName() + " attack history: ");
        textArea.append(String.valueOf(player1History));
        textArea.append("\n");
        textArea.append(winner.getName() + " attack history: ");
        textArea.append(String.valueOf(player2History));
        textArea.append("\n");
        textArea.append(winner.getName() + " won the game: ");
        textArea.append("\n\n");
    }
}
