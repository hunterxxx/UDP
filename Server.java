
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.UnknownHostException;
import java.net.SocketTimeoutException;
import java.util.Random;
import java.util.ArrayList;

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

    // Packet Senden
    public void senden(String content) {
        /*
         * Da das packet größer ist als der versendete String,
         * ist das ~ das Letzte Symbol des Strings um später 
         * den versendeten String aus dem Packet rauszuschchneiden
         */
        content = content + "~";

        byte[] sendData = content.getBytes();
        // Das Packet an alle belegten Ports schicken
        for (int i = 50001; i < 50010; i++) {
            try {
                InetAddress ipAddress = InetAddress.getByName("localhost");
                // Einen neuen Socket zum Senden aufmachen
                // Create a datagram socket, send the packet to the Server.
                DatagramSocket sender = new DatagramSocket();
                // Das packet mit Inhalt, Größe, Addresse, und Portnummer versehen
                DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, ipAddress, i);
                // Und abschicken
                sender.send(sendPacket);
                // Socket wieder schließen
                sender.close();
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    // Packet empfangen
    public String receive() {
        Random zufall = new Random();
        byte[] receiveData = new byte[1024];
        try {
            receiver.setSoTimeout((int) (zufall.nextDouble() * 2000));
            DatagramPacket empfangsPacket = new DatagramPacket(receiveData, receiveData.length);
            // Packet empfangen
            receiver.receive(empfangsPacket);
            // Inhalt des Packetes lesen
            String inhalt = new String(empfangsPacket.getData());
            // Inhalt noch zuschneiden und zurückgeben
            inhalt = inhalt.substring(0, inhalt.indexOf("~"));
            return inhalt;
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return "0";
    }
}
