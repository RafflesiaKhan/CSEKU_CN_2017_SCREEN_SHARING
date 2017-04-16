import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

class ClientLogIn extends JFrame implements ActionListener
{
		private Socket cSocket = null;
		DataOutputStream psswrchk = null;
		DataInputStream verification = null;
		String Pass_verify ="";
		JButton SUBMIT;
		JPanel panel;
		JLabel label_1, label_2;
		String width="",height="";
		final JTextField text_1;

	ClientLogIn(Socket cSocket)
	{
		// TODO Auto-generated constructor stub
		
				label_2=new JLabel();
				label_2.setText("Enter Password");
				text_1 = new JTextField(15);
				this.cSocket = cSocket;

				label_1=new JLabel();
				label_1.setText("");
				this.setLayout(new BorderLayout());

				SUBMIT = new JButton("SUBMIT");
				
				//panel=new JPanel(new BorderLayout(2,1));
				panel=new JPanel(new GridLayout(2,1));
				panel.add(label_2);
				panel.add(text_1);
				panel.add(label_1);
				panel.add(SUBMIT);
				add(panel,BorderLayout.CENTER);
				SUBMIT.addActionListener(this);
				setTitle("LOGIN FORM");// LogIn for Client made
	}


	public void actionPerformed(ActionEvent ae)
	{
		// TODO Auto-generated method stub
				String read_Pass=text_1.getText();//get text from the text box
				
				try 
				{
					psswrchk= new DataOutputStream(cSocket.getOutputStream());// Objects for verifing the password with server's set one
					verification= new DataInputStream(cSocket.getInputStream());
					psswrchk.writeUTF(read_Pass);// write the read_Pass in server 
					Pass_verify=verification.readUTF();// read the server send verification command
				} 
				catch (IOException e)
				{
					// TODO: handle exception
					e.printStackTrace();
				}
				
				if (Pass_verify.equals("valid")) // If Server verifies that
				{	
					try 
					{
						// reading the other 2 from Place1 wirttings from server
						width = verification.readUTF();
						height = verification.readUTF();
					} 
					catch (IOException e)
					{
						// TODO: handle exception
						e.printStackTrace();
					}
					
					CreateClientFrame abc= new CreateClientFrame(cSocket,width,height);// creating the Screen frame for client
					dispose();// frame dispose
					
				} 
				
				else {
					System.out.println("enter the valid password");
					JOptionPane.showMessageDialog(this, "Incorrect  password", "Error", JOptionPane.ERROR_MESSAGE);
					dispose();
				}
	
	}
			
}

