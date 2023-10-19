import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import java.util.Collections;
import java.util.Comparator;


public class BaccaratGameLogicTest {
    static ArrayList<Card> p_hand;
    static ArrayList<Card> b_hand;

    @BeforeAll
    static void init() {
        p_hand = new ArrayList<>();
        b_hand = new ArrayList<>();
    }

    @BeforeEach
    void setup() {
        p_hand.clear();
        b_hand.clear();

        p_hand.add(new Card("", 1));
        p_hand.add(new Card("", 2));
        p_hand.add(new Card("", 3));

        b_hand.add(new Card("", 1));
        b_hand.add(new Card("", 2));
        b_hand.add(new Card("", 3));
    }

    @Test
    void test_hand_total() {
        assertEquals(6, BaccaratGameLogic.handTotal(p_hand));
        assertEquals(6, BaccaratGameLogic.handTotal(b_hand));
    }

    @Test
    void test_hand_total_add() {
        p_hand.add(new Card("", 10));
        assertEquals(6, BaccaratGameLogic.handTotal(p_hand));
        
        p_hand.add(new Card("", 9));
        assertEquals(5, BaccaratGameLogic.handTotal(p_hand));
        
        p_hand.add(new Card("", 9));
        assertEquals(4, BaccaratGameLogic.handTotal(p_hand));
    }

    @Test
    void test_who_won_draw() {
        assertEquals("Draw", BaccaratGameLogic.whoWon(p_hand, b_hand));
    }

    @Test
    void test_who_won() {
        p_hand.add(new Card("", 10));
        assertEquals("Draw", BaccaratGameLogic.whoWon(p_hand, b_hand));

        p_hand.add(new Card("", 9));
        assertEquals("Banker", BaccaratGameLogic.whoWon(p_hand, b_hand));

        
        p_hand.add(new Card("", 9));
        assertEquals("Player", BaccaratGameLogic.whoWon(b_hand, p_hand));

        p_hand.add(new Card("", 4));
        assertEquals("Player", BaccaratGameLogic.whoWon(p_hand, b_hand));
    }

    @Test
    void test_player_draw_true() {
        p_hand.remove(0);
        assertTrue(BaccaratGameLogic.evaluatePlayerDraw(p_hand));
    }

    @Test 
    void test_player_draw_false() {
        assertFalse(BaccaratGameLogic.evaluatePlayerDraw(p_hand));
    }

    @Test
    void test_banker_draw() {
        b_hand.remove(2);
        b_hand.remove(1);
        assertTrue(BaccaratGameLogic.evaluateBankerDraw(b_hand, new Card(null, 9)));
        b_hand.add(new Card(null, 7));
        assertFalse(BaccaratGameLogic.evaluateBankerDraw(b_hand, new Card(null, 9)));
    }

