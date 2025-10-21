package Interfaces;

import domain.card.Deck;
import domain.player.Player;

import java.util.List;

public interface Game {
    void startRound();
    void endRound();
    Player playRound(Deck deck, List<Player> players, Dealer dealer, HandEvaluator evaluator);

}