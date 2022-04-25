package de.candylord.player;

import de.candylord.candy.Candy;
import de.candylord.events.Giftable;
import de.candylord.events.Muggable;
import de.candylord.location.Location;
import de.candylord.location.city.Chicago;
import de.candylord.location.city.Detroit;
import de.candylord.location.city.NewYork;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * Represents a Player
 */
public class Player {

    /**
     * Represents the start money of a player
     */
    private static final int START_CASH = 5000;

    /**
     * Represents the maximum amount of candies a player can hold
     */
    public static final int MAX_CANDY_AMOUNT = 10;

    /**
     * Contains the current Location of the player
     */
    private Location currentLocation;

    private int dayCounter = 1;

    private final List<Location> locations;

    /**
     * Contains the cash of a player
     */
    private int cashOnHand;

    private int currentAmountOfCandies;

    /**
     * Contains candy names as key and the amount a player holds as value
     */
    private Map<Candy, Integer> candyOnHand;

    public Player(List<Location> locations) {
        this.locations = locations;
        cashOnHand = START_CASH;
        initRandomLocation();
        initCandyOnHand();
    }

    public void getMugged(Muggable robber){
        robber.mug();
    }

    public void getGifted(Giftable patron){
        patron.give();
    }

    public void sellCandy(Candy candyToSell, int amount) throws Exception{
        candyOnHand.keySet().forEach(candy -> {
            if(candy.getName().equals(candyToSell.getName())){
                if(candyOnHand.get(candy) < amount){
                    throw new IllegalArgumentException("you've got not enough candies of this type");
                }else{
                    int currPrice = currentLocation.getCandies().stream()
                            .filter(candy1 -> candy1.getName().equals(candy.getName()))
                            .collect(Collectors.toList()).get(0).getPrice();
                    candyOnHand.put(candy, candyOnHand.get(candy) - amount);
                    cashOnHand += currPrice * amount;
                }
            }
        });
    }

    public void buyCandy(Candy wantedCandy, int amount) throws Exception{
        if(amount > MAX_CANDY_AMOUNT || MAX_CANDY_AMOUNT-currentAmountOfCandies < amount){
            throw new IllegalArgumentException("you can't hold that much candies");
        }
        List<Candy> candies = currentLocation.getCandies();
        for (Candy candy : candies) {
            if(candy.getName().equals(wantedCandy.getName())) {
                if (candy.getPrice() * amount > cashOnHand){
                    throw new IllegalArgumentException("you've got not enough money");
                }
                else{
                    addCandiesToHand(wantedCandy, amount, candy.getPrice());
                }
                break;
            }
        }
    }

    public void travelTo(Location location) throws Exception{
        boolean enoughCash = false;
        int travelCosts = 0;
        for (Location city : locations) {
            if(city.equals(location) && !location.equals(currentLocation)){
                switch (city.getClass().getSimpleName()) {
                    case "Detroit" -> {
                        enoughCash = checkForEnoughCash(Detroit.TRAVEL_COST);
                        travelCosts = Detroit.TRAVEL_COST;
                    }
                    case "Chicago" -> {
                        enoughCash = checkForEnoughCash(Chicago.TRAVEL_COST);
                        travelCosts = Chicago.TRAVEL_COST;
                    }
                    case "NewYork" -> {
                        enoughCash = checkForEnoughCash(NewYork.TRAVEL_COST);
                        travelCosts = NewYork.TRAVEL_COST;
                    }
                }
                if(enoughCash){
                    currentLocation = city;
                    cashOnHand -= travelCosts;
                    location.createNewPrices();
                    dayCounter++;
                    break;
                }else{
                    throw new IllegalArgumentException("Not enough money bro");
                }
            }else if(city.equals(location) && location.equals(currentLocation)){
                throw new IllegalArgumentException("Are you stoned???");
            }
        }
    }

    private boolean checkForEnoughCash(int travelCost) {
        return travelCost <= cashOnHand;
    }

    public void addCandiesToHand(Candy wantedCandy, int amount, int price) {
        currentAmountOfCandies += amount;
        candyOnHand.keySet().forEach(candy -> {
            if(candy.getName().equals(wantedCandy.getName())){
                candyOnHand.put(candy, candyOnHand.get(candy) +amount);
            }
        });
        cashOnHand -= price * amount;
    }

    private void initRandomLocation(){
        Random rand = new Random();
        currentLocation = locations.get(rand.nextInt(0, locations.size()));
    }

    private void initCandyOnHand() {
        candyOnHand = new HashMap<>();
        for(Candy candy: currentLocation.getCandies()){
            candyOnHand.put(candy, 0);
        }
    }

    public Location getCurrentLocation() {
        return currentLocation;
    }

    public void setCurrentLocation(Location currentLocation) {
        this.currentLocation = currentLocation;
    }

    public int getCash() {
        return cashOnHand;
    }

    public void setCash(int cashOnHand) {
        this.cashOnHand = cashOnHand;
    }

    public Map<Candy, Integer> getCandyOnHand() {
        return candyOnHand;
    }

    public void setCandyOnHand(Map<Candy, Integer> candyOnHand) {
        this.candyOnHand = candyOnHand;
    }

    public List<Location> getLocations() {
        return locations;
    }

    public int getDayCounter() {
        return dayCounter;
    }

    @Override
    public String toString() {
        return "Player{" +
                "currentLocation=" + currentLocation +
                ", cashOnHand=" + cashOnHand +
                '}';
    }
}
