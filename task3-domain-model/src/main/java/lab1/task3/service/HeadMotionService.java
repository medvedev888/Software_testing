package lab1.task3.service;

import lab1.task3.model.Direction;
import lab1.task3.model.Robot;

public class HeadMotionService {

    public void jerkUp(Robot robot) {
        validateRobot(robot);

        robot.getHead().jerk(Direction.UP);
    }

    public void shake(Robot robot) {
        validateRobot(robot);

        robot.getHead().shakingHead();
    }

    private void validateRobot(Robot robot) {
        if (robot == null) {
            throw new IllegalArgumentException("Робот не может быть null");
        }

        if (robot.getHead() == null) {
            throw new IllegalStateException("Робот не имеет головы");
        }
    }

}