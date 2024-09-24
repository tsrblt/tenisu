package co.atelier.tenisu.repository;

import co.atelier.tenisu.dto.PlayersWrapper;
import co.atelier.tenisu.entity.Player;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Repository
public class PlayerRepositoryImpl implements PlayerRepository {

    private static final String LIST_PLAYERS_JSON = "/static/headtohead.json";

    @Override
    public List<Player> findAllPlayers() {
        return getPlayersListFromJson();
    }

    @Override
    public Player findPlayerById(int id) {
        List<Player> players = getPlayersListFromJson();

        for (Player player : players) {
            if (player.getId() == id) {
                return player;
            }
        }
        return null;
    }

    private List<Player> getPlayersListFromJson() {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            InputStream inputStream = getClass().getResourceAsStream(LIST_PLAYERS_JSON);
            if (inputStream != null) {
                PlayersWrapper wrapper = objectMapper.readValue(inputStream, PlayersWrapper.class);
                return wrapper.getPlayers();
            } else {
                throw new RuntimeException("Cannot find data file");
            }
        } catch (IOException e) {
            return new ArrayList<>();
        }
    }
}

