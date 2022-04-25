package de.candylord.location;

import de.candylord.candy.Candy;

import java.awt.*;
import java.util.List;
import java.util.Random;

/**
 * Represents a location
 */
public abstract class Location {

    /**
     * Contains a travelRate
     */
    public static final int travelRate = 50;

    /**
     * Contains the name of the location
     */
    private final String locationName;

    /**
     * Contains a List of all candies
     */
    protected List<Candy> candies;

    protected Random rand = new Random();

    /**
     * contains the travelCosts
     */
    private int travelCosts;

    /**
     * contains the coordinates for this location
     */
    protected Point coordinates;

    public Location(String locationName) {
        this.locationName = locationName;
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

    public void setTravelCosts(int travelCosts) {
        this.travelCosts = travelCosts;
    }

    protected abstract List<Candy> createCandies();

    public abstract void createNewPrices();

    public Point getCoordinates() {
        return coordinates;
    }

    @Override
    public String toString() {
        return "Location{" +
                "locationName='" + locationName + '\'' +
                ", candies=" + candies +
                '}';
    }
}
