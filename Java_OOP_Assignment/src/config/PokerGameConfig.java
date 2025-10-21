package config;

public class PokerGameConfig {

    private final int win = 0;
    private final int lose = 0;
    private final int money =10000;
    private final int peopleCnt = 4;
    private final int defaultPeopleCnt = 4;
    private final int winnerPrize = 100;
    private final int loserPenalty = 0;
    private final int roundCnt = 100;

    private PokerGameConfig(){}

    private static class PokerGameConfigHolder{
        private static final PokerGameConfig Instance = new PokerGameConfig();
    }

    public static PokerGameConfig getInstance() {
        return PokerGameConfigHolder.Instance;
    }

    public int getWin(){return this.win;}
    public int getLose(){return this.lose;}
    public int getMoney(){return this.money;}
    public int getPeopleCnt(){return this.peopleCnt;}
    public int getDefaultPeopleCnt(){return this.defaultPeopleCnt;}
    public int getWinnerPrize(){return this.winnerPrize;}
    public int getLoserPenalty(){return this.loserPenalty;}
    public int getRoundCnt(){return this.roundCnt;}

}
