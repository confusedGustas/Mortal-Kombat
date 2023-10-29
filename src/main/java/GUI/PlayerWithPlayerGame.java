    package GUI;

    import Entities.Player;
    import Logic.PlayerWithPlayerLogic;

    import javax.swing.*;
    import java.awt.*;
    import java.awt.event.KeyAdapter;
    import java.awt.event.KeyEvent;
    import java.util.Random;

    public class PlayerWithPlayerGame {
        private static final int MAX_HP = 100;
        public static Boolean isInitialized = false;
        private static final JProgressBar hpProgressBarPlayer1 = createProgressBar();
        private static final JProgressBar hpProgressBarPlayer2 = createProgressBar();
        private static final JPanel cardPanel = InitialScreen.getCardPanel();
        private static final CardLayout cardLayout = InitialScreen.getCardLayout();
        private static final JPanel playerWithPlayerScreen = (JPanel) cardPanel.getComponent(1);
        private static final JButton Player1ButtonA = createButton("M");
        private static final JButton Player1ButtonS = createButton("S");
        private static final JButton Player1ButtonD = createButton("Sw");
        private static final JButton Player2ButtonJ = createButton("M");
        private static final JButton Player2ButtonK = createButton("S");
        private static final JButton Player2ButtonL = createButton("Sw");
        private static final JLabel player1Name = new JLabel();
        private static final JLabel player2Name = new JLabel();
        private static final JButton InitialScreenButton = createButton("End Game");
        private static Player player1 = null;
        private static Player player2 = null;
        private static final JFrame frame = InitialScreen.getMainFrame();
        private static boolean keyReleased = true;
        private static boolean isListenerEnabled = true;
        private static Player currentPlayer;
        private static final JLabel turnPlayer1 = new JLabel("<- Turn   ");
        private static final JLabel turnPlayer2 = new JLabel("   Turn ->");
        private static final JLabel winner = new JLabel("");

        public static void initializationPlayerWithPlayerGame() {
            PlayerWithPlayerLogic.initializationPlayers();
            initializeUIComponents();
            addActionListeners();
            frame.requestFocusInWindow();
            isInitialized = true;

            pickPlayer();
        }

        public static void updateGame() {
            PlayerWithPlayerLogic.initializationPlayers();
            updateUsernames();
            pickPlayer();
            winner.setText("");
            isListenerEnabled = true;
            frame.requestFocusInWindow();
        }

        private static void pickPlayer() {
            currentPlayer = new Random().nextBoolean() ? player1 : player2;
            toggleTurn();
        }

        private static void toggleTurn() {
            if (currentPlayer == player1) {
                turnPlayer2.setVisible(false);
                turnPlayer1.setVisible(true);
            } else {
                turnPlayer1.setVisible(false);
                turnPlayer2.setVisible(true);
            }
        }

        private static void updateUsernames() {
            player1 = PlayerWithPlayerInput.player1;
            player2 = PlayerWithPlayerInput.player2;
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
            playerWithPlayerScreen.add(turnPlayer1);
            playerWithPlayerScreen.add(turnPlayer2);

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
            turnPlayer1.setBounds(313, 75, 80, 20);
            turnPlayer2.setBounds(323, 75, 80, 20);
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

        private static JButton createButton(String text) {
            return new JButton(text);
        }

        private static void updateHPBarValues() {
            hpProgressBarPlayer1.setValue(player1.getHP());
            hpProgressBarPlayer2.setValue(player2.getHP());
            hpProgressBarPlayer1.setString(String.valueOf(player1.getHP()));
            hpProgressBarPlayer2.setString(String.valueOf(player2.getHP()));
        }

        private static void addActionListeners() {
            frame.addKeyListener(new KeyAdapter() {
                @Override
                public void keyPressed(KeyEvent e) {
                    int key = e.getKeyCode();

                    if (keyReleased && isListenerEnabled) {
                        if (currentPlayer == player1) {
                            if (key == KeyEvent.VK_A) {
                                changeButtonColor(Player1ButtonA, Color.RED);
                                player1ButtonAttack(Player1ButtonA);
                            } else if (key == KeyEvent.VK_S) {
                                changeButtonColor(Player1ButtonS, Color.RED);
                                player1ButtonAttack(Player1ButtonS);
                            } else if (key == KeyEvent.VK_D) {
                                changeButtonColor(Player1ButtonD, Color.RED);
                                player1ButtonAttack(Player1ButtonD);
                            }
                        } else if (currentPlayer == player2) {
                            if (key == KeyEvent.VK_J) {
                                changeButtonColor(Player2ButtonJ, Color.GREEN);
                                player2ButtonAttack(Player2ButtonJ);
                            } else if (key == KeyEvent.VK_K) {
                                changeButtonColor(Player2ButtonK, Color.GREEN);
                                player2ButtonAttack(Player2ButtonK);
                            } else if (key == KeyEvent.VK_L) {
                                changeButtonColor(Player2ButtonL, Color.GREEN);
                                player2ButtonAttack(Player2ButtonL);
                            }
                        }
                        keyReleased = false;
                    }
                }

                @Override
                public void keyReleased(KeyEvent e) {
                    keyReleased = true;
                    resetButtonColors();
                }
            });

            InitialScreenButton.addActionListener(e -> {
                cardLayout.show(cardPanel, "initial");
                resetPlayers();
            });
        }

        private static void togglePlayer() {
            if (currentPlayer == player1) {
                currentPlayer = player2;
            } else {
                currentPlayer = player1;
            }
        }

        private static void changeButtonColor(JButton button, Color color) {
            button.setBackground(color);
            togglePlayer();
            toggleTurn();
        }

        private static void resetButtonColors() {
            Player1ButtonA.setBackground(UIManager.getColor("Button.background"));
            Player1ButtonS.setBackground(UIManager.getColor("Button.background"));
            Player1ButtonD.setBackground(UIManager.getColor("Button.background"));
            Player2ButtonJ.setBackground(UIManager.getColor("Button.background"));
            Player2ButtonK.setBackground(UIManager.getColor("Button.background"));
            Player2ButtonL.setBackground(UIManager.getColor("Button.background"));
        }

        private static void player1ButtonAttack(JButton button) {
            if (button.getText().equals("M")) {
                PlayerWithPlayerLogic.player1AttackM();
            } else if (button.getText().equals("S")) {
                PlayerWithPlayerLogic.player1AttackS();
            } else if (button.getText().equals("Sw")) {
                PlayerWithPlayerLogic.player1AttackSw();
            }
            updateHPBarValues();
        }

        private static void player2ButtonAttack(JButton button) {
            if (button.getText().equals("M")) {
                PlayerWithPlayerLogic.player2AttackM();
            } else if (button.getText().equals("S")) {
                PlayerWithPlayerLogic.player2AttackS();
            } else if (button.getText().equals("Sw")) {
                PlayerWithPlayerLogic.player2AttackSw();
            }
            updateHPBarValues();
        }

        private static void setWinnerBoundsAndComponents() {
            int labelWidth = winner.getPreferredSize().width;
            int x = (frame.getWidth() - labelWidth) / 2;
            winner.setBounds(x, 25, labelWidth + 200, 20);
            playerWithPlayerScreen.add(winner);
            winner.repaint();
        }

        public static void displayWinnerPlayer1(Player Player) {
            setWinnerComponents(Player);
            player2.setHP(0);
            turnPlayer2.setVisible(false);
        }

        public static void displayWinnerPlayer2(Player Player) {
            setWinnerComponents(Player);
            player1.setHP(0);
            turnPlayer1.setVisible(false);
        }

        private static void setWinnerComponents(Player player) {
            winner.setText(player.getName() + " wins the game!");
            setWinnerBoundsAndComponents();
            isListenerEnabled = false;
        }
    }
