package repository.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import domain.Task;
import repository.TaskRepository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import static com.sun.tools.jdeprscan.DeprDB.loadFromFile;


public class FileTaskRepository implements TaskRepository {
    private final Map<UUID, Task> tasks = new HashMap<>();
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final Path filePath;

    public FileTaskRepository(Path filePath) {
        this.filePath = filePath;
        loadFromFile();
    }


    @Override
    public void save(Task task) {
        if (task == null) {
            throw new IllegalArgumentException("Task must not be null");
        }
        tasks.put(task.getId(), task);
        writeToFile();
    }

    @Override
    public Optional<Task> findById(UUID id) {
        if (id == null) {
            throw new IllegalArgumentException("Task ID must not be null");
        }
        return Optional.ofNullable(tasks.get(id));
    }

    @Override
    public List<Task> findAll() {
        return new ArrayList<>(tasks.values());
    }
    private void loadFromFile() {
        if (!Files.exists(filePath)) {
            return;
        }

        try {
            Task[] loadedTasks =
                    objectMapper.readValue(filePath.toFile(), Task[].class);

            for (Task task : loadedTasks) {
                tasks.put(task.getId(), task);
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to load tasks from file", e);
        }
    }
    private void writeToFile() {
        try {
            objectMapper.writeValue(filePath.toFile(), tasks.values());
        } catch (IOException e) {
            throw new RuntimeException("Failed to write tasks to file", e);
        }
    }


}
