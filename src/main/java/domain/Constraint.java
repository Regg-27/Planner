package domain;

import java.time.Instant;
import java.util.Objects;
import java.util.UUID;

public final class Constraint {
    private final UUID id;
    private final ConstraintType type;
    private final String description;
    private final int severity;
    private final Instant createdAt;

    public Constraint(UUID id, ConstraintType type, String description, int severity) {
        if (id == null) {
            throw new IllegalArgumentException("Constraint id must not be null");
        }
        if (type == null) {
            throw new IllegalArgumentException("Constraint type must not be null");
        }
        if (description == null || description.isBlank()) {
            throw new IllegalArgumentException("Constraint description must not be blank");
        }
        if (severity < 1 || severity > 5) {
            throw new IllegalArgumentException("Constraint severity must be between 1 and 5");
        }

        this.id = id;
        this.type = type;
        this.description = description;
        this.severity = severity;
        this.createdAt = Instant.now();
    }

    public UUID getId() {
        return id;
    }

    public ConstraintType getType() {
        return type;
    }

    public String getDescription() {
        return description;
    }

    public int getSeverity() {
        return severity;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Constraint)) return false;
        Constraint that = (Constraint) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Constraint{" +
                "id=" + id +
                ", type=" + type +
                ", description='" + description + '\'' +
                ", severity=" + severity +
                ", createdAt=" + createdAt +
                '}';
    }


}
