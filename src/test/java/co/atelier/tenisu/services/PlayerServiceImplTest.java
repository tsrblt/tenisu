package co.atelier.tenisu.services;

import co.atelier.tenisu.entity.Data;
import co.atelier.tenisu.entity.Player;
import co.atelier.tenisu.entity.SexEnum;
import co.atelier.tenisu.repository.PlayerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class PlayerServiceImplTest {

    @InjectMocks
    private PlayerServiceImpl playerService;

    @Mock
    private PlayerRepository playerRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Find all players when return none")
    void getAllPlayersWhenZero() {
        when(playerRepository.findAllPlayers()).thenReturn(new ArrayList<>());
        assertEquals(0, this.playerService.getAllPlayers().size());
    }

    @Test
    @DisplayName("Find all players when return null")
    void getAllPlayersWhenNull() {
        when(playerRepository.findAllPlayers()).thenReturn(null);
        assertEquals(0, this.playerService.getAllPlayers().size());
    }

    @Test
    @DisplayName("Find all players when return singleton list")
    void getAllPlayersWithOne() {
        Data data = new Data(89, 12, 98, 180, 108, new int[]{0, 0, 0, 0, 0});
        Player player = new Player(12, "John", "McEnroe", "JME", SexEnum.M, "", null, data);
        when(playerRepository.findAllPlayers()).thenReturn(List.of(player));
        assertEquals(1, this.playerService.getAllPlayers().size());
        assertEquals("McEnroe", this.playerService.getAllPlayers().getFirst().getLastname());
    }

    @Test
    @DisplayName("Find all players sorted by rank when return multiple players")
    void getAllPlayersSorted() {
        Data data1 = new Data(100, 12, 98, 180, 108, new int[]{0, 0, 0, 0, 0});
        Player player1 = new Player(125, "Iga", "Swiatek", "IS", SexEnum.F, "", null, data1);

        Data data2 = new Data(50, 12, 98, 180, 108, new int[]{0, 0, 0, 0, 0});
        Player player2 = new Player(123, "Carlos", "Alcaraz", "CA", SexEnum.M, "", null, data2);

        Data data3 = new Data(1, 12, 98, 180, 108, new int[]{0, 0, 0, 0, 0});
        Player player3 = new Player(12, "John", "McEnroe", "JME", SexEnum.M, "", null, data3);

        when(playerRepository.findAllPlayers()).thenReturn(List.of(player1, player2, player3));
        assertEquals(3, this.playerService.getAllPlayers().size());
        assertEquals("McEnroe", this.playerService.getAllPlayers().getFirst().getLastname());
        assertEquals("Alcaraz", this.playerService.getAllPlayers().get(1).getLastname());
        assertEquals("Swiatek", this.playerService.getAllPlayers().getLast().getLastname());
    }

    @Test
    @DisplayName("Find all players sorted by rank when return multiple players")
    void getAllPlayersSortedSameRank() {
        Data data1 = new Data(100, 12, 98, 180, 108, new int[]{0, 0, 0, 0, 0});
        Player player1 = new Player(125, "Iga", "Swiatek", "IS", SexEnum.F, "", null, data1);

        Data data2 = new Data(100, 12, 98, 180, 108, new int[]{0, 0, 0, 0, 0});
        Player player2 = new Player(123, "Carlos", "Alcaraz", "CA", SexEnum.M, "", null, data2);

        when(playerRepository.findAllPlayers()).thenReturn(List.of(player1, player2));
        assertEquals(2, this.playerService.getAllPlayers().size());
        assertEquals("Swiatek", this.playerService.getAllPlayers().getFirst().getLastname());
        assertEquals("Alcaraz", this.playerService.getAllPlayers().getLast().getLastname());
    }
}
