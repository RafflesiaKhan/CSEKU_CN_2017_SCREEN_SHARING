import java.awt.BorderLayout;
import java.beans.PropertyVetoException;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import java.util.zip.*;

public class CreateClientFrame extends Thread
{
	
	String width="", height="";
	private JFrame frame_Client = new JFrame();
	
	//JDesktopPane represents the main container that will contain all connected clients' screens
	
	private Socket Client_Socket = null;
	
	private JDesktopPane desktopObj = new JDesktopPane();
	
	//This constructor creates an internal frame with the title, that can be resize, maximize, close, and iconifiable.
	private JInternalFrame internalFrame = new JInternalFrame("Server Screen", true, true, true);
	//Creates a frame within a frame
	private JPanel panel = new JPanel();
	
	public CreateClientFrame(Socket Socket, String width, String height) {
		// TODO Auto-generated constructor stub
		this.width = width;
		this.height = height;
		this.Client_Socket = Socket;
		start();//Start the thread and hence calling run method
	}
	
	
	//Draw GUI per each connected client
	public void creatGUI()
	{
		frame_Client.add(desktopObj, BorderLayout.CENTER);
		frame_Client.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//Show the frame in maximized state
		//	frame.setExtendedState(frame.getExtendedState()|JFrame.MAXIMIZED_BOTH);
			
		frame_Client.setExtendedState(frame_Client.getExtendedState()|JFrame.MAXIMIZED_BOTH);		//CheckE
		frame_Client.setVisible(true);
			
			internalFrame.setLayout(new BorderLayout());
			internalFrame.getContentPane().add(panel, BorderLayout.CENTER);
			internalFrame.setSize(100,100);
			desktopObj.add(internalFrame);// pass the inner frame to main container desktop
			
			
			try 
			{
				//Initially show the internal frame maximized
				internalFrame.setMaximum(true);
			}
			catch (PropertyVetoException ex) 
			{ 
					ex.printStackTrace();
			}

			//This allows to handle KeyListener events
			//The call to the setFocusable(true) method makes the component focusable.
			panel.setFocusable(true);
			internalFrame.setVisible(true);//visible korara jonno
	}
	
	public  void run()
	{
		//Used to read screenshots
		InputStream in = null;
		//start drawing GUI
	    creatGUI();
		
	    try {
	    	in = Client_Socket.getInputStream();
		} catch (IOException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	    
	    	//Start receiving screenshots
	  		new ClientReceiveScreen(in,panel);
	  		//Start sending events to the client
	  		new ClientSendEvents(Client_Socket,panel,width,height);
	    
	}
	
	

}
