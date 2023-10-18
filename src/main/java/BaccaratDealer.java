import java.util.ArrayList;
import java.util.Random;

public class BaccaratDealer {
    private ArrayList<Card> deck;

    public BaccaratDealer() {
        this.clearDeck();
        this.generateDeck();
    }

    public void clearDeck() {
        this.deck = new ArrayList<>();
    }

    // Standard 52 card deck
    public void generateDeck() {
        ArrayList<String> suites = new ArrayList<>();
        suites.add("Hearts");
        suites.add("Diamonds");
        suites.add("Spades");
        suites.add("Clubs");

        int values[] = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13};

        for (String s : suites) {
            for (int i = 0; i < 13; i++) {
                this.deck.add(new Card(s, values[i]));
            }
        }

        this.shuffleDeck();
    }

    // Deal 2 cards
    public ArrayList<Card> dealHand() {
        ArrayList<Card> hand = new ArrayList<>();
        hand.add(this.drawOne());
        hand.add(this.drawOne());
        return hand;
    }

    public Card drawOne() {
        // top of deck is rightmost element
        Card topCard = this.deck.get(this.deckSize() - 1);
        this.deck.remove(this.deckSize() - 1);
        return topCard;
    }

    public void shuffleDeck() {
        ArrayList<Card> shuffled = new ArrayList<>();
        Random r = new Random();    
        
        while (this.deckSize() > 0) {
            int index = r.nextInt(this.deckSize());
            shuffled.add(this.deck.get(index));
            this.deck.remove(index);
        }

        this.deck = shuffled;
    }

    public int deckSize() {
        return this.deck.size();
    }
}
