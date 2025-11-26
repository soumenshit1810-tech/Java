class SegmentTree {
    int[] tree;
    int n;

    public SegmentTree(int[] arr) {
        n = arr.length;
        tree = new int[4 * n];
        build(arr, 0, n - 1, 1);
    }

    void build(int[] arr, int start, int end, int node) {
        if (start == end) {
            tree[node] = arr[start];
            return;
        }

        int mid = (start + end) / 2;
        build(arr, start, mid, node * 2);
        build(arr, mid + 1, end, node * 2 + 1);

        tree[node] = tree[node * 2] + tree[node * 2 + 1];
    }

    int query(int l, int r) {
        return queryUtil(0, n - 1, l, r, 1);
    }

    int queryUtil(int start, int end, int l, int r, int node) {
        if (r < start || l > end) return 0;
        if (l <= start && end <= r) return tree[node];

        int mid = (start + end) / 2;
        return queryUtil(start, mid, l, r, node * 2) +
               queryUtil(mid + 1, end, l, r, node * 2 + 1);
    }

    void update(int index, int value) {
        updateUtil(0, n - 1, index, value, 1);
    }

    void updateUtil(int start, int end, int index, int value, int node) {
        if (start == end) {
            tree[node] = value;
            return;
        }

        int mid = (start + end) / 2;
        if (index <= mid) updateUtil(start, mid, index, value, node * 2);
        else updateUtil(mid + 1, end, index, value, node * 2 + 1);

        tree[node] = tree[node * 2] + tree[node * 2 + 1];
    }
}
