package Services;

import Exceptions.ISINFormatException;
import Exceptions.ISINNichtGefundenException;
import Exceptions.WertpapierkaufEmptyException;
import Objekte.Wertpapier;
import Objekte.Wertpapierkauf;
import Tools.Tools;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;

public class KonsoleService {

    private DepotService depotService = new DepotService();

    /*
    Diese Methode startet die Konsolen abfrage. Diese Methode wird auch benutzt, um
    von den einzelnen anderen Methoden wieder auf die Auswahlmaske zurückzukommen.
    Nur diese ist Public, da andere nicht von außerhalb der Klasse gestartet werden sollen.
     */
    public void starteKonsolenAbfrage(){
        System.out.println("Wilkommen zu dem Wertpapier-Depot-Rechner!");
        System.out.printf("Sie haben %d Wertpapiere im Depot\n",depotService.anzahlWertpapiere());
        System.out.println("Sie haben folgende Möglichkeiten:");
        System.out.println("\t[1] Neues Wertpapier hinzufügen.");
        //Die Restlichen befehle werden nur angezeigt, wenn auch Objekte im Depot sind
        if(depotService.anzahlWertpapiere() > 0){
            System.out.println("\t[2] Den Gesamtwert einer Position berechnen.");
            System.out.println("\t[3] Die Rendite einer Position berechnen.");
            System.out.println("\t[4] Den Gesamtwert des Depots berechnen.");
            System.out.println("\t[5] Die Rendite des Depots berechnen.");
            System.out.println("\t[6] Den Best-Performer herrausfinden.");
            System.out.println("\t[7] Den Worst-Performer herrausfinden.");
            System.out.println("\t[8] Alle Positionen auflisten.");
        }

        System.out.println("\tZum beenden benutzen Sie irgendeine andere Zahl");

        int eingabe = Tools.intEingabe();
        cleanKonsole();
        //Dieser Teil bestimmt das weitere Vorgehen
        if(depotService.anzahlWertpapiere() > 0){
            //Das sind alle Auswahlmöglichkeiten, die es gibt, wenn das Depot gefüllt ist
            //mit dem switch-case wird die Integer Eingabe abgefragt
            switch (eingabe){
                case 1:
                    anlegenNeuesWertpapier();
                    break;
                case 2:
                    berechneGesamtwertPosition();
                    break;
                case 3:
                    berechneRenditePosition();
                    break;
                case 4:
                    berechneGesamtwertDepot();
                    break;
                case 5:
                    RenditeDepotBerechnen();
                    break;
                case 6:
                    findBestPerformer();
                    break;
                case 7:
                    findWorstPerformer();
                    break;
                case 8:
                    findAlleWertpapiere();
                    break;
                default: // Der Default wird nur ausgeführt, wenn keine der oberen Cases zutrifft (wird benutzt, um das Programm zu beenden)
                    return;
            }
        }else if(depotService.anzahlWertpapiere() == 0){
            //Abfrage, wenn keine Objekte im Depot sind
            if (eingabe == 1) {
                anlegenNeuesWertpapier();
            } else {
                return;
            }
        }

    }

    //Gibt alle Wertpapiere aus, die im Depot sind
    private void findAlleWertpapiere() {
        //aufruf im depotService gibt eine Liste der Wertpapiere wieder
        List<Wertpapier> alleWertpapiere =  depotService.alleWertpapierAuflisten();


        System.out.println("Die Wertpapiere in ihrem Depot:");

        for (int i = 0; i < alleWertpapiere.size(); i++) {
            System.out.printf("Das %d. Wertpapier:",i+1);
            //Durch das einfache mitgeben des Objektes an das print wird die toString() Methode des Objekts ausgeführt.
            //Diese wurde in dem Objekt überschrieben (kann mit Intellij einfach generiert werden.
            System.out.println(alleWertpapiere.get(i));
        }

        //Wieder zurück zur Auswahlmaske
        starteKonsolenAbfrage();

    }

    private void findWorstPerformer() {
        System.out.println("Der Worst-Performer des depots ist der Kauf:");

        //Durch das einfache Mitgeben des Objektes an das print wird die toString() Methode des Objekts ausgeführt.
        //Diese wurde in dem Objekt überschrieben (kann mit Intellij einfach generiert werden.
        System.out.println(depotService.getWorstPerformer());

        //Wieder zurück zur Auswahlmaske
        starteKonsolenAbfrage();
    }

