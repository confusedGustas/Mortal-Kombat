package GUI;

import Entities.Player;

import javax.swing.*;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import java.awt.*;

public class PlayerWithPlayerInput {
    public static Player player1;
    public static Player player2;
    public static JLabel error = new JLabel("You must enter a username");
    private static final JTextField Player1 = new JTextField(10);
    private static final JTextField Player2 = new JTextField(10);
    private static final JLabel Player1Label = new JLabel("Enter Player 1 username");
    private static final JLabel Player2Label = new JLabel("Enter Player 2 username");
    private static final JButton StartButton = new JButton("Fight!");
    private static final JButton InitialScreenButton = new JButton("Back");
    private static final JPanel cardPanel = InitialScreen.getCardPanel();
    private static final CardLayout cardLayout  = InitialScreen.getCardLayout();
    private static final JPanel playerWithPlayerInput = (JPanel) cardPanel.getComponent(2);
    public static void initializationPlayerWithPlayerInputUI() {
        playerWithPlayerInput.setLayout(null);

        addObjects();
        setBounds();
        addActionListener();
        setMaxLength(Player1);
        setMaxLength(Player2);
    }

    private static void addObjects() {
        playerWithPlayerInput.add(Player1);
        playerWithPlayerInput.add(Player2);
        playerWithPlayerInput.add(Player1Label);
        playerWithPlayerInput.add(Player2Label);
        playerWithPlayerInput.add(StartButton);
        playerWithPlayerInput.add(InitialScreenButton);
        playerWithPlayerInput.add(error);
    }

    private static void setBounds() {
        InitialScreenButton.setBounds(10, 10, 80, 20);
        StartButton.setBounds(310, 140, 80, 20);
        Player1.setBounds(88, 70, 200, 20);
        Player2.setBounds(428, 70, 200, 20);
        Player1Label.setBounds(112, 100, 200, 10);
        Player2Label.setBounds(452, 100, 200, 10);
    }

    private static void resetPlayers() {
        player1 = null;
        player2 = null;
        error.setVisible(false);
        Player1.setText("");
        Player2.setText("");
    }

    private static void addActionListener() {
        InitialScreenButton.addActionListener(e -> {
            cardLayout.show(cardPanel, "initial");
            resetPlayers();
        });

        StartButton.addActionListener(e -> {
            String name1 = Player1.getText(), name2 = Player2.getText();
            if (name1.length() != 0 && name2.length() != 0) {
                player1 = new Player(Player1.getText());
                player2 = new Player(Player2.getText());
                error.setVisible(false);
                Player1.setText("");
                Player2.setText("");

                if (!PlayerWithPlayerGame.isInitialized) {
                    PlayerWithPlayerGame.initializationPlayerWithPlayerGame();
                } else {
                    PlayerWithPlayerGame.updateGame();
                }
                cardLayout.show(cardPanel, "playerWithPlayerGame");
            } else {
                error.setBounds(266, 170, 250, 20);
                playerWithPlayerInput.add(error);
                error.setVisible(true);
            }
        });
    }

    private static void setMaxLength(JTextField textField) {
        AbstractDocument doc = (AbstractDocument) textField.getDocument();
        doc.setDocumentFilter(new DocumentFilter() {
            @Override
            public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs)
                    throws BadLocationException {
                int newLength = fb.getDocument().getLength() - length + text.length();
                if (newLength <= 10) {
                    super.replace(fb, offset, length, text, attrs);
                }
            }
        });
    }
}