package com.soclosetoheaven.client;


import java.io.IOException;

/**
 * Client main-class
 */
public class ClientApp {

    /**
     * private class-constructor
     */
    private ClientApp() {
        throw new UnsupportedOperationException("This is utility class!");
    }

    /**
     * main method, runs ConsoleClient
     * @param args command-string arguments
     */
    public static void main(String[] args) throws IOException {
        ClientInstance client = new ClientInstance();
        client.run();
    }
}
