package sample;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class Controller {

    @FXML
    private Canvas mainCanvas;
    @FXML
    private GraphicsContext gc;
    private List<String[]> data = new ArrayList<String[]>();
    private ArrayList<Double> closeData = new ArrayList<>();


    public void downloadFile(String url){
        try{
            InputStream file = new URL(url).openStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(file,"UTF-8"));
            String line;
            while((line = bufferedReader.readLine()) != null){
                String[] cols = line.split(",");
                data.add(cols);
                //System.out.println(line);
            }
        }catch (IOException e){
            e.printStackTrace();
        }
        boolean header = true;
        for(int i = 0; i < data.size(); i++){
            if(!header){
                closeData.add(Double.valueOf(data.get(i)[4]));
            }else{
                header = false;
            }
        }
        //System.out.println(closeData);
    }

    @FXML public void initialize(){
        gc = mainCanvas.getGraphicsContext2D();
        downloadFile("https://query1.finance.yahoo.com/v7/finance/download/GOOG?period1=1262322000&period2=1451538000&interval=1mo&events=history&includeAdjustedClose=true");
        drawLinePlot();
    }

    public void drawLinePlot(){
        gc.setFill(Color.BLACK);
        gc.strokeLine(0,50,0,950);
        gc.strokeLine(0,950,800,950);

        gc.setFill(Color.RED);
        double x = 0;
        double y = 950;
        for(int i = 0; i < closeData.size() -1; i++){
            try{
                gc.strokeLine(x,y - closeData.get(i) + 50,x + 10, y - closeData.get(i+1) + 50);
            }catch (IndexOutOfBoundsException e){
                e.printStackTrace();
            }
            x += 10;
        }
    }

}
