package cp213;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.math.BigDecimal;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * The GUI for the Order class.
 *
 * @author Chris Tran
 * @version 2023-03-23
 */
@SuppressWarnings("serial")
public class OrderPanel extends JPanel {

    // Attributes
    private Menu menu = null; // Menu attached to panel.
    private final Order order = new Order(); // Order to be printed by panel.
    // GUI Widgets
    private final JButton printButton = new JButton("Print");
    private final JLabel subtotalLabel = new JLabel("0");
    private final JLabel taxLabel = new JLabel("0");
    private final JLabel totalLabel = new JLabel("0");

    private JLabel nameLabels[] = null;
    private JLabel priceLabels[] = null;
    // TextFields for menu item quantities.
    private JTextField quantityFields[] = null;

    /**
     * Displays the menu in a GUI.
     *
     * @param menu The menu to display.
     */
    public OrderPanel(final Menu menu) {
	this.menu = menu;
	this.nameLabels = new JLabel[this.menu.size()];
	this.priceLabels = new JLabel[this.menu.size()];
	this.quantityFields = new JTextField[this.menu.size()];
	this.layoutView();
	this.registerListeners();
    }

    /**
     * Implements an ActionListener for the 'Print' button. Prints the current
     * contents of the Order to a system printer or PDF.
     */
    private class PrintListener implements ActionListener {

	@Override
	public void actionPerformed(final ActionEvent e) {
		 final JButton source = (JButton) e.getSource();

	}
    }

    /**
     * Implements a FocusListener on a JTextField. Accepts only positive integers,
     * all other values are reset to 0. Adds a new MenuItem to the Order with the
     * new quantity, updates an existing MenuItem in the Order with the new
     * quantity, or removes the MenuItem from the Order if the resulting quantity is
     * 0.
     */
    private class QuantityListener implements FocusListener {
	private MenuItem item = null;
	private int index;

	QuantityListener(final MenuItem item,int index) {
	    this.item = item;
	    this.index=index;
	}

	 @Override
     public void focusGained(final FocusEvent evt) {
         OrderPanel.this.quantityFields[this.index].selectAll();
         
     }

     @Override
     public void focusLost(final FocusEvent evt) {
    	 //try parse.int
    	 //catch exception
    	 int num=0;
    	 try {
    		 num= Integer.parseInt(OrderPanel.this.quantityFields[this.index].getText());
    		 if(num<0) {
    			 throw new Exception();
    		 }
    		 order.add(item,num);
    		 String value=String.format("%5.2f", order.getSubTotal());
    		 OrderPanel.this.subtotalLabel.setText(value);
    		 String tax=String.format("%5.2f", order.getTaxes());
    		 OrderPanel.this.taxLabel.setText(tax);
    		 String tot=String.format("%5.2f", order.getTotal());
    		 OrderPanel.this.totalLabel.setText(tot);
    		 
    	 }
    	 catch (Exception e) {
    		 OrderPanel.this.quantityFields[this.index].setText(String.valueOf(0));
    		 order.update(item, 0);
    	 }
     }
    }

    /**
     * Layout the panel.
     */
    private void layoutView() {
    	//size of window
    	this.setBorder(BorderFactory.createEmptyBorder(6,6,6,6));
    	//size (rows(menu size)+ headers, blank, and cost),columns)
    	this.setLayout(new GridLayout(menu.size()+5,3));
    	//instantiating array for formatting
    	JPanel[][] format=new JPanel[menu.size()+5][3];
    	//row
    	for(int i=0;i<menu.size()+5;i++) {
    	//column
    	//assigning where each array slot will be aligned 
    	for(int j=0;j<3;j++) {
    		//left
    		if(j==0&&i>0)
    			format[i][j]=new JPanel(new FlowLayout(FlowLayout.LEFT));
    		else if(j==2&&i>0)
    		//far right
    			format[i][j]=new JPanel(new FlowLayout(FlowLayout.RIGHT));
    		else if(j==1&&i>0)
    			//middle
    			format[i][j]=new JPanel(new FlowLayout(FlowLayout.RIGHT));
    		else
    			format[i][j]=new JPanel();
    	this.add(format[i][j]);
    	}
    	}
    	//Headers
    	JLabel item= new JLabel("Item");
    	JLabel prices= new JLabel("Price");
    	JLabel quantity= new JLabel("Quantity");
    	//assigning where the labels go
    	format[0][0].add(item);
    	format[0][1].add(prices);
    	format[0][2].add(quantity);
    	//Adding labels for each menu name
    	for(int j=0;j<menu.size();j++) {
    		//setting the label
    		this.nameLabels[j]=new JLabel(menu.getItem(j).getName());
    		//creating string for price
    		String price=String.format("$%,.2f", menu.getItem(j).getPrice());
    		//setting the price
    		this.priceLabels[j]=new JLabel(price);
    		//create a quantity action for user
    		//5 is the size of the quantity box
    		this.quantityFields[j]=new JTextField("0",5);
    		this.quantityFields[j].setHorizontalAlignment(JTextField.RIGHT);
    	}
    	//adding the names,labels,and quantity field
    	for(int j=1;j<menu.size();j++) {
    		format[j][0].add(nameLabels[j-1]);
    		format[j][1].add(priceLabels[j-1]);
    		format[j][2].add(quantityFields[j-1]);
    	}
    	//the bottom stuff of the gui
    	format[menu.size()+1][0].add(new JLabel("Subtotal: "));
    	format[menu.size()+2][0].add(new JLabel("Tax: "));
    	format[menu.size()+3][0].add(new JLabel("Total: "));
    	format[menu.size()+1][2].add(subtotalLabel);
    	format[menu.size()+2][2].add(taxLabel);
    	format[menu.size()+3][2].add(totalLabel);
    	format[menu.size+4][1].add(this.printButton);
    }

    /**
     * Register the widget listeners.
     */
    private void registerListeners() {
	// Register the PrinterListener with the print button.
    	//gui_var.addListener(new class);
	this.printButton.addActionListener(new PrintListener());
	for(int i=0;i<this.quantityFields.length;i++) {
		this.quantityFields[i].addFocusListener(new QuantityListener(menu.getItem(i),i));
	
	}
	
	// your code here
    }

}