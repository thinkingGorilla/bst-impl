public class BinarySearchTree {

    public TreeNode root;

    // 삭제할 노드가 자손이 한 개이던, 두 개이던 결국에는
    // 해당 노드의 우측 서브트리로부터 가장 작은 값을 찾아 삭제할 노드를 대신한다.
    // 즉 삭제 재귀는 대신할 노드가 리프 노드가 될 때까지 반복한다.
    public TreeNode delete(TreeNode root, int key) {
        if (root == null) return null;

        if (key < root.key) {
            System.out.println("키가 루트보다 작다.");
            root.left = delete(root.left, key);
        } else if (key > root.key) {
            System.out.println("키가 루트보다 크다.");
            root.right = delete(root.right, key);
        } else {
            System.out.println("키가 루트와 같다.");
            // left, right가 모두 null인 경우
            // 첫번째 조건문에 의해 right가 반환되지만 그 값은 null이다.
            if (root.left == null) return root.right;
            if (root.right == null) return root.left;

            System.out.println("before key: " + root.key);
            root.key = minValue(root.right);
            // root.key = maxValue(root.left);
            System.out.println("after key: " + root.key);
            // 우측 서브트리의 최소값을 root.key로 올려썼으므로,
            // 오른쪽 서브트리에서 그 후임자 노드를 삭제해 중복을 제거한다.
            // (전임자 방식은 왼쪽 서브트리의 최대값을 사용하며, 그 경우에는 왼쪽 서브트리에서 전임자 노드를 삭제한다.)
            root.right = delete(root.right, root.key);
            // root.left = delete(root.left, root.key);
            System.out.printf("%s, %s%n", root.key, root.right == null ? "null" : root.right.key);
        }
        return root;
    }

    private int minValue(TreeNode node) {
        int min = node.key;
        while (node.left != null) {
            min = node.left.key;
            node = node.left;
        }
        return min;
    }

    private int maxValue(TreeNode node) {
        int max = node.key;
        while (node.right != null) {
            max = node.right.key;
            node = node.right;
        }
        return max;
    }

    public TreeNode insert(TreeNode root, int key) {
        if (root == null) return new TreeNode(key);
        if (key < root.key) {
            root.left = insert(root.left, key);
        } else if (key > root.key) {
            root.right = insert(root.right, key);
        }
        return root;
    }

    public void inorder(TreeNode root) {
        if (root != null) {
            inorder(root.left);
            System.out.print(root.key + " ");
            inorder(root.right);
        }
    }

    public static void main(String[] args) {
        BinarySearchTree bst = new BinarySearchTree();
        TreeNode root = null;
        int[] keys = {50, 25, 75, 10, 40, 60, 90, 15, 35, 65, 95, 68};

        for (int key : keys) {
            root = bst.insert(root, key);
        }

        System.out.println("중위 순회:");
        bst.inorder(root);
        System.out.println();

        root = bst.delete(root, 50);
        System.out.println("\n삭제 후 중위순회:");
        bst.inorder(root);
    }
}
