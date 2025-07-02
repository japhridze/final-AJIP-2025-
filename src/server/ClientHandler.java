package server;

import model.Board;
import model.PGNValidator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientHandler implements Runnable {
    private Socket socket;

    public ClientHandler(Socket socket) {
        this.socket = socket;
    }

    public void run() {
        try (
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true)
        ) {
            Board board = new Board(); // initialize board

            String move;
            while ((move = in.readLine()) != null) {
                if (move.equalsIgnoreCase("EXIT")) break;

                boolean valid = PGNValidator.validateMove(move, board);
                DBManager.saveMove(move, valid);
                out.println(valid ? "INVALID" : "VALID");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
