package main.exceptions;

/**
 * Exception pour les erreurs d'importation de fichiers  ^_^
 */
public class GenerateurInexistantException extends Exception {
    public GenerateurInexistantException(String message) {
        super(message);
    }
    
    public GenerateurInexistantException(String message, Throwable cause) {
        super(message, cause);
    }
} 