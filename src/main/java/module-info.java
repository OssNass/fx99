module io.github.ossnass.fx99 {
    requires javafx.controls;
    requires javafx.fxml;
    requires io.github.classgraph;
    exports io.github.ossnass.fx99;
    exports io.github.ossnass.fx99.actions;
    exports io.github.ossnass.fx99.types;
    opens io.github.ossnass.fx99.types to javafx.fxml;
}