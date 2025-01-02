package javier.tasks.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javier.tasks.models.Task;
import javier.tasks.services.TaskService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.ResourceBundle;

@Component
public class IndexController implements Initializable {

    private static final Logger logger = LoggerFactory.getLogger(IndexController.class);

    @Autowired
    private TaskService taskService;

    @FXML
    private TableView<Task> taskTable;

    @FXML
    private TableColumn<Task, Integer> taskIdColumn;

    @FXML
    private TableColumn<Task, String> taskNameColumn;

    @FXML
    private TableColumn<Task, String> managerColumn;

    @FXML
    private TableColumn<Task, Integer> statusColumn;

    private final ObservableList<Task> listTasks = FXCollections.observableArrayList();

    @FXML
    private TextField nameText;

    @FXML
    private TextField managerText;

    @FXML
    private TextField statusText;

    private Integer internalTaskId;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        taskTable.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        configureColumns();
        showTasks();
    }

    private void configureColumns() {
        taskIdColumn.setCellValueFactory(new PropertyValueFactory<>("taskId"));
        taskNameColumn.setCellValueFactory(new PropertyValueFactory<>("taskName"));
        managerColumn.setCellValueFactory(new PropertyValueFactory<>("taskManager"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
    }

    private void showTasks() {
        listTasks.clear();
        listTasks.addAll(taskService.listTasks());
        taskTable.setItems(listTasks);
    }

    private void showMessage(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void collectFormData(Task task) {
        if (internalTaskId != null) {
            task.setTaskId(internalTaskId);
        }
        task.setTaskName(nameText.getText());
        task.setTaskManager(managerText.getText());
        task.setStatus(statusText.getText());
    }

    @FXML
    private void clearForm() {
        internalTaskId = null;
        nameText.clear();
        managerText.clear();
        statusText.clear();
    }

    public void loadFormTask() {
        var task = taskTable.getSelectionModel().getSelectedItem();
        if (task != null) {
            internalTaskId = task.getTaskId();
            nameText.setText(task.getTaskName());
            managerText.setText(task.getTaskManager());
            statusText.setText(task.getStatus());
        }
    }

    public void addTask() {
        if (nameText.getText().isEmpty()) {
            showMessage("Error Validacion", "Debe proporcionar una tarea");
            nameText.requestFocus();
            return;
        } else {
            var task = new Task();
            collectFormData(task);
            taskService.saveTask(task);
            showMessage("Informacion", "Tarea agregada con exito");
            clearForm();
            showTasks();
        }
    }

    public void editTask() {
        if (internalTaskId == null) {
            showMessage("Informacion", "Debe seleccionar una tarea");
            return;
        }
        if (nameText.getText().isEmpty()) {
            showMessage("Error Validacion", "Debe proporcionar una tarea");
            nameText.requestFocus();
            return;
        }

        var task = new Task();
        collectFormData(task);
        taskService.saveTask(task);
        showMessage("Informacion", "Tarea modificada con exito");
        clearForm();
        showTasks();
    }

    public void deleteTask() {
        var task = taskTable.getSelectionModel().getSelectedItem();
        if (task != null) {
            taskService.deleteTask(task);
            showMessage("Informacion", "Tarea eliminada con exito");
            clearForm();
            showTasks();
        } else {
            showMessage("Error Validacion", "Debe seleccionar una tarea");
        }
    }
}
