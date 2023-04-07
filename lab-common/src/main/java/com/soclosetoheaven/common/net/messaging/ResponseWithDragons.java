package com.soclosetoheaven.common.net.messaging;

import com.soclosetoheaven.common.models.Dragon;

import java.io.Serial;

public class ResponseWithDragons extends Response{

    @Serial
    private final static long serialVersionUID = 9511891;

    private final Dragon[] dragons;

    public ResponseWithDragons(String description, Dragon[] dragons) {
        super(description);
        this.dragons = dragons;
    }

    public Dragon[] getDragons() {
        return dragons;
    }
}
