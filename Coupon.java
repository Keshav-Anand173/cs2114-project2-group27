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
        this.category = category;
        this.discount = discount;
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
        this.category = category;
    }


    // ----------------------------------------------------------
    /**
     * Setter method for the item's discount
     * 
     * @param discount
     */
    public void setDiscount(double discount) {
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
