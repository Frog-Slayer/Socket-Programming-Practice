package udp;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPClient {
    public static String serverHost = "localhost";
    public static Integer serverPort = 12000;

    public String bytesToString(byte[] bytes) {

        int len = 0;
        for (byte b : bytes) {
            if (b == 0) break;
            len++;
        }
        return new String(bytes, 0, len);
    }

    public void run() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        try (DatagramSocket socket = new DatagramSocket()) {
            InetAddress address = InetAddress.getByName(serverHost);

            String input = br.readLine();
            DatagramPacket outPacket = new DatagramPacket(input.getBytes(), input.length(), address, serverPort);
            DatagramPacket inPacket = new DatagramPacket(new byte[1024], 1024);

            socket.send(outPacket);
            socket.receive(inPacket);

            System.out.println(bytesToString(inPacket.getData()));
        }
    }

    public static void main(String[] args) throws Exception {
        new UDPClient().run();
    }
}