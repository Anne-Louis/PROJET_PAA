package main.exceptions;

/**
 * Exception pour les erreurs de nom déjà pris par une maison
 * lors de la création d'un générateur ou 
 * nom déjà pris par un générateur lors de la création d'une maison
 */
public class NomDejaPrisException extends Exception {
    public NomDejaPrisException(String message) {
        super(message);
    }
    
    public NomDejaPrisException(String message, Throwable cause) {
        super(message, cause);
    }
} 