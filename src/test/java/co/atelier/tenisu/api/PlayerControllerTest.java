package co.atelier.tenisu.api;

import co.atelier.tenisu.dto.PlayersStatisticsDto;
import co.atelier.tenisu.entity.Player;
import co.atelier.tenisu.entity.SexEnum;
import co.atelier.tenisu.service.PlayerService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(PlayerController.class)
public class PlayerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PlayerService playerService;

    @Test
    @DisplayName("Get all players endpoint")
    public void getAllPlayers() throws Exception {
        Player player1 = new Player(125, "Iga", "Swiatek", "IS", SexEnum.F, "", null, null);
        Player player2 = new Player(123, "Carlos", "Alcaraz", "CA", SexEnum.M, "", null, null);
        Player player3 = new Player(12, "John", "McEnroe", "JME", SexEnum.M, "", null, null);

        List<Player> players = Arrays.asList(player1, player2, player3);

        when(playerService.getAllPlayers()).thenReturn(players);

        mockMvc.perform(get("/api/v1/players/")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(3))
                .andExpect(jsonPath("$[0].lastname").value("Swiatek"))
                .andExpect(jsonPath("$[1].firstname").value("Carlos"))
                .andExpect(jsonPath("$[2].id").value(12));
    }

    @Test
    @DisplayName("Get all players endpoint with no players")
    public void getAllPlayersWithNoData() throws Exception {
        List<Player> players = Collections.emptyList();

        when(playerService.getAllPlayers()).thenReturn(players);

        mockMvc.perform(get("/api/v1/players/")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(0));
    }

    @Test
    @DisplayName("Get player by id who exists")
    public void getPlayerById() throws Exception {
        Player player = new Player(125, "Iga", "Swiatek", "IS", SexEnum.F, "", null, null);

        when(playerService.getPlayerById(anyInt())).thenReturn(player);

        mockMvc.perform(get("/api/v1/players/125")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.lastname").value("Swiatek"));
    }

    @Test
    @DisplayName("Get player by id not found")
    public void getPlayerByIdNotFound() throws Exception {
        when(playerService.getPlayerById(anyInt())).thenReturn(null);

        mockMvc.perform(get("/api/v1/players/125")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(content().string(""));
    }

    @Test
    @DisplayName("Get players statistics")
    public void getPlayersStatistics() throws Exception {
        PlayersStatisticsDto playersStatisticsDto = new PlayersStatisticsDto("FR", new BigDecimal("25.55"), new BigDecimal(180));
        when(playerService.calculatePlayersStatistics()).thenReturn(playersStatisticsDto);

        mockMvc.perform(get("/api/v1/players/statistics")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.countryCode").value("FR"))
                .andExpect(jsonPath("$.averageBMI").value("25.55"))
                .andExpect(jsonPath("$.medianHeight").value("180"));
    }
}
