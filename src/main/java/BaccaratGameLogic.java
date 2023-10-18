import java.util.ArrayList;

public class BaccaratGameLogic {
    public String whoWon(ArrayList<Card> player, ArrayList<Card> banker) {

        return "";
    }

    public int handTotal(ArrayList<Card> hand) {
        int total = 0;

        for (Card c : hand) {
            total += c.getValue();
        }

        return total;
    }


    public boolean evaluateBankerDraw(ArrayList<Card> hand, Card playerCard) {
        return false;
    }

    public boolean evaluatePlayerDraw(ArrayList<Card> hand) {
        return false;
    }
}
