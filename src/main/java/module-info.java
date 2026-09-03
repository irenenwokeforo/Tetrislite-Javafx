module com.tetrislite {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires javafx.media;
    requires java.desktop;
    requires javafx.base;
    

    opens com.tetrislite to javafx.fxml;
    exports com.tetrislite;
}
