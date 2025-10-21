package game.blackjack;

import Interfaces.Dealer;
import Interfaces.HandEvaluator;
import domain.card.Card;
import domain.card.Deck;
import domain.player.Player;

import java.util.ArrayList;
import java.util.List;

public class BlackJackDealer implements Dealer {

    // 딜러의 패
    private List<Card> dealerHand = new ArrayList<>();

    // 다음에 분배할 카드 인덱스 (덱에서 어디까지 사용했는지 추적)
    private int nextCardIndex = 0;

    @Override
    public void shuffleDeck(Deck deck) {
        deck.shuffle();
        nextCardIndex = 0;  // 카드 인덱스 초기화
        System.out.println("카드를 섞었습니다.");
    }

    @Override
    public void dealCards(List<Player> players, Deck deck, int cardsPerPlayer) {
        System.out.println("\n 카드 분배 중.");

        // 각 플레이어의 기존 카드 제거
        for (Player player : players) {
            player.clearHand();
        }

        // 딜러 패도 초기화
        dealerHand.clear();
        nextCardIndex = 0;  // 인덱스 초기화

        // 카드 분배 (각 플레이어에게 cardsPerPlayer장씩)
        for (int i = 0; i < cardsPerPlayer; i++) {
            for (Player player : players) {
                Card card = deck.getDeck()[nextCardIndex++];
                player.addCard(card);
            }
        }

        // 딜러도 2장 받기
        dealerHand.add(deck.getDeck()[nextCardIndex++]);
        dealerHand.add(deck.getDeck()[nextCardIndex++]);

        System.out.println("블랙잭 카드를 분배했습니다. (각 " + cardsPerPlayer + "장)");
        System.out.println("딜러의 오픈 카드: " + dealerHand.get(0));
        System.out.println("딜러의 히든 카드: [?]");
    }

    /**
     * 딜러 자동 플레이
     * 규칙:
     * - 16점 이하: 무조건 히트
     * - 17점 이상: 무조건 스탠드
     */
    public void playDealerTurn(Deck deck, BlackJackHandEvaluator evaluator) {
        System.out.println("\n========================");
        System.out.println("    딜러 턴 ");
        System.out.println("========================");

        // 딜러 패 공개
        System.out.println("딜러의 패 공개: " + formatCards(dealerHand));
        int currentScore = evaluator.evaluate(dealerHand);
        System.out.println("딜러 현재 점수: " + currentScore + "점");

        // 딜러 규칙: 16 이하면 히트, 17 이상이면 스탠드
        while (currentScore < 17) {
            System.out.println("\n딜러: 16점 이하이므로 히트!");

            // 카드 한 장 받기
            Card newCard = deck.getDeck()[nextCardIndex++];
            dealerHand.add(newCard);

            System.out.println("받은 카드: " + newCard);
            currentScore = evaluator.evaluate(dealerHand);
            System.out.println("딜러 현재 점수: " + currentScore + "점");

            // 버스트 체크
            if (evaluator.isBust(dealerHand)) {
                System.out.println("딜러 버스트! (" + currentScore + "점)");
                return;
            }
        }

        // 17 이상이면 스탠드
        System.out.println("\n딜러: 17점 이상이므로 스탠드!");
        System.out.println("딜러 최종 점수: " + currentScore + "점");

        // 블랙잭 체크
        if (evaluator.isBlackjack(dealerHand)) {
            System.out.println("딜러 블랙잭!");
        }
    }

    // 딜러의 패 반환
    public List<Card> getDealerHand() {
        return dealerHand;
    }

    //카드 목록을 보기 좋게 포맷
    private String formatCards(List<Card> cards) {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < cards.size(); i++) {
            sb.append(cards.get(i).toString());
            if (i < cards.size() - 1) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }
}
