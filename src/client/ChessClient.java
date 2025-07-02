package client;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class ChessClient {
    public static void main(String[] args) throws Exception {
        Socket socket = new Socket("localhost", 5000);
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter moves ");

        while (true) {
            System.out.print("Move: ");
            String move = scanner.nextLine();
            out.println(move);

            if (move.equalsIgnoreCase("EXIT")) break;

            String response = in.readLine();
            System.out.println("Server: " + response);
        }

        socket.close();
    }
}
