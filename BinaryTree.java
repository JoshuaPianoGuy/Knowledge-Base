/** The BinaryTree class is an implementation of a Binary Search Tree (BST). It has methods to insert
 * new nodes, search nodes, update existing nodes and to both update existing nodes or add new
 * nodes to the tree.
 * 
 * @author Joshua Diegaardt (DGRJOS001), but adapted from Jan Smuts (Jan Smuts Data Structures lecture slides)
 */
public class BinaryTree  {
    BinaryTreeNode root;
    /*ANSI ecsape charcaters from https://www.tutorialspoint.com/how-to-print-colored-text-in-java-console#:~:text=Utilizing%20the%20ANSI%20Escape%20Codes%20for%20Text%20Color.&text=One%20must%20add%20the%20relevant,text%20to%20complete%20the%20process. */
    String RESET = "\u001B[0m";
    String RED = "\u001B[31m";
    String GREEN = "\u001B[32m";
    String CYAN = "\u001B[36m";
    String YELLOW = "\u001B[33m";
    String MAGENTA = "\u001B[35m";
    /**
     * The constructor initialises an empty BST by setting the root node (BinaryTreeNode type) to null
     */
    public BinaryTree(){
        root = null;
    }
    /**
     * This searches the tree nodes for a specific term
     * @param target The target is a given term which has an associated statement and confidence score (String)
     * @return String representation of the statement associated with the given term and the confidence score associated with the statement. 
     */
    public String searchTerm(String target){
        //checks if the tree is empty
        if (root == null){
            return YELLOW + "Term not found" + RESET;
        }
        else return searchTerm(target, root);
    } 
    /** 
     * @param target The target is the term to be searched for
     * @param node This is the node where each recursive call of the search starts
     * @return String representation of the assocaited statement and its confidence score 
     */
    public String searchTerm(String target, BinaryTreeNode node){
        int compare = target.compareToIgnoreCase(node.data.getTerm());
        //term found
        if (compare == 0){
            return GREEN + "Statement found: " + RESET + node.data.getStatement() + " (Confidence score: " + node.data.getConfidence() + ")";
        }
        //term in the left subtree
        else if (compare < 0){
            return (node.left == null) ? YELLOW + "Term not found" + RESET :  searchTerm(target, node.left);
        }
        //term in the right subtree
        else return (node.right == null) ? YELLOW + "Term not found" + RESET : searchTerm(target, node.right);
    }
    /**This inserts a node into the BST, taking the definition of a BST into account where each new
     * node is either placed to the left of some current node (if less than) and to the right
     * (if greater than)
     * @param data Data is an object of type ArrayInformation to be inserted as a node
     */
    public void insert(ArrayInformation data){
        //if the tree is empty, the node is inserted as the root node
        if (root == null){
            root = new BinaryTreeNode(data, null, null);
        }
        else insert(data, root);
    }
    /**
     * Helper method to insert data, starting from the root, traversing the entire tree to see the new node
     * should be inserted
     * @param data The node to be inserted which contains information of type ArrayInformation
     * @param node The node to which the new node is compared to see whether to traverse the left or right
     * subtree from that node
     */
    public void insert(ArrayInformation data, BinaryTreeNode node){
        //makes sure that the confidence is valid
        if (data.getConfidence() >=0 && data.getConfidence() <=1){
            //less than and placed in the left subtree
            if (data.compareTo(node.data) < 0){
                if (node.left == null){
                    node.left = new BinaryTreeNode(data, null, null);
                }
                else insert(data, node.left);
            }
            //greater than and placed in the right subtree
            else{
                if (node.right == null){
                    node.right = new BinaryTreeNode(data, null, null);
                }
                else insert(data, node.right);
        }       
        }
        else System.out.println(YELLOW + "Please enter a valid confidence score (between 0 and 1)." + RESET);
        return;
    }
    /**
     * This searches each node for a term, and a statement associated with that term
     * @param term The term is to be searched for (String)
     * @param statement The statement is associated with the term (STring)
     * @return String representation of the confidence score associated with the statement
     */
    public String fullSearch(String term, String statement){
        //String which contains the term and statement. This is used as the target for the recursive call
        String fullTarget = term + " " + statement.replaceAll(".", "");
        if (root == null){
            return YELLOW + "The term and statement were not found" + RESET;
        }
        else return fullSearch(fullTarget, root);
    }
    /**
     * Helper method of fullSearch()
     * @param target Given term and statement to be searched for (String)
     * @param node The node at which the search starts at each recursive call
     * @return String representation of the confidence score associated with the statement
     */
    public String fullSearch(String target, BinaryTreeNode node){
        int compare = target.compareToIgnoreCase(node.data.getTerm() + " " + node.data.getStatement().replaceAll(".",""));
        if (compare == 0) return GREEN + "The statement was found and has a confidence score of " + RESET + node.data.getConfidence();
        //search the left subtree
        else if (compare < 0){
            return (node.left == null) ? YELLOW +"The term and statement were not found" + RESET : fullSearch(target, node.left);
        }
        //search the right subtree
        else return (node.right == null) ? YELLOW +  "The term and statement were not found" + RESET : fullSearch(target, node.right);
    }
    
