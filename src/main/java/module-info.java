module com.example.coursework {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires java.desktop;
    requires javafx.swing;
    requires junit;

    opens com.example.coursework to javafx.fxml;
    exports com.example.coursework;
    exports com.example.MMask;
    exports com.example.ibuff;
    exports com.example.ifiltrs;

}