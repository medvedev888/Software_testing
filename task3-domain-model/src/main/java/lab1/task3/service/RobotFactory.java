package lab1.task3.service;

import lab1.task3.model.Position;
import lab1.task3.model.Robot;

public class RobotFactory {

    public static Robot createRobot(Position position) {
        return new Robot(position);
    }

}