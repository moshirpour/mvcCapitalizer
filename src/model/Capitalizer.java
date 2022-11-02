package model;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

public class Capitalizer implements Runnable {
	
	private PrintWriter socketOut;
	private BufferedReader socketIn;
	
	
	public Capitalizer (BufferedReader socketIn, PrintWriter socketOut) {
		
		this.socketIn = socketIn;
		this.socketOut = socketOut;
		
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		String line = null;

		while (true) {
			try {
				line = socketIn.readLine();
				if (line.equals("QUIT")) {
					break;
				}
				line = line.toUpperCase();
				socketOut.println(line);
			} catch (IOException e) {
				e.getStackTrace();
			}
		}

	}

}
