class BST {
    Node root;

    Node insert(Node root, int data) {
        if (root == null) return new Node(data);
        if (data < root.data) root.left = insert(root.left, data);
        else root.right = insert(root.right, data);
        return root;
    }

    boolean search(Node root, int key) {
        if (root == null) return false;
        if (root.data == key) return true;
        return key < root.data
                ? search(root.left, key)
                : search(root.right, key);
    }

    public static void main(String[] args) {
        BST bst = new BST();
        bst.root = bst.insert(bst.root, 50);
        bst.insert(bst.root, 30);
        bst.insert(bst.root, 70);

        System.out.println(bst.search(bst.root, 30));
    }
}
