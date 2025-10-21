package factory;

import Interfaces.CardGameFactory;
import domain.card.Card;
import domain.card.Deck;
import domain.card.PokerCard;

// 포커카드용 팩토리
public class  PokerCardFactory implements CardGameFactory {


    @Override
    public Deck createDeck() {
        return new Deck();  // 52장의 포커카드 덱
    }

    @Override
    public Card createCard() {
        return new PokerCard();
    }
}
