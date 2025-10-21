package domain.card;

public abstract class Card {

    protected int kind;
    protected int number;

    public abstract String toString();
    public abstract int getKind();
    public abstract int getNumber();
}
