import java.util.ArrayList;
import java.util.List;

/**
 * The AVLTree class represents an AVL tree data structure that manages Stock objects.
 * It supports standard AVL tree operations such as insertion, deletion, searching,
 * and in-order traversal while maintaining balance for efficient operations.
 */
public class AVLTree 
{
    private class Node // Node class to represent each node in the AVLtree
    {
        Stock stock;
        Node left;
        Node right;
        int height;

        Node(Stock stock) 
        {
            this.stock = stock;
            this.height = 1; // leaf node
        }
    }

    private Node root; // Root node of the AVL tree

    /**
     * Inserts a new stock into the AVL tree.
     *
     * @param stock The stock to be inserted.
     */
    public void insert(Stock stock) // inserting methodr -> insert to all AVLTRee
    {
        root = insert(root, stock);
    }

    /**
     * Recursively inserts a new stock into the AVL tree.
     *
     * @param node The current node in the AVL tree.
     * @param stock The stock to be inserted.
     * @return The updated node.
     */
    private Node insert(Node node, Stock stock) // recurs,ve method of inserting
    {
        if (node == null) 
        {
            return new Node(stock);
        }

        /***** Comparing the stock symbol to decide where to insert the new node *****/

        if (stock.getSymbol().compareTo(node.stock.getSymbol()) < 0) 
        {
            node.left = insert(node.left, stock);
        } 

        else if (stock.getSymbol().compareTo(node.stock.getSymbol()) > 0) 
        {
            node.right = insert(node.right, stock);
        } 
        
        else 
        {
            return node; // Duplicate keys are not allowed
        }

        /***************************************************************************/

        // Updating the height of the current node
        node.height = 1 + Math.max(height(node.left), height(node.right));

        return balance(node);
    }

    /**
     * Deletes a stock from the AVL tree based on its symbol.
     *
     * @param symbol The symbol of the stock to be deleted.
     */
    public void delete(String symbol) 
    {
        root = delete(root, symbol);
    }

    /**
     * Recursively deletes a stock from the AVL tree.
     *
     * @param node The current node in the AVL tree.
     * @param symbol The symbol of the stock to be deleted.
     * @return The updated node.
     */
    private Node delete(Node node, String symbol) 
    {
        if (node == null) 
        {
            return node;
        }

        /******** Compare the stock symbol to find the node to delete *******/

        if (symbol.compareTo(node.stock.getSymbol()) < 0) 
        {
            node.left = delete(node.left, symbol);
        } 

        else if (symbol.compareTo(node.stock.getSymbol()) > 0) 
        {
            node.right = delete(node.right, symbol);
        } 

        else 
        {
            if ((node.left == null) || (node.right == null)) // Node with only one child or no child
            {
                Node temp = null;

                if (temp == node.left) 
                {
                    temp = node.right;
                } 

                else 
                {
                    temp = node.left;
                }

                if (temp == null) // if dont exist child
                {
                    temp = node;
                    node = null;
                } 

                else 
                {
                    node = temp; // One child case
                }
            } 

            else // Node with two children: Get the inorder successor
            {
                Node temp = minValueNode(node.right);
                node.stock = temp.stock;
                node.right = delete(node.right, temp.stock.getSymbol());
            }
        }
        /*********************************************************************/

        if (node == null) // If the tree had only one node
        {
            return node;
        }
        // Update the height of the current node
        node.height = Math.max(height(node.left), height(node.right)) + 1;

        return balance(node);
    }

    /**
     * Searches for a stock in the AVL tree based on its symbol.
     *
     * @param symbol The symbol of the stock to be searched.
     * @return The stock if found, otherwise null.
     */
    public Stock search(String symbol) 
    {
        return search(root, symbol);
    }

    /**
     * Recursively searches for a stock in the AVL tree.
     *
     * @param node The current node in the AVL tree.
     * @param symbol The symbol of the stock to be searched.
     * @return The stock if found, otherwise null.
     */
    private Stock search(Node node, String symbol) // Recursive method to search for a stock
    {
        if (node == null || node.stock.getSymbol().equals(symbol)) // Base case -> node is null or the symbol is found
        {
            return (node != null) ? node.stock : null;
        }

        /* Search in the left or right subtree */

        if (symbol.compareTo(node.stock.getSymbol()) < 0) 
        {
            return search(node.left, symbol); //  seraching left tree recursively
        }
        return search(node.right, symbol); //searching right tree recursevily
    }

