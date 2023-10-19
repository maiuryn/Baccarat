public class Card {
    private String suite;
    private int value;
    private int id;

    // Suite and id
    // id of 11 is Jack, 12 Queen, 13, King
    public Card(String mySuite, int myId) {
        this.suite = mySuite;
        this.value = myId >= 10 ? 0 : myId;
        this.id = myId;
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

