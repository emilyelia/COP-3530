public class InMatrix extends SparseMatrix {
    private int row;
    private int col;
    private int data;
   
    public InMatrix(int row, int col, int data) {
    this.row = row;
    this.col = col;
    this.data = data;
  }
  
   public int getData() {

    return this.data;
  }
   
    public void setData(int data) {

	this.data=data;
  }
    
    public int getRow() {

    return this.row;
  }
    public void  setRow() {

     this.row=row;
  }

   public int getCol() {

    return this.col;
  }
   public void setCol () {
       this.col=col;
   }
    @Override
    public boolean equals(Object InMatrix) {
        
        return (this.row == ((InMatrix)InMatrix).row && this.col == ((InMatrix)InMatrix).col);
    }
    
}
    
    
    
    
