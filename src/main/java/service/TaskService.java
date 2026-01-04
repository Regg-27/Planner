package service;

import domain.Task;
import repository.TaskRepository;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


public class TaskService {
    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        if (taskRepository == null) {
            throw new IllegalArgumentException("TaskRepository must not be null");
        }
        this.taskRepository = taskRepository;
    }

    public Task createTask(String description, int estimatedEffort) {
        UUID id = UUID.randomUUID();
        Task task = new Task(id, description, estimatedEffort);
        taskRepository.save(task);
        return task;
    }
    public Optional<Task> getTaskById(UUID id) {
        return taskRepository.findById(id);
    }
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

}
