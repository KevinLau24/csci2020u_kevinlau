package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class Controller {

    @FXML private Text confirmation;
    @FXML private TextField username;
    @FXML private TextField fName;
    @FXML private TextField email;
    @FXML private TextField phone;

    public void handleButton(ActionEvent actionEvent) {
        System.out.println("Username is "+ username.getText());
        System.out.println("Full Name is "+ fName.getText());
        System.out.println("E-Mail is "+ email.getText());
        System.out.println("Phone # is "+ phone.getText());
        confirmation.setText("You are registered!");
    }
}
