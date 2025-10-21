package domain.player;

import domain.card.Card;

import java.util.ArrayList;
import java.util.List;

public abstract class Player {

    protected String nickname;
    protected int win;
    protected int lose;
    protected long money;
    protected List<Card> hand;

    public Player() {
        this.hand = new ArrayList<>();
    }

    public void addCard(Card card) {
        hand.add(card);
    }

    public void clearHand() {
        hand.clear();
    }

    public List<Card> getHand() {
        return hand;
    }

    public String getNickname() {
        return nickname;
    }

    public int getWin() {
        return win;
    }

    public int getLose() {
        return lose;
    }

    public long getMoney() {
        return money;
    }

    public void addWin() {
        this.win++;
    }

    public void addLose() {
        this.lose++;
    }

    public void addMoney(long amount) {
        this.money += amount;
    }

    public void subtractMoney(long amount) {
        this.money -= amount;
    }
}
