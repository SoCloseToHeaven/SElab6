package com.soclosetoheaven.server;

import java.io.IOException;
import java.util.logging.Logger;

public final class ServerApp {


    public static final Logger LOGGER = Logger.getAnonymousLogger();
    private ServerApp() {
        throw new UnsupportedOperationException("This is an utility class and can not be instantiated");
    }

    public static void main(String[] args) throws IOException {
        ServerInstance server = new ServerInstance(System.getenv("selabjson"));
        server.run();
    }
}
