package com.soclosetoheaven.common.commandmanagers;

import com.soclosetoheaven.common.commands.*;
import com.soclosetoheaven.common.exceptions.UnknownCommandException;
import com.soclosetoheaven.common.io.BasicIO;
import com.soclosetoheaven.common.net.messaging.Request;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class ClientCommandManager implements CommandManager<Request, String> {

    private final HashMap<String, AbstractCommand> commands;

    public ClientCommandManager() {
        commands = new HashMap<>();
    }

    @Override
    public Request manage(String t) {
        String[] args = t.trim().split("\\s+");
        String commandName = args[0];
        int commandArgumentsStartPosition = 1;
        args = Arrays.copyOfRange(args, commandArgumentsStartPosition, args.length);
        AbstractCommand command;
        if ((command = commands.get(commandName)) == null)
            throw new UnknownCommandException(commandName);
        return command.toRequest(args);
    }

    @Override
    public Map<String, ? extends AbstractCommand> getCommands() {
        return commands;
    }

    @Override
    public void addCommand(AbstractCommand command) {
        commands.put(command.getName(), command);
    }



    public static ClientCommandManager defaultManager(BasicIO io) {
        ClientCommandManager cm = new ClientCommandManager(); // add commands later
        Arrays.asList(
                new InfoCommand(null),
                new AddCommand(null, io),
                new SortCommand(null),
                new RemoveAllByAgeCommand(null),
                new ShowCommand(null),
                new ShowCommand(null),
                new CountLessThanAgeCommand(null),
                new ClearCommand(null),
                new RemoveByIDCommand(null),
                new RemoveAtCommand(null),
                new HelpCommand(null),
                new GroupCountingByCreationDateCommand(null),
                new UpdateCommand(null, io)
                ).forEach(cm::addCommand);
        return cm;
    }
}
