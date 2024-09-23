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

    @Override
    public List<Player> findAllPlayers() {
        return getPlayersListFromJson();
    }

    private List<Player> getPlayersListFromJson() {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            InputStream inputStream = getClass().getResourceAsStream("/static/headtohead.json");
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

