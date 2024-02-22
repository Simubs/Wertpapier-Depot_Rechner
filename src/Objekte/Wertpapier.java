package Objekte;

import Exceptions.ISINFormatException;

import java.util.Objects;

public class Wertpapier {

    private String name;

    private String ISIN;

    //Eine generierte und selbst angepasste toString Methode
    //Diese wird immer aufgerufen, wenn das Objekt direkt in ein print jeglicher art gepackt wird
    @Override
    public String toString() {
        return "ISIN:  " + ISIN +"\n" +
                "Name: " + name;
    }

    public Wertpapier(){

    }

    //Intelij Generated
    //wird immer aufgerufen, wenn .equals benutzt wird. Wird auch bei der remove/find Methode von Listen verwendet
    //War notwendig, da nur nach der ISIN verglichen werden soll
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Wertpapier that = (Wertpapier) o;
        return Objects.equals(ISIN, that.ISIN);
    }

    //Konstruktor mit beiden Attributen
    //Kann eine Exception schmeißen, wenn die ISIN nicht das richtige Format hat.
    //Die Exception wird durch die setMethode der ISIN geworfen
    public Wertpapier(String name, String ISIN) throws ISINFormatException {
        this.name = name;
        setISIN(ISIN);
    }

    public String getISIN() {
        return ISIN;
    }

    //die setMethode der ISIN
    public void setISIN(String ISIN) throws ISINFormatException {
        //Wenn die Methode ueberpruefeISIN eine Exception wirft, dann wird alles danach nicht abgearbeitet und die Exception wird weiter nach oben gegeben.
        ueberpruefeISIN(ISIN);
        this.ISIN = ISIN;
    }

    //Mithilfe von RegEx wird die ISIN überprüft
    //Wirft bei nichteinhalten eine Exception
    private void ueberpruefeISIN(String ISIN) throws ISINFormatException {

        //RegEx Anweisung: [A-Z](Nur Großbuchstaben){2}(Es dürfen nur 2 mal die Charaktere der Klammer vorkommen)[0-9](Nur Zahlen){10}(darf nur 10 Zahlen enthalten)
        if(ISIN.matches("[A-Z]{2}[0-9]{10}")){
            //Wenn alles eingehalten wurde wird einfach returnt, und das Programm läuft einfach weiter
           return;
        }
        // Wenn RegEx nicht eingehalten, wird die Exception geworfen und somit den Programmfluss unterbrochen
        throw new ISINFormatException();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
