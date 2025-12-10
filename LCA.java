class LCA {
    int N, LOG;
    int[][] up;
    int[] depth;
    List<Integer>[] g;

    LCA(int n) {
        N = n;
        LOG = 32 - Integer.numberOfLeadingZeros(n);
        up = new int[LOG][n];
        depth = new int[n];
        g = new ArrayList[n];
        for (int i = 0; i < n; i++) g[i] = new ArrayList<>();
    }

    void addEdge(int u, int v) {
        g[u].add(v);
        g[v].add(u);
    }

    void dfs(int v, int p) {
        up[0][v] = p;
        for (int i : g[v]) if (i != p) {
            depth[i] = depth[v] + 1;
            dfs(i, v);
        }
    }

    void build(int root) {
        dfs(root, root);
        for (int k = 1; k < LOG; k++)
            for (int i = 0; i < N; i++)
                up[k][i] = up[k - 1][ up[k - 1][i] ];
    }

    int kth(int v, int k) {
        for (int i = 0; i < LOG; i++)
            if ((k & (1 << i)) != 0)
                v = up[i][v];
        return v;
    }

    int lca(int a, int b) {
        if (depth[a] < depth[b]) { int t = a; a = b; b = t; }
        a = kth(a, depth[a] - depth[b]);

        if (a == b) return a;

        for (int i = LOG - 1; i >= 0; i--)
            if (up[i][a] != up[i][b]) {
                a = up[i][a];
                b = up[i][b];
            }
        return up[0][a];
    }
}
