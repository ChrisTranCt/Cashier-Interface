package cp213;

import java.util.Collection;
import java.util.Scanner;

/**
 * Stores a List of MenuItems and provides a method return these items in a
 * formatted String. May be constructed from an existing List or from a file
 * with lines in the format:
 *
 * <pre>
1.25 hot dog
10.00 pizza
...
 * </pre>
 *
 * @author Chris Tran
 * @version 2023-03-23
 */
public class Menu {

    // Attributes.
	int i=0;
	int size=0;
	private MenuItem[] food=new MenuItem[7];
    // your code here

    /**
     * Creates a new Menu from an existing Collection of MenuItems. MenuItems are
     * copied into the Menu List.
     *
     * @param items an existing Collection of MenuItems.
     */
    public Menu(Collection<MenuItem> items) {

    	for (MenuItem val : items) {
    		this.food[this.i]=val;
    		this.i++;
    		this.size++;
    	}
    }

    /**
     * Constructor from a Scanner of MenuItem strings. Each line in the Scanner
     * corresponds to a MenuItem. You have to read the Scanner line by line and add
     * each MenuItem to the List of items.
     *
     * @param fileScanner A Scanner accessing MenuItem String data.
     */
    public Menu(Scanner fileScanner) {
    	while(fileScanner.hasNext()&&this.i<=7) {
    		String data=fileScanner.nextLine();
    		String[] word=data.split(" ",2);
    		MenuItem fresh=new MenuItem(word[1], Double.parseDouble(word[0]));
    		food[this.i]=fresh;
    		this.i++;
    		this.size++;
    	}
    	

    }

    /**
     * Returns the List's i-th MenuItem.
     *
     * @param i Index of a MenuItem.
     * @return the MenuItem at index i
     */
    public MenuItem getItem(int i) {

	// your code here

	return this.food[i];
    }

    /**
     * Returns the number of MenuItems in the items List.
     *
     * @return Size of the items List.
     */
    public int size() {


	return this.size;
    }

    /**
     * Returns the Menu items as a String in the format:
     *
     * <pre>
    5) poutine      $ 3.75
    6) pizza        $10.00
     * </pre>
     *
     * where n) is the index + 1 of the MenuItems in the List.
     */
    @Override
    public String toString() {
    	String sentence="";
    	for(int z=0;z<this.size;z++) {
    		sentence+=this.food[z].toString()+"\n";
    	}

	return sentence;
    }
}