package com.soclosetoheaven.common.net.connections;

import java.io.IOException;

public interface SimpleConnection<getT, sendT> {

    public static final int BUFFER_SIZE = 8192;
    public static final int MAX_PACKET_SIZE = BUFFER_SIZE - 1;
    void start();

    getT waitAndGetData() throws IOException;

    void sendData(sendT t) throws IOException;
}
