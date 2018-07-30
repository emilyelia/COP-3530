import java.util.ArrayList;
import java.lang.StringBuilder;
 /* The getters setters and constructors for this class are from open data structures. I did very little to chage them. Traverse tree methods are not from open data structures*/
public class HuffTree {
    
    private HuffBaseNode root;
    //constructors for 2 ways the root could be (where params came from)
    public HuffTree(char element, int weight) {
        
        this.root = new HuffLeafNode(element, weight);
    }
    //default constructor
    public HuffTree (){
       this.root=root;
    }
    
    public HuffTree(HuffBaseNode left, HuffBaseNode right, int weight) {
        this.root = new HuffInternalNode(left, right, weight);
    }
    
    public HuffTree(HuffBaseNode root) {
        
        this.root = root;
    }
    //getters and setters for the hufftree
    public HuffBaseNode getRoot() {
        
        return this.root;
    }
    
    public int getWeight() {
        
        return root.weight();
    }
    /* Traverse tree methods: there needs to be two becasue traverseTree in HuffmanEncoder only passes two params to the method. An additonal method is needed to overwrite it so all three values can be worked with*/
    public void traverseTree(HuffBaseNode node, String [] codes) {
        //if there is nothing; do nothing
        if (codes == null || node == null) {
            return;
        }
        //leaf is not the same as not leaf or null so need to pass control here
           else if (node.isLeaf()==true){
               //leaf does not mean null!!!! could be a sole asc ii char or a string of the same asc ii char which could be a collison if not handeled properly otherwise still need to pass control to other method
               //0=root is a node
            traverseTree(node, codes, "0");
        }
        else{
        traverseTree(((HuffInternalNode)(node)).right(), codes, "1");
        
        traverseTree(((HuffInternalNode)(node)).left(), codes, "0");
        }
    }
    //this should only be accessed by other members in THIS class; not huffman eoncder so it is private gives the final version of the tree traversak
    private void traverseTree(HuffBaseNode node,  String [] codes, String code) {
        
        if (node.isLeaf()==true) {
            codes[( (HuffLeafNode) node ).value()] = code;
            code +=((HuffLeafNode) node).weight();
        }
        
        else {
            traverseTree(  ( (HuffInternalNode)(node) ).right(), codes, code + "1");
            
            traverseTree(  ( (HuffInternalNode)(node) ).left(), codes, code + "0");
        }
    }
}
