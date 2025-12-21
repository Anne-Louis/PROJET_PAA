package main.exceptions;

/**
 * Exception pour les erreurs d'importation de fichiers 
 */
public class ImportException extends Exception {
    public ImportException(String message) {
        super(message);
    }
    
    public ImportException(String message, Throwable cause) {
        super(message, cause);
    }
} 