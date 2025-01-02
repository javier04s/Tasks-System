package javier.tasks.services;

import javier.tasks.models.Task;

import java.util.List;

public interface iTaskService {
    public List<Task> listTasks();

    public Task searchTaskById(int taskId);

    public void saveTask(Task task);

    public void deleteTask(Task task);
}
