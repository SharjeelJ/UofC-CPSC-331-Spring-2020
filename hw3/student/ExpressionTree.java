package hw3.student;

import java.text.ParseException;
import java.util.LinkedList;

public class ExpressionTree implements ExpressionTreeInterface
{
    private ExpressionTreeNode root;

    /**
     * Constructor to build an empty parse tree.
     */
    public ExpressionTree()
    {
        root = null;
    }

    /**
     * Build a parse tree from an expression.
     *
     * @param line String containing the expression
     * @throws ParseException If an error occurs during parsing
     */
    public void parse(String line) throws ParseException
    {
        // Creates a 1D string array of the input string where elements are delimited by whitespace
        String[] delimiterSeparatedInput = line.trim().split(" ");

        // Creates a linked list that will store the elements of the 1D array
        LinkedList<String> delimiterSeparatedInputList = new LinkedList<String>();

        // Adds all the elements of the 1D array to the linked list
        for (int counter = 0; counter < delimiterSeparatedInput.length; counter++)
            delimiterSeparatedInputList.add(delimiterSeparatedInput[counter]);

        // Adds an additional whitespace element at the end of the linked list (will signal an end to the recursion)
        delimiterSeparatedInputList.add(" ");

        // Starts the recursive decent by calling the method that will parse the production rules of E for the provided grammar
        root = parseE(delimiterSeparatedInputList);
    }

    /**
     * Performs the production rules of E in the provided grammar
     *
     * @param list Linked list consisting of individual elements for operators / integers present in the original passed in string
     * @return ExpressionTreeNode that is the result of the current stage's production rules from the grammar
     * @throws ParseException If an error occurs during parsing
     */
    private ExpressionTreeNode parseE(LinkedList<String> list) throws ParseException
    {
        // Creates a node that will contain the results of the current parsing stage and will be passed up after completion
        ExpressionTreeNode node;

        // Calls the method that will parse the production rules of T for the provided grammar and stores the result
        node = parseT(list);

        // Checks to see if further calls to the method handling the production rules of T are necessary
        while (list.getFirst().matches("[+,\\-]"))
        {
            String operator = list.getFirst();
            list.removeFirst();
            ExpressionTreeNode secondTerm = parseT(list);
            node = new ExpressionTreeNode(operator, node, secondTerm);
        }

        // Returns the result of the current parsing stage
        return node;
    }

    /**
     * Performs the production rules of T in the provided grammar
     *
     * @param list Linked list consisting of individual elements for operators / integers present in the original passed in string
     * @return ExpressionTreeNode that is the result of the current stage's production rules from the grammar
     * @throws ParseException If an error occurs during parsing
     */
    private ExpressionTreeNode parseT(LinkedList<String> list) throws ParseException
    {
        // Creates a node that will contain the results of the current parsing stage and will be passed up after completion
        ExpressionTreeNode node;

        // Calls the method that will parse the production rules of F for the provided grammar and stores the result
        node = parseF(list);

        // Checks to see if further calls to the method handling the production rules of F are necessary
        while (list.getFirst().matches("[*,/]"))
        {
            String operator = list.getFirst();
            list.removeFirst();
            ExpressionTreeNode secondTerm = parseF(list);
            node = new ExpressionTreeNode(operator, node, secondTerm);
        }

        // Returns the result of the current parsing stage
        return node;
    }

    /**
     * Performs the production rules of F in the provided grammar
     *
     * @param list Linked list consisting of individual elements for operators / integers present in the original passed in string
     * @return ExpressionTreeNode that is the result of the current stage's production rules from the grammar
     * @throws ParseException If an error occurs during parsing
     */
    private ExpressionTreeNode parseF(LinkedList<String> list) throws ParseException
    {
        // Creates a node that will contain the results of the current parsing stage and will be passed up after completion
        ExpressionTreeNode node;

        // Checks to see if the current element being parsed is an integer and creates a node for it if true while removing it from the parse list
        if (list.getFirst().matches("-?\\d+"))
        {
            node = new ExpressionTreeNode(list.getFirst());
            list.removeFirst();
            return node;
        }
        // Checks to see if the current element being parsed is an open bracket and calls the production rules of E if true
        else if (list.getFirst().matches("[(]"))
        {
            list.removeFirst();
            node = parseE(list);

            // Makes sure that a matching close bracket follows otherwise throws a parsing error
            if (list.getFirst().matches("[)]")) list.removeFirst();
            else throw new ParseException("Found an unmatched bracket!", 0);

            // Returns the result of the current parsing stage
            return node;
        } else throw new ParseException("Invalid input!", 0);
    }

    /**
     * Evaluate the expression tree and return the integer result. An
     * empty tree returns 0.
     */
    public int evaluate()
    {
        // Check to see if the tree is empty and returns 0 if true
        if (root == null)
        {
            return 0;
        }
        // In the case the tree is not empty, computes and returns the integer result
        else
        {
            simplify(0);
            return Integer.valueOf(root.el);
        }
    }

    /**
     * Simplifies the tree to a specified height h (h >= 0). After simplification, the tree
     * has a height of h. Any subtree rooted at a height of h is replaced by a leaf node
     * containing the evaluated result of that subtree.
     * If the height of the tree is already less than the specified height, the tree is unaffected.
     *
     * @param h The height to simplify the tree to.
     */
    public void simplify(int h)
    {
        // Stores the height of the tree
        int treeHeight = maxDepth(root) - 1;

        // If the current height of the tree is greater than the desired height, performs simplification
        if (treeHeight > h && h >= 0)
        {
            // Gets the deepest node in the tree that needs to be evaluated
            ExpressionTreeNode deepestNode = deepestNode(root);

            // Stores the left and right child (first and second term) alongside the parent's value (operator)
            int firstTerm = Integer.valueOf(deepestNode.left.el);
            int secondTerm = Integer.valueOf(deepestNode.right.el);
            String operator = deepestNode.el;

            // Performs the appropriate arithmetic calculation based on the operator value
            switch (operator)
            {
                case "+":
                    deepestNode.el = String.valueOf(firstTerm + secondTerm);
                    deepestNode.left = null;
                    deepestNode.right = null;
                    break;
                case "-":
                    deepestNode.el = String.valueOf(firstTerm - secondTerm);
                    deepestNode.left = null;
                    deepestNode.right = null;
                    break;
                case "*":
                    deepestNode.el = String.valueOf(firstTerm * secondTerm);
                    deepestNode.left = null;
                    deepestNode.right = null;
                    break;
                case "/":
                    deepestNode.el = String.valueOf(firstTerm / secondTerm);
                    deepestNode.left = null;
                    deepestNode.right = null;
                    break;
            }

            // Recursively calls itself to further simplify the tree if necessary
            simplify(h);
        }
    }

    /**
     * Returns the deepest node in the tree
     *
     * @param node Node to look deeper from
     * @return ExpressionTreeNode that is the deepest node in the tree
     */
    private ExpressionTreeNode deepestNode(ExpressionTreeNode node)
    {
        // Checks to see if the left / right node contains an operator and traverses down that side
        if (node.left.el.matches("[+,\\-,*,/]")) return deepestNode(node.left);
        else if (node.right.el.matches("[+,\\-,*,/]")) return deepestNode(node.right);
        return node;
    }

    /**
     * Returns the height of the tree
     *
     * @param node Node to look deeper from
     * @return Integer that is the height of the tree
     */
    private int maxDepth(ExpressionTreeNode node)
    {
        // Checks to see if the tree is empty and returns 0 if true
        if (node == null) return 0;
        else
        {
            // Stores the depths from the left and right nodes
            int leftDepth = maxDepth(node.left);
            int rightDepth = maxDepth(node.right);

            // Returns the left node's depth if it is greater than the right node's otherwise store the right node's depth
            if (leftDepth > rightDepth) return (leftDepth + 1);
            else return (rightDepth + 1);
        }
    }

    /**
     * Returns a parentheses-free prefix representation of the expression. Tokens are
     * separated by whitespace. An empty tree returns a zero length string.
     */
    public String prefix()
    {
        return prefix(root).trim();
    }

    /**
     * Returns the prefix expression from the passed in node and everything below
     *
     * @param node Node to start building the string from
     * @return String that is the prefix expression down from the passed in node
     */
    private String prefix(ExpressionTreeNode node)
    {
        String expressionString = "";
        if (node != null)
        {
            expressionString += " " + node.el;
            expressionString += prefix(node.left);
            expressionString += prefix(node.right);
        }
        return expressionString;
    }

    /**
     * Returns a parentheses-free postfix representation of the expression. Tokens
     * are separated by whitespace. An empty tree returns a zero length string.
     */
    public String postfix()
    {
        return postfix(root).trim();
    }

    /**
     * Returns the postfix expression from the passed in node and everything below
     *
     * @param node Node to start building the string from
     * @return String that is the postfix expression down from the passed in node
     */
    private String postfix(ExpressionTreeNode node)
    {
        String expressionString = "";
        if (node != null)
        {
            expressionString += postfix(node.left);
            expressionString += postfix(node.right);
            expressionString += " " + node.el;
        }
        return expressionString;
    }

    /**
     * Returns the infix expression from the passed in node and everything below
     *
     * @param node Node to start building the string from
     * @return String that is the infix expression down from the passed in node
     */
    private String infix(ExpressionTreeNode node)
    {
        String expressionString = "";
        if (node != null)
        {
            expressionString += infix(node.left);
            expressionString += " " + node.el;
            expressionString += infix(node.right);
        }
        return expressionString;
    }
}
