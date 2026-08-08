module com.tetrislite {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;

    opens com.tetrislite to javafx.fxml;
    exports com.tetrislite;
}
