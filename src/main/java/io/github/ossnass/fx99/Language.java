package io.github.ossnass.fx99;

import io.github.ossnass.fx99.types.ControllerInfo;

/**
 * This interface provides the basic skeleton for language file
 *
 * @author Ossama Nasser <ossnass@gmail.com>
 */
public interface Language {

    /**
     * The name of the language in the file
     */
    String LANG_NAME = "LANG.NAME";

    /**
     * The short name of the language like ar for Arabic, en for English
     * Combined with {@link Language#LANG_COUNTRY} to create for language identifier
     */
    String LANG_SHORT = "LANG.SHORT";

    /**
     * The country of the language for setting the correct locale, like SY for Syria, GB for Great Britain
     * Combined with {@link Language#LANG_SHORT} to create for language identifier
     */
    String LANG_COUNTRY = "LANG.COUNTRY";


    /**
     * The title of the stages in javafx, automatically loaded from the language file.
     * <p>
     * This is a formatted string, and need to use the {@link ControllerInfo#Id()}s to identify the correct stage name
     */
    String WINDOW_TITLE = "TITLE.%s";


    /**
     * The direction of the languae
     * <ul>
     *     <li>LTR for left to right</li>
     *      <li>RTL for right to left</li>
     * </ul>
     */
    String LANG_DIR = "LANG.DIR";

    String LANG_DIR_RTL="RTL";
    String LANG_DIR_LTR="LTR";
}
