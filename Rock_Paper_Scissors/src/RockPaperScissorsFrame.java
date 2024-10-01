// RockPaperScissorsFrame.java
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class RockPaperScissorsFrame extends JFrame implements ActionListener {
    private JButton rockButton, paperButton, scissorsButton, quitButton;
    private JTextField playerWinsField, computerWinsField, tiesField;
    private JTextArea resultArea;
    private int playerWins = 0, computerWins = 0, ties = 0;
    private Random random = new Random();

    public RockPaperScissorsFrame() {
        setTitle("Rock Paper Scissors Game");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Create buttons panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBorder(BorderFactory.createTitledBorder("Choose your move"));
        rockButton = new JButton("Rock", new ImageIcon("src/rock.png"));
        paperButton = new JButton("Paper", new ImageIcon("src/paper.png"));
        scissorsButton = new JButton("Scissors", new ImageIcon("src/scissors.png"));
        quitButton = new JButton("Quit");

        rockButton.addActionListener(this);
        paperButton.addActionListener(this);
        scissorsButton.addActionListener(this);
        quitButton.addActionListener(this);

        buttonPanel.add(rockButton);
        buttonPanel.add(paperButton);
        buttonPanel.add(scissorsButton);
        buttonPanel.add(quitButton);

        add(buttonPanel, BorderLayout.NORTH);

        // Create stats panel
        JPanel statsPanel = new JPanel(new GridLayout(3, 2));
        statsPanel.setBorder(BorderFactory.createTitledBorder("Stats"));
        statsPanel.add(new JLabel("Player Wins:"));
        playerWinsField = new JTextField("0");
        playerWinsField.setEditable(false);
        statsPanel.add(playerWinsField);
        statsPanel.add(new JLabel("Computer Wins:"));
        computerWinsField = new JTextField("0");
        computerWinsField.setEditable(false);
        statsPanel.add(computerWinsField);
        statsPanel.add(new JLabel("Ties:"));
        tiesField = new JTextField("0");
        tiesField.setEditable(false);
        statsPanel.add(tiesField);

        add(statsPanel, BorderLayout.WEST);

        // Create result area
        resultArea = new JTextArea();
        resultArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(resultArea);
        add(scrollPane, BorderLayout.CENTER);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == quitButton) {
            System.exit(0);
        } else {
            String playerMove = ((JButton) e.getSource()).getText();
            String computerMove = getComputerMove();
            String result = determineWinner(playerMove, computerMove);
            updateStats(result);
            displayResult(playerMove, computerMove, result);
        }
    }

    private String getComputerMove() {
        String[] moves = {"Rock", "Paper", "Scissors"};
        return moves[random.nextInt(moves.length)];
    }

    private String determineWinner(String playerMove, String computerMove) {
        if (playerMove.equals(computerMove)) {
            return "Tie";
        } else if ((playerMove.equals("Rock") && computerMove.equals("Scissors")) ||
                (playerMove.equals("Paper") && computerMove.equals("Rock")) ||
                (playerMove.equals("Scissors") && computerMove.equals("Paper"))) {
            return "Player wins";
        } else {
            return "Computer wins";
        }
    }

    private void updateStats(String result) {
        if (result.equals("Player wins")) {
            playerWins++;
            playerWinsField.setText(String.valueOf(playerWins));
        } else if (result.equals("Computer wins")) {
            computerWins++;
            computerWinsField.setText(String.valueOf(computerWins));
        } else {
            ties++;
            tiesField.setText(String.valueOf(ties));
        }
    }

    private void displayResult(String playerMove, String computerMove, String result) {
        String resultString = playerMove + " vs " + computerMove + " (" + result + ")\n";
        resultArea.append(resultString);
    }
}