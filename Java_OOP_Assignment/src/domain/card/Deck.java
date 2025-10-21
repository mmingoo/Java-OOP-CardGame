package domain.card;

public class Deck {
    final int CARD_NUM = 52;    // 카드의 개수
    PokerCard pokerCardArr[] = new PokerCard[CARD_NUM];  // Card객체 배열을 포함

    public Deck() {	// Deck의 카드를 초기화한다.
        int i=0;

        for(int k = PokerCard.KIND_MAX; k > 0; k--)
            for(int n = 0; n < PokerCard.NUM_MAX ; n++)
                pokerCardArr[i++] = new PokerCard(k, n+1);
    }

    PokerCard pick(int index) {     // 지정된 위치(index)에 있는 카드 하나를 꺼내서 반환
        return pokerCardArr[index];
    }

    PokerCard pick() {              // Deck에서 카드 하나를 선택한다.
        int index = (int)(Math.random() * CARD_NUM);
        return pick(index);
    }

    public void shuffle() { // 카드의 순서를 섞는다.
        for(int i = 0; i < pokerCardArr.length; i++) {
            int r = (int)(Math.random() * CARD_NUM);

            PokerCard temp  = pokerCardArr[i];
            pokerCardArr[i] = pokerCardArr[r];
            pokerCardArr[r] = temp;
        }
    }

    public PokerCard[] getDeck(){
        return this.pokerCardArr;
    }
} // Deck클래스의 끝