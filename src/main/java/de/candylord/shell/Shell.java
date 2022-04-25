package de.candylord.shell;

import de.candylord.candy.Candy;
import de.candylord.controller.Controller;
import de.candylord.location.Location;
import de.candylord.player.Player;

import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Shell {

    public static final String NO_VALID_INT = "No valid integer";

    private static final String DEVIDER = "+-------------------------------------------------------------------+%n";

    private final Controller controller;

    public Shell(Controller controller) {
        this.controller = controller;
    }


    public void createOverview() {
        Player player = controller.getPlayer();
        List<Candy> candies = player.getCurrentLocation().getCandies();

        System.out.format(DEVIDER);
        System.out.format("|                             CANDY LORD                            |%n");
        System.out.format(DEVIDER);
        System.out.printf("| Day: %-8s                                       max Days: 30  |%n", controller.getPlayer().getDayCounter());
        System.out.format(DEVIDER);
        System.out.printf("| location: %-8s           max amount: %d           cash:$%-3.0f  |%n", player.getCurrentLocation().getLocationName(), Player.MAX_CANDY_AMOUNT, (double) player.getCash());
        System.out.format(DEVIDER);
        System.out.format("|       Candies        |  on hand | street prices |  travel cost    |%n");
        System.out.format("+----------------------+----------+---------------+-----------------+%n");

        for (int i = candies.size() - 1, j = 0; i >= 0; i--, j++) {
            Candy candy = candies.get(i);
            Candy candyForPrice = candies.get(i);
            String cityName = "";
            String travelCosts = "";
            if (j < player.getLocations().size()) {
                cityName = player.getLocations().get(j).getLocationName();
                if (!player.getCurrentLocation().getLocationName().equals(cityName)) {
                    travelCosts += "$" + player.getLocations().get(j).getTravelCosts();
                }

            }
            for (Candy candy1 : player.getCandyOnHand().keySet()) {
                if (candy1.getName().equals(candy.getName())) candy = candy1;
            }
            System.out.printf("| %-20s | %8d | $%-12.0f | %-10s%-5s |%n",
                    candy.getName(), player.getCandyOnHand().get(candy),
                    (double) candyForPrice.getPrice(), cityName, travelCosts);
        }
        System.out.format(DEVIDER);
        if(!controller.getEventMessage().equals("")) {
            System.out.println(controller.getEventMessage());
            controller.setEventMessage("");
            System.out.format(DEVIDER);

        }

        System.out.println();
    }


    public void run() {
        Scanner sc = new Scanner(System.in);
        while (!controller.isGameOver()) {
            createOverview();
            showActions();
            String input = sc.next();

            try {
                evaluateActionInput(input);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            controller.checkIfGameIsOver();
            if(controller.isGameOver()) {
                createOverview();
                System.out.println("Restart game? (Y)es or (N)o");
            }
        }
        sc.close();
    }

    private void createTravelToDialog() {
        io.vavr.collection.List<Location> locations = io.vavr.collection.List.ofAll(controller.getPlayer().getLocations());
        locations.forEachWithIndex((location, index) -> System.out.println(index + 1  + ". " + location.getLocationName()));
        try {
            Scanner sc = new Scanner(System.in);
            int citySelection = sc.nextInt();
            switch (citySelection){
                case 1,2,3,4,5,6 -> {
                    if(controller.tryToTravel(locations.get(citySelection-1)))
                        controller.triggerRandomEvent(new Random().nextInt(1, 10));
                }
                default -> System.out.println("This city doesn't exist");
            }
        } catch (NumberFormatException e) {
            System.out.println(NO_VALID_INT);
        }

    }

    private void createSellDialog() {
        List<Candy> candies = controller.getPlayer().getCurrentLocation().getCandies();
        for (int i = candies.size() - 1; i >= 0; i--) {
            System.out.println(candies.size() - i + ". " + candies.get(i).getName());
        }
        try {
            Scanner sc = new Scanner(System.in);
            int candySelection = candies.size() - Integer.parseInt(sc.nextLine());
            switch (candySelection) {
                case 0, 1, 2, 3, 4, 5, 6 -> createAmountDialogSell(candies.get(candySelection));
                default -> {
                    System.out.println("There is no candy of this type. Try it again.");
                    createSellDialog();
                }
            }
        } catch (NumberFormatException e) {
            System.out.println(NO_VALID_INT);
        }
    }

    private void createBuyDialog() {
        List<Candy> candies = controller.getPlayer().getCurrentLocation().getCandies();
        for (int i = candies.size() - 1; i >= 0; i--) {
            System.out.println(candies.size() - i + ". " + candies.get(i).getName());
        }
        try {
            Scanner sc = new Scanner(System.in);
            int candySelection = candies.size() - Integer.parseInt(sc.nextLine());
            switch (candySelection) {
                case 0, 1, 2, 3, 4, 5, 6 -> createAmountDialogBuy(candies.get(candySelection));
                default -> {
                    System.out.println("There is no candy of this type. Try it again.");
                    createBuyDialog();
                }
            }
        } catch (NumberFormatException e) {
            System.out.println(NO_VALID_INT);
        }
    }



    private void createAmountDialogBuy(Candy candy) {
        Scanner sc = new Scanner(System.in);
        System.out.println("How much do you want?");
        int inputAmount = Integer.parseInt(sc.nextLine());
        switch (inputAmount) {
            case 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 -> controller.tryToBuyCandy(candy, inputAmount);
            default -> System.out.println("Invalid amount. Try it again");
        }
    }

    private void createAmountDialogSell(Candy candy) {
        Scanner sc = new Scanner(System.in);
        System.out.println("How much do you want?");
        int inputAmount = Integer.parseInt(sc.nextLine());
        switch (inputAmount) {
            case 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 -> controller.tryToSellCandy(candy, inputAmount);
            default -> System.out.println("Invalid amount. Try it again");
        }
    }

    public void evaluateActionInput(String input) throws Exception {
        switch (input.toLowerCase().trim()) {
            case "b" -> createBuyDialog();
            case "s" -> createSellDialog();
            case "j" -> createTravelToDialog();
            default -> throw new IllegalArgumentException("Try it again, there are just three possibilities");
        }
    }




    public void showActions() {
        System.out.println("What you plan to do, dude?");
        System.out.println("(B)uy some candies");
        System.out.println("(S)ell some candies");
        System.out.println("(J)et to another city");
    }
}
