package main.exceptions;

/**
 * Exception pour les erreurs d'arguments rentrés dans le terminal via une ligne de commande
 */

public class ArgumentsException extends Exception {
    public ArgumentsException(String message){
        super(message);
    }

    public ArgumentsException(String message, Throwable cause){
        super(message,cause);
    }
}
