package view;


import interface_adapters.candle_game.CandleGameViewModel;
import interface_adapters.candle_game.CandleGameViewModelListener;
import javax.swing.*;
import interface_adapters.candle_game.CandleGameController;
import interface_adapters.candle_game.EvaluateGuessController;
import interface_adapters.home.HomeViewModel;

import java.awt.event.*;
import java.util.Arrays;
import java.util.List;

import java.awt.*;

public class CandleGameView extends JPanel implements ActionListener, CandleGameViewModelListener {

    // TODO add home view model
    private final HomeViewModel homeViewModel;
    private final CandleGameViewModel candleGameViewModel;
    private EvaluateGuessController evaluateGuessController;
    private CandleGameController candleGameController;
    private final JLabel[][] attempts;
    private final JLabel attemptsRemaining;
    private final JLabel background;
    private final JLabel[] candleFlames;
    private final JLabel message;
    private final JTextField[] numberBoxes;
    private final ImageIcon brightCandle = new ImageIcon("src/images/ORANGE_FLAME.png");
    private final ImageIcon dimCandle = new ImageIcon("src/images/RED_FLAME.png");
    private final ImageIcon winMessage = new ImageIcon("src/images/WIN_MESSAGE.PNG");
    private final ImageIcon lossMessage = new ImageIcon("src/images/END_MESSAGE.PNG");
    private final JButton quitButton;
    private final JButton instructionsButton;
    private final JButton submitButton;

    /**
     * Constructs the Candle Game View
     * Initializes all it's components, and connects the View to the Controller
     */
    public CandleGameView(CandleGameViewModel candleGameViewModel, HomeViewModel homeViewModel) {
        this.homeViewModel = homeViewModel;
        this.candleGameViewModel = candleGameViewModel;
        this.candleGameViewModel.addListener(this);

        this.setLayout(null);


        //Attempts Remaining Tracker
        attemptsRemaining = new JLabel("10");
        attemptsRemaining.setBounds(75, 80, 500, 35);
        attemptsRemaining.setFont(FontManager.getWinkle(48f));
        this.add(attemptsRemaining);


        // Quit Button
        quitButton = new JButton("QUIT");
        quitButton.setBounds(10, 10, 100, 50);
        quitButton.setFont(FontManager.getWinkle(30f));
        quitButton.setBackground(Color.decode("#362514"));
        quitButton.setForeground(Color.WHITE);
        quitButton.addActionListener(this);
        this.add(quitButton);

        // Instructions Button
        instructionsButton = new JButton("?");
        instructionsButton.setBounds(10, 70, 50, 50);
        instructionsButton.setFont(FontManager.getWinkle(30f));
        instructionsButton.setBackground(Color.decode("#362514"));
        instructionsButton.setForeground(Color.WHITE);
        instructionsButton.addActionListener(this);
        this.add(instructionsButton);

        // Submit Button
        submitButton = new JButton("SUBMIT");
        submitButton.setBounds(340, 460, 120, 50);
        submitButton.setFont(FontManager.getWinkle(30f));
        submitButton.setBackground(Color.decode("#362514"));
        submitButton.setForeground(Color.WHITE);
        submitButton.addActionListener(this);
        this.add(submitButton);

        // Background
        background = new JLabel(new ImageIcon("src/images/CANDLE_GAME_BG.png"));
        background.setBounds(0, 0, 800, 600);
        this.add(background);
        // Make sure it is the bottom Layer
        this.setComponentZOrder(background, this.getComponentCount() - 1);

        // Number Boxes
        numberBoxes = new JTextField[5];
        for (int i = 0; i < numberBoxes.length; i++) {
            numberBoxes[i] = new JTextField();
            numberBoxes[i].setBounds(70 + (i * 150), 370, 60, 30);
            numberBoxes[i].setFont(FontManager.getWinkle(30f));
            numberBoxes[i].setHorizontalAlignment(JTextField.CENTER);
            this.add(numberBoxes[i]);
        }
        // Only 1 digit per box and goes to next box after you type

        for (int i =0; i < numberBoxes.length; i++) {
            final int currentIndex = i;
            numberBoxes[i].addKeyListener(new KeyAdapter() {
                @Override
                public void keyTyped(KeyEvent e) {
                    char digit = e.getKeyChar();
                    if (!Character.isDigit(digit) || !numberBoxes[currentIndex].getText().isEmpty()){
                        e.consume();
                    }

                }

                @Override
                public void keyPressed(KeyEvent e) {
                    int index = Arrays.asList(numberBoxes).indexOf(e.getSource());
                    if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
                        if (numberBoxes[index].getText().isEmpty()&& index>0){
                            numberBoxes[index-1].setText("");
                            numberBoxes[index-1].requestFocus();
                        }
                    }
                }


                @Override
                public void keyReleased(KeyEvent e) {
                    if (!numberBoxes[currentIndex].getText().isEmpty() && currentIndex < numberBoxes.length-1) {
                        numberBoxes[currentIndex+1].requestFocusInWindow();
                    }
                }
            });
        }


