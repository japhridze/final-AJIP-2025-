package server;

import java.io.*;
import java.net.Socket;

public class ClientHandler implements Runnable {
    private final Socket socket;

    public ClientHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try (
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true)
        ) {
            int gameId = DBManager.createNewGame("Player1", "Player2");

            String move;
            int moveNumber = 1;
            while ((move = in.readLine()) != null) {
                System.out.println("Received move: " + move);
                DBManager.recordMove(gameId, moveNumber++, move);
                out.println("Move received: " + move);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
