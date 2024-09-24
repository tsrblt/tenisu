package co.atelier.tenisu.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static java.math.BigDecimal.*;

public class Data {
    private Integer rank;
    private int points;
    private int weight;
    private int height;
    private int age;
    private int[] last;

    public Data() {}

    public Data(Integer rank, int points, int weight, int height, int age, int[] last) {
        this.rank = rank;
        this.points = points;
        this.weight = weight;
        this.height = height;
        this.age = age;
        this.last = last;
    }

    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int[] getLast() {
        return last;
    }

    public void setLast(int[] last) {
        this.last = last;
    }

    @JsonIgnore
    public BigDecimal getBMI() {
        BigDecimal weight = new BigDecimal(this.weight);
        BigDecimal height = new BigDecimal(this.height);

        return weight.divide(height.multiply(height), 2, RoundingMode.HALF_EVEN).multiply(TEN);
    }

    @JsonIgnore
    public int getWins() {
        int wins = 0;
        for (int result : last) {
            if (result == 1) {
                wins++;
            }
        }
        return wins;
    }
}
