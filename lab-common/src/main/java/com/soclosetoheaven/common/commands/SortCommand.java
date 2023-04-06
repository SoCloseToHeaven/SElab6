package com.soclosetoheaven.common.commands;

import com.soclosetoheaven.common.collectionmanagers.FileCollectionManager;
import com.soclosetoheaven.common.net.messaging.Request;
import com.soclosetoheaven.common.net.messaging.RequestBody;
import com.soclosetoheaven.common.net.messaging.Response;
import com.soclosetoheaven.common.util.TerminalColors;

public class SortCommand extends AbstractCommand{
    private FileCollectionManager cm;
    public SortCommand(FileCollectionManager cm) {
        super("sort");
        this.cm = cm;
    }

    @Override
    public Response execute(RequestBody requestBody) {
        cm.sort();
        return new Response(
                TerminalColors.setColor(
                        "Collection was sorted(DEFAULT)",
                        TerminalColors.BLUE)
        );
    }

    @Override
    public Request toRequest(String[] args) {
        return super.toRequest(null);
    }

    @Override
    public String getUsage() {
        return "%s%s".formatted(
                "sort",
                " - sorts the collection in ascending order by ID"
        );
    }
}
