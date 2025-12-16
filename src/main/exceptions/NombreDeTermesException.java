package main.exceptions;

public class NombreDeTermesException extends Exception{
    public NombreDeTermesException(String message){
        super(message);
    }

    public NombreDeTermesException(String message, Throwable cause){
        super(message,cause);
    }
}
