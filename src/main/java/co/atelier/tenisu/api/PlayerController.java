package co.atelier.tenisu.api;

import co.atelier.tenisu.dto.PlayersStatisticsDto;
import co.atelier.tenisu.service.PlayerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import co.atelier.tenisu.entity.Player;

import java.util.List;

@RestController
@RequestMapping("/api/v1/players")
public class PlayerController {

    private final PlayerService playerService;

    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @GetMapping("/")
    public ResponseEntity<List<Player>> getPlayers() {
        List<Player> players = this.playerService.getAllPlayers();
        return ResponseEntity.ok().body(players);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Player> getPlayerById(@PathVariable int id) {
        Player player = this.playerService.getPlayerById(id);
        if (player == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok().body(player);
    }

    @GetMapping("/statistics")
    public ResponseEntity<PlayersStatisticsDto> getPlayersStatistics() {
        PlayersStatisticsDto stats = this.playerService.calculatePlayersStatistics();

        return ResponseEntity.ok().body(stats);
    }
}
