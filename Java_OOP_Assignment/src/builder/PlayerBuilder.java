package builder;

import domain.player.Player;

public interface  PlayerBuilder {
    PlayerBuilder nickname(String nickname);
    PlayerBuilder win(int win);
    PlayerBuilder lose(int lose);
    PlayerBuilder money(long money);
    Player getPlayer();


}
