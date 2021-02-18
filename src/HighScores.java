import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

public class HighScores {
    private Map<Integer, Set<ScoreEntry>> scores = new TreeMap<>();
    private String current;
    private String fileName;

    // Create new high scores object, parses given text file for high score
    // information
    public HighScores(String filename) throws IOException {
        this.fileName = filename;
        BufferedReader br = new BufferedReader(new FileReader(filename));
        current = br.readLine();
        while (current != null) {
            if (current != null) {
                int space = current.indexOf(" ");
                int dash = current.indexOf("-");
                int score = Integer.parseInt(current.substring(0, space));
                ScoreEntry entry = new ScoreEntry(current.substring(space + 1, dash),
                        current.substring(dash + 1));
                Set<ScoreEntry> entries = new TreeSet<>();
                entries.add(entry);
                if (!scores.containsKey(score))
                    scores.put(score, entries);
                else {
                    Set<ScoreEntry> old = scores.get(score);
                    old.add(entry);
                    scores.put(score, old);
                }
                current = br.readLine();
            }
        }
        br.close();
    }

    // Reads the file and updates internal state to reflect new scores
    public void refreshScores() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(fileName));
        current = br.readLine();
        while (current != null) {
            if (current != null) {
                int space = current.indexOf(" ");
                int dash = current.indexOf("-");
                int score = Integer.parseInt(current.substring(0, space));
                ScoreEntry entry = new ScoreEntry(current.substring(space + 1, dash),
                        current.substring(dash + 1));
                Set<ScoreEntry> entries = new TreeSet<>();
                entries.add(entry);
                if (!scores.containsKey(score))
                    scores.put(score, entries);
                else {
                    Set<ScoreEntry> old = scores.get(score);
                    old.add(entry);
                    scores.put(score, old);
                }
                current = br.readLine();
            }
        }
        br.close();
    }

    // Returns all high score entries as a string with formatting
    public String getScores() {
        String toReturn = "";
        int counter = 0;
        for (Entry<Integer, Set<ScoreEntry>> entry : scores.entrySet()) {
            int key = entry.getKey();
            Set<ScoreEntry> setOfNames = entry.getValue();
            for (ScoreEntry names : setOfNames) {
                counter++;
                toReturn = toReturn + (counter + ") " + names.getWinner() + " beat "
                        + names.getLoser() + " in " + key + " turns\n");
            }

        }
        return toReturn;
    }

    // Writes internal state of scores to the file
    public void saveScores() throws IOException {
        FileWriter fw = new FileWriter(fileName);
        for (Entry<Integer, Set<ScoreEntry>> entry : scores.entrySet()) {
            int key = entry.getKey();
            Set<ScoreEntry> setOfNames = entry.getValue();
            for (ScoreEntry names : setOfNames) {
                fw.write(key + " " + names.getWinner() + "-" + names.getLoser() + "\n");
            }

        }
        fw.close();
    }

    //Adds latest score to high scores, limits the number of entries to 10
    public void addPossibleScore(int score, ScoreEntry se) {
        if (scores.containsKey(score)) {
            Set<ScoreEntry> nameSet = scores.get(score);
            nameSet.add(se);
            scores.put(score, nameSet);
        } else {
            Set<ScoreEntry> set = new TreeSet<>();
            set.add(se);
            scores.put(score, set);
        }
        Set<Integer> allScores = scores.keySet();
        int entries = 0;
        for (int currentScore: allScores){
           entries += scores.get(currentScore).size();
        }
        if (entries > 10) {
            int highestScore = ((TreeMap<Integer, Set<ScoreEntry>>) scores).lastKey();
            TreeSet<ScoreEntry> toDecrease = (TreeSet<ScoreEntry>) scores.get(highestScore);
            if (toDecrease.size() <= 1){
                scores.remove(highestScore);
            }
            else {
                toDecrease.remove(toDecrease.last());
                scores.remove(highestScore);
                scores.put(highestScore, toDecrease);
            }
        }

        try {
            this.saveScores();
            this.refreshScores();
        } catch (IOException e) {
            System.out.println("Invalid file name");
            e.printStackTrace();
        }
    }
}
