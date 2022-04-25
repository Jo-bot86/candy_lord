package de.candylord.location.city;

import de.candylord.candy.Candy;
import de.candylord.candy.CandyName;
import de.candylord.location.Location;

import java.awt.*;
import java.util.List;

public class LosAngeles extends Location {
    public static final int MIN_PRICE_CHEW_GUM = 6;

    public static final int MAX_PRICE_CHEW_GUM = 15;

    public static final int MIN_PRICE_LOLLIPOP = 19;

    public static final int MAX_PRICE_LOLLIPOP = 30;

    public static final int MIN_PRICE_COOKIE = 35;

    public static final int MAX_PRICE_COOKIE = 50;

    public static final int MIN_PRICE_CHOCOLATE_BAR = 52;

    public static final int MAX_PRICE_CHOCOLATE_BAR = 150;

    public static final int MIN_PRICE_MARSHMALLOW = 250;

    public static final int MAX_PRICE_MARSHMALLOW = 360;

    public static final int MIN_PRICE_BROWNIE = 420;

    public static final int MAX_PRICE_BROWNIE = 630;

    public static final int MIN_PRICE_GINGER_BREAD = 900;

    public static final int MAX_PRICE_GINGER_BREAD = 1020;


    public LosAngeles() {
        super("L.A.");
        super.setCandies(createCandies());
        super.coordinates = new Point(-20,2);
    }

    @Override
    protected java.util.List<Candy> createCandies() {
        return List.of(
                new Candy(CandyName.CHEW_GUM, rand.nextInt(MIN_PRICE_CHEW_GUM, MAX_PRICE_CHEW_GUM)),
                new Candy(CandyName.LOLLIPOP, rand.nextInt(MIN_PRICE_LOLLIPOP, MAX_PRICE_LOLLIPOP)),
                new Candy(CandyName.COOKIE, rand.nextInt(MIN_PRICE_COOKIE, MAX_PRICE_COOKIE)),
                new Candy(CandyName.CHOCOLATE_BAR, rand.nextInt(MIN_PRICE_CHOCOLATE_BAR, MAX_PRICE_CHOCOLATE_BAR)),
                new Candy(CandyName.MARSHMALLOW, rand.nextInt(MIN_PRICE_MARSHMALLOW, MAX_PRICE_MARSHMALLOW)),
                new Candy(CandyName.BROWNIE, rand.nextInt(MIN_PRICE_BROWNIE, MAX_PRICE_BROWNIE)),
                new Candy(CandyName.GINGER_BREAD, rand.nextInt(MIN_PRICE_GINGER_BREAD, MAX_PRICE_GINGER_BREAD)));
    }

    @Override
    public void createNewPrices() {
        candies.forEach(candy -> candy.setPrice(getPriceForSpecialCandy(candy)));
    }

    private int getPriceForSpecialCandy(Candy candy) {
        return switch (candy.getName()) {
            case CHEW_GUM -> rand.nextInt(MIN_PRICE_CHEW_GUM, MAX_PRICE_CHEW_GUM);
            case LOLLIPOP -> rand.nextInt(MIN_PRICE_LOLLIPOP, MAX_PRICE_LOLLIPOP);
            case COOKIE -> rand.nextInt(MIN_PRICE_COOKIE, MAX_PRICE_COOKIE);
            case CHOCOLATE_BAR -> rand.nextInt(MIN_PRICE_CHOCOLATE_BAR, MAX_PRICE_CHOCOLATE_BAR);
            case MARSHMALLOW -> rand.nextInt(MIN_PRICE_MARSHMALLOW, MAX_PRICE_MARSHMALLOW);
            case BROWNIE -> rand.nextInt(MIN_PRICE_BROWNIE, MAX_PRICE_BROWNIE);
            case GINGER_BREAD -> rand.nextInt(MIN_PRICE_GINGER_BREAD, MAX_PRICE_GINGER_BREAD);
        };
    }
}
