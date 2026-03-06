package lab1.task3.service;

import lab1.task3.model.Robot;
import lab1.task3.model.RobotState;

public class RobotMovementService {

    public void standUp(Robot robot) {
        validateRobot(robot);

        if (robot.getState() == RobotState.STANDING) {
            throw new IllegalStateException("Робот уже стоит");
        }

        if (robot.getState() == RobotState.MOVING) {
            throw new IllegalStateException("Робот уже движется");
        }

        robot.standUp();
    }

    public void attemptCrossRoom(Robot robot) {
        validateRobot(robot);

        if (robot.getState() == RobotState.SITTING) {
            throw new IllegalStateException("Робот должен встать перед движением");
        }

        if (robot.getState() == RobotState.MOVING) {
            throw new IllegalStateException("Робот уже движется");
        }

        robot.attemptWalk();
    }

    public void stop(Robot robot) {
        validateRobot(robot);

        if (robot.getState() == RobotState.STOPPED) {
            throw new IllegalStateException("Робот уже остановился");
        }

        robot.stop();
    }

    private void validateRobot(Robot robot) {
        if (robot == null) {
            throw new IllegalArgumentException("Робот не может быть null");
        }
    }

}
