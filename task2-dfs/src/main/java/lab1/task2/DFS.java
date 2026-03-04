package lab1.task2;

import java.util.ArrayList;
import java.util.List;

public class DFS {
    private List<List<Integer>> graph;
    private List<String> tracePoints;
    private boolean[] visited;

    public List<String> run(List<List<Integer>> graph, int start) {
        if (graph == null) {
            throw new IllegalArgumentException("Graph must not be null");
        }

        if (graph.isEmpty()) {
            throw new IllegalArgumentException("Graph must not be empty");
        }

        if (start < 0 || start >= graph.size()) {
            throw new IllegalArgumentException(
                    "Start vertex " + start + " is out of bounds. Graph size: " + graph.size()
            );
        }

        this.graph = graph;
        this.tracePoints = new ArrayList<>();
        this.visited = new boolean[graph.size()];

        tracePoints.add("T1(start=" + start + ")");
        dfs(start);
        tracePoints.add("T7(end)");

        return tracePoints;
    }

    public List<String> getTracePoints() {
        return tracePoints;
    }

    private void dfs(int v) {
        tracePoints.add("T2(enter=" + v + ")");
        visited[v] = true;
        tracePoints.add("T3(mark=" + v + ")");

        for(int neighbor : graph.get(v)) {
            tracePoints.add("T4(check " + v + "→" + neighbor + ")");
            if (!visited[neighbor]) {
                tracePoints.add("T5(recurse " + neighbor + ")");
                dfs(neighbor);
            }
        }
        tracePoints.add("T6(exit=" + v + ")");
    }
}