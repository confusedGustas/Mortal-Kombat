    package GUI;

    import Entities.Player;
    import javax.swing.*;
    import java.awt.*;
    import java.awt.event.KeyAdapter;
    import java.awt.event.KeyEvent;

    public class PlayerWithPlayerGame {
        private static final int MAX_HP = 3000;
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
        public static void initializationPlayerWithPlayerGame() {
            initializeUIComponents();
            addActionListeners();
            frame.requestFocusInWindow();
            isInitialized = true;
        }

        public static void updateGame() {
            updateUsernames();
            frame.requestFocusInWindow();
        }

        public static void updateUsernames() {
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

            disableButtons();
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

                    if (key == KeyEvent.VK_A) {
                        player1ButtonAttack(Player1ButtonA);
                    } else if (key == KeyEvent.VK_S) {
                        player1ButtonAttack(Player1ButtonS);
                    } else if (key == KeyEvent.VK_D) {
                        player1ButtonAttack(Player1ButtonD);
                    } else if (key == KeyEvent.VK_J) {
                        player2ButtonAttack(Player2ButtonJ);
                    } else if (key == KeyEvent.VK_K) {
                        player2ButtonAttack(Player2ButtonK);
                    } else if (key == KeyEvent.VK_L) {
                        player2ButtonAttack(Player2ButtonL);
                    }
                }
            });

            InitialScreenButton.addActionListener(e -> {
                cardLayout.show(cardPanel, "initial");
                resetPlayers();
            });
        }

        private static void player1ButtonAttack(JButton button) {
            if (button.getText().equals("M")) {
                player1.attackM();
            } else if (button.getText().equals("S")) {
                player1.attackS();
            } else if (button.getText().equals("Sw")) {
                player1.attackSw();
            }
            updateHPBarValues();
        }

        private static void player2ButtonAttack(JButton button) {
            if (button.getText().equals("M")) {
                player2.attackM();
            } else if (button.getText().equals("S")) {
                player2.attackS();
            } else if (button.getText().equals("Sw")) {
                player2.attackSw();
            }
            updateHPBarValues();
        }
    }
