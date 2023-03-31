package com.soclosetoheaven.common.commands;


import com.soclosetoheaven.common.clientio.BasicClientIO;
import com.soclosetoheaven.common.collectionmanagers.FileCollectionManager;
import com.soclosetoheaven.common.commandmanagers.BasicCommandManager;
import com.soclosetoheaven.common.exceptions.InvalidCommandArgumentException;
import com.soclosetoheaven.common.util.TerminalColors;


public class HelpCommand extends AbstractCommand{

    public HelpCommand(BasicClientIO io, FileCollectionManager fcm, BasicCommandManager bcm) {
        super("help", io, fcm, bcm);
    }

    @Override
    public void execute(String[] args) throws InvalidCommandArgumentException {
        if (args.length != 1)
            throw new InvalidCommandArgumentException(this.getName());
        getBasicCommandManager().getCommands().forEach((name, command) -> getIO().writeln(command.getUsage()));
    }

    @Override
    public String getUsage() {
        return "%s%s".formatted(
                TerminalColors.setColor("help", TerminalColors.GREEN),
                TerminalColors.setColor(" - provides information for all usable commands",
                        TerminalColors.BLUE));
    }
}
