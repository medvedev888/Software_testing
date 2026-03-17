package lab1.task1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ArctgTest {

    private static final int TERMS = 200;
    private static final double EPS = 1e-6;
    private static final double BORDER_EPS = 2e-3;

    @Test
    @DisplayName("arctg(0) должен быть равен 0")
    void shouldReturnZeroForZeroArgument() {
        double actual = TaylorSeries.arctg(0.0, TERMS);
        assertEquals(0.0, actual, EPS);
    }

    @Test
    @DisplayName("arctg(1) должен быть равен π/4")
    void shouldReturnPiOverFourForOne() {
        double actual = TaylorSeries.arctg(1.0, TERMS);
        assertEquals(Math.PI / 4, actual, BORDER_EPS);
    }

    @Test
    @DisplayName("arctg(-1) должен быть равен -π/4")
    void shouldReturnMinusPiOverFourForMinusOne() {
        double actual = TaylorSeries.arctg(-1.0, TERMS);
        assertEquals(-Math.PI / 4, actual, BORDER_EPS);
    }

    @Test
    @DisplayName("arctg(√3/3) должен быть равен π/6")
    void shouldReturnPiOverSixForSqrt3Div3() {
        double argument = Math.sqrt(3) / 3;
        double actual = TaylorSeries.arctg(argument, TERMS);
        assertEquals(Math.PI / 6, actual, EPS);
    }

    @Test
    @DisplayName("arctg(-√3/3) должен быть равен -π/6")
    void shouldReturnMinusPiOverSixForMinusSqrt3Div3() {
        double argument = -Math.sqrt(3) / 3;
        double actual = TaylorSeries.arctg(argument, TERMS);
        assertEquals(-Math.PI / 6, actual, EPS);
    }

    @Test
    @DisplayName("arctg(-√3) должен быть равен -π/3")
    void shouldReturnPiOverThreeForSqrt3() {
        double argument = Math.sqrt(3);
        double actual = TaylorSeries.arctg(argument, TERMS);
        assertEquals(Math.PI / 3, actual, EPS);
    }

    @Test
    @DisplayName("arctg(argument) должен быть нечётной функцией при |argument| <= 1")
    void shouldReturnMinusPiOverThreeForMinusSqrt3() {
        double argument = -Math.sqrt(3);
        double actual = TaylorSeries.arctg(argument, TERMS);
        assertEquals(-Math.PI / 3, actual, EPS);
    }

    @Test
    @DisplayName("arctg(argument) должен быть нечётной функцией при |argument| > 1")
    void shouldBeOddFunctionInsideMainInterval() {
        double argument = Math.sqrt(3) / 3;
        double positive = TaylorSeries.arctg(argument, TERMS);
        double negative = TaylorSeries.arctg(-argument, TERMS);

        assertEquals(-positive, negative, EPS);
    }

    @Test
    @DisplayName("arctg(argument) должен быть нечётной функцией при |argument| > 1")
    void shouldBeOddFunctionOutsideMainInterval() {
        double argument = Math.sqrt(3);
        double positive = TaylorSeries.arctg(argument, TERMS);
        double negative = TaylorSeries.arctg(-argument, TERMS);

        assertEquals(-positive, negative, EPS);
    }

    @Test
    @DisplayName("Должно выбрасываться исключение при отрицательном количестве членов ряда!")
    void shouldThrowExceptionForZeroTerms() {
        assertThrows(IllegalArgumentException.class, () -> TaylorSeries.arctg(0.5, 0));
    }

    @Test
    @DisplayName("Должно выбрасываться исключение, если аргумент равен NaN!")
    void shouldThrowExceptionForNegativeTerms() {
        assertThrows(IllegalArgumentException.class, () -> TaylorSeries.arctg(0.5, -5));
    }

    @Test
    @DisplayName("Должно выбрасываться исключение, если аргумент равен NaN!")
    void shouldThrowExceptionForNaNArgument() {
        assertThrows(IllegalArgumentException.class, () -> TaylorSeries.arctg(Double.NaN, TERMS));
    }

    @Test
    @DisplayName("Должно выбрасываться исключение, если аргумент равен +Infinity!")
    void shouldThrowExceptionForPositiveInfinityArgument() {
        assertThrows(IllegalArgumentException.class,
                () -> TaylorSeries.arctg(Double.POSITIVE_INFINITY, TERMS));
    }

    @Test
    @DisplayName("Должно выбрасываться исключение, если аргумент равен -Infinity")
    void shouldThrowExceptionForNegativeInfinityArgument() {
        assertThrows(IllegalArgumentException.class,
                () -> TaylorSeries.arctg(Double.NEGATIVE_INFINITY, TERMS));
    }
}