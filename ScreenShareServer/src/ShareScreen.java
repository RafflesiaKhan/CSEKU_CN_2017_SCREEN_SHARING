import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import javax.imageio.ImageIO;


public class ShareScreen extends Thread
{
	Socket socket=null;
	Robot robotObj=null;
	Rectangle rectangleObj=null;
	boolean Loop_continue=true;
	OutputStream ops=null;
	
	public ShareScreen(Socket socket,Robot robot,Rectangle rectangle) 
	{
			this.socket=socket;
			this.robotObj=robot;
			this.rectangleObj=rectangle;
			start();//Start the thread and hence calling run method
	}
	
	public void run() 
	{
		
		try {
			
			ops=socket.getOutputStream();//FileOutputStream 
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		while (Loop_continue) // Contineously Capture picture
		{
				BufferedImage image=robotObj.createScreenCapture(rectangleObj);// Capture the screen
				//pass the perviously created rectangle instance as the argument.
				//This creates a BufferedImage, which we pass as the first argument in the ImageIO.write() method. The second argument we pass in the file format. In the third  argument, we pass an FileOutputStream that’ll write the image to disk.
				
				try {
					
					ImageIO.write(image,"jpeg",ops);//Sending Image ip client
				} 
				catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
				
				try {
					
					Thread.sleep(10);
				} 
				catch (InterruptedException e) {
					// TODO: handle exception
					e.printStackTrace();
				}

		}
		
	}
}
