class TreeNode {
    int val;
    TreeNode left, right;

    TreeNode(int val) {
        this.val = val;
    }
}

public class BinaryTree {
    TreeNode root;

    void inorder(TreeNode node) {
        if (node == null) return;
        inorder(node.left);
        System.out.print(node.val + " ");
        inorder(node.right);
    }

    void preorder(TreeNode node) {
        if (node == null) return;
        System.out.print(node.val + " ");
        preorder(node.left);
        preorder(node.right);
    }

    void postorder(TreeNode node) {
        if (node == null) return;
        postorder(node.left);
        postorder(node.right);
        System.out.print(node.val + " ");
    }

    public static void main(String[] args) {
        BinaryTree t = new BinaryTree();
        t.root = new TreeNode(1);
        t.root.left = new TreeNode(2);
        t.root.right = new TreeNode(3);

        System.out.print("Inorder: ");
        t.inorder(t.root);
    }
}
