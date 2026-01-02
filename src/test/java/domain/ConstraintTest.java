package domain;

import org.junit.jupiter.api.Test;
import java.util.UUID;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ConstraintTest {
    @Test
    void constraintCannotBeCreatedWithInvalidSeverity() {
        UUID id = UUID.randomUUID();

        assertThrows(IllegalArgumentException.class, () -> {
            new Constraint(id, ConstraintType.TIME, "Too much work", 0);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            new Constraint(id, ConstraintType.TIME, "Too much work", 6);
        });
    }

    @Test
    void constraintMustHaveAType() {
        UUID id = UUID.randomUUID();

        assertThrows(IllegalArgumentException.class, () -> {
            new Constraint(id, null, "No type provided", 3);
        });
    }
}
