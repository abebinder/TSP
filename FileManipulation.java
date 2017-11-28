import java.io.*;
import java.util.*;
public class FileManipulation{ 

  int[][] readFile(String s){
    int[][]dummyarray=new int[1][1];
    File tsp= new File(s); //instantiates a new object of type file with parameter of the filename from the working directory
    String allnumbers="";
    int citynumber=0;
    try{
      Scanner fileScanner2 = new Scanner(tsp); //tries making a Scanner to read that file, needs to be in try/catch or it wont work
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
  } //method to read any file in the working directory with string as parameter
}