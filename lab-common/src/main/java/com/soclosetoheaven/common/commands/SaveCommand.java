package com.soclosetoheaven.common.commands;


import com.soclosetoheaven.common.clientio.BasicClientIO;
import com.soclosetoheaven.common.collectionmanagers.FileCollectionManager;
import com.soclosetoheaven.common.exceptions.InvalidCommandArgumentException;
import com.soclosetoheaven.common.util.TerminalColors;


public class SaveCommand extends AbstractCommand{

    public SaveCommand(BasicClientIO io, FileCollectionManager fcm) {
        super("save", io, fcm);
    }

    @Override
    public void execute(String[] args) throws InvalidCommandArgumentException {
        if (args.length != 1)
            throw new InvalidCommandArgumentException(this.getName());
        if (this.getFileCollectionManager().save())
            this.getIO().writeln(TerminalColors.setColor("Collection was successfully saved", TerminalColors.BLUE));
        else
            this.getIO().writeln(TerminalColors.setColor("Collection wasn't saved, try again", TerminalColors.RED));
    }

    @Override
    public String getUsage() {
        return "%s%s".formatted(
                TerminalColors.setColor("save", TerminalColors.GREEN),
                TerminalColors.setColor(" - saves collection to file",
                        TerminalColors.BLUE));
    }
}
