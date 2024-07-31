package udp;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPServer {
    public static Integer serverPort = 12000;

    public String bytesToString(byte[] bytes) {
        int len = 0;
        for (byte b : bytes) {
            if (b == 0) break;
            len++;
        }
        return new String(bytes, 0, len);
    }

    public void run(){
        try (DatagramSocket socket = new DatagramSocket(serverPort)) {
            DatagramPacket outPacket;
            DatagramPacket inPacket;

            while (true){
                inPacket = new DatagramPacket(new byte[1024], 1024);

                socket.receive(inPacket);
                InetAddress address = inPacket.getAddress();
                int port = inPacket.getPort();

                String input = bytesToString(inPacket.getData());
                System.out.println("[Server] Received packet from " + address + ":" + port + ". data: " + input);

                String output = input.toUpperCase();
                outPacket = new DatagramPacket(output.getBytes(), output.length(), address, port);

                socket.send(outPacket);
            }
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args) throws Exception {
        new UDPServer().run();
    }
}
