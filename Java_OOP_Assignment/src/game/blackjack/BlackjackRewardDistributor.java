package game.blackjack;

import Interfaces.RewardDistributor;
import config.PokerGameConfig;
import domain.player.Player;

import java.util.List;

public class BlackjackRewardDistributor implements RewardDistributor {
    
    PokerGameConfig config = PokerGameConfig.getInstance();
    private final int winnerPrize = config.getWinnerPrize();
    private final int loserPenalty = config.getLoserPenalty();
    
    // 블랙잭 보너스 배당 (1.5배)
    private final int blackjackBonus = (int)(winnerPrize * 1.5);



    @Override
    public void distributeRewards(List<Player> players, Player winner) {
        // winner가 null이면 딜러 승리 (모든 플레이어 패배)
        if (winner == null) {
            System.out.println("\n보상 분배:");
            System.out.println("ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ");
            for (Player player : players) {
                player.addLose();
                player.subtractMoney(loserPenalty);
                System.out.println(player.getNickname() + ": -" + loserPenalty + "원 (패배)");
            }
            System.out.println("ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ");
            return;
        }

        System.out.println("\n보상 분배:");
        System.out.println("ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ");

        // 각 플레이어를 개별적으로 처리
        for (Player player : players) {
            // 승자인지 확인 (여러 명 가능)
            if (player.equals(winner)) {
                player.addWin();

                // 블랙잭이면 보너스 배당
                // (간단하게 처리: 실제로는 각 플레이어 블랙잭 체크 필요)
                player.addMoney(winnerPrize);
                System.out.println(player.getNickname() + ": +" + winnerPrize + "원 (승리)");

            } else {
                player.addLose();
                player.subtractMoney(loserPenalty);
                System.out.println(player.getNickname() + ": -" + loserPenalty + "원 (패배)");
            }
        }
        System.out.println("ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ");
    }
}