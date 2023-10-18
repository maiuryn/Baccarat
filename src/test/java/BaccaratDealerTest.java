import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.DisplayName;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import java.util.Collections;
import java.util.Comparator;

class BaccaratDealerTest {
    @Test
    void test_constructor_dealer() {
        BaccaratDealer a = new BaccaratDealer();
        ArrayList<Card> a_cards = new ArrayList<>();
        assertEquals(52, a.deckSize(), "initial deck size incorrect");

        int numCards = 0;

        while (a.deckSize() > 0) {
            Card curr = a.drawOne();
            numCards += 1;
            a_cards.add(curr);
            System.out.println(curr.getSuite() + " " + curr.getValue() + " " + curr.getId());
        }

        assertEquals(52, numCards, "number of cards incorrect");
        assertEquals(0, a.deckSize(), "deckSize incorrect");
        
        // BaccaratDealer b = new BaccaratDealer();
        // ArrayList<Card> b_cards = new ArrayList<>();
        // numCards = 0;
        // while (b.deckSize() > 0) {
        //     Card curr = b.drawOne();
        //     numCards += 1;
        //     b_cards.add(curr);
        //     System.out.println(curr.getSuite() + " " + curr.getValue() + " " + curr.getId());
        // }

        // assertEquals(52, numCards, "number of cards incorrect");
        // assertEquals(0, b.deckSize(), "deckSize incorrect");

        // Comparator<Card> comp = Comparator.comparing(Card::getSuite);
        // Collections.sort(b_cards, comp);
        // Collections.sort(a_cards, comp);

        // assertEquals(a_cards.toArray(), b_cards.toArray(), "Cards not matching");
    }

    @Test
    void test_constructor_dealer_matching_deck() {
        BaccaratDealer a = new BaccaratDealer();
        ArrayList<Card> a_cards = new ArrayList<>();
        assertEquals(52, a.deckSize(), "initial deck size incorrect");

        int numCards = 0;

        while (a.deckSize() > 0) {
            Card curr = a.drawOne();
            numCards += 1;
            a_cards.add(curr);
            System.out.println(curr.getSuite() + " " + curr.getValue() + " " + curr.getId());
        }

        assertEquals(52, numCards, "number of cards incorrect");
        assertEquals(0, a.deckSize(), "deckSize incorrect");

        BaccaratDealer b = new BaccaratDealer();
        ArrayList<Card> b_cards = new ArrayList<>();
        numCards = 0;
        while (b.deckSize() > 0) {
            Card curr = b.drawOne();
            numCards += 1;
            b_cards.add(curr);
            System.out.println(curr.getSuite() + " " + curr.getValue() + " " + curr.getId());
        }

        assertEquals(52, numCards, "number of cards incorrect");
        assertEquals(0, b.deckSize(), "deckSize incorrect");

        Comparator<Card> comp1 = Comparator.comparing(Card::getSuite);
        Collections.sort(b_cards, comp);
        Collections.sort(a_cards, comp);

        String a_suites[] = new String[52];
        int a_vals[] = new int[52];
        int a_ids[] = new int[52];

        for (int i = 0; i < 52; i++) {
            a_suites[i] = a_cards.get(i).getSuite();
            a_vals[i] = a_cards.get(i).getValue();
            a_ids[i] = a_cards.get(i).getId();
        }

        String b_suites[] = new String[52];
        int b_vals[] = new int[52];
        int b_ids[] = new int[52];

        for (int i = 0; i < 52; i++) {
            b_suites[i] = b_cards.get(i).getSuite();
            b_vals[i] = b_cards.get(i).getValue();
            b_ids[i] = b_cards.get(i).getId();
        }

        assertArrayEquals(a_suites, b_suites, "Suites not matching");
        assertArrayEquals(a_vals, b_vals, "Values not matching");
        assertArrayEquals(a_ids, b_ids, "ids not matching");
    }

    @Test
    void test_deck_size() {
        BaccaratDealer a = new BaccaratDealer();
        ArrayList<Card> a_cards = new ArrayList<>();
        assertEquals(52, a.deckSize(), "initial deck size incorrect");

        int numCards = 52;

        while (a.deckSize() > 0) {
            Card curr = a.drawOne();
            numCards -= 1;
            a_cards.add(curr);
            System.out.println(curr.getSuite() + " " + curr.getValue() + " " + curr.getId());
            assertEquals(numCards, a.deckSize(), "intermediate size incorrect");
        }

        assertEquals(0, numCards, "number of cards incorrect");
        assertEquals(0, a.deckSize(), "deckSize incorrect");
    }
    
}
