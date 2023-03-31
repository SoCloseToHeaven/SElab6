package com.soclosetoheaven.common.commands;

import com.soclosetoheaven.common.clientio.BasicClientIO;
import com.soclosetoheaven.common.collectionmanagers.FileCollectionManager;
import com.soclosetoheaven.common.exceptions.InvalidCommandArgumentException;
import com.soclosetoheaven.common.models.Dragon;
import com.soclosetoheaven.common.models.DragonType;
import com.soclosetoheaven.common.util.TerminalColors;


public class UpdateCommand extends CollectionElementEditorCommand {

    public UpdateCommand(BasicClientIO io, FileCollectionManager fcm) {
        super("update", io, fcm);
    }

    @Override
    public void execute(String[] args) throws InvalidCommandArgumentException { //rebuild later
        if (args.length != 2 || !args[1].matches("[1-9]\\d*"))
            throw new InvalidCommandArgumentException(this.getName());
        long id = Long.parseLong(args[1]);
        String name = inputName();
        Integer x = inputX();
        double y = inputY();
        Long age = inputAge();
        String description = inputDescription();
        Integer wingspan = inputWingspan();
        long depth = inputDepth();
        int numberOfTreasures = inputNumberOfTreasures();
        DragonType type = inputDragonType();
        Dragon dragon = getFileCollectionManager().getByID(id);
        dragon.update(name,x,y,age, description,wingspan,type,depth,numberOfTreasures);
        getIO().writeln(TerminalColors.setColor("Element updated successfully",TerminalColors.BLUE));
    }

    @Override
    public String getUsage() {
        return "%s%s".formatted(
                TerminalColors.setColor("update {ID} {element}", TerminalColors.GREEN),
                TerminalColors.setColor(" - updates data(in the same way as in {add} command) with the same ID",
                        TerminalColors.BLUE));
    }
}
