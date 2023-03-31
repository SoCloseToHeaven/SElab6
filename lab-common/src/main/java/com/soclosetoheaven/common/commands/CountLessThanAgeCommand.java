package com.soclosetoheaven.common.commands;
import com.soclosetoheaven.common.clientio.BasicClientIO;
import com.soclosetoheaven.common.collectionmanagers.FileCollectionManager;

import com.soclosetoheaven.common.exceptions.InvalidCommandArgumentException;
import com.soclosetoheaven.common.models.Dragon;
import com.soclosetoheaven.common.util.TerminalColors;


public class CountLessThanAgeCommand extends AbstractCommand {

    public CountLessThanAgeCommand(BasicClientIO io, FileCollectionManager fcm) {
        super("count_less_than_age", io, fcm);
    }

    @Override
    public void execute(String[] args) throws InvalidCommandArgumentException {
        if (args.length != 2 || !args[1].matches("[1-9]\\d*"))
            throw new InvalidCommandArgumentException(this.getName());
        long age = Long.parseLong(args[1]);
        long counter = 0;
        for (Dragon dragon : getFileCollectionManager ().getCollection()) {
            if (dragon.getAge() == null || dragon.getAge() <= age)
                counter++;
        }
        getIO().writeln(TerminalColors.setColor(
                        "%d %s %s".formatted(counter, "elements found lower than", age),
                        TerminalColors.BLUE));
    }

    @Override
    public String getUsage() {
        return "%s%s".formatted(
                TerminalColors.setColor("count_less_than_age {age}", TerminalColors.GREEN),
                TerminalColors.setColor(" - counts elements of collection which age is lower than {age}(can't be null)",
                        TerminalColors.BLUE));
    }
}
