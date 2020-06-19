package Assignment3;

public class Part1Question2
{
    public static class BSTNode<T extends Comparable<? super T>>
    {
        protected T el;
        protected BSTNode<T> left, right;

        public BSTNode()
        {
            left = right = null;
        }

        public BSTNode(T el)
        {
            this(el, null, null);
        }

        public BSTNode(T el, BSTNode<T> lt, BSTNode<T> rt)
        {
            this.el = el;
            left = lt;
            right = rt;
        }

        // Public facing method to check if the passed in binary tree node and everything below it is a binary search tree
        public boolean isValidBST(BSTNode<T> node)
        {
            return isValidBST(node, null, null);
        }

        // Method to check if the passed in binary tree node and everything below it is a binary search tree
        private boolean isValidBST(BSTNode<T> node, T lowerLimit, T upperLimit)
        {
            // Checks to see if the current node being evaluated is null and returns true if it is (passes BST condition)
            if (node == null) return true;
                // Checks to see if the current node's value < lower limit (fails BST condition)
            else if (lowerLimit != null && node.el.compareTo(lowerLimit) < 0) return false;
                // Checks to see if the current node's value >= upper limit (fails BST condition)
            else if (upperLimit != null && node.el.compareTo(upperLimit) >= 0) return false;

            // Stores the results from recursively checking whether the left & right subtrees are valid BST
            boolean leftSubtreeValid = isValidBST(node.left, lowerLimit, node.el);
            boolean rightSubtreeValid = isValidBST(node.right, node.el, upperLimit);

            // If both the left & right subtrees are BST then returns true otherwise returns false
            if (leftSubtreeValid && rightSubtreeValid) return true;
            else return false;
        }
    }

    public static void main(String args[])
    {
        BSTNode bst = new BSTNode();
        System.out.println(bst.isValidBST(bst));

        bst = new BSTNode(3);
        bst.right = new BSTNode(3);
        System.out.println(bst.isValidBST(bst));

        bst = new BSTNode(3);
        bst.left = new BSTNode(3);
        System.out.println(bst.isValidBST(bst));

        bst = new BSTNode(2);
        bst.left = new BSTNode(3);
        System.out.println(bst.isValidBST(bst));

        bst = new BSTNode("D");
        bst.left = new BSTNode("C");
        bst.right = new BSTNode("N");
        bst.left.left = new BSTNode("A");
        bst.left.left.right = new BSTNode("A");
        bst.left.left.right.right = new BSTNode("A");
        System.out.println(bst.isValidBST(bst));

        bst = new BSTNode(13);
        bst.left = new BSTNode(10);
        bst.right = new BSTNode(25);
        bst.left.left = new BSTNode(2);
        bst.left.right = new BSTNode(12);
        bst.right.left = new BSTNode(20);
        bst.right.right = new BSTNode(31);
        bst.right.right.left = new BSTNode(29);
        System.out.println(bst.isValidBST(bst));
    }
}
