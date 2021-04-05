package Server;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class ServerController {

    @FXML
    private TextArea textArea;

    @FXML
    public void exit() {
        System.exit(0);
    }

    @FXML
    public void setTextArea(String output) {
        String prev = textArea.getText();
        textArea.setText(prev + "\n" + output);
    }

    @FXML
    public void initialize() throws IOException {
        ThreadPoolExecutor pool = (ThreadPoolExecutor) Executors.newFixedThreadPool(5);
        pool.execute(new ThreadPool());
    }
    class ThreadPool implements Runnable{
        ThreadPool(){

        }
        @Override
        public void run() {
            ThreadPoolExecutor pool = (ThreadPoolExecutor)Executors.newFixedThreadPool(25);
            try (var listener = new ServerSocket(8080)) {
                while (true) {
                    pool.execute(new Instance(listener.accept()));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    class Instance implements Runnable {
        private Socket socket;

        Instance(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            try {
                var s = new Scanner(socket.getInputStream());
                var pw = new PrintWriter(socket.getOutputStream(), true);
                String message = s.nextLine();
                setTextArea(message);
            }
            catch (Exception e) {
                System.out.println(e);
            }
            finally{
                try{
                    socket.close();
                }
                catch (IOException e){
                    e.printStackTrace();
                }
            }
        }
    }
}
