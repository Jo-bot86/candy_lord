package de.candylord.controller;

import de.candylord.candy.Candy;
import de.candylord.location.Location;
import de.candylord.player.Player;

import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Controller {

    private final Player player;

    private boolean gameOver;

    private String eventMessage = "";


    public Controller(Player player) {
        this.player = player;
    }


    public void triggerRandomEvent(int randNumber) {
        Random rand = new Random();
        switch (randNumber) {
            case 1 -> {
                player.getGifted(() -> {
                    int gift = rand.nextInt(100, 1000);
                    player.setCash(player.getCash() + gift);
                    eventMessage +=
                            String.format("Ey Buddy, today is your Birthday. Your grandma gives ya %4d bugs", gift);
                });
            }
            case 2 -> {
                player.getMugged(() -> {
                    if(player.getCash() > 0){
                        int lostMoney = rand.nextInt(1, player.getCash());
                        player.setCash(player.getCash()- lostMoney);
                        eventMessage +=
                                String.format("Damn, you were robbed by a gang of kids. They took %4d bugs from you", lostMoney);
                    }
                });
            }
        }
    }

    public void tryToBuyCandy(Candy candy, int amount) {
        try {
            player.buyCandy(candy, amount);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void tryToSellCandy(Candy candy, int amount) {
        try {
            player.sellCandy(candy, amount);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

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
