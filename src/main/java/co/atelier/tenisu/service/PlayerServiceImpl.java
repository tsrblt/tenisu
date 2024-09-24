package co.atelier.tenisu.service;

import co.atelier.tenisu.dto.PlayersStatisticsDto;
import co.atelier.tenisu.entity.Player;
import co.atelier.tenisu.repository.PlayerRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static co.atelier.tenisu.util.PlayerStatisticsUtil.*;
import static java.util.Comparator.comparing;

@Service
public class PlayerServiceImpl implements PlayerService {

    private final PlayerRepository playerRepository;

    public PlayerServiceImpl(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }


    @Override
    public List<Player> getAllPlayers() {
        List<Player> players = this.playerRepository.findAllPlayers();

        if (CollectionUtils.isEmpty(players)) {
            return new ArrayList<>();
        }

        return this.playerRepository.findAllPlayers().stream().sorted(comparing(p -> p.getData().getRank())).collect(Collectors.toList());
    }

    @Override
    public Player getPlayerById(int id) {
        return this.playerRepository.findPlayerById(id);
    }

    @Override
    public PlayersStatisticsDto calculatePlayersStatistics() {
        List<Player> players = this.playerRepository.findAllPlayers();
        return new PlayersStatisticsDto(calculateCountryBestRatio(players), calculateAverageBMI(players), calculateMedianHeight(players));
    }
}
