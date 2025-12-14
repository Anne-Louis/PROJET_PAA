package main.exceptions;

public class ArgumentsException extends Exception {
    public ArgumentsException(String message){
        super(message);
    }

    public ArgumentsException(String message, Throwable cause){
        super(message,cause);
    }
}
