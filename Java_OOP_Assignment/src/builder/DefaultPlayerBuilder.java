package builder;

import domain.player.Player;
import domain.player.PokerPlayer;

public class DefaultPlayerBuilder implements PlayerBuilder{


    private String nickname;
    private int win;
    private int lose;
    private long money;



    // 닉네임 길이 유효성 체크 해야함
    @Override
    public PlayerBuilder nickname(String nickname) {
        this.nickname = nickname;
        return this;
    }

    @Override
    public PlayerBuilder win(int win) {
        this.win = win;
        return this;
    }

    @Override
    public PlayerBuilder lose(int lose) {
        this.lose = lose;
        return this;
    }

    @Override
    public PlayerBuilder money(long money) {
        this.money = money;
        return this;
    }

    @Override
    public Player getPlayer() {
        return new PokerPlayer(nickname, win, lose, money);
    }

}
