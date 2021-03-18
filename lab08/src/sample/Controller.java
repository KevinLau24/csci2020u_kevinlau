package sample;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;

import javax.management.monitor.StringMonitor;
import java.awt.event.PaintEvent;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static sample.DataSource.*;

public class Controller {

    @FXML
    private TableView tableView;
    @FXML
    private TableColumn SID;
    @FXML
    private TableColumn assignment;
    @FXML
    private TableColumn midterm;
    @FXML
    private TableColumn fExam;
    @FXML
    private TableColumn fMark;
    @FXML
    private TableColumn lGrade;
    @FXML
    private MenuBar menuBar;
    @FXML
    private MenuItem menuItem;
    @FXML
    private TextField newSID;
    @FXML
    private TextField newAssignment;
    @FXML
    private TextField newMidterm;
    @FXML
    private TextField newFinal;

    private ObservableList<StudentRecord> data;
    private File currentFilename = new File("output.csv");

    @FXML
    public void initialize(){
        data = DataSource.getAllMarks();
        SID.setCellValueFactory(new PropertyValueFactory<>("SID"));
        assignment.setCellValueFactory(new PropertyValueFactory<>("assignment"));
        midterm.setCellValueFactory(new PropertyValueFactory<>("midterm"));
        fExam.setCellValueFactory(new PropertyValueFactory<>("fExam"));
        fMark.setCellValueFactory(new PropertyValueFactory<>("fMark"));
        lGrade.setCellValueFactory(new PropertyValueFactory<>("lGrade"));
        tableView.setItems(data);
        //System.out.println(data);
    }

    @FXML
    public void clear(){
        data.clear();
        System.out.println("Clearing data");
    }

    @FXML
    public void exit(){
        System.out.println("Program Exiting");
        System.exit(0);
    }

    @FXML
    public void load(){
        currentFilename = new File("output.csv");
        try{
            BufferedReader br = new BufferedReader(new FileReader(currentFilename));
            String line;
            String header = br.readLine();
            while ((line = br.readLine()) != null){
                String[] fields = line.split(",",-1);
                StudentRecord studentRecord = new StudentRecord(fields[0],Double.parseDouble(fields[1]),Double.parseDouble(fields[2]),Double.parseDouble(fields[3]));
                data.add(studentRecord);
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }
        System.out.println("Opening: " + currentFilename.getName());
    }

    @FXML
    public void save(){
        StudentRecord records;
        List<List<String>> list = new ArrayList<>();
        for(int i = 0; i < tableView.getItems().size(); i++){
            records = (StudentRecord) tableView.getItems().get(i);
            list.add(new ArrayList<>());
            list.get(i).add(records.getSID());
            list.get(i).add(String.valueOf(records.getAssignment()));
            list.get(i).add(String.valueOf(records.getMidterm()));
            list.get(i).add(String.valueOf(records.getFExam()));
        }
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < list.size(); i++){
            for(int j = 0; j < list.get(i).size(); j++) {
                sb.append(list.get(i).get(j));
                sb.append(",");
            }
            sb.append("\n");
        }
        try(PrintWriter pw = new PrintWriter(currentFilename)){
            pw.append(sb.toString());
            System.out.println("Saving to file: " + currentFilename.getName());
        }catch(FileNotFoundException e){
            e.printStackTrace();
        }
    }

    @FXML
    public void saveAs(){
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open output file");
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("CSV Files", "*.csv"));
        currentFilename = fileChooser.showOpenDialog(null);
        System.out.println("Selected output file: " + currentFilename);
        save();
    }

    @FXML
    public void add(){
        data.add(new StudentRecord(newSID.getText(),Double.parseDouble(newAssignment.getText()),
                Double.parseDouble(newMidterm.getText()),Double.parseDouble(newFinal.getText())));
    }

}
