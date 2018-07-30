//Emily Elia
import java.util.Scanner;
import java.util.Deque;
import java.util.ArrayDeque;
import java.io.File;
import java.util.Hashtable;
import java.lang.StringBuilder;
import java.util.HashMap;
import java.util.Map;
import java.util.HashSet;
import java.util.Set;
import java.util.ArrayList;
public class HuffmanEncoder implements HuffmanCoding {
    //create the hashtable so it can be easily accessed
    private Hashtable x;
    public HuffmanEncoder () {
        this.x=null;
    }

	@Override
    /* reads the file; gets the frequencies for each letter
     Runtime O(n)*/
	public String getFrequencies(File inputFile) {
        //read the file
		Scanner input;
		try {
			input = new Scanner(inputFile);
            
		} 
		catch (Exception e) {
			 return e.toString();
		}
        //space = new char; used to count frequencies
		input.useDelimiter("");
        //holds the frequencies
		int[] freq = new int[128];
        int words =0;
		while (input.hasNext()) {
            freq[input.next().charAt(0)]++;
		}
        //temp is the character with the frequency together put it together
		StringBuilder temp = new StringBuilder();
		for (int i = 32; i < 127; i++) {
            if (freq[i] == 0){
                continue;
            }
            //add char & freq to the string in the proper format
        temp.append(String.valueOf((char)i) + " " + freq[i] + "\n");
		}
       String toRet =temp.toString();
 
        
        //close the scanner
        input.close();
        //return the string and the frequencies
        return toRet;
       
	}


	//take a file as input and create a Huffman Tree
    //runtime O( n log n)
    public HuffTree buildTree(File inputFile) {
        //get the string from the get frequencies method above
       String input =(String) getFrequencies(inputFile);
        //will return pass it to buildheap that in turn will build the tree and will return that string
        return buildTree(buildHeap(input));
    }
    
	
	//take a file and a HuffTree and encode the file.  
	//output a string of 1's and 0's representing the file
    //runtime O(n log (n))
	@Override
	public String encodeFile(File inputFile, HuffTree huffTree) {
        //if the input is not valid then return null
        if (inputFile == null || huffTree == null) {
            return null;
        }
        else {
        //build the code table and initialize the encoded string as an empty string
        Hashtable table = buildhashTable ( traverseHuffmanTree(huffTree) );
            String a = traverseHuffmanTree(huffTree);
        //this is the string that you are putting the hashtable in
       StringBuilder encoded = new StringBuilder();
        
        //make scanner
        Scanner input;
        
        //wrap scanner inside the file
       try {
          input = new Scanner(inputFile);
        }
        catch (Exception e) {
             return e.toString();
       }
        //use delimiter grab item char by char
        input.useDelimiter("");
        //go through char by char
        while (input.hasNext()) {
           //this is the key
            char e = input.next().charAt(0);
           encoded.append(table.get(e));
        }
            //string to be returned
        String toret =encoded.toString();
        //return the encoded string
            input.close();
            return toret;
        }
       
	}

