package lab1.task3.model;

public class Head {
    private final Robot robot;

    public Head(Robot robot) {
        this.robot = robot;
    }

    public void jerk(Direction direction) {
        robot.addTrace("рывок головой " + direction.toString());
    }

    public void shakingHead() {
        robot.addTrace("покачивание головой");
    }
}
