package main.exceptions;

/**
 * Exception pour les erreurs si un générateur n'existe pas
 */
public class GenerateurInexistantException extends Exception {
    public GenerateurInexistantException(String message) {
        super(message);
    }
    
    public GenerateurInexistantException(String message, Throwable cause) {
        super(message, cause);
    }
} 