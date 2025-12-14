class WaveletTree {
    int lo, hi;
    WaveletTree left, right;
    int[] pref;

    WaveletTree(int[] arr, int l, int r) {
        lo = l; hi = r;
        if (l == r || arr.length == 0) return;

        int mid = (l + r) >> 1;
        pref = new int[arr.length + 1];
        List<Integer> L = new ArrayList<>(), R = new ArrayList<>();

        for (int i = 0; i < arr.length; i++) {
            pref[i + 1] = pref[i];
            if (arr[i] <= mid) {
                pref[i + 1]++;
                L.add(arr[i]);
            } else R.add(arr[i]);
        }

        left = new WaveletTree(L.stream().mapToInt(i -> i).toArray(), l, mid);
        right = new WaveletTree(R.stream().mapToInt(i -> i).toArray(), mid + 1, r);
    }

    int kth(int l, int r, int k) {
        if (lo == hi) return lo;
        int inLeft = pref[r] - pref[l - 1];
        if (k <= inLeft)
            return left.kth(pref[l - 1] + 1, pref[r], k);
        return right.kth(l - pref[l - 1], r - pref[r], k - inLeft);
    }

    int countLE(int l, int r, int k) {
        if (k < lo) return 0;
        if (hi <= k) return r - l + 1;
        return left.countLE(pref[l - 1] + 1, pref[r], k)
             + right.countLE(l - pref[l - 1], r - pref[r], k);
    }
}
