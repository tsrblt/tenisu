package co.atelier.tenisu.services;

import co.atelier.tenisu.repository.PlayerRepository;

public class PlayerServiceImpl implements PlayerService {

    private final PlayerRepository playerRepository;

    public PlayerServiceImpl(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

}
