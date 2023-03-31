package com.soclosetoheaven.common.commands;

import com.soclosetoheaven.common.collectionmanagers.CollectionManager;
import com.soclosetoheaven.common.exceptions.InvalidCommandArgumentException;
import com.soclosetoheaven.common.net.messaging.Request;
import com.soclosetoheaven.common.net.messaging.RequestBody;
import com.soclosetoheaven.common.net.messaging.Response;
import com.soclosetoheaven.common.net.messaging.ResponseWithException;
import com.soclosetoheaven.common.util.TerminalColors;

public class InfoCommand extends AbstractCommand{

    private final CollectionManager cm;
    public InfoCommand(CollectionManager cm) {
        super("info");
        this.cm = cm;
    }

    @Override
    public Response execute(RequestBody requestBody) {
        return new Response(TerminalColors.setColor(cm.toString(), TerminalColors.BLUE));
    }

    @Override
    public Request toRequest(String[] args) {
        return super.toRequest(null);
    }
}
