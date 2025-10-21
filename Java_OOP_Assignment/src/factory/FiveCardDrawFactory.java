package factory;

import Interfaces.Game;
import Interfaces.Dealer;
import Interfaces.GameFactory;
import Interfaces.HandEvaluator;
import Interfaces.RewardDistributor;
import game.fivecardraw.FiveCardDrawDealer;
import game.fivecardraw.FiveCardDrawGame;
import game.fivecardraw.FiveCardDrawHandEvaluator;
import game.fivecardraw.StandardRewardDistributor;

public class FiveCardDrawFactory implements GameFactory {

    @Override
    public Game createGame() {
        return new FiveCardDrawGame();
    }

    @Override
    public Dealer createDealer() {
        return new FiveCardDrawDealer();
    }

    @Override
    public HandEvaluator createEvaluator() {
        return new FiveCardDrawHandEvaluator();
    }

    @Override
    public RewardDistributor createRewardDistributor() {
        return new StandardRewardDistributor();
    }
}