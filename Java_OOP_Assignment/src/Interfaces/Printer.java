package Interfaces;

import domain.player.Player;

import java.util.List;

public interface Printer {
    void printRoundResult(int round, Player winner);
    void printFinalResults(List<Player> players);
}
