package main.exceptions;

/**
 * Exception pour les erreurs si une connexion existe déjà
 */
public class ConnexionDejaExistanteException extends Exception {
    public ConnexionDejaExistanteException(String message) {
        super(message);
    }
    
    public ConnexionDejaExistanteException(String message, Throwable cause) {
        super(message, cause);
    }
} 