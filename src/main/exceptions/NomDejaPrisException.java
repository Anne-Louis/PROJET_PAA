package main.exceptions;

/**
 * Exception pour les erreurs d'importation de fichiers  ^_^
 */
public class NomDejaPrisException extends Exception {
    public NomDejaPrisException(String message) {
        super(message);
    }
    
    public NomDejaPrisException(String message, Throwable cause) {
        super(message, cause);
    }
} 