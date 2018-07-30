/* the class and the constcutors are from Open Data Structures.com like prof. Resch suggested. I did very little to edit it what they had. */
public class HuffLeafNode implements HuffBaseNode {
  private char element;      
  private int weight;

  /** Constructor */
  public HuffLeafNode(char el, int wt){
   this.element = el;
  this.weight = wt;
      
 }

  /** @return The element value */
  char value() {
  return this.element;
      
  }
    @Override
    public boolean isLeaf() {
        return true;
        
    }

  /** @return The weight */
    @Override
  public int weight() {
      return this.weight;
      
  }


   
}