    private void findBestPerformer() {
        System.out.println("Der Worst-Performer des depots ist der Kauf:");

        //Durch das einfache Mitgeben des Objektes an das print wird die toString() Methode des Objekts ausgeführt.
        //Diese wurde in dem Objekt überschrieben (kann mit Intellij einfach generiert werden.
        System.out.println(depotService.getBestPerformer());

        //Wieder zurück zur Auswahlmaske
        starteKonsolenAbfrage();
    }

    private void RenditeDepotBerechnen() {
        System.out.println("Wollen Sie die Absolute oder Prozentuale Rendite?\n" +
                "[1] Absolute Rendite\n" +
                "[2] Prozentuale Rendite\n" +
                "Ander Zahl um zurück zu kommen");
        int eingabe = Tools.intEingabe();
        //Auswahl der verschiedenen optionen, der Ausgabe der Rendite
        switch(eingabe){
            case 1:
                absoluteAusgabeGesamtrendite();
                break;
            case 2:
                prozentualeAusgabeGesamtrendite();
                break;
            default:
                cleanKonsole();
                //Wieder zurück zur Auswahlmaske
                starteKonsolenAbfrage();
        }

    }

    private void prozentualeAusgabeGesamtrendite() {

        System.out.printf("Die Prozentuale Rendite ist: %f Prozent \n",depotService.berechneGesammtrenditeDepotProzentual());

        //Wieder zurück zur Auswahlmaske
        starteKonsolenAbfrage();
    }

    private void absoluteAusgabeGesamtrendite() {
        System.out.printf("Die Absolute Rendite ist: %f€ \n",depotService.berechneGesammtrenditeDepotAbsolut());

        //Wieder zurück zur Auswahlmaske
        starteKonsolenAbfrage();
    }

    private void berechneGesamtwertDepot() {
        System.out.printf("Der Gesamtwert des Depots beläuft sich auf %f€\n",depotService.berechneGesammtwertDepot());

        //Wieder zurück zur Auswahlmaske
        starteKonsolenAbfrage();
    }

    private void berechneRenditePosition() {

        System.out.println("Für welche ISIN soll die Rendite berechnet werden?");
        String ISIN = Tools.stringEingabe();

        System.out.println("Wollen Sie die Absolute oder Prozentuale Rendite?\n" +
                "[1] Absolute Rendite\n" +
                "[2] Prozentuale Rendite\n" +
                "Ander Zahl um zurück zu kommen");
        int eingabe = Tools.intEingabe();

        // Auswahl der verschiedenen Optionen für die Ausgabe der Rendite
        switch(eingabe){
            case 1:
                absoluteAusgabePositionsrendite(ISIN);
                break;
            case 2:
                prozentualeAusgabePositionsrendite(ISIN);
                break;
            default:
                cleanKonsole();
                //Wieder zurück zur Auswahlmaske
                starteKonsolenAbfrage();
        }
    }

    private void prozentualeAusgabePositionsrendite(String ISIN)  {
        try {
            System.out.printf("Die Prozentuale Rendite ist: %f Prozent \n",depotService.berrechneRenditeProzentual(ISIN));

            //Wieder zurück zur Auswahlmaske, wenn alles durchläuft
            starteKonsolenAbfrage();

        // Die Methode berrechneRenditeProzentual kann eine Exception schmeißen, wenn die ISIN nicht in dem Depot gefunden wurde.
        // Diese Exception wird hier abgefangen
        } catch (ISINNichtGefundenException e) {
            System.out.println("Es wurde kein passendes Wertpapier zu dieser ISIN gefunden!");
            //hier wird zurückgegangen zur Eingabe der ISIN
            berechneRenditePosition();
        }

    }

    private void absoluteAusgabePositionsrendite(String ISIN) {
        try {
            System.out.printf("Die Absolute Rendite ist: %f€ \n",depotService.berechneRenditeAbsolut(ISIN));

            //Wieder zurück zur Auswahlmaske, wenn alles durchläuft
            starteKonsolenAbfrage();

            // Die Methode berrechneRenditeAbsolut kann eine Exception schmeißen, wenn die ISIN nicht in dem Depot gefunden wurde.
            // Diese Exception wird hier abgefangen
        } catch (ISINNichtGefundenException e) {
            System.out.println("Es wurde kein passendes Wertpapier zu dieser ISIN gefunden!");
            //hier wird zurückgegangen zur Eingabe der ISIN
            berechneRenditePosition();
        }

    }

