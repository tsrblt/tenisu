package co.atelier.tenisu.dto;

import java.math.BigDecimal;

public class PlayersStatisticsDto {
    private String countryCode;
    private BigDecimal averageBMI;
    private BigDecimal medianHeight;

    public PlayersStatisticsDto(String countryCode, BigDecimal averageBMI, BigDecimal medianHeight) {
        this.countryCode = countryCode;
        this.averageBMI = averageBMI;
        this.medianHeight = medianHeight;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public BigDecimal getAverageBMI() {
        return averageBMI;
    }

    public void setAverageBMI(BigDecimal averageBMI) {
        this.averageBMI = averageBMI;
    }

    public BigDecimal getMedianHeight() {
        return medianHeight;
    }

    public void setMedianHeight(BigDecimal medianHeight) {
        this.medianHeight = medianHeight;
    }
}
