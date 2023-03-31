package com.soclosetoheaven.client;

import com.soclosetoheaven.common.clientio.BasicClientIO;
import com.soclosetoheaven.common.collectionmanagers.FileCollectionManager;
import com.soclosetoheaven.common.commandmanagers.BasicCommandManager;


import com.soclosetoheaven.common.commands.*;
import com.soclosetoheaven.common.exceptions.ExecutingScriptException;
import com.soclosetoheaven.common.exceptions.InvalidCommandArgumentException;
import com.soclosetoheaven.common.exceptions.InvalidFieldValueException;
import com.soclosetoheaven.common.exceptions.UnknownCommandException;
import com.soclosetoheaven.common.util.TerminalColors;

import java.io.IOException;
import java.util.Arrays;
import java.util.NoSuchElementException;

/**
 * class used for interaction of user with console
 */
public class ConsoleClient {

    private final BasicClientIO io;

    private final BasicCommandManager cm;

    /**
     * Prefix used before user's input
     */
    private static final String INPUT_PREFIX = TerminalColors.setColor("> ", TerminalColors.CYAN);


    private FileCollectionManager fcm;

    public ConsoleClient(BasicClientIO io, BasicCommandManager cm){
        this.io = io;
        this.cm = cm;
    }

    /**
     * runs ConsoleClient
     */
    public void run() {
        //addBasicCommands(this.io, this.cm, this.fcm);
        io.writeln(TerminalColors.setColor("Type the name of environment variable: ", TerminalColors.GREEN));
        try {
            String filePath = System.getenv(io.read(INPUT_PREFIX));
            this.fcm = new FileCollectionManager(filePath);
            fcm.open();
        } catch (IOException |
                NullPointerException e) {
            io.write(TerminalColors.setColor("%s - exiting program".formatted(e.getMessage()), TerminalColors.RED));
            System.exit(-1);
        }
        addBasicCommands(this.io, this.cm, this.fcm);
        io.writeln(TerminalColors.setColor("Collection with these elements was loaded:",TerminalColors.BLUE));
        fcm.getCollection().forEach(element -> io.writeln(element.toString()));
        io.writeln(TerminalColors.setColor(
                "Type [help] to get information about all usable commands or type another command:",
                TerminalColors.GREEN));
        String input;
        while ((input = io.read(INPUT_PREFIX)) != null) {
            try {
                cm.manage(input);
            } catch (UnknownCommandException |
                     NoSuchElementException |
                     InvalidFieldValueException |
                     InvalidCommandArgumentException |
                     NullPointerException |
                     NumberFormatException |
                     ExecutingScriptException |
                     UnsupportedOperationException e) {
                io.writeln(TerminalColors.setColor(e.getMessage(), TerminalColors.RED));
            }
        }
    }


    private void addBasicCommands(BasicClientIO io, BasicCommandManager bcm, FileCollectionManager fcm) {
        Arrays.asList(
                new AddElementCommand(io, fcm),
                new ClearCommand(io, fcm),
                new CountLessThanAgeCommand(io, fcm),
                new ExecuteScriptCommand(io, fcm, bcm),
                new ExitCommand(io, fcm),
                new GroupCountingByCreationDateCommand(io, fcm),
                new HelpCommand(io, fcm, bcm),
                new HistoryCommand(io, fcm, bcm),
                new InfoCommand(io, fcm),
                new RemoveAllByAgeCommand(io, fcm),
                new RemoveAtCommand(io, fcm),
                new RemoveByIDCommand(io, fcm),
                new SaveCommand(io, fcm),
                new ShowCommand(io, fcm),
                new SortCommand(io, fcm),
                new UpdateCommand(io, fcm)).forEach(bcm::addCommand);
    }
}
