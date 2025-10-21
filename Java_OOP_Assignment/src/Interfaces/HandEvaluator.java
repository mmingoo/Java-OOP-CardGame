package Interfaces;

import domain.card.Card;

import java.util.List;

public interface HandEvaluator {
    int evaluate(List<Card> cards);
    String getHandRank(List<Card> cards);
}
