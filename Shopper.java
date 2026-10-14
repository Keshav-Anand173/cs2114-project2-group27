import java.util.Scanner;

/**
 * Console application for managing multiple shopping lists
 * and coupon lists.
 */
public class Shopper {
    private Scanner scanner;
    private LinkedChain<ShoppingList> shoppingLists;
    private LinkedChain<CouponList> couponLists;

    /**
     * Creates a Shopper with no shopping or coupon lists.
     */
    public Shopper() {
        scanner = new Scanner(System.in);
        shoppingLists = new LinkedChain<ShoppingList>();
        couponLists = new LinkedChain<CouponList>();
    }


    /**
     * Runs the console application.
     */
    public void run() {
        int choice = -1;

        while (choice != 0) {
            System.out.println();
            System.out.println("Shopper Menu");
            System.out.println("1. Create shopping list");
            System.out.println("2. Create coupon list");
            System.out.println("3. Add shopping item");
            System.out.println("4. Add coupon");
            System.out.println("5. Buy shopping item");
            System.out.println("6. Use coupon");
            System.out.println("7. Remove shopping item");
            System.out.println("8. Remove coupon");
            System.out.println("9. Display all lists");
            System.out.println("10. Search shopping item");
            System.out.println("11. Search coupon");
            System.out.println("12. Best coupon for a category");
            System.out.println("0. Quit");
            System.out.print("Choose an option: ");

            try {
                choice = Integer.parseInt(scanner.nextLine().trim());

                /*
                 * Create a shopping list.
                 */
                if (choice == 1) {
                    String category = readText("Shopping list category: ",
                        "Category cannot be empty.");

                    boolean duplicate = false;

                    for (int i = 0; i < shoppingLists.size(); i++) {

                        ShoppingList currentList = shoppingLists.get(i);

                        if (currentList.getCategory().equalsIgnoreCase(
                            category)) {

                            duplicate = true;
                        }
                    }

                    if (duplicate) {
                        System.out.println("A shopping list with that "
                            + "category already exists.");
                    }
                    else {
                        ShoppingList newList = new ShoppingList(category);

                        shoppingLists.add(newList);

                        System.out.println("Shopping list created.");
                    }
                }

                /*
                 * Create a coupon list.
                 */
                else if (choice == 2) {
                    String category = readText("Coupon list category: ",
                        "Category cannot be empty.");

                    boolean duplicate = false;

                    for (int i = 0; i < couponLists.size(); i++) {

                        CouponList currentList = couponLists.get(i);

                        if (currentList.getCategory().equalsIgnoreCase(
                            category)) {

                            duplicate = true;
                        }
                    }

                    if (duplicate) {
                        System.out.println("A coupon list with that "
                            + "category already exists.");
                    }
                    else {
                        CouponList newList = new CouponList(category);

                        couponLists.add(newList);

                        System.out.println("Coupon list created.");
                    }
                }

                /*
                 * Add a shopping item.
                 */
                else if (choice == 3) {
                    System.out.print("Item category: ");
                    String category = scanner.nextLine().trim();

                    ShoppingList selectedList = null;

                    for (int i = 0; i < shoppingLists.size(); i++) {

                        ShoppingList currentList = shoppingLists.get(i);

                        if (currentList.getCategory().equalsIgnoreCase(
                            category)) {

                            selectedList = currentList;
                        }
                    }

                    if (selectedList == null) {
                        System.out.println("No shopping list exists for "
                            + "that category.");
                    }
                    else {
                        String name = readText("Item name: ",
                            "Item name cannot be empty.");
                        double price = readPrice();

                        ShoppingItem item = new ShoppingItem(name,
                            price, selectedList.getCategory());

                        selectedList.addItem(item);

                        System.out.println("Item added.");
                    }
                }

                /*
                 * Add a coupon.
                 */
                else if (choice == 4) {
                    System.out.print("Coupon category: ");
                    String category = scanner.nextLine().trim();

                    CouponList selectedList = null;

                    for (int i = 0; i < couponLists.size(); i++) {

                        CouponList currentList = couponLists.get(i);

                        if (currentList.getCategory().equalsIgnoreCase(
                            category)) {

                            selectedList = currentList;
                        }
                    }

                    if (selectedList == null) {
                        System.out.println("No coupon list exists for "
                            + "that category.");
                    }
                    else {
                        double discount = readDiscount();

                        Coupon coupon = new Coupon(selectedList
                            .getCategory(), discount);

                        selectedList.addCoupon(coupon);

                        System.out.println("Coupon added.");
                    }
                }

                /*
                 * Buy or remove a shopping item.
                 */
                else if (choice == 5 || choice == 7) {
                    System.out.print("Item category: ");
                    String category = scanner.nextLine().trim();

                    ShoppingList selectedList = null;

                    for (int i = 0; i < shoppingLists.size(); i++) {

                        ShoppingList currentList = shoppingLists.get(i);

                        if (currentList.getCategory().equalsIgnoreCase(
                            category)) {

                            selectedList = currentList;
                        }
                    }

                    if (selectedList == null) {
                        System.out.println("No shopping list exists for "
                            + "that category.");
                    }
                    else {
                        String name = readText("Item name: ",
                            "Item name cannot be empty.");
                        double price = readPrice();

                        ShoppingItem item = new ShoppingItem(name,
                            price, selectedList.getCategory());

                        boolean removed = selectedList.removeItem(item);

                        if (!removed) {
                            System.out.println("Item not found.");
                        }
                        else if (choice == 5) {
                            System.out.println("Item purchased.");
                        }
                        else {
                            System.out.println("Item removed.");
                        }
                    }
                }

                /*
                 * Use or remove a coupon.
                 */
                else if (choice == 6 || choice == 8) {
                    System.out.print("Coupon category: ");
                    String category = scanner.nextLine().trim();

                    CouponList selectedList = null;

                    for (int i = 0; i < couponLists.size(); i++) {

                        CouponList currentList = couponLists.get(i);

                        if (currentList.getCategory().equalsIgnoreCase(
                            category)) {

                            selectedList = currentList;
                        }
                    }

                    if (selectedList == null) {
                        System.out.println("No coupon list exists for "
                            + "that category.");
                    }
                    else {
                        double discount = readDiscount();

                        Coupon coupon = new Coupon(selectedList
                            .getCategory(), discount);

                        boolean removed = selectedList.removeCoupon(coupon);

                        if (!removed) {
                            System.out.println("Coupon not found.");
                        }
                        else if (choice == 6) {
                            System.out.println("Coupon used.");
                        }
                        else {
                            System.out.println("Coupon removed.");
                        }
                    }
                }

                /*
                 * Display every list.
                 */
                else if (choice == 9) {
                    System.out.println();
                    System.out.println("Shopping Lists");

                    if (shoppingLists.isEmpty()) {
                        System.out.println("No shopping lists have "
                            + "been created.");
                    }
                    else {
                        for (int i = 0; i < shoppingLists.size(); i++) {

                            ShoppingList currentList = shoppingLists.get(i);

                            System.out.println();
                            System.out.println(currentList.getCategory());

                            currentList.printList();
                        }
                    }

                    System.out.println();
                    System.out.println("Coupon Lists");

                    if (couponLists.isEmpty()) {
                        System.out.println("No coupon lists have "
                            + "been created.");
                    }
                    else {
                        for (int i = 0; i < couponLists.size(); i++) {

                            CouponList currentList = couponLists.get(i);

                            System.out.println();
                            System.out.println(currentList.getCategory());

                            currentList.printList();
                        }
                    }
                }

                /*
                 * Search for a shopping item.
                 */
                else if (choice == 10) {
                    System.out.print("Item category: ");
                    String category = scanner.nextLine().trim();

                    ShoppingList selectedList = null;

                    for (int i = 0; i < shoppingLists.size(); i++) {

                        ShoppingList currentList = shoppingLists.get(i);

                        if (currentList.getCategory().equalsIgnoreCase(
                            category)) {

                            selectedList = currentList;
                        }
                    }

                    if (selectedList == null) {
                        System.out.println("No shopping list exists for "
                            + "that category.");
                    }
                    else {
                        String name = readText("Item name: ",
                            "Item name cannot be empty.");
                        double price = readPrice();

                        ShoppingItem item = new ShoppingItem(name,
                            price, selectedList.getCategory());

                        if (selectedList.containsItem(item)) {
                            System.out.println("Item found.");
                        }
                        else {
                            System.out.println("Item not found.");
                        }
                    }
                }

                /*
                 * Search for a coupon.
                 */
                else if (choice == 11) {
                    System.out.print("Coupon category: ");
                    String category = scanner.nextLine().trim();

                    CouponList selectedList = null;

                    for (int i = 0; i < couponLists.size(); i++) {

                        CouponList currentList = couponLists.get(i);

                        if (currentList.getCategory().equalsIgnoreCase(
                            category)) {

                            selectedList = currentList;
                        }
                    }

                    if (selectedList == null) {
                        System.out.println("No coupon list exists for "
                            + "that category.");
                    }
                    else {
                        double discount = readDiscount();

                        Coupon coupon = new Coupon(selectedList
                            .getCategory(), discount);

                        if (selectedList.containsCoupon(coupon)) {
                            System.out.println("Coupon found.");
                        }
                        else {
                            System.out.println("Coupon not found.");
                        }
                    }
                }

                /*
                 * Recommend the best coupon for a category.
                 */
                else if (choice == 12) {
                    System.out.print("Coupon category: ");
                    String category = scanner.nextLine().trim();

                    CouponList selectedList = null;

                    for (int i = 0; i < couponLists.size(); i++) {

                        CouponList currentList = couponLists.get(i);

                        if (currentList.getCategory().equalsIgnoreCase(
                            category)) {

                            selectedList = currentList;
                        }
                    }

                    if (selectedList == null) {
                        System.out.println("No coupon list exists for "
                            + "that category.");
                    }
                    else {
                        Coupon best = selectedList.getBestCoupon();

                        if (best == null) {
                            System.out.println("No coupons in that list.");
                        }
                        else {
                            System.out.println("Best coupon: "
                                + best.getDiscount() + "% off");
                        }
                    }
                }

                /*
                 * Quit the program.
                 */
                else if (choice == 0) {
                    System.out.println("Goodbye!");
                }

                /*
                 * Handle a menu number outside the valid range.
                 */
                else {
                    System.out.println("Please enter a number from 0 to 12.");
                }
            }
            catch (NumberFormatException exception) {
                System.out.println(
                    "Invalid input. Please enter a valid number.");
            }
            catch (IllegalArgumentException exception) {
                System.out.println("Invalid input: " + exception.getMessage());
            }
        }

        scanner.close();
    }


    /**
     * Asks until the user types something
     *
     * @param prompt what to ask
     * @param error what to say if it is empty
     * @return what the user typed
     */
    private String readText(String prompt, String error) {
        System.out.print(prompt);
        String text = scanner.nextLine().trim();
        while (text.isEmpty()) {
            System.out.println(error);
            System.out.print(prompt);
            text = scanner.nextLine().trim();
        }
        return text;
    }


    /**
     * Asks until the user types a number
     *
     * @param prompt what to ask
     * @return the number
     */
    private double readNumber(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                return Double.parseDouble(scanner.nextLine().trim());
            }
            catch (NumberFormatException exception) {
                System.out.println(
                    "Invalid input. Please enter a valid number.");
            }
        }
    }


    /**
     * Asks until the user types a price that is not negative
     *
     * @return the price
     */
    private double readPrice() {
        double price = readNumber("Item price: ");
        while (price < 0) {
            System.out.println("Price cannot be negative.");
            price = readNumber("Item price: ");
        }
        return price;
    }


    /**
     * Asks until the user types a discount from 0 to 100
     *
     * @return the discount
     */
    private double readDiscount() {
        double discount = readNumber("Discount percentage: ");
        while (discount < 0 || discount > 100) {
            System.out.println("Discount must be between 0 and 100.");
            discount = readNumber("Discount percentage: ");
        }
        return discount;
    }


    /**
     * Starts the console application.
     *
     * @param args
     *            unused command-line arguments
     */
    public static void main(String[] args) {
        Shopper shopper = new Shopper();
        shopper.run();
    }
}
