package com.soclosetoheaven.server;


import com.soclosetoheaven.common.collectionmanagers.FileCollectionManager;
import com.soclosetoheaven.common.commandmanagers.ServerCommandManager;

import com.soclosetoheaven.common.net.connections.UDPServerConnection;
import com.soclosetoheaven.common.net.messaging.Request;
import com.soclosetoheaven.common.net.messaging.Response;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.util.logging.Level;


public class ServerInstance {

    private final UDPServerConnection connection;
    private ServerCommandManager commandManager;

    private FileCollectionManager fcm;

    private final String filePath;
    public ServerInstance(String filePath) throws IOException {
        connection = new UDPServerConnection(34684);
        this.filePath = filePath;
    }

    public void run() {
        try {
            fcm = new FileCollectionManager(filePath);
            fcm.open();
            commandManager = ServerCommandManager.defaultManager(fcm);
        } catch (FileNotFoundException | NullPointerException e) {
            ServerApp.LOGGER.severe("%s - server shutdown".formatted(e.getMessage()));
            System.exit(-1);
        }
        System.out.println(fcm.toString());
        while (true) {
            try {
                Pair<Request, InetSocketAddress> pair = connection.waitAndGetData();
                InetSocketAddress client = pair.getRight();
                Request request = pair.getLeft();
                ServerApp.LOGGER.log(Level.INFO,
                        "GOT REQUEST - %s - CLIENT - %s:%s".
                                formatted(
                                        request.getCommandName(),
                                        client.getHostName(),
                                        client.getPort()
                                )
                );
                Response response = commandManager.manage(request);
                connection.sendData(new ImmutablePair<>(response , client));
            } catch (IOException e) {
                ServerApp.LOGGER.severe("Exception: " + e.getMessage());
            }
        }

    }


}
