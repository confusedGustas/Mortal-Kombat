package GUI;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class InitialScreen extends JFrame {
    public static JPanel cardPanel;
    public static CardLayout cardLayout;

    public InitialScreen() {
        initializeUI();
    }

    private void initializeUI() {
        setTitle("Mortal Kombat");
        setSize(700, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        centerWindowOnScreen();
        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);

        createAndAddPanels();

        setVisible(true);
    }

    private void centerWindowOnScreen() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int centerX = (screenSize.width - getWidth()) / 2;
        int centerY = (screenSize.height - getHeight()) / 2;
        setLocation(centerX, centerY);
    }

    private void createAndAddPanels() {
        cardPanel.add(createInitialScreen(), "initial");
        cardPanel.add(new JPanel(), "playerWithPlayerScreen");
        cardPanel.add(new JPanel(), "playerWithPlayerName");
        cardPanel.add(new JPanel(), "playerWithAIScreen");
        cardPanel.add(new JPanel(), "playerWithAIName");
        add(cardPanel, BorderLayout.CENTER);
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

        JButton gameHistory = createButton("Game History");
        gameHistory.addActionListener(e -> openGameHistory());

        JButton button1 = createButton("1 Player");
        button1.addActionListener(e -> cardLayout.show(cardPanel, "playerWithAIName"));

        JButton button2 = createButton("2 Players");
        button2.addActionListener(e -> {
            cardLayout.show(cardPanel, "playerWithPlayerName");
            PlayerWithPlayerName.PlayerWithPlayerNameInitialization();
        });

        buttonPanel.add(button1);
        buttonPanel.add(button2);
        buttonPanel.add(gameHistory);

        gbc.gridy = 100;
        panel.add(buttonPanel, gbc);

        return panel;
    }

    private JButton createButton(String text) {
        return new JButton(text);
    }

    private void openGameHistory() {
        try {
            String currentDirectory = System.getProperty("user.dir") + "/src/main/java/Misc/History.json";
            File jsonFile = new File(currentDirectory);
            Desktop desktop = Desktop.getDesktop();
            desktop.open(jsonFile);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
}
