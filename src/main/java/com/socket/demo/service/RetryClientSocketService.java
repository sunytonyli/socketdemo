package com.socket.demo.service;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@EnableRetry
@Service
public class RetryClientSocketService {

	private static final Logger logger = LoggerFactory.getLogger(RetryClientSocketService.class);
	
	Socket requestSocket;
	ObjectOutputStream out;
	ObjectInputStream in;
	String message;

	@Retryable(value= {Exception.class}, maxAttempts = 2, backoff = @Backoff(delay = 5000l, multiplier = 1))
	public void requestSoc() {
		
		try {
			
			// 1. creating a socket to connect to the server
			//requestSocket = new Socket("localhost", 2004);
			requestSocket = new Socket();
			// 设置connect timeout 为2000毫秒 java.net.SocketTimeoutException: connect timed out
			requestSocket.connect(new InetSocketAddress("localhost1", 2004), 2000);
			
			// 设置so timeout 为2000毫秒 java.net.SocketTimeoutException: Read timed out
			requestSocket.setSoTimeout(2000);
			
			System.out.println("Connected to localhost in port 2004");
			// 2. get Input and Output streams
			out = new ObjectOutputStream(requestSocket.getOutputStream());
			out.flush();
			in = new ObjectInputStream(requestSocket.getInputStream());
			// 3: Communicating with the server
			do {
				try {
					message = (String) in.readObject();
					System.out.println("server>" + message);
					sendMessage("Hi my server");
					message = "bye";
					sendMessage(message);
				} catch (ClassNotFoundException classNot) {
					System.err.println("data received in unknown format");
				}
			} while (!message.equals("bye"));
		}catch (UnknownHostException unknownHost) {
			System.err.println("You are trying to connect to an unknown host!");
			System.err.println(unknownHost);
		}catch(SocketTimeoutException ste) {
			System.err.println(ste);
		} catch (IOException ioException) {
			ioException.printStackTrace();
		} finally {
			// 4: Closing connection
			try {
					in.close();
					out.close();
					requestSocket.close();
			} catch (IOException ioException) {
				ioException.printStackTrace();
			}
		}
	}

	@Retryable(value = {UnknownHostException.class, SocketTimeoutException.class}, maxAttempts = 3, backoff = @Backoff(delay = 5000l, multiplier = 1))
	public void requestSocTwo() throws UnknownHostException, SocketTimeoutException {
		
		try {
			
			// 1. creating a socket to connect to the server
			//requestSocket = new Socket("localhost", 2004);
			requestSocket = new Socket();
			// 设置connect timeout 为2000毫秒 java.net.SocketTimeoutException: connect timed out
			requestSocket.connect(new InetSocketAddress("localhost1", 2004), 2000);
			
			// 设置so timeout 为2000毫秒 java.net.SocketTimeoutException: Read timed out
			requestSocket.setSoTimeout(2000);
			
			System.out.println("Connected to localhost in port 2004");
			// 2. get Input and Output streams
			out = new ObjectOutputStream(requestSocket.getOutputStream());
			out.flush();
			in = new ObjectInputStream(requestSocket.getInputStream());
			// 3: Communicating with the server
			do {
				try {
					message = (String) in.readObject();
					System.out.println("server>" + message);
					sendMessage("Hi my server");
					message = "bye";
					sendMessage(message);
				} catch (ClassNotFoundException classNot) {
					System.err.println("data received in unknown format");
				}
			} while (!message.equals("bye"));
		}catch (UnknownHostException unknownHost) {
			System.err.println("You are trying to connect to an unknown host!");
			throw new UnknownHostException();
		}catch(SocketTimeoutException ste) {
			System.err.println(ste);
			throw new SocketTimeoutException();
		} catch (IOException ioException) {
			ioException.printStackTrace();
		}
	}
	
	public void sendMessage(String msg) {
		try {
			out.writeObject(msg);
			out.flush();
			System.out.println("client>" + msg);
		} catch (IOException ioException) {
			ioException.printStackTrace();
		}
	}
	
	@Recover
	public void recover(UnknownHostException ex) {
		logger.error("失败:You are trying to connect to an unknown host!");
	}
	
	@Recover
	public void recover(SocketTimeoutException ex) {
		logger.error("失败:", ex);
	}
	
	@Recover
	public void recover(NullPointerException ex) {
		logger.error("失败:NullPointerException");
	}
}
