package game.blackjack;

import Interfaces.Dealer;
import Interfaces.Game;
import Interfaces.HandEvaluator;
import domain.card.Deck;
import domain.player.Player;

import java.util.ArrayList;
import java.util.List;

public class BlackJackGame implements Game {
    @Override
    public void startRound() {
        System.out.println("\n========== 블랙잭 라운드 시작 ==========");
    }

    @Override
    public void endRound() {
        System.out.println("========== 블랙잭 라운드 종료 ==========\n");

    }

    @Override
    public Player playRound(Deck deck, List<Player> players, Dealer dealer, HandEvaluator evaluator) {
        // 블랙잭 딜러
        BlackJackDealer blackJackDealer = (BlackJackDealer) dealer;

        // 블랙잭 평가기
        BlackJackHandEvaluator blackJackHandEvaluator = (BlackJackHandEvaluator) evaluator;

        // 1단계: 플레이어들 패 출력
        playersHands(players, blackJackHandEvaluator);

        // 2단계: 딜러 턴 (자동 플레이)
        blackJackDealer.playDealerTurn(deck, blackJackHandEvaluator);

        // 3단계; 승자 선택
        Player winner = determineWinner(players, blackJackDealer, blackJackHandEvaluator);

        return winner;
    }


    /**
     * 플레이어들이 가진 패 출력
     * */
    private void playersHands(List<Player> players, BlackJackHandEvaluator blackJackHandEvaluator) {
        System.out.println("\n 플레이어들의 패:");
        System.out.println("─────────────────────────────");

        for (Player player : players) {
            String handInfo = blackJackHandEvaluator.getHandRank(player.getHand());
            System.out.println(player.getNickname() + ": " + handInfo);

            // 버스트 체크
            if (blackJackHandEvaluator.isBust(player.getHand())) {
                System.out.println("  → 버스트! 탈락!");
            }
            // 블랙잭 체크
            else if (blackJackHandEvaluator.isBlackjack(player.getHand())) {
                System.out.println("  → 블랙잭!");
            }
        }
        System.out.println("─────────────────────────────");
    }


    /**
     * 3단계: 승자 결정 , 각 플레이어와 딜러 비교
     * */
    private Player determineWinner(List<Player> players,
                                    BlackJackDealer dealer,
                                    BlackJackHandEvaluator evaluator) {

        System.out.println("\n 승패 결정:");
        System.out.println("─────────────────────────────");

        // 승자 목록 (여러 명 가능)
        List<Player> winners = new ArrayList<>();
        int highestScore = -1;

        String result = "";
        boolean isWinner = false;

        // 딜러 점수
        int dealerScore = evaluator.evaluate(dealer.getDealerHand());
        boolean dealerBust = evaluator.isBust(dealer.getDealerHand());
        boolean dealerBlackjack = evaluator.isBlackjack(dealer.getDealerHand());


        // 각 플레이어를 딜러와 비교
        for (Player player : players) {
            int playerScore = evaluator.evaluate(player.getHand());
            boolean playerBust = evaluator.isBust(player.getHand());
            boolean playerBlackjack = evaluator.isBlackjack(player.getHand());



            // 케이스 1: 플레이어 버스트 → 무조건 패배
            if (playerBust) {
                result = "패배 (버스트)";
            }
            // 케이스 2: 딜러 버스트 → 플레이어 승리
            else if (dealerBust) {
                result = "승리 (딜러 버스트)";
                isWinner = true;
            }
            // 케이스 3: 플레이어 블랙잭 vs 딜러 블랙잭 → 무승부
            else if (playerBlackjack && dealerBlackjack) {
                result = "무승부 (둘 다 블랙잭)";
            }
            // 케이스 4: 플레이어만 블랙잭 → 승리
            else if (playerBlackjack && !dealerBlackjack) {
                result = "승리 (블랙잭!)";
                isWinner = true;
            }
            // 케이스 5: 딜러만 블랙잭 → 패배
            else if (!playerBlackjack && dealerBlackjack) {
                result = "패배 (딜러 블랙잭)";
            }
            // 케이스 6: 점수 비교
            else if (playerScore > dealerScore) {
                result = "승리 (" + playerScore + " > " + dealerScore + ")";
                isWinner = true;
            }
            else if (playerScore < dealerScore) {
                result = "패배 (" + playerScore + " < " + dealerScore + ")";
            }
            else {
                result = "무승부 (" + playerScore + " = " + dealerScore + ")";
            }

            // 결과 출력
            System.out.println(player.getNickname() + ": " + result);

            // 승자 추가
            if (isWinner) {
                // 점수가 가장 높은 플레이어를 승자로 두려고
                if (playerScore > highestScore) {
                    winners.clear();
                    winners.add(player);
                    highestScore = playerScore;

                    // 만약 점수가 같은 승자 여러명이면 winner 에 추가
                } else if (playerScore == highestScore) {
                    winners.add(player);
                }
            }
        }

        System.out.println("─────────────────────────────");

        // 승자가 없으면 null 반환 (딜러 승리)
        if (winners.isEmpty()) {
            System.out.println(" 딜러 승리! (모든 플레이어 패배)");
            return null;
        }

        // 승자가 여러 명이면 첫 번째 반환
        // (RewardDistributor에서 모든 승자에게 보상 줘야 함)
        if (winners.size() > 1) {
            System.out.println(winners.size() + "명 승리!");
        } else {
            System.out.println(winners.getFirst().getNickname() + " 승리!");
        }

        return winners.getFirst();
    }
}
