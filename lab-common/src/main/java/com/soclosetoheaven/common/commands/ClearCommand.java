package com.soclosetoheaven.common.commands;


import com.soclosetoheaven.common.clientio.BasicClientIO;
import com.soclosetoheaven.common.collectionmanagers.FileCollectionManager;
import com.soclosetoheaven.common.exceptions.InvalidCommandArgumentException;
import com.soclosetoheaven.common.util.TerminalColors;


public class ClearCommand extends AbstractCommand{

    public ClearCommand(BasicClientIO io, FileCollectionManager fcm) {
        super("clear", io, fcm);
    }

    @Override
    public void execute(String[] args) throws InvalidCommandArgumentException {
        if (args.length != 1)
            throw new InvalidCommandArgumentException(this.getName());
        this.getFileCollectionManager().clear();
        getIO().writeln(TerminalColors.setColor("Collection was successfully cleared    ",TerminalColors.BLUE));
    }

    @Override
    public String getUsage() {
        return "%s%s".formatted(
                TerminalColors.setColor("clear", TerminalColors.GREEN),
                TerminalColors.setColor(" - removes all elements out of collection",
                        TerminalColors.BLUE));
    }
}
