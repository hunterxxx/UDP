import java.net.DatagramSocket;
import java.net.InetAddress;
import java.io.IOException;
import java.net.DatagramPacket;

public class Server {

    DatagramSocket receiver;
    private int port = 50001;

    public Server() {
        while (true) {
            try {
                DatagramSocket receiver = new DatagramSocket(port);
                this.receiver = receiver;
                break;
            } catch (IOException ex) {
                port = port + 1;
            }
        }
        this.port = port;
    }

    // Paket Senden
    public void send(String content) {
        content = content + "~";

        byte[] sendData = content.getBytes();
        // Das Packet an alle belegten Ports schicken
        for (int i = 50001; i < 50010; i++) {
            try {
                InetAddress ipAddress = InetAddress.getByName("localhost");
                DatagramSocket sender = new DatagramSocket();
                DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, ipAddress, i);
                sender.send(sendPacket);
                sender.close();
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    public String receive() {
        byte[] receiveData = new byte[1024];
        try {
            // 2 Sekunden warten, um Datagram Socket zu blockieren
            receiver.setSoTimeout((int) (2000));
            DatagramPacket empfangsPacket = new DatagramPacket(receiveData, receiveData.length);
            receiver.receive(empfangsPacket);
            String inhalt = new String(empfangsPacket.getData());
            inhalt = inhalt.substring(0, inhalt.indexOf("~"));
            return inhalt;
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return "0";
    }
}
