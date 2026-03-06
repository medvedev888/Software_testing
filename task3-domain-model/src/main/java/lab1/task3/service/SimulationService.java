package lab1.task3.service;

import lab1.task3.model.FocusPoint;
import lab1.task3.model.Person;
import lab1.task3.model.Robot;

public class SimulationService {

    private final HeadMotionService headService;
    private final RobotMovementService movementService;

    public SimulationService(HeadMotionService headService,
                             RobotMovementService movementService) {
        this.headService = headService;
        this.movementService = movementService;
    }

    public void run(Robot robot, Person person) {

        headService.jerkUp(robot);
        headService.shake(robot);

        movementService.standUp(robot);
        movementService.attemptCrossRoom(robot);

        movementService.stop(robot);

        robot.lookAt(person, new FocusPoint("сквозь левое плечо"));
    }

}