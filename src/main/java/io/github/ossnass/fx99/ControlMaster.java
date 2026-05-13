package io.github.ossnass.fx99;

import io.github.classgraph.ClassGraph;
import io.github.ossnass.fx99.types.Controller;
import io.github.ossnass.fx99.types.ControllerInfo;
import io.github.ossnass.fx99.types.exceptions.ControllerNotFoundException;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

/**
 * The control master is responsible for loading controllers and their resources, it also provides the ability to change the language of the application.
 * @author Ossama Nasser <ossnass@gmail.com></ossnass@gmail.com>
 *
 */
public class ControlMaster {

    private final Map<String, ExtendedControllerInfo> controllers;
    private ResourceBundle language;

    private ControlMaster() {
        controllers = new HashMap<>();
    }

    /**
     * Sets the language of the application
     *
     * @param file the path of the language file, if you want to use class path please add the prefix: 'classpath://' otherwise it will be read as a standard file path
     * @return ControlMaster with language
     * @throws IOException in case unable to read language file
     */
    public ControlMaster setLanguage(String file) throws IOException {
        language = new PropertyResourceBundle(ResourceManager.get().getResourceAsInputStream(file));
        return this;
    }

    /**
     * Intialized the control manager
     *
     * @param languageFile the language of the application
     * @param packageName  the name of the root package to scan for controllers
     * @throws IOException in case unable to read language file
     */
    public void initControllerMaster(String languageFile, String packageName) throws IOException {
        ResourceManager.initResourceManager(packageName.replace(".", "/"));
        var annotationName = ControllerInfo.class.getCanonicalName();
        this.setLanguage(languageFile);
        try (var scanResult = new ClassGraph()
                .enableAllInfo()             // Required to read annotations
                .acceptPackages(packageName)         // Restrict scan to your package
                .scan()) {
            for (var routeClassInfo : scanResult.getClassesWithAnnotation(annotationName)) {

                var annotation = routeClassInfo.getAnnotationInfo(annotationName);
                var params = annotation.getParameterValues();
                String id = (String) params.getValue("Id");
                ExtendedControllerInfo extendedInfo = new ExtendedControllerInfo();
                extendedInfo.FXMLFile = (String) params.getValue("fXMLfile");
                extendedInfo.extraCSS = (String) params.getValue("extraCSS");
                extendedInfo.icon = (String) params.getValue("icon");
                controllers.put(id, extendedInfo);
            }
        }
    }

    /**
     * Returns a controller with all bells and whistles
     *
     * @param id        the id of the controller
     * @param withStage true if you want to have a stage false otherwise
     * @return the controller
     * @throws IOException                 in case unable to open FXML file
     * @throws ControllerNotFoundException if the id doesn't match any controller
     */
    public Controller getController(String id, boolean withStage) throws IOException {
        if (!controllers.containsKey(id))
            throw new ControllerNotFoundException(id);
        var info = controllers.get(id);
        var controller = createController(info).createScene().setId(id);
        return withStage ? controller.setStage(new Stage()).setIcon(info.icon) : controller;
    }

    private Controller createController(ExtendedControllerInfo info) throws IOException {
        var loader = new FXMLLoader(ResourceManager.get().getURL(info.FXMLFile), this.language);
        loader.setResources(this.language);
        loader.load();
        var controller = (Controller) loader.getController();
        System.out.println(controller.getRoot().toString());
        return controller;
    }


    private static final ControlMaster INSTANCE = new ControlMaster();

    /**
     * Returns the active control master
     *
     * @return the active control master
     */
    public static ControlMaster get() {
        return INSTANCE;
    }

    private class ExtendedControllerInfo {
        String FXMLFile;
        String icon;
        String extraCSS;
    }
}
