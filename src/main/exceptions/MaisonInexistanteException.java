package main.exceptions;

/**
 * Exception pour les erreurs d'importation de fichiers  ^_^
 */
public class MaisonInexistanteException extends Exception {
    public MaisonInexistanteException(String message) {
        super(message);
    }
    
    public MaisonInexistanteException(String message, Throwable cause) {
        super(message, cause);
    }
} 