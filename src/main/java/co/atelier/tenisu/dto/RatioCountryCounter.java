package co.atelier.tenisu.dto;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static java.math.BigDecimal.*;

public class RatioCountryCounter {
    private int wins;
    private int total;

    public RatioCountryCounter(int wins, int total) {
        this.wins = wins;
        this.total = total;
    }

    public int getWins() {
        return wins;
    }

    public void setWins(int wins) {
        this.wins = wins;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public void addWins(int wins) {
        this.wins += wins;
    }

    public void addTotal(int total) {
        this.total += total;
    }

    public BigDecimal getRatio() {
        if (total == 0) {
            return ZERO;
        }
        return new BigDecimal(wins).divide(new BigDecimal(total), 2, RoundingMode.HALF_UP);
    }
}
