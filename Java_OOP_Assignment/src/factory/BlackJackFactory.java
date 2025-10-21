package factory;

import Interfaces.*;
import game.blackjack.BlackJackDealer;
import game.blackjack.BlackJackGame;
import game.blackjack.BlackJackHandEvaluator;
import game.fivecardraw.StandardRewardDistributor;

public class BlackJackFactory implements GameFactory {

    @Override
    public Game createGame() {
        return new BlackJackGame();
    }

    @Override
    public Dealer createDealer() {
        return new BlackJackDealer();
    }

    @Override
    public HandEvaluator createEvaluator() {
        return new BlackJackHandEvaluator();
    }

    @Override
    public RewardDistributor createRewardDistributor() {
        return new StandardRewardDistributor();
    }
}