import java.text.NumberFormat;

// -------------------------------------------------------------------------
/**
 * Represents a list of items the user wants to buy
 * 
 * @author Keshav Anand
 * @version 10.14.2026
 */
public class ShoppingList {
    // ~ Fields ................................................................
    private String category;
    private LinkedChain<ShoppingItem> shoppingList;

    // ~ Constructors ..........................................................
    /**
     * Creates an empty shopping list.
     *
     * @param category
     *            the list's category
     */
    public ShoppingList(String category) {
        if (category == null) {
            throw new IllegalArgumentException("please enter a category");
        }

        this.category = category;
        shoppingList = new LinkedChain<ShoppingItem>();
    }


    // ~Public Methods ........................................................
    /**
     * Returns the list's category.
     *
     * @return the category
     */
    public String getCategory() {
        return category;
    }


    // ----------------------------------------------------------
    /**
     * returns the inner LinkedChain
     * 
     * @return the inner LinkedChain
     */
    public LinkedChain<ShoppingItem> getList() {
        return shoppingList;
    }


    /**
     * Adds an item to the shopping list.
     *
     * @param item
     *            the item to add
     */
    public void addItem(ShoppingItem item) {
        shoppingList.add(item);
    }


    /**
     * Removes an item from the shopping list.
     *
     * @param item
     *            the item to remove
     * @return returns true if the item was removed
     */
    public boolean removeItem(ShoppingItem item) {
        return shoppingList.remove(item);
    }


    /**
     * Determines whether the list contains an item.
     * 
     * @param item
     *            the item to find
     * @return true if the item is present
     */
    public boolean containsItem(ShoppingItem item) {
        return shoppingList.contains(item);
    }


    /**
     * Returns the number of items in the list.
     *
     * @return the number of items
     */
    public int getSize() {
        return shoppingList.size();
    }


    /**
     * Determines whether the list is empty.
     *
     * @return true if the list is empty
     */
    public boolean isEmpty() {
        return shoppingList.isEmpty();
    }


    // ----------------------------------------------------------
    /**
     * prints the shopping list
     */
    public void printList() {
        NumberFormat money = NumberFormat.getCurrencyInstance();
        System.out.println("Category: " + category + "\n");
        if (isEmpty()) {
            System.out.println("list is empty");
        }
        else {
            ShoppingItem[] items = shoppingList.toArray(
                new ShoppingItem[getSize()]);
            for (int i = 0; i < items.length; i++) {
                System.out.println("Name: " + items[i].getName());
                System.out.println("Price: " + money.format(items[i]
                    .getPrice()) + "\n");
            }
        }
    }
}
