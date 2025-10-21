import factory.BlackJackFactory;
import factory.FiveCardDrawFactory;
import factory.PokerCardFactory;
import manager.GameManager;


public class CardGameApp {
    public static void main(String[] args) {

        startBlackJack();

    }

    private static void startFiveCardDraw(){
        // 포커카드로 파이브카드드로우 게임
        GameManager manager = new GameManager(
                new PokerCardFactory(),      // 포커카드 사용
                new FiveCardDrawFactory()    // 파이브카드드로우 규칙
        );

        // 100라운드 실행
        manager.runTournament();

        // 최종 결과 출력
        manager.displayFinalResults();
    }


    private static void startBlackJack(){
        GameManager manager = new GameManager(
                new PokerCardFactory(),
                new BlackJackFactory()
        );

        manager.runTournament();
    }
}
