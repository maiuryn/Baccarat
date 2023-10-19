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
    static BaccaratGameLogic l;

    static ArrayList<Card> p_hand;
    static ArrayList<Card> b_hand;

    @BeforeAll
    static void init() {
        l = new BaccaratGameLogic();
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
        assertEquals(6, l.handTotal(p_hand));
        assertEquals(6, l.handTotal(b_hand));
    }

    @Test
    void test_hand_total_add() {
        p_hand.add(new Card("", 10));
        assertEquals(6, l.handTotal(p_hand));
        
        p_hand.add(new Card("", 9));
        assertEquals(5, l.handTotal(p_hand));
        
        p_hand.add(new Card("", 9));
        assertEquals(4, l.handTotal(p_hand));
    }

    @Test
    void test_who_won_draw() {
        assertEquals("Draw", l.whoWon(p_hand, b_hand));
    }

    @Test
    void test_who_won() {
        p_hand.add(new Card("", 10));
        assertEquals("Draw", l.whoWon(p_hand, b_hand));

        p_hand.add(new Card("", 9));
        assertEquals("Banker", l.whoWon(p_hand, b_hand));

        
        p_hand.add(new Card("", 9));
        assertEquals("Player", l.whoWon(b_hand, p_hand));

        p_hand.add(new Card("", 4));
        assertEquals("Player", l.whoWon(p_hand, b_hand));
    }

    @Test
    void test_player_draw_true() {
        p_hand.remove(0);
        assertTrue(l.evaluatePlayerDraw(p_hand));
    }

    @Test 
    void test_player_draw_false() {
        assertFalse(l.evaluatePlayerDraw(p_hand));
    }

    @Test
    void test_banker_draw() {
        b_hand.remove(2);
        b_hand.remove(1);
        assertTrue(l.evaluateBankerDraw(b_hand, new Card(null, 9)));
        b_hand.add(new Card(null, 7));
        assertFalse(l.evaluateBankerDraw(b_hand, new Card(null, 9)));
    }

    @Test
    void test_banker_draw_selected() {
        // 6
        assertFalse(l.evaluateBankerDraw(b_hand, new Card(null, 0)));
        assertFalse(l.evaluateBankerDraw(b_hand, new Card(null, 1)));
        assertFalse(l.evaluateBankerDraw(b_hand, new Card(null, 2)));
        assertFalse(l.evaluateBankerDraw(b_hand, new Card(null, 3)));
        assertFalse(l.evaluateBankerDraw(b_hand, new Card(null, 4)));
        assertFalse(l.evaluateBankerDraw(b_hand, new Card(null, 5)));
        assertTrue(l.evaluateBankerDraw(b_hand, new Card(null, 6)));
        assertTrue(l.evaluateBankerDraw(b_hand, new Card(null, 7)));
        assertFalse(l.evaluateBankerDraw(b_hand, new Card(null, 8)));
        assertFalse(l.evaluateBankerDraw(b_hand, new Card(null, 9)));
        assertFalse(l.evaluateBankerDraw(b_hand, null));

        // 5
        b_hand.clear();
        b_hand.add(new Card(null, 5));
        assertFalse(l.evaluateBankerDraw(b_hand, new Card(null, 0)));
        assertFalse(l.evaluateBankerDraw(b_hand, new Card(null, 1)));
        assertFalse(l.evaluateBankerDraw(b_hand, new Card(null, 2)));
        assertFalse(l.evaluateBankerDraw(b_hand, new Card(null, 3)));
        assertTrue(l.evaluateBankerDraw(b_hand, new Card(null, 4)));
        assertTrue(l.evaluateBankerDraw(b_hand, new Card(null, 5)));
        assertTrue(l.evaluateBankerDraw(b_hand, new Card(null, 6)));
        assertTrue(l.evaluateBankerDraw(b_hand, new Card(null, 7)));
        assertFalse(l.evaluateBankerDraw(b_hand, new Card(null, 8)));
        assertFalse(l.evaluateBankerDraw(b_hand, new Card(null, 9)));
        assertTrue(l.evaluateBankerDraw(b_hand, null));

        // 4
        b_hand.clear();
        b_hand.add(new Card(null, 4));
        assertFalse(l.evaluateBankerDraw(b_hand, new Card(null, 0)));
        assertFalse(l.evaluateBankerDraw(b_hand, new Card(null, 1)));
        assertTrue(l.evaluateBankerDraw(b_hand, new Card(null, 2)));
        assertTrue(l.evaluateBankerDraw(b_hand, new Card(null, 3)));
        assertTrue(l.evaluateBankerDraw(b_hand, new Card(null, 4)));
        assertTrue(l.evaluateBankerDraw(b_hand, new Card(null, 5)));
        assertTrue(l.evaluateBankerDraw(b_hand, new Card(null, 6)));
        assertTrue(l.evaluateBankerDraw(b_hand, new Card(null, 7)));
        assertFalse(l.evaluateBankerDraw(b_hand, new Card(null, 8)));
        assertFalse(l.evaluateBankerDraw(b_hand, new Card(null, 9)));
        assertTrue(l.evaluateBankerDraw(b_hand, null));

        // 3
        b_hand.clear();
        b_hand.add(new Card(null, 3));
        assertTrue(l.evaluateBankerDraw(b_hand, new Card(null, 0)));
        assertTrue(l.evaluateBankerDraw(b_hand, new Card(null, 1)));
        assertTrue(l.evaluateBankerDraw(b_hand, new Card(null, 2)));
        assertTrue(l.evaluateBankerDraw(b_hand, new Card(null, 3)));
        assertTrue(l.evaluateBankerDraw(b_hand, new Card(null, 4)));
        assertTrue(l.evaluateBankerDraw(b_hand, new Card(null, 5)));
        assertTrue(l.evaluateBankerDraw(b_hand, new Card(null, 6)));
        assertTrue(l.evaluateBankerDraw(b_hand, new Card(null, 7)));
        assertFalse(l.evaluateBankerDraw(b_hand, new Card(null, 8)));
        assertTrue(l.evaluateBankerDraw(b_hand, new Card(null, 9)));
        assertTrue(l.evaluateBankerDraw(b_hand, null));
    }
}   
