package Interfaces;


public interface GameFactory {
    Game createGame();
    Dealer createDealer();
    HandEvaluator createEvaluator();
    RewardDistributor createRewardDistributor();  // 추가

}