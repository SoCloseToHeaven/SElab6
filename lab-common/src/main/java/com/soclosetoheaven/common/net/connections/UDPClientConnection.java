package com.soclosetoheaven.common.net.connections;

import com.soclosetoheaven.common.net.messaging.Request;
import com.soclosetoheaven.common.net.messaging.Response;
import org.apache.commons.lang3.SerializationException;
import org.apache.commons.lang3.SerializationUtils;

import java.io.*;
import java.net.DatagramPacket;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.util.ArrayList;
import java.util.Arrays;

public class UDPClientConnection implements SimpleConnection<Response, Request> {


    private final DatagramChannel channel;

    private final SocketAddress address;

    private ByteBuffer buffer;

    private boolean running;

    public UDPClientConnection(String addr, int port) throws IOException {
        this.address = new InetSocketAddress(addr, port);
        channel = DatagramChannel.open();
        buffer = ByteBuffer.allocate(BUFFER_SIZE);
    }


    @Override
    public void start() {
        running = true;
    }

    @Override
    public Response waitAndGetData() throws IOException { // сделать нормальный приём данных, потому что сейчас хуйня
        ArrayList<byte[]> buffers = new ArrayList<>();
        byte[] bufferArray;
        do {
            buffer.clear();
            channel.receive(buffer);
            bufferArray = buffer.array();
            buffers.add(bufferArray);
        } while (bufferArray.length == MAX_PACKET_SIZE);
        byte[] data = new byte[buffers.size() * BUFFER_SIZE];
        int currentPosition = 0;
        for (byte[] i : buffers) {
            for (byte j : i) {
                data[currentPosition] = j;
                currentPosition++;
            }
        }

        return SerializationUtils.deserialize(data);
    }

    @Override
    public void sendData(Request request) throws IOException{
        byte[] data = SerializationUtils.serialize(request);
        byte[][] packets = new byte[(int) Math.ceil(data.length / (double) MAX_PACKET_SIZE)][MAX_PACKET_SIZE];
        int currentPosition = 0;
        for (int i = 0; i < packets.length; ++i) {
            packets[i] = Arrays.copyOfRange(data, currentPosition, currentPosition + MAX_PACKET_SIZE);
            currentPosition += MAX_PACKET_SIZE;
        }

        for (byte[] bytePacket : packets) {
            buffer.clear();
            buffer.put(bytePacket);
            buffer.flip();
            channel.send(buffer, address);
        }
    }
}
