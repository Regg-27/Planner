package service;

import domain.Constraint;
import domain.ConstraintType;
import domain.Task;
import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.UUID;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class ConstraintEvaluationServiceTest {
    @Test
    void constraintIsViolatedWhenTaskEffortExceedsSeverity() {
        Task task = new Task(UUID.randomUUID(), "Write final report", 5);
        Constraint constraint = new Constraint(UUID.randomUUID(), ConstraintType.ENERGY, "Low energy day", 3);
        ConstraintEvaluationService service = new ConstraintEvaluationService();
        List<Constraint> violations = service.evaluateConstraints(task, List.of(constraint));
        assertEquals(1, violations.size());
    }
    @Test
    void constraintIsNotViolatedWhenTaskEffortDoesNotExceedSeverity() {
        Task task = new Task(UUID.randomUUID(), "Light reading", 2);
        Constraint constraint = new Constraint(UUID.randomUUID(), ConstraintType.ENERGY, "Moderate energy day", 3);
        ConstraintEvaluationService service = new ConstraintEvaluationService();
        List<Constraint> violations = service.evaluateConstraints(task, List.of(constraint));
        assertEquals(0, violations.size());
    }
    @Test
    void timeAndEnergyConstraintsAreEvaluatedDifferently() {
        Task task = new Task(UUID.randomUUID(), "Intense work session", 3);
        Constraint energyConstraint = new Constraint(UUID.randomUUID(), ConstraintType.ENERGY, "Low energy day", 3);
        Constraint timeConstraint = new Constraint(UUID.randomUUID(), ConstraintType.TIME, "Tight schedule", 3);
        ConstraintEvaluationService service = new ConstraintEvaluationService();
        List<Constraint> violations = service.evaluateConstraints(task, List.of(energyConstraint, timeConstraint));
        assertEquals(1, violations.size());
        assertEquals(timeConstraint, violations.get(0));
    }


}
