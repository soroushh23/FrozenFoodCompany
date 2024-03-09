import java.io.*;
import java.util.Scanner;


public class MainProgram {

    private static SortedLinkedList<Subscriber> subscribersSorted = new SortedLinkedList<>();
    private static SortedLinkedList<Meal> mealSorted = new SortedLinkedList<>();
    private static PrintWriter outFile;


    public static void main(String[] args) throws IOException {
        // A greeting that is printed out to the terminal console.
        System.out.println("Welcome to The FrozenFood Company!");
        openingAndSortingTheTextFile();
        //As soon as the main program starts running a new file of letters.txt is created to write letters to
        // subscribers in the event of not having enough quantity of meals to add to their orders.
        outFile = new PrintWriter("letters.txt");
        boolean done = false; /* A variable that is set false at the beginning and is used in the following while loop that keeps iterating until done is true.*/
        Scanner s = new Scanner(System.in); // The scanner s is created to be used in the following switch statements to prompt user to enter an input
        while (!done) {
            printMenu();
            String response = s.nextLine(); // This scanner prompts user to enter a value in the next line and stores it as the 'response' variable
            switch (response) {
                case "m": // Displays all the meals information.
                    displaySortedMealsList();
                    System.out.println("=================================================================================================");
                    break;
                case "s": // Displays all the information about all registered subscribers.
                    displaySortedSubscriberList();
                    System.out.println("=================================================================================================");
                    break;
                case "a": // To update the stored data when a registered subscriber adds meals to their subscription.
                    addMealsToTheSubscriber();
                    System.out.println("=================================================================================================");
                    break;
                case "r": // To update the stored data when a registered subscriber removes meals from their subscription.
                    removeMealsFromTheSubscriber();
                    System.out.println("=================================================================================================");
                    break;
                case "f": // Stops running the program.
                    done = true;
                    System.out.println("Thank you, Goodbye!");
                    break;
                // In case of the event when the user enters an input other than f,m,s,a,r the following lines would be printed.
                default:
                    System.out.println("Invalid input! Please choose an option from f,m,s,a,r ");
                    System.out.println("=================================================================================================");
                    break;
            }
        }
        outFile.close();
    }

    /**
     * This method assumes that the format of the text file is as described in the coursework specification and in addition to
     * opening up the text file, each line of text is analysed based on their object type and stored accordingly.
     * This method is where the insertionSort method in SortedLinkedList class is implemented.
     *
     * @throws IOException
     */
    private static void openingAndSortingTheTextFile() throws IOException {
        Scanner inFile = new Scanner(new FileReader("input_data.txt"));
        int numberOFSubscribers = Integer.parseInt(inFile.nextLine());
        for (int i = 0; i < numberOFSubscribers; i++) {
            String[] usersInfo = inFile.nextLine().split(" "); // the space refers to the space between first name and last name
            Subscriber sub = new Subscriber(usersInfo[0], usersInfo[1]);
            subscribersSorted.add(sub);
            subscribersSorted.insertionSort();
        }
        int numberOfMealsTypes = Integer.parseInt(inFile.nextLine());
        for (int i = 0; i < numberOfMealsTypes; i++) {
            String mealsName = inFile.nextLine();
            int availableMeals = Integer.parseInt(inFile.nextLine());
            Meal food = new Meal(mealsName, availableMeals);
            mealSorted.add(food);
            mealSorted.insertionSort();
        }
        inFile.close();
    }

    /**
     * This print menu is kept printed in the terminal console until option "f" from the menu
     * is selected and program stops running.
     */
    private static void printMenu() {
        System.out.println();
        System.out.println("Please choose an option from the menu list below:");
        System.out.println("f: Finish running the program");
        System.out.println("m: Display all the meals information");
        System.out.println("s: Display all the subscribers information");
        System.out.println("a: to update the stored data when a registered subscriber adds meals to their subscription.");
        System.out.println("r: to update the stored data when a registered subscriber removes meals from their subscription.");
        System.out.println("=================================================================================================");
    }

