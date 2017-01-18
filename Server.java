
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.io.IOException;
import java.net.DatagramPacket;

public class Server {

    DatagramSocket receiver;
    //mit port 50001 anfangen
    private int port = 50001;

    public Server() {
        while (true) {
            //wenn port 50001 besetz ist, dann increment port bei 1
            try {
                DatagramSocket receiver = new DatagramSocket(port);
                this.receiver = receiver;
                break;
            } catch (IOException ex) {
                port = port + 1;
            }
        }
        // danach verwendet diese Port
        this.port = port;
    }

    // Paket Senden
    public void send(String content) {
        byte[] sendData = content.getBytes();
        // Paket wird zwischen 500010 und 50001 port schicken
        for (int i = 50001; i < 50010; i++) {
            try {
                //192.168.0.1 als Host Addresse
                InetAddress ipAddress = InetAddress.getByName("localhost");
                DatagramSocket sender = new DatagramSocket();
                DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, ipAddress, i);
                sender.send(sendPacket);
                //Seit java 7 ist close unbedingt nötig
                sender.close();
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }
    
    //Paket empfängen
    public String receive() {
        byte[] receiveData = new byte[1024];
        try {
            // 2 Sekunden warten, um Datagram Socket zu blockieren
            receiver.setSoTimeout((int) (2000));
            DatagramPacket empfangsPacket = new DatagramPacket(receiveData, receiveData.length);
            receiver.receive(empfangsPacket);
            String inhalt = new String(empfangsPacket.getData());
            return inhalt;
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return "0";
    }
}
