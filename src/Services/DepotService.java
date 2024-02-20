package Services;

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
        return depot.remove(new Wertpapierkauf(new Wertpapier(null,ISIN)));
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

    public double berechneRenditeAbsoulut(String ISIN){
        return 0;
    }

}