    /**
     * This method displays the LinkedList of Subscribers sorted in an alphabetically ascending order
     * when the option "s" on the menu is selected.
     */
    private static void displaySortedSubscriberList() {
        for (Subscriber subscriber : subscribersSorted) {
            System.out.println(subscriber.getFirstName() + " " + subscriber.getLastName() + " " + subscriber.getMealsOrdered());
        }
    }

    /**
     * This method displays the LinkedList of Meals sorted in an alphabetically ascending order.
     * when the option "m" on the menu is selected.
     */
    private static void displaySortedMealsList() {
        for (Meal food : mealSorted) {
            //This line is used to create spaces between the meal type and their corresponding available quantities in the LinkedList.
            System.out.println(String.format("%-13s%13s", food.getName(), food.getMealsLeft()));
        }
    }

    /**
     * This method is used to prompt the clerk to enter a first name and a family name and then checks the validity of the
     * entered name against the customer names in the subscribersSorted SortedLinkedList.
     * This method is called in two methods of addMealsToTheSubscriber and removeMealsToTheSubscriber.
     * The method is also designed to print out appropriate responses when an error occurs (i.e., no match in the
     * subscribersSorted SortedLinkedList).
     *
     * @return an object type of Subscriber that consists of a first and a last name.
     */
    private static Subscriber subscriberValidation() {
        try {
            Scanner scanner = new Scanner(System.in);
            Subscriber subscriber = new Subscriber();
            System.out.println("Please enter the First Name of the customer: ");
            String firstName = scanner.nextLine();
            subscriber.setFirstName(firstName);
            System.out.println("Please enter the Last Name of the customer: ");
            String lastName = scanner.nextLine();
            subscriber.setLastName(lastName);
            for (Subscriber s : subscribersSorted) {
                if (s.getFirstName().equalsIgnoreCase(subscriber.getFirstName()) && s.getLastName().equalsIgnoreCase(subscriber.getLastName())) {
                    System.out.println("Subscriber \"" + s.getFullName() + "\" is in the system.");
                    System.out.println(s.getFirstName() + "\'s order list is: " + s.getMealsOrdered());
                    return s;
                }
            }
            System.out.println("Subscriber not found!");
            return null;
        } catch (Exception e) {
            System.out.println("An error occurred");
            return null;
        }
    }

