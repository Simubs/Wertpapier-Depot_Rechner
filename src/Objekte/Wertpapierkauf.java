package Objekte;

import java.time.LocalDate;
import java.util.Objects;

public class Wertpapierkauf {

    private Wertpapier wertpapier;

    private LocalDate kaufdatum;

    private double aktuellerKurs;

    private double kursZumKaufdatum;

    private double stueckzahl;

    private double kaufkosten;

    public Wertpapierkauf(Wertpapier wertpapier) {
        this.wertpapier = wertpapier;
    }

    public Wertpapierkauf() {

    }

    //Intelij Generated
    //wird immer aufgerufen, wenn .equals benutzt wird. Wird auch bei der remove/find Methode von Listen verwendet
    //War notwendig, da nur nach den Wertpapier verglichen werden soll
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Wertpapierkauf that = (Wertpapierkauf) o;
        return Objects.equals(wertpapier, that.wertpapier);
    }

    //Konstruktor mit allen Attributen
    public Wertpapierkauf(Wertpapier wertpapier, LocalDate kaufdatum, double aktuellerKurs, double kursZumKaufdatum, double stueckzahl, double kaufkosten) {
        this.wertpapier = wertpapier;
        this.kaufdatum = kaufdatum;
        this.aktuellerKurs = aktuellerKurs;
        this.kursZumKaufdatum = kursZumKaufdatum;
        this.stueckzahl = stueckzahl;
        this.kaufkosten = kaufkosten;
    }

    public Wertpapier getWertpapier() {
        return wertpapier;
    }

    //berechnet die Rendite des Wertpapierkaufs
    public double berechneRendite(){

        return aktuellerKurs*stueckzahl - kursZumKaufdatum*stueckzahl-kaufkosten;

    }

    //berechnet den aktuellen Wert des Wertpapierkaufs
    public double berechneAktuellenWert(){
        return aktuellerKurs*stueckzahl;
    }

    public void setWertpapier(Wertpapier wertpapier) {
        this.wertpapier = wertpapier;
    }

    //Eine generierte und selbst angepasste toString Methode
    //Diese wird immer aufgerufen, wenn das Objekt direkt in ein print jeglicher art gepackt wird
    @Override
    public String toString() {
        return "Die Position: \n" +
                "\tISIN: " +wertpapier.getISIN()+"\n" +
                "\tName: " +wertpapier.getName()+"\n" +
                "\tKaufdatum: " + kaufdatum +"\n" +
                "\tStückzahl: "+stueckzahl+"\n" +
                "\tKurs zum Kaufdatum: "+kursZumKaufdatum+"\n" +
                "\tKaufkosten: "+kaufkosten+"\n" +
                "\tAktueller Kurs: "+aktuellerKurs;
    }

    public LocalDate getKaufdatum() {
        return kaufdatum;
    }

    public void setKaufdatum(LocalDate kaufdatum) {
        this.kaufdatum = kaufdatum;
    }

    public double getAktuellerKurs() {
        return aktuellerKurs;
    }

    public void setAktuellerKurs(double aktuellerKurs) {
        this.aktuellerKurs = aktuellerKurs;
    }

    public double getKursZumKaufdatum() {
        return kursZumKaufdatum;
    }

    public void setKursZumKaufdatum(double kursZumKaufdatum) {
        this.kursZumKaufdatum = kursZumKaufdatum;
    }

    public double getStueckzahl() {
        return stueckzahl;
    }

    public void setStueckzahl(double stueckzahl) {
        this.stueckzahl = stueckzahl;
    }

    public double getKaufkosten() {
        return kaufkosten;
    }

    public void setKaufkosten(double kaufkosten) {
        this.kaufkosten = kaufkosten;
    }
}