    /**
     * This updates an existing statement with a new statement if the new confidence score is equal to or greater
     * than the existing confidence score, and if the term is in one of the nodes.
     * @param term The term with which the statement is associated (String)
     * @param statement The new statement (String)
     * @param confidence The new confidence score (double)
     */
    public void updateStatement(String term, String statement, double confidence){
        if (root == null){
            System.out.println(YELLOW + "The knowledge base has not been loaded." + RESET);
            return;
        }
        else updateStatement(term, statement, confidence, root);
    }
    /**
     * Helper method to updateStatement() that checks if a term is present, and if the new confidence
     * is greater than the existing confidence. If so, the current statement is updated.
     * @param term The term to be searched for
     * @param statement The statement to be updated
     * @param confidence The confidence score used to determine if the existing statement is updated
     * @param node The node to which the previous informationis compared
     */
    public void updateStatement(String term, String statement, double confidence, BinaryTreeNode node){
        //check if the confidence score is valid
        if (confidence >= 0 && confidence <=1) {
            int compare = term.compareToIgnoreCase(node.data.getTerm());
        if ((compare == 0) && (confidence >= node.data.getConfidence())){
            node.data.setStatement(statement);
            node.data.setConfidence(confidence);
            System.out.println(GREEN + "The statement has been updated successfully! " + RESET + "The new statement is: " + statement );
        }
        //if the new confidence score is lower than the existing confidence
        else if ((compare == 0) && (confidence < node.data.getConfidence())){
            System.out.println(YELLOW + "The confidence score is too low. Please verify the information." + RESET);
            return;
        }
        else if (compare < 0){
            //check if the end of the tree has been reached
            if (node.left == null){
                System.out.println(YELLOW + "The given term is not in the knowledge base" + RESET );
            } 
            else updateStatement(term, statement, confidence, node.left);
        }
        else{
            //check if the end of the tree has been reached
            if (node.right == null){
                System.out.println(YELLOW + "The given term is not in the knowledge base" + RESET);
            }
            else updateStatement(term, statement, confidence, node.right);
        } 
        }
        else System.out.println(YELLOW + "Please enter a valid confidence score (between 0 and 1)." + RESET);
        return;          
    }

