module sio.devoir3sio1b {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    opens sio.devoir3sio1b.Model;
    opens sio.devoir3sio1b to javafx.fxml;
    exports sio.devoir3sio1b;
}