    /**
     * Finds the node with the minimum value in the subtree rooted with the given node.
     *
     * @param node The root node of the subtree.
     * @return The node with the minimum value.
     */
    private Node minValueNode(Node node) // return the node with the minimum value
    {
        Node current = node;

        while (current.left != null) 
        {
            current = current.left; //search in left tree because left side is always smaller
        }
        return current;
    }

    /**
     * Returns the height of the given node.
     *
     * @param node The node whose height is to be calculated.
     * @return The height of the node.
     */
    private int height(Node node) 
    {
        return (node == null) ? 0 : node.height;
    }

    /**
     * Balances the AVL tree at the given node.
     *
     * @param node The node to be balanced.
     * @return The balanced node.
     */
    private Node balance(Node node) // Balance AVL tree
    {
        int balance = getBalance(node);

        /* case:
                 root
                /
              Left 
             /   \
          (Left)
        */
        if (balance > 1 && getBalance(node.left) >= 0) 
        {
            return rightRotate(node);
        }

        /* case:
                 root
                /
              Left 
             /   \
              (Right)
        */
        if (balance > 1 && getBalance(node.left) < 0) 
        {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }

        /* case:
                root
                    \
                   Right
                   /   \
                      (Right)
        */
        if (balance < -1 && getBalance(node.right) <= 0) 
        {
            return leftRotate(node);
        }

        /* case:
                root
                    \
                   Right
                   /   \
                (left)
        */
        if (balance < -1 && getBalance(node.right) > 0) 
        {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }
        return node;
    }
    
    /**
     * Returns the balance factor of the given node.
     *
     * @param node The node whose balance factor is to be calculated.
     * @return The balance factor of the node.
     */
    private int getBalance(Node node) // retrning balance factor of a node
    {
        return (node == null) ? 0 : height(node.left) - height(node.right);
    }

    /**
     * Right rotates the subtree rooted with the unbalanced node.
     *
     * @param unbalanced The unbalanced node.
     * @return The new root of the subtree after rotation.
     */
    private Node rightRotate(Node unbalanced) 
    {
        Node newRoot = unbalanced.left;
        Node rightSubtreeOfNewRoot = newRoot.right;

        newRoot.right = unbalanced;
        unbalanced.left = rightSubtreeOfNewRoot;

        // Update heights
        unbalanced.height = Math.max(height(unbalanced.left), height(unbalanced.right)) + 1;
        newRoot.height = Math.max(height(newRoot.left), height(newRoot.right)) + 1;

        return newRoot;
    }

    /**
     * Left rotates the subtree rooted with the unbalanced node.
     *
     * @param unbalanced The unbalanced node.
     * @return The new root of the subtree after rotation.
     */
    private Node leftRotate(Node unbalanced) 
    {
        Node newRoot = unbalanced.right;
        Node leftSubtreeOfNewRoot = newRoot.left;

        newRoot.left = unbalanced;
        unbalanced.right = leftSubtreeOfNewRoot;

        // Update heights
        unbalanced.height = Math.max(height(unbalanced.left), height(unbalanced.right)) + 1;
        newRoot.height = Math.max(height(newRoot.left), height(newRoot.right)) + 1;

        return newRoot;
    }

    /**
     * Performs an in-order traversal of the AVL tree.
     *
     * @return A list of stocks in in-order.
     */
    public List<Stock> inOrderTraversal() // Perform in-order traversal of the AVL tree
    {
        List<Stock> stocks = new ArrayList<>();
        inOrderTraversal(root, stocks);

        return stocks;
    }

    /**
     * Recursively performs an in-order traversal of the AVL tree.
     *
     * @param node The current node in the AVL tree.
     * @param stocks The list to store the stocks.
     */
    private void inOrderTraversal(Node node, List<Stock> stocks) // Recursive method for in-order traversal
    {
        if (node != null) 
        {
            inOrderTraversal(node.left, stocks);
            stocks.add(node.stock);
            inOrderTraversal(node.right, stocks);
        }
    }
}
