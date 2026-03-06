package lab1.task3;

import lab1.task3.model.Position;
import lab1.task3.model.Robot;
import lab1.task3.model.RobotState;
import lab1.task3.service.RobotMovementService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Тестирование движения робота")
public class RobotMovementServiceTest {
    private final RobotMovementService service = new RobotMovementService();

    @Test
    @DisplayName("Робот может встать если сидит")
    void robotStandUp() {
        Robot robot = new Robot(Position.CORNER);
        service.standUp(robot);
        assertEquals(RobotState.STANDING, robot.getState());
    }

    @Test
    @DisplayName("Робот не может встать повторно")
    void robotStandUpTwice() {
        Robot robot = new Robot(Position.CORNER);
        service.standUp(robot);
        assertThrows(IllegalStateException.class, () -> {
            service.standUp(robot);
        });
    }

    @Test
    @DisplayName("Попытка пересечь комнату возможна только стоя")
    void attemptMove() {
        Robot robot = new Robot(Position.CORNER);
        service.standUp(robot);
        service.attemptCrossRoom(robot);
        assertEquals(RobotState.MOVING, robot.getState());
    }

    @Test
    @DisplayName("Остановка робота")
    void stopRobot() {
        Robot robot = new Robot(Position.CORNER);
        service.standUp(robot);
        service.attemptCrossRoom(robot);
        service.stop(robot);
        assertEquals(RobotState.STOPPED, robot.getState());
    }

    @Test
    @DisplayName("Ошибка если робот null")
    void robotIsNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            service.standUp(null);
        });
    }

    @Test
    @DisplayName("Нельзя начать движение если робот сидит")
    void attemptMoveWhileSitting() {
        Robot robot = new Robot(Position.CORNER);

        assertThrows(IllegalStateException.class, () -> {
            service.attemptCrossRoom(robot);
        });
    }

    @Test
    @DisplayName("Нельзя начать движение если робот уже движется")
    void attemptMoveWhileMoving() {
        Robot robot = new Robot(Position.CORNER);

        service.standUp(robot);
        service.attemptCrossRoom(robot);

        assertThrows(IllegalStateException.class, () -> {
            service.attemptCrossRoom(robot);
        });
    }

    @Test
    @DisplayName("Нельзя встать если уже стоит")
    void standUpWhenAlreadyStanding() {
        Robot robot = new Robot(Position.CORNER);
        service.standUp(robot);
        assertThrows(IllegalStateException.class, () -> {
            service.standUp(robot);
        });
    }

    @Test
    @DisplayName("Нельзя остановить уже остановленного робота")
    void stopWhenAlreadyStopped() {
        Robot robot = new Robot(Position.CORNER);

        service.standUp(robot);
        service.attemptCrossRoom(robot);
        service.stop(robot);

        assertThrows(IllegalStateException.class, () -> {
            service.stop(robot);
        });
    }

}