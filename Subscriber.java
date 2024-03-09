import java.util.ArrayList;

/**
 * This class is used to create an object type of Subscriber that not only stores three different fields of first name,
 * last name and orders, but also uses various methods to manage and manipulate multiple functionalities on the
 * subscriber object.
 * This class also implements the Comparable interface that allows ordering the subscriber objects lexicographically
 * by using the insertionSort algorithm in the SortedLinkedList class.
 */
public class Subscriber implements Comparable<Subscriber> {

    // Fields

    private String firstName;
    private String lastName;
    private ArrayList<Meal> orders;


    // Constructors

    /**
     * This constructor was created to be able to create a new subscriber type object without passing any parameters.
     */
    public Subscriber() {
    }

    /**
     * This constructor was created to be able to create a subscriber type object by passing their first and last name.
     * this constructor also creates an empty ArrayList of orders when a new subscriber object is created.
     *
     * @param firstName refers to the first name of the customer.
     * @param lastName  refers to the surname of the customer.
     */
    public Subscriber(String firstName, String lastName) {

        this.firstName = firstName;
        this.lastName = lastName;
        this.orders = new ArrayList<>();
    }

    // The setter methods

    /**
     * Sets the first name of the customer object by passing the firstName parameter.
     *
     * @param firstName refers to the first name of the customer.
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * sets the last name of the customer object by passing the lastName parameter.
     *
     * @param lastName refers to the last name of the customer.
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    // The Getter Methods

    /**
     * @return This method returns the first name of the subscriber object.
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * @return This method returns the last name of the subscriber object.
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * @return This method returns the first name and the last name of the subscriber object.
     */
    public String getFullName() {
        return firstName + " " + lastName;
    }

    /**
     * @return This method returns the orders ArrayList of the subscriber object.
     */
    public ArrayList<Meal> getMealsOrdered() {
        return orders;
    }

    // Other Methods

    /**
     * This method adds meal objects to the ArrayList of orders.
     *
     * @param m refers to the meal object type
     */
    public void addToArrayList(Meal m) {
        if (orders == null) {
            orders = new ArrayList<>();
        }
        orders.add(m);
    }

    /**
     * This method is used to check whether a meal type object that is passed as an argument, is already existent in the
     * orders ArrayList by comparing their meal names.
     * This method is case-insensitive.
     *
     * @param m refers to the meal object type.
     * @return a boolean that is initially set to false but if the name of the meal matches a name in the orders ArrayList
     * the boolean is set to true.
     */
    public boolean checkForMealExists(Meal m) {
        boolean b = false;
        for (Meal k : orders) {
            if (k.getName().equalsIgnoreCase(m.getName())) {
                b = true;
            }
        }
        return b;
    }

    /**
     * This method takes a meal object type as a parameter and checks its existence in the orders ArrayList. In the event
     * of finding a match in the ArrayList, the quantity of the meal object is returned as an integer.
     *
     * @param m refers to the meal object type.
     * @return The quantity of the meal object is returned as an integer.
     */
    public int quantityOfTheMeal(Meal m) {
        int quant = 0;
        for (Meal k : orders) {
            if (k.getName().equalsIgnoreCase(m.getName())) {
                quant = k.getMealsLeft();
            }
        }
        return quant;
    }

    /**
     * This method takes a meal object type as a parameter and checks its existence in the orders ArrayList. In the event
     * of finding a match in the ArrayList, the index of the meal object in the ArrayList is returned.
     *
     * @param m refers to the meal object type.
     * @return The index of the meal object in the ArrayList is returned.
     */
    public int returnInt(Meal m) {
        int index = 0;
        for (Meal k : orders) {
            if (k.getName().equalsIgnoreCase(m.getName())) {
                index = orders.indexOf(k);
            }
        }
        return index;
    }

    /**
     * This method uses the returnInt method to remove the meal object from the orders Arraylist.
     *
     * @param m refers to the meal object type.
     */
    public void dropTheMeal(Meal m) {
        orders.remove(returnInt(m));
    }


    //The toString method

    /**
     * @return This method returns the subscriber object in the form of a string.
     */
    @Override
    public String toString() {
        return firstName + " " + lastName + " " + orders;
    }

    //The compareTo method

    /**
     * "The following were obtained from Oracle Java documentation for the Interface Comparable<T>"
     * This method initially compares two strings of last names and then the two strings of first names lexicographically.
     * This method is necessary for the subscriber class to function as it implements the comparable interface.
     *
     * @param o The subscriber object to be compared.
     * @return A negative integer, zero, or a positive integer as this object is less than, equal to, or greater than
     * the specified subscriber object.
     */
    @Override
    public int compareTo(Subscriber o) {
        int lnComparison = lastName.compareTo(o.lastName);
        if (lnComparison != 0)
            return lnComparison;
        return firstName.compareTo(o.firstName);
    }
}