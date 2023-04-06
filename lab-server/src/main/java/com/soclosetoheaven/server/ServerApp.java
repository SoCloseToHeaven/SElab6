package com.soclosetoheaven.server;

import com.soclosetoheaven.common.io.BasicIO;

import java.io.IOException;
import java.util.logging.Logger;

public final class ServerApp {


    public static final Logger LOGGER = Logger.getAnonymousLogger();

    private static boolean running;

    //private static final String INPUT_PREFIX = "> ";

    private static final BasicIO io = new BasicIO();
    private ServerApp() {
        throw new UnsupportedOperationException("This is an utility class and can not be instantiated");
    }

    public static void main(String[] args) throws IOException{
        ServerInstance server = new ServerInstance(System.getenv("selabjson"), io);
        running = true; // пока что тоже костыль, будет исправлено
        server.run();
    }

    public static boolean getState() {
        return running;
    }
}
