package Exceptions;

public class ISINNichtGefundenException extends Exception {

    public ISINNichtGefundenException(){super("Die ISIN konnte nicht im Depot gefunden werden!");}

    public ISINNichtGefundenException(String massage){super(massage);}

}
