package manager;

import Interfaces.Game;
import Interfaces.*;
import config.PokerGameConfig;
import domain.card.Deck;
import factory.DefaultPlayerFactory;
import domain.player.Player;
import game.common.PokerCardPrinter;

import java.util.ArrayList;
import java.util.List;


public class GameManager implements Manager {
    private CardGameFactory cardFactory;
    private GameFactory gameFactory;
    private PlayerFactory playerFactory;
    private List<Player> players;
    private Printer printer;
    PokerGameConfig pokerGameConfig = PokerGameConfig.getInstance();

    public GameManager(CardGameFactory cardFactory, GameFactory gameFactory) {
        this.cardFactory = cardFactory;
        this.gameFactory = gameFactory;
        this.playerFactory = new DefaultPlayerFactory();
        this.printer = new PokerCardPrinter();
        this.players = new ArrayList<>();
    }

    @Override
    public void runTournament() {
        // 게임 구성요소 생성
        Deck deck = cardFactory.createDeck();
        Game game = gameFactory.createGame();
        Dealer dealer = gameFactory.createDealer();
        HandEvaluator evaluator = gameFactory.createEvaluator();
        RewardDistributor distributor = gameFactory.createRewardDistributor();

        // 플레이어 생성
        players = playerFactory.createPlayers();

        // 라운드 진행
        for (int i = 0; i < pokerGameConfig.getRoundCnt(); i++) {
            game.startRound();

            // 카드 섞기 및 분배
            dealer.shuffleDeck(deck);
            dealer.dealCards(players, deck, 5);

            // 승자 결정
            Player winner = game.playRound(deck, players, dealer, evaluator);

            // 보상 분배
            distributor.distributeRewards(players, winner);

            // 라운드 결과 출력
            printer.printRoundResult(i + 1, winner);

            game.endRound();
        }
    }

    @Override
    public void displayFinalResults() {
        printer.printFinalResults(players);
    }

}


