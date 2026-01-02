package service;

import domain.Constraint;
import domain.Task;

import java.util.ArrayList;
import java.util.List;

public class ConstraintEvaluationService {
    public List<Constraint> evaluateConstraints(Task task, List<Constraint> constraints) {
        if (task == null) {
            throw new IllegalArgumentException("Task must not be null");
        }
        if (constraints == null) {
            throw new IllegalArgumentException("Constraints must not be null");
        }

        List<Constraint> violatedConstraints = new ArrayList<>();
        for (Constraint constraint : constraints) {
            switch (constraint.getType()) {
                case ENERGY:
                    if (isEnergyConstraintViolated(task, constraint)) {
                        violatedConstraints.add(constraint);
                    }
                    break;
                case TIME:
                    if (isTimeConstraintViolated(task, constraint)) {
                        violatedConstraints.add(constraint);
                    }
                    break;
                case CONTEXT:
                break;
            }
        }
        return violatedConstraints;
    }

    private boolean isTimeConstraintViolated(Task task, Constraint constraint) {
        return task.getEstimatedEffort() > constraint.getSeverity();
    }

    private boolean isEnergyConstraintViolated(Task task, Constraint constraint) {
        return task.getEstimatedEffort() >= constraint.getSeverity();
    }
}
