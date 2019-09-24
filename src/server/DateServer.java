package server;

import java.io.*;
import java.net.*;
import java.text.SimpleDateFormat;
import java.util.*;

public class DateServer {
	public static void main(String[] args) {
		try {
			ServerSocket servSock = new ServerSocket(33333);
			int clientNumber = 1;
			System.out.println("The server is running");
			while (true) {
				Socket socket = servSock.accept();
				String welcomeMessage = "Hello, you are client " + clientNumber++;
				ClientHandler handler = new ClientHandler(socket, welcomeMessage);
				handler.start();
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}
