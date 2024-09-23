package co.atelier.tenisu.services;

import co.atelier.tenisu.entity.Player;
import co.atelier.tenisu.repository.PlayerRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
}
