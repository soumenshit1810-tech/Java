class KMP {
    int[] lps;

    void buildLPS(String pat) {
        int n = pat.length();
        lps = new int[n];
        int len = 0, i = 1;

        while (i < n) {
            if (pat.charAt(i) == pat.charAt(len)) {
                lps[i++] = ++len;
            } else {
                if (len != 0) len = lps[len - 1];
                else lps[i++] = 0;
            }
        }
    }

    void search(String text, String pat) {
        buildLPS(pat);
        int i = 0, j = 0;

        while (i < text.length()) {
            if (text.charAt(i) == pat.charAt(j)) {
                i++; j++;
            }

            if (j == pat.length()) {
                System.out.println("Pattern found at index " + (i - j));
                j = lps[j - 1];
            } else if (i < text.length() && text.charAt(i) != pat.charAt(j)) {
                if (j != 0) j = lps[j - 1];
                else i++;
            }
        }
    }
}
