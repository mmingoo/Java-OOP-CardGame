package Interfaces;

import domain.card.Card;
import domain.card.Deck;

public interface CardGameFactory {
    Deck createDeck();
    Card createCard();
//    List<Player> createPlayer();
//    Printer createPrinter();
//    void runFiveCardDrawGame(Deck deck, List<Player> playerList, Dealer dealer, HandEvaluator handEvaluator);
//    void endFiveCardDrawGame(Printer printer);

}
