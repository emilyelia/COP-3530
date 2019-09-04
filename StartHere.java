import java.util.Scanner;
public class StartHere {
   
		public static void main(String[] args) {
            Scanner sc=new Scanner(System.in);
            //create matrix
         
            boolean choices = true;
            System.out.println("how many rows are in the matrix ?");
            int numrows = sc.nextInt();
            
       
            System.out.println("how many columns are in the matrix ?");
            int numcol = sc.nextInt();
            int colZero = numcol;
            SparseMatrix m1 = new SparseMatrix();

            //ensure there is a matrx to work with
          
            //formula for the number of elements that exist in the matrix
            int size = numrows*numcol;
             m1.setSize(size);
  
            while (choices) {
                System.out.println("Choose your adventure: ");
            System.out.println("\n\t1. Add Element\n\t2. Remove Element\n\t3. Return the size");
            System.out.println(" \t4. Calculate Determinant \n\t5.To String \n\t6. Change Size\n\t7. Clear");
            System.out.println("\t8. End Game");
            int choice = sc.nextInt();
          // m1.getRownum(numrows);
            if (choice==1) {
                System.out.println("what row do you want to add the element to ?");
                int row = sc.nextInt();
                System.out.println("what column do you want to add the element to ?");
                int col = sc.nextInt();
                
                System.out.println("what is the element ?");
                  int data = sc.nextInt();
                if (data!=0) {
                     m1.addElement(row, col, data);
                }
              
            }
            
            if (choice==2) {
                System.out.println("which row would you like to remove ?" );
                int removeRow =sc.nextInt();
                System.out.println("which column would you like to remove ?");
                int removeCol = sc.nextInt();
               m1.removeItem(removeRow, removeCol);
            }
                if (choice==3) {
                   System.out.println("size: " + m1.getSize());
                }
                if (choice==4) {
                    System.out.println("Determinant is: " + m1.determinant());

                }
                if (choice==5) {
                    
                System.out.println(m1.toString());
                }
                if (choice==6) {
                    m1.setSize(size);
               
                }
                if (choice ==7) {
                    
                    m1.clear();
                }
                if (choice==8) {
                System.out.println("exiting the program.");
                choices=false;
                break;
            }
                else {
                    System.out.println("invalid input. Enter a # 1-8");
                }
            }
		}
	}
