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


public class BaccaratGameTest {
    static BaccaratGame game;
    static BaccaratDealer dealer;
    static BaccaratGameLogic logic;

    @BeforeEach
    void setup() {
        game = new BaccaratGame();
        dealer = new BaccaratDealer();
        logic = new BaccaratGameLogic();
    }

    @Test
    void constructor_test() {
        BaccaratGame b = new BaccaratGame();

    }

    @Test
    void evaluate_winnings_test() {
        assertEquals(0, game.evaluateWinnings(), "initial state incorrect");
    }

    @Test
    void evaluate_winnings_test_win_player() {
        ArrayList<Card> p = new ArrayList<>();
        p.add(new Card(null, 9));
        
        ArrayList<Card> b = new ArrayList<>();
        b.add(new Card(null, 8));
        
        game.setPlayerHand(p);
        game.setBankerHand(b);

        assertEquals(0, game.evaluateWinnings(), "initial state incorrect");

        game.setCurrentBet(100);
        game.setBetSelection("Player");
        assertEquals(200, game.evaluateWinnings(), "payout incorrect");
    }

    @Test
    void evaluate_winnings_test_win_banker() {
        ArrayList<Card> p = new ArrayList<>();
        p.add(new Card(null, 8));
        
        ArrayList<Card> b = new ArrayList<>();
        b.add(new Card(null, 9));
        
        game.setPlayerHand(p);
        game.setBankerHand(b);

        assertEquals(0, game.evaluateWinnings(), "initial state incorrect");
        
        game.setCurrentBet(100);
        game.setBetSelection("Banker");
        assertEquals(190, game.evaluateWinnings(), "payout incorrect"); 
    }

    @Test
    void evaluate_winnings_test_win_draw() {
        ArrayList<Card> p = new ArrayList<>();
        p.add(new Card(null, 9));
        
        ArrayList<Card> b = new ArrayList<>();
        b.add(new Card(null, 9));
        
        game.setPlayerHand(p);
        game.setBankerHand(b);

        assertEquals(0, game.evaluateWinnings(), "initial state incorrect");
        
        game.setCurrentBet(100);
        game.setBetSelection("Draw");
        assertEquals(900, game.evaluateWinnings(), "payout incorrect"); 
    }
}
