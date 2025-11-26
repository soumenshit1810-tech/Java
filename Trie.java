class Trie {
    class Node {
        Node[] children = new Node[26];
        boolean end;
    }

    Node root = new Node();

    void insert(String word) {
        Node curr = root;
        for (char c : word.toCharArray()) {
            int i = c - 'a';
            if (curr.children[i] == null)
                curr.children[i] = new Node();
            curr = curr.children[i];
        }
        curr.end = true;
    }

    boolean search(String word) {
        Node curr = root;
        for (char c : word.toCharArray()) {
            int i = c - 'a';
            if (curr.children[i] == null) return false;
            curr = curr.children[i];
        }
        return curr.end;
    }

    boolean startsWith(String prefix) {
        Node curr = root;
        for (char c : prefix.toCharArray()) {
            int i = c - 'a';
            if (curr.children[i] == null) return false;
            curr = curr.children[i];
        }
        return true;
    }
}
