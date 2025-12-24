package domain;

import java.time.Instant;
import java.util.Objects;
import java.util.UUID;

public class Task {
    private final UUID id;
    private final String description;
    private final int estimatedEffort;
    private final Instant createdAt;

    public Task(UUID id, String description, int estimatedEffort) {
        if (id == null) {
            throw new IllegalArgumentException("Task ID must be null");
        }
        if (description == null) {
            throw new IllegalArgumentException("Task description must not be null");
        }
        if (estimatedEffort <= 0) {
            throw new IllegalArgumentException("Estimated effort must be positive");
        }

        this.id = id;
        this.description = description;
        this.estimatedEffort = estimatedEffort;
        this.createdAt = Instant.now();
    }

    public UUID getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public int getEstimatedEffort() {
        return estimatedEffort;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Task)) return false;
        Task task = (Task) o;
        return id.equals(task.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", estimatedEffort=" + estimatedEffort +
                ", createdAt=" + createdAt +
                '}';
    }
}
