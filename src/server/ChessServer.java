package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
public class ChessServer {
    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(5000)) {
            System.out.println("Chess Server started on port 5000...");
            DBManager.createMovesTable(); // Create tables

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Client connected: " + clientSocket.getInetAddress());
                new Thread(new ClientHandler(clientSocket)).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
