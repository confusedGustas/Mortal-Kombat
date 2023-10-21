package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class initialScreen extends JFrame {
    public static JPanel cardPanel;
    private CardLayout cardLayout;

    public initialScreen() {
        initializeUI();
    }

    private void initializeUI() {
        this.setTitle("Mortal Kombat");
        this.setSize(700, 400);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);

        // Create the initial screen
        JPanel initialScreen = createInitialScreen();

        // Create new screen
        JPanel playerWithPlayerScreen = new JPanel();
        JPanel playerWithAIScreen = new JPanel();

        cardPanel.add(initialScreen, "initial");
        cardPanel.add(playerWithPlayerScreen, "playerWithPlayerScreen");
        cardPanel.add(playerWithAIScreen, "playerWithAIScreen");

        this.add(cardPanel, BorderLayout.CENTER);

        this.setVisible(true);
    }

    private JPanel createInitialScreen() {
        JPanel panel = new JPanel(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(0, 0, 2, 0);

        JLabel label1 = new JLabel("Welcome to Mortal Kombat");
        JLabel label2 = new JLabel("Please select how many players will be playing");
        JLabel label3 = new JLabel("Note: If you pick 1 player, you will be fighting against an AI");

        panel.add(label1, gbc);
        panel.add(label2, gbc);
        panel.add(label3, gbc);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

        JButton button1 = new JButton("1 Player");
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "playerWithAIScreen");
                playerWithAI();
            }
        });

        JButton button2 = new JButton("2 Players");
        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "playerWithPlayerScreen");
                playerWithPlayer();
            }
        });

        buttonPanel.add(button1);
        buttonPanel.add(button2);

        gbc.gridy = 100;
        panel.add(buttonPanel, gbc);

        return panel;
    }

    private void playerWithPlayer() {
        new playerWithPlayer();
    }

    private void playerWithAI() {

    }
}