    /**
     * This updates the existing tree with new informtion. Information in each node is either updated if the new confidence
     * score associated with a statement and term is higher than the existing confidence score in  node, or a new node is
     * created if the information is not in the tree and the condfidence score is valid.
     * @param newData This is a new object of ArrayInformation type that is added to the BST.
     */
    public void updateTree(ArrayInformation newData){
        updateTree(newData, root);
    }
    /**
     * Helper method to updateTree() that compares an instance of ArrayInformation to the nodes in a tree
     * to see if the information in the nodes can be updated, or if the information can be inserted
     * @param data The data that is being compared to the nodes in the tree, to either update or insert (ArrayInformation)
     * @param node The node to which data (ArrayInformation) is compared
     */
    public void updateTree(ArrayInformation data, BinaryTreeNode node){
        int compare = data.compareTo(node.data);
        if (compare == 0){
            //ensures that only valid confidence scores are accepted
            if ((data.getConfidence() >= 0 && data.getConfidence() <=1) && (data.getConfidence() >= node.data.getConfidence())){
                node.data.setStatement(data.getStatement());
                node.data.setConfidence(data.getConfidence());
            }
        }
        else if (compare < 0){
            if (node.left == null){
                node.left = new BinaryTreeNode(data, null, null);
            }
            else updateTree(data, node.left);
        }
        else if (compare > 0){
            if (node.right == null){
                node.right = new BinaryTreeNode(data, null, null);
            }
            else updateTree(data, node.right);
        }
    }
    /**
     * This method randomly returns a verified piece of information(with a confidence score of 1) from the BST
     * @return String representation of the statement
     */
    public String funFact(){
        return funFact(root);
    }
    /**
     * Helper method that generates a random number, and traverses the tree while incrementing a counter until
     * this random number is reached.
     * @param root This is where the iterative implementation begins
     * @return String representation of the statement
     */
    public String funFact(BinaryTreeNode root) {
        if (root == null) {
          return YELLOW + "The tree is empty" + RESET;
        }
        int leftSize = getSize(root.left);
        /*returns the size of the smaller subtree as the upper bound for the random number. This ensures that a null node is not reached
        for example, if the left subtree is smaller and the random number is always less than this size and the entire left subtree
        is travered, then a null node will not be reached */
        int max = getSize(); 
        int min = 1; //BST doesn't need to start at 0 like with the indices of an array
        int randomNum = (int) ((Math.random() * (max - min)) + min)/2; // Generate random index bewteen 1 and max
      
        int currentCount = 1; // Keeps track of visited nodes and starts at 1
      
        while (root != null) {
          // Check if current node is the desired one based on randomNum and currentCount and confidence score
          if (currentCount == randomNum && root.data.getConfidence() == 1) {
            return MAGENTA + "Fun fact: " + RESET + root.data.getStatement();
          }
          // Update currentIndex based on subtree size
          leftSize = getSize(root.left);
          if (randomNum < leftSize) {
            root = root.left;
          } else {
            /*If randomNum is greater than the size of the left subtree, then the left subtree is out of range and 
            it won't contain the 'target'(where currentCount == randomNum) node as eventually a null node will be reached if it is traversed. 
            The subtraction ignores the rest of the left subtree and moves to the right subtree starting at the currentCount, 
            of the right subtree if the entire left subtree was traversed, allowing a more efficient traversal of the
            right subtree as the factor of randomness could result in a small or large number*/ 
            
            randomNum -= leftSize; 
            root = root.right;
          }
          currentCount++;
        }  
        /*If no node is found, then the funFact() method is called again until the currentCount
        is equal to the random number and a node is reached that has a confidence score of 1. The method
        is called a fun fact that has confidence score of 1 is found */ 
        
        return funFact();
      }

      /**
       * Returns the size of the tree starting at the root. It can be used to find the size of subtrees
       * if a child node is used instead of a root node.
       * @return Size of the tree
       */
    public int getSize(){
        if (root == null){
            return 0;
        }
        else return getSize(root);
    }
    /**
     * Helper method that gets the size of a tree, starting at some node.
     * @param node The node is the starting point from where the height is determined
     * @return The size of the tree (int)
     */
    public int getSize(BinaryTreeNode node){
        if (node == null){
            return 0;
        }
        return 1 + getSize(node.left) + getSize(node.right);
}
}
