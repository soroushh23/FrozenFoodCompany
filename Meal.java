/**
 * This class is used to create an object type of Meal that has two fields of meal name and their corresponding quantity.
 * This class also implements the Comparable interface that allows ordering the meal objects lexicographically
 * by using the insertionSort algorithm in the SortedLinkedList class.
 */
public class Meal implements Comparable<Meal> {
    //fields
    private String mealName;
    private int mealsLeft;


    // Constructors

    /**
     * This constructor was created to be able to create a new meal type object without passing any parameters.
     */
    public Meal() {
    }

    /**
     * This constructor was created to be able to create a new meal type object that would first store the string of mealName
     * and then followed by an integer that would correspond to the number of available meals called mealsLeft.
     */
    public Meal(String mealName, int mealsLeft) {
        this.mealName = mealName;
        this.mealsLeft = mealsLeft;
    }


    // The setter methods

    /**
     * Sets the quantity of the meal object by passing the mealsLeft parameter.
     *
     * @param mealsLeft refers to the quantity of the meal.
     */
    public void setQuantity(int mealsLeft) {
        this.mealsLeft = mealsLeft;
    }

    /**
     * Sets the name of the meal object by passing the mealName parameter.
     *
     * @param mealName refers to the meal name.
     */
    public void setName(String mealName) {
        this.mealName = mealName;
    }

    // The getter methods

    /**
     * @return This method returns the name of the meal object.
     */
    public String getName() {
        return mealName;
    }

    /**
     * @return This method returns the quantity of the meal object.
     */
    public int getMealsLeft() {
        return mealsLeft;
    }

    //The toString method

    /**
     * @return This method returns the meal object in the form of a string.
     */
    @Override
    public String toString() {
        return mealName + " " + mealsLeft;
    }

    //The compareTo method

    /**
     * "The following were obtained from Oracle Java documentation for the Interface Comparable<T>"
     * This method compares two string of meal name lexicographically and is necessary for the Meal class to function
     * as it implements the comparable interface.
     *
     * @param o the Meal object to be compared.
     * @return A negative integer, zero, or a positive integer as this object is less than, equal to, or greater than
     * the specified Meal object.
     */
    @Override
    public int compareTo(Meal o) {
        return mealName.compareTo(o.mealName);
    }
}