package com.soclosetoheaven.common.exceptions;

/**
 * Used when command manager can't find command
 */
public class UnknownCommandException extends RuntimeException{

    /**
     * @param name command's name
     */

    public UnknownCommandException(String name) {
        super("There is no command with name: %s".formatted(name));
    }
}
