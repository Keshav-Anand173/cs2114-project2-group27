/**
 * Tests ShoppingItem
 *
 * @author Arjun Ramaswamy (arjun79)
 * @version 2026.10.14
 */
public class ShoppingItemTest extends student.TestCase {

    private ShoppingItem item;


    /** Sets up a milk item */
    public void setUp() {
        item = new ShoppingItem("Milk", 3.49, "Grocery");
    }


    /** Tests the constructor */
    public void testConstructor() {
        assertEquals("Milk", item.getName());
        assertEquals(3.49, item.getPrice(), 0.001);
        assertEquals("Grocery", item.getCategory());
    }


    /** Tests the constructor with bad input */
    public void testConstructorBad() {
        Exception thrown = null;
        try {
            new ShoppingItem("", 3.49, "Grocery");
        }
        catch (Exception exception) {
            thrown = exception;
        }
        assertTrue(thrown instanceof IllegalArgumentException);

        thrown = null;
        try {
            new ShoppingItem("Milk", -1, "Grocery");
        }
        catch (Exception exception) {
            thrown = exception;
        }
        assertTrue(thrown instanceof IllegalArgumentException);

        thrown = null;
        try {
            new ShoppingItem("Milk", 3.49, null);
        }
        catch (Exception exception) {
            thrown = exception;
        }
        assertTrue(thrown instanceof IllegalArgumentException);
    }


    /** Tests getName */
    public void testGetName() {
        assertEquals("Milk", item.getName());
    }


    /** Tests getName after a bad setName */
    public void testGetNameAfterBadSet() {
        try {
            item.setName("  ");
        }
        catch (IllegalArgumentException exception) {
            // expected
        }
        assertEquals("Milk", item.getName());
    }


    /** Tests getPrice */
    public void testGetPrice() {
        assertEquals(3.49, item.getPrice(), 0.001);
    }


    /** Tests getPrice after a bad setPrice */
    public void testGetPriceAfterBadSet() {
        try {
            item.setPrice(-5);
        }
        catch (IllegalArgumentException exception) {
            // expected
        }
        assertEquals(3.49, item.getPrice(), 0.001);
    }


    /** Tests getCategory */
    public void testGetCategory() {
        assertEquals("Grocery", item.getCategory());
    }


    /** Tests getCategory after a bad setCategory */
    public void testGetCategoryAfterBadSet() {
        try {
            item.setCategory("");
        }
        catch (IllegalArgumentException exception) {
            // expected
        }
        assertEquals("Grocery", item.getCategory());
    }


    /** Tests setName */
    public void testSetName() {
        item.setName("Bread");
        assertEquals("Bread", item.getName());
    }


    /** Tests setName with empty and null */
    public void testSetNameBad() {
        Exception thrown = null;
        try {
            item.setName("");
        }
        catch (Exception exception) {
            thrown = exception;
        }
        assertTrue(thrown instanceof IllegalArgumentException);

        thrown = null;
        try {
            item.setName(null);
        }
        catch (Exception exception) {
            thrown = exception;
        }
        assertTrue(thrown instanceof IllegalArgumentException);
    }


    /** Tests setPrice */
    public void testSetPrice() {
        item.setPrice(2.99);
        assertEquals(2.99, item.getPrice(), 0.001);
        item.setPrice(0);
        assertEquals(0, item.getPrice(), 0.001);
    }


    /** Tests setPrice with a negative */
    public void testSetPriceBad() {
        Exception thrown = null;
        try {
            item.setPrice(-0.01);
        }
        catch (Exception exception) {
            thrown = exception;
        }
        assertTrue(thrown instanceof IllegalArgumentException);
    }


    /** Tests setCategory */
    public void testSetCategory() {
        item.setCategory("Dairy");
        assertEquals("Dairy", item.getCategory());
    }


    /** Tests setCategory with empty and null */
    public void testSetCategoryBad() {
        Exception thrown = null;
        try {
            item.setCategory(" ");
        }
        catch (Exception exception) {
            thrown = exception;
        }
        assertTrue(thrown instanceof IllegalArgumentException);

        thrown = null;
        try {
            item.setCategory(null);
        }
        catch (Exception exception) {
            thrown = exception;
        }
        assertTrue(thrown instanceof IllegalArgumentException);
    }


    /** Tests equals */
    public void testEquals() {
        assertTrue(item.equals(item));
        assertTrue(item.equals(new ShoppingItem("Milk", 3.49, "Grocery")));
    }


    /** Tests equals with no match */
    public void testEqualsBad() {
        assertFalse(item.equals(null));
        assertFalse(item.equals("Milk"));
        assertFalse(item.equals(new ShoppingItem("Eggs", 3.49, "Grocery")));
        assertFalse(item.equals(new ShoppingItem("Milk", 4.00, "Grocery")));
        assertFalse(item.equals(new ShoppingItem("Milk", 3.49, "Dairy")));
    }
}
