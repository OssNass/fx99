package io.github.ossnass.fx99.actions;

import io.github.ossnass.fx99.types.Controller;
import javafx.collections.ObservableList;

/**
 * 
 * This class is used by the controller to manage keyboard actions
 * 
 * The user must not use this class, as it is available by default in the {@link Controller}.
 * @author Ossama Nasser <ossnass@gmail.com>
 *
 */
class ActionManager {

	/**
	 * The controller this ActionManager is connected to.
	 */
	private Controller controller;
	
	/**
	 * A list of available actions in this ActionManager
	 */
	private ObservableList<Action> actions;
	
	//private ObservableMap<KeyCombination, V>
}
