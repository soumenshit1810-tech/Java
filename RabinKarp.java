class RabinKarp {
    static final int MOD = 1_000_000_007;
    static final int P = 31;

    List<Integer> search(String text, String pat) {
        int n = text.length(), m = pat.length();

        long patHash = 0, pow = 1;

        for (char c : pat.toCharArray()) {
            patHash = (patHash * P + c) % MOD;
        }

        long[] prefix = new long[n+1];
        long[] power = new long[n+1];
        power[0] = 1;

        for (int i = 1; i <= n; i++) {
            prefix[i] = (prefix[i-1] * P + text.charAt(i-1)) % MOD;
            power[i] = (power[i-1] * P) % MOD;
        }

        List<Integer> res = new ArrayList<>();

        for (int i = 0; i <= n - m; i++) {
            long cur = (prefix[i+m] - prefix[i]*power[m] % MOD + MOD) % MOD;
            if (cur == patHash) res.add(i);
        }

        return res;
    }
}
