import java.util.ArrayList;
/* This clas was based off of the opendata structures class that prof. Resch suggested that we use */
public class minHeap {
    //for traversal
	 private int size;
    //for heap representation
	 private ArrayList<HuffTree> trees;
	 public minHeap() {
	 	trees = new ArrayList<HuffTree>();
	 	size = 0;
	 }

	 public int size() {

	 	return this.size;
	 }

	 public boolean isEmpty() {

	 	return size == 0;
	 }
    //inserts nodes into the hufftree
	 public void insert(HuffTree toAdd) {
	 	//add to the end of the list and increment size
	 	trees.add(toAdd);
	 	size++;

	 	//the child was the node that we inserted
         int child = size-1;
         int parent = (child-1)/2;
	 	HuffTree temp = null;

	 	/* while the parent isnt the child and the frequency of the parent is greater than that of the child  */
	 	while (parent != child && trees.get(parent).getWeight() > trees.get(child).getWeight()) {

	 		//swap parent and child
	 		temp = trees.get(parent);
	 		trees.set(parent, trees.get(child));
	 		trees.set(child, temp);
            //assert the final values
	 		child = parent;
	 		parent = (child-1)/2;
	 	}
	 }

	 public HuffTree removeMin() {

	 	//edge case nothing therefore no heap
         if (size == 0) {
             return null;
         }

	 	//grab the minimum element at the root and place it in min
	 	HuffTree min = trees.get(0);
	 	
	 	//set the root equal to the last element in the list
	 	trees.set(0, trees.get(size-1));

	 	//remove the last element in the list as it is now at the root
	 	trees.remove(size-1);
	 	size--;
 	//current node, its left and right children, a temporary variable holding the minimum between left and right, and a temp HuffTree holder
         int curr = 0;
         int left = 1;
         int right = 2;
         int swap = -1;
	 	HuffTree temp = null;

	 	/* while the left node is a valid node & the freq of the current node is > the frequency of the left same for right node */
	 	while (true) {

	 		if (right >= size) {

                  if (left >= size) break;
                  else swap = left;
            }
			else {
                  
                  if (trees.get(left).getWeight() <= trees.get(right).getWeight()) swap = left;
                  else swap = right;
            }
			if (trees.get(curr).getWeight() > trees.get(swap).getWeight()) {

				temp = trees.get(curr);
	 			trees.set(curr, trees.get(swap));
				trees.set(swap, temp);
                //heap children formula; from slides
	 			curr = swap;
	 			left = 2*curr+1;
	 			right = 2*curr+2;
            }
            else break;
	 	}
	 	return min;
	 }
}
