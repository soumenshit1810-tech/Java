import java.util.*;

class AhoCorasick {
    static class Node {
        int[] next = new int[26];
        int link = -1;
        List<Integer> out = new ArrayList<>();
        Node() { Arrays.fill(next, -1); }
    }

    List<Node> trie = new ArrayList<>();
    AhoCorasick() { trie.add(new Node()); }

    void add(String s, int id) {
        int v = 0;
        for (char ch : s.toCharArray()) {
            int c = ch - 'a';
            if (trie.get(v).next[c] == -1) {
                trie.get(v).next[c] = trie.size();
                trie.add(new Node());
            }
            v = trie.get(v).next[c];
        }
        trie.get(v).out.add(id);
    }

    void build() {
        Queue<Integer> q = new ArrayDeque<>();
        trie.get(0).link = 0;
        for (int c = 0; c < 26; c++) {
            int v = trie.get(0).next[c];
            if (v != -1) {
                trie.get(v).link = 0;
                q.add(v);
            } else {
                trie.get(0).next[c] = 0;
            }
        }
        while (!q.isEmpty()) {
            int v = q.poll();
            for (int c = 0; c < 26; c++) {
                int u = trie.get(v).next[c];
                if (u != -1) {
                    trie.get(u).link = trie.get(trie.get(v).link).next[c];
                    trie.get(u).out.addAll(trie.get(trie.get(u).link).out);
                    q.add(u);
                } else {
                    trie.get(v).next[c] = trie.get(trie.get(v).link).next[c];
                }
            }
        }
    }

    // returns list of (patternId, endPosition)
    List<int[]> search(String text) {
        List<int[]> res = new ArrayList<>();
        int v = 0;
        for (int i = 0; i < text.length(); i++) {
            char ch = text.charAt(i);
            if (ch < 'a' || ch > 'z') { v = 0; continue; } // adapt if needed
            v = trie.get(v).next[ch - 'a'];
            for (int pid : trie.get(v).out) {
                res.add(new int[]{pid, i}); // pattern pid ends at position i
            }
        }
        return res;
    }
}
