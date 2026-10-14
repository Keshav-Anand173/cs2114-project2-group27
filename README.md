# Shopping List – Project 2 Deliverable 3 (Prototype + Tests)

CS 2114 Fall 2026 · Group 27 · Keshav Anand, Arjun Ramaswamy

A console app for shopping lists and coupon lists. The user creates lists by category, adds items and coupons, buys items, uses coupons, searches, and prints everything.

All storage goes through the provided generic `LinkedChain<T>`, the same class with no type-specific copies, holding **four** element types:

| Element type | Where |
|---|---|
| `ShoppingItem` | `LinkedChain<ShoppingItem>` inside `ShoppingList` |
| `Coupon` | `LinkedChain<Coupon>` inside `CouponList` |
| `ShoppingList` | `LinkedChain<ShoppingList>` inside `Shopper` |
| `CouponList` | `LinkedChain<CouponList>` inside `Shopper` |

## Files

| File | What it is |
|---|---|
| `LinkedChain.java` | Provided by CS 2114 staff. Used unchanged (only Eclipse auto-formatting). |
| `ShoppingItem.java` | One item: name, price, category |
| `Coupon.java` | One coupon: category, percent off |
| `ShoppingList.java` | Items for one category, stored in a `LinkedChain<ShoppingItem>` |
| `CouponList.java` | Coupons for one category, stored in a `LinkedChain<Coupon>`; can pick the best coupon |
| `Shopper.java` | Menu, input, and all the lists |
| `ShoppingItemTest.java`, `CouponTest.java`, `ShoppingListTest.java`, `CouponListTest.java`, `ShopperTest.java` | JUnit tests (extend `student.TestCase`) |

## How to compile and run

### Eclipse
1. Put every `.java` file in the `src` folder of a Java project (default package).
2. Add the **CS2-Support** library: right-click the project > Build Path > Add Libraries > CS2-Support. The tests use `student.TestCase`.
3. **Run the program:** right-click `Shopper.java` > Run As > Java Application, then type in the Console.
4. **Run the tests:** right-click the project > Run As > JUnit Test. All 93 tests pass.

### Command line
`student.jar` (CS2-Support) and JUnit 4 must be on the classpath. On Windows, use `;` instead of `:`.
```
javac -cp student.jar:junit-4.13.2.jar:hamcrest-core-1.3.jar *.java
java Shopper
java -cp .:student.jar:junit-4.13.2.jar:hamcrest-core-1.3.jar org.junit.runner.JUnitCore ShoppingItemTest CouponTest ShoppingListTest CouponListTest ShopperTest
```

### Menu
```
1. Create shopping list     7. Remove shopping item
2. Create coupon list       8. Remove coupon
3. Add shopping item        9. Display all lists
4. Add coupon              10. Search shopping item
5. Buy shopping item       11. Search coupon
6. Use coupon              12. Best coupon for a category
0. Quit
```
Make a list first (1 or 2). Items and coupons go into the list whose category you type, ignoring case.

## Bad input: what happens and where it is caught

| Bad input (from our scope) | Caught by | Response |
|---|---|---|
| Empty item name | `Shopper.readText` checks first; `ShoppingItem.setName` / constructor throw `IllegalArgumentException` as a backup | "Item name cannot be empty." and asks again |
| Empty list category | `Shopper.readText` | "Category cannot be empty." and asks again |
| Duplicate list category | `Shopper.run` | "A ... list with that category already exists." |
| Price or discount that is not a number | `Shopper.readNumber` catches `NumberFormatException` | "Invalid input. Please enter a valid number." and asks again |
| Negative price, or discount outside 0 to 100 | `Shopper.readPrice` / `readDiscount` check first; `ShoppingItem.setPrice` and `Coupon.setDiscount` throw as a backup | "Price cannot be negative." / "Discount must be between 0 and 100." and asks again |
| Removing, buying or using something not in the list | `LinkedChain.remove(T)` returns false, passed up by `removeItem`/`removeCoupon` | "Item not found." / "Coupon not found." |
| Searching for something not in the list | `LinkedChain.contains` returns false, passed up by `containsItem`/`containsCoupon` | "Item not found." / "Coupon not found." |
| A category with no list | `Shopper.run` | "No shopping list exists for that category." / "No coupon list exists for that category." |
| Printing an empty list | `printList` checks `isEmpty` | "list is empty" |
| Null entry | `LinkedChain.add` throws `IllegalArgumentException` | Never stored |
| Invalid menu choice | `Shopper.run` | "Please enter a number from 0 to 12." or "Invalid input. Please enter a valid number." |

