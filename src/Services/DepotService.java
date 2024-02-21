package Services;

import Exceptions.ISINFormatException;
import Exceptions.ISINNichtGefundenException;
import Exceptions.WertpapierkaufEmptyException;
import Objekte.Wertpapier;
import Objekte.Wertpapierkauf;

import java.lang.invoke.WrongMethodTypeException;
import java.util.ArrayList;
import java.util.List;

public class DepotService {


    private List<Wertpapierkauf> depot;

    public DepotService(List<Wertpapierkauf> depot) {
        this.depot = depot;
    }

    public DepotService() {
        this.depot = new ArrayList<>();
    }

    public void addKauf(Wertpapierkauf wertpapierkauf) throws WertpapierkaufEmptyException {
        if(wertpapierkauf == null){
            throw new WertpapierkaufEmptyException();
        }

        depot.add(wertpapierkauf);
    }

    public boolean deleteIndex(int i){
        return depot.remove(i) != null;
    }

    public boolean deleteISIN(String ISIN){
        try {
            return depot.remove(new Wertpapierkauf(new Wertpapier(null,ISIN)));
        } catch (ISINFormatException e) {
            throw new RuntimeException(e);
        }
    }

    public double berechneGesammtwertDepot(){

        double wert = 0;

        for(int i = 0;i<depot.size();i++){
            wert += depot.get(i).berechneAktuellenWert();
        }

        return wert;
    }

    public double berechneGesammtrenditeDepotAbsolut(){
        double rendite = 0;

        for(int i = 0;i<depot.size();i++){
            rendite += depot.get(i).berechneRendite();
        }

        return rendite;

    }

    public double berechneGesammtrenditeDepotProzentual(){
        double ausgaben = 0;
        double rendite = berechneGesammtrenditeDepotAbsolut();

        for(int i = 0;i<depot.size();i++){
            ausgaben += depot.get(i).getKursZumKaufdatum()+depot.get(i).getStueckzahl();
            ausgaben += depot.get(i).getKaufkosten();
        }

        return rendite*100/ausgaben;
    }

    public Wertpapierkauf getBestPerformer(){
        if(depot == null || depot.isEmpty()){
            return null;
        }
        Wertpapierkauf bestPerformer = null;

        for(int i = 0; i<depot.size();i++){
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

    public Wertpapierkauf getWorstPerformer(){
        if(depot == null || depot.isEmpty()){
            return null;
        }
        Wertpapierkauf worstPerformer = null;

        for(int i = 0; i<depot.size();i++){
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

    public double berechneRenditeAbsoulut(String ISIN) throws ISINNichtGefundenException{

        Wertpapierkauf gefundenerKauf = getKauf(ISIN);

        if(gefundenerKauf == null){
            throw new ISINNichtGefundenException();
        }
        return gefundenerKauf.berechneRendite();

    }

    public double berrechneWertPosition(String ISIN)throws ISINNichtGefundenException{
        Wertpapierkauf gefundenerKauf = getKauf(ISIN);

        if(gefundenerKauf == null){
            throw new ISINNichtGefundenException();
        }
        return gefundenerKauf.berechneAktuellenWert();
    }

    private Wertpapierkauf getKauf(String ISIN) {
        if(ISIN != null){
            for(int i = 0; i<depot.size();i++){

                if(depot.get(i).getWertpapier().getISIN().equals(ISIN)){
                    return depot.get(i);
                }
            }
        }
        return null;
    }

    public double berrechneRenditeProzentual(String ISIN) throws ISINNichtGefundenException{

        Wertpapierkauf gefundenerKauf = getKauf(ISIN);

        if(gefundenerKauf == null){
            throw  new ISINNichtGefundenException();
        }

        return gefundenerKauf.berechneRendite()*100/(gefundenerKauf.getKaufkosten()+ gefundenerKauf.getKursZumKaufdatum()*gefundenerKauf.getStueckzahl());
    }

    public int anzahlWertpapiere(){
        if(depot != null){
            return depot.size();
        }
        return 0;
    }

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
