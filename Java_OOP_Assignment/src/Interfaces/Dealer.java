package Interfaces;

import domain.card.Deck;
import domain.player.Player;

import java.util.List;

public interface Dealer {
    void shuffleDeck(Deck deck);

    void dealCards(List<Player> players, Deck deck, int cardsPerPlayer);
}