None of these crash the program; `ShopperTest` feeds each one in and checks the response, including that the question is asked again.

## Big-O of app methods vs. the spec

n = entries in one list, L = number of lists.

| Method | Built | Spec said | Match? |
|---|---|---|---|
| `ShoppingItem` / `Coupon` constructors, getters, setters, `equals` | O(1) | O(1) | Yes |
| `addItem`, `addCoupon` | O(1), `LinkedChain.add` at the front | O(1) | Yes |
| `removeItem`, `removeCoupon` | O(n) | O(n) | Yes |
| `containsItem`, `containsCoupon` | O(n) | O(n) | Yes |
| `getSize`, `isEmpty`, `getCategory`, `getList` | O(1) | O(1) | Yes |
| `printList` (both) | O(n), one pass over `toArray` | O(n) | Yes |
| `CouponList.getBestCoupon` | O(n), one pass over `toArray` | not in spec | New |
| `Shopper` constructor, `main` | O(1) | O(1) | Yes |
| `Shopper.run`, per menu choice | O(L²) to find a list (loops with `LinkedChain.get(i)`, which is O(i)), plus the list method | O(1) plus the list method | **No.** Fix planned in Next Steps |

## Reflection

### What changed from our specification?
1. **Multiple lists by category.** The spec gave `Shopper` one `ShoppingList` and one `CouponList`. The build keeps a `LinkedChain<ShoppingList>` and a `LinkedChain<CouponList>`, so the user can make a list per category (Grocery, Dairy, ...). New items and coupons take their list's category, and duplicate categories are rejected. This idea came from our revised scope ("allow users to create multiple lists with different categories"). *Origin: group discussion; Keshav built it.*
2. **New menu actions.** "Buy shopping item" and "Use coupon" were not in the spec. They remove the entry and print "Item purchased." / "Coupon used." *Origin: Keshav, while building.*
3. **Number input.** The spec said Shopper would use `hasNextDouble()`. The build reads each line and parses it with `Integer.parseInt`/`Double.parseDouble`, catching `NumberFormatException`; Keshav built it this way. In the first build, a bad number, empty name or out-of-range value printed an error and went back to the menu. Our spec says Shopper re-prompts, so we added small private helpers to `Shopper` (`readText`, `readNumber`, `readPrice`, `readDiscount`) that keep asking until the input is valid. The messages differ a little from the spec's wording. *Origin: Keshav built the parsing; the re-prompt fix came from a GenAI (Claude) review against our spec.*
4. **`ShoppingItem.equals`** compares name, price **and** category; the spec said name and price. This works because each item takes its list's category. *Origin: Keshav, while building.*
5. **`ShoppingList.getList()`** was added. It returns the inner chain but `Shopper` does not use it. *Origin: Keshav.*
6. **Backup validation in `ShoppingItem` and `Coupon`.** Our spec said these classes throw `IllegalArgumentException` for an empty name or category, a negative price, or a discount outside 0 to 100. The first build had no checks, so we added them to the setters and made the constructors call the setters. *Origin: GenAI (Claude) review against our spec's "Where Validation Lives".*
7. **Coupon printing bug.** `CouponList.printList` passed a `String` to `NumberFormat.format`, which threw an exception, so coupons never printed ("Invalid input: Cannot format given Object as a Number"). It now prints `Coupon 1 Discount: 10.0%`. *Origin: GenAI (Claude) found it while testing.*
8. **Search added (menu 10 and 11).** Our MVP says users can search, and our scope lists "search for an item that doesn't exist" as bad input, but the first build had no search option. `Shopper` now calls `containsItem`/`containsCoupon`. *Origin: GenAI (Claude) review against our scope.*
9. **Best coupon (menu 12, stretch goal).** `CouponList.getBestCoupon()` returns the coupon with the biggest discount, or null for an empty list. *Origin: GenAI (Claude) suggestion.*
10. **`printList` uses `toArray`.** It used to loop with `get(i)`, which is O(n²); one pass over `toArray` is O(n), matching the spec. *Origin: GenAI (Claude) review of Big-O.*
11. **Test plan.** The spec's test plan had LinkedChain rows; we dropped them, since LinkedChain is provided and this deliverable tests our app methods. Every public app method has at least one normal and one bad-input test (93 tests), including empty lists and removing what isn't there. `ShopperTest` feeds typed input through `System.setIn`. *Origin: own reflection on the rubric; tests drafted with GenAI.*

