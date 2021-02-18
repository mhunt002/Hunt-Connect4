/**
 * CIS 120 Game HW
 * (c) University of Pennsylvania
 * @version 2.0, Mar 2013
 */

// imports necessary libraries for Java swing
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

import javax.swing.*;

/**
 * Game Main class that specifies the frame and widgets of the GUI
 */
public class Game implements Runnable {
    
    public void run() {
        // NOTE : recall that the 'final' keyword notes inmutability
        // even for local variables.

        // Top-level frame in which game components live
        final JFrame frame = new JFrame("Connect 4");
        frame.setLocation(300, 300);

        
        HighScores highScores = null;
            try {
                highScores = new HighScores("highscores.txt");
            } catch (IOException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
            
        // Main playing area
        final GameBoard board = new GameBoard(frame, highScores);
        frame.add(board, BorderLayout.CENTER);

        // Status panel- Includes buttons, turn display, high scores
        FlowLayout layout = new FlowLayout();
        final JPanel status_panel = new JPanel(layout);
        layout.setHgap(30);
        frame.add(status_panel, BorderLayout.NORTH);
        status_panel.add(board.getTurnDisplay());

        // Reset button

        // Note here that when we add an action listener to the reset
        // button, we define it as an anonymous inner class that is
        // an instance of ActionListener with its actionPerformed()
        // method overridden. When the button is pressed,
        // actionPerformed() will be called.
        final JButton reset = new JButton("Reset");
        reset.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                board.reset();
                board.repaint();
            }
        });
        

        //high scores button
        final JButton highscoresBtn = new JButton("High Scores");
        highscoresBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                board.highScorePopUp();
                board.requestFocusInWindow();
            }
        });
        //instructions button
        final JButton instructions = new JButton("Instructions");
        instructions.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(frame,
                        "Welcome to Connect 4!\nIn this game, players alternate dropping their "
                        + "colored coins into a grid from above. \nGravity affects these coins, "
                        + "as they will fall to the lowest unoccupied circle.\nThe first player to "
                        + "get 4 coins in a row, either horizontally, vertically or diagonally wins!"
                        + " \n\nTo play, use the left and right arrow keys to change which column "
                        + "the coin will be dropped in. \nAn arrow will appear if the currently "
                        + "selected column is an empty space to drop in.\nUse the space bar to "
                        + "drop your coin."
                        + "\n\n\n Support: alhaynes@seas.upenn.edu",
                        "Instructions Window", JOptionPane.PLAIN_MESSAGE);
                board.requestFocusInWindow();
            }
        });
        //assemble panel
        final JLabel names = new JLabel("");
        status_panel.add(names);
        status_panel.add(highscoresBtn);
        status_panel.add(instructions);
        status_panel.add(reset);

        // Put the frame on the screen
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        // Start game
        board.reset();
        String name1 = "Player1";
        String name2 = "Player2";

        // Get Usernames with custom pop-up
        String[] options = { "OK" };
        JPanel panel = new JPanel();
        JLabel lbl = new JLabel("Player 1, you will play as red. Please enter a valid name: ");
        JLabel lbl2 = new JLabel("Player 2, you will play as blue. Please enter a valid name: ");
        JTextField txt1 = new JTextField(15);
        JTextField txt2 = new JTextField(15);
        panel.add(lbl);
        panel.add(txt1);
        int selectedOption = JOptionPane.showOptionDialog(null, panel, "Enter Name",
                JOptionPane.NO_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);

        while (!board.isAlpha(txt1.getText())) {
            selectedOption = JOptionPane.showOptionDialog(null, panel, "Enter Name",
                    JOptionPane.NO_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
        }
        if (selectedOption == 0 && board.isAlpha(txt1.getText())) {
            name1 = txt1.getText();
        }

        panel.remove(lbl);
        panel.add(lbl2);
        panel.remove(txt1);
        panel.add(txt2);

        selectedOption = JOptionPane.showOptionDialog(null, panel, "Enter Name",
                JOptionPane.NO_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
        while (!board.isAlpha(txt2.getText())) {
            selectedOption = JOptionPane.showOptionDialog(null, panel, "Enter Name",
                    JOptionPane.NO_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
        }
        if (selectedOption == 0 && board.isAlpha(txt2.getText())) {
            name2 = txt2.getText();
        }
        names.setText(name1 + " (Red) vs. " + name2 + " (Blue)");
        board.setPlayerNames(name1, name2);

    }

    /*
     * Main method run to start and run the game Initializes the GUI elements
     * specified in Game and runs it IMPORTANT: Do NOT delete! You MUST include
     * this in the final submission of your game.
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Game());
    }
    
}
