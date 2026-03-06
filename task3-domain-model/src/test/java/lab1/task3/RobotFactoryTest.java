package lab1.task3;

import lab1.task3.service.RobotFactory;
import lab1.task3.model.Position;
import lab1.task3.model.Robot;
import lab1.task3.model.RobotState;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Тестирование фабрики роботов")
public class RobotFactoryTest {

    @Test
    @DisplayName("Создание робота в углу комнаты")
    void createRobotInCorner() {
        Robot robot = RobotFactory.createRobot(Position.CORNER);

        assertNotNull(robot);
        assertEquals(Position.CORNER, robot.getPosition());
        assertEquals(RobotState.SITTING, robot.getState());
        assertNotNull(robot.getHead());
    }

}