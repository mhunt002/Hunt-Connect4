
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Random;

/**
 * GameBoard
 * 
 * ADD XXXXXXX
 * 
 */
@SuppressWarnings("serial")
public class GameBoard extends JPanel {

    // state of board
    // [row][col]
    // 0 = empty
    // 1 = player1
    // 2 = player2
    private int[][] boardState = new int[6][7];

    // Turn count
    private int turnCount = 1;

    // player names
    private String player1 = "Player1";
    private String player2 = "Player2";

    // which player's turn it is 1 = player1 2 = player2
    private int currentPlayer;
    // who played first
    private int startingPlayer;

    // which column is currently selected for play ranges from 0 to boardwidth-1
    private int currentSelection;
    private JFrame frame;
    
    //boolean that keeps track if its safe to read and write highscores
    private boolean highScoresAvailable;
    private HighScores highScores;

    public boolean playing = false; // whether the game is running

    //Sets up fresh game board
    public GameBoard(JFrame frame, HighScores highScores) {
        this.highScoresAvailable = highScores != null;
        this.highScores = highScores;
        
        Random r = new Random();
        turnCount = 0;
        this.frame = frame;
        if (r.nextBoolean())
            currentPlayer = 1;
        else
            currentPlayer = 2;

        startingPlayer = currentPlayer;

        // creates border around the court area, JComponent method
        setBorder(BorderFactory.createLineBorder(Color.BLACK));

        //Controls
        addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                    moveSelectionLeft();
                    repaint();
                } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                    moveSelectionRight();
                    repaint();
                } else if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                    if (validMove(currentSelection) && playing)
                        doMove();
                    repaint();
                }

            }
        });

    }

    /**
     * (Re-)set the game to its initial state.
     */
    public void reset() {

        for (int row = 0; row < 6; row++)
            for (int col = 0; col < 7; col++)
                boardState[row][col] = 0;
        turnCount = 1;
        turnLabel.setText("Turn:" + 1);
        playing = true;
        Random r = new Random();
        if (r.nextBoolean())
            currentPlayer = 1;
        else
            currentPlayer = 2;

        startingPlayer = currentPlayer;

        // Make sure that this component has the keyboard focus
        requestFocusInWindow();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        //Draw background
        g.setColor(new Color(255, 224, 75));
        g.fillRect(0, 150, 700, 600);
        g.setColor(new Color(209, 173, 0));
        g.fillRect(30, 130, 670, 20);
        
        

        // Drawing Cell Lines

        for (int i = 0; i < 8; i++) {
            g.setColor(new Color(214, 178, 0));
            g.drawLine(i * 100, 150, i * 100, 750);
            g.setColor(new Color(128, 106, 0));
            g.drawLine((i * 100) + 1, 150, (i * 100) + 1, 750);
            
        }

        for (int i = 0; i < 7; i++) {
            g.setColor(new Color(214, 178, 0));
            g.drawLine(0, (i * 100) + 150, 700, (i * 100) + 150);
            g.setColor(new Color(128, 106, 0));
            g.drawLine(0, (i * 100) + 151, 700, (i * 100) + 151);
            //Draws dividing triangles
            g.setColor(new Color(148, 122, 0));
            int xs2[] = { 0 + (i * 100), 30 + (i * 100),
                    30 + (i* 100) };
            int ys2[] = { 150, 150, 130 };
            g.fillPolygon(xs2, ys2, 3);
        }

        // Drawing Coins

        for (int row = 0; row < 6; row++) {
            for (int col = 0; col < 7; col++) {
                int val = boardState[row][col];
                if (val == 0)
                    g.setColor(Color.WHITE);
                if (val == 1)
                    g.setColor(Color.RED);
                if (val == 2)
                    g.setColor(Color.BLUE);
                g.fillOval(10 + (col * 100), 160 + (row * 100), 80, 80);
            }
        }

        // Drawing Selection Indicators
        if (currentPlayer == 1)
            g.setColor(Color.RED);
        if (currentPlayer == 2)
            g.setColor(Color.BLUE);
        g.fillOval(10 + (currentSelection * 100), 10, 80, 80);

        if (validMove(currentSelection) && playing) {
            g.setColor(Color.DARK_GRAY);
            int xs[] = { 20 + (currentSelection * 100), 50 + (currentSelection * 100),
                    80 + (currentSelection * 100) };
            int ys[] = { 120, 140, 120 };
            g.fillPolygon(xs, ys, 3);
            g.fillRect(35 + (currentSelection * 100), 98, 30, 30);
        }

    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(700, 750);
    }

    // Checks if column is valid to move in
    public boolean validMove(int col) {
        // if the top space is empty, that column will always be valid to move
        // in
        return boardState[0][col] == 0;
    }

    //Checks that selection can be moved left and moves it left
    public void moveSelectionLeft() {
        if (currentSelection != 0) {
            currentSelection--;
        }
    }

    //Checks that selection can be moved right and moves it right
    public void moveSelectionRight() {
        if (currentSelection != boardState[0].length - 1) {
            currentSelection++;
        }
    }
    
    
    //Create turn display for GUI
    final JLabel turnLabel = new JLabel("Turn: " + turnCount);
    public JLabel getTurnDisplay() {
        return turnLabel;
    }
    
    //Create pop-up for highscore button
    public void highScorePopUp(){
    JOptionPane.showMessageDialog(frame,
            highScores.getScores(),
            "High Scores", JOptionPane.PLAIN_MESSAGE);
    }

    //Drops coin into selected column, increments turn counter, checks win after every move
    public void doMove() {
        int flag = 0;
        int i = boardState.length - 1;
        while (flag == 0 && i > -1) {
            if (boardState[i][currentSelection] == 0) {
                boardState[i][currentSelection] = currentPlayer;
                flag = 1;
            }
            i--;
        }
        if (currentPlayer != startingPlayer) {
            turnCount++;
            turnLabel.setText("Turn: " + turnCount);
        }
        if (currentPlayer == 1)
            currentPlayer = 2;
        else
            currentPlayer = 1;
        repaint();

        int possibleWinner = checkWin();
        if (possibleWinner == 3) {
            playing = false;
            JOptionPane.showMessageDialog(frame,
                    "Wow, that was a long one! " + player1 + " tied " + turnCount + ""
                            + " turns!\n To play again, press reset.",
                    "Tie Game", JOptionPane.PLAIN_MESSAGE);
        }
        
        if (possibleWinner == 1) {
            playing = false;
            if (highScoresAvailable)
                highScores.addPossibleScore(turnCount, new ScoreEntry(player1, player2));
            JOptionPane.showMessageDialog(frame,
                    "Congratulations, " + player1 + " has won in " + turnCount + ""
                            + " turns!\n To play again, press reset.",
                    "Winner", JOptionPane.PLAIN_MESSAGE);
        }
        if (possibleWinner == 2) {
            playing = false;
            if (highScoresAvailable){
                highScores.addPossibleScore(turnCount, new ScoreEntry(player2, player1));
            }
            JOptionPane.showMessageDialog(frame,
                    "Congratulations, " + player2 + " has won in " + turnCount
                            + " turns!\n To play again, press reset.",
                    "Winner", JOptionPane.PLAIN_MESSAGE);
        }
    }

    //Returns turn count
    public int getTurn() {
        return turnCount;
    }

    /** Checks if the current board has a win. Checks horizontal, vertical and diagonal wins. 
     * 
     * @return 0 if no win, 1 if player1 wins, 2 if player2 wins and 3 if tie (all spaces filled)
     */
    
    public int checkWin() {
        int emptyCount = 0;
        for (int row = 0; row < 6; row++) {
            for (int col = 0; col < 7; col++) {
                if (boardState[row][col]==0)
                    emptyCount++;
            }
        }
        if(emptyCount == 0)
            return 3;
        for (int row = 0; row < 6; row++) {
            for (int col = 0; col < 7; col++) {
                int current = boardState[row][col];
                if (current != 0 && row - 3 >= 0 && boardState[row - 1][col] == current
                        && boardState[row - 2][col] == current
                        && boardState[row - 3][col] == current) {
                    return current;

                }
                if (current != 0 && row + 3 <= 5 && boardState[row + 1][col] == current
                        && boardState[row + 2][col] == current
                        && boardState[row + 3][col] == current) {
                    return current;
                }
                if (current != 0 && col + 3 <= 6 && boardState[row][col + 1] == current
                        && boardState[row][col + 2] == current
                        && boardState[row][col + 3] == current) {
                    return current;
                }
                if (current != 0 && col - 3 >= 0 && boardState[row][col - 1] == current
                        && boardState[row][col - 2] == current
                        && boardState[row][col - 3] == current) {
                    return current;
                }
                if (current != 0 && col - 3 >= 0 && row - 3 >= 0
                        && boardState[row - 1][col - 1] == current
                        && boardState[row - 2][col - 2] == current
                        && boardState[row - 3][col - 3] == current) {
                    return current;
                }
                if (current != 0 && col + 3 <= 6 && row + 3 <= 5
                        && boardState[row + 1][col + 1] == current
                        && boardState[row + 2][col + 2] == current
                        && boardState[row + 3][col + 3] == current) {
                    return current;
                }
                if (current != 0 && col + 3 <= 6 && row - 3 >= 0
                        && boardState[row - 1][col + 1] == current
                        && boardState[row - 2][col + 2] == current
                        && boardState[row - 3][col + 3] == current) {
                    return current;
                }
                if (current != 0 && col - 3 >= 0 && row + 3 <= 5
                        && boardState[row + 1][col - 1] == current
                        && boardState[row + 2][col - 2] == current
                        && boardState[row + 3][col - 3] == current) {
                    return current;
                }

            }
        }
        return 0;
    }

    //Returns the whether the game is being played or not
    public boolean getPlaying() {
        return playing;
    }

    /** Sets the names of the players to the given strings
     * 
     * @param name1 name for player1
     * @param name2 name for player2
     */
    public void setPlayerNames(String name1, String name2) {
        this.player1 = name1;
        this.player2 = name2;
    }

    //Checks if the given string is a valid name, meaning only contains letters
    public boolean isAlpha(String name) {
        return name.matches("[a-zA-Z]+");
    }
}
