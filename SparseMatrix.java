qimport java.util.LinkedList;
import java.util.Iterator;
import java.util.Collections;

public class SparseMatrix implements SparseInterface {
    private int size;
    private LinkedList<InMatrix>  matrix;
    private int numRows;
    private int numCols;
    
    // Constructor method.
    
    public  SparseMatrix() {
        //creates a 5X5 sparse matrix as dictated by the TAs
        this.matrix = new LinkedList <InMatrix>();
        //this line of code creates a defualt matrix of size 5X5. This line is used for testing the program
        //if the size is not supposed to be 25 then another size will be programed in
        size=5;
    }
    
    public SparseMatrix(int size, LinkedList<InMatrix> matrix) {
        this.size = size;
        this.matrix = matrix;
    }
    public SparseMatrix(int numRows, int numCols){
        this.numRows=numRows;
        this.numCols=numCols;
    }
    //this method clears the matrix O(1) time
    @Override
    public void clear() {
        //points the list to an empty linked list
        LinkedList<InMatrix> zeros = new LinkedList<InMatrix>();
        this.matrix=zeros;
    }
    
    @Override
    //this method clears the matrix O(1) time after the marix is cleared it will set the size of the matrix
    public void setSize(int numRows, int numCols){
        this.numRows=numRows;
        this.numCols=numCols;
    }
    //size getter method O(1)
    public int getSize(int size) {
        return this.size;
    }
    //gets the number of rows O(1)
    public int getNumRows() {
        this.numRows=numRows;
        return numRows;
        
    }
    //gets and returns the # of cols O(1)
    public int getNumCols() {
        this.numCols=numCols;
        return numCols;
    }
    //get the matrix O(1)
    public LinkedList <InMatrix> getMatrix (){
        this.matrix=matrix;
      
        return matrix;
    }

        /* this method removes items from the linked list. It does it without clearing the matrix like in get element. This also will remove an element that is already in place if another element is entered to be in that place complexity O(1)  */
    
       @Override
        public void addElement(int row, int col, int data) {
        int x = getNumRows();
        int y =getNumCols();
        if (data==0) {
        //try {
           // if (data==0)
            removeItem(row,col,data);
            return;
        }
          //  //DEBUG
          //  throw new OutOfBoundsException("can not add zero");
       // }
        //catch (OutOfBoundsException e) {
          //  System.out.println(e.toString());
       // }
        
        try {
            if (!(row < size && col < size))
                throw new OutOfBoundsException("Not a valid row or column number.");
           
        }
        catch (OutOfBoundsException e) {
            System.out.println(e.toString());
            return;

        }
        try {
            if (row<0 || col<0)
                throw new OutOfBoundsException("Can not have a negative row column combo");
        }
        catch (OutOfBoundsException e) {
            System.out.println(e.toString());
            return;
        }
        if (data!=0){
            
        removeItem(row, col,data);
        matrix.add(new InMatrix(row, col, data));
    
      }
        
    }
    /* Part of the SparseInterface it removes the element from the given position throws error if input is invalid
     Runtime O(1)*/
    public void removeElement(int row, int col){
        int xx = getNumRows();
        int y =getNumCols();
        try {
            if (!(row <xx &&col <y))
                throw new OutOfBoundsException("Not a valid row or column number.");
        }
        catch (OutOfBoundsException e) {
            System.out.println(e.toString());
            return;
        }
        try {
            if (row<0 || col<0)
                throw new OutOfBoundsException("Can not have a negative row column combo");
        }
        catch (OutOfBoundsException e) {
            System.out.println(e.toString());
            return;
        }
        
      
        int index = matrix.indexOf(new InMatrix(row, col, 0));
        int x =((InMatrix)matrix.get(index)).getData();
        //removes the specified element
        matrix.remove(new InMatrix(row, col, x));
    }
    //removes an element from the matrix
    //brute force, less coplicated than getElement and handels data with row and col
    //if an element already exists in that place then it removes it regardless
    //crucial for addElement
    //O(1)
    public void removeItem(int row, int col,int data) {
        
        // check to see if valid
        try {
            
            if (!(row < size && col < size) || (row < 0) || (col < 0)) throw new OutOfBoundsException("Not a valid row or column number.");
        }
        catch (OutOfBoundsException e) {
            System.out.println(e.toString());
            return;
        }
        // remove the element
        
        matrix.remove(new InMatrix(row, col, data));
    }
    //getter for the size of the matrix O(1)
    public int getSize () {
        return this.size;
    }
    //add an element to the matrix. If out of bounds, it throws an error. If an element already exists in that slot, it removes it by calling
    //the remove item method above
    //O(1)
    //does what is stated in the SparseInterface retruns the data that is not removed runtime O(1)
    @Override
    public int getElement(int row, int col) {
        
        //check to see if the row and column are valid arguments
        try {
            //check input
            if (!(row < size && col < size) || (row < 0) || (col < 0))
                throw new OutOfBoundsException("Not a valid row or column.");
        }
        catch (OutOfBoundsException e) {
            
          //print error message
            System.out.println(e.toString());
            return 0;
        }
        
        //get the index ensures the right element is removed and returned
        int index = matrix.indexOf(new InMatrix(row, col, 0));
        
        if (index == -1) {
          
            return 0;
        }

     return   ((InMatrix)matrix.get(index)).getData();
    
    }


