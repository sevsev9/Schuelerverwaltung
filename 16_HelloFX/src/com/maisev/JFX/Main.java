package com.maisev.JFX;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Main extends Application{

    public static void main(String[] args) {
	    Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("SeasHTL");
        Group root = new Group(new Button("Hello"));
        Scene scene = new Scene(root,300,100);
        primaryStage.setScene(scene);
        primaryStage.initStyle(StageStyle.DECORATED);
        //primaryStage.initStyle(StageStyle.UNDECORATED);
        //primaryStage.initStyle(StageStyle.TRANSPARENT);
        //primaryStage.initStyle(StageStyle.UNIFIED);
        //primaryStage.initStyle(StageStyle.UTILITY);
        primaryStage.show();

        Rectangle2D bounds = Screen.getPrimary().getVisualBounds();
        double x = bounds.getMinX() + (bounds.getWidth() - primaryStage.getWidth())/2;
        double y = bounds.getMinY() + (bounds.getHeight() - primaryStage.getHeight())/2;
        primaryStage.setX(x);
        primaryStage.setY(y);
    }
}
