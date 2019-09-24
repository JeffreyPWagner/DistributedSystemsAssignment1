package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Calendar;


public class ClientHandler extends Thread {

    private DataInputStream inputStream;
    private DataOutputStream outputStream;

    public ClientHandler(Socket socket, String welcomeMessage) {
        try {
            inputStream = new DataInputStream(socket.getInputStream());
            outputStream = new DataOutputStream(socket.getOutputStream());
            outputStream.writeUTF(welcomeMessage);
            outputStream.flush();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void run() {
        try {
            boolean running = true;
            String command;
            while (running) {
                command = inputStream.readUTF();
                if (command.equals("time")) {
                    SimpleDateFormat dateFormatter = new SimpleDateFormat("E MMM dd HH:mm:ss z yyyy");
                    outputStream.writeUTF(dateFormatter.format(Calendar.getInstance().getTime()));
                    outputStream.flush();
                } else if (command.equals("")) {
                    System.out.println("Closing connection and thread");
                    running = false;
                } else {
                    outputStream.writeUTF(command.toUpperCase());
                    outputStream.flush();
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
