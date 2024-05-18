import java.util.Scanner;
import java.io.*;

/**The GenericsKbBSTApp reads in information from a knowledge base (text file) and allows various
 * types of manipulation and query thorugh a user interface. This is similar to the ArrayApp, but instead a
 * Binary Search Tree (BST) is used, with the added functionality of updating the tree by adding new nodes.
 * 
 * @author Joshua Diegaardt (DGRJOS001)
 */

public class GenericsKbBSTApp {
    /**
     * 
     * @param args Argument for the main method
     * @throws FileNotFoundException FileNotFoundException is thrown if a given file does not exist
     */
    public static void main(String[] args)throws FileNotFoundException{
          /*ANSI ecsape charcaters from https://www.tutorialspoint.com/how-to-print-colored-text-in-java-console#:~:text=Utilizing%20the%20ANSI%20Escape%20Codes%20for%20Text%20Color.&text=One%20must%20add%20the%20relevant,text%20to%20complete%20the%20process. */
          String RESET = "\u001B[0m";
          String RED = "\u001B[31m";
          String GREEN = "\u001B[32m";
          String CYAN = "\u001B[36m";
          String YELLOW = "\u001B[33m";
        try {
            Scanner keyboard = new Scanner(System.in);
            String userOptions = "Choose an action from the menu:\n 1. Load a knowledge base from a file\n 2. Add a new statement to the knowledge base\n 3. Update an existing statement.\n 4. Search for an item in the knowledge base by term\n 5. Search for a item in the knowledge base by term and sentence\n 6. Fun Fact!\n 7. Quit";
            System.out.println("");
            File file = null;
            BinaryTree tree = new BinaryTree();
            boolean condition = true;
            String term;
            String statement;
            double confidence;
    
            while(condition){
                System.out.println(userOptions);
                System.out.print("Enter your choice: ");
                int choice = keyboard.nextInt();
                keyboard.nextLine();
                System.out.println("");
    
                switch(choice){          
                    case 1:
                        System.out.print("Enter file name: ");
                        String fileName = keyboard.nextLine();
                        file = new File(fileName);
                        Scanner fileScan = new Scanner(file);
    
                        //checks if the tree is empty--root would be null
                        if (tree.root == null){
                            while(fileScan.hasNext()){
                                String[] textLine = fileScan.nextLine().split("\t");
                                ArrayInformation obj = new ArrayInformation(textLine[0], textLine[1], Double.parseDouble(textLine[2]));
                                tree.insert(obj);
                            }
                            System.out.println(GREEN + "Knowledge base loaded successfully!" + RESET);
                        }
                        else{
                            while(fileScan.hasNext()){
                                String[] newTextLine = fileScan.nextLine().split("\t");
                                ArrayInformation newObj = new ArrayInformation(newTextLine[0], newTextLine[1], Double.parseDouble(newTextLine[2]));
                                tree.updateTree(newObj);
                            }
                            System.out.println(CYAN + "Knowledge base updated successfully!" + RESET);
                        }       
                        break;
    
                    case 2:
                    //prompts the user to load in a knowledge base if one has not been loaded yet
                    if (file == null) {
                        System.out.println(YELLOW + "Please load a knowledge base by entering 1." + RESET);
                        break;
                    } 
                        System.out.print("Enter term: ");
                        term = keyboard.nextLine();
                        System.out.print("Enter the statement: ");
                        statement = keyboard.nextLine();
                        System.out.print("Enter confidence score: ");
                        confidence = keyboard.nextDouble();
                        keyboard.nextLine();
                        ArrayInformation objNew= new ArrayInformation(term, statement, confidence);
                        tree.insert(objNew);
                        break;
                    
                    case 3:
                    //prompts the user to load in a knowledge base if one has not been loaded yet
                        if (file == null) {
                            System.out.println(YELLOW + "Please load a knowledge base by entering 1." + RESET);
                            break;
                        } 
                        System.out.print("Enter term: ");
                        term = keyboard.nextLine();
                        System.out.print("Enter the statement: ");
                        statement = keyboard.nextLine();
                        System.out.print("Enter confidence score: ");
                        confidence = keyboard.nextDouble();
                        keyboard.nextLine();
                        tree.updateStatement(term, statement, confidence);
                        break;
    
                    case 4:
                    //prompts the user to load in a knowledge base if one has not been loaded yet
                        if (file == null) {
                            System.out.println(YELLOW + "Please load a knowledge base by entering 1." + RESET);
                            break;
                        } 
                        System.out.print("Enter a term to search for: ");
                        term = keyboard.nextLine();
                        System.out.println(tree.searchTerm(term));  
                        break;
                    case 5:
                    //prompts the user to load in a knowledge base if one has not been loaded yet
                        if (file == null) {
                            System.out.println(YELLOW + "Please load a knowledge base by entering 1." + RESET);
                            break;
                        } 
                        System.out.print("Enter a term to search for: ");
                        term = keyboard.nextLine();
                        System.out.print("Enter a statement to search for: ");
                        statement = keyboard.nextLine();
                        System.out.println(tree.fullSearch(term, statement));
                        break;
                    case 6:
                    //prompts the user to load in a knowledge base if one has not been loaded yet
                        if (file == null) {
                            System.out.println(YELLOW + "Please load a knowledge base by entering 1." + RESET);
                            break;
                        }    
                        System.out.println(tree.funFact());
                        break;
                    case 7:
                        condition = false;
                        break;
                    default:
                        System.out.println("Please enter a valid choice.");
                        break;
                }
            }
            keyboard.close();
        }
        catch(FileNotFoundException e){
            System.out.println(RED + "Please enter a valid knowledge base" + RESET);
        }
        }  
}
