module jedrekszor.planetbound {
    requires javafx.controls;
    requires kotlin.stdlib;
    requires javafx.fxml;
    exports jedrekszor.planetbound;
    exports jedrekszor.planetbound.ui;
    opens jedrekszor.planetbound.ui;
}