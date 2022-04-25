package de.candylord.location;

import de.candylord.candy.Candy;

import java.util.List;
import java.util.Random;

/**
 * Represents a location
 */
public abstract class Location {

    /**
     * Contains the name of the location
     */
    private final String locationName;

    /**
     * Contains a List of all candies
     */
    protected List<Candy> candies;

    protected Random rand = new Random();

    private int travelCosts;

    public Location(String locationName, int travelCosts) {
        this.locationName = locationName;
        this.travelCosts = travelCosts;
    }

    /**
     * Provides the name of the location
     * @return name of the location
     */
    public String getLocationName() {
        return locationName;
    }

    /**
     * Provides a list of all candies
     * @return a list of all candies
     */
    public List<Candy> getCandies() {
        return candies;
    }

    /**
     * Sets a list with all candies
     * @param candies list of candies to set
     */
    public void setCandies(List<Candy> candies) {
        this.candies = candies;
    }

    public int getTravelCosts() {
        return travelCosts;
    }

    protected abstract List<Candy> createCandies();

    public abstract void createNewPrices();

    @Override
    public String toString() {
        return "Location{" +
                "locationName='" + locationName + '\'' +
                ", candies=" + candies +
                '}';
    }
}
