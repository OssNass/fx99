package io.github.ossnass.fx99.types.exceptions;

/**
 * Used when attempting to create a non-existing controller
 */
public class ControllerNotFoundException extends RuntimeException{
    public ControllerNotFoundException(String message) {
        super(String.format("Controller with %s not found",message));
    }
}
