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
        setName(name);
        setPrice(price);
        setCategory(category);
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
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Item name cannot be empty.");
        }
        this.name = name;
    }


    // ----------------------------------------------------------
    /**
     * sets the item's price
     * 
     * @param price
     */
    public void setPrice(double price) {
        if (price < 0) {
            throw new IllegalArgumentException("Price cannot be negative.");
        }
        this.price = price;
    }


    // ----------------------------------------------------------
    /**
     * sets the item's category
     * 
     * @param category
     */
    public void setCategory(String category) {
        if (category == null || category.trim().isEmpty()) {
            throw new IllegalArgumentException("Category cannot be empty.");
        }
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
