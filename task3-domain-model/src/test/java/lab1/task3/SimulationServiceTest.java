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
}