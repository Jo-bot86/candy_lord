import de.candylord.candy.Candy;
import de.candylord.candy.CandyName;
import de.candylord.location.Location;
import de.candylord.location.city.Chicago;
import de.candylord.location.city.Detroit;
import de.candylord.player.Player;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class PlayerTest {

    List<Location> locations = List.of(new Detroit(), new Chicago());
    Player player = new Player(locations);

    @Test
    void initCandyOnHand_candyOnHandShouldNotBeNullTest(){
        assertNotNull(player.getCandyOnHand());
    }

    @Test
    void initCandyOnHand_candyOnHandSizeShouldBeSevenTest(){
        assertEquals(7, player.getCandyOnHand().size());
    }

    @Test
    void initCandyOnHand_candyOnHandValuesShouldBeZeroTest(){
        for (Integer value : player.getCandyOnHand().values()) {
            assertEquals(0, value);
        }
    }

    @Test
    void initCandyOnHand_candyOnHandKeysShouldBeOfTypeCandyNameTest(){
        player.getCandyOnHand().keySet().forEach(candy -> assertTrue(candy instanceof Candy));
    }

//    *****************************************************************************************************

    @Test
    void initRandomLocation_currentLocationShouldNotBeNullTest(){
        assertNotNull(player.getCurrentLocation());
    }


//   *******************************************************************************************************

    @Test
    void travelTo(){
        // set currentLocation to Detroit
       /* player.setCurrentLocation(locations.get(0));
        System.out.println(player.getCandyOnHand());
        player.buyCandy(player.getCurrentLocation().getCandies().get(0), 2);
        System.out.println(player.getCandyOnHand());;*/

    }

    @Test
    void addCandiesToHand(){

    }



}
