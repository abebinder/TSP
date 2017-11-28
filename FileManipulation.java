import java.io.*;
import java.util.*;
public class FileManipulation{ 

  int[][] readFile(String s){
    int[][]dummyarray=new int[1][1];
    File tsp= new File(s); 
    String allnumbers="";
    int citynumber=0;
    try{
      Scanner fileScanner2 = new Scanner(tsp); 
      citynumber=Integer.parseInt(fileScanner2.nextLine());
      int[][]matrix=new int[citynumber][citynumber];
      int counter=0;
      while(fileScanner2.hasNextLine()){
        String line =fileScanner2.nextLine();
        String[]linearr=line.split(" ");
        for(int i=0; i<citynumber; i++){
        matrix[counter][i]=Integer.parseInt(linearr[i]);
        }
        counter++;
        }
       
      fileScanner2.close();
      return matrix;
    }catch(Exception e){System.out.println(e.getMessage());}
    return dummyarray;
  } 
}