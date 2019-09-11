package server;

import java.io.*;
import java.net.*;
import java.text.SimpleDateFormat;
import java.util.*;

public class DateServer {
	
	private ServerSocket servSock;
	private Socket sock;
	private DataInputStream inputStream;
	private DataOutputStream outputStream;
	
	public DateServer() {
		try {
			servSock = new ServerSocket(33333);
			System.out.println("The server is running");
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	public void connect() {
		try {
			sock = servSock.accept();
			inputStream = new DataInputStream(sock.getInputStream());
			outputStream = new DataOutputStream(sock.getOutputStream());
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	private void provideDate() {
		try {
			SimpleDateFormat dateFormatter = new SimpleDateFormat("E MMM dd HH:mm:ss z yyyy");
			outputStream.writeUTF(dateFormatter.format(Calendar.getInstance().getTime()));
			outputStream.flush();
			System.out.println(inputStream.readUTF());
		} catch (Exception e) {
			System.out.print(e);
		}
	}
	
	public static void main(String[] args) {
			DateServer dateServ = new DateServer();
			while (true) {
				dateServ.connect();
				dateServ.provideDate();
			}
	}
}
