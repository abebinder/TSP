import java.util.*;
import java.io.*;
public class Controller{
  public static void main(String[]args){
  FileManipulation fileman= new FileManipulation();
  TSP tsp=new TSP();
  int[][]matrix=fileman.readFile("tsp_15");

  ArrayList<Integer>citiestouched=new ArrayList<Integer>();
  
  tsp.minpath= tsp.smartStartRepeated(matrix);
  
  
  int smartmin=tsp.findMinPath(matrix,0,matrix.length,0,citiestouched);
  
  System.out.println(smartmin);
  
  for(int i=0; i<tsp.bestroute.size(); i++){
    System.out.println(tsp.bestroute.get(i));
  } 
  
  }
}