package com.maisev.JFX;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;

public class Main extends Application{

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        Button ownedNoneButton = new Button("Owned None");
        ownedNoneButton.setOnAction(event -> showDialog(primaryStage,Modality.NONE));

        Button nonOwnedNon = new Button("Non Owned Non");
        nonOwnedNon.setOnAction(event -> showDialog(null,Modality.NONE));

        Button ownedWindowModal = new Button("Owned Window Modal");
        ownedNoneButton.setOnAction(event -> showDialog(primaryStage, Modality.WINDOW_MODAL));

        Button nonOwnedWindowModal = new Button("Non Owned Window Modal");
        ownedNoneButton.setOnAction(event -> showDialog(null, Modality.WINDOW_MODAL));

        Button ownedAppModal = new Button("Owned Application Modal");
        ownedNoneButton.setOnAction(event -> showDialog(primaryStage, Modality.APPLICATION_MODAL));

        Button nonOwnedAppModal = new Button("Non Owned Application Modal");
        ownedNoneButton.setOnAction(event -> showDialog(null, Modality.APPLICATION_MODAL));

        VBox root = new VBox();
        root.getChildren().addAll(ownedNoneButton,nonOwnedNon,ownedWindowModal,nonOwnedWindowModal,ownedAppModal,nonOwnedAppModal);

        Scene scene = new Scene(root);
        primaryStage.setScene(scene);

        primaryStage.show();
    }

    private void showDialog(Window owner, Modality windowModal) {
        Stage stage = new Stage();
        stage.setTitle("Hellou");
        stage.initOwner(owner);
        stage.initModality(windowModal);
        stage.setHeight(200);
        stage.setWidth(200);
        stage.show();
    }
}
