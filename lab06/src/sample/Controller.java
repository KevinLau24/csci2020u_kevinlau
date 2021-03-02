package sample;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;

public class Controller {

    @FXML
    private Canvas mainCanvas;
    @FXML
    private GraphicsContext gc;

    private static double[] avgHousingPricesByYear =
            { 247381.0,264171.4,287715.3,294736.1, 308431.4,322635.9,340253.0,363153.7};
    private static double[] avgCommercialPricesByYear =
            { 1121585.3,1219479.5,1246354.2,1295364.8, 1335932.6,1472362.0,1583521.9,1613246.3};

    private static String[] ageGroups =
            { "18-25", "26-35", "36-45", "46-55", "56-65", "65+"};
    private static int[] purchasesByAgeGroup =
            { 648, 1021, 2453, 3173, 1868, 2247};
    private static Color[] pieColours =
            { Color.AQUA, Color.GOLD, Color.DARKORANGE, Color.DARKSALMON, Color.LAWNGREEN, Color.PLUM};

    @FXML
    public void initialize(){
        gc = mainCanvas.getGraphicsContext2D();
        drawGraph(450,450,avgHousingPricesByYear, Color.RED, avgCommercialPricesByYear, Color.BLUE);
        drawPie(950, 450, ageGroups, purchasesByAgeGroup, pieColours);
    }

    public void drawGraph(int w, int h, double[] data, Color color, double[] data2, Color color2) {
        double xInc = (w/2) / data.length;
        double maxVal = Double.NEGATIVE_INFINITY;
        double minVal = 0;
        for (double val : data2) {
            if (val > maxVal) {
                maxVal = val;
            }
        }

        //Plotting Graph
        double x = 0;
        double x2 = xInc;
        for (double val: data) {
            gc.setFill(color);
            double height = ((val - minVal) / (maxVal - minVal)) * h;
            gc.fillRect(x, (h - height), xInc, height);
            x += xInc + xInc + 5;
        }
        for (double val2 : data2) {
            double height2 = ((val2 - minVal) / (maxVal - minVal)) * h;
            gc.setFill(color2);
            gc.fillRect(x2, (h - height2), xInc, height2);
            x2 += xInc + xInc + 5;
        }
    }

    public void drawPie(int w, int h, String[] label, int[] data, Color[] color){
        double start = 0;
        double end = 0;
        int sum = 0;
        int i = 0;

        for (int val : data){
            sum += val;
        }

        // Drawing Pie Chart
        for (int val : data){
            gc.setFill(color[i]);
            end =(val/(double)sum) * 360;
            gc.fillArc(550, 0, 400,400, start, end, ArcType.ROUND);
            start += end ;
            i++;
        }
    }

    // Tut Example
    public void drawExample(int w, int h){
        gc.strokeLine(0,0,w,h);
        gc.setFill(Color.RED);
        gc.fillRect(0,0,150,150);
    }

}
