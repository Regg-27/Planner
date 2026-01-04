package repository.impl;

import domain.Task;
import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import static org.junit.jupiter.api.Assertions.*;

class FileTaskRepositoryTest {
    @Test
    void savedTaskCanBeRetrievedById() {
        Path tempFile = Files.createTempFile("tasks", ".json");
        FileTaskRepository repository = new FileTaskRepository(tempFile);

        UUID id = UUID.randomUUID();
        Task task = new Task(id, "Write persistence layer", 4);

        repository.save(task);
        Optional<Task> result = repository.findById(id);

        assertTrue(result.isPresent());
        assertEquals(task, result.get());
    }
    @Test
    void findAllReturnsAllSavedTasks() {
        Path tempFile = Files.createTempFile("tasks", ".json");
        FileTaskRepository repository = new FileTaskRepository(tempFile);

        Task task1 = new Task(UUID.randomUUID(), "Task one", 2);
        Task task2 = new Task(UUID.randomUUID(), "Task two", 3);

        repository.save(task1);
        repository.save(task2);

        List<Task> tasks = repository.findAll();
        assertEquals(2, tasks.size());

        assertTrue(tasks.contains(task1));
        assertTrue(tasks.contains(task2));

    }

}
