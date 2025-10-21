package game.blackjack;

import Interfaces.HandEvaluator;
import domain.card.Card;
import domain.card.PokerCard;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BlackJackHandEvaluator implements HandEvaluator {

    @Override
    public int evaluate(List<Card> cards) {
        int sum = 0;
        int aceCount = 0;

        //카드가 없거나 카드가 5장이 아니면 에러 발생
        if (cards == null || cards.isEmpty()) {
            throw new IllegalArgumentException("5장의 카드가 필요합니다.");

        }

        // 1. 모든 카드 점수 구하기
        for (Card card : cards) {
            int number = card.getNumber();

            if (number > 10) {
                sum += 10;
            } else if (number == 1) {

                // 우선 ace 는 1 처리
                sum += 1;
                aceCount++;
            } else {
                sum += number;
            }
        }

        //2. ace를 11로 해도 넘는지 안 넘는지 비교 후 안 넘으면 1 -> 11 로 변경하기
        while (aceCount > 0 && 10 + sum <= 21) {
            sum += 10;
            aceCount--;
        }

        return sum;
    }


    @Override
    public String getHandRank(List<Card> cards) {
        int score = evaluate(cards);

        if(score > 21){
            System.out.println("burst");
            return "버스트! "+score+"점";
        } else if (score == 21) {
            return "블랙잭 ";
        }

        return "score :"+score;
    }







    /**
     * 버스트 여부 확인 (21 초과)
     */
    public boolean isBust(List<Card> cards) {
        return evaluate(cards) > 21;
    }

    /**
     * 블랙잭 여부 확인 (초기 2장이 21점)
     */
    public boolean isBlackjack(List<Card> cards) {
        return cards.size() == 2 && evaluate(cards) == 21;
    }

    /**
     * Soft Hand 여부 확인 (A를 11로 계산하고 있는 상태)
     */
    public boolean isSoftHand(List<Card> cards) {
        int sum = 0;
        int aceCount = 0;

        for (Card card : cards) {
            int number = card.getNumber();
            if (number >= 10) {
                sum += 10;
            } else if (number == 1) {
                sum += 1;
                aceCount++;
            } else {
                sum += number;
            }
        }

        // A가 있고, A를 11로 계산해도 21 이하면 Soft Hand
        return aceCount > 0 && sum + 10 <= 21;
    }
}

