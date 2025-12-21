package main.exceptions;

/**
 * Exception pour les erreurs lorsqu'un réseau ne répond pas aux exigences du sujet
 * et n'est donc pas considéré comme valide
 */

public class InvalideReseauException extends Exception {
    
    public InvalideReseauException(String message){
        super(message);
    }

    public InvalideReseauException(String message, Throwable cause){
        super(message, cause);
    }
    
}
