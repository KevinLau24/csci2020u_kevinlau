package sample;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.io.BufferedOutputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class Controller {
    @FXML
    private TextField msgTextField;
    @FXML
    private TextField userTextField;

    @FXML
    public void sendMessage(){
        String message = msgTextField.getText();
        String user = userTextField.getText();

        Socket clientSocket = null;
        PrintWriter out = null;
        try{
            String output = userTextField.getText() + ": " + msgTextField.getText();
            clientSocket = new Socket("localhost",8080);
            out = new PrintWriter(clientSocket.getOutputStream(),true);
            out.println(output);
            out.flush();
        }catch (Exception e){
            e.printStackTrace();
        }
        //System.out.println(message);
    }

    @FXML
    public void exit(){
        System.exit(0);
    }
}
