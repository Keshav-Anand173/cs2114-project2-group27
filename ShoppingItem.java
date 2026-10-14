// -------------------------------------------------------------------------
/**
 * Represents an item the user wants to buy
 * 
 * @author Keshav Anand (906916054)
 * @version 2026.10.14
 */
public class ShoppingItem {
    // ~ Fields ................................................................
    private String name;
    private double price;
    private String category;

    // ----------------------------------------------------------
    /**
     * Create a new ShoppingItem object.
     * 
     * @param name
     *            for the item's name
     * @param price
     *            for the item's price
     * @param category
     *            for the item's category
     */
    // ~ Constructors ..........................................................
    public ShoppingItem(String name, double price, String category) {
        this.name = name;
        this.price = price;
        this.category = category;
    }


    // ----------------------------------------------------------
    /**
     * getter method for name
     * 
     * @return the name of the item
     */
    // ~Public Methods ........................................................
    public String getName() {
        return name;
    }


    // ----------------------------------------------------------
    /**
     * getter method for price
     * 
     * @return the price of the item
     */
    public double getPrice() {
        return price;
    }


    // ----------------------------------------------------------
    /**
     * getter method for category
     * 
     * @return the item's category
     */
    public String getCategory() {
        return category;
    }


    // ----------------------------------------------------------
    /**
     * sets the item's name
     * 
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }


    // ----------------------------------------------------------
    /**
     * sets the item's price
     * 
     * @param price
     */
    public void setPrice(double price) {
        this.price = price;
    }


    // ----------------------------------------------------------
    /**
     * sets the item's category
     * 
     * @param category
     */
    public void setCategory(String category) {
        this.category = category;
    }


    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        else if (!(obj instanceof ShoppingItem)) {
            return false;
        }

        ShoppingItem other = (ShoppingItem)obj;
        return (name.equals(other.getName()) && price == other.getPrice()
            && category.equals(other.getCategory()));
    }
}