    @Test
    void test_banker_draw_selected() {
        // 6
        assertFalse(BaccaratGameLogic.evaluateBankerDraw(b_hand, new Card(null, 0)));
        assertFalse(BaccaratGameLogic.evaluateBankerDraw(b_hand, new Card(null, 1)));
        assertFalse(BaccaratGameLogic.evaluateBankerDraw(b_hand, new Card(null, 2)));
        assertFalse(BaccaratGameLogic.evaluateBankerDraw(b_hand, new Card(null, 3)));
        assertFalse(BaccaratGameLogic.evaluateBankerDraw(b_hand, new Card(null, 4)));
        assertFalse(BaccaratGameLogic.evaluateBankerDraw(b_hand, new Card(null, 5)));
        assertTrue(BaccaratGameLogic.evaluateBankerDraw(b_hand, new Card(null, 6)));
        assertTrue(BaccaratGameLogic.evaluateBankerDraw(b_hand, new Card(null, 7)));
        assertFalse(BaccaratGameLogic.evaluateBankerDraw(b_hand, new Card(null, 8)));
        assertFalse(BaccaratGameLogic.evaluateBankerDraw(b_hand, new Card(null, 9)));
        assertFalse(BaccaratGameLogic.evaluateBankerDraw(b_hand, null));

        // 5
        b_hand.clear();
        b_hand.add(new Card(null, 5));
        assertFalse(BaccaratGameLogic.evaluateBankerDraw(b_hand, new Card(null, 0)));
        assertFalse(BaccaratGameLogic.evaluateBankerDraw(b_hand, new Card(null, 1)));
        assertFalse(BaccaratGameLogic.evaluateBankerDraw(b_hand, new Card(null, 2)));
        assertFalse(BaccaratGameLogic.evaluateBankerDraw(b_hand, new Card(null, 3)));
        assertTrue(BaccaratGameLogic.evaluateBankerDraw(b_hand, new Card(null, 4)));
        assertTrue(BaccaratGameLogic.evaluateBankerDraw(b_hand, new Card(null, 5)));
        assertTrue(BaccaratGameLogic.evaluateBankerDraw(b_hand, new Card(null, 6)));
        assertTrue(BaccaratGameLogic.evaluateBankerDraw(b_hand, new Card(null, 7)));
        assertFalse(BaccaratGameLogic.evaluateBankerDraw(b_hand, new Card(null, 8)));
        assertFalse(BaccaratGameLogic.evaluateBankerDraw(b_hand, new Card(null, 9)));
        assertTrue(BaccaratGameLogic.evaluateBankerDraw(b_hand, null));

        // 4
        b_hand.clear();
        b_hand.add(new Card(null, 4));
        assertFalse(BaccaratGameLogic.evaluateBankerDraw(b_hand, new Card(null, 0)));
        assertFalse(BaccaratGameLogic.evaluateBankerDraw(b_hand, new Card(null, 1)));
        assertTrue(BaccaratGameLogic.evaluateBankerDraw(b_hand, new Card(null, 2)));
        assertTrue(BaccaratGameLogic.evaluateBankerDraw(b_hand, new Card(null, 3)));
        assertTrue(BaccaratGameLogic.evaluateBankerDraw(b_hand, new Card(null, 4)));
        assertTrue(BaccaratGameLogic.evaluateBankerDraw(b_hand, new Card(null, 5)));
        assertTrue(BaccaratGameLogic.evaluateBankerDraw(b_hand, new Card(null, 6)));
        assertTrue(BaccaratGameLogic.evaluateBankerDraw(b_hand, new Card(null, 7)));
        assertFalse(BaccaratGameLogic.evaluateBankerDraw(b_hand, new Card(null, 8)));
        assertFalse(BaccaratGameLogic.evaluateBankerDraw(b_hand, new Card(null, 9)));
        assertTrue(BaccaratGameLogic.evaluateBankerDraw(b_hand, null));

        // 3
        b_hand.clear();
        b_hand.add(new Card(null, 3));
        assertTrue(BaccaratGameLogic.evaluateBankerDraw(b_hand, new Card(null, 0)));
        assertTrue(BaccaratGameLogic.evaluateBankerDraw(b_hand, new Card(null, 1)));
        assertTrue(BaccaratGameLogic.evaluateBankerDraw(b_hand, new Card(null, 2)));
        assertTrue(BaccaratGameLogic.evaluateBankerDraw(b_hand, new Card(null, 3)));
        assertTrue(BaccaratGameLogic.evaluateBankerDraw(b_hand, new Card(null, 4)));
        assertTrue(BaccaratGameLogic.evaluateBankerDraw(b_hand, new Card(null, 5)));
        assertTrue(BaccaratGameLogic.evaluateBankerDraw(b_hand, new Card(null, 6)));
        assertTrue(BaccaratGameLogic.evaluateBankerDraw(b_hand, new Card(null, 7)));
        assertFalse(BaccaratGameLogic.evaluateBankerDraw(b_hand, new Card(null, 8)));
        assertTrue(BaccaratGameLogic.evaluateBankerDraw(b_hand, new Card(null, 9)));
        assertTrue(BaccaratGameLogic.evaluateBankerDraw(b_hand, null));
    }
}   