    /**
     * This method is used to prompt the clerk to enter a meal name and checks its validity against the meals in the
     * mealSorted SortedLinkedList. In case of a successful name validation, the user is prompted to enter a quantity for the meal.
     * This method is called in two methods of addMealsToTheSubscriber and removeMealsToTheSubscriber.
     * The method is also designed to print out appropriate responses when an error occurs (i.e., no match in the
     * subscribersSorted SortedLinkedList)
     *
     * @return an object type of Meal that consists of a meal and its quantity.
     */
    private static Meal mealValidation() {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Please choose the meal type: ");
            String mealName = scanner.nextLine();
            Meal food = new Meal();
            food.setName(mealName);
            for (Meal m : mealSorted) {
                if (m.getName().equalsIgnoreCase(mealName)) {
                    System.out.println("There are " + m.getMealsLeft() + " " + m.getName() + "(s) remained in the company stock.");
                    System.out.println("Choose the meal quantity: ");
                    int quantity = scanner.nextInt();
                    food.setQuantity(quantity);
                    return food;
                }
            }
            System.out.println("Invalid meal name!");
            return null;
        } catch (Exception e) {
            System.out.println("An error occurred");
            return null;
        }
    }

    /**
     * This method is used in option "a" on the menu to enable the clerk to add meals to the list of subscribers.
     */
    private static void addMealsToTheSubscriber() {
        Subscriber customer = subscriberValidation();
        if (customer != null) {
            if (customer.getMealsOrdered().size() < 3) {
                Meal chosenMeal = mealValidation();
                if (chosenMeal != null) {
                    for (Meal m : mealSorted) {
                        if (m.getName().equalsIgnoreCase(chosenMeal.getName())) {
                            if (chosenMeal.getMealsLeft() < 0) {
                                System.out.println("The " + m.getName() + " quantity, can't be a negative number!");
                            } else if (chosenMeal.getMealsLeft() == 0) {
                                System.out.println("The " + m.getName() + " quantity, can't be ZERO!");
                            } else if (chosenMeal.getMealsLeft() > m.getMealsLeft()) {
                                writingToTheLetter(m, customer);
                            } else {
                                if (!customer.checkForMealExists(chosenMeal)) {
                                    customer.addToArrayList(chosenMeal);
                                    m.setQuantity(m.getMealsLeft() - chosenMeal.getMealsLeft());
                                } else {
                                    for (Meal n : customer.getMealsOrdered()) {
                                        if (n.getName().equalsIgnoreCase(chosenMeal.getName())) {
                                            n.setQuantity(n.getMealsLeft() + chosenMeal.getMealsLeft());
                                            m.setQuantity(m.getMealsLeft() - chosenMeal.getMealsLeft());
                                        }
                                    }
                                }
                                System.out.println(chosenMeal.getMealsLeft() + " " + m.getName() + "(s) have been successfully added to " + customer.getFirstName() + ("\'s orders."));
                            }
                        }
                    }
                }
            } else {
                // This is printed to the console when a subscriber is already subscribed to 3 different meals.
                System.out.println("The user can't be subscribed to more than 3 types of meal at a time!\n" +
                        "To add meals to an existent order please first remove the meal from the subscriber using option\n\"r\" " +
                        "in the menu, then place a new order using option \"a\" in the menu.");
            }
        } else {
            System.out.println("Please enter a valid customer name!");
        }
    }

    /**
     * This method is used in option "r" on the menu to enable the clerk to remove meals from the list of subscribers.
     */
    private static void removeMealsFromTheSubscriber() {
        Subscriber customer = subscriberValidation();
        if (customer != null) {
            Meal chosenMeal = mealValidation();
            if (chosenMeal != null && chosenMeal.getMealsLeft() <= customer.quantityOfTheMeal(chosenMeal) && chosenMeal.getMealsLeft() > 0) {
                for (Meal m : customer.getMealsOrdered()) {
                    if (m.getName().equalsIgnoreCase(chosenMeal.getName())) {
                        m.setQuantity(m.getMealsLeft() - chosenMeal.getMealsLeft());
                        for (Meal n : mealSorted) {
                            if (n.getName().equalsIgnoreCase(chosenMeal.getName())) {
                                n.setQuantity(n.getMealsLeft() + chosenMeal.getMealsLeft());
                            }
                        }
                        System.out.println(chosenMeal.getMealsLeft() + " " + chosenMeal.getName() + "(s) have been successfully removed from " + customer.getFirstName() + "\'s orders.");
                        if (m.getMealsLeft() == 0) {
                            customer.dropTheMeal(m);
                            break;
                        }
                    }
                }
            } else {
                System.out.println("An error occurred!");
            }
        }
    }

    /**
     * This method is called when there is either none (0) or not enough quantity of meals available in the system.
     * This method prints out a message to the subscriber informing them what particular type of meal is not available in the
     * form of a letters.txt file.
     * The letter file is saved once the option "f" in the menu is selected.
     * In addition to the content of the letter, the clerk would also be informed by a statement about the range of available
     * meals in the terminal console if there are not enough number of meals available
     *
     * @param meal refers to the Meal object type that holds the two values of name and quantity of the meal.
     * @param customer refers to the Subscriber object type that is used to get the first and last name of the customer.
     */

    private static void writingToTheLetter(Meal meal, Subscriber customer) {
        outFile.println("Dear " + customer.getFullName() + ",\n\nUnfortunately there is not enough of " + meal.getName() + " available at the moment.\n\nSincerely Yours,\nFrozen Food Company\n\n\n");
        System.out.println();

        if (meal.getMealsLeft() == 0) {
            // This is only printed when there is no more meals left to add to the subscriber's order list.
            System.out.println("There are no " + meal.getName() + "(s) left in the store.");
        } else {
            // This is only printed when the input number for meal quantity is more than the number of available meals
            // when adding meals to the subscriber's order list.
            System.out.println("The valid quantity range for " + meal.getName() + " is 1-" + meal.getMealsLeft() + ".");
        }
    }
}
