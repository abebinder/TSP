import java.util.*;
import java.io.*;
public class Controller{
  public static void main(String[]args){
  FileManipulation fileman= new FileManipulation();
  TSP tsp=new TSP();
  Scanner sc=new Scanner(System.in);
  System.out.print("What is the name of file where the city matrix is stored?\n(Make sure file is working directory)");
  String filename=sc.nextLine();
  int[][]matrix=fileman.readFile(filename);

  ArrayList<Integer>citiestouched=new ArrayList<Integer>();
  
  tsp.minpath= tsp.smartStartRepeated(matrix);
  
  
  int smartmin=tsp.findMinPath(matrix,0,matrix.length,0,citiestouched);
  
  
  System.out.println("Mininimum Path Length is: "+smartmin);
  System.out.println("Order of Cities:");
  for(int i=0; i<tsp.bestroute.size(); i++){
    System.out.println(tsp.bestroute.get(i));
  } 
  
  }
}