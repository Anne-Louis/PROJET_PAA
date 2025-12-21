package main.exceptions;

/**
 * Exception pour la gestion d'erreur si un mauvais nombre de termes est écrit pour la création
 * de générateurs, maisons, connexions
 * ainsi que la suppresion de connexions
 */
public class NombreDeTermesException extends Exception{
    public NombreDeTermesException(String message){
        super(message);
    }

    public NombreDeTermesException(String message, Throwable cause){
        super(message,cause);
    }
}
