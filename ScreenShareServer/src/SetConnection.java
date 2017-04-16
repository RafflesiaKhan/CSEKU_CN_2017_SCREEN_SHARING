import java.awt.Dimension;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.*;

public class SetConnection
{
	ServerSocket socket = null;// Creates the interface between the server and client
	DataInputStream password = null; // Used for Reading data that is send by the client
	DataOutputStream verify_Pass = null; // Used for writting or sending data to client back after reading 
	String width="", height="";
	//String ;
	// By calling the constructor of SetConnection class connection would be set so constructor have this connection code
	
	
	
	public SetConnection(int port_number,String Pass_string)
	{
		Robot robotObj = null;
		Rectangle rectangleObj = null;
		
		try
		{
			System.out.println("Waiting for connecting with client ----");
			socket=new ServerSocket(port_number);// Establish the connection Here 
			//System.out.println("Awaiting Connection from Client");
			
			GraphicsEnvironment gEnvObj = GraphicsEnvironment.getLocalGraphicsEnvironment();//Returns the local GraphicsEnvironment.
			GraphicsDevice gDevObj = gEnvObj.getDefaultScreenDevice();//Returns the default screen GraphicsDevice.
			
			Dimension screenSize =Toolkit.getDefaultToolkit().getScreenSize();//gets the dimensions of the screen
			String width=""+screenSize .getWidth();
			String height=""+screenSize .getHeight();
			rectangleObj=new Rectangle(screenSize );//dimensions is used to create a Rectangle instance.
			robotObj=new Robot(gDevObj);//
			
			while(true)
			{
				Socket sc=socket.accept();// Connection with client accepted
				password=new DataInputStream(sc.getInputStream());// object for reading the input pass of client 
				verify_Pass=new DataOutputStream(sc.getOutputStream());//
				//String username=password.readUTF();
				String pssword=password.readUTF();
				
				if(pssword.equals(Pass_string)) // client er pathano pass ta jodi valid hoy then
				{
					verify_Pass.writeUTF("valid");// Place1 here server sends the pass is valid + the hight and width of screen
					verify_Pass.writeUTF(width);
					verify_Pass.writeUTF(height);
					new ShareScreen(sc,robotObj,rectangleObj);// Screen shot create ...
					//new ReceiveShowEvents(sc,robotObj,rectangleObj);
					new ReceiveShowEvents(sc,robotObj);
				}
				else{
					verify_Pass.writeUTF("Invalid");
				}
			}
			
		} catch (Exception e)
		{
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}



