package com.example.somecode.io;

public class CustomFileException extends RuntimeException {

    public CustomFileException(){
        super();
    }

    public CustomFileException(String message){
        super(message);
    }

    public CustomFileException(Throwable cause){
        super(cause);
    }

    public CustomFileException(String message, Throwable cause) {
        super(message, cause);
    }
}
