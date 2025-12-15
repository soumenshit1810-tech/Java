class OfflineLCA {
    List<Integer>[] tree;
    List<int[]>[] queries;
    int[] parent, ans;
    boolean[] visited;

    OfflineLCA(int n, int q) {
        tree = new ArrayList[n];
        queries = new ArrayList[n];
        parent = new int[n];
        ans = new int[q];
        visited = new boolean[n];
        for (int i = 0; i < n; i++) {
            tree[i] = new ArrayList<>();
            queries[i] = new ArrayList<>();
            parent[i] = i;
        }
    }

    void addEdge(int u, int v) {
        tree[u].add(v);
        tree[v].add(u);
    }

    void addQuery(int u, int v, int idx) {
        queries[u].add(new int[]{v, idx});
        queries[v].add(new int[]{u, idx});
    }

    int find(int x) {
        if (parent[x] != x) parent[x] = find(parent[x]);
        return parent[x];
    }

    void dfs(int v, int p) {
        for (int u : tree[v])
            if (u != p) {
                dfs(u, v);
                parent[u] = v;
            }

        visited[v] = true;
        for (int[] q : queries[v]) {
            int u = q[0], idx = q[1];
            if (visited[u])
                ans[idx] = find(u);
        }
    }
}
