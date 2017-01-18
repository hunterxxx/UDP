import java.util.ArrayList;

public class Client{
    ArrayList<String> daten = new ArrayList<String>();
    Server server;

    public Client(String sentence, ArrayList<String> daten){
        Server server = new Server();
        this.server = server;
        this.daten = daten;
        System.out.println(sentence);

        String[] temp = new String[2];
        for(int i = 0; i < daten.size();i++){
            temp = daten.get(i).split("#");
            System.out.println("Interpret: " + temp[0] + "\t" + "Titel: " + temp[1]);
        }
    }

    public void start(){
        String receive = new String();
        String send = new String();
        String[] temp = new String[2];
        // Server empfängt und Sendet für immer
        while(true){
            // Alle Musikdaten einzeln senden
            for(int i = 0; i < daten.size(); i++){
                // Warten auf eingehendes Packet
                receive = server.receive();
                // Wenn keine Daten und kein Paket bekommen              
                    if(!receive.equals("0") && !daten.contains(receive)){
                        temp = receive.split("#");
                        System.out.println("Interpret: " + temp[0] + "\t" + "Titel: " + temp[1]);
                        daten.add(receive);
                    }
                // Erstes MusikdateiPacket absenden
                send = daten.get(i);
                server.send(send);
            }
        }
    }
}
