package client;

import java.io.*;
import java.net.Socket;

public class ChessClient {
    public static void main(String[] args) {
        try (
                Socket socket = new Socket("localhost", 5000);
                BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))
        ) {
            System.out.println("Connected to Chess Server.");
            String move;

            while ((move = userInput.readLine()) != null) {
                out.println(move); // Send move to server
                System.out.println("Server: " + in.readLine()); // Show server response
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
