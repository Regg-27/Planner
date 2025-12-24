package domain;

import org.junit.jupiter.api.Test;
import java.util.UUID;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TaskTest {
    @Test
    void taskCannotBeCreatedWithNonPositiveEffort() {
        UUID id = UUID.randomUUID();

        assertThrows(IllegalArgumentException.class, () ->
                new Task(id, "Test task", 0)
        );
    }

    @Test
    void taskCannotBeCreatedWithBlankDescription() {
        UUID id = UUID.randomUUID();

        assertThrows(IllegalArgumentException.class, () ->
                new Task(id, "   ", 5)
        );
    }

}
