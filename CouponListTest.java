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
 * Tests CouponList
 *
 * @author Arjun Ramaswamy (arjun79)
 * @version 2026.10.14
 */
public class CouponListTest extends student.TestCase {

    private CouponList list;
    private Coupon tenOff;
    private Coupon twentyOff;


    /** Sets up a list and two coupons */
    public void setUp() {
        list = new CouponList("Grocery");
        tenOff = new Coupon("Grocery", 10);
        twentyOff = new Coupon("Grocery", 20);
    }


    /** Tests the constructor */
    public void testConstructor() {
        assertEquals("Grocery", list.getCategory());
        assertTrue(list.isEmpty());
    }


    /** Tests the constructor with null */
    public void testConstructorNull() {
        Exception thrown = null;
        try {
            new CouponList(null);
        }
        catch (Exception exception) {
            thrown = exception;
        }
        assertTrue(thrown instanceof IllegalArgumentException);
    }


    /** Tests getCategory */
    public void testGetCategory() {
        assertEquals("Grocery", list.getCategory());
    }


    /** Tests getCategory after changes */
    public void testGetCategoryAfterChanges() {
        list.addCoupon(tenOff);
        list.removeCoupon(tenOff);
        list.removeCoupon(twentyOff);
        assertEquals("Grocery", list.getCategory());
    }


    /** Tests addCoupon */
    public void testAddCoupon() {
        list.addCoupon(tenOff);
        list.addCoupon(tenOff);
        assertEquals(2, list.getSize());
        assertTrue(list.containsCoupon(tenOff));
    }


    /** Tests addCoupon with null */
    public void testAddCouponNull() {
        Exception thrown = null;
        try {
            list.addCoupon(null);
        }
        catch (Exception exception) {
            thrown = exception;
        }
        assertTrue(thrown instanceof IllegalArgumentException);
        assertEquals(0, list.getSize());
    }


    /** Tests removeCoupon */
    public void testRemoveCoupon() {
        list.addCoupon(tenOff);
        list.addCoupon(twentyOff);
        assertTrue(list.removeCoupon(tenOff));
        assertEquals(1, list.getSize());
        assertFalse(list.containsCoupon(tenOff));
    }


    /** Tests removeCoupon with a missing coupon */
    public void testRemoveCouponMissing() {
        assertFalse(list.removeCoupon(tenOff));
        list.addCoupon(twentyOff);
        assertFalse(list.removeCoupon(tenOff));
        assertFalse(list.removeCoupon(null));
        assertEquals(1, list.getSize());
    }


    /** Tests containsCoupon */
    public void testContainsCoupon() {
        list.addCoupon(tenOff);
        assertTrue(list.containsCoupon(new Coupon("Grocery", 10)));
    }


    /** Tests containsCoupon with a missing coupon */
    public void testContainsCouponMissing() {
        assertFalse(list.containsCoupon(tenOff));
        list.addCoupon(twentyOff);
        assertFalse(list.containsCoupon(tenOff));
        assertFalse(list.containsCoupon(null));
    }


    /** Tests getSize */
    public void testGetSize() {
        list.addCoupon(tenOff);
        list.addCoupon(twentyOff);
        assertEquals(2, list.getSize());
    }


    /** Tests getSize on an empty list */
    public void testGetSizeEmpty() {
        assertEquals(0, list.getSize());
    }


    /** Tests isEmpty */
    public void testIsEmpty() {
        list.addCoupon(tenOff);
        assertFalse(list.isEmpty());
    }


    /** Tests isEmpty after removing */
    public void testIsEmptyAfterRemove() {
        assertTrue(list.isEmpty());
        list.addCoupon(tenOff);
        list.removeCoupon(tenOff);
        assertTrue(list.isEmpty());
    }


    /** Tests getBestCoupon */
    public void testGetBestCoupon() {
        list.addCoupon(tenOff);
        list.addCoupon(twentyOff);
        list.addCoupon(new Coupon("Grocery", 15));
        assertEquals(twentyOff, list.getBestCoupon());
    }


    /** Tests getBestCoupon on an empty list */
    public void testGetBestCouponEmpty() {
        assertNull(list.getBestCoupon());
    }


    /** Tests printList */
    public void testPrintList() {
        list.addCoupon(tenOff);
        list.addCoupon(twentyOff);
        list.printList();
        String output = systemOut().getHistory();
        assertTrue(output.contains("Category: Grocery"));
        assertTrue(output.contains("Coupon 1 Discount: 20.0%"));
        assertTrue(output.contains("Coupon 2 Discount: 10.0%"));
    }


    /** Tests printList on an empty list */
    public void testPrintListEmpty() {
        list.printList();
        String output = systemOut().getHistory();
        assertTrue(output.contains("list is empty"));
        assertFalse(output.contains("Coupon 1"));
    }
}
