package de.candylord.controller;

import de.candylord.candy.Candy;
import de.candylord.location.Location;
import de.candylord.player.Player;

import java.util.Random;

/**
 * Represent the game controller.
 */
public class Controller {

    /**
     * Represents a player
     */
    private final Player player;

    /**
     * Contains the game state
     */
    private boolean gameOver;

    /**
     * Contains the event message
     */
    private String eventMessage = "";

    /**
     * Initializes the player
     * @param player
     */
    public Controller(Player player) {
        this.player = player;
    }

    /**
     * Triggers a random event
     * @param randNumber random number between 1 and 10 (excl)
     */
    public void triggerRandomEvent(int randNumber) {
        Random rand = new Random();
        switch (randNumber) {
            case 1 -> {
                player.getGifted(() -> {
                    int gift = rand.nextInt(100, 1000);
                    player.setCash(player.getCash() + gift);
                    eventMessage +=
                            String.format("|Ey Buddy, today is your Birthday. Your grandma gives ya %4d bugs  |", gift);
                });
            }
            case 2 -> {
                player.getMugged(() -> {
                    if(player.getCash() > 0){
                        int lostMoney = rand.nextInt(1, player.getCash());
                        player.setCash(player.getCash()- lostMoney);
                        eventMessage +=
                                String.format("|Damn, you got mugged by some kids. You lost %-4d                   |", lostMoney);
                    }
                });
            }
        }
    }

    /**
     * Tries to process buy candy. Throws an exception if it's not possible.
     * @param candy the candy to buy
     * @param amount the amount of candies to buy
     */
    public void tryToBuyCandy(Candy candy, int amount) {
        try {
            player.buyCandy(candy, amount);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Tries to process sell candy. Throws an exception if it's not possible.
     * @param candy the candy to sell
     * @param amount the amount of candies to sell
     */
    public void tryToSellCandy(Candy candy, int amount) {
        try {
            player.sellCandy(candy, amount);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Tries to travel to location
     * @param location the target location
     * @return true if and only if it's possible to travel
     */
    public boolean tryToTravel(Location location) {
        try {
            player.travelTo(location);
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    /**
     * Checks if the Game ist over and sets gameOver true if the dayCounter of player
     * is greater than 30 or if the player has not enough money to buy at least one chew gum
     */
    public void checkIfGameIsOver() {
        if (player.getDayCounter() > 30) gameOver = true;
        player.getCurrentLocation().getCandies().forEach(candy -> {
            if (candy.getName().equals("Chew Gum") && candy.getPrice() > player.getCash()) {
                gameOver = true;
            }
        });
    }

    public Player getPlayer() {
        return player;
    }

    public String getEventMessage() {
        return eventMessage;
    }

    public void setEventMessage(String eventMessage) {
        this.eventMessage = eventMessage;
    }
}
