package factory;

import Interfaces.PlayerFactory;
import builder.DefaultPlayerBuilder;
import domain.player.Player;
import builder.PlayerCreateDirector;

import java.util.List;

public class DefaultPlayerFactory implements PlayerFactory {
    @Override
    public List<Player> createPlayers() {
        PlayerCreateDirector director = new PlayerCreateDirector(new DefaultPlayerBuilder());
        return director.getPlayerList();
    }
}