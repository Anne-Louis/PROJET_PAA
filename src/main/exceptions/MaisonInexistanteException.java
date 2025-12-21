package main.exceptions;

/**
 * Exception pour les erreurs si une maison n'existe pas
 */
public class MaisonInexistanteException extends Exception {
    public MaisonInexistanteException(String message) {
        super(message);
    }
    
    public MaisonInexistanteException(String message, Throwable cause) {
        super(message, cause);
    }
} 