package game.fivecardraw;

import Interfaces.HandEvaluator;
import domain.card.Card;
import domain.card.PokerCard;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FiveCardDrawHandEvaluator implements HandEvaluator {
    // 핸드 랭크 점수 (높을수록 좋음)
    private static final int ROYAL_FLUSH = 100;
    private static final int STRAIGHT_FLUSH = 90;
    private static final int FOUR_OF_KIND = 80;
    private static final int FULL_HOUSE = 70;
    private static final int FLUSH = 60;
    private static final int STRAIGHT = 50;
    private static final int THREE_OF_KIND = 40;
    private static final int TWO_PAIR = 30;
    private static final int ONE_PAIR = 20;
    private static final int HIGH_CARD = 10;

    @Override
    public int evaluate(List<Card> cards) {

        //카드가 없거나 카드가 5장이 아니면 에러 발생
        if (cards == null || cards.size() != 2) {
            throw new IllegalArgumentException("2장의 카드가 필요합니다.");
        }

        // PokerCard로 캐스팅, 포커카드 담기
        List<PokerCard> pokerCards = new ArrayList<>();
        for (Card card : cards) {
            pokerCards.add((PokerCard) card);
        }

        // 정렬 (숫자 기준)
        pokerCards.sort((a, b) -> Integer.compare(a.getNumber(), b.getNumber()));

        // 각 핸드 체크 (높은 순서부터)
        if (isRoyalFlush(pokerCards)) return ROYAL_FLUSH;
        if (isStraightFlush(pokerCards)) return STRAIGHT_FLUSH;
        if (isFourOfKind(pokerCards)) return FOUR_OF_KIND;
        if (isFullHouse(pokerCards)) return FULL_HOUSE;
        if (isFlush(pokerCards)) return FLUSH;
        if (isStraight(pokerCards)) return STRAIGHT;
        if (isThreeOfKind(pokerCards)) return THREE_OF_KIND;
        if (isTwoPair(pokerCards)) return TWO_PAIR;
        if (isOnePair(pokerCards)) return ONE_PAIR;

        return HIGH_CARD;
    }

    @Override
    // 카드 평가
    public String getHandRank(List<Card> cards) {
        int score = evaluate(cards);

        switch (score) {
            case ROYAL_FLUSH: return "로얄 플러시";
            case STRAIGHT_FLUSH: return "스트레이트 플러시";
            case FOUR_OF_KIND: return "포카드";
            case FULL_HOUSE: return "풀하우스";
            case FLUSH: return "플러시";
            case STRAIGHT: return "스트레이트";
            case THREE_OF_KIND: return "트리플";
            case TWO_PAIR: return "투페어";
            case ONE_PAIR: return "원페어";
            case HIGH_CARD: return "하이카드";
            default: return "알 수 없음";
        }
    }

    // 로얄 플러시: 5장의 무늬가 같으며 A, K, Q, J, 10 으로 이루어진 족보
    private boolean isRoyalFlush(List<PokerCard> cards) {
        if (!isFlush(cards)) return false;

        // A(1), 10, J(11), Q(12), K(13) 확인
        int[] royalNumbers = {1, 10, 11, 12, 13};
        int index = 0;

        for (PokerCard card : cards) {
            if (card.getNumber() != royalNumbers[index++]) {
                return false;
            }
        }

        return true;
    }

    // 스트레이트 플러시: 연속된 5장 + 같은 무늬
    private boolean isStraightFlush(List<PokerCard> cards) {
        return isFlush(cards) && isStraight(cards);
    }

    // 포카드: 같은 숫자 4장
    private boolean isFourOfKind(List<PokerCard> cards) {
        Map<Integer, Integer> countMap = getNumberCount(cards);
        return countMap.containsValue(4);
    }

    // 풀하우스: 트리플(동일한 숫자 3장) + 원페어(동일한 숫자 2장)
    private boolean isFullHouse(List<PokerCard> cards) {
        Map<Integer, Integer> countMap = getNumberCount(cards);
        return countMap.containsValue(3) && countMap.containsValue(2);
    }

    // 플러시: 같은 무늬 5장
    private boolean isFlush(List<PokerCard> cards) {
        int firstKind = cards.get(0).getKind();
        for (PokerCard card : cards) {
            if (card.getKind() != firstKind) {
                return false;
            }
        }
        return true;
    }

    // 스트레이트: 연속된 숫자 5장
    private boolean isStraight(List<PokerCard> cards) {
        // A-2-3-4-5 스트레이트 체크
        if (cards.get(0).getNumber() == 1 &&
                cards.get(1).getNumber() == 2 &&
                cards.get(2).getNumber() == 3 &&
                cards.get(3).getNumber() == 4 &&
                cards.get(4).getNumber() == 5) {
            return true;
        }

        // 일반 스트레이트 체크
        for (int i = 0; i < cards.size() - 1; i++) {
            if (cards.get(i + 1).getNumber() - cards.get(i).getNumber() != 1) {
                return false;
            }
        }

        return true;
    }

    // 트리플: 같은 숫자 3장
    private boolean isThreeOfKind(List<PokerCard> cards) {
        Map<Integer, Integer> countMap = getNumberCount(cards);
        return countMap.containsValue(3);
    }

    // 투페어: 페어 2개
    private boolean isTwoPair(List<PokerCard> cards) {
        Map<Integer, Integer> countMap = getNumberCount(cards);
        int pairCount = 0;
        for (int count : countMap.values()) {
            if (count == 2) pairCount++;
        }
        return pairCount == 2;
    }

    // 원페어: 같은 숫자 2장
    private boolean isOnePair(List<PokerCard> cards) {
        Map<Integer, Integer> countMap = getNumberCount(cards);
        return countMap.containsValue(2);
    }

    // 숫자별 카드 개수 계산
    private Map<Integer, Integer> getNumberCount(List<PokerCard> cards) {
        Map<Integer, Integer> countMap = new HashMap<>();
        for (PokerCard card : cards) {
            countMap.put(card.getNumber(),
                    countMap.getOrDefault(card.getNumber(), 0) + 1);
        }
        return countMap;
    }
}

