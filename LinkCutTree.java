class LinkCutTree {
    static class Node {
        Node left, right, parent;
        boolean rev;
        int val, sum;

        Node(int v) {
            val = v;
            sum = v;
        }
    }

    boolean isRoot(Node x) {
        return x.parent == null || (x.parent.left != x && x.parent.right != x);
    }

    void push(Node x) {
        if (x != null && x.rev) {
            Node t = x.left;
            x.left = x.right;
            x.right = t;
            if (x.left != null) x.left.rev ^= true;
            if (x.right != null) x.right.rev ^= true;
            x.rev = false;
        }
    }

    void pull(Node x) {
        x.sum = x.val +
                (x.left != null ? x.left.sum : 0) +
                (x.right != null ? x.right.sum : 0);
    }

    void rotate(Node x) {
        Node p = x.parent;
        Node g = p.parent;
        push(p); push(x);

        if (p.left == x) {
            p.left = x.right;
            if (x.right != null) x.right.parent = p;
            x.right = p;
        } else {
            p.right = x.left;
            if (x.left != null) x.left.parent = p;
            x.left = p;
        }

        p.parent = x;
        x.parent = g;

        if (g != null) {
            if (g.left == p) g.left = x;
            else if (g.right == p) g.right = x;
        }

        pull(p); pull(x);
    }

    void splay(Node x) {
        push(x);
        while (!isRoot(x)) {
            Node p = x.parent;
            Node g = p.parent;
            if (!isRoot(p))
                rotate((p.left == x) == (g.left == p) ? p : x);
            rotate(x);
        }
    }

    Node access(Node x) {
        Node last = null;
        for (Node y = x; y != null; y = y.parent) {
            splay(y);
            y.right = last;
            pull(y);
            last = y;
        }
        splay(x);
        return last;
    }

    void makeRoot(Node x) {
        access(x);
        x.rev ^= true;
        push(x);
    }

    void link(Node u, Node v) {
        makeRoot(u);
        u.parent = v;
    }

    void cut(Node x) {
        access(x);
        if (x.left != null) {
            x.left.parent = null;
            x.left = null;
        }
        pull(x);
    }

    int pathSum(Node u, Node v) {
        makeRoot(u);
        access(v);
        return v.sum;
    }
}
