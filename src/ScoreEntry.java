
public class ScoreEntry implements Comparable{
    private String winner;
    private String loser;

    public ScoreEntry(String winner, String loser) {
        this.winner = winner;
        this.loser = loser;
    }

    public String getWinner() {
        return winner;
    }

    public String getLoser() {
        return loser;
    }

    public int compareTo(Object se) {
        if (!(se instanceof ScoreEntry))
            return 1;
       if(winner.compareTo(((ScoreEntry) se).getWinner()) == 0)
           return loser.compareTo(((ScoreEntry) se).getLoser());
       return winner.compareTo(((ScoreEntry) se).getWinner());
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((loser == null) ? 0 : loser.hashCode());
        result = prime * result + ((winner == null) ? 0 : winner.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        ScoreEntry other = (ScoreEntry) obj;
        if (loser == null) {
            if (other.loser != null)
                return false;
        } else if (!loser.equals(other.loser))
            return false;
        if (winner == null) {
            if (other.winner != null)
                return false;
        } else if (!winner.equals(other.winner))
            return false;
        return true;
    }

    

}
