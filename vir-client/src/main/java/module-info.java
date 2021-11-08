module com.vir.bm.client {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;

    opens com.vir.bm.client to javafx.fxml;
    exports com.vir.bm.client;
}