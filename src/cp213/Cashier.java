package cp213;
import java.util.Scanner;
/**
 * Wraps around an Order object to ask for MenuItems and quantities.
 *
 * @author Chris Tran
 * @author Abdul-Rahman Mawlood-Yunis
 * @author David Brown
 * @version 2023-03-23
 */
public class Cashier {

    // Attributes
    private Menu menu = null;

    /**
     * Constructor.
     *
     * @param menu A Menu.
     */
    public Cashier(Menu menu) {
	this.menu = menu;
    }

    /**
     * Prints the menu.
     */
    private void printCommands() {
	System.out.println("\nMenu:");
	System.out.println(menu.toString());
	System.out.println("Press 0 when done.");
	System.out.println("Press any other key to see the menu again.\n");
    }

    /**
     * Asks for commands and quantities. Prints a receipt when all orders have been
     * placed.
     *
     * @return the completed Order.
     */
    public Order takeOrder() {
//    	Scanner command = new Scanner(System.in);
//    	Scanner quantity = new Scanner(System.in);
//    	int quant;
//    	int com;
//    	Order order = new Order();
//	System.out.println("Welcome to WLU Foodorama!\n");
//	printCommands();
//	while(true) {
//    	try {
//    		System.out.print("\nCommand: ");
//    	com=command.nextInt();
//    	System.out.print("\nHow many do you want? ");
//    	quant=quantity.nextInt();
//    	//add these items
//    	order.add(menu.getItem(com), quant);
//    	}
//    	catch(java.util.InputMismatchException e) {
//    		if(command.nextInt()==(0)||quantity.nextInt()==0) {
//    			break;
//    		}
//    		else {
//    		System.out.println("Not a valid number");
//    		System.out.println(menu.toString());
//    		}
//    	}
//	}
//	System.out.println("----------------------------------------");
//	String receipt=order.toString();
//	System.out.println(receipt);
	return null;
    }
	
}