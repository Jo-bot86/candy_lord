package de.candylord;

import de.candylord.candy.Candy;
import de.candylord.candy.CandyName;
import de.candylord.controller.Controller;
import de.candylord.location.Location;
import de.candylord.location.city.Chicago;
import de.candylord.location.city.Detroit;
import de.candylord.location.city.LosAngeles;
import de.candylord.location.city.NewYork;
import de.candylord.player.Player;
import de.candylord.shell.Shell;

import java.util.List;

public class Application {

    public static void main(String[] args) {

        List<Location> cities = List.of(new Detroit(), new Chicago(), new NewYork(), new LosAngeles());
        Player player = new Player(cities);
        Controller controller = new Controller(player);
        Shell shell = new Shell(controller);
        shell.run();

//        shell.createOverview();
//        System.out.println("buy ChewGum");
//        player.buyCandy(player.getCurrentLocation().getCandies().get(0),1);
//        shell.createOverview();
//        System.out.println("travel to Chicago");
//        player.travelTo(cities.get(1));
//        shell.createOverview();
//        System.out.println("buy 4 Marshmallows");
//        player.buyCandy(player.getCurrentLocation().getCandies().get(4),3);
//        shell.createOverview();
//        System.out.println("travel to Detroit");
//        player.travelTo(cities.get(0));
//        shell.createOverview();
//        System.out.println("sell 2 Marshmallows");
//        player.sellCandy(player.getCurrentLocation().getCandies().get(4),2);
//        shell.createOverview();
//        player.sellCandy(player.getCurrentLocation().getCandies().get(4),2);

    }
}
