package client;

import java.util.*;
import java.io.*;
import java.net.*;

public class DateClient {
	public static void main(String[] args) {
		try {
			System.out.print("Enter date server IP address:");
			Scanner scanner = new Scanner(System.in);
			String serverIP = scanner.nextLine();
			Socket sock = new Socket(serverIP, 33333);
			DataInputStream inputStream = new DataInputStream(sock.getInputStream());
			DataOutputStream outputStream = new DataOutputStream(sock.getOutputStream());
			System.out.println(inputStream.readUTF());
			String command;
			boolean running = true;
			while (running) {
				System.out.println("Please enter a command: ");
				command = scanner.nextLine();
				if (command.equals("")) {
					System.out.println("Closing connection");
					sock.close();
					running = false;
				} else {
					outputStream.writeUTF(command);
					outputStream.flush();
					System.out.println(inputStream.readUTF());
				}
			}
			scanner.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}
