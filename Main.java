import java.util.ArrayList;

//Main Methode
public class Main {

    public static void main(String[] args) {
        //enthält ein * Client besizt die Daten:
        String sentence = new String();
        //enthält Intepret und Titel
        ArrayList<String> inhalt = new ArrayList<String>();
        // Client erzeugen mit Parameter 1,2,3
        switch (args[0]) {
            case "1":
                sentence = "Ein Clients besitzt die Daten:";
                inhalt.add("Beatles#I Wanne Be Your Man");
                inhalt.add("Sportfreunde Stiller#Ein Kompliment");
                break;
            case "2":
                sentence = "Ein zweiter Client besitzt die Daten:";
                inhalt.add("Beatles#All My Loving");
                inhalt.add("Rolling Stones#Statisfaction");
                break;
            case "3":
                sentence = "Ein dritter Clients besitzt die Daten:";
                inhalt.add("Michael Jackson#Thriller");
                inhalt.add("Razorlight#Wire to Wire");
                break;
        }
        Client client = new Client(sentence, inhalt);
        //client starten, dann um receive und senden von Server aufrufen
        client.start();
    }
}
