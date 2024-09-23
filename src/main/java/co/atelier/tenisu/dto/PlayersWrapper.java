package co.atelier.tenisu.dto;

import co.atelier.tenisu.entity.Player;

import java.util.List;

public class PlayersWrapper {
    private List<Player> players;

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }
}
