package org.example.demo;

import javafx.scene.control.cell.PropertyValueFactory;
import org.example.demo.combiner.JavaFXMethods;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import org.example.demo.combiner.CourseTeacherClassroom;

import java.io.IOException;
import java.util.List;
import java.util.Properties;

public class SecondMenuController {

    public Button b2;
    public HBox centerHbox;
    public Pane paneLeft;
    public Pane paneRight;

    final ObservableList<CourseTeacherClassroom> ctc = FXCollections.observableArrayList();
    @FXML
    private TableView table;
    @FXML
    private TableColumn courseCol = new TableColumn("Course");
    @FXML
    private TableColumn teacherCol = new TableColumn("Teacher");
    @FXML
    private TableColumn classroomCol = new TableColumn("Classroom");

    @FXML
    private TextArea textArea;

    public void initialize() {
        showMenu();
        table = new TableView<>();

        table.getColumns().addAll(courseCol, teacherCol, classroomCol);

        courseCol.setCellValueFactory(
                new PropertyValueFactory<CourseTeacherClassroom, String>("courseName")
        );
        teacherCol.setCellValueFactory(
                new PropertyValueFactory<CourseTeacherClassroom, String>("teacherName")
        );
        classroomCol.setCellValueFactory(
                new PropertyValueFactory<CourseTeacherClassroom, String>("classroomName")
        );
        table.setItems(CourseTeacherClassroom.collectedCombinators);
    }

    public void showMenu() {
        textArea.setText(JavaFXMethods.readMenu());
    }


    public void newMenu(MouseEvent mouseEvent) throws IOException {
        Button button = (Button) mouseEvent.getSource();

        switch (button.getId()) {
            case "b1" -> Platform.runLater(() -> {
                showb1(JavaFXMethods.showCoursesFX());
            });
            case "b2" -> Platform.runLater(() -> {
                showb2();
            });
            case "Back" -> returnToMain();
        }
    }

    private void showb1(List<String> strings) {
        StringBuilder str = new StringBuilder();
        strings.forEach((e) -> str.append(e + "\n"));
        textArea.setText(String.valueOf(str));
    }

    private void returnToMain() throws IOException {
        MainMenuController.loadMain();
    }

    private void showb2() {
        int indexOld = centerHbox.getChildren().indexOf(textArea);
        if (indexOld >= 0) {
            centerHbox.getChildren().remove(textArea);
            centerHbox.getChildren().set(indexOld, table);
            centerHbox.alignmentProperty();
        }
    }


}
