import java.util.ArrayList;

public class GameHistory {
    private ArrayList<String> log;

    public GameHistory() {
        this.log = new ArrayList<>();
    }

    public void add(int playerTotal, int bankerTotal, String whoWon, String betSelection, double winnings) {
        if (betSelection == whoWon)
            this.log.add("Player Total: " + playerTotal + " Banker Total: " + bankerTotal + "\n" + whoWon + " wins\n" + "Congrats, you bet " + whoWon + "! You win $" + winnings + "!\n");
        else
            this.log.add("Player Total: " + playerTotal + " Banker Total: " + bankerTotal + "\n" + whoWon + " wins\n" + "Sorry, you bet " + betSelection + "! You lost your bet!");
    }

    public ArrayList<String> getLog() {
        return log;
    }
}
