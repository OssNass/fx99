package io.github.ossnass.fx99.types.exceptions;

import io.github.ossnass.fx99.types.Controller;
import io.github.ossnass.fx99.types.ControllerInfo;



/**
 * This class represents an exception thrown if and only if the {@link Controller} is not annotated with {@link ControllerInfo}
 * @author Ossama Nasser <ossnass@gmail.com>
 *
 */
public class ControllerNotAnnotatedException extends RuntimeException{
    /**
	 * 
	 */

	private static final long serialVersionUID = 2247702364519019248L;

	/**
     * Create a new instance of {@link ControllerNotFoundException}
	 * @param controllerName The name of the controller that was not annotated
     */
	public ControllerNotAnnotatedException(String controllerName){
        super(String.format("Conteroller %s is not annotated with ControllerInfo annotation",controllerName));
    }
}