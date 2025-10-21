package game.fivecardraw;

import Interfaces.RewardDistributor;
import config.PokerGameConfig;
import domain.player.Player;

import java.util.List;

public class StandardRewardDistributor implements RewardDistributor {
    PokerGameConfig pokerGameConfig = PokerGameConfig.getInstance();
    private final int winnerPrize = pokerGameConfig.getWinnerPrize();
    private final int loserPenalty = pokerGameConfig.getLoserPenalty();

    @Override
    public void distributeRewards(List<Player> players, Player winner) {
        for (Player player : players) {
            if (player.equals(winner)) {
                player.addWin();
                player.addMoney(winnerPrize);
            } else {
                player.addLose();
                player.subtractMoney(loserPenalty);
            }
        }
    }

}