
/**
 * Represents a list of coupons the user has
 **/
import java.text.NumberFormat;

public class CouponList {
    private String category;
    private LinkedChain<Coupon> couponList;

    /**
     * Creates an empty coupon list.
     *
     * @param category
     *            the list's category
     */
    public CouponList(String category) {
        if (category == null) {
            throw new IllegalArgumentException("please enter a category");
        }

        this.category = category;
        couponList = new LinkedChain<Coupon>();
    }


    /**
     * Returns the list's category
     *
     * * @return the category
     */
    public String getCategory() {
        return category;
    }


    /**
     * Adds a coupon to the list.
     *
     * @param coupon
     *            the*coupon to add
     */
    public void addCoupon(Coupon coupon) {
        couponList.add(coupon);
    }


    /**
     * Removes a coupon from the list.
     *
     * @param coupon
     *            the coupon to remove
     * @return returns true if the coupon was removed
     */
    public boolean removeCoupon(Coupon coupon) {
        return couponList.remove(coupon);
    }


    /**
     * Determines whether the list contains a coupon.
     *
     * @param coupon
     *            the coupon to find
     * @return true if the coupon is present
     */
    public boolean containsCoupon(Coupon coupon) {
        return couponList.contains(coupon);
    }


    /**
     * Returns the number of coupons.
     * *
     * 
     * @return the number of coupons
     */
    public int getSize() {
        return couponList.size();
    }


    /**
     * Determines whether the coupon list is empty.
     *
     * @return true if the list is empty
     */
    public boolean isEmpty() {
        return couponList.isEmpty();
    }


    // ----------------------------------------------------------
    /**
     * prints the coupon list
     */
    public void printList() {
        NumberFormat money = NumberFormat.getCurrencyInstance();
        System.out.println("Category: " + category + "\n");
        if (isEmpty()) {
            System.out.println("list is empty");
        }
        else {
            for (int i = 0; i < getSize(); i++) {
                System.out.println("Coupon " + (i + 1) + "Discount: " + money
                    .format(couponList.get(i).getDiscount() + "\n"));
            }
        }
    }
}