        // Candle Flames
        candleFlames = new JLabel[5];
        for (int i = 0; i < candleFlames.length; i++) {
            candleFlames[i] = new JLabel();
            candleFlames[i].setBounds(3 + (i * 150), 250, 150, 100);
            this.add(candleFlames[i]);
            this.setComponentZOrder(background, this.getComponentCount() - 1);
        }

        //Attempts Recorded
        attempts = new JLabel[10][5];
        for (int i = 0; i < attempts.length; i++) {
            for (int c = 0; c < attempts[i].length; c++) {
                attempts[i][c] = new JLabel();
                attempts[i][c].setBounds(720 + (c * 10), 20 + (i * 20), 200, 20);
                attempts[i][c].setFont(FontManager.getWinkle(30f));
                this.add(attempts[i][c]);
            }
        }
        this.setComponentZOrder(background, this.getComponentCount() - 1);

        // Initializes the game as soon as it opens
        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentShown(ComponentEvent e) {
                if (candleGameController != null) {
                    candleGameController.initGame();
                }
            }
        });

        // Adds the timer to the view
        TimerDisplayPanel timerPanel = new TimerDisplayPanel(homeViewModel);
        timerPanel.setBounds(285, 0, 225, 85);
        this.add(timerPanel);
        this.setComponentZOrder(background, this.getComponentCount() - 1);

        // Initialize the message when you win or lose the game
        message = new JLabel();
        message.setBounds(20, 30, 700, 500);
        this.add(message);
        this.setComponentZOrder(background, this.getComponentCount() - 1);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == instructionsButton) {
            instructionsOpen();
        } else if (e.getSource() == quitButton) {
            if (candleGameController != null) {
                candleGameController.quitGame();

            }
            // Needs to bring back to home view but will implement later
            System.out.println("Quit button clicked!");
        } else if (e.getSource() == submitButton) {
            StringBuilder guess = new StringBuilder();
            for (JTextField box : numberBoxes) {
                guess.append(box.getText());
            }
            // Needs to keep submission
            if (evaluateGuessController != null) {
                evaluateGuessController.execute(guess.toString());
            }
        }
    }


    private void instructionsOpen() {
        JFrame instructionsFrame = new JFrame("Instructions for Light it Up!");
        instructionsFrame.setMinimumSize(new java.awt.Dimension(400, 300));

        JLabel instructionsTitle = new JLabel("How to Play Light it Up", JLabel.CENTER);

        instructionsFrame.setLayout(new BorderLayout(10, 10));
        instructionsTitle.setFont(FontManager.getWinkle(20f));
        instructionsTitle.setForeground(new Color(74, 50, 35));
        instructionsTitle.setBackground(new Color(90, 27, 27));
        instructionsTitle.setBounds(100, 10, 200, 20);
        instructionsTitle.setBorder(
                BorderFactory.createEmptyBorder(15, 10, 10, 10));


        JTextArea instructions = new JTextArea(
                "GOAL\n" +
                        "   - You have 10 attempts to guess a 5 digit code on each candle.\n" +
                        "   - If you can guess the code in 10 attempts, you win the game. \n" +
                        "   - If you can not guess the code in 10 attempts or the timer ends, you lose the game. \n" +
                        "\n"+

                "Secret Code\n" +
                        "   - The code is 5 digits from 0-9. \n" +
                        "   - More than 2 of the same numbers are possible.\n " +
                        "       - Examples: 11111, 11222, 11345. \n" +
                        "\n"+

                "Submitting a Guess \n" +
                        "   - You can either click each label on the candle and type in a digit, which are numbers from 0-9 ~.\n"+
                        "       - Once you type a digit in, it automatically moves to the next box. \n" +
                        "   - You can press the backspace key on the label to delete a digit. \n" +
                        "   - Click the SUBMIT button to submit a guess\n" +
                        "\n"+

                "Other information\n" +
                        "   - Previous guesses appear on the right side of the screen\n" +
                        "   - The colour of each digit corresponds to the candle feedback\n" +
                        "   - Your attempts are displayed beside the ? in the game window\n" +
                        "   - Click ? button to view the instructions again\n" +
                        "   - Click the navigation bar to return to other windows."
        );

        instructions.setFont(FontManager.getWinkle(20f));
        instructions.setEditable(false);
        instructions.setLineWrap(true);
        instructions.setWrapStyleWord(true);
        instructions.setBackground(new Color(218, 172, 99));
        instructions.setForeground(new Color(54, 37, 20));
        instructions.setBorder(
                BorderFactory.createEmptyBorder(15, 15, 15, 15));

        JScrollPane scrollPane = new JScrollPane(instructions);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        instructionsFrame.add(instructionsTitle, BorderLayout.NORTH);
        instructionsFrame.add(scrollPane, BorderLayout.CENTER);

        instructionsFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        instructionsFrame.setLocationRelativeTo(this);
        instructionsFrame.setVisible(true);
    }

    @Override
    public void candleGameStateChanged() {
        updateCandles(candleGameViewModel.getCandleFeedback());
        attemptsRemaining.setText(String.valueOf(candleGameViewModel.getAttemptsRemaining()));
        printPastGuesses(candleGameViewModel.getPastGuesses(), candleGameViewModel.getPastFeedback());
        updateGameCondition(candleGameViewModel.getGameCondition());
    }

    private void updateCandles(List<String> feedback) {
        for (int i = 0; i < feedback.size(); i++) {
            if (feedback.get(i).equals("BRIGHT")) {
                candleFlames[i].setIcon(brightCandle);
            } else if (feedback.get(i).equals("DIM")) {
                candleFlames[i].setIcon(dimCandle);
            } else {
                candleFlames[i].setIcon(null);
            }
        }
    }

    private void printPastGuesses(List<List<String>> guesses, List<List<String>> feedback) {
        for (int i = 0; i < guesses.size(); i++) {
            for (int c = 0; c < guesses.get(i).size(); c++) {
                String digit = guesses.get(i).get(c);
                String candleType = feedback.get(i).get(c);

                if (candleType.equals("BRIGHT")) {
                    attempts[i][c].setForeground(new Color(255, 117, 24));
                } else if (candleType.equals("DIM")) {
                    attempts[i][c].setForeground(new Color(179, 27, 27));
                } else {
                    attempts[i][c].setForeground(Color.DARK_GRAY);
                }
                attempts[i][c].setText(digit);
            }
        }
    }

    private void updateGameCondition(String gameCondition) {
        if (gameCondition.equals("WON")) {
            message.setIcon(winMessage);
            // Handles removing button after condition is reached
            submitButton.setEnabled(false);
            submitButton.setVisible(false);
        }
        else if (gameCondition.equals("LOSS")) {
            message.setIcon(lossMessage);
            // Handles removing button after condition is reached
            submitButton.setEnabled(false);
            submitButton.setVisible(false);
        }
        else {
            message.setIcon(null);
            submitButton.setEnabled(true);
        }
    }

    public void setEvaluateGuessController(EvaluateGuessController evaluateGuessController) {
        this.evaluateGuessController = evaluateGuessController;
    }

    public void setCandleGameController(CandleGameController candleGameController) {
        this.candleGameController = candleGameController;
    }
}

