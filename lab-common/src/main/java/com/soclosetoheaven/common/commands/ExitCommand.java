package com.soclosetoheaven.common.commands;


import com.soclosetoheaven.common.clientio.BasicClientIO;
import com.soclosetoheaven.common.collectionmanagers.FileCollectionManager;
import com.soclosetoheaven.common.exceptions.InvalidCommandArgumentException;
import com.soclosetoheaven.common.util.TerminalColors;


public class ExitCommand extends AbstractCommand {

    public ExitCommand(BasicClientIO io, FileCollectionManager fcm) {
        super("exit", io, fcm);
    }

    @Override
    public void execute(String[] args) throws InvalidCommandArgumentException {
        if (args.length != 1)
            throw new InvalidCommandArgumentException(this.getName());
        this.getIO().writeln(TerminalColors.setColor("Program stopped, thanks for using it :)", TerminalColors.BLUE));
        System.exit(0);
    }

    @Override
    public String getUsage() {
        return "%s%s".formatted(
                TerminalColors.setColor("exit", TerminalColors.GREEN),
                TerminalColors.setColor(" - stops this program",
                        TerminalColors.BLUE));
    }
}
