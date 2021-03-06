package com.socket.demo.socketserver;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * https://stackoverflow.com/questions/1776457/java-client-server-application-with-sockets
 * @author syli
 *
 */

public class MyServerSocket {

	ServerSocket providerSocket;
	Socket connection = null;
	ObjectOutputStream out;
	ObjectInputStream in;
	String message;

	MyServerSocket() {
	}

	void run() {
		try {
			// 1. creating a server socket
			providerSocket = new ServerSocket(2004, 10);
			// 2. Wait for connection
			System.out.println("Waiting for connection");
			connection = providerSocket.accept();
			System.out.println("Connection received from " + connection.getInetAddress().getHostName());
			// 3. get Input and Output streams
			out = new ObjectOutputStream(connection.getOutputStream());
			out.flush();
			in = new ObjectInputStream(connection.getInputStream());
			
			sendMessage("Connection successful");
			// 4. The two parts communicate via the input and output streams
			do {
				try {
					message = (String) in.readObject();
					System.out.println("client>" + message);
					
					if (message.equals("bye"))
						sendMessage("bye");
				} catch (ClassNotFoundException classnot) {
					System.err.println("Data received in unknown format");
				}
			} while (!message.equals("bye"));
			
			//测试读到数据超时
			/*try {
				Thread.sleep(8000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/
			
			//sendMessage("Connection successful");
			
		} catch (IOException ioException) {
			ioException.printStackTrace();
		} finally {
			// 4: Closing connection
			try {
				in.close();
				out.close();
				providerSocket.close();
			} catch (IOException ioException) {
				ioException.printStackTrace();
			}
		}
	}

	void sendMessage(String msg) {
		try {
			out.writeObject(msg);
			out.flush();
			System.out.println("server>" + msg);
		} catch (IOException ioException) {
			ioException.printStackTrace();
		}
	}

	public static void main(String args[]) {
		MyServerSocket server = new MyServerSocket();
		while (true) {
			server.run();
		}
	}

}
