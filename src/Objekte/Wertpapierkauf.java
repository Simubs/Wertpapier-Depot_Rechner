package Objekte;

import java.time.LocalDate;

public class Wertpapierkauf {

    private Wertpapier wertpapier;

    private LocalDate kaufdatum;

    private double aktuellerKurs;

    private double kursZumKaufdatum;

    private double stueckzahl;

    private double kaufkosten;

    public Wertpapier getWertpapier() {
        return wertpapier;
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
