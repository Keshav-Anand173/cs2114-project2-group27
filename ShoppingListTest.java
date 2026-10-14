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
 * Tests ShoppingList
 *
 * @author Arjun Ramaswamy (arjun79)
 * @version 2026.10.14
 */
public class ShoppingListTest extends student.TestCase {

    private ShoppingList list;
    private ShoppingItem milk;
    private ShoppingItem bread;


    /** Sets up a list and two items */
    public void setUp() {
        list = new ShoppingList("Grocery");
        milk = new ShoppingItem("Milk", 3.49, "Grocery");
        bread = new ShoppingItem("Bread", 2.50, "Grocery");
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
            new ShoppingList(null);
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
        list.addItem(milk);
        list.removeItem(milk);
        list.removeItem(bread);
        assertEquals("Grocery", list.getCategory());
    }


    /** Tests getList */
    public void testGetList() {
        list.addItem(milk);
        assertEquals(1, list.getList().size());
        assertTrue(list.getList().contains(milk));
    }


    /** Tests getList on an empty list */
    public void testGetListEmpty() {
        assertNotNull(list.getList());
        assertTrue(list.getList().isEmpty());
    }


    /** Tests addItem */
    public void testAddItem() {
        list.addItem(milk);
        list.addItem(milk);
        assertEquals(2, list.getSize());
        assertTrue(list.containsItem(milk));
    }


    /** Tests addItem with null */
    public void testAddItemNull() {
        Exception thrown = null;
        try {
            list.addItem(null);
        }
        catch (Exception exception) {
            thrown = exception;
        }
        assertTrue(thrown instanceof IllegalArgumentException);
        assertEquals(0, list.getSize());
    }


    /** Tests removeItem */
    public void testRemoveItem() {
        list.addItem(milk);
        list.addItem(bread);
        assertTrue(list.removeItem(milk));
        assertEquals(1, list.getSize());
        assertFalse(list.containsItem(milk));
    }


    /** Tests removeItem with a missing item */
    public void testRemoveItemMissing() {
        assertFalse(list.removeItem(milk));
        list.addItem(bread);
        assertFalse(list.removeItem(milk));
        assertFalse(list.removeItem(null));
        assertEquals(1, list.getSize());
    }


    /** Tests containsItem */
    public void testContainsItem() {
        list.addItem(milk);
        assertTrue(list.containsItem(new ShoppingItem("Milk", 3.49,
            "Grocery")));
    }


    /** Tests containsItem with a missing item */
    public void testContainsItemMissing() {
        assertFalse(list.containsItem(milk));
        list.addItem(bread);
        assertFalse(list.containsItem(milk));
        assertFalse(list.containsItem(null));
    }


    /** Tests getSize */
    public void testGetSize() {
        list.addItem(milk);
        list.addItem(bread);
        assertEquals(2, list.getSize());
    }


    /** Tests getSize on an empty list */
    public void testGetSizeEmpty() {
        assertEquals(0, list.getSize());
    }


    /** Tests isEmpty */
    public void testIsEmpty() {
        list.addItem(milk);
        assertFalse(list.isEmpty());
    }


    /** Tests isEmpty after removing */
    public void testIsEmptyAfterRemove() {
        assertTrue(list.isEmpty());
        list.addItem(milk);
        list.removeItem(milk);
        assertTrue(list.isEmpty());
    }


    /** Tests printList */
    public void testPrintList() {
        list.addItem(milk);
        list.addItem(bread);
        list.printList();
        String output = systemOut().getHistory();
        assertTrue(output.contains("Category: Grocery"));
        assertTrue(output.contains("Name: Milk"));
        assertTrue(output.contains("Name: Bread"));
        assertTrue(output.contains("3.49"));
        assertTrue(output.contains("2.50"));
        assertTrue(output.indexOf("Bread") < output.indexOf("Milk"));
    }


    /** Tests printList on an empty list */
    public void testPrintListEmpty() {
        list.printList();
        String output = systemOut().getHistory();
        assertTrue(output.contains("list is empty"));
        assertFalse(output.contains("Name:"));
    }
}
