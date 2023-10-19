import java.util.ArrayList;

public class BaccaratGameLogic {
    // card values 0 - 9, then no draw for last index
    private static boolean bankerDraw[][] = new boolean[][] {
        {true, true, true, true, true, true, true, true, false, true, true}, 
        {false, false, true, true, true, true, true, true, false, false, true}, 
        {false, false, false, false, true, true, true, true, false, false, true}, 
        {false, false, false, false, false, false, true, true, false, false, false}
    };


    // Player dealt hand first, so they should win if natural,
    // then Banker dealt hand, so they should win if natural.
    public String whoWon(ArrayList<Card> player, ArrayList<Card> banker) {
        int playerScore = 9 - handTotal(player);
        int bankerScore = 9 - handTotal(banker);
        return playerScore < bankerScore ? "Player" :
               bankerScore < playerScore ? "Banker" : "Draw";
    }

    public int handTotal(ArrayList<Card> hand) {
        int total = 0;

        for (Card c : hand) 
            total += c.getValue();

        return total % 10;
    }

    public boolean evaluateBankerDraw(ArrayList<Card> hand, Card playerCard) {
        int bankerTotal = handTotal(hand);
        if (bankerTotal >= 7)
            return false;
        if (bankerTotal <= 2)
            return true;
        if (playerCard == null)
            return bankerDraw[bankerTotal - 3][10];
        else
            return bankerDraw[bankerTotal - 3][playerCard.getValue()];
    }

    // The Player will go first: if hand totals to 5 or less, The Player gets one more
    // card. If the hand totals to 6 or 7 points, no more cards are given.
    public boolean evaluatePlayerDraw(ArrayList<Card> hand) {
        int playerTotal = handTotal(hand);
        if (playerTotal >= 6) 
            return false;
        else
            return true;
    }
}
