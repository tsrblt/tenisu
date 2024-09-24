package co.atelier.tenisu.util;

import co.atelier.tenisu.entity.Data;
import co.atelier.tenisu.entity.Player;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static co.atelier.tenisu.entity.SexEnum.F;
import static co.atelier.tenisu.entity.SexEnum.M;
import static java.math.BigDecimal.ZERO;
import static org.junit.jupiter.api.Assertions.assertEquals;

class PlayerStatisticsUtilTest {

    @Test
    @DisplayName("Calculate average BMI with no player")
    void averageBMIWithNoPlayer() {
        BigDecimal result = PlayerStatisticsUtil.calculateAverageBMI(null);
        assertEquals(ZERO, result);

        result = PlayerStatisticsUtil.calculateAverageBMI(new ArrayList<>());
        assertEquals(ZERO, result);
    }

    @Test
    @DisplayName("Calculate average BMI with one player")
    void averageBMIWithOnePlayer() {
        Data data = new Data(100, 12, 98000, 180, 108, new int[]{0, 0, 0, 0, 0});
        Player player = new Player(125, "Iga", "Swiatek", "IS", F, "", null, data);

        BigDecimal result = PlayerStatisticsUtil.calculateAverageBMI(List.of(player));
        assertEquals(player.getData().getBMI(), result);
        assertEquals("30.20", result.toString());
    }

    @Test
    @DisplayName("Calculate average BMI with several players")
    void averageBMIWithSeveralPlayers() {
        Data data1 = new Data(100, 12, 61000, 167, 108, new int[]{0, 0, 0, 0, 0});
        Player player1 = new Player(125, "Iga", "Swiatek", "IS", F, "", null, data1);

        Data data2 = new Data(50, 12, 75000, 187, 108, new int[]{0, 0, 0, 0, 0});
        Player player2 = new Player(123, "Carlos", "Alcaraz", "CA", M, "", null, data2);

        Data data3 = new Data(1, 12, 97000, 179, 108, new int[]{0, 0, 0, 0, 0});
        Player player3 = new Player(12, "John", "McEnroe", "JME", M, "", null, data3);

        BigDecimal result = PlayerStatisticsUtil.calculateAverageBMI(List.of(player1, player2, player3));
        assertEquals("24.53", result.toString());
    }

    @Test
    @DisplayName("Calculate median height with no player")
    void medianHeightWithNoPlayer() {
        BigDecimal result = PlayerStatisticsUtil.calculateMedianHeight(null);
        assertEquals(ZERO, result);

        result = PlayerStatisticsUtil.calculateMedianHeight(new ArrayList<>());
        assertEquals(ZERO, result);
    }

    @Test
    @DisplayName("Calculate median height with one player")
    void medianHeightWithOnePlayer() {
        Data data = new Data(100, 12, 98000, 180, 108, new int[]{0, 0, 0, 0, 0});
        Player player = new Player(125, "Iga", "Swiatek", "IS", F, "", null, data);

        BigDecimal result = PlayerStatisticsUtil.calculateMedianHeight(List.of(player));
        assertEquals(new BigDecimal(player.getData().getHeight()), result);
        assertEquals(180, result.intValue());
    }

    @Test
    @DisplayName("Calculate median height with odd number of players")
    void medianHeightWithOddNumberOfPlayers() {
        Data data1 = new Data(100, 12, 61000, 167, 108, new int[]{0, 0, 0, 0, 0});
        Player player1 = new Player(125, "Iga", "Swiatek", "IS", F, "", null, data1);

        Data data2 = new Data(50, 12, 75000, 187, 108, new int[]{0, 0, 0, 0, 0});
        Player player2 = new Player(123, "Carlos", "Alcaraz", "CA", M, "", null, data2);

        Data data3 = new Data(1, 12, 97000, 179, 108, new int[]{0, 0, 0, 0, 0});
        Player player3 = new Player(12, "John", "McEnroe", "JME", M, "", null, data3);

        BigDecimal result = PlayerStatisticsUtil.calculateMedianHeight(List.of(player1, player2, player3));
        assertEquals(179, result.intValue());
    }

    @Test
    @DisplayName("Calculate median height with even number of players")
    void medianHeightWithEvenNumberOfPlayers() {
        Data data1 = new Data(100, 12, 61000, 167, 108, new int[]{0, 0, 0, 0, 0});
        Player player1 = new Player(125, "Iga", "Swiatek", "IS", F, "", null, data1);

        Data data2 = new Data(50, 12, 75000, 187, 108, new int[]{0, 0, 0, 0, 0});
        Player player2 = new Player(123, "Carlos", "Alcaraz", "CA", M, "", null, data2);

        BigDecimal result = PlayerStatisticsUtil.calculateMedianHeight(List.of(player1, player2));
        assertEquals(177, result.intValue());
    }

    @Test
    @DisplayName("Calculate median height with even number of players 2")
    void medianHeightWithEvenNumberOfPlayers2() {
        Data data1 = new Data(100, 12, 61000, 167, 108, new int[]{0, 0, 0, 0, 0});
        Player player1 = new Player(125, "Iga", "Swiatek", "IS", F, "", null, data1);

        Data data2 = new Data(50, 12, 75000, 187, 108, new int[]{0, 0, 0, 0, 0});
        Player player2 = new Player(123, "Carlos", "Alcaraz", "CA", M, "", null, data2);

        Data data3 = new Data(1, 12, 97000, 179, 108, new int[]{0, 0, 0, 0, 0});
        Player player3 = new Player(12, "John", "McEnroe", "JME", M, "", null, data3);

        Data data4 = new Data(1, 12, 54000, 157, 108, new int[]{0, 0, 0, 0, 0});
        Player player4 = new Player(12, "Venus", "Williams", "VW", F, "", null, data4);

        BigDecimal result = PlayerStatisticsUtil.calculateMedianHeight(List.of(player1, player2, player3, player4));
        assertEquals(173, result.intValue());
    }

}