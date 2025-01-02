package javier.tasks;

import javafx.application.Application;
import javier.tasks.presentation.TaskSystemFX;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TasksApplication {

	public static void main(String[] args) {
		Application.launch(TaskSystemFX.class, args);
	}

}
