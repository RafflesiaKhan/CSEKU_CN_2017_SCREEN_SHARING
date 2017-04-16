import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class SetPassword extends JFrame implements ActionListener
{
	public static String port_nember="4907";// Server Uses this port
	JButton SUBMIT;/* JButton class to create simple buttons and add event handlers to them.*/
	JPanel panelObj;/*The JPanel class provides general-purpose containers for lightweight components.*/
	JLabel label_2,label_1;
	JTextField text_1;
	String string_1;
	
	public SetPassword()
	{
		// TODO Auto-generated constructor stub
		label_1=new JLabel();
		label_1.setText("Set Password");// Ask for setting password
		text_1 = new JTextField(15);//Creates a new text field with a 15 columns.
		
		label_2=new JLabel();
		label_2.setText("");
		
		this.setLayout(new BorderLayout());/*The BorderLayout divides the container into five areas which include: PAGE_START, PAGE_END, LINE_START, CENTER and LINE_END*/
		
		SUBMIT = new JButton("SUBMIT");
		
		// Adding the label and textbox on panel
		panelObj=new JPanel(new GridLayout(2,1));/*Creates a grid layout with a given number of rows and columns*/
		panelObj.add(label_1);
		panelObj.add(text_1);
		
		panelObj.add(label_2);//for extra space
		panelObj.add(SUBMIT);//Adding the button
		
		add(panelObj,BorderLayout.CENTER);// sets the panel at the center
		
		SUBMIT.addActionListener(this);// call the action method when button pressed
		
		setTitle("Set Password for connecting with Client");// Title of the Jpanel SetPassword box
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0)
	{
		// TODO Auto-generated method stub
		string_1=text_1.getText();// Get the password given
		dispose();// oi coto window ta dispose
		new SetConnection(Integer.parseInt(port_nember),string_1);
		
	}
	
	public String getString1()
	{
		return string_1;
	}
	
	
}
