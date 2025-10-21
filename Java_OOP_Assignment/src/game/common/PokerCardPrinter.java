package game.common;

import Interfaces.HandEvaluator;
import Interfaces.Printer;
import domain.player.Player;
import game.fivecardraw.FiveCardDrawGame;
import game.fivecardraw.FiveCardDrawHandEvaluator;

import java.util.List;
import java.util.stream.Collectors;

public class PokerCardPrinter implements Printer {
    HandEvaluator fiveCardDrawHandEvaluator = new FiveCardDrawHandEvaluator();
    @Override
    public void printRoundResult(int round, Player winner) {
        System.out.println("라운드 " + round + " 승자: " + winner.getNickname());
        System.out.println("승자: " + winner.getNickname()+"의 패 : "+ fiveCardDrawHandEvaluator.getHandRank(winner.getHand()));
    }

    @Override
    public void printFinalResults(List<Player> players) {
        System.out.println("\n=== 최종 결과 ===");
        List<Player> sortedPlayers = players.stream()
            .sorted((p1, p2) -> Integer.compare(p2.getWin(), p1.getWin()))
            .collect(Collectors.toList());

        for (int i = 0; i < sortedPlayers.size(); i++) {
            Player player = sortedPlayers.get(i);
            System.out.printf("%d위: %s - 승: %d, 패: %d, 보유금액: %d원\n",
                i + 1, player.getNickname(), player.getWin(), player.getLose(), player.getMoney());
        }
    }
}