package co.atelier.tenisu.repository;

import co.atelier.tenisu.entity.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class PlayerRepositoryImplTest {

    private PlayerRepositoryImpl playerRepository;

    @BeforeEach
    void setUp() {
        playerRepository = new PlayerRepositoryImpl();
    }

    @Test
    @DisplayName("Find all players")
    void findAllPlayers() {
        List<Player> players = playerRepository.findAllPlayers();
        assertEquals(4, players.size());
        assertEquals("Williams", players.getFirst().getLastname());
    }

    @Test
    @DisplayName("Find existing player by id")
    void findExistingPlayerById() {
        Player player = playerRepository.findPlayerById(65);
        assertEquals("Wawrinka", player.getLastname());
        assertEquals("SUI", player.getCountry().getCode());
        assertEquals(183, player.getData().getHeight());
    }

    @Test
    @DisplayName("Find not existing player by id")
    void findNotExistingPlayerById() {
        Player player = playerRepository.findPlayerById(124);
        assertNull(player);
    }
}
