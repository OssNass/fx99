package io.github.ossnass.fx99.actions;

import javafx.scene.input.KeyCombination;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * This annotation is used to decorate the methods that are associated with {@link Action#shortcut()} keyboard shortcut.
 * 
 * It requires 2 parameters:
 * <ol>
 * <li>
 * {@link Action#name()} a unique name of the action so you change its shortcut after the application launches
 * </li>
 * <li>
 * {@link Action#shortcut()} the default keyboard shortcut associated with the action, please refer to {@link KeyCombination} for more info.
 * </li>
 * </ol>
 * @author ossama
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Action {
	/**
	 * A unique name of the action so you change its shortcut after the application launches
	 * @return the name of the action
	 */
	String name();
	
	/**
	 * The default keyboard shortcut associated with the action, please refer to {@link KeyCombination} for more info.
	 * @return the default shortcut
	 */
	String shortcut();
}
