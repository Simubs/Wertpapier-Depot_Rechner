package Objekte;

import Exceptions.ISINFormatException;

import java.util.Objects;

public class Wertpapier {

    private String name;

    private String ISIN;

    @Override
    public String toString() {
        return "ISIN:  " + ISIN +"\n" +
                "Name: " + name;
    }

    public Wertpapier(){

    }

    //Intelij Generated
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Wertpapier that = (Wertpapier) o;
        return Objects.equals(ISIN, that.ISIN);
    }


    public Wertpapier(String name, String ISIN) throws ISINFormatException {
        this.name = name;
        setISIN(ISIN);
    }

    public String getISIN() {
        return ISIN;
    }

    public void setISIN(String ISIN) throws ISINFormatException {
        //Wenn die Methode ueberpruefeISIN eine Exception wirft, dann wird alles danach nicht abgearbeitet und die Exception wird weiter nach oben gegeben.
        ueberpruefeISIN(ISIN);
        this.ISIN = ISIN;
    }

    private void ueberpruefeISIN(String ISIN) throws ISINFormatException {

        if(ISIN.matches("[A-Z]{2}[0-9]{10}")){
           return;
        }

        throw new ISINFormatException();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
