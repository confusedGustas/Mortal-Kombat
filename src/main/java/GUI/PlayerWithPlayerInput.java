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
    private static final JLabel attackM = new JLabel("M attack: 35 Damage with a 0.7 Second cool down");
    private static final JLabel attackS = new JLabel("S attack: 25 Damage with a 0.5 Second cool down");
    private static final JLabel attackSw = new JLabel("Sw attack: 20 Damage with a 0.3 Second cool down");
    private static final JLabel comboAttackMMSw = new JLabel("MMSw Combo attack: 150 Damage");
    private static final JLabel comboAttackSSwS = new JLabel("SSwS Combo attack: 100 Damage");
    private static final JLabel comboAttackSSS = new JLabel("SSS Combo attack: 200 Damage");
    private static final JLabel comboAttackSMSw = new JLabel("SMSw Combo attack: 250 Damage");

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
        playerWithPlayerInput.add(attackM);
        playerWithPlayerInput.add(attackS);
        playerWithPlayerInput.add(attackSw);
        playerWithPlayerInput.add(comboAttackMMSw);
        playerWithPlayerInput.add(comboAttackSSwS);
        playerWithPlayerInput.add(comboAttackSSS);
        playerWithPlayerInput.add(comboAttackSMSw);
    }

    private static void setBounds() {
        InitialScreenButton.setBounds(10, 10, 80, 20);
        StartButton.setBounds(310, 140, 80, 20);
        Player1.setBounds(88, 70, 200, 20);
        Player2.setBounds(428, 70, 200, 20);
        Player1Label.setBounds(112, 100, 200, 10);
        Player2Label.setBounds(452, 100, 200, 10);
        attackSw.setBounds(182, 200, 326, 20);
        attackM.setBounds(185, 220, 326, 20);
        attackS.setBounds(187, 240, 326, 20);
        comboAttackMMSw.setBounds(240, 260, 220, 20);
        comboAttackSMSw.setBounds(242, 280, 220, 20);
        comboAttackSSwS.setBounds(244, 300, 220, 20);
        comboAttackSSS.setBounds(248, 320, 220, 20);
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