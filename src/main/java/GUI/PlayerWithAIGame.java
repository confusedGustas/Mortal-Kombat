package GUI;

import javax.swing.Timer;
import Entities.Player;
import Logic.PlayerWithAILogic;
import Logic.PlayerWithPlayerLogic;
import javax.swing.*;
import javax.swing.text.DefaultCaret;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Time;
import java.util.Random;

public class PlayerWithAIGame {
    private static final int MAX_HP = 2000;
    public static Boolean isInitialized = false;
    private static final JProgressBar hpProgressBarPlayer1 = createProgressBar();
    private static final JProgressBar hpProgressBarPlayer2 = createProgressBar();
    private static final JPanel cardPanel = InitialScreen.getCardPanel();
    private static final CardLayout cardLayout = InitialScreen.getCardLayout();
    private static final JPanel playerWithPlayerScreen = (JPanel) cardPanel.getComponent(3);
    private static final JButton PlayerButtonA = new JButton("M");
    private static final JButton PlayerButtonS = new JButton("S");
    private static final JButton PlayerButtonD = new JButton("Sw");
    private static final JButton AIButtonJ = new JButton("M");
    private static final JButton AIButtonK = new JButton("S");
    private static final JButton AIButtonL = new JButton("Sw");
    public static Timer PlayerButtonATimer = createTimer(700, PlayerButtonA);
    public static Timer PlayerButtonSTimer = createTimer(500, PlayerButtonS);
    public static Timer PlayerButtonDTimer = createTimer(300, PlayerButtonD);
    public static Timer AIButtonJTimer = createTimer(700, AIButtonJ);
    public static Timer AIButtonKTimer = createTimer(500, AIButtonK);
    public static Timer AIButtonLTimer = createTimer(300, AIButtonL);
    private static final JLabel playerName = new JLabel();
    private static final JLabel aiName = new JLabel();
    private static final JButton InitialScreenButton = new JButton("Exit");
    private static Player player;
    private static Player AI;
    private static final JFrame frame = InitialScreen.getMainFrame();
    private static boolean isListenerEnabled = false;
    private static final JLabel winner = new JLabel("");
    public static final JTextArea textArea = new JTextArea();
    private static final JScrollPane scrollPane = new JScrollPane(textArea);
    private static int countdownValue = 5;
    private static final JLabel countdownLabel = new JLabel("");
    private static Timer timer;
    private static boolean winnerSet = false;
    private static final JButton[] values = {AIButtonJ, AIButtonK, AIButtonL};
    private static final Timer[] timers = {AIButtonJTimer, AIButtonKTimer, AIButtonLTimer};
    private static final Random random = new Random();
    private static final DefaultCaret caret = (DefaultCaret) textArea.getCaret();

    public static void startCountdown() {
        timer = new Timer(1000, e -> {
            if (countdownValue > 2) {
                countdownLabel.setText("   " + (countdownValue - 2));
                countdownValue--;
            } else if (countdownValue == 2) {
                countdownLabel.setText("FIGHT");
                countdownValue--;
            } else {
                countdownLabel.setText("");
                isListenerEnabled = true;
                timer.stop();
                timer = null;
                new Thread(PlayerWithAIGame::aiLoop).start();
            }
        });

        timer.start();
    }

    public static void initializationPlayerWithAIGame() {
        caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
        startCountdown();
        initializeUIComponents();
        addActionListeners();
        frame.requestFocusInWindow();
        isInitialized = true;
    }

    public static void updateGame() {
        startCountdown();
        updateUsernames();
        winner.setText("");
        frame.requestFocusInWindow();
    }

    private static void aiLoop() {
        int randomIndex;
        while (!winnerSet && isListenerEnabled) {
            randomIndex = random.nextInt(values.length);
            ButtonPress(timers[randomIndex], values[randomIndex], AI);
        }
    }

    private static void updateUsernames() {
        player = PlayerWithAIInput.player;
        AI = PlayerWithAIInput.AI;
        player.setOpponent(AI);
        AI.setOpponent(player);
        updateHPBarValues();
        playerName.setText(player.getName());
        aiName.setText(AI.getName());
    }

    private static void initializeUIComponents() {
        playerWithPlayerScreen.setFocusable(true);
        playerWithPlayerScreen.setLayout(null);

        updateUsernames();

        playerName.setHorizontalAlignment(JLabel.CENTER);
        aiName.setHorizontalAlignment(JLabel.CENTER);

        setComponentBounds();

        playerWithPlayerScreen.add(InitialScreenButton);
        playerWithPlayerScreen.add(PlayerButtonA);
        playerWithPlayerScreen.add(PlayerButtonS);
        playerWithPlayerScreen.add(PlayerButtonD);
        playerWithPlayerScreen.add(AIButtonJ);
        playerWithPlayerScreen.add(AIButtonK);
        playerWithPlayerScreen.add(AIButtonL);
        playerWithPlayerScreen.add(playerName);
        playerWithPlayerScreen.add(aiName);
        playerWithPlayerScreen.add(hpProgressBarPlayer1);
        playerWithPlayerScreen.add(hpProgressBarPlayer2);
        playerWithPlayerScreen.add(countdownLabel);

        textArea.setEditable(false);
        textArea.setBackground(Color.WHITE);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        playerWithPlayerScreen.add(scrollPane);

        disableButtons();
        setOpaque();
    }

