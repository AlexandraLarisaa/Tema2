module ro.mta.se.lab {
    requires javafx.controls;
    requires javafx.fxml;
    requires json.simple;
    requires java.desktop;

    opens ro.mta.se.lab to javafx.fxml;
    exports ro.mta.se.lab;

    exports ro.mta.se.lab.controller;
    opens ro.mta.se.lab.controller to javafx.fxml;

}