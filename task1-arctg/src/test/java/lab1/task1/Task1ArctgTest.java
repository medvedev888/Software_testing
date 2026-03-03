package lab1.task1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Taylor series: arctg(x)")
public class Task1ArctgTest {

    private static final int TERMS = 200;
    private static final double EPS = 1e-8;

    @Test
    @DisplayName("terms must be positive")
    void shouldThrowForNonPositiveTerms() {
        assertThrows(IllegalArgumentException.class, () -> TaylorSeries.arctg(0.3, 0));
        assertThrows(IllegalArgumentException.class, () -> TaylorSeries.arctg(0.3, -5));
    }

    @Test
    @DisplayName("basic known points: x=0, x=±1")
    void shouldMatchKnownPoints() {
        assertEquals(0.0, TaylorSeries.arctg(0.0, TERMS), 1e-12);

        assertEquals(Math.PI / 4.0, TaylorSeries.arctg(1.0, TERMS), 1e-6);
        assertEquals(-Math.PI / 4.0, TaylorSeries.arctg(-1.0, TERMS), 1e-6);
    }

    @Test
    @DisplayName("should be close to Math.atan(x) for a set of values (including |x|>1 branches)")
    void shouldMatchMathAtanForTypicalValues() {
        double[] xs = {-10.0, -2.0, -1.0, -0.9, -0.5, -0.2, 0.0, 0.2, 0.5, 0.9, 1.0, 2.0, 10.0};

        for (double x : xs) {
            double expected = Math.atan(x);
            double actual = TaylorSeries.arctg(x, TERMS);
            assertEquals(expected, actual, EPS, "x=" + x);
        }
    }

    @Test
    @DisplayName("odd function property: arctg(-x) = -arctg(x)")
    void shouldBeOddFunction() {
        double[] xs = {-10.0, -2.5, -1.2, -1.0, -0.7, -0.1, 0.1, 0.7, 1.0, 1.2, 2.5, 10.0};

        for (double x : xs) {
            double a = TaylorSeries.arctg(x, TERMS);
            double b = TaylorSeries.arctg(-x, TERMS);
            assertEquals(-a, b, 1e-9, "x=" + x);
        }
    }

    @Test
    @DisplayName("accuracy should improve when increasing terms (inside |x|<=1)")
    void accuracyShouldImproveWhenTermsIncrease() {
        double x = 0.8;
        double expected = Math.atan(x);

        double err10 = Math.abs(TaylorSeries.arctg(x, 10) - expected);
        double err30 = Math.abs(TaylorSeries.arctg(x, 30) - expected);
        double err80 = Math.abs(TaylorSeries.arctg(x, 80) - expected);

        assertTrue(err30 < err10, "terms=30 should be better than terms=10");
        assertTrue(err80 < err30, "terms=80 should be better than terms=30");
    }

    @Test
    @DisplayName("monotonicity smoke test")
    void monotonicitySmokeTest() {
        double a = TaylorSeries.arctg(-0.5, TERMS);
        double b = TaylorSeries.arctg(0.0, TERMS);
        double c = TaylorSeries.arctg(0.5, TERMS);

        assertTrue(a < b);
        assertTrue(b < c);
    }
}