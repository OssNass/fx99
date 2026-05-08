package io.github.ossnass.fx99.types;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * This class provides the annotation neccessary to be used by any class
 * extending {@link Controller}.
 * <p>
 * It records the following information:
 * <ol>
 * <li>The path to FXML File</li>
 * <li>The name of the extra css containing custom styling, defaults to null</li>
 * <li>The name of the icon of the stage of the controller, defaults to
 * null</li>
 * </ol>
 *
 * @author Ossama Nasser <ossnass@gmail.com>
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface ControllerInfo {
    /**
     * The Id of the Controller
     */
    String Id();

    /**
     * The name of the FXML file of this controller cannot be null
     */
    String fXMLfile();

    /**
     * The name of the extra css containg custom styling, defaults to null
     */
    String extraCSS() default "";

    /**
     * The name of the icon of the stage of the controller, defaults to null
     */
    String icon() default "";
}