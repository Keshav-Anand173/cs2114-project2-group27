import java.io.ByteArrayInputStream;
import java.io.InputStream;

/**
 * Tests Shopper by feeding it typed input
 *
 * @author Arjun Ramaswamy (arjun79)
 * @version 2026.10.14
 */
public class ShopperTest extends student.TestCase {

    // makes Grocery lists
    private static final String LISTS = "1\nGrocery\n2\nGrocery\n";

    // adds milk and a coupon
    private static final String MILK_AND_COUPON =
        LISTS + "3\nGrocery\nMilk\n3.49\n4\nGrocery\n10\n";


    /**
     * Runs the app with typed input
     *
     * @param input what the user types
     * @return everything printed
     */
    private String runWith(String input) {
        InputStream original = System.in;
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        try {
            new Shopper().run();
        }
        finally {
            System.setIn(original);
        }
        return systemOut().getHistory();
    }


    /**
     * Counts how often text shows up
     *
     * @param output the text to search
     * @param target what to count
     * @return the count
     */
    private int count(String output, String target) {
        return output.split(java.util.regex.Pattern.quote(target), -1).length
            - 1;
    }


    /** Tests quitting right away */
    public void testConstructor() {
        String output = runWith("0\n");
        assertTrue(output.contains("Shopper Menu"));
        assertTrue(output.contains("Goodbye!"));
    }


    /** Tests a new shopper has no lists */
    public void testConstructorEmpty() {
        String output = runWith("9\n0\n");
        assertTrue(output.contains("No shopping lists have been created."));
        assertTrue(output.contains("No coupon lists have been created."));
    }


    /** Tests creating a shopping list */
    public void testCreateShoppingList() {
        String output = runWith("1\nGrocery\n0\n");
        assertTrue(output.contains("Shopping list created."));
    }


    /** Tests bad shopping list names */
    public void testCreateShoppingListBad() {
        String output = runWith("1\n\n \nGrocery\n1\ngrocery\n0\n");
        assertTrue(output.contains("Category cannot be empty."));
        assertEquals(4, count(output, "Shopping list category: "));
        assertTrue(output.contains(
            "A shopping list with that category already exists."));
        assertEquals(1, count(output, "Shopping list created."));
    }


    /** Tests creating a coupon list */
    public void testCreateCouponList() {
        String output = runWith("2\nGrocery\n0\n");
        assertTrue(output.contains("Coupon list created."));
    }


    /** Tests bad coupon list names */
    public void testCreateCouponListBad() {
        String output = runWith("2\n  \nGrocery\n2\nGROCERY\n0\n");
        assertTrue(output.contains("Category cannot be empty."));
        assertEquals(3, count(output, "Coupon list category: "));
        assertTrue(output.contains(
            "A coupon list with that category already exists."));
        assertEquals(1, count(output, "Coupon list created."));
    }


    /** Tests adding an item */
    public void testAddItem() {
        String output = runWith(LISTS + "3\ngrocery\nMilk\n3.49\n9\n0\n");
        assertTrue(output.contains("Item added."));
        assertTrue(output.contains("Name: Milk"));
        assertTrue(output.contains("3.49"));
    }


    /** Tests bad item input asks again */
    public void testAddItemBad() {
        String output = runWith(LISTS
            + "3\nDairy\n"
            + "3\nGrocery\n\n\nMilk\nabc\n-5\n-1\n3.49\n"
            + "9\n0\n");
        assertTrue(output.contains(
            "No shopping list exists for that category."));
        assertTrue(output.contains("Item name cannot be empty."));
        assertTrue(output.contains(
            "Invalid input. Please enter a valid number."));
        assertTrue(output.contains("Price cannot be negative."));
        assertEquals(3, count(output, "Item name: "));
        assertEquals(4, count(output, "Item price: "));
        assertEquals(1, count(output, "Item added."));
        assertTrue(output.contains("Name: Milk"));
    }


    /** Tests adding a coupon */
    public void testAddCoupon() {
        String output = runWith(LISTS + "4\nGrocery\n10\n9\n0\n");
        assertTrue(output.contains("Coupon added."));
        assertTrue(output.contains("Coupon 1 Discount: 10.0%"));
    }


    /** Tests bad coupon input asks again */
    public void testAddCouponBad() {
        String output = runWith(LISTS
            + "4\nDairy\n"
            + "4\nGrocery\n150\n-1\nten\n10\n"
            + "0\n");
        assertTrue(output.contains(
            "No coupon list exists for that category."));
        assertEquals(2, count(output, "Discount must be between 0 and 100."));
        assertTrue(output.contains(
            "Invalid input. Please enter a valid number."));
        assertEquals(4, count(output, "Discount percentage: "));
        assertEquals(1, count(output, "Coupon added."));
    }


    /** Tests buying an item */
    public void testBuyItem() {
        String output = runWith(MILK_AND_COUPON
            + "5\nGrocery\nMilk\n3.49\n9\n0\n");
        assertTrue(output.contains("Item purchased."));
        assertTrue(output.contains("list is empty"));
    }


    /** Tests buying a missing item */
    public void testBuyItemMissing() {
        String output = runWith(MILK_AND_COUPON
            + "5\nGrocery\nEggs\n2\n5\nDairy\n0\n");
        assertTrue(output.contains("Item not found."));
        assertTrue(output.contains(
            "No shopping list exists for that category."));
        assertFalse(output.contains("Item purchased."));
    }


    /** Tests using a coupon */
    public void testUseCoupon() {
        String output = runWith(MILK_AND_COUPON + "6\nGrocery\n10\n0\n");
        assertTrue(output.contains("Coupon used."));
    }


