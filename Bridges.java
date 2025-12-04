import java.util.*;

class Bridges {
    int time = 0;
    List<List<Integer>> graph;
    boolean[] visited;
    int[] disc, low;

    Bridges(int n) {
        graph = new ArrayList<>();
        for (int i = 0; i < n; i++) graph.add(new ArrayList<>());
        visited = new boolean[n];
        disc = new int[n];
        low = new int[n];
    }

    void addEdge(int u, int v) {
        graph.get(u).add(v);
        graph.get(v).add(u);
    }

    void dfs(int u, int parent) {
        visited[u] = true;
        disc[u] = low[u] = ++time;

        for (int v : graph.get(u)) {
            if (v == parent) continue;

            if (!visited[v]) {
                dfs(v, u);
                low[u] = Math.min(low[u], low[v]);

                if (low[v] > disc[u]) {
                    System.out.println("Bridge: " + u + " - " + v);
                }
            } else {
                low[u] = Math.min(low[u], disc[v]);
            }
        }
    }
}
