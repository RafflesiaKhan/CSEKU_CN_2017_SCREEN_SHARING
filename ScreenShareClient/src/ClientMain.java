import java.net.Socket;

import javax.swing.JOptionPane;

public class ClientMain{
	static String port = "4907";//Default port server and client

	public static void main(String args[]){
			String ip = JOptionPane.showInputDialog("Please enter server ip");
			new ClientMain().initialize(ip, Integer.parseInt(port));
			}

	public void initialize(String ip, int port){
			try{
				
				Socket sc = new Socket(ip,port);
				System.out.println("Connecting to the Server");
				//Authenticate class is responsible for security purposes
				ClientLogIn frame1= new ClientLogIn(sc);
	
				frame1.setSize(300,80);
				frame1.setLocation(500,300);
				frame1.setVisible(true);
			} 
			
			catch (Exception ex)
			{
				ex.printStackTrace();
			}
					           
	
	}
		

}



