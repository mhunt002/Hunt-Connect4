import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.JFrame;

import org.junit.Test;

public class MyTests {
    // IO Testing
    @Test
    public void testEmptyHighScoresFile() {
        HighScores highScores;
        try {
            highScores = new HighScores("highscoretest1.txt");
            highScores.addPossibleScore(5, new ScoreEntry("Alex", "Lexia"));
            assertEquals(highScores.getScores(), "1) Alex beat Lexia in 5 turns\n");
        } catch (IOException e) {
        }
    }

    @Test
    public void testFileDoesntExist() throws IOException {
        try {
            HighScores highScores = new HighScores("idontexist.txt");
            fail("Should Thow IOException");
        } catch (FileNotFoundException e) {
            // supposed to happen
        }
    }

    @Test
    public void testAlreadyEntryForScore() {
        HighScores highScores;
        try {
            highScores = new HighScores("highscoretest2.txt");
            highScores.addPossibleScore(5, new ScoreEntry("Alex", "Lexia"));
            assertEquals(highScores.getScores(),
                    "1) Alex beat Lexia in 5 turns\n" + "2) dog beat cat in 5 turns\n");
        } catch (IOException e) {
        }
    }

    @Test
    public void testLimitsToTenEntries() {
        HighScores highScores;
        try {
            highScores = new HighScores("highscoretest3.txt");
            highScores.addPossibleScore(10, new ScoreEntry("Alex", "Lexia"));
            assertEquals(highScores.getScores(),
                    "1) dog beat caa in 5 turns\n" + "2) dog beat cab in 5 turns\n"
                            + "3) dog beat cac in 5 turns\n" + "4) dog beat cad in 5 turns\n"
                            + "5) dog beat cae in 5 turns\n" + "6) dog beat caf in 5 turns\n"
                            + "7) dog beat cag in 5 turns\n" + "8) dog beat cah in 5 turns\n"
                            + "9) dog beat cai in 5 turns\n" + "10) dog beat caj in 5 turns\n");
        } catch (IOException e) {
        }
    }

    // Win State Testing
    @Test
    public void testEmptyBoardNotWin() throws IOException {
        JFrame frame = new JFrame();
        HighScores highScores = new HighScores("highscoretestThrowAway.txt");
        GameBoard board = new GameBoard(frame, highScores);
        assertEquals(board.checkWin(), 0);
    }

    @Test
    public void testVeritcalWin() throws IOException {
        JFrame frame = new JFrame();
        HighScores highScores = new HighScores("highscoretestThrowAway.txt");
        GameBoard board = new GameBoard(frame, highScores);
        board.doMove();
        board.moveSelectionRight();
        board.doMove();
        board.moveSelectionLeft();
        board.doMove();
        board.moveSelectionRight();
        board.doMove();
        board.moveSelectionLeft();
        board.doMove();
        board.moveSelectionRight();
        board.doMove();
        board.moveSelectionLeft();
        board.doMove();
        assertTrue(board.checkWin() == 1 || board.checkWin() == 2);
    }

    @Test
    public void testHorizontalWin() throws IOException {
        JFrame frame = new JFrame();
        HighScores highScores = new HighScores("highscoretestThrowAway.txt");
        GameBoard board = new GameBoard(frame, highScores);
        board.doMove();
        board.doMove();
        board.moveSelectionRight();
        board.doMove();
        board.doMove();
        board.moveSelectionRight();
        board.doMove();
        board.doMove();
        board.moveSelectionRight();
        board.doMove();
        assertTrue(board.checkWin() == 1 || board.checkWin() == 2);
    }

    @Test
    public void testDiagnolWin1() throws IOException {
        JFrame frame = new JFrame();
        HighScores highScores = new HighScores("highscoretestThrowAway.txt");
        GameBoard board = new GameBoard(frame, highScores);
        board.doMove();
        board.moveSelectionRight();
        board.doMove();
        board.doMove();
        board.moveSelectionLeft();
        board.doMove();
        board.moveSelectionRight();
        board.moveSelectionRight();
        board.doMove();
        board.doMove();
        board.doMove();
        board.moveSelectionRight();
        board.doMove();
        board.doMove();
        board.doMove();
        board.doMove();
        assertTrue(board.checkWin() == 1 || board.checkWin() == 2);
    }

