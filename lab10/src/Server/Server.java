package Server;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Server extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root2 = FXMLLoader.load(getClass().getResource("server.fxml"));
        primaryStage.setTitle("lab10 - Server");
        Scene mainScene = new Scene(root2, 300,275);
        primaryStage.setScene(mainScene);
        primaryStage.show();
    }

    public static void main(String[] args){
        launch(args);
    }
}


