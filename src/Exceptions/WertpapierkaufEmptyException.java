package Exceptions;

public class WertpapierkaufEmptyException extends Exception{

    public WertpapierkaufEmptyException(){super("Fehler");}

    public WertpapierkaufEmptyException(String massage){super(massage);}

}
