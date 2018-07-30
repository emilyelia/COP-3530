/* the class and the constcutors are from OpenData structures.com like prof. Resch suggested I did very little to change the contents of it. */
public class HuffInternalNode implements HuffBaseNode {
  private int weight;
    //objects
  private HuffBaseNode left;  
  private HuffBaseNode right; 

  /** Constructor */
    public HuffInternalNode(HuffBaseNode l, HuffBaseNode r) {
      this.left = l;
      this.right = r;
     weight = left.weight() + right.weight();
  }
    public HuffInternalNode(HuffBaseNode left, HuffBaseNode right, int weight) {
        
        this.left = left;
        this.right = right;
        this.weight = weight;
    }
  /** @return The left child */
public  HuffBaseNode left() {
      return this.left;
  }
    //setter method
    public void setLeft() {
         this.left=left;
    }
  /** @return The right child */
public  HuffBaseNode right() {
      return this.right;
  }
    //setter method
    public void setRight() {
         this.right=right;
    }
  /** @return The weight */
 public int weight() {
      return this.weight;
  }
    //setter method
    public void setWeight() {
    this.weight=weight;
    }

  /** Return false */
 public boolean isLeaf() {
      return false;
  }
}
