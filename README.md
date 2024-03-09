# FrozenFoodCompany

Introduction: 
A local Frozen Food Company has a need to set up a weekly subscription service that delivers frozen meals to customers. The company offers various types of meals to its subscribers.
Subscribers can add/remove meals to/from their subscription, but the number of each type of meal available is limited.
This programme reads a list of registered subscribers and a list of available meals from a file (input_data.txt). The content of the input file should have the following form: 
The first line contains an integer representing the number of registered subscribers, followed by the information about the subscribers (one line for every subscriber with their first name and surname). The next line contains an integer representing the number of different types of meal available, and is followed by the information about the meals (two lines for every meal: one line containing the name of the meal and the second one containing the number of these meals available each week).

Objectives:
- To gain experience in designing an interactive system of practical importance.
- To reinforce your knowledge about list classes in java.
- To gain experience at using the java.lang.Comparable<E> interface, inheritance and generic classes in Java.


The program stores information about meals and subscribers:
1) For each meal, the information required is: the name of the meal and the number meals still available to be ordered.
2) For each subscriber, the office should know their first name, surname and their chosen types of meals together with the number of each type of meal that they have subscribed to. To simplify shipping, a subscriber can be subscribed to at most three different types of meal at a time (but can subscribe to more than three meals in total). We also assume that no two subscribers share both their first name and their surname.

After the initial information has been read from the file, the clerk will work with the program interactively.
The program displays a menu on the screen offering a choice of possible operations, each represented by a lower case letter:
* f: to finish running the program.
* m: to display on the screen information about all the meals.
* s: to display on the screen information about all the subscribers.
* a: to update the stored data when a registered subscriber adds meals to their subscription.
* r: to update the stored data when a registered subscriber removes meals from their subscription



