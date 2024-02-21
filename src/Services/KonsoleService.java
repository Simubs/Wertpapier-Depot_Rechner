package Services;

import Exceptions.ISINFormatException;
import Exceptions.ISINNichtGefundenException;
import Exceptions.WertpapierkaufEmptyException;
import Objekte.Wertpapier;
import Objekte.Wertpapierkauf;
import Tools.Tools;

import java.time.LocalDate;
import java.util.List;

public class KonsoleService {

    private DepotService depotService = new DepotService();


    public void starteKonsolenAbfrage(){
        System.out.println("Wilkommen zu dem Wertpapier-Depot-Rechner!");
        System.out.printf("Sie haben %d Wertpapiere im Depot\n",depotService.anzahlWertpapiere());
        System.out.println("Sie haben folgende Möglichkeiten:");
        System.out.println("\t[1] Neues Wertpapier hinzufügen.");
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
        if(depotService.anzahlWertpapiere() > 0 && eingabe <= 9){
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
                default:
                    return;
            }
        }else if(depotService.anzahlWertpapiere() == 0 && eingabe <= 2){
            if (eingabe == 1) {
                anlegenNeuesWertpapier();
            } else {
                return;
            }
        }

    }

    private void findAlleWertpapiere() {
        List<Wertpapier> alleWertpapiere =  depotService.alleWertpapierAuflisten();


        System.out.println("Die Wertpapiere in ihrem Depot:");

        for (int i = 0; i < alleWertpapiere.size(); i++) {
            System.out.printf("Das %d. Wertpapier:",i+1);
            System.out.println(alleWertpapiere.get(i));
        }

        starteKonsolenAbfrage();

    }

    private void findWorstPerformer() {
        System.out.println("Der Worst-Performer des depots ist der Kauf:");
        System.out.println(depotService.getWorstPerformer());

        starteKonsolenAbfrage();
    }

    private void findBestPerformer() {
        System.out.println("Der Worst-Performer des depots ist der Kauf:");
        System.out.println(depotService.getBestPerformer());

        starteKonsolenAbfrage();
    }

    private void RenditeDepotBerechnen() {
        System.out.println("Wollen Sie die Absolute oder Prozentuale Rendite?\n" +
                "[1] Absolute Rendite\n" +
                "[2] Prozentuale Rendite\n" +
                "Ander Zahl um zurück zu kommen");
        int eingabe = Tools.intEingabe();

        switch(eingabe){
            case 1:
                absoluteAusgabeGesamtrendite();
                break;
            case 2:
                prozentualeAusgabeGesamtrendite();
                break;
            default:
                cleanKonsole();
                starteKonsolenAbfrage();
        }

    }

    private void prozentualeAusgabeGesamtrendite() {

        System.out.printf("Die Prozentuale Rendite ist: %f Prozent \n",depotService.berechneGesammtrenditeDepotProzentual());
        starteKonsolenAbfrage();
    }

    private void absoluteAusgabeGesamtrendite() {
        System.out.printf("Die Absolute Rendite ist: %f€ \n",depotService.berechneGesammtrenditeDepotAbsolut());
        starteKonsolenAbfrage();
    }

    private void berechneGesamtwertDepot() {
        System.out.printf("Der Gesamtwert des Depots beläuft sich auf %f€\n",depotService.berechneGesammtwertDepot());
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

        switch(eingabe){
            case 1:
                absoluteAusgabePositionsrendite(ISIN);
                break;
            case 2:
                prozentualeAusgabePositionsrendite(ISIN);
                break;
            default:
                cleanKonsole();
                starteKonsolenAbfrage();
        }
    }

    private void prozentualeAusgabePositionsrendite(String ISIN)  {
        try {
            System.out.printf("Die Prozentuale Rendite ist: %f Prozent \n",depotService.berrechneRenditeProzentual(ISIN));
        } catch (ISINNichtGefundenException e) {
            System.out.println("Es wurde kein passendes Wertpapier zu dieser ISIN gefunden!");
            berechneRenditePosition();
        }
        starteKonsolenAbfrage();
    }

    private void absoluteAusgabePositionsrendite(String ISIN) {
        try {
            System.out.printf("Die Absolute Rendite ist: %f€ \n",depotService.berechneRenditeAbsoulut(ISIN));
        } catch (ISINNichtGefundenException e) {
            System.out.println("Es wurde kein passendes Wertpapier zu dieser ISIN gefunden!");
            berechneRenditePosition();
        }
        starteKonsolenAbfrage();
    }

    private void berechneGesamtwertPosition() {
        System.out.println("Für welche ISIN soll der Wert berechnet werden?");
        String ISIN = Tools.stringEingabe();

        try {
            System.out.printf("Der Wert der Position ist %f€\n",depotService.berrechneWertPosition(ISIN));
        } catch (ISINNichtGefundenException e) {
            System.out.println("Es wurde kein passendes Wertpapier zu dieser ISIN gefunden!");
            berechneGesamtwertPosition();
        }
        starteKonsolenAbfrage();
    }

    private void anlegenNeuesWertpapier() {

        Wertpapier neuesWertpapier = new Wertpapier();

        System.out.println("Zum neuen Anlegen geben sie bitte die ISIN an:");

        try {
            neuesWertpapier.setISIN(Tools.stringEingabe());
        } catch (ISINFormatException e) {
            System.out.println("Bitte geben sie eine Gültige ISIN ein!");
            anlegenNeuesWertpapier();
        }

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
        if(Tools.booleanEingabe()){
            try {
                depotService.addKauf(wertpapierkauf);
            } catch (WertpapierkaufEmptyException e) {
                System.out.println("Es gibt einen Fehler beim Hinzufügen des Kaufs versuchen sie es bitte später erneut!");
            }
        }
        cleanKonsole();
        starteKonsolenAbfrage();
    }

    private void cleanKonsole(){
        for(int i = 0; i <20; i++){
            System.out.println();
        }
    }

}
