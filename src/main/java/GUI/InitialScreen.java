package GUI;

import javax.net.ssl.KeyStoreBuilderParameters;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.Random;

public class InitialScreen {
    private static JPanel cardPanel;
    private static CardLayout cardLayout;
    private static JFrame mainFrame;
    private static final JButton currentGameHistory = new JButton("Current Game History");
    private static final JButton everyGameHistory = new JButton("Every Game History");
    private static final JButton button1 = new JButton("1 Player");
    private static final JButton button2 = new JButton("2 Players");
    private static final JButton exitGameButton = new JButton("Exit Game");
    private static final JButton changeColour = new JButton("Change background colour");
    private static final JLabel label1 = new JLabel("Welcome to Mortal Kombat");
    private static final JLabel label2 = new JLabel("Please select how many players will be playing");
    private static final JLabel label3 = new JLabel("If you pick 1 Player, you will be fighting against an AI");
    private static final JPanel panel = new JPanel();
    private static final Random random = new Random();
    private static int red;
    private static int green;
    private static int blue;
    private static Color randomColor;
    private static final JPanel playerWithPlayerGamePanel = new JPanel();
    private static final JPanel playerWithPlayerInputPanel = new JPanel();
    private static final JPanel playerWithAIGamePanel = new JPanel();
    private static final JPanel playerWithAIInputPanel = new JPanel();
    private static final JPanel currentGameHistoryDisplayPanel = new JPanel();
    private static final JButton clearCurrentHistory = new JButton("Clear current history");
    private static final JButton clearTotalHistory = new JButton("Clear total history");

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
        cardPanel.add(playerWithPlayerGamePanel, "playerWithPlayerGame");
        cardPanel.add(playerWithPlayerInputPanel, "playerWithPlayerInput");
        cardPanel.add(playerWithAIGamePanel, "playerWithAIGame");
        cardPanel.add(playerWithAIInputPanel , "playerWithAIInput");
        cardPanel.add(currentGameHistoryDisplayPanel, "currentGameHistoryDisplay");
    }

    private static void setComponentBounds() {
        label1.setBounds(262, 75, 175, 20);
        label2.setBounds(200, 95, 295, 20);
        label3.setBounds(175, 115, 350, 20);
        button1.setBounds(50, 150, 85, 20);
        button2.setBounds(150, 150, 85, 20);
        currentGameHistory.setBounds(250, 150, 150, 20);
        everyGameHistory.setBounds(415, 150, 150, 20);
        exitGameButton.setBounds(580, 150, 75, 20);
        changeColour.setBounds(10, 10, 200, 20);
        clearCurrentHistory.setBounds(250, 10, 200, 20);
        clearTotalHistory.setBounds(490, 10, 200, 20);
    }

    private static JPanel initializeFrameUI() {
        panel.setLayout(null);

        setComponentBounds();

        panel.add(label1);
        panel.add(label2);
        panel.add(label3);
        panel.add(button1);
        panel.add(button2);
        panel.add(everyGameHistory);
        panel.add(currentGameHistory);
        panel.add(exitGameButton);
        panel.add(changeColour);
        panel.add(clearCurrentHistory);
        panel.add(clearTotalHistory);

        return panel;
    }


    private static void addActionListener() {
        button1.addActionListener(e -> cardLayout.show(cardPanel, "playerWithAIInput"));
        button2.addActionListener(e -> cardLayout.show(cardPanel, "playerWithPlayerInput"));

        everyGameHistory.addActionListener(e -> {
            try {
                String currentDirectory = System.getProperty("user.dir") + "/src/main/java/Misc/History.json";
                File jsonFile = new File(currentDirectory);
                Desktop desktop = Desktop.getDesktop();
                desktop.open(jsonFile);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        currentGameHistory.addActionListener(e -> cardLayout.show(cardPanel, "currentGameHistoryDisplay"));

        exitGameButton.addActionListener(e -> System.exit(0));

        changeColour.addActionListener(e -> {
            red = random.nextInt(256);
            green = random.nextInt(256);
            blue = random.nextInt(256);
            randomColor = new Color(red, green, blue);
            panel.setBackground(randomColor);
            playerWithPlayerGamePanel.setBackground(randomColor);
            playerWithPlayerInputPanel.setBackground(randomColor);
            playerWithAIGamePanel.setBackground(randomColor);
            playerWithAIInputPanel.setBackground(randomColor);
            currentGameHistoryDisplayPanel.setBackground(randomColor);
        });

        clearCurrentHistory.addActionListener(e -> CurrentGameHistory.textArea.setText(""));

        clearTotalHistory.addActionListener(e -> {
            String jsonFilePath = System.getProperty("user.dir") + "/src/main/java/Misc/History.json";

            try {
                Files.write(new File(jsonFilePath).toPath(), new byte[0], StandardOpenOption.TRUNCATE_EXISTING);
                System.out.println("JSON file cleared successfully.");
            } catch (IOException exception) {
                System.err.println("Error clearing JSON file: " + exception.getMessage());
                exception.printStackTrace();
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