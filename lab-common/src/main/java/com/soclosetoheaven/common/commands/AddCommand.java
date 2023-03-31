package com.soclosetoheaven.common.commands;

import com.soclosetoheaven.common.collectionmanagers.FileCollectionManager;
import com.soclosetoheaven.common.exceptions.InvalidRequestException;
import com.soclosetoheaven.common.io.BasicIO;
import com.soclosetoheaven.common.models.Dragon;
import com.soclosetoheaven.common.net.factories.RequestFactory;
import com.soclosetoheaven.common.net.messaging.*;

public class AddCommand extends AbstractCommand{

    private final FileCollectionManager cm;

    private final BasicIO io;

    public AddCommand(FileCollectionManager cm, BasicIO io) {
        super("add");
        this.cm = cm;
        this.io = io;
    }

    @Override
    public Response execute(RequestBody requestBody) {
        if (requestBody instanceof RequestBodyWithDragon) {
            Dragon dragon = ((RequestBodyWithDragon) requestBody).getDragon();
            cm.add(dragon);
            return new Response("%s - %s".formatted(dragon, "added in collection"));
        }
        return new ResponseWithException(
                "ATTENTION",
                new InvalidRequestException("r"));
    }

    @Override
    public Request toRequest(String[] args) {
        return RequestFactory.createRequestWithDragon(getName(), null, io);
    }
}