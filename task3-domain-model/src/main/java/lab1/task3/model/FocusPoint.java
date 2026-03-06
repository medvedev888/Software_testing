package lab1.task3.model;

public class FocusPoint {

    private final String description;

    public FocusPoint(String description) {
        if (description == null || description.isBlank()) {
            throw new IllegalArgumentException("Описание точки фокуса не может быть пустым или null");
        }

        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}