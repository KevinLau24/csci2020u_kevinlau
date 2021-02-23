package sample;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

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

    private TableView<StudentRecord> people;

    @FXML
    public void initialize(){
        SID.setCellValueFactory(new PropertyValueFactory<>("SID"));
        assignment.setCellValueFactory(new PropertyValueFactory<>("assignment"));
        midterm.setCellValueFactory(new PropertyValueFactory<>("midterm"));
        fExam.setCellValueFactory(new PropertyValueFactory<>("fExam"));
        fMark.setCellValueFactory(new PropertyValueFactory<>("fMark"));
        lGrade.setCellValueFactory(new PropertyValueFactory<>("lGrade"));
        tableView.setItems(DataSource.getAllMarks());
    }

}
