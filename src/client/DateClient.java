package client;

import java.util.*;
import java.io.*;
import java.net.*;

public class DateClient {
	
	private String serverIP;
	private Socket sock;
	private DataInputStream inputStream;
	private DataOutputStream outputStream;
	private Scanner scanner;
	
	public DateClient() {
		try {
			System.out.print("Enter date server IP address:");
			scanner = new Scanner(System.in);
			serverIP = scanner.nextLine();
			scanner.close();
			sock = new Socket(serverIP, 33333);
			inputStream = new DataInputStream(sock.getInputStream());
			outputStream = new DataOutputStream(sock.getOutputStream());
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	private void getDate() {
		try {
			System.out.println(inputStream.readUTF());
			outputStream.writeUTF("Received");
			outputStream.flush();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	public static void main(String[] args) {
		DateClient dateClient = new DateClient();
		dateClient.getDate();
	}
}