    @Test
    public void testDiagnolWin2() throws IOException {
        JFrame frame = new JFrame();
        HighScores highScores = new HighScores("highscoretestThrowAway.txt");
        GameBoard board = new GameBoard(frame, highScores);
        board.doMove();
        board.doMove();
        board.doMove();
        board.doMove();
        board.moveSelectionRight();
        board.doMove();
        board.moveSelectionRight();
        board.moveSelectionRight();
        board.doMove();
        board.moveSelectionLeft();
        board.moveSelectionLeft();
        board.doMove();
        board.doMove();
        board.moveSelectionRight();
        board.doMove();
        board.doMove();
        assertTrue(board.checkWin() == 1 || board.checkWin() == 2);
    }

    @Test
    public void testTie() throws IOException {
        JFrame frame = new JFrame();
        HighScores highScores = new HighScores("highscoretestThrowAway.txt");
        GameBoard board = new GameBoard(frame, highScores);
        board.doMove();
        board.moveSelectionRight();
        board.doMove();
        board.moveSelectionRight();
        board.doMove();
        board.moveSelectionRight();
        board.doMove();
        board.moveSelectionRight();
        board.doMove();
        board.moveSelectionRight();
        board.doMove();
        board.moveSelectionRight();
        board.doMove();
        board.doMove();
        board.moveSelectionLeft();
        board.doMove();
        board.moveSelectionLeft();
        board.doMove();
        board.moveSelectionLeft();
        board.doMove();
        board.moveSelectionLeft();
        board.doMove();
        board.moveSelectionLeft();
        board.doMove();
        board.moveSelectionLeft();
        board.doMove();
        board.moveSelectionRight();
        board.doMove();
        board.moveSelectionRight();
        board.doMove();
        board.moveSelectionRight();
        board.doMove();
        board.moveSelectionRight();
        board.doMove();
        board.moveSelectionRight();
        board.doMove();
        board.moveSelectionRight();
        board.doMove();
        board.doMove();
        board.moveSelectionLeft();
        board.doMove();
        board.moveSelectionLeft();
        board.doMove();
        board.moveSelectionLeft();
        board.doMove();
        board.moveSelectionLeft();
        board.doMove();
        board.moveSelectionLeft();
        board.doMove();
        board.moveSelectionLeft();
        board.doMove();
        board.doMove();
        board.doMove();
        board.moveSelectionRight();
        board.doMove();
        board.moveSelectionRight();
        board.doMove();
        board.moveSelectionRight();
        board.doMove();
        board.moveSelectionRight();
        board.doMove();
        board.moveSelectionRight();
        board.doMove();
        board.moveSelectionRight();
        board.doMove();
        board.doMove();
        board.moveSelectionLeft();
        board.doMove();
        board.moveSelectionLeft();
        board.doMove();
        board.moveSelectionLeft();
        board.doMove();
        board.moveSelectionLeft();
        board.doMove();
        board.moveSelectionLeft();
        board.doMove();
        board.moveSelectionLeft();
        board.doMove();
        assertTrue(board.checkWin() == 3);
    }
    
    @Test
    public void testSelectionStaysInBoard() throws IOException {
        JFrame frame = new JFrame();
        HighScores highScores = new HighScores("highscoretestThrowAway.txt");
        GameBoard board = new GameBoard(frame, highScores);
        board.doMove();
        board.moveSelectionRight();
        board.doMove();
        board.moveSelectionLeft();
        board.moveSelectionLeft();
        board.moveSelectionLeft();
        board.moveSelectionLeft();
        board.moveSelectionLeft();
        board.moveSelectionLeft();
        board.moveSelectionLeft();
        board.doMove();
        board.moveSelectionRight();
        board.doMove();
        board.moveSelectionLeft();
        board.doMove();
        board.moveSelectionRight();
        board.doMove();
        board.moveSelectionLeft();
        board.doMove();
        assertTrue(board.checkWin() == 1 || board.checkWin() == 2);
    }
    
}
