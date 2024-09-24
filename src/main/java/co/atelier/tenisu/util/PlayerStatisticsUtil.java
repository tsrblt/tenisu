package co.atelier.tenisu.util;

import co.atelier.tenisu.dto.RatioCountryCounter;
import co.atelier.tenisu.entity.Player;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.math.BigDecimal.TWO;
import static java.math.BigDecimal.ZERO;
import static java.math.RoundingMode.HALF_UP;
import static java.util.Comparator.comparing;
import static org.springframework.util.CollectionUtils.isEmpty;

public abstract class PlayerStatisticsUtil {
    public static BigDecimal calculateAverageBMI(List<Player> players) {
        if (isEmpty(players)) {
            return ZERO;
        }
        BigDecimal result = new BigDecimal(0);
        for (Player player : players) {
            result = result.add(player.getData().getBMI());
        }
        BigDecimal nbPlayers = new BigDecimal(players.size());
        return result.divide(nbPlayers, 2, HALF_UP);
    }

    public static BigDecimal calculateMedianHeight(List<Player> players) {
        if (isEmpty(players)) {
            return ZERO;
        }
        players = players.stream().sorted(comparing(player -> player.getData().getHeight())).collect(Collectors.toList());
        int nbPlayers = players.size();
        if (nbPlayers % 2 == 1) {
            return new BigDecimal(players.get((nbPlayers - 1) / 2).getData().getHeight());
        }
        BigDecimal midHeight1 = new BigDecimal(players.get(nbPlayers / 2).getData().getHeight());
        BigDecimal midHeight2 = new BigDecimal(players.get((nbPlayers / 2) - 1).getData().getHeight());

        return  midHeight1.add(midHeight2).divide(TWO, 2, HALF_UP);
    }

    public static String calculateCountryBestRatio(List<Player> players) {
        if (isEmpty(players)) {
            return "";
        }
        Map<String, RatioCountryCounter> countryRatio = computeCountryRatioMap(players);
        String result = null;
        BigDecimal bestRatio = ZERO;
        for (Map.Entry<String, RatioCountryCounter> elm : countryRatio.entrySet()) {
            BigDecimal currentRatio = elm.getValue().getRatio();
            if (currentRatio.compareTo(bestRatio) == 1) {
                bestRatio = currentRatio;
                result = elm.getKey();
            } else if (currentRatio.compareTo(bestRatio) == 0) {
                result += ", " + elm.getKey();
            }
        }
        return result;
    }

    private static Map<String, RatioCountryCounter> computeCountryRatioMap(List<Player> players) {
        Map<String, RatioCountryCounter> countryRatio = new HashMap<>();
        for (Player player: players) {
            if (player.getCountry() == null || player.getData() == null) {
                continue;
            }
            int total = player.getData().getLast().length;
            int wins = player.getData().getWins();
            String countryCode = player.getCountry().getCode();
            RatioCountryCounter counter = countryRatio.putIfAbsent(countryCode, new RatioCountryCounter(wins, total));

            if (counter != null) {
                counter.addWins(wins);
                counter.addTotal(total);
                countryRatio.replace(countryCode, counter);
            }
        }
        return countryRatio;
    }
}
