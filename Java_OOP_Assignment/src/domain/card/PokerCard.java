package domain.card;

public class PokerCard extends Card{
    static final int KIND_MAX = 4;
    static final int NUM_MAX = 13;

    static final int SPADE = 4;
    static final int DIAMOND = 3;
    static final int HEART = 2;
    static final int CLOVER = 1;

    public PokerCard() {
        this(SPADE, 1);
    }

    public PokerCard(int kind, int number) {
        this.kind = kind;
        this.number = number;
    }

    public int getKind() {
        return kind;
    }

    public int getNumber() {
        return number;
    }

    @Override
    public String toString() {
        String[] kinds = {"", "CLOVER", "HEART", "DIAMOND", "SPADE"};
        String numbers = "0123456789XJQK";
        return "kind : " + kinds[this.kind] + ", number : " + numbers.charAt(this.number);
    }

} // Card클래스의 끝