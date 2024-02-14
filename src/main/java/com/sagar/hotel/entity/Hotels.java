package com.sagar.hotel.entity;

public class Hotels {
    private String name;
    private int rating;
    private int weekDayRate;
    private int weekEndRate;
    private int weekDayReward;
    private int weekEndReward;

    public Hotels() {
    }

    public Hotels(String name, int rating, int weekDayRate, int weekEndRate, int weekDayReward, int weekEndReward) {
        this.name = name;
        this.rating = rating;
        this.weekDayRate = weekDayRate;
        this.weekEndRate = weekEndRate;
        this.weekDayReward = weekDayReward;
        this.weekEndReward = weekEndReward;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public int getWeekDayRate() {
        return weekDayRate;
    }

    public void setWeekDayRate(int weekDayRate) {
        this.weekDayRate = weekDayRate;
    }

    public int getWeekEndRate() {
        return weekEndRate;
    }

    public void setWeekEndRate(int weekEndRate) {
        this.weekEndRate = weekEndRate;
    }

    public int getWeekDayReward() {
        return weekDayReward;
    }

    public void setWeekDayReward(int weekDayReward) {
        this.weekDayReward = weekDayReward;
    }

    public int getWeekEndReward() {
        return weekEndReward;
    }

    public void setWeekEndReward(int weekEndReward) {
        this.weekEndReward = weekEndReward;
    }


    @Override
    public String toString() {
        return "Hotels{" +
                "\nname='" + name +
                "\nrating=" + rating +
                "\nweekDayRate=" + weekDayRate +
                "\nweekEndRate=" + weekEndRate +
                "\nweekDayReward=" + weekDayReward +
                "\nweekEndReward=" + weekEndReward;
    }
}
