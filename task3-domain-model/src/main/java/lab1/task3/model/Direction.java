package lab1.task3.model;

public enum Direction {
    LEFT("влево"),
    RIGHT("вправо"),
    UP("вверх"),
    DOWN("вниз");

    private final String text;

    Direction(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }
}
