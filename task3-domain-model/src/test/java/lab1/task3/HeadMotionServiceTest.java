package lab1.task3;


import lab1.task3.model.Position;
import lab1.task3.model.Robot;
import lab1.task3.service.HeadMotionService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Тестирование движения головы робота")
public class HeadMotionServiceTest {
    private final HeadMotionService service = new HeadMotionService();

    @Test
    @DisplayName("Рывок головы вверх")
    void jerkHeadUp() {
        Robot robot = new Robot(Position.CORNER);
        service.jerkUp(robot);
        assertEquals("рывок головой вверх", robot.getTrace().get(0));
    }

    @Test
    @DisplayName("Покачивание головой")
    void shakeHead() {
        Robot robot = new Robot(Position.CORNER);
        service.shake(robot);
        assertEquals("покачивание головой", robot.getTrace().get(0));
    }

    @Test
    @DisplayName("Ошибка если робот null")
    void robotIsNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            service.jerkUp(null);
        });
    }

    @Test
    @DisplayName("Ошибка если у робота нет головы")
    void robotWithoutHead() {
        Robot robot = new Robot(Position.CORNER);
        robot.setHead(null);
        assertThrows(IllegalStateException.class, () -> {
            service.shake(robot);
        });
    }
}