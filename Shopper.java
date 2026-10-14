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
            System.out.println("0. Quit");
            System.out.print("Choose an option: ");

            try {
                choice = Integer.parseInt(scanner.nextLine().trim());

                /*
                 * Create a shopping list.
                 */
                if (choice == 1) {
                    System.out.print("Shopping list category: ");

                    String category = scanner.nextLine().trim();

                    if (category.isEmpty()) {
                        System.out.println("Category cannot be empty.");
                    }
                    else {
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
                }

                /*
                 * Create a coupon list.
                 */
                else if (choice == 2) {
                    System.out.print("Coupon list category: ");

                    String category = scanner.nextLine().trim();

                    if (category.isEmpty()) {
                        System.out.println("Category cannot be empty.");
                    }
                    else {
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
                        System.out.print("Item name: ");
                        String name = scanner.nextLine().trim();

                        if (name.isEmpty()) {
                            System.out.println("Item name cannot be empty.");
                        }
                        else {
                            System.out.print("Item price: ");

                            double price = Double.parseDouble(scanner.nextLine()
                                .trim());

                            if (price < 0) {
                                System.out.println("Price cannot be negative.");
                            }
                            else {
                                ShoppingItem item = new ShoppingItem(name,
                                    price, selectedList.getCategory());

                                selectedList.addItem(item);

                                System.out.println("Item added.");
                            }
                        }
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
                        System.out.print("Discount percentage: ");

                        double discount = Double.parseDouble(scanner.nextLine()
                            .trim());

                        if (discount < 0 || discount > 100) {
                            System.out.println("Discount must be between "
                                + "0 and 100.");
                        }
                        else {
                            Coupon coupon = new Coupon(selectedList
                                .getCategory(), discount);

                            selectedList.addCoupon(coupon);

                            System.out.println("Coupon added.");
                        }
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
                        System.out.print("Item name: ");
                        String name = scanner.nextLine().trim();

                        if (name.isEmpty()) {
                            System.out.println("Item name cannot be empty.");
                        }
                        else {
                            System.out.print("Item price: ");

                            double price = Double.parseDouble(scanner.nextLine()
                                .trim());

                            if (price < 0) {
                                System.out.println("Price cannot be negative.");
                            }
                            else {
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
                        System.out.print("Discount percentage: ");

                        double discount = Double.parseDouble(scanner.nextLine()
                            .trim());

                        if (discount < 0 || discount > 100) {
                            System.out.println("Discount must be between "
                                + "0 and 100.");
                        }
                        else {
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
                 * Quit the program.
                 */
                else if (choice == 0) {
                    System.out.println("Goodbye!");
                }

                /*
                 * Handle a menu number outside the valid range.
                 */
                else {
                    System.out.println("Please enter a number from 0 to 9.");
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
