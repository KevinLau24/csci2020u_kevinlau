package sample;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Main extends Application{

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("lab04");

        // FXML Solution
        primaryStage.setScene(new Scene(root, 600, 300));


        // Programmatic Solution (Remove comment from primaryStage.setScene(scene))
        GridPane layout = new GridPane();
        layout.setAlignment(Pos.CENTER_LEFT);
        layout.setHgap(10);
        layout.setVgap(10);
        layout.setPadding(new Insets(25, 25, 25, 25));

        Scene scene = new Scene(layout, 600, 300);
        //primaryStage.setScene(scene);

        // Button
        Button button = new Button("Register");
        HBox hButton = new HBox(10);
        hButton.setAlignment(Pos.BOTTOM_LEFT);
        hButton.getChildren().add(button);

        // Creating Labels, Text
        Text welcome = new Text("Welcome");
        Label username = new Label("Username:");
        Label pass = new Label("Password:");
        Label fName = new Label("Full Name:");
        Label email = new Label("E-Mail:");
        Label phone = new Label("Phone #:");
        Label birth = new Label("Date of Birth:");
        Text confirmation = new Text();

        // Creating TextFields, DatePicker, PasswordField
        TextField usernameField = new TextField();
        usernameField.setPromptText("Username");
        PasswordField passField = new PasswordField();
        passField.setPromptText("Password");
        TextField fNameField = new TextField();
        fNameField.setPromptText("Full Name");
        TextField emailField = new TextField();
        emailField.setPromptText("E-Mail");
        TextField phoneField = new TextField();
        phoneField.setPromptText("Phone # (000-000-0000)");
        DatePicker birthDate = new DatePicker();
        birthDate.setPromptText("Date of Birth");

        // Adding on layout pane
        layout.add(welcome, 0,0);
        layout.add(username,0,1);
        layout.add(usernameField, 1,1);
        layout.add(pass, 0,2);
        layout.add(passField,1,2);
        layout.add(fName,0,3);
        layout.add(fNameField,1,3);
        layout.add(email,0,4);
        layout.add(emailField,1,4);
        layout.add(phone,0,5);
        layout.add(phoneField,1,5);
        layout.add(birth,0,6);
        layout.add(birthDate,1,6);
        layout.add(hButton,1,7);
        layout.add(confirmation, 4,1);

        // Printing confirmation to layout and text fields to console
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                System.out.println("Username is "+ usernameField.getText());
                System.out.println("Full Name is "+ fNameField.getText());
                System.out.println("E-Mail is "+ emailField.getText());
                System.out.println("Phone # is "+ phoneField.getText());
                confirmation.setText("You are registered!");
            }
        });

        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }

}
