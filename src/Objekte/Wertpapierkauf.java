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

    //Generated
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Wertpapierkauf that = (Wertpapierkauf) o;
        return Objects.equals(wertpapier, that.wertpapier);
    }

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

    public double berechneRendite(){

        return aktuellerKurs*stueckzahl - kursZumKaufdatum*stueckzahl;

    }

    public double berechneAktuellenWert(){
        return aktuellerKurs*stueckzahl;
    }

    public void setWertpapier(Wertpapier wertpapier) {
        this.wertpapier = wertpapier;
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
