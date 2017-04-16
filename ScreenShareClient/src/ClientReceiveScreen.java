import java.awt.Graphics;
import java.awt.Image;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class ClientReceiveScreen extends Thread
{
	//private ObjectInputStream Client_ObjectInputStream = null;
	private JPanel cPanel = null;
	private boolean Loop_continue = true;
	InputStream oinS = null;
	Image image_1 = null;
	
	public ClientReceiveScreen(InputStream in, JPanel cPnel) {
		// TODO Auto-generated constructor stub
		oinS = in;//for reading the JPEG file body data 
		cPanel = cPnel;
		start();
	}
	
	public void run() {
		
		try 
		{
			//Reading  screenshots of the client and  drawing them
			while (Loop_continue) 
			{
				byte[] bytes = new byte[1024*1024];
				int count = 0;
				do
				{
					count+=oinS.read(bytes,count,bytes.length-count);
					//This method reads up to bytes.length-count bytes of data from the input stream into an array of bytes.
					//read the JPEG file body data 
					//System.out.println(count);
					
				}while(!(count>4 && bytes[count-2]==(byte)-1 && bytes[count-1]==(byte)-39));

				image_1 = ImageIO.read(new ByteArrayInputStream(bytes));
				image_1 = image_1.getScaledInstance(cPanel.getWidth(),cPanel.getHeight(),Image.SCALE_FAST);

				//Draw the received screenshots

				Graphics graphics = cPanel.getGraphics();
				graphics.drawImage(image_1, 0, 0, cPanel.getWidth(), cPanel.getHeight(), cPanel);//method draws an image at a specific location
				
				//o dynamically scale an image you use: uSe
					//graphics.drawImage(image1, 0, 0, cPanel.getWidth(), cPanel.getHeight(), cPanel);
			    
			}
		} 
		
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}

}