    //Behandelt den Fall, wenn der Gesammtwert einer Position ausgegeben werden soll
    private void berechneGesamtwertPosition() {
        System.out.println("Für welche ISIN soll der Wert berechnet werden?");
        String ISIN = Tools.stringEingabe();

        try {
            System.out.printf("Der Wert der Position ist %f€\n",depotService.berrechneWertPosition(ISIN));

            //Wieder zurück zur Auswahlmaske, wenn alles durchläuft
            starteKonsolenAbfrage();

            // Die Methode berrechneWertPosition kann eine Exception schmeißen, wenn die ISIN nicht in dem Depot gefunden wurde.
            // Diese Exception wird hier abgefangen
        } catch (ISINNichtGefundenException e) {
            System.out.println("Es wurde kein passendes Wertpapier zu dieser ISIN gefunden!");
            //hier wird zurückgegangen zur Eingabe der ISIN
            berechneGesamtwertPosition();
        }

    }

    // Wird aufgerufen, wenn ein neues Wertpapier hinzugefügt werden soll.
    private void anlegenNeuesWertpapier() {

        Wertpapier neuesWertpapier = new Wertpapier();

        System.out.println("Zum neuen Anlegen geben sie bitte die ISIN an:");

        try {
            neuesWertpapier.setISIN(Tools.stringEingabe());

            System.out.println("Bitte geben sie den Names des Wertpapiers ein:");
            neuesWertpapier.setName(Tools.stringEingabe());

            Wertpapierkauf wertpapierkauf = new Wertpapierkauf(neuesWertpapier);

            System.out.println("Bitte geben sie das Kaufdatum an: (Das Format YYYY-MM-DD)");
            wertpapierkauf.setKaufdatum(LocalDate.parse(Tools.stringEingabe()));

            System.out.println("Bitte geben sie die Stückzahl ein:");
            wertpapierkauf.setStueckzahl(Tools.doubleEingabe());

            System.out.println("Bitte geben sie den Kurs zum Kaufdatum ein:");
            wertpapierkauf.setKursZumKaufdatum(Tools.doubleEingabe());

            System.out.println("Bitte geben sie die Kaufkosten an, die beim Kauf angefallen sind:");
            wertpapierkauf.setKaufkosten(Tools.doubleEingabe());

            System.out.println("Bitte geben sie den aktuellen Kurs ein:");
            wertpapierkauf.setAktuellerKurs(Tools.doubleEingabe());

            System.out.println("Ihre Eingaben:");
            System.out.println(wertpapierkauf);

            System.out.println();
            System.out.println("Wollen sie diesen hinzufügen?");
            // Wenn der Anwender false angibt, wird der Wertpapierkauf nicht gespeichert
            if(Tools.booleanEingabe()){
                depotService.addKauf(wertpapierkauf);
            }
            cleanKonsole();
            starteKonsolenAbfrage();

            //Die Methode setISIN kann di Exception ISINFormatException werfen, wenn das Format der ISIN nicht Stimmt.
            //Diese Exception wird hier abgefangen
        } catch (ISINFormatException e) {
            System.out.println("Bitte geben sie eine Gültige ISIN ein!");
            anlegenNeuesWertpapier();
            // Die Methode addKauf kann die Exception WertpapierkaufEmptyException werfen, wenn aus irgendeinen Grund
            // das Objekt Wertpapierkauf null ist. Diese Exception wird hier abgefangen.
        } catch (WertpapierkaufEmptyException e) {
            System.out.println("Es gibt einen Fehler beim Hinzufügen des Kaufs versuchen sie es bitte später erneut!");
            cleanKonsole();
            starteKonsolenAbfrage();

            //Die Methode LocalDate.parse kann eine Exception werfen, wenn das Datum in einem falschen Format
            //eingegeben wurde.
        } catch (DateTimeParseException e){
            System.out.println("Bitte ein gültiges Datum im richtigen Format eingeben!");
            System.out.println("Bitte geben sie eine Gültige ISIN ein!");
            anlegenNeuesWertpapier();
        }


    }

    //Printet viele Zeilen und "Cleant" somit die Konsole
    private void cleanKonsole(){
        for(int i = 0; i <20; i++){
            System.out.println();
        }
    }

}
