package controller;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.*;

import model.Capitalizer;

public class ServerWithThreadPool {

	private Socket aSocket;
	private ServerSocket serverSocket;
	private PrintWriter socketOut;
	private BufferedReader socketIn;
	
	private ExecutorService pool;

	public ServerWithThreadPool(int port) {
		try {
			serverSocket = new ServerSocket(port);
			pool = Executors.newFixedThreadPool(2);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void runServer () {
		try {
			while (true) {
				aSocket = serverSocket.accept();
				System.out.println("Connection accepted by server!");
				socketIn = new BufferedReader (new InputStreamReader (aSocket.getInputStream()));
				socketOut = new PrintWriter((aSocket.getOutputStream()), true);
				
				Capitalizer cap = new Capitalizer (socketIn, socketOut);
				pool.execute(cap);
			}
			
		}catch (IOException e) {}
		pool.shutdown();
		
		try {
			socketIn.close();
			socketOut.close();
			aSocket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public static void main (String [] args){
		ServerWithThreadPool myServer = new ServerWithThreadPool (9898);
		myServer.runServer();
	
	}

}
