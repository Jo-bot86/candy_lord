package de.candylord.candy;

/**
 * Represents a candy
 */
public class Candy {

    /**
     * Contains the name of the candy
     */
    private CandyName name;

    /**
     * Contains the price of the candy
     */
    private int price;

    public Candy(CandyName name, int price) {
        this.name = name;
        this.price = price;
    }

    /**
     * Provides the name of the candy
     * @return name of the candy
     */
    public CandyName getName() {
        return name;
    }

    /**
     * Sets the name of the candy
     * @param name name to set for the candy
     */
    public void setName(CandyName name) {
        this.name = name;
    }

    /**
     * Provides the price of the candy
     * @return price of the candy
     */
    public int getPrice() {
        return price;
    }

    /**
     * Sets the name of the candy
     * @param price price to set for the candy
     */
    public void setPrice(int price) {
        this.price = price;
    }

    /**
     * Provides the name and the price of a candy for testing purposes
     * @return name and price of the candy
     */
    @Override
    public String toString() {
        return "Candy{" +
                "name=" + name +
                ", price=" + price +
                '}';
    }
}
