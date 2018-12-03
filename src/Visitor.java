public interface Visitor<Key extends Comparable<Key>> {
    /**
     This method is called at each node.
     @param n the visited node
     */
    void visit(RedBlackTree.Node<Key> n);
}