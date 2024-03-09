import java.util.LinkedList;


/**
 * This generic class is designed in a way that would be able to sort both of the meal and subscriber object types.
 */
public class SortedLinkedList<E extends Comparable<? super E>> extends LinkedList<E> {
    /**
     * The following method uses Insertion Sort algorithm to sort the two LinkedLists of subscribersSorted and mealSorted
     * that hold different object types of Subscriber and Meal respectively.
     * Both LinkedLists are stored in ascending order lexicographically.
     * Subscribers are stored firstly based on their surnames and then in case having the same surname they would be
     * sorted based on their first name.
     * Meals are sorted based on their names.
     */

    public void insertionSort() {
        for (int i = 1; i < this.size(); i++) {
            E value = this.get(i);
            int j;
            for (j = i; j > 0; j--) {
                if (this.get(j - 1).compareTo(value) < 0) {
                    break;
                } else {
                    this.set(j, this.get(j - 1));
                }
            }
            this.set(j, value);
        }
    }
}