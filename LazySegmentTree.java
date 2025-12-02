class LazySegmentTree {
    int[] tree, lazy;
    int n;

    LazySegmentTree(int[] arr) {
        n = arr.length;
        tree = new int[4 * n];
        lazy = new int[4 * n];
        build(arr, 0, 0, n - 1);
    }

    void build(int[] arr, int node, int start, int end) {
        if (start == end) {
            tree[node] = arr[start];
            return;
        }
        int mid = (start + end) / 2;
        build(arr, 2*node+1, start, mid);
        build(arr, 2*node+2, mid+1, end);
        tree[node] = tree[2*node+1] + tree[2*node+2];
    }

    void applyLazy(int node, int start, int end) {
        if (lazy[node] != 0) {
            tree[node] += (end - start + 1) * lazy[node];
            if (start != end) {
                lazy[2*node+1] += lazy[node];
                lazy[2*node+2] += lazy[node];
            }
            lazy[node] = 0;
        }
    }

    void update(int node, int start, int end, int L, int R, int val) {
        applyLazy(node, start, end);

        if (end < L || start > R) return;

        if (L <= start && end <= R) {
            lazy[node] += val;
            applyLazy(node, start, end);
            return;
        }

        int mid = (start + end) / 2;
        update(2*node+1, start, mid, L, R, val);
        update(2*node+2, mid+1, end, L, R, val);
        tree[node] = tree[2*node+1] + tree[2*node+2];
    }

    int query(int node, int start, int end, int L, int R) {
        applyLazy(node, start, end);

        if (end < L || start > R) return 0;

        if (L <= start && end <= R) return tree[node];

        int mid = (start + end) / 2;
        return query(2*node+1, start, mid, L, R)
             + query(2*node+2, mid+1, end, L, R);
    }
}
