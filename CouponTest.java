// Project 2 Deliverable 3 fall 2026
// Virginia Tech Honor Code Pledge:
//
// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal, nor will I accept the actions of those who
// do.
// -- Arjun Ramaswamy (arjun79)
//
// LLM Statement:
//
// During the preparation of this assignment, I, Arjun Ramaswamy, used Claude
// to write these test cases.
// After using this tool, I reviewed and edited the content as needed to ensure
// its accuracy and take full responsibility for the content in relation to
// grading. I understand that I am responsible for being able to complete this
// work without the use of assistance.

/**
 * Tests Coupon
 *
 * @author Arjun Ramaswamy (arjun79)
 * @version 2026.10.14
 */
public class CouponTest extends student.TestCase {

    private Coupon coupon;


    /** Sets up a grocery coupon */
    public void setUp() {
        coupon = new Coupon("Grocery", 10);
    }


    /** Tests the constructor */
    public void testConstructor() {
        assertEquals("Grocery", coupon.getCategory());
        assertEquals(10, coupon.getDiscount(), 0.001);
    }


    /** Tests the constructor with bad input */
    public void testConstructorBad() {
        Exception thrown = null;
        try {
            new Coupon("Grocery", 150);
        }
        catch (Exception exception) {
            thrown = exception;
        }
        assertTrue(thrown instanceof IllegalArgumentException);

        thrown = null;
        try {
            new Coupon("", 10);
        }
        catch (Exception exception) {
            thrown = exception;
        }
        assertTrue(thrown instanceof IllegalArgumentException);
    }


    /** Tests getCategory */
    public void testGetCategory() {
        assertEquals("Grocery", coupon.getCategory());
    }


    /** Tests getCategory after a bad setCategory */
    public void testGetCategoryAfterBadSet() {
        try {
            coupon.setCategory(null);
        }
        catch (IllegalArgumentException exception) {
            // expected
        }
        assertEquals("Grocery", coupon.getCategory());
    }


    /** Tests getDiscount */
    public void testGetDiscount() {
        assertEquals(10, coupon.getDiscount(), 0.001);
    }


    /** Tests getDiscount after a bad setDiscount */
    public void testGetDiscountAfterBadSet() {
        try {
            coupon.setDiscount(101);
        }
        catch (IllegalArgumentException exception) {
            // expected
        }
        assertEquals(10, coupon.getDiscount(), 0.001);
    }


    /** Tests setCategory */
    public void testSetCategory() {
        coupon.setCategory("Dairy");
        assertEquals("Dairy", coupon.getCategory());
    }


    /** Tests setCategory with empty and null */
    public void testSetCategoryBad() {
        Exception thrown = null;
        try {
            coupon.setCategory("  ");
        }
        catch (Exception exception) {
            thrown = exception;
        }
        assertTrue(thrown instanceof IllegalArgumentException);

        thrown = null;
        try {
            coupon.setCategory(null);
        }
        catch (Exception exception) {
            thrown = exception;
        }
        assertTrue(thrown instanceof IllegalArgumentException);
    }


    /** Tests setDiscount */
    public void testSetDiscount() {
        coupon.setDiscount(25);
        assertEquals(25, coupon.getDiscount(), 0.001);
        coupon.setDiscount(0);
        assertEquals(0, coupon.getDiscount(), 0.001);
        coupon.setDiscount(100);
        assertEquals(100, coupon.getDiscount(), 0.001);
    }


    /** Tests setDiscount below 0 and above 100 */
    public void testSetDiscountBad() {
        Exception thrown = null;
        try {
            coupon.setDiscount(-10);
        }
        catch (Exception exception) {
            thrown = exception;
        }
        assertTrue(thrown instanceof IllegalArgumentException);

        thrown = null;
        try {
            coupon.setDiscount(100.5);
        }
        catch (Exception exception) {
            thrown = exception;
        }
        assertTrue(thrown instanceof IllegalArgumentException);
    }


    /** Tests equals */
    public void testEquals() {
        assertTrue(coupon.equals(coupon));
        assertTrue(coupon.equals(new Coupon("Grocery", 10)));
    }


    /** Tests equals with no match */
    public void testEqualsBad() {
        assertFalse(coupon.equals(null));
        assertFalse(coupon.equals("Grocery"));
        assertFalse(coupon.equals(new Coupon("Dairy", 10)));
        assertFalse(coupon.equals(new Coupon("Grocery", 20)));
    }
}
