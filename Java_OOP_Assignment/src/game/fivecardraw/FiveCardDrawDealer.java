package game.fivecardraw;

import Interfaces.Dealer;
import domain.card.Deck;
import domain.player.Player;

import java.util.List;

public class FiveCardDrawDealer implements Dealer {

    @Override
    public void shuffleDeck(Deck deck) {
        deck.shuffle();
        System.out.println("카드를 섞었습니다.");
    }

    @Override
    public void dealCards(List<Player> players, Deck deck, int cardsPerPlayer) {
        // 각 플레이어의 기존 카드 제거
        for (Player player : players) {
            player.clearHand();
        }

        // 카드 분배
        int cardIndex = 0;
        for (int i = 0; i < cardsPerPlayer; i++) {
            for (Player player : players) {
                player.addCard(deck.getDeck()[cardIndex++]);
            }
        }
        System.out.println("카드를 분배했습니다.");
    }
}
