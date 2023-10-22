package GUI;

import Entities.Player;

import javax.swing.*;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

public class PlayerWithPlayerName {
    public static Player player1;
    public static Player player2;
    public static void PlayerWithPlayerNameInitialization() {
        JPanel cardPanel = InitialScreen.cardPanel;
        JPanel playerWithPlayerName = (JPanel) cardPanel.getComponent(2);
        playerWithPlayerName.setLayout(null);

        JTextField Player1 = new JTextField(10);
        JTextField Player2 = new JTextField(10);
        JLabel Player1Label = new JLabel("Enter Player 1 username");
        JLabel Player2Label = new JLabel("Enter Player 2 username");
        JButton StartButton = new JButton("Fight!");
        JButton InitialScreenButton = new JButton("Back");

        InitialScreenButton.setBounds(10, 10, 50, 20);
        StartButton.setBounds(310, 140, 80, 20);
        Player1.setBounds(88, 70, 200, 20);
        Player2.setBounds(428, 70, 200, 20);
        Player1Label.setBounds(112, 100, 200, 10);
        Player2Label.setBounds(452, 100, 200, 10);
        setMaxLength(Player1);
        setMaxLength(Player2);
        playerWithPlayerName.add(Player1);
        playerWithPlayerName.add(Player2);
        playerWithPlayerName.add(Player1Label);
        playerWithPlayerName.add(Player2Label);
        playerWithPlayerName.add(StartButton);
        playerWithPlayerName.add(InitialScreenButton);

        InitialScreenButton.addActionListener(e -> {
            InitialScreen.cardLayout.show(InitialScreen.cardPanel, "initial");
            player1 = null;
            player2 = null;
        });

        StartButton.addActionListener(e -> {
            String name1 = Player1.getText(), name2 = Player2.getText();
            if (name1.length() != 0 && name2.length() != 0) {
                player1 = new Player(Player1.getText());
                player2 = new Player(Player2.getText());
                Player1.setText("");
                Player2.setText("");

                new PlayerWithPlayer();
                InitialScreen.cardLayout.show(InitialScreen.cardPanel, "playerWithPlayerScreen");
            } else {
                JLabel error = new JLabel("You must enter a username");
                error.setBounds(266, 170, 250, 20);
                playerWithPlayerName.add(error);
                playerWithPlayerName.revalidate();
                playerWithPlayerName.repaint();
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
