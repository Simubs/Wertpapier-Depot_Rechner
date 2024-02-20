package Exceptions;

public class ISINFormatException extends Exception{

    public ISINFormatException(){super("Das Format der ISIN stimmt nicht.");}

    public ISINFormatException(String massage){super(massage);}

}
