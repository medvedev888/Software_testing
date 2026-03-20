package lab1.task2;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("Тестирование обхода графа в глубину")
public class DFSTest {
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


    @Test
    @DisplayName("Проверка сложного графа с множественными ветвлениями")
    void testComplexGraph() {
        List<List<Integer>> graph = List.of(
                List.of(1, 2, 3),
                List.of(4, 5),
                List.of(6),
                List.of(7, 8),
                List.of(),
                List.of(9),
                List.of(),
                List.of(),
                List.of(),
                List.of()
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

                "T4(check 1→4)",
                "T5(recurse 4)",
                "T2(enter=4)",
                "T3(mark=4)",
                "T6(exit=4)",

                "T4(check 1→5)",
                "T5(recurse 5)",
                "T2(enter=5)",
                "T3(mark=5)",

                "T4(check 5→9)",
                "T5(recurse 9)",
                "T2(enter=9)",
                "T3(mark=9)",
                "T6(exit=9)",
                "T6(exit=5)",

                "T6(exit=1)",

                "T4(check 0→2)",
                "T5(recurse 2)",
                "T2(enter=2)",
                "T3(mark=2)",

                "T4(check 2→6)",
                "T5(recurse 6)",
                "T2(enter=6)",
                "T3(mark=6)",
                "T6(exit=6)",
                "T6(exit=2)",

                "T4(check 0→3)",
                "T5(recurse 3)",
                "T2(enter=3)",
                "T3(mark=3)",

                "T4(check 3→7)",
                "T5(recurse 7)",
                "T2(enter=7)",
                "T3(mark=7)",
                "T6(exit=7)",

                "T4(check 3→8)",
                "T5(recurse 8)",
                "T2(enter=8)",
                "T3(mark=8)",
                "T6(exit=8)",

                "T6(exit=3)",
                "T6(exit=0)",
                "T7(end)"
        );

        assertEquals(expectedTrace, actualTrace);
    }

}
