import java.util.Comparator;

class MatrixComp implements Comparator<InMatrix>{
    //runtime O(1)
    //orders the matrix based off of the row numbers
    // compare the coordinates 
    public int compare(InMatrix e1, InMatrix e2) {
        //row numbers
        int r1 = e1.getRow();
        int c1 = e1.getCol();
        
        int r2 = e2.getRow();
        int c2 = e2.getCol();
        //the returned value will determine the order of the linked list
        if (r1 < r2) {
            return -1;
        } 
        else if (r1 == r2) {
            if (c1 < c2) {
                return -1;
            }
            else if (c1 == c2) {
                return 0;
            }
            else {
                return 1;
            }
        }
        else {
            return 1;
        }
    }
}


