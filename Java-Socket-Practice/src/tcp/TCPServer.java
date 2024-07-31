package tcp;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.*;

public class TCPServer {

    public void run() {
        try (ServerSocket serverSocket = new ServerSocket(12000) ){
            while (true) {
                Socket connectionSocket = serverSocket.accept();

                BufferedReader reader = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));																	// 데이터를 읽어옴

                String input = reader.readLine();

                SocketAddress address = connectionSocket.getRemoteSocketAddress();
                int port = connectionSocket.getPort();

                System.out.println("[Server] Received packet from " + address + ":" + port + ". data: " + input);

                PrintWriter writer = new PrintWriter(new OutputStreamWriter(connectionSocket.getOutputStream())); // 소켓으로 데이터를

                writer.println(input.toUpperCase());
                writer.flush();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args) {
        new TCPServer().run();
    }
}
