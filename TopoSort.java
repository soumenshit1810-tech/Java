import java.util.*;

class TopoSort {
    void dfs(int node, boolean[] visited, Stack<Integer> st, List<List<Integer>> g) {
        visited[node] = true;

        for (int v : g.get(node))
            if (!visited[v]) dfs(v, visited, st, g);

        st.push(node);
    }

    void topo(int n, List<List<Integer>> g) {
        boolean[] visited = new boolean[n];
        Stack<Integer> st = new Stack<>();

        for (int i = 0; i < n; i++)
            if (!visited[i]) dfs(i, visited, st, g);

        while (!st.isEmpty())
            System.out.print(st.pop() + " ");
    }
}
