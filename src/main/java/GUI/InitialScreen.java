package GUI;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class InitialScreen {
    private static JPanel cardPanel;
    private static CardLayout cardLayout;
    private static JFrame mainFrame;
    private static JButton gameHistory;
    private static JButton button1;
    private static JButton button2;

    public static void initializeFrame() {
        mainFrame = new JFrame();
        mainFrame.setTitle("Mortal Kombat");
        mainFrame.setSize(700, 400);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int centerX = (screenSize.width - mainFrame.getWidth()) / 2;
        int centerY = (screenSize.height - mainFrame.getHeight()) / 2;
        mainFrame.setLocation(centerX, centerY);

        initializeCardLayout();
        addActionListener();

        mainFrame.add(cardPanel, BorderLayout.CENTER);
        mainFrame.setVisible(true);
    }

    private static void initializeCardLayout() {
        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);

        cardPanel.add(initializeFrameUI(), "initial");
        cardPanel.add(new JPanel(), "playerWithPlayerGame");
        cardPanel.add(new JPanel(), "playerWithPlayerInput");
        cardPanel.add(new JPanel(), "playerWithAIGame");
        cardPanel.add(new JPanel(), "playerWithAIInput");
    }

    private static JPanel initializeFrameUI() {
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(0, 0, 2, 0);

        JLabel label1 = new JLabel("Welcome to Mortal Kombat");
        JLabel label2 = new JLabel("Please select how many players will be playing");
        JLabel label3 = new JLabel("Note: If you pick 1 Player, you will be fighting against an AI");

        button1 = new JButton("1 Player");
        button2 = new JButton("2 Players");
        gameHistory = new JButton("Game History");

        buttonPanel.add(button1);
        buttonPanel.add(button2);
        buttonPanel.add(gameHistory);

        panel.add(label1, gbc);
        panel.add(label2, gbc);
        panel.add(label3, gbc);

        gbc.gridy = 100;
        panel.add(buttonPanel, gbc);

        return panel;
    }

    private static void addActionListener() {
        button1.addActionListener(e -> cardLayout.show(cardPanel, "playerWithAIInput"));
        button2.addActionListener(e -> cardLayout.show(cardPanel, "playerWithPlayerInput"));

        gameHistory.addActionListener(e -> {
            try {
                String currentDirectory = System.getProperty("user.dir") + "/src/main/java/Misc/History.json";
                File jsonFile = new File(currentDirectory);
                Desktop desktop = Desktop.getDesktop();
                desktop.open(jsonFile);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
    }

    public static JFrame getMainFrame() {
        return mainFrame;
    }

    public static CardLayout getCardLayout() {
        return cardLayout;
    }

    public static JPanel getCardPanel() {
        return cardPanel;
    }
}