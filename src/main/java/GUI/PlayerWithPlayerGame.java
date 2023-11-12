    package GUI;

    import javax.swing.Timer;
    import Entities.Player;
    import Logic.PlayerWithPlayerLogic;
    import javax.swing.*;
    import java.awt.*;
    import java.awt.event.KeyAdapter;
    import java.awt.event.KeyEvent;

    public class PlayerWithPlayerGame {
        private static final int MAX_HP = 2000;
        public static Boolean isInitialized = false;
        private static final JProgressBar hpProgressBarPlayer1 = createProgressBar();
        private static final JProgressBar hpProgressBarPlayer2 = createProgressBar();
        private static final JPanel cardPanel = InitialScreen.getCardPanel();
        private static final CardLayout cardLayout = InitialScreen.getCardLayout();
        private static final JPanel playerWithPlayerScreen = (JPanel) cardPanel.getComponent(1);
        private static final JButton Player1ButtonA = new JButton("M");
        private static final JButton Player1ButtonS = new JButton("S");
        private static final JButton Player1ButtonD = new JButton("Sw");
        private static final JButton Player2ButtonJ = new JButton("M");
        private static final JButton Player2ButtonK = new JButton("S");
        private static final JButton Player2ButtonL = new JButton("Sw");
        public static Timer Player1ButtonATimer = createTimer(700, Player1ButtonA);
        public static Timer Player1ButtonSTimer = createTimer(500, Player1ButtonS);
        public static Timer Player1ButtonDTimer = createTimer(300, Player1ButtonD);
        public static Timer Player2ButtonJTimer = createTimer(700, Player2ButtonJ);
        public static Timer Player2ButtonKTimer = createTimer(500, Player2ButtonK);
        public static Timer Player2ButtonLTimer = createTimer(300, Player2ButtonL);
        private static final JLabel player1Name = new JLabel();
        private static final JLabel player2Name = new JLabel();
        private static final JButton InitialScreenButton = new JButton("Exit");
        private static Player player1;
        private static Player player2;
        private static final JFrame frame = InitialScreen.getMainFrame();
        private static boolean isListenerEnabled = false;
        private static final JLabel winner = new JLabel("");
        public static final JTextArea textArea = new JTextArea();
        private static final JScrollPane scrollPane = new JScrollPane(textArea);
        private static int countdownValue = 5;
        private static final JLabel countdownLabel = new JLabel("");
        private static Timer timer;

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
                }
            });

            timer.start();
        }

        public static void initializationPlayerWithPlayerGame() {
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

        private static void updateUsernames() {
            player1 = PlayerWithPlayerInput.player1;
            player2 = PlayerWithPlayerInput.player2;
            player1.setOpponent(player2);
            player2.setOpponent(player1);
            updateHPBarValues();
            player1Name.setText(player1.getName());
            player2Name.setText(player2.getName());
        }

        private static void initializeUIComponents() {
            playerWithPlayerScreen.setFocusable(true);
            playerWithPlayerScreen.setLayout(null);

            updateUsernames();

            player1Name.setHorizontalAlignment(JLabel.CENTER);
            player2Name.setHorizontalAlignment(JLabel.CENTER);

            setComponentBounds();

            playerWithPlayerScreen.add(InitialScreenButton);
            playerWithPlayerScreen.add(Player1ButtonA);
            playerWithPlayerScreen.add(Player1ButtonS);
            playerWithPlayerScreen.add(Player1ButtonD);
            playerWithPlayerScreen.add(Player2ButtonJ);
            playerWithPlayerScreen.add(Player2ButtonK);
            playerWithPlayerScreen.add(Player2ButtonL);
            playerWithPlayerScreen.add(player1Name);
            playerWithPlayerScreen.add(player2Name);
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
            Player1ButtonA.setBounds(70, 100, 70, 30);
            Player1ButtonS.setBounds(140, 100, 70, 30);
            Player1ButtonD.setBounds(210, 100, 70, 30);
            Player2ButtonJ.setBounds(420, 100, 70, 30);
            Player2ButtonK.setBounds(490, 100, 70, 30);
            Player2ButtonL.setBounds(560, 100, 70, 30);
            player1Name.setBounds(127, 50, 100, 20);
            player2Name.setBounds(477, 50, 100, 20);
            scrollPane.setBounds(200, 150,300, 200);
            countdownLabel.setBounds(335, 50, 60, 20);
        }

        private static void resetPlayers() {
            player1 = null;
            player2 = null;
            player1Name.setText("");
            player2Name.setText("");
            player1Name.repaint();
            player2Name.repaint();
            PlayerWithPlayerInput.error.setVisible(false);
        }

        private static void setOpaque() {
            Player1ButtonA.setOpaque(true);
            Player1ButtonS.setOpaque(true);
            Player1ButtonD.setOpaque(true);
            Player2ButtonJ.setOpaque(true);
            Player2ButtonK.setOpaque(true);
            Player2ButtonL.setOpaque(true);
        }

        private static void disableButtons() {
            Player1ButtonA.setEnabled(false);
            Player1ButtonS.setEnabled(false);
            Player1ButtonD.setEnabled(false);
            Player2ButtonJ.setEnabled(false);
            Player2ButtonK.setEnabled(false);
            Player2ButtonL.setEnabled(false);
        }

        private static JProgressBar createProgressBar() {
            JProgressBar progressBar = new JProgressBar(0, MAX_HP);
            progressBar.setValue(0);
            progressBar.setStringPainted(true);
            progressBar.setString("0");
            return progressBar;
        }

        public static void updateHPBarValues() {
            hpProgressBarPlayer1.setValue(player1.getHP());
            hpProgressBarPlayer2.setValue(player2.getHP());
            hpProgressBarPlayer1.setString(String.valueOf(player1.getHP()));
            hpProgressBarPlayer2.setString(String.valueOf(player2.getHP()));
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
                            case KeyEvent.VK_A -> ButtonPress(Player1ButtonATimer, Player1ButtonA, player1);
                            case KeyEvent.VK_S -> ButtonPress(Player1ButtonSTimer, Player1ButtonS, player1);
                            case KeyEvent.VK_D -> ButtonPress(Player1ButtonDTimer, Player1ButtonD, player1);
                            case KeyEvent.VK_J -> ButtonPress(Player2ButtonJTimer, Player2ButtonJ, player2);
                            case KeyEvent.VK_K -> ButtonPress(Player2ButtonKTimer, Player2ButtonK, player2);
                            case KeyEvent.VK_L -> ButtonPress(Player2ButtonLTimer, Player2ButtonL, player2);
                        }
                    }
                }
            });

            InitialScreenButton.addActionListener(e -> {
                cardLayout.show(cardPanel, "initial");
                PlayerWithPlayerLogic.jsonBuilder(player1, player2);
                CurrentGameHistory.setText(player1.getAttackHistory(), player2.getAttackHistory(), player1, player2);
                resetPlayers();
                resetAllButtonColors();
                resetAllTimers();
                textArea.setText("");
                countdownLabel.setText("");
                countdownValue = 5;
                isListenerEnabled = false;
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
            Player1ButtonA.setBackground(UIManager.getColor("Button.background"));
            Player1ButtonS.setBackground(UIManager.getColor("Button.background"));
            Player1ButtonD.setBackground(UIManager.getColor("Button.background"));
            Player2ButtonJ.setBackground(UIManager.getColor("Button.background"));
            Player2ButtonK.setBackground(UIManager.getColor("Button.background"));
            Player2ButtonL.setBackground(UIManager.getColor("Button.background"));
        }

        private static void resetAllTimers() {
            Player1ButtonATimer.setDelay(0);
            Player1ButtonSTimer.setDelay(0);
            Player1ButtonDTimer.setDelay(0);
            Player2ButtonJTimer.setDelay(0);
            Player2ButtonKTimer.setDelay(0);
            Player2ButtonLTimer.setDelay(0);
        }

        private static void playerButtonAttack(Player player, JButton button) {
            String buttonText = button.getText();

            if (buttonText.equals("M") || buttonText.equals("S") || buttonText.equals("Sw")) {
                PlayerWithPlayerLogic.playerAttack(player, buttonText);
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

        public static void displayWinner(Player player) {
            setWinnerComponents(player);

            if (player == player1) {
                player2.setHP(0);
            } else if (player == player2) {
                player1.setHP(0);
            }
        }

        private static void setWinnerComponents(Player player) {
            winner.setText(player.getName() + " wins the game!");
            setWinnerBoundsAndComponents();
            isListenerEnabled = false;
        }
    }
