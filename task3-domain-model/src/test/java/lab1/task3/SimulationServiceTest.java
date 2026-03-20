package lab1.task3;


import lab1.task3.model.Person;
import lab1.task3.service.HeadMotionService;
import lab1.task3.service.RobotFactory;
import lab1.task3.model.Position;
import lab1.task3.model.Robot;
import lab1.task3.service.RobotMovementService;
import lab1.task3.service.SimulationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("Тестирование симуляции сцены")
public class SimulationServiceTest {

    private SimulationService simulation;

    @BeforeEach
    void setUp() {
        simulation = new SimulationService(
                new HeadMotionService(),
                new RobotMovementService()
        );
    }

    @Test
    @DisplayName("Проверка полного сценария движения робота")
    void testSimulationScenario() {
        Robot robot = RobotFactory.createRobot(Position.CORNER);
        Person person = new Person("Триллиан");

        simulation.run(robot, person);

        List<String> expectedTrace = List.of(
                "рывок головой вверх",
                "покачивание головой",
                "робот встал",
                "робот попытался пройти",
                "робот остановился",
                "робот смотрит на сквозь левое плечо Триллиан"
        );

        assertEquals(expectedTrace, robot.getTrace());
    }


    @Test
    @DisplayName("После рывка головы следует покачивание")
    void headMotionOrder() {
        Robot robot = RobotFactory.createRobot(Position.CORNER);
        Person person = new Person("Триллиан");

        simulation.run(robot, person);

        List<String> trace = robot.getTrace();

        int jerkIndex = trace.indexOf("рывок головой вверх");
        int shakeIndex = trace.indexOf("покачивание головой");

        assertTrue(jerkIndex + 1 == shakeIndex);
    }


    @Test
    @DisplayName("После попытки движения робот останавливается")
    void movementLeadsToStop() {
        Robot robot = RobotFactory.createRobot(Position.CORNER);
        Person person = new Person("Триллиан");

        simulation.run(robot, person);

        List<String> trace = robot.getTrace();

        int moveIndex = trace.indexOf("робот попытался пройти");
        int stopIndex = trace.indexOf("робот остановился");

        assertTrue(moveIndex < stopIndex);
    }


    @Test
    @DisplayName("Робот смотрит только после остановки")
    void lookAfterStop() {
        Robot robot = RobotFactory.createRobot(Position.CORNER);
        Person person = new Person("Триллиан");

        simulation.run(robot, person);

        List<String> trace = robot.getTrace();

        int stopIndex = trace.indexOf("робот остановился");
        int lookIndex = trace.indexOf("робот смотрит на сквозь левое плечо Триллиан");

        assertTrue(stopIndex < lookIndex);
    }


    @Test
    @DisplayName("Робот взаимодействует с Триллиан")
    void robotInteractsWithPerson() {
        Robot robot = RobotFactory.createRobot(Position.CORNER);
        Person person = new Person("Триллиан");

        simulation.run(robot, person);

        String lastAction = robot.getTrace().get(5);

        assertTrue(lastAction.contains("Триллиан"));
    }
}