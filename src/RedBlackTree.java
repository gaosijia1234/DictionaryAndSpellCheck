import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;

import static java.lang.System.currentTimeMillis;

public class RedBlackTree<Key extends Comparable<Key>> {
    //an instance variable that points to the root RBNode.
    private RedBlackTree.Node<String> root;

    //Red-black trees consist of nodes, which are instances of the class that is given in RBNode.
    public static class Node<Key extends Comparable<Key>> { //changed to static

        Key key;
        Node<String> parent;
        Node<String> leftChild;
        Node<String> rightChild;
        boolean isRed;
        int color; // 1 is black, 0 is red

        public Node(Key key){
            this.key = key;
            leftChild = null;
            rightChild = null;
        }

        //the value in every node is larger than the value of the left child (or any value in the left subtree)
        // and smaller than the value of the right child (or any value in the right subtree)
        public int compareTo(Node<Key> n){ 	//this < that  <0
            return key.compareTo(n.key);  	//this > that  >0
        }

        public boolean isLeaf(){
            if (this.equals(root) && this.leftChild == null && this.rightChild == null)
                return true;
            if (this.equals(root))
                return false;
            if (this.leftChild == null && this.rightChild == null){
                return true;
            }
            return false;
        }
    }


    public boolean isLeaf(RedBlackTree.Node<String> n){
        // if only the root, it is leaf
        if (n.equals(root) && n.leftChild == null && n.rightChild == null)
            return true;
        if (n.equals(root))
            return false;
        // if has no child, it is leaf
        if (n.leftChild == null && n.rightChild == null){
            return true;
        }
        return false;
    }

    public interface Visitor<Key extends Comparable<Key>> {
        /**
         This method is called at each node.
         @param n the visited node
         */
        void visit(Node<Key> n);
    }

    public void printTree(){  //preorder: visit, go left, go right
        RedBlackTree.Node<String> currentNode = root;
        printTree(currentNode);
    }

    /**
     * Start at the root node and traverse the tree using preorder
     * @param node
     */
    public void printTree(RedBlackTree.Node<String> node){
        System.out.print(node.key);
        if (node.isLeaf()){
            return;
        }
        printTree(node.leftChild);
        printTree(node.rightChild);
    }

    /**
     * place a new node in the RB tree with data the parameter and color it red.
     * @param data
     */
    public void addNode(String data, Node node) {  	//this < that  <0.  this > that  >0


    }

    public void insert(String data) {
        addNode(data, root);
    }

    /**
     * searches for a key.
     * @param k
     * @return
     */
    public RedBlackTree.Node<String> lookup(String k){
        //fill
        Node current = root;
        if (k.compareTo(current.key.toString()) < 0){

        }
    }


    /**
     * left, resp. right, rotate around the node parameter.
     * @param n
     */
    public void rotateLeft(RedBlackTree.Node<String> n){
        // 1. define node x and y
        // node: x
        // y: temp
        Node node = n;
        Node temp = node.rightChild;
        Node originalParent = node.parent;

        // 2. connect B and x
        Node B = temp.leftChild;
        node.rightChild = B; // so at this point, B get both node-x and temp-y points to it
        // make sure B is not a leaf, update nodes' values for B and x
        if (B != null){
            // Connect B with parent node: node
            B.parent = node;
        }

        // 3. connect y and x's parent
        // if x's parent is null. then x is the root, so change root to be y
        if (originalParent == null){
            root = temp;
            temp.parent = null;
        } // if the originalParent is not null
        else {
            // if x is less than x's parent, x is the left child, so y is one of the left childs
            if (node.compareTo(originalParent) < 0) {
                temp = originalParent.leftChild;
                originalParent.leftChild = temp;
                // connect y with initial parent
                temp.parent = originalParent;

            } else {
                temp = originalParent.rightChild;
                originalParent.rightChild = temp;
                // parent connect y with initial parent
                temp.parent = originalParent;
            }
        }

        // 4. connect x and y
        temp.leftChild = node;
        node.parent = temp;
    }

    public void rotateRight(RedBlackTree.Node<String> n){
        // 1. define node x and y
        // node: y
        // x: temp
        Node node = n;
        Node temp = node.leftChild;
        Node originalParent = node.parent;

        // 2. connect B and y
        Node B = temp.rightChild;
        node.leftChild = B; // so at this point, B get both node-x and temp-y points to it
        // make sure B is not a leaf, update nodes' values for B and x
        if (B != null){
            // Connect B with parent node: node
            B.parent = node;
        }

        // 3. connect y and x's parent
        // if x's parent is null. then x is the root, so change root to be y
        if (originalParent == null){
            root = temp;
            temp.parent = null;
        } // if the originalParent is not null
        else {
            // if x is less than x's parent, x is the left child, so y is one of the left childs
            if (node.compareTo(originalParent) < 0) {
                temp = originalParent.leftChild;
                originalParent.leftChild = temp;
                // connect y with initial parent
                temp.parent = originalParent;

            } else {
                temp = originalParent.rightChild;
                originalParent.rightChild = temp;
                // parent connect y with initial parent
                temp.parent = originalParent;
            }
        }

        // 4. connect x and y
        temp.rightChild = node;
        node.parent = temp;
    }

    /**
     * recursive function: recursively traverse the tree to make it a Red Black tree.
     * @param current
     */
    public void fixTree(RedBlackTree.Node<String> current) {
        // not sure if it is the original parent, or if it is necessary
        Node originalParent = current.parent;
        Node grandParent = current.parent.parent;
        Node parent = current.parent;
        Node aunt = getAunt(current);

        // 1) current is the root node. Make it black and quit.
        if (current == root){
            current.color = 1;
            return;
        }

        // 2) Parent is black. Quit, the tree is a Red Black Tree.
        if (parent.color == 1){
            return;
        }

        //3) The current node is red and the parent node is red.
        // The tree is unbalanced and you will have to modify it in the following way.
        if (current.color == 0 && parent.color == 0){

            // I. If the aunt node is empty or black, then there are four sub cases that you have to process.
            if (getAunt(current) == null || getAunt(current).color == 1){

                // A) grandparent ?parent(is left child)? current (is right child) case.
                if (isLeftChild(grandParent, parent) && isRightChild(parent, current)){
                    // Solution: rotate the parent left and then continue recursively fixing the tree starting with the original parent.
                    rotateLeft(parent);
                    fixTree(originalParent);
                }

                // B) grandparent ?parent (is right child)? current (is left child) case.
                if (isRightChild(grandParent, parent) && isLeftChild(parent, current)){
                    // Solution: rotate the parent right and then continue recursively fixing the tree starting with the original parent.
                    rotateRight(parent);
                    fixTree(originalParent);
                }

                //C) grandparent ?parent (is left child)? current (is left child) case.
                if (isLeftChild(grandParent, parent) && isLeftChild(parent, current)){
                    // Solution: make the parent black, make the grandparent red,
                    // rotate the grandparent to the right and quit, tree is balanced
                    parent.color = 1;
                    grandParent.color = 0;
                    rotateRight(grandParent);
                    return;
                }

                // D) grandparent ?parent (is right child)? current (is right child) case.
                if (isRightChild(grandParent, parent) && isRightChild(parent, current)){
                    //Solution: make the parent black, make the grandparent red,
                    // rotate the grandparent to the left, quit tree is balanced.
                    parent.color = 1;
                    grandParent.color = 0;
                    rotateLeft(grandParent);
                    return;
                }
            }

            // II. Else if the aunt is red, then make the parent black,
            // make the aunt black, make the grandparent red
            // and continue recursively fix up the tree starting with the grandparent.
            else if (aunt.color == 0){
                parent.color = 1;
                aunt.color = 1;
                grandParent.color = 0;
                fixTree(grandParent);
            }
        }

    }

    public boolean isEmpty(RedBlackTree.Node<String> n){
        if (n.key == null){
            return true;
        }
        return false;
    }

    public void preOrderVisit(Visitor<String> v) {
        preOrderVisit(root, v);
    }

    private static void preOrderVisit(RedBlackTree.Node<String> n, Visitor<String> v) {
        if (n == null) {
            return;
        }
        v.visit(n);
        preOrderVisit(n.leftChild, v);
        preOrderVisit(n.rightChild, v);
    }

    /**
     * returns the sibling node of the parameter If the sibling does not exist, then return null.
     * @param n
     * @return
     */
    public RedBlackTree.Node<String> getSibling(RedBlackTree.Node<String> n){
        if (isLeftChild(n.parent, n)){
            return n.parent.rightChild;
        }
        else
            return n.parent.leftChild;
    }

    /**
     * returns the aunt of the parameter or the sibling of the parent node.
     * If the aunt node does not exist, then return null.
     * @param n
     * @return
     */
    public RedBlackTree.Node<String> getAunt(RedBlackTree.Node<String> n){
        if ((n.parent != null)){
            return getSibling(n.parent);
        }
        else
            return null;
    }

    /**
     * returns the parent of your parent node, if it doesn?t exist return null.
     * @param n
     * @return
     */
    public RedBlackTree.Node<String> getGrandparent(RedBlackTree.Node<String> n){
        return n.parent.parent;
    }

    public boolean isLeftChild(RedBlackTree.Node<String> parent, RedBlackTree.Node<String> child)
    {
        if (child.compareTo(parent) < 0 ) {//child is less than parent
            return true;
        }
        return false;
    }

    public boolean isRightChild(RedBlackTree.Node<String> parent, RedBlackTree.Node<String> child)
    {
        if (child.compareTo(parent) > 0 ) {//child is greater than parent
            return true;
        }
        return false;
    }

}

