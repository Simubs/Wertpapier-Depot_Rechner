package Services;

import Tools.Tools;

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
            System.out.println("\t[9] Programm beenden.");
        } else {
            System.out.println("\t[2] Programm beenden.");
        }

        int eingabe = Tools.intEingabe();

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
                case 9:
                    return;
            }
        }else if(depotService.anzahlWertpapiere() == 0 && eingabe <= 2){
            switch (eingabe){
                case 1:
                    anlegenNeuesWertpapier();
                    break;
                case 2:
                    return;
            }
        }

    }

    private void findAlleWertpapiere() {
        System.out.println("alle");
    }

    private void findWorstPerformer() {
        System.out.println("wperf");
    }

    private void findBestPerformer() {
        System.out.println("asdda");
    }

    private void RenditeDepotBerechnen() {System.out.println("rendep");
    }

    private void berechneGesamtwertDepot() {
    }

    private void berechneRenditePosition() {
    }

    private void berechneGesamtwertPosition() {
    }

    private void anlegenNeuesWertpapier() {
    }

}
