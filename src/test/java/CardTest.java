import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.DisplayName;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class CardTest {
    @Test
    void test_constructor_card() {
        Card a = new Card("A", 1);
        assertEquals("A", a.getSuite(), "Suite incorrect");
        assertEquals(1, a.getValue(), "Value incorrect");
        assertEquals(1, a.getId(), "Id incorrect");

        Card b = new Card("A", 11);
        assertEquals("A", b.getSuite(), "Suite incorrect");
        assertEquals(0, b.getValue(), "Value incorrect");
        assertEquals(11, b.getId(), "Id incorrect");
        
    }
}
