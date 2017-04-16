import java.awt.Robot;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;
/* Used to recieve server commands then execute them at the client side*/

public class ReceiveShowEvents extends Thread
{
	Socket socket= null;
	Robot robot = null;
	boolean Loop_continue = true;
	
	public ReceiveShowEvents(Socket socket, Robot robot)
	{
		this.socket = socket;
		this.robot = robot;
		start(); //Start the thread and hence calling run method
	}
	
	public void run() 
	{
		Scanner scanner=null;
		try {
			scanner = new Scanner(socket.getInputStream());
			
			while (Loop_continue) 
			{
				int take_command = scanner.nextInt();
				
				switch(take_command)
				{
					case-1:
						{
							robot.mousePress(scanner.nextInt());
							break;
						}
					case-2:
						{
							robot.mouseRelease(scanner.nextInt());
							break;
						}
					case-3:
						{
							robot.keyPress(scanner.nextInt());
							break;
						}
					case-4:
						{
							robot.keyRelease(scanner.nextInt());
							break;
						}
						
					case-5:
						{
							robot.mouseMove(scanner.nextInt(),scanner.nextInt());
							break;
						}
						
				}
			}
			
		} 
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}
