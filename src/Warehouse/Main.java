package Warehouse;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {


     //only one list being created as is static.
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("Warehouse.fxml"));
        primaryStage.setTitle("Warehouse Program");
        primaryStage.setScene(new Scene(root, 700, 500));
        primaryStage.show();

    }


    public static void main(String[] args) {
        launch(args);
    }
}
