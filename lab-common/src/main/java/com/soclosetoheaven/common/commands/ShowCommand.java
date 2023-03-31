package com.soclosetoheaven.common.commands;


import com.soclosetoheaven.common.clientio.BasicClientIO;
import com.soclosetoheaven.common.collectionmanagers.FileCollectionManager;
import com.soclosetoheaven.common.exceptions.InvalidCommandArgumentException;
import com.soclosetoheaven.common.models.Dragon;
import com.soclosetoheaven.common.util.TerminalColors;

import java.util.ArrayList;


public class ShowCommand extends AbstractCommand{

    public ShowCommand(BasicClientIO io, FileCollectionManager fcm) {
        super("show", io, fcm);
    }

    @Override
    public void execute(String[] args) throws InvalidCommandArgumentException {
        if (args.length != 1)
            throw new InvalidCommandArgumentException(this.getName());
        ArrayList<Dragon> collection = getFileCollectionManager().getCollection();
        if (collection.size() == 0)
            getIO().writeln(TerminalColors.setColor("No elements found in collection", TerminalColors.BLUE));
        else
            collection.forEach(element -> getIO().writeln(element.toString()));
    }

    @Override
    public String getUsage() {
        return "%s%s".formatted(
                TerminalColors.setColor("show", TerminalColors.GREEN),
                TerminalColors.setColor(" - displays all elements of collection with their data",
                        TerminalColors.BLUE));
    }
}
