package co.atelier.tenisu.service;

import co.atelier.tenisu.dto.PlayersStatisticsDto;
import co.atelier.tenisu.entity.Player;

import java.util.List;

public interface PlayerService {

    List<Player> getAllPlayers();

    Player getPlayerById(int id);

    PlayersStatisticsDto calculatePlayersStatistics();
}
