package io.github.ossnass.fx99.types.exceptions;

/**
 * Used when attempting to create a non-existing controller
 */
public class ControllerNotFoundException extends RuntimeException {
    /**
     * Creates a new instance of {@link ControllerNotFoundException}
     *
     * @param message the id of the controller that was not found
     */
    public ControllerNotFoundException(String message) {
        super(String.format("Controller with %s not found", message));
    }
}