### What changed in the LinkedChain?
Nothing in behavior. We used the provided `LinkedChain.java` as is; the only difference is Eclipse's auto-formatting of braces and comments. The app uses `add`, `remove(T)`, `contains`, `size`, `isEmpty`, `get` and `toArray`.

### What happened to our unknowns?
- **How to structure a coupon recommendation system:** partly figured out. Since coupon lists are per category, menu 12 asks for a category and shows that list's biggest discount. Still open: matching coupons to each item in a shopping list automatically.
- **How to create a GUI:** still open. Nothing built.

### What doesn't work yet?
- Finding a list by category in `Shopper` is O(L²) (see Big-O), and the same search loop is copy-pasted 8 times.
- To buy, remove or search for an item you must type its exact name and price.
- If input ends with no `0` (for example Ctrl+Z in the console), `Scanner.nextLine` throws `NoSuchElementException`.
- Lists print newest first, because `LinkedChain.add` puts entries at the front.
- Text-file lists and a GUI (stretch goals) are not built.
- All 93 tests pass; none are failing or missing for current public methods.

## Next Steps

### What is left to finish the MVP (in order)
1. **One helper to find a list by category:** add `findShoppingList(String)` and `findCouponList(String)` in `Shopper` that loop once over `toArray` (O(L)), and replace the 8 copied loops. Owner: **Keshav**
2. **Remove, buy and search by name only:** add `ShoppingList.removeByName(String)` and `containsName(String)`. Owner: **Keshav**
3. **Handle input running out** without crashing (check `scanner.hasNextLine()`). Owner: **Arjun**
4. **Print in the order items were added** and show each list's total price. Owner: **Keshav**
5. **Update the spec and README** to match the final build. Owner: **both**

### Stretch goals
- Coupon recommendation: **committed.** The basic version works (menu 12). Next, show the best coupon for every item in a shopping list. Owner: **Arjun**
- Lists from text files: **committed.** Owner: **Arjun**
- GUI: **dropped.** The console app covers the MVP and we would rather finish it well.

### New tests we will need
- `findShoppingList` / `findCouponList`: category that exists, different case, missing, no lists at all.
- `removeByName` / `containsName`: name present, missing, empty name, empty list.
- Input running out: blank input, input ending mid-question.
- Total price: normal list, empty list.
- Recommendation per item: item with a matching coupon list, without one, with an empty coupon list.
- Text files: valid file, missing file, line with a bad price, empty file.

### Who does what
| Item | Owner |
|---|---|
| Find-list helper + tests | Keshav |
| Remove/buy/search by name + tests | Keshav |
| Input running out + tests | Arjun |
| Print order, total price + tests | Keshav |
| Recommendation per item + tests | Arjun |
| Text-file lists + tests | Arjun |
| Spec/README update | Both |

## Attribution

| File | Who | How |
|---|---|---|
| `LinkedChain.java` | CS 2114 staff | Provided, used unchanged |
| `ShoppingItem.java` | Keshav | Wrote the class. Arjun added the setter/constructor validation |
| `Coupon.java` | Keshav | Wrote the class. Arjun added the setter/constructor validation |
| `ShoppingList.java` | Keshav | Wrote the class. Arjun changed `printList` to use `toArray` |
| `CouponList.java` | Keshav | Wrote the class. Arjun fixed the `printList` crash and added `getBestCoupon` |
| `Shopper.java` | Keshav | Wrote the menu and all original actions. Arjun added search and best coupon (menu 10 to 12) and the re-prompt helpers |
| `ShoppingItemTest.java` | Arjun | GenAI-assisted (Claude tests); reviewed and ran |
| `CouponTest.java` | Arjun | GenAI-assisted (Claude drafted); reviewed and ran |
| `ShoppingListTest.java` | Arjun | GenAI-assisted (Claude tests); reviewed and ran |
| `CouponListTest.java` | Arjun | GenAI-assisted (Claude tests); reviewed and ran |
| `ShopperTest.java` | Arjun | GenAI-assisted (Claude drafted); reviewed and ran |
| `README.md` | Arjun | GenAI-assisted (Claude drafted it); reviewed and edited |

### GenAI note
Arjun used Claude on this deliverable. He gave it our scope, spec, this assignment and Keshav's code. Claude wrote the first drafts of all five test files and this README. It found the coupon-printing crash, and pointed out the missing search option, the validation our spec put in `ShoppingItem` and `Coupon`, and that our spec says bad input should re-prompt. It then wrote those fixes, plus the best-coupon option. We read every change and test, ran all 93 tests, and are responsible for explaining them in the grading session. Keshav wrote his original classes himself, with little bit help of  GenAI.
