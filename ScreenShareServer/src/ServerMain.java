import javax.swing.JFrame;
public class ServerMain 
{
	
	public static void main(String[] args)
	{
			// make a passwore set box and set the password for giving access to only password varified client
			SetPassword SetPasswordFrame= new SetPassword();
			SetPasswordFrame.setSize(300,80); 				
			SetPasswordFrame.setLocation(500,300);
			SetPasswordFrame.setVisible(true);	 
	}
	
}
