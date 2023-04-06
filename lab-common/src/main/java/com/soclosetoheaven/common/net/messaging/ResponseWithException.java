package com.soclosetoheaven.common.net.messaging;


import java.io.Serial;

public class ResponseWithException extends Response {

    @Serial
    private final static long serialVersionUID = 9513113;

    private final Exception e; // Exception implements serializable
    public ResponseWithException(String description, Exception e) {
        super(description);
        this.e = e;
    }

    public ResponseWithException(Exception e) {
        super("SERVER RESPONDED WITH EXCEPTION");
        this.e = e;
    }

    public Exception getException() {
        return this.e;
    }

    @Override
    public String toString() {
        return "%s - %s".formatted(description, e.getMessage());
    }
}
