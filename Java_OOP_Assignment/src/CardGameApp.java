import factory.FiveCardDrawFactory;
import factory.PokerCardFactory;
import manager.GameManager;


public class CardGameApp {
    public static void main(String[] args) {

        // 포커카드로 파이브카드드로우 게임
        GameManager manager = new GameManager(
                new PokerCardFactory(),      // 포커카드 사용
                new FiveCardDrawFactory()    // 파이브카드드로우 규칙
        );

        long startTime = System.currentTimeMillis();
        // 100라운드 실행
        manager.runTournament();

        // 최종 결과 출력
        manager.displayFinalResults();
        long endTime = System.currentTimeMillis();

        System.out.println("실행시간 : " + (endTime - startTime));
    }
}
