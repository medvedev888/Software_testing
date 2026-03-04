package lab1.task2;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("Тестирование обхода графа в глубину")
public class Task2DFSTest {
    private DFS dfs;


    @BeforeEach
    void setUp() {
        dfs = new DFS();
    }


    @Test
    @DisplayName("Проверка пустого графа")
    void testEmptyGraph() {
        List<List<Integer>> graph = List.of();

        assertThrows(IllegalArgumentException.class, () -> {
            dfs.run(graph, 0);
        });
    }


    @Test
    @DisplayName("Стартовая вершина меньше нуля")
    void testNegativeStartVertex() {
        List<List<Integer>> graph = List.of(
                List.of()
        );

        assertThrows(IllegalArgumentException.class, () -> {
            dfs.run(graph, -1);
        });
    }


    @Test
    @DisplayName("Стартовая вершина выходит за пределы графа")
    void testStartVertexOutOfBounds() {
        List<List<Integer>> graph = List.of(
                List.of()
        );

        assertThrows(IllegalArgumentException.class, () -> {
            dfs.run(graph, 5);
        });
    }


    @Test
    @DisplayName("Проверка графа из одной вершины")
    void testSingleVertexGraph() {
        List<List<Integer>> graph = List.of(
                List.of()
        );

        dfs.run(graph, 0);
        List<String> actualTrace = dfs.getTracePoints();

        List<String> expectedTrace = List.of(
                "T1(start=0)",
                "T2(enter=0)",
                "T3(mark=0)",
                "T6(exit=0)",
                "T7(end)"
        );

        assertEquals(expectedTrace, actualTrace);
    }


    @Test
    @DisplayName("Проверка обычного графа")
    void testTheSimpleGraph() {
        List<List<Integer>> graph = List.of(
                List.of(1, 2),
                List.of(0, 3),
                List.of(0),
                List.of(1)
        );

        List<String> expectedTrace = List.of(
                "T1(start=0)",
                "T2(enter=0)",
                "T3(mark=0)",
                "T4(check 0→1)",
                "T5(recurse 1)",
                "T2(enter=1)",
                "T3(mark=1)",
                "T4(check 1→0)",
                "T4(check 1→3)",
                "T5(recurse 3)",
                "T2(enter=3)",
                "T3(mark=3)",
                "T4(check 3→1)",
                "T6(exit=3)",
                "T6(exit=1)",
                "T4(check 0→2)",
                "T5(recurse 2)",
                "T2(enter=2)",
                "T3(mark=2)",
                "T4(check 2→0)",
                "T6(exit=2)",
                "T6(exit=0)",
                "T7(end)"
        );

        dfs.run(graph, 0);
        List<String> actualTrace = dfs.getTracePoints();
        assertEquals(expectedTrace, actualTrace);
    }


    @Test
    @DisplayName("Проверка несвязного графа")
    void testDisconnectedGraph() {
        List<List<Integer>> graph = List.of(
                List.of(1),
                List.of(0),
                List.of(3),
                List.of(2)
        );

        dfs.run(graph, 0);
        List<String> actualTrace = dfs.getTracePoints();

        List<String> expectedTrace = List.of(
                "T1(start=0)",
                "T2(enter=0)",
                "T3(mark=0)",
                "T4(check 0→1)",
                "T5(recurse 1)",
                "T2(enter=1)",
                "T3(mark=1)",
                "T4(check 1→0)",
                "T6(exit=1)",
                "T6(exit=0)",
                "T7(end)"
        );

        assertEquals(expectedTrace, actualTrace);
    }


    @Test
    @DisplayName("Проверка графа с циклом")
    void testGraphWithCycle() {
        List<List<Integer>> graph = List.of(
                List.of(1),
                List.of(2),
                List.of(0)
        );

        dfs.run(graph, 0);
        List<String> actualTrace = dfs.getTracePoints();

        List<String> expectedTrace = List.of(
                "T1(start=0)",
                "T2(enter=0)",
                "T3(mark=0)",
                "T4(check 0→1)",
                "T5(recurse 1)",
                "T2(enter=1)",
                "T3(mark=1)",
                "T4(check 1→2)",
                "T5(recurse 2)",
                "T2(enter=2)",
                "T3(mark=2)",
                "T4(check 2→0)",
                "T6(exit=2)",
                "T6(exit=1)",
                "T6(exit=0)",
                "T7(end)"
        );

        assertEquals(expectedTrace, actualTrace);
    }

}
