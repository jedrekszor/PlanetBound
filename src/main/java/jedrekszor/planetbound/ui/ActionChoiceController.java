package jedrekszor.planetbound.ui;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import jedrekszor.planetbound.App;
import jedrekszor.planetbound.logic.Logger;
import jedrekszor.planetbound.logic.Singleton;
import jedrekszor.planetbound.logic.data.ship.MiningShip;
import jedrekszor.planetbound.logic.data.ship.Ship;
import jedrekszor.planetbound.logic.states.Exploration;

import java.io.IOException;

public class ActionChoiceController {
    Scene scene;
    Stage stage;

    @FXML Circle planet;
    @FXML Circle station;

    @FXML CheckBox captain;
    @FXML CheckBox cargoOfficer;
    @FXML CheckBox landingParty;
    @FXML CheckBox weaponsOfficer;
    @FXML CheckBox shieldsOfficer;
    @FXML CheckBox navigationOfficer;

    @FXML Label black;
    @FXML Label blue;
    @FXML Label green;
    @FXML Label red;
    @FXML Label artefact;

    @FXML Label drone;
    @FXML Label fuel;
    @FXML Label shields;
    @FXML Label weapons;

    @FXML Button advanceB;
    @FXML Button exploreB;
    @FXML Button shopB;


    @FXML Button buyShieldB;
    @FXML Button buyWeaponB;
    @FXML Button buyFuelB;



    @FXML Button buyShieldSB;
    @FXML Button buyWeaponSB;
    @FXML Button buyFuelSB;
    @FXML Button serviceDroneB;
    @FXML Button buyDroneB;
    @FXML Button hireCrewB;
    @FXML Button upgradeWeaponsB;
    @FXML Button upgradeCargoB;


    @FXML Pane space;
    @FXML Pane converting;
    @FXML Pane upgrading;

    private Ship ship;
    private Singleton singleton;

    @FXML
    public void initialize() {
        singleton = Singleton.getInstance();
        ship = singleton.getShip();
        bind();
    }

    private void bind() {
        ship.captainProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observableValue, Boolean aBoolean, Boolean t1) {
                captain.setSelected(t1);
            }
        });
        ship.navigationOfficerProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observableValue, Boolean aBoolean, Boolean t1) {
                navigationOfficer.setSelected(t1);
            }
        });
        ship.landingPartyProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observableValue, Boolean aBoolean, Boolean t1) {
                landingParty.setSelected(t1);
            }
        });
        ship.weaponsOfficerProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observableValue, Boolean aBoolean, Boolean t1) {
                weaponsOfficer.setSelected(t1);
            }
        });
        ship.shieldsOfficerProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observableValue, Boolean aBoolean, Boolean t1) {
                shieldsOfficer.setSelected(t1);
            }
        });
        ship.cargoOfficerProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observableValue, Boolean aBoolean, Boolean t1) {
                cargoOfficer.setSelected(t1);
            }
        });

        ship.blackProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                black.setText(t1 + "/" + ship.getStorageLevel() * ship.getStoragePerLevel());
            }
        });
        ship.blueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                blue.setText(t1 + "/" + ship.getStorageLevel() * ship.getStoragePerLevel());
            }
        });
        ship.greenProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                green.setText(t1 + "/" + ship.getStorageLevel() * ship.getStoragePerLevel());
            }
        });
        ship.redProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                red.setText(t1 + "/" + ship.getStorageLevel() * ship.getStoragePerLevel());
            }
        });

        ship.artefactProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                artefact.setText(t1 + "/5");
            }
        });

        ship.getDrone().hpProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                drone.setText(t1 + "/" + ship.getDrone().getMaxHp());
            }
        });
        ship.fuelProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                fuel.setText(t1 + "/" + ship.getMaxFuel());
            }
        });
        ship.shieldsProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                shields.setText(t1 + "/" + ship.getMaxShields());
            }
        });
        ship.weaponsProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                weapons.setText(t1 + "/" + ship.getWeaponsLevel() * ship.getWeaponsPerLevel());
            }
        });

        singleton.currentPlanetColorProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                if(singleton.getCurrentPlanet() != null)
                    planet.setFill(Color.valueOf(t1));
                else
                    planet.setFill(Color.WHITE);

                if(singleton.getCurrentPlanet().hasStation())
                    station.setFill(Color.RED);
                else
                    station.setFill(Color.WHITE);
            }
        });

        updateFields();
        setVisible();
        verifyBuying();
    }

    @FXML
    public void advance() {
        singleton.advance();
        showStage("handlingEvent");
    }

    @FXML
    public void explore() {
        if(singleton.getShip().hasLandingParty()) {
            singleton.explore();
            showStage("exploration");
        } else {
            Logger.log("No landing party!");
        }
    }

    public void save() {
        singleton.save();
    }

    public void load() {
        singleton.load();
        singleton = Singleton.getInstance();
        if(singleton.getCurrentState() instanceof Exploration)
            showStage("exploration");
        ship = singleton.getShip();
        bind();
    }

    @FXML
    public void shop() {
        Singleton.getInstance().shop();
        setVisible();
        verifyBuying();
    }

    private void showStage(String name) {
        try {
            App.setRoot(name);
//            stage = new Stage();
//            scene = new Scene(App.loadFXML(name));
//            stage.setScene(scene);
//            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void updateFields() {
        captain.setSelected(ship.hasCaptain());
        navigationOfficer.setSelected(ship.hasNavigationOfficer());
        landingParty.setSelected(ship.hasLandingParty());
        weaponsOfficer.setSelected(ship.hasWeaponsOfficer());
        shieldsOfficer.setSelected(ship.hasShieldsOfficer());
        cargoOfficer.setSelected(ship.hasCargoOfficer());

        black.setText(ship.getBlack() + "/" + ship.getStoragePerLevel() * ship.getStorageLevel());
        blue.setText(ship.getBlue() + "/" + ship.getStoragePerLevel() * ship.getStorageLevel());
        green.setText(ship.getGreen() + "/" + ship.getStoragePerLevel() * ship.getStorageLevel());
        red.setText(ship.getRed() + "/" + ship.getStoragePerLevel() * ship.getStorageLevel());
        artefact.setText(ship.getArtefact() + "/5");

        if(ship.getDrone() != null)
            drone.setText(ship.getDrone().getHp() + "/" + ship.getDrone().getMaxHp());
        else
            drone.setText("0/6");
        fuel.setText(ship.getFuel() + "/" + ship.getMaxFuel());
        shields.setText(ship.getShields() + "/" + ship.getMaxShields());
        weapons.setText(ship.getWeapons() + "/" + ship.getWeaponsLevel() * ship.getWeaponsPerLevel());

        if(singleton.getCurrentPlanetColor() != null) {
            planet.setFill(Color.valueOf(singleton.getCurrentPlanetColor()));
        }
        else
            planet.setFill(Color.WHITE);

        if(singleton.getCurrentPlanet().hasStation())
            station.setFill(Color.RED);
        else
            station.setFill(Color.WHITE);
    }

    private void setVisible() {
        if(singleton.getCurrentState() == singleton.getActionChoice()) {
            space.setVisible(true);
            converting.setVisible(false);
            upgrading.setVisible(false);

            advanceB.setDisable(false);
            exploreB.setDisable(false);
        }
        else if(singleton.getCurrentState() == singleton.getConvertingResources()) {
            space.setVisible(false);
            converting.setVisible(true);
            upgrading.setVisible(false);

            advanceB.setDisable(true);
            exploreB.setDisable(true);
        }
        else if(singleton.getCurrentState() == singleton.getBuyingUpgrades()) {
            space.setVisible(false);
            converting.setVisible(false);
            upgrading.setVisible(true);

            advanceB.setDisable(true);
            exploreB.setDisable(true);
        }

    }

    private void verifyBuying() {
        if(singleton.getCurrentState() == singleton.getConvertingResources()) {
            buyFuelB.setDisable(ship.getBlack() == 0 || ship.getRed() == 0 || ship.getGreen() == 0 || ship.getFuel() == ship.getMaxFuel());
            buyShieldB.setDisable(ship.getBlack() == 0 || ship.getBlue() == 0 || ship.getGreen() == 0 || ship.getShields() == ship.getMaxShields());
            buyWeaponB.setDisable(ship.getBlack() == 0 || ship.getBlue() == 0 || ship.getWeapons() == ship.getWeaponsLevel() * ship.getWeaponsPerLevel());
        }
        else if(singleton.getCurrentState() == singleton.getBuyingUpgrades()) {
            buyFuelSB.setDisable(ship.getBlack() == 0 || ship.getRed() == 0 || ship.getGreen() == 0 || ship.getFuel() == ship.getMaxFuel());
            buyShieldSB.setDisable(ship.getBlack() == 0 || ship.getBlue() == 0 || ship.getGreen() == 0 || ship.getShields() == ship.getMaxShields());
            buyWeaponSB.setDisable(ship.getBlack() == 0 || ship.getBlue() == 0 || ship.getWeapons() == ship.getWeaponsLevel() * ship.getWeaponsPerLevel());

            if(ship.getBlack() == 0 || ship.getBlue() == 0 || ship.getGreen() == 0 || ship.getRed() == 0) {
                serviceDroneB.setDisable(true);
                buyDroneB.setDisable(true);
                hireCrewB.setDisable(true);
                upgradeWeaponsB.setDisable(true);
                upgradeCargoB.setDisable(true);
                return;
            } else {
                serviceDroneB.setDisable(ship.getDrone() == null || ship.getDrone().getHp() == ship.getDrone().getMaxHp());
                buyDroneB.setDisable(ship.getDrone() != null);
                hireCrewB.setDisable(ship.getCrew() == ship.getMaxCrew());
                upgradeWeaponsB.setDisable(ship instanceof MiningShip || ship.getWeaponsLevel() == ship.getMaxWeaponsLevel());
                upgradeCargoB.setDisable(ship.getStorageLevel() == ship.getMaxStorageLevel());
            }
        }
    }

    public void buyShield() {
        singleton.buyShield();
        verifyBuying();
    }
    public void buyWeapon() {
        singleton.buyWeapon();
        verifyBuying();
    }
    public void buyFuel() {
        singleton.buyFuel();
        verifyBuying();
    }



    public void serviceDrone() {
        singleton.serviceDrone();
        verifyBuying();
    }
    public void buyDrone() {
        singleton.buyDrone();
        verifyBuying();
    }
    public void hireCrew() {
        singleton.hireCrew();
        verifyBuying();
    }
    public void upgradeWeapons() {
        singleton.upgradeWeapons();
        verifyBuying();
    }
    public void upgradeCargo() {
        singleton.upgradeCargo();
        verifyBuying();
    }


    public void finishShopping() {
        singleton.endShopping();
        setVisible();
    }
}
