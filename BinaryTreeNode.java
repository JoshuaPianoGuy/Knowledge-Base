/**The BinaryTreeNode class creates a node for a Binary Tree
 * 
 * @author Jan Smuts (code used from Jan Smuts Data Structure slides)
 */


public class BinaryTreeNode{
    ArrayInformation data;
    BinaryTreeNode left;
    BinaryTreeNode right;

    /**
     * The constructor creates a node for the tree, and sets the pointers to the left and right children to null
     * as they don't exist yet
     * @param d This is the data of ArrayInformation type
     * @param l This is the refernce to the left child.
     * @param r This is the reference to the right child.
     */
    public BinaryTreeNode (ArrayInformation d, BinaryTreeNode l , BinaryTreeNode r){
        data = d;
        left = l;
        right = r;
    }

    /** This returns a reference to the left child
     * @return The reference to the left child
     */
    public BinaryTreeNode getLeft(){
        return left;
    }

    /** This returns a reference to the right child
     * @return The reference to the right child
     */
    public BinaryTreeNode getRight(){
        return right;
    }


}