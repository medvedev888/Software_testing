package lab1.task3.model;

public enum Position {
    CORNER("в углу"),
    CENTER("в центре"),
    NEAR_WALL("около стены");

    private final String text;

    Position(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }
}