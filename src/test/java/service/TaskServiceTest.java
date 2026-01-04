package service;

import domain.Task;
import repository.impl.FileTaskRepository;
import org.junit.jupiter.api.Test;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;

public class TaskServiceTest {
    @Test
    void createdTaskIsPersistedAndRetrievable() throws Exception {
        Path tempFile = Files.createTempFile("tasks", ".json");
        FileTaskRepository repository = new FileTaskRepository(tempFile);
        TaskService taskService = new TaskService(repository);
        Task createdTask = taskService.createTask("Write TaskService test", 3);

        assertNotNull(createdTask);
        Optional<Task> retrieved = taskService.getTaskById(createdTask.getId());
        assertTrue(retrieved.isPresent());
        assertEquals(createdTask, retrieved.get());


    }

}
