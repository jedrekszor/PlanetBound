package jedrekszor.planetbound.ui;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import jedrekszor.planetbound.App;
import jedrekszor.planetbound.logic.Singleton;
import jedrekszor.planetbound.logic.data.exploration.Coordinates;
import jedrekszor.planetbound.logic.data.exploration.Drone;
import jedrekszor.planetbound.logic.data.exploration.Surface;
import jedrekszor.planetbound.logic.data.exploration.aliens.Alien;

import java.io.IOException;

public class ExplorationController {
    @FXML Rectangle alienO;
    @FXML Circle droneO;
    @FXML Ellipse resourceO;
    @FXML Label landingO;
    @FXML GridPane grid;

    @FXML Button up;
    @FXML Button down;
    @FXML Button left;
    @FXML Button right;

    private Surface surface;
    private Drone drone;
    private Coordinates landing;
    private Coordinates resource;
    Stage stage;
    Singleton singleton = Singleton.getInstance();

    @FXML
    public void initialize() {
        surface =  singleton.getCurrentSurface();
        drone = surface.getDrone();
        landing = surface.getLandingLocation();
        resource = surface.getResourceLocation();

        surface.alienXProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                GridPane.setColumnIndex(alienO, (int)t1);
            }
        });
        surface.alienYProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                GridPane.setRowIndex(alienO, (int)t1);
            }
        });
        drone.getCoordinates().xProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                GridPane.setColumnIndex(droneO, (int)t1);
                left.setDisable(!surface.canGoLeft());
                right.setDisable(!surface.canGoRight());
            }
        });
        drone.getCoordinates().yProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                GridPane.setRowIndex(droneO, (int)t1);
                up.setDisable(!surface.canGoUp());
                down.setDisable(!surface.canGoDown());
            }
        });

        surface.alienColorProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                alienO.setFill(Color.valueOf(t1));
            }
        });
        surface.runningProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observableValue, Boolean aBoolean, Boolean t1) {
                if(!t1 && surface.getSuccess())
                    successWindow();
                else if(!t1 && !surface.getSuccess())
                    failureWindow();
            }
        });
        surface.getAlien().dedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observableValue, Boolean aBoolean, Boolean t1) {
                alienO.setDisable(t1);
            }
        });

        setInitial();
    }
    private void setInitial() {
        GridPane.setColumnIndex(droneO, landing.getX());
        GridPane.setRowIndex(droneO, landing.getY());

        GridPane.setColumnIndex(alienO, surface.getAlienX());
        GridPane.setRowIndex(alienO, surface.getAlienY());

        GridPane.setColumnIndex(landingO, landing.getX());
        GridPane.setRowIndex(landingO, landing.getY());

        GridPane.setColumnIndex(resourceO, resource.getX());
        GridPane.setRowIndex(resourceO, resource.getY());

        System.out.println(surface.getAlienColor());
        alienO.setFill(Color.valueOf(surface.getAlienColor()));
        resourceO.setFill(Color.valueOf(surface.getResourceColor()));

        left.setDisable(!surface.canGoLeft());
        right.setDisable(!surface.canGoRight());
        down.setDisable(!surface.canGoDown());
        up.setDisable(!surface.canGoUp());
    }

    public void goUp() {
        singleton.moveDrone(0);
    }
    public void goDown() {
        singleton.moveDrone(1);
    }
    public void goRight() {
        singleton.moveDrone(2);
    }
    public void goLeft() {
        singleton.moveDrone(3);
    }




    private void successWindow() {
        try {
//            surface.stop();
            App.setRoot("successExploration");
//            stage = new Stage();
//            Scene scene = new Scene(App.loadFXML("successExploration"));
//            stage.setScene(scene);
//            stage.show();
//            System.out.println("dupa");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void failureWindow() {
        try {
            stage = new Stage();
            Scene scene = new Scene(App.loadFXML("failureExploration"));
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
