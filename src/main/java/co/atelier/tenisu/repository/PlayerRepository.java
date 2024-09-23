package co.atelier.tenisu.repository;

import co.atelier.tenisu.entity.Player;

import java.util.List;

public interface PlayerRepository {
    List<Player> findAllPlayers();
}
