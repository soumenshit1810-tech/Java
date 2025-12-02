import java.util.*;

class SuffixArray {
    public static int[] buildSuffixArray(String s) {
        int n = s.length();
        int[] sa = new int[n];
        int[] ranks = new int[n];
        int[] temp = new int[n];

        for (int i = 0; i < n; i++) {
            sa[i] = i;
            ranks[i] = s.charAt(i);
        }

        for (int k = 1; k < n; k <<= 1) {
            int finalK = k;
            Arrays.sort(sa, (a, b) -> {
                if (ranks[a] != ranks[b])
                    return ranks[a] - ranks[b];

                int ra = (a + finalK < n) ? ranks[a + finalK] : -1;
                int rb = (b + finalK < n) ? ranks[b + finalK] : -1;
                return ra - rb;
            });

            temp[sa[0]] = 0;

            for (int i = 1; i < n; i++) {
                int a = sa[i-1], b = sa[i];
                int same = (ranks[a] == ranks[b])
                        && ((a+finalK < n ? ranks[a+finalK] : -1)
                        == (b+finalK < n ? ranks[b+finalK] : -1));
                temp[b] = temp[a] + (same ? 0 : 1);
            }

            System.arraycopy(temp, 0, ranks, 0, n);

            if (ranks[sa[n-1]] == n-1) break;
        }
        return sa;
    }
}
