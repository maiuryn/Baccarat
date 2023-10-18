public class Card {
    private String suite;
    private int value;
    private int id;

    public Card(String mySuite, int myVal) {
        this.suite = mySuite;
        this.value = myVal >= 10 ? 0 : myVal;
        this.id = myVal;
    }

    public String getSuite() {
        return this.suite;
    }

    public int getValue() {
        return this.value;
    }

    public int getId() {
        return this.id;
    }
}

