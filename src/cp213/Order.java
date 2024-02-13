package cp213;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Set;

/**
 * Stores a HashMap of MenuItem objects and the quantity of each MenuItem
 * ordered. Each MenuItem may appear only once in the HashMap.
 *
 * @author Chris Tran
 * @version 2023-03-23
 */
public class Order implements Printable {

    /**
     * The current tax rate on menu items.
     */
    public static final BigDecimal TAX_RATE = new BigDecimal(0.13);
    
    // Attributes
    BigDecimal subTotal=null;
    BigDecimal Total=null;
    HashMap<MenuItem, Integer> order = new HashMap<MenuItem, Integer>();
    // your code here

    /**
     * Increments the quantity of a particular MenuItem in an Order with a new
     * quantity. If the MenuItem is not in the order, it is added.
     *
     * @param item     The MenuItem to purchase - the HashMap key.
     * @param quantity The number of the MenuItem to purchase - the HashMap value.
     */
    public void add(final MenuItem item, final int quantity) {
    	if(quantity>0) {
    	if(order.containsKey(item))
    	order.put(item, order.get(item)+quantity);
    	else {
    		order.put(item, quantity);
    	}
    	BigDecimal num=BigDecimal.valueOf(quantity);
    	BigDecimal val=item.getPrice().multiply(num);
    	if(this.subTotal==null) {
    		this.subTotal=val;}
    	else
    	this.subTotal=this.subTotal.add(val);
    	}
    	this.Total=this.subTotal;
    }

    /**
     * Calculates the total value of all MenuItems and their quantities in the
     * HashMap.
     *
     * @return the total price for the MenuItems ordered.
     */
    public BigDecimal getSubTotal() {
	

	return this.subTotal;
    }

    /**
     * Calculates and returns the total taxes to apply to the subtotal of all
     * MenuItems in the order. Tax rate is TAX_RATE.
     *
     * @return total taxes on all MenuItems
     */
    public BigDecimal getTaxes() {

	// your code here
    	BigDecimal num=this.TAX_RATE.multiply(this.subTotal);
	return num;
    }

    /**
     * Calculates and returns the total price of all MenuItems order, including tax.
     *
     * @return total price
     */
    public BigDecimal getTotal() {
	return this.Total.add(this.getTaxes());
    }

    /*
     * Implements the Printable interface print method. Prints lines to a Graphics2D
     * object using the drawString method. Prints the current contents of the Order.
     */
    @Override
    public int print(final Graphics graphics, final PageFormat pageFormat, final int pageIndex)
	    throws PrinterException {
	int result = PAGE_EXISTS;

	if (pageIndex == 0) {
	    final Graphics2D g2d = (Graphics2D) graphics;
	    g2d.setFont(new Font("MONOSPACED", Font.PLAIN, 12));
	    g2d.translate(pageFormat.getImageableX(), pageFormat.getImageableY());
	    // Now we perform our rendering
	    final String[] lines = this.toString().split("\n");
	    int y = 100;
	    final int inc = 12;

	    for (final String line : lines) {
		g2d.drawString(line, 100, y);
		y += inc;
	    }
	} else {
	    result = NO_SUCH_PAGE;
	}
	return result;
    }

    /**
     * Returns a String version of a receipt for all the MenuItems in the order.
     */
    @Override
    public String toString() {
    	String receipt="Receipt\n";
    	Set<MenuItem> list=this.order.keySet();
    	for(MenuItem A:list) {
    		BigDecimal num=BigDecimal.valueOf(this.order.get(A));
    		String price=A.getPrice().toString();
//    		receipt+=String.format("%-17s$%-5s @ $ %-s = $ %-s\n", 
//    				A.getName().toString(),this.order.get(A),A.getPrice().toString(),A.getPrice().multiply(num).toString());
    		receipt+=String.format("%-13s$", A.getName());
    		receipt+=String.format("%-2d @ $ ", this.order.get(A));
    		receipt+=String.format("%-4s = $ ", price);
    		receipt+=String.format(" %-4s\n",A.getPrice().multiply(num).toString());
    	}
    	receipt+=String.format("Subtotal:                   $ %5.2f\n", this.subTotal);
    	receipt+=String.format("Taxes:                      $ %5.2f\n", this.getTaxes());
    	receipt+=String.format("Total:                      $ %5.2f\n", this.subTotal.add(this.getTaxes()));
    	
	return receipt;
    }

    /**
     * Replaces the quantity of a particular MenuItem in an Order with a new
     * quantity. If the MenuItem is not in the order, it is added. If quantity is 0
     * or negative, the MenuItem is removed from the Order.
     *
     * @param item     The MenuItem to update
     * @param quantity The quantity to apply to item
     */
    public void update(final MenuItem item, final int quantity) {
    	//if 0 or negative item is removed
    	if(quantity==0)
    		this.order.remove(item);
    	else
    		this.order.put(item, quantity);

    }
}