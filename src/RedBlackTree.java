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
        int color;

        public Node(Key data){
            this.key = data;
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

    public boolean spellCheck(String inputFile) {
        double start = currentTimeMillis();
        // check the word by calling lookup();

        double end = currentTimeMillis();
        double duration = end - start;
    }

    public boolean isLeaf(RedBlackTree.Node<String> n){
        if (n.equals(root) && n.leftChild == null && n.rightChild == null)
            return true;
        if (n.equals(root))
            return false;
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

    public void visit(Node<Key> n){
        System.out.println(n.key);
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
    public void addNode(String data){  	//this < that  <0.  this > that  >0
//        Time startTime = new Time();

        double start = currentTimeMillis();

        //	fill
        // read each line and add to the tree
        // http://www.math.sjsu.edu/~foster/dictionary.txt



        double end = currentTimeMillis();
        double duration = end - start;
    }

    public void insert(String data){
        addNode(data);
    }

    /**
     * searches for a key.
     * @param k
     * @return
     */
    public RedBlackTree.Node<String> lookup(String k){
        //fill
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
        if (isLeftChild(n.parent.parent, n.parent)){
            return n.parent.parent.rightChild;
        }
        else
            return n.parent.parent.leftChild;
    }

    /**
     * Similar to getAunt() and getSibling(), returns the parent of your parent node,
     * if it doesn?t exist return null.
     * @param n
     * @return
     */
    public RedBlackTree.Node<String> getGrandparent(RedBlackTree.Node<String> n){
        return n.parent.parent;
    }

    /**
     * left, resp. right, rotate around the node parameter.
     * @param n
     */
    public void rotateLeft(RedBlackTree.Node<String> n){
        //
    }

    public void rotateRight(RedBlackTree.Node<String> n){
        //
    }

    /**
     * recursive function: recursively traverse the tree to make it a Red Black tree.
     * @param current
     */
    public void fixTree(RedBlackTree.Node<String> current) {
        Node originalParent = current.parent;
        Node grandParent = current.parent.parent;
        Node parent = current.parent;

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
                if (isLeftChild(parent, current) && isRight)
                // Solution: rotate the parent left and then continue recursively fixing the tree starting with the original parent.
                rotateLeft(parent);
                fixTree(originalParent);

                // B) grandparent ?parent (is right child)? current (is left child) case.
                // Solution: rotate the parent right and then continue recursively fixing the tree starting with the original parent.
            }



        }

    }

    public boolean isEmpty(RedBlackTree.Node<String> n){
        if (n.key == null){
            return true;
        }
        return false;
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


}

