package fxverwaltung;

import fxverwaltung.Controllers.Main_ctr;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Main_ctr ctr = new Main_ctr();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("fxml/main.fxml"));
        fxmlLoader.setController(ctr);
        Parent root = fxmlLoader.load();
        primaryStage.setTitle("Schuelerverwaltung 4AHIT Style                                                                                                                             2018");
        primaryStage.setScene(new Scene(root));
        primaryStage.setResizable(false);
        primaryStage.show();
        ctr.init();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
