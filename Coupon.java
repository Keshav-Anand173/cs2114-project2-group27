// -------------------------------------------------------------------------
/**
 * Represents a coupon that can be used to purchase items
 * 
 * @author Keshav Anand
 * @version 10.14.2026
 */
public class Coupon {
    // ~ Fields ................................................................
    private String category;
    private double discount;

    // ----------------------------------------------------------
    /**
     * Create a new Coupon object.
     * 
     * @param category
     *            the coupon's category
     * @param discount
     *            the coupon's discount
     */
    // ~ Constructors ..........................................................
    public Coupon(String category, double discount) {
        setCategory(category);
        setDiscount(discount);
    }


    // ----------------------------------------------------------
    /**
     * getter method for the item's category
     * 
     * @return the item's category
     */
    // ~Public Methods ........................................................
    public String getCategory() {
        return category;
    }


    // ----------------------------------------------------------
    /**
     * getter method for the item's discount
     * 
     * @return the item's discount
     */
    public double getDiscount() {
        return discount;
    }


    // ----------------------------------------------------------
    /**
     * setter method for the item's category
     * 
     * @param category
     */
    public void setCategory(String category) {
        if (category == null || category.trim().isEmpty()) {
            throw new IllegalArgumentException("Category cannot be empty.");
        }
        this.category = category;
    }


    // ----------------------------------------------------------
    /**
     * Setter method for the item's discount
     * 
     * @param discount
     */
    public void setDiscount(double discount) {
        if (discount < 0 || discount > 100) {
            throw new IllegalArgumentException(
                "Discount must be between 0 and 100.");
        }
        this.discount = discount;
    }


    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        else if (!(obj instanceof Coupon)) {
            return false;
        }

        Coupon other = (Coupon)obj;
        return (category.equals(other.getCategory()) && discount == other
            .getDiscount());
    }
}
