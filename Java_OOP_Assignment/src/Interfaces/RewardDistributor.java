package Interfaces;

import domain.player.Player;

import java.util.List;

public interface RewardDistributor {
    void distributeRewards(List<Player> players, Player winner);

}