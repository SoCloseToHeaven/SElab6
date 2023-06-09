package com.soclosetoheaven.common.models.factories;

import com.soclosetoheaven.common.exceptions.InvalidFieldValueException;
import com.soclosetoheaven.common.io.BasicIO;
import com.soclosetoheaven.common.models.Dragon;
import com.soclosetoheaven.common.util.TerminalColors;

public class DragonFactory {

    private DragonFactory() {
        throw new UnsupportedOperationException("This is an utility class and can not be instantiated");
    }

    public static Dragon createDragon(BasicIO io) {
        return new Dragon(
                inputName(io),
                CoordinatesFactory.createCoordinates(io),
                inputAge(io),
                inputDescription(io),
                inputWingspan(io),
                DragonTypeFactory.createDragonType(io),
                DragonCaveFactory.createDragonCave(io)
                );
    }

    private static String inputName(BasicIO io) {
        try {
            String name = io.stdRead("Type element's name: ");
            Dragon.VALIDATOR.validateName(name);
            return name;
        } catch (InvalidFieldValueException e) {
            io.writeln(e.getMessage());
            return inputName(io);
        }
    }

    private static Long inputAge(BasicIO io) {
        try {
            String ageString = io.stdReadLineWithNull("Type age: "); //age can be null
            Long age = (ageString == null) ? null : Long.parseLong(ageString);
            Dragon.VALIDATOR.validateAge(age);
            return age;
        } catch (NumberFormatException|InvalidFieldValueException e) {
            io.writeln(TerminalColors.setColor(e.getMessage(), TerminalColors.RED));
            return inputAge(io);
        }
    }

    private static String inputDescription(BasicIO io) {
        try {
            String description = io.stdReadLineWithNull("Type description: ");//description
            Dragon.VALIDATOR.validateDescription(description);
            return description;
        } catch (InvalidFieldValueException e) {
            io.writeln(TerminalColors.setColor(e.getMessage(), TerminalColors.RED));
            return inputDescription(io);
        }
    }
    private static Integer inputWingspan(BasicIO io) {
        try {
            Integer wingspan = Integer.parseInt(io.stdReadLineWithNull("Type wingspan: "));//wingspan
            Dragon.VALIDATOR.validateWingspan(wingspan);
            return wingspan;
        } catch (InvalidFieldValueException | NumberFormatException e) {
            io.writeln(TerminalColors.setColor(e.getMessage(), TerminalColors.RED));
            return inputWingspan(io);
        }
    }
}