    @Override
    //toStrng O(n) prints out the string. Uses comparators to ensure proper order of the string
    public String toString () {
        //calls Matrix Comp to sort the matrix using comparators
        Collections.sort(matrix, new MatrixComp());
        Iterator<InMatrix>it =matrix.iterator();
        InMatrix x;
        String result ="";
        while (it.hasNext()) {
            x = (InMatrix)it.next();
            // /* add the node's particular data items to the string and add a new line */
            //in the row col data form that was described
          
           result += x.getRow() + " " + x.getCol() + " " + x.getData() + "\n";
        }
        //return the string that we have been putting data on
        
        return result;
    }


    @Override
    /* This method takes in a sparse matrix and adds it to another spares matrix
     if checks to make sure both matircies have the same size then it adds them stores them and the size of the final
     matrix to be returned
     the runtime is O(N) *O(N) =O(N^2) where n is the number of elements in the matrix */
    public SparseInterface addMatrices(SparseInterface matrixToAdd) {
        int numRows;
        int numCols;
       int sparseRows= matrixToAdd.getNumRows();
        int sparseCols=matrixToAdd.getNumCols();
        int matRows =getNumRows();
        int matCols = getNumCols();
        if(matCols!=sparseCols && matRows != sparseRows) {
           
            return null;
        }
      
        int size = getSize();
//if one has made it to this point in the program the # of rows and cols will be the same
        numRows=sparseRows;
        numCols=sparseCols;
       //System.out.println(" what would you do if i told you that i lalala loved you!");
        LinkedList <InMatrix> finalSum = new LinkedList<InMatrix>();

        for (int i=0; i<numRows; i++) {
            for (int j=0; j<numCols; j++) {
                int x =getElement(i,j);
                int y = matrixToAdd.getElement(i,j);
                //if both of the getElements return 0 then nothing is there so it should not
                //be added to the final part of this
                if (x==0 && y==0) {
                    continue;
                }
                int sum= x+y;
                if (sum==0 ){
                    removeElement(i,j);
                }
                else {
                    finalSum.add( new InMatrix(i,j, sum));
                }
              
            }
        
        }
        return new SparseMatrix(size, finalSum);
        }
        /* This method takes in a sparse interface object and checks to ensure that the parameters are correct
         and then it multiplies the two matricies together
         Runtime O(n^3) */
    public SparseInterface multiplyMatrices(SparseInterface matrixToMultiply) {
     
        int sparseRows= matrixToMultiply.getNumRows();
        int sparseCols=matrixToMultiply.getNumCols();
        int numRows;
        int numCols;
        int count =0;
        int matixSize= matrix.size();
       // And the result will have the same number of rows as the 1st matrix, and the same number of columns as the 2nd matrix.
        //this is what the diemsions of the result will be
        int numColsMat = getNumCols();
        int numRowsMat = getNumRows();
        int dummy =(numColsMat) *(numRowsMat);
        int numRowsSparse=  matrixToMultiply.getNumRows();
        int numColsSparse = matrixToMultiply.getNumCols();
        numRows=numRowsMat;
        numCols=numColsSparse;
        //the linkedlist matrix is the one that controls the number of rows in the actual matrix
        if(matixSize<dummy) {
           // if(dummy%3==0) {
                numRows=matixSize/3 +dummy%3;
            //}
        }
        int finalSize=(numRows)*numCols;
        finalSize=finalSize-1;
          LinkedList <InMatrix> finalProduct = new LinkedList<InMatrix>();
         //The number of columns of the 1st matrix must equal the number of rows of the 2nd matrix
        if(numColsMat !=numRowsSparse) {
            return null;
        }

        int element=0;
     
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j< numColsSparse; j++) {
                for (int k= 0;  k<numColsMat; k++) {
              
                     element +=getElement(i,k) * matrixToMultiply.getElement(k,j);
                  
            }
                if (element==0) {
                    continue;
                }

                finalProduct.add(new InMatrix(i,j,element));
                if (element==0) {
                    //don't allocate memory for zeros in a sparse matrix
                    removeElement(i,j);
                }

                //elemetn needs to be reset in so the matrix is not summed up this is not stored in memory
                
                element=0;
     }
    
        }
          return new SparseMatrix(dummy, finalProduct);
    
}
}

