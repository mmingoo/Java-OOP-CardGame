package game.fivecardraw;

import Interfaces.Game;
import Interfaces.Dealer;
import Interfaces.HandEvaluator;
import domain.card.Deck;
import domain.player.Player;

import java.util.List;

public class FiveCardDrawGame implements Game {

    @Override
    public void startRound() {
        System.out.println("새로운 라운드를 시작합니다.");
    }

    @Override
    public void endRound() {
        System.out.println("라운드가 종료되었습니다.");
        System.out.println();
    }

    @Override
    public Player playRound(Deck deck, List<Player> players, Dealer dealer, HandEvaluator evaluator) {
        // 각 플레이어의 패를 평가하여 승자 결정
        Player winner = null;
        int highestScore = -1;

        for (Player player : players) {
            int score = evaluator.evaluate(player.getHand());
            if (score > highestScore) {
                highestScore = score;
                winner = player;
            }
        }

        return winner;
    }


}