    private static void setComponentBounds() {
        hpProgressBarPlayer1.setBounds(78, 75, 196, 20);
        hpProgressBarPlayer2.setBounds(428, 75, 196, 20);
        InitialScreenButton.setBounds(10, 10, 140, 20);
        PlayerButtonA.setBounds(70, 100, 70, 30);
        PlayerButtonS.setBounds(140, 100, 70, 30);
        PlayerButtonD.setBounds(210, 100, 70, 30);
        AIButtonJ.setBounds(420, 100, 70, 30);
        AIButtonK.setBounds(490, 100, 70, 30);
        AIButtonL.setBounds(560, 100, 70, 30);
        playerName.setBounds(127, 50, 100, 20);
        aiName.setBounds(477, 50, 100, 20);
        scrollPane.setBounds(200, 150,300, 200);
        countdownLabel.setBounds(335, 50, 60, 20);
    }

    private static void resetPlayers() {
        player = null;
        AI = null;
        playerName.setText("");
        aiName.setText("");
        playerName.repaint();
        aiName.repaint();
        PlayerWithPlayerInput.error.setVisible(false);
    }

    private static void setOpaque() {
        PlayerButtonA.setOpaque(true);
        PlayerButtonS.setOpaque(true);
        PlayerButtonD.setOpaque(true);
        AIButtonJ.setOpaque(true);
        AIButtonK.setOpaque(true);
        AIButtonL.setOpaque(true);
    }

    private static void disableButtons() {
        PlayerButtonA.setEnabled(false);
        PlayerButtonS.setEnabled(false);
        PlayerButtonD.setEnabled(false);
        AIButtonJ.setEnabled(false);
        AIButtonK.setEnabled(false);
        AIButtonL.setEnabled(false);
    }

    private static JProgressBar createProgressBar() {
        JProgressBar progressBar = new JProgressBar(0, MAX_HP);
        progressBar.setValue(0);
        progressBar.setStringPainted(true);
        progressBar.setString("0");
        return progressBar;
    }

    public static void updateHPBarValues() {
        hpProgressBarPlayer1.setValue(player.getHP());
        hpProgressBarPlayer2.setValue(AI.getHP());
        hpProgressBarPlayer1.setString(String.valueOf(player.getHP()));
        hpProgressBarPlayer2.setString(String.valueOf(AI.getHP()));
    }

    public static Timer createTimer(int time, JButton button) {
        Timer timer = new Timer(time, e -> resetButtonColors(button));
        timer.setRepeats(false);
        return timer;
    }

    private static void addActionListeners() {
        frame.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                int key = e.getKeyCode();
                if (isListenerEnabled) {
                    switch (key) {
                        case KeyEvent.VK_A -> ButtonPress(PlayerButtonATimer, PlayerButtonA, player);
                        case KeyEvent.VK_S -> ButtonPress(PlayerButtonSTimer, PlayerButtonS, player);
                        case KeyEvent.VK_D -> ButtonPress(PlayerButtonDTimer, PlayerButtonD, player);
                    }
                }
            }
        });

        InitialScreenButton.addActionListener(e -> {
            cardLayout.show(cardPanel, "initial");
            PlayerWithPlayerLogic.jsonBuilder(player, AI);
            CurrentGameHistory.setText(player.getAttackHistory(), AI.getAttackHistory(), player, AI);
            resetPlayers();
            resetAllButtonColors();
            resetAllTimers();
            textArea.setText("");
            countdownLabel.setText("");
            countdownValue = 5;
            isListenerEnabled = false;
            winnerSet = false;
        });
    }

    private static void ButtonPress(Timer timer, JButton button, Player player) {
        if (!timer.isRunning()) {
            buttonSettings(button, player);
            timer.start();
        }
    }

    private static void buttonSettings(JButton button, Player player) {
        playerButtonAttack(player, button);
        button.setBackground(Color.RED);
    }

    private static void resetButtonColors(JButton button) {
        button.setBackground(UIManager.getColor("Button.background"));
    }

    private static void resetAllButtonColors() {
        PlayerButtonA.setBackground(UIManager.getColor("Button.background"));
        PlayerButtonS.setBackground(UIManager.getColor("Button.background"));
        PlayerButtonD.setBackground(UIManager.getColor("Button.background"));
        AIButtonJ.setBackground(UIManager.getColor("Button.background"));
        AIButtonK.setBackground(UIManager.getColor("Button.background"));
        AIButtonL.setBackground(UIManager.getColor("Button.background"));
    }

    private static void resetAllTimers() {
        PlayerButtonATimer.setDelay(0);
        PlayerButtonSTimer.setDelay(0);
        PlayerButtonDTimer.setDelay(0);
        AIButtonJTimer.setDelay(0);
        AIButtonKTimer.setDelay(0);
        AIButtonLTimer.setDelay(0);
    }

    private static void playerButtonAttack(Player player, JButton button) {
        String buttonText = button.getText();

        if (buttonText.equals("M") || buttonText.equals("S") || buttonText.equals("Sw")) {
            PlayerWithAILogic.playerAttack(player, buttonText);
            updateHPBarValues();
        }
    }

    private static void setWinnerBoundsAndComponents() {
        int labelWidth = winner.getPreferredSize().width;
        int x = (frame.getWidth() - labelWidth) / 2;
        winner.setBounds(x, 25, labelWidth + 200, 20);
        playerWithPlayerScreen.add(winner);
        winner.repaint();
    }

    public static void displayWinner(Player winner) {
        setWinnerComponents(winner);

        if (winner == player) {
            AI.setHP(0);
        } else if (winner == AI) {
            player.setHP(0);
        }
        winnerSet = true;
    }

    private static void setWinnerComponents(Player player) {
        winner.setText(player.getName() + " wins the game!");
        setWinnerBoundsAndComponents();
        isListenerEnabled = false;
    }
}
