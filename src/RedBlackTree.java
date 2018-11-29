import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;

import static java.lang.System.currentTimeMillis;
import static java.lang.System.in;

// Key is the general type
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

//        public boolean isLeaf(){
//            if (this.equals(root) && this.leftChild == null && this.rightChild == null)
//                return true;
//            if (this.equals(root))
//                return false;
//            if (this.leftChild == null && this.rightChild == null){
//                return true;
//            }
//            return false;
//        }
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

    public void preOrderVisit(Visitor<String> v) {
        preOrderVisit(root, v);
    }

    private static void preOrderVisit(RedBlackTree.Node<String> n, Visitor<String> v) {
        if (n == null) {
            return;
        }
//        v.visit(n);
        System.out.println(n.key + n.color);
        //
        if (n.leftChild != null) {
//            System.out.println(n.leftChild.key);
        }
        if (n.rightChild != null){
//            System.out.println(n.rightChild.key);
        }
        //
        preOrderVisit(n.leftChild, v);
        preOrderVisit(n.rightChild, v);
    }

    public void insert(String data) {
        addNode(root, data);
//        insert(root, data);
    }

    /**
     * place a new node in the RB tree with data the parameter and color it red.
     * @param data the data wants to be added
     */
    public void addNode(Node node, String data) {  	//this < that  <0.  this > that  >0
        Node temp = null;
        // give the data a node
        Node insertNode = new Node(data);
        //
        String keyTemp;
        //
        String sibKey;

        // at the first time, root passed into node, if the root is null
        if (node == null){
            root = insertNode;
            insertNode.color = 1;
        } else {
            // keep tracking down until reach to leaf, then add the node on the leaf
            while (node != null) {
                temp = node;
                // if node is less than data, data is greater than the node's value, then goes right
                if (node.key.compareTo(data) < 0) {
                    node = node.rightChild;
                } else {
                    node = node.leftChild;
                }
            }
            insertNode.parent = temp;
            //
            keyTemp = (String) temp.key;
            System.out.println(keyTemp);

            //debug ---
            if (getSibling(temp) != null){
                Node sib = getSibling(temp);
                //
                sibKey = (String) sib.key;
                System.out.println(sibKey);
            }
            // ----
            if (temp.key.compareTo(data) < 0) {
                temp.rightChild = insertNode;
            } else {
                temp.leftChild = insertNode;
            }
        }
        insertNode.leftChild = null;
        insertNode.rightChild = null;
        insertNode.color = 0;
        fixTree(insertNode);
    }

//    private Node insert(Node node, String data) {
//        Node temp = null;
//        Node insertNode = new Node(data);
//        while (node != null) {
//            temp = node;
//            if (node.key.compareTo(data) > 0)
//                node = node.leftChild;
//            else
//                node = node.rightChild;
//        }
//        insertNode.parent = temp;
//        if (temp == null) {
//            root = insertNode;
//            insertNode.color = 1;//black root
//            return root;
//        } else {
//            if (insertNode.key.compareTo(data) < 0)
//                temp.leftChild = insertNode;
//            else
//                temp.rightChild = insertNode;
//        }
//        insertNode.leftChild = null;
//        insertNode.rightChild = null;
//        insertNode.color = 0;
//        fixTree(insertNode);
//        return root;
//
//    }

    /**
     * recursive function: recursively traverse the tree to make it a Red Black tree.
     * @param current
     */
    public void fixTree(RedBlackTree.Node<String> current) {
        // debug
        String keyValue = current.key;

        Node parent = null;
        String keyP;
        Node grandParent = null;
        String keyGP;
        Node aunt = null;
        String auntK;

        // not sure if it is the original parent, or if it is necessary
        if (getParent(current) != null){
            parent = getParent(current);
            keyP = getParent(current).key;
//            System.out.println(keyP);
        }
        if (getGrandparent(current) != null) {
            grandParent = getGrandparent(current);
//            keyGP = getGrandparent(current).key;
//            System.out.println(keyGP);
        }
        if (getAunt(current) != null) {
            aunt = getAunt(current);
//            auntK = getAunt(current).key;
//            System.out.println(auntK);
        }

        // 1) current is the root node. Make it black and quit.
        if (current.parent == null){
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
                    // Solution: rotate the parent left and then
                    // continue recursively fixing the tree starting with the original parent.
                    rotateLeft(parent);
                    fixTree(parent);
                }

                // B) grandparent ?parent (is right child)? current (is left child) case.
                if (isRightChild(grandParent, parent) && isLeftChild(parent, current)){
                    // Solution: rotate the parent right and then
                    // continue recursively fixing the tree starting with the original parent.
                    rotateRight(parent);
                    fixTree(parent);
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
        Node originalParent = null;

        if (getParent(node) != null){
            originalParent = getParent(node);
        }

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
        Node originalParent = null;

        if (getParent(node) != null){
            originalParent = getParent(node);
        }

        // 2. connect B and y
        Node B = temp.rightChild;
        node.leftChild = B; // so at this` point, B get both node-x and temp-y points to it
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
     * searches for a key.
     * @param k
     * @return
     */
    public boolean lookup(String k){
        //fill
        return lookup(root, k);
    }

    public boolean lookup(Node node, String k){
        //fill
        boolean found = false;
        while (node != null && !found){
            // if the node's value is less than the look up value "k", then go left
            if (node.key.compareTo(k) > 0){
                node = node.leftChild;
            } else if (node.key.compareTo(k) < 0){
                node = node.rightChild;
            } else {
                found = true;
                break;
            }
        }
        return found;
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
        if (node != null) {
            System.out.print(node.key);
        }
        if (isLeaf(node)){
            return;
        }
        printTree(node.leftChild);
        printTree(node.rightChild);
    }

    /**
     * if the node exist but the key doesn't
     * @param n node
     * @return true if the node doesn't have a value, false if the node has a value
     */
    public boolean isEmpty(RedBlackTree.Node<String> n){
        if (n.key == null){
            return true;
        }
        return false;
    }

    /**
     * returns the sibling node of the parameter If the sibling does not exist, then return null.
     * @param n
     * @return
     */
    public RedBlackTree.Node<String> getSibling(RedBlackTree.Node<String> n){
        if (n.parent != null) {
            if (isLeftChild(n.parent, n)) {
                return n.parent.rightChild;
            } else
                return n.parent.leftChild;
        }
        else
            return null;
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

    public RedBlackTree.Node<String> getParent(RedBlackTree.Node<String> n){
        if ((n.parent != null)){
            return n.parent;
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
        if (n.parent != null && n.parent.parent != null){
            return n.parent.parent;
        }
        else
            return null;
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

