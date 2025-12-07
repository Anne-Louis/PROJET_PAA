package main.exceptions;

public class InvalideReseauException extends Exception {
    
    public InvalideReseauException(String message){
        super(message);
    }

    public InvalideReseauException(String message, Throwable cause){
        super(message, cause);
    }
    
}
