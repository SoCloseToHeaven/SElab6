package com.soclosetoheaven.common.commands;


import com.soclosetoheaven.common.clientio.BasicClientIO;
import com.soclosetoheaven.common.collectionmanagers.FileCollectionManager;
import com.soclosetoheaven.common.commandmanagers.BasicCommandManager;
import com.soclosetoheaven.common.exceptions.InvalidCommandArgumentException;
import com.soclosetoheaven.common.util.TerminalColors;


public class HistoryCommand extends AbstractCommand{

    public HistoryCommand(BasicClientIO io, FileCollectionManager fcm, BasicCommandManager bcm) {
        super("history", io, fcm, bcm);
    }

    @Override
    public void execute(String[] args) throws InvalidCommandArgumentException {
        if (args.length != 1)
            throw new InvalidCommandArgumentException(this.getName());
        getIO().writeln(TerminalColors.setColor(getBasicCommandManager().getCommandHistory().toString(), TerminalColors.BLUE));
    }

    @Override
    public String getUsage() {
        return "%s%s".formatted(
                TerminalColors.setColor("history", TerminalColors.GREEN),
                TerminalColors.setColor(" - displays 13 recently used commands",
                        TerminalColors.BLUE));
    }

}