	//take a String and HuffTree and output the decoded words
    //run time O( n log(n))
    @Override
    public String decodeFile(String code, HuffTree huffTree) {
    
        //if there is nothing in the string/tree; return null
            try {
                   if (code==null || huffTree==null)
                       throw new OutOfBoundsException("There is nothing in the tree");

                       }
            catch (Exception e) {
                return e.toString();
            }
        //get the table
        Hashtable table = buildhashTable (traverseHuffmanTree(huffTree));
        int size = table.size();
        //string to put final result in
        StringBuilder decodedString = new StringBuilder();
        int start =0;
        StringBuilder decoded = new StringBuilder();
        StringBuilder coded = new StringBuilder(code);
        int count =0;
        int ee = code.length();
        int counts =0;
         while (coded.length() != 0) {
            //pass char by char to the metod that actualtl does the decoding
            coded=decodetheString (coded, huffTree.getRoot(), decoded);
             counts++;
             //in case there is a leaf only case; ensures it will not be an infinite loop for an edge case
             if (counts>ee) {
                 break;
             }
        }
      //  System.out.println("decoded toString is " + decoded.toString());
        return decoded.toString();

    }
    //does the actual decoding work
    //runtime O( log n)
    public StringBuilder decodetheString (StringBuilder code, HuffBaseNode root, StringBuilder decoded) {
        int length = code.length();
        if (root.isLeaf()) {
            //return the leaf value
            decoded.append(String.valueOf( ((HuffLeafNode)root).value() )  );
            return code;
        }
      else if (length==0) {
          //if there is nothing add nothing
          StringBuilder x = new StringBuilder();
            x.append("");
            return x;
        }
        //gets the right child
        else if (code.charAt(0) == '1') {
            return decodetheString(code.deleteCharAt(0), ((HuffInternalNode)root).right(), decoded);
           
        }
        //gets the right left child
        else if (code.charAt(0) == '0') {
            return decodetheString(code.deleteCharAt(0), ((HuffInternalNode)root).left(), decoded);
        }
        //if nothing else, do nothing
       else  {
         decoded.append("");
         return code;
        }
  
    }
    //makes hashmap; use it to get keys; easier than hashmap for the keys
    //runtime O(n)
    public HashMap<String, String> makeHashMap (Hashtable y) {
        HashMap<String, String> x = new HashMap<String, String>();
        x.putAll(y);
        return x;
    }
    //Objective: print the characters and their codes
	@Override
   /* this method traverses the huffman tree.
    Runtime O(n log(n))*/
	public String traverseHuffmanTree(HuffTree tree) {
        String[] codes = new String[128];
        StringBuilder codeString = new StringBuilder();
        //if the tree is empty it will throw an error; an empty tree with no chars=null &cannot be traversed
        try {
            if (tree==null)
                throw new OutOfBoundsException("Tree is empty. Can not be encoded or decoded");
        }
        catch (Exception e) {
            return e.toString();
        }
        //passes control to the HuffTree to be traversed
        tree.traverseTree(tree.getRoot(), codes);
        //counter variables
        int i=0;
        int n=128;
        while (i<n) {
        if (codes[i]==null){
             i++;
                continue;
           
            }
            //DEBUG
            codeString.append( ((char)(i)) + " " + codes[i]+ "\n" );
           
        i++;
        }
        //String to be returned
     String toRet =codeString.toString();
                 return toRet;
        }
    


    /* this method was based off the method on open data strucutres and the class slides which is what prof. Resch recomended that we use */
    public HuffTree buildTree(minHeap heap) {
        HuffTree a, b;
        
        while (heap.size() > 1) {
            //calls removeMin method from minHeap class
            a = heap.removeMin();
            b = heap.removeMin();
            
            heap.insert(new HuffTree( new HuffInternalNode( a.getRoot(), b.getRoot(), a.getWeight()+b.getWeight() ) ) );
        }
        return heap.removeMin();
    }
    //hashtable should only be built in this class; It should not be accseed in any of the other classes
    //runtime o(n)
    private Hashtable buildhashTable(String code){
        if (x != null){
       return x;
        }
        if (code == null){
            return null;
        }
        //create a hashtable of 128 elements to be returned will map huffman values to english chars
        Hashtable xx = new Hashtable(128);
        //the start at 0
        int start = 0;
        String occurence;
        int iterate =code.length();
        //iterate over each character in code string
        for (int i = 0; i <iterate; i++) {
            //newline=new char
            if (code.charAt(i) == '\n') {
                //substring the occurence from its start to i
                occurence = code.substring(start, i);
                char e =occurence.charAt(0);
                String ee= occurence.substring(occurence.lastIndexOf(' ')+1);
                xx.put(e,ee);
                //get the next one and repeat.
                start = i + 1;
            }
        }
        this.x = xx;
        //return the filled table. if it equals null then it isnt present in the file.
        return xx;
    }
  /* this method was based off the method on open data strucutres and the class slides which is what prof. Resch recomended that we use runtime O (n) */
    public minHeap buildHeap(String frequencies) {
        minHeap heap = new minHeap();
        //the start of the new occurence. starts at 0 and then occurs at i + 2
        int start = 0;
        String occurence;
        
        for (int i = 0; i < frequencies.length(); i++) {
            //\n=new char therefore is will act as the delimeter
            if (frequencies.charAt(i) == '\n') {
              
                occurence = frequencies.substring(start, i);
                //insert into the heap in the proper place
                heap.insert( new HuffTree( occurence.charAt(0), Integer.valueOf(occurence.substring(occurence.lastIndexOf(' ')+1)) ));
                start = i + 1;
            }
        }

        return heap;
        
    }


}