    /** Tests using a missing coupon */
    public void testUseCouponMissing() {
        String output = runWith(MILK_AND_COUPON
            + "6\nGrocery\n50\n6\nGrocery\n200\n10\n0\n");
        assertTrue(output.contains("Coupon not found."));
        assertTrue(output.contains("Discount must be between 0 and 100."));
        assertEquals(1, count(output, "Coupon used."));
    }


    /** Tests removing an item */
    public void testRemoveItem() {
        String output = runWith(MILK_AND_COUPON
            + "7\nGrocery\nMilk\n3.49\n0\n");
        assertTrue(output.contains("Item removed."));
    }


    /** Tests removing a missing item */
    public void testRemoveItemMissing() {
        String output = runWith(LISTS
            + "7\nGrocery\nMilk\n3.49\n"
            + "7\nGrocery\n\nMilk\n-1\nfree\n3.49\n0\n");
        assertEquals(2, count(output, "Item not found."));
        assertTrue(output.contains("Item name cannot be empty."));
        assertTrue(output.contains("Price cannot be negative."));
        assertTrue(output.contains(
            "Invalid input. Please enter a valid number."));
        assertFalse(output.contains("Item removed."));
    }


    /** Tests removing a coupon */
    public void testRemoveCoupon() {
        String output = runWith(MILK_AND_COUPON + "8\nGrocery\n10\n9\n0\n");
        assertTrue(output.contains("Coupon removed."));
        assertFalse(output.contains("Coupon 1 Discount"));
    }


    /** Tests removing a missing coupon */
    public void testRemoveCouponMissing() {
        String output = runWith(LISTS + "8\nGrocery\n10\n8\nDairy\n0\n");
        assertTrue(output.contains("Coupon not found."));
        assertTrue(output.contains("No coupon list exists for that category."));
    }


    /** Tests display */
    public void testDisplay() {
        String output = runWith(MILK_AND_COUPON + "9\n0\n");
        assertTrue(output.contains("Shopping Lists"));
        assertTrue(output.contains("Coupon Lists"));
        assertTrue(output.contains("Name: Milk"));
        assertTrue(output.contains("Coupon 1 Discount: 10.0%"));
    }


    /** Tests display with empty lists */
    public void testDisplayEmpty() {
        String output = runWith(LISTS + "9\n0\n");
        assertEquals(2, count(output, "list is empty"));
    }


    /** Tests searching for an item */
    public void testSearchItem() {
        String output = runWith(MILK_AND_COUPON
            + "10\nGrocery\nMilk\n3.49\n0\n");
        assertTrue(output.contains("Item found."));
    }


    /** Tests searching for a missing item */
    public void testSearchItemMissing() {
        String output = runWith(MILK_AND_COUPON
            + "10\nGrocery\nEggs\n2\n"
            + "10\nDairy\n"
            + "10\nGrocery\n\nMilk\n-2\nx\n3.49\n0\n");
        assertTrue(output.contains("Item not found."));
        assertTrue(output.contains(
            "No shopping list exists for that category."));
        assertTrue(output.contains("Item name cannot be empty."));
        assertTrue(output.contains("Price cannot be negative."));
        assertTrue(output.contains(
            "Invalid input. Please enter a valid number."));
        assertEquals(1, count(output, "Item found."));
    }


    /** Tests searching for a coupon */
    public void testSearchCoupon() {
        String output = runWith(MILK_AND_COUPON + "11\nGrocery\n10\n0\n");
        assertTrue(output.contains("Coupon found."));
    }


    /** Tests searching for a missing coupon */
    public void testSearchCouponMissing() {
        String output = runWith(MILK_AND_COUPON
            + "11\nGrocery\n30\n"
            + "11\nDairy\n"
            + "11\nGrocery\n-3\n10\n0\n");
        assertTrue(output.contains("Coupon not found."));
        assertTrue(output.contains(
            "No coupon list exists for that category."));
        assertTrue(output.contains("Discount must be between 0 and 100."));
        assertEquals(1, count(output, "Coupon found."));
    }


    /** Tests best coupon */
    public void testBestCoupon() {
        String output = runWith(MILK_AND_COUPON
            + "4\nGrocery\n25\n4\nGrocery\n5\n12\nGrocery\n0\n");
        assertTrue(output.contains("Best coupon: 25.0% off"));
    }


    /** Tests best coupon with no coupons */
    public void testBestCouponNone() {
        String output = runWith(LISTS + "12\nGrocery\n12\nDairy\n0\n");
        assertTrue(output.contains("No coupons in that list."));
        assertTrue(output.contains("No coupon list exists for that category."));
    }


    /** Tests bad menu choices */
    public void testInvalidChoice() {
        String output = runWith("42\n-1\nabc\n\n0\n");
        assertEquals(2, count(output, "Please enter a number from 0 to 12."));
        assertEquals(2, count(output,
            "Invalid input. Please enter a valid number."));
        assertTrue(output.contains("Goodbye!"));
    }


    /** Tests main */
    public void testMain() {
        InputStream original = System.in;
        System.setIn(new ByteArrayInputStream("0\n".getBytes()));
        try {
            Shopper.main(new String[0]);
        }
        finally {
            System.setIn(original);
        }
        assertTrue(systemOut().getHistory().contains("Goodbye!"));
    }


    /** Tests main with bad input */
    public void testMainBadInput() {
        InputStream original = System.in;
        System.setIn(new ByteArrayInputStream("hello\n99\n0\n".getBytes()));
        try {
            Shopper.main(new String[0]);
        }
        finally {
            System.setIn(original);
        }
        String output = systemOut().getHistory();
        assertTrue(output.contains(
            "Invalid input. Please enter a valid number."));
        assertTrue(output.contains("Please enter a number from 0 to 12."));
        assertTrue(output.contains("Goodbye!"));
    }
}
