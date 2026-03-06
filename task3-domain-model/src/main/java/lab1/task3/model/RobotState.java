package lab1.task3.model;

public enum RobotState {
    SITTING("сидит"),
    STANDING("стоит"),
    MOVING("движется"),
    STOPPED("остановлен");

    private final String text;

    RobotState(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }
}