package sample;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.Inet4Address;
import java.util.HashMap;
import java.util.Map;

public class Controller {

    @FXML
    private Canvas mainCanvas;
    @FXML
    private GraphicsContext gc;

    private static Color[] pieColours =
            { Color.AQUA, Color.GOLD, Color.DARKORANGE, Color.DARKSALMON, Color.LAWNGREEN, Color.PLUM};
    private static HashMap<String, Integer> data = new HashMap<String, Integer>();
    //private static String[] label = {"SPECIAL MARINE", "TORNADO", "FLASH FLOOD", "SEVERE THUNDERSTORM"};

    @FXML public void initialize(){
        gc = mainCanvas.getGraphicsContext2D();
        FileLoader loader = new FileLoader("./weatherwarnings-2015.csv");
        loader.loadFile();
        data = loader.getData();
        //System.out.println(data.get("FLASH FLOOD"));
        drawPie(950,450, data, pieColours);
        drawLegend(data, pieColours);
    }

    public void drawPie(int w, int h, HashMap<String, Integer> data, Color[] color){
        double start = 0;
        double end = 0;
        int sum = 0;
        int i = 0;

        for (Map.Entry<String, Integer> map : data.entrySet()){
            sum += map.getValue();
        }

        // Drawing Pie Chart
        for (Map.Entry<String, Integer> map : data.entrySet()){
            gc.setFill(color[i]);
            end = (map.getValue()/(double)sum) * 360;
            gc.fillArc(550, 0, 400,400, start, end, ArcType.ROUND);
            start += end ;
            i++;
        }
    }

    public void drawLegend(HashMap<String, Integer> data, Color[] color){
        int i = 0;
        int rectY = 100;
        int textY = 125;

        for(Map.Entry<String, Integer> map : data.entrySet()){
            gc.setFill(color[i]);
            gc.fillRect(50,rectY, 50, 40);
            gc.strokeText(map.getKey(), 150, textY);
            rectY += 50;
            textY += 50;
            i++;
        }
    }
}
