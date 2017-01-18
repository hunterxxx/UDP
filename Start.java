import java.util.ArrayList;

/**
 * Startet den Clienten mit dem parameter 1,2,3
 */

public class Start{
    public static void main(String[] args){
        String sentence= new String();
        ArrayList<String> inhalt = new ArrayList<String>();
        // Entsprechenden Clienten erzeugen
         switch(args[0]){
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
                    //Gibt man was Falsches ein geht das Programm wieder aus
                    default:
                        System.exit(0);
                   
                }
        
        // Erzeugen der Musikdaten und starten der Verteilung
        Client client = new Client(sentence, inhalt);
        client.start();
    }
}
