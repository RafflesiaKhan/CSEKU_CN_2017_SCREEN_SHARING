import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

import javax.swing.JPanel;

public class ClientSendEvents implements KeyListener, MouseMotionListener, MouseListener
{
	
		private Socket Client_Socket = null;
		private JPanel Client_Panel = null;
		private PrintWriter writer_S = null;
		String width = "", height = "";
		double w,h;
	public ClientSendEvents(Socket Soket, JPanel Pnel, String wdth, String heght)
	{
		Client_Socket = Soket;
		Client_Panel = Pnel;
		width = wdth;
		height = heght;
		w = Double.valueOf(width.trim()).doubleValue(); //String to Double & trim() method eliminates leading and trailing spaces
		h = Double.valueOf(width.trim()).doubleValue();

		//Associate event listeners to the panel

		Client_Panel.addKeyListener(this); // ai plane er moddhe event gula add koro
		Client_Panel.addMouseListener(this);
		Client_Panel.addMouseMotionListener(this);

		try{
			//Prepare PrintWriter which will be used to send commands to the client
				writer_S = new PrintWriter(Client_Socket.getOutputStream());
			} 
			catch(IOException ex) 
			{
				ex.printStackTrace();
			}
	}
	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		writer_S.println(CommandsIdentifire.PRESS_MOUSE.getEvent());
		int abutton = arg0.getButton();//Which button was pressed, either LEFT, CENTER, or RIGHT.
		int bButton = 16;// Column of cursor hot spot in bitmap (-16 to 16)
		// // There are 16 buttons in the toolbar. Use the WaitUntil method to wait until all 16 buttons are loaded.

		if(abutton==3){
			bButton = 4;
		}
		writer_S.println(bButton);
		writer_S.flush();
		
	}
	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		writer_S.println(CommandsIdentifire.RELEASE_MOUSE.getEvent());
		int abutton = arg0.getButton();
		int bButton = 16;// Column of cursor hot spot in bitmap (-16 to 16)
		if(abutton==3){
			bButton = 4;
		}
		writer_S.println(bButton);
		writer_S.flush();
		
	}
	@Override
	public void mouseDragged(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseMoved(MouseEvent arg0) {
		// 	Called in response to the user moving the mouse with no mouse buttons pressed.
		//	This event is fired by the component that's currently under the cursor.
		double xScale = (double)w/Client_Panel.getWidth();	// (ServerH/ClientplaneH)* Presnt Mouse position
		double yScale = (double)h/Client_Panel.getHeight();
		writer_S.println(CommandsIdentifire.MOVE_MOUSE.getEvent());
		writer_S.println((int)(arg0.getX()*xScale));//Returns the horizontal x position of the event relative to the source component.
		writer_S.println((int)(arg0.getY()*yScale));//Returns the vertical y position of the event relative to the source component.
		writer_S.flush();
		
	}
	@Override
	public void keyPressed(KeyEvent e) {
		writer_S.println(CommandsIdentifire.PRESS_KEY.getEvent());
		writer_S.println(e.getKeyCode());
		writer_S.flush();
		
	}
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		writer_S.println(CommandsIdentifire.RELEASE_KEY.getEvent());
		writer_S.println(e.getKeyCode());
		writer_S.flush();
		
	}
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}
