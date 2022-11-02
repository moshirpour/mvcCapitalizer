package view;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class ClientController {
	
	private Socket aSocket;
	private PrintWriter socketOut;
	private BufferedReader socketIn;
	private BufferedReader stdIn;
	private ClientView theView;
	
	public ClientController (String serverName, int portNumber, ClientView theView) {
		
		try {
			aSocket = new Socket (serverName, portNumber);
			//keyboard input stream
			stdIn = new BufferedReader (new InputStreamReader (System.in));
			//Socket input stream
			socketIn = new BufferedReader (new InputStreamReader (aSocket.getInputStream()));
			socketOut = new PrintWriter((aSocket.getOutputStream()), true);
			this.theView = theView;
			theView.addCapListener(e ->  {
				String input = theView.getUserInput();
				String response = communicate (input);
				theView.setResultedString(response);
			});

			theView.addCloseListener (new WindowAdapter() {


				public void windowClosing(WindowEvent e) {

					socketOut.println("QUIT");
					try {
						stdIn.close();
						socketIn.close();
						socketOut.close();
					}catch(IOException ex) {
						ex.getStackTrace();
					}
				}
				});
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public String communicate (String userInput) {
		String line = userInput;
		String response = "";
		
			try {
				
				socketOut.println(line);
				response = socketIn.readLine(); // read response from the socket
				
			}catch(IOException e) {
				e.getStackTrace();
			}

			return response;	
	}
	
	
	public static void main (String [] args) throws IOException{
		ClientView theView = new ClientView();
		ClientController myClient = new ClientController ("localhost", 9898, theView);
	}
	
	
	

}
