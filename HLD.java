class HLD {
    int n, curPos;
    int[] parent, depth, heavy, head, pos, size;
    int[] seg;
    List<Integer>[] tree;

    HLD(int n) {
        this.n = n;
        parent = new int[n];
        depth = new int[n];
        heavy = new int[n];
        head = new int[n];
        pos = new int[n];
        size = new int[n];
        seg = new int[4*n];
        tree = new ArrayList[n];
        Arrays.fill(heavy, -1);
        for (int i = 0; i < n; i++) tree[i] = new ArrayList<>();
    }

    void addEdge(int u, int v) {
        tree[u].add(v);
        tree[v].add(u);
    }

    int dfs(int v, int p) {
        size[v] = 1;
        int max = 0;
        for (int u : tree[v]) {
            if (u != p) {
                parent[u] = v;
                depth[u] = depth[v] + 1;
                int sub = dfs(u, v);
                size[v] += sub;
                if (sub > max) {
                    max = sub;
                    heavy[v] = u;
                }
            }
        }
        return size[v];
    }

    void decompose(int v, int h) {
        head[v] = h;
        pos[v] = curPos++;
        if (heavy[v] != -1)
            decompose(heavy[v], h);
        for (int u : tree[v]) {
            if (u != parent[v] && u != heavy[v])
                decompose(u, u);
        }
    }

    void build() {
        dfs(0, -1);
        decompose(0, 0);
    }

    void segUpdate(int idx, int val, int i, int l, int r) {
        if (l == r) {
            seg[i] = val;
            return;
        }
        int m = (l+r)/2;
        if (idx <= m) segUpdate(idx, val, 2*i, l, m);
        else segUpdate(idx, val, 2*i+1, m+1, r);
        seg[i] = seg[2*i] + seg[2*i+1];
    }

    int segQuery(int ql, int qr, int i, int l, int r) {
        if (ql > r || qr < l) return 0;
        if (ql <= l && r <= qr) return seg[i];
        int m = (l+r)/2;
        return segQuery(ql,qr,2*i,l,m) + segQuery(ql,qr,2*i+1,m+1,r);
    }

    int queryPath(int a, int b) {
        int res = 0;
        while (head[a] != head[b]) {
            if (depth[head[a]] < depth[head[b]]) {
                int t = a; a = b; b = t;
            }
            res += segQuery(pos[head[a]], pos[a], 1, 0, n-1);
            a = parent[head[a]];
        }
        if (depth[a] > depth[b]) {
            int t = a; a = b; b = t;
        }
        res += segQuery(pos[a], pos[b], 1, 0, n-1);
        return res;
    }
}
