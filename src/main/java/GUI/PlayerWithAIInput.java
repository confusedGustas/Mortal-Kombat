package GUI;

import Entities.Player;

import javax.swing.*;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import java.awt.*;

public class PlayerWithAIInput {
    private static final JLabel PlayerLabel = new JLabel("Enter your username");
    public static JLabel error = new JLabel("You must enter a username");
    public static Player player;
    public static Player AI;
    private static final JPanel cardPanel = InitialScreen.getCardPanel();
    private static final CardLayout cardLayout  = InitialScreen.getCardLayout();
    private static final JPanel playerWithAIInput = (JPanel) cardPanel.getComponent(4);
    private static final JTextField PlayerText = new JTextField(10);
    private static final JButton InitialScreenButton = new JButton("Back");
    private static final JLabel attackM = new JLabel("M attack: 35 Damage with a 0.7 Second cool down");
    private static final JLabel attackS = new JLabel("S attack: 25 Damage with a 0.5 Second cool down");
    private static final JLabel attackSw = new JLabel("Sw attack: 20 Damage with a 0.3 Second cool down");
    private static final JLabel comboAttackMMSw = new JLabel("MMSw Combo attack: 150 Damage");
    private static final JLabel comboAttackSSwS = new JLabel("SSwS Combo attack: 100 Damage");
    private static final JLabel comboAttackSSS = new JLabel("SSS Combo attack: 200 Damage");
    private static final JLabel comboAttackSMSw = new JLabel("SMSw Combo attack: 250 Damage");
    private static final JButton StartButton = new JButton("Fight!");

    public static void initializationPlayerWithAIInput() {
        playerWithAIInput.setLayout(null);

        addObjects();
        setBounds();
        addActionListener();
        setMaxLength();
    }

    private static void addObjects() {
        playerWithAIInput.add(error);
        playerWithAIInput.add(attackM);
        playerWithAIInput.add(attackS);
        playerWithAIInput.add(attackSw);
        playerWithAIInput.add(comboAttackMMSw);
        playerWithAIInput.add(comboAttackSSwS);
        playerWithAIInput.add(comboAttackSSS);
        playerWithAIInput.add(comboAttackSMSw);
        playerWithAIInput.add(StartButton);
        playerWithAIInput.add(PlayerLabel);
        playerWithAIInput.add(InitialScreenButton);
        playerWithAIInput.add(PlayerText);
    }

    private static void setBounds() {
        InitialScreenButton.setBounds(10, 10, 80, 20);
        StartButton.setBounds(310, 140, 80, 20);
        PlayerText.setBounds(250, 70, 200, 20);
        PlayerLabel.setBounds(285, 100, 150, 20);
        attackSw.setBounds(182, 200, 326, 20);
        attackM.setBounds(185, 220, 326, 20);
        attackS.setBounds(187, 240, 326, 20);
        comboAttackMMSw.setBounds(240, 260, 220, 20);
        comboAttackSMSw.setBounds(242, 280, 220, 20);
        comboAttackSSwS.setBounds(244, 300, 220, 20);
        comboAttackSSS.setBounds(248, 320, 220, 20);
    }

    private static void resetPlayers() {
        player = null;
        error.setVisible(false);
        PlayerText.setText("");
    }

    private static void addActionListener() {
        InitialScreenButton.addActionListener(e -> {
            cardLayout.show(cardPanel, "initial");
            resetPlayers();
        });

        StartButton.addActionListener(e -> {
            String name = PlayerText.getText();
            if (name.length() != 0) {
                player = new Player(PlayerText.getText());
                AI = new Player("AI");
                error.setVisible(false);
                PlayerText.setText("");

                if (!PlayerWithAIGame.isInitialized) {
                    PlayerWithAIGame.initializationPlayerWithAIGame();
                } else {
                    PlayerWithAIGame.updateGame();
                }
                cardLayout.show(cardPanel, "playerWithAIGame");
            } else {
                error.setBounds(266, 170, 250, 20);
                playerWithAIInput.add(error);
                error.setVisible(true);
            }
        });
    }

    private static void setMaxLength() {
        AbstractDocument doc = (AbstractDocument) PlayerWithAIInput.PlayerText.getDocument();
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
