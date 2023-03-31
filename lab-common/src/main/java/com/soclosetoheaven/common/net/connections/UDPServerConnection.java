package com.soclosetoheaven.common.net.connections;

import com.soclosetoheaven.common.net.messaging.Request;
import com.soclosetoheaven.common.net.messaging.Response;
import org.apache.commons.lang3.SerializationUtils;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

import java.io.*;
import java.net.*;
import java.nio.ByteBuffer;
import java.util.*;

public class UDPServerConnection implements SimpleConnection<Pair<Request, InetSocketAddress>, Pair<Response, InetSocketAddress>> {


    private final DatagramSocket socket;

    //private final LinkedHashSet<InetSocketAddress> clients; // K - address, V - port

    private byte[] buffer;

    private boolean running;

    public UDPServerConnection(int port) throws SocketException {
        socket = new DatagramSocket(port);
        buffer = new byte[BUFFER_SIZE];
    }

    @Override
    public void start() {
        running = true;
    }

    @Override
    public Pair<Request, InetSocketAddress> waitAndGetData() throws IOException {
        ArrayList<byte[]> buffers = new ArrayList<>();
        InetSocketAddress address;
        do {
            clearBuffer();
            DatagramPacket packet = new DatagramPacket(buffer, MAX_PACKET_SIZE);
            socket.receive(packet);
            address = new InetSocketAddress(packet.getAddress(), packet.getPort());
            buffers.add(buffer);
        } while (buffer.length == MAX_PACKET_SIZE);
        byte[] data = new byte[buffers.size() * BUFFER_SIZE];
        int currentPosition = 0;
        for (byte[] i : buffers) {
            for (byte j : i) {
                data[currentPosition] = j;
                currentPosition++;
            }
        }
        return new ImmutablePair<>(SerializationUtils.deserialize(data), address);
    }

    @Override
    public void sendData(Pair<Response, InetSocketAddress> pair) throws IOException{ // сделать нормальную посылку данных
        Response response = pair.getLeft();
        InetSocketAddress client = pair.getRight();
        byte[] data = SerializationUtils.serialize(response);
        byte[][] packets = new byte[(int) Math.ceil(data.length / (double) MAX_PACKET_SIZE)][MAX_PACKET_SIZE];

        int currentPosition = 0;
        for (int i = 0; i < packets.length; ++i) {
            packets[i] = Arrays.copyOfRange(data, currentPosition, currentPosition + MAX_PACKET_SIZE);
            currentPosition += MAX_PACKET_SIZE;
        }

        for (byte[] pac : packets) {
            DatagramPacket packet = new DatagramPacket(
                    ByteBuffer.allocate(MAX_PACKET_SIZE).put(pac).array(),
                    MAX_PACKET_SIZE,
                    client
            );

            socket.send(packet);
        }

    }

    private void clearBuffer() {
        buffer = new byte[BUFFER_SIZE];
    }

}
