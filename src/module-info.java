module com.explorehub.explorehub {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;

    opens com.explorehub.explorehub to javafx.fxml;
    exports com.explorehub.explorehub;
}