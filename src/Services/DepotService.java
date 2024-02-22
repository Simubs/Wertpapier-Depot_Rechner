package Services;

import Exceptions.ISINFormatException;
import Exceptions.ISINNichtGefundenException;
import Exceptions.WertpapierkaufEmptyException;
import Objekte.Wertpapier;
import Objekte.Wertpapierkauf;

import java.util.ArrayList;
import java.util.List;

public class DepotService {


    private List<Wertpapierkauf> depot;

    //Kostruktor
    public DepotService(List<Wertpapierkauf> depot) {
        this.depot = depot;
    }

    //Kostruktor
    public DepotService() {
        this.depot = new ArrayList<>();
    }

    //Mit dieser Funktion können Wertpapierkäufe hinzugefügt werden
    //Wirft eine Exception wenn das zu speichernde Objekt null ist
    public void addKauf(Wertpapierkauf wertpapierkauf) throws WertpapierkaufEmptyException {
        if(wertpapierkauf == null){
            throw new WertpapierkaufEmptyException();
        }
        depot.add(wertpapierkauf);
    }

    //Methode zum löschen eines Wertpapierkaufs anhand des Indexes (nicht verwendet)
    public boolean deleteIndex(int i){
        return depot.remove(i) != null;
    }

    //Methode zum löschen eines Wertpapierkaufs anhand der ISIN (nicht verwendet)
    public boolean deleteISIN(String ISIN){
        try {
            return depot.remove(new Wertpapierkauf(new Wertpapier(null,ISIN)));
        } catch (ISINFormatException e) {
            throw new RuntimeException(e);
        }
    }

    // Diese Methode berechnet den Gesamtwert des Depots und gibt diesen zurück
    public double berechneGesammtwertDepot(){

        double wert = 0;

        for(int i = 0;i<depot.size();i++){
            wert += depot.get(i).berechneAktuellenWert();
        }

        return wert;
    }

    ///Diese Methode berechnet die Gesamtrendite des Depots und gibt diese zurück
    public double berechneGesammtrenditeDepotAbsolut(){
        double rendite = 0;

        for(int i = 0;i<depot.size();i++){
            rendite += depot.get(i).berechneRendite();
        }

        return rendite;
    }

    //Diese Methode berechnet die Gesamtrendite des Depots Prozentual und gibt diese zurück
    public double berechneGesammtrenditeDepotProzentual(){
        double ausgaben = 0;
        double rendite = berechneGesammtrenditeDepotAbsolut();

        for(int i = 0;i<depot.size();i++){
            ausgaben += depot.get(i).getKursZumKaufdatum()+depot.get(i).getStueckzahl();
            ausgaben += depot.get(i).getKaufkosten();
        }

        return rendite*100/ausgaben;
    }

    //Diese Methode ermittelt den Best-Performer und gibt den zurück
    public Wertpapierkauf getBestPerformer(){
        if(depot == null || depot.isEmpty()){
            return null;
        }
        Wertpapierkauf bestPerformer = null;

        //jeder Kauf wird durchgegangen und mit dem vorherigen Best-Performer verglichen anhand der Rendite
        for(int i = 0; i<depot.size();i++){
            //Bei dem ersten Kauf wird dieser direkt als Best-Performer eingetragen
            if(bestPerformer==null){
                bestPerformer = depot.get(i);
            }
            Wertpapierkauf temp = depot.get(i);

            if(temp.berechneRendite()> bestPerformer.berechneRendite()){
                bestPerformer=temp;
            }
        }

        return bestPerformer;
    }

    //Diese Methode ermittelt denWorst-Performer und gibt diesen zurück
    public Wertpapierkauf getWorstPerformer(){
        if(depot == null || depot.isEmpty()){
            return null;
        }
        Wertpapierkauf worstPerformer = null;

        //jeder Kauf wird durchgegangen und mit dem vorherigen Worst-Performer verglichen anhand der Rendite
        for(int i = 0; i<depot.size();i++){
            //Bei dem ersten Kauf wird dieser direkt als Worst-Performer eingetragen
            if(worstPerformer==null){
                worstPerformer = depot.get(i);
            }
            Wertpapierkauf temp = depot.get(i);

            if(temp.berechneRendite()< worstPerformer.berechneRendite()){
                worstPerformer=temp;
            }
        }

        return worstPerformer;
    }

    //Diese Methode bestimmt die Absolute Rendite einer Position anhand der ISIN
    //Diese Methode schmeißt eine Exception, wenn kein Kauf mit der ISIN gefunden wurde (getKauf gibt null wieder, wenn keins gefunden wurde)
    public double berechneRenditeAbsolut(String ISIN) throws ISINNichtGefundenException{

        Wertpapierkauf gefundenerKauf = getKauf(ISIN);

        if(gefundenerKauf == null){
            throw new ISINNichtGefundenException();
        }
        return gefundenerKauf.berechneRendite();

    }

    //Diese Methode bestimmt den Wert einer Position anhand der ISIN
    //Diese Methode schmeißt eine Exception, wenn kein Kauf mit der ISIN gefunden wurde (getKauf gibt null wieder, wenn keins gefunden wurde)
    public double berrechneWertPosition(String ISIN)throws ISINNichtGefundenException{
        Wertpapierkauf gefundenerKauf = getKauf(ISIN);

        if(gefundenerKauf == null){
            throw new ISINNichtGefundenException();
        }
        return gefundenerKauf.berechneAktuellenWert();
    }

    // Diese Methode ist private und wird nur in dieser Klasse benutzt.
    //Die Methode sucht das Depot nach einem Kauf mit der mitgegebenen ISIN und gibt diesen zurück.
    //Die Methode gibt bei dem nicht finden null wieder
    private Wertpapierkauf getKauf(String ISIN) {
        if(ISIN != null){
            //alle Käufe werden durchgegangen
            for(int i = 0; i<depot.size();i++){

                if(depot.get(i).getWertpapier().getISIN().equals(ISIN)){
                    return depot.get(i);
                }
            }
        }
        return null;
    }

    //Diese Methode bestimmt die Prozentuale Rendite einer Position anhand der ISIN
    //Diese Methode schmeißt eine Exception, wenn kein Kauf mit der ISIN gefunden wurde (getKauf gibt null wieder, wenn keins gefunden wurde)
    public double berrechneRenditeProzentual(String ISIN) throws ISINNichtGefundenException{

        Wertpapierkauf gefundenerKauf = getKauf(ISIN);

        if(gefundenerKauf == null){
            throw  new ISINNichtGefundenException();
        }
        //Berechnet die Rendite direkt im Return
        return gefundenerKauf.berechneRendite()*100/(gefundenerKauf.getKaufkosten()+ gefundenerKauf.getKursZumKaufdatum()*gefundenerKauf.getStueckzahl());
    }

    //Gibt einen Integer zurück, welcher die größe des Depots darstellt
    public int anzahlWertpapiere(){
        if(depot != null){
            return depot.size();
        }
        return 0;
    }

    //Diese Methode gibt eine Liste aller Wertpapieren zurück
    public List<Wertpapier> alleWertpapierAuflisten(){

        if(depot == null && depot.size() == 0){
            return null;
        }

        List<Wertpapier> wertpapiere = new ArrayList<>();

        for(int i = 0; i<depot.size();i++){
            wertpapiere.add(depot.get(i).getWertpapier());
        }
        return wertpapiere;
    }
}
