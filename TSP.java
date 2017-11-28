import java.util.*;
import java.io.*;
import java.util.*;
public class TSP{
  
  int genmin=0;
  int minpath=0;
  ArrayList<Integer>bestroute=new ArrayList<Integer>();
  ArrayList<Integer>route=new ArrayList<Integer>();
  
  
  
  
  public int findMinPath2(int[][]matrix, int city, int original, int path, ArrayList<Integer> citiestouched){  
    if(countOptions(matrix, city)==1){
      int continuedpath=path+matrix[city][0];
      if(continuedpath<minpath){
        minpath=continuedpath;
        bestroute=cloneList(route);
      }
      return minpath;       
    }
    
    else{       
      for(int i=1; i<matrix[city].length; i++){//a for loop to check all the subroutes from the current city
        if(!route.contains(i)&&matrix[city][i]!=0&&(path<=minpath)&&path+getTheoMin(matrix, city, i)<=minpath){
          int continuedpath=path+matrix[city][i];
          int[][]updatedmatrix=nullColumn(matrix, i);
          route.add(i);
          findMinPath2(updatedmatrix, i, original, continuedpath, citiestouched);
          route.remove(route.size()-1);
          
        }
      }
    }
    return minpath;
  }
  
  
  
  
  
  
  
  
  int getTheoMin(int[][]matrix, int startcity, int secondcity){
    int theomin=0;
    int tempmin=0;
    theomin=matrix[startcity][secondcity];
    for(int i=0; i<matrix[secondcity].length; i++){
      boolean istouched=route.contains(i);
      if(tempmin==0&&i!=startcity&&matrix[secondcity][i]!=0&&istouched==false){
        tempmin=matrix[secondcity][i];
        
      }
      else if(tempmin!=0&&i!=startcity&&matrix[secondcity][i]<tempmin&&matrix[secondcity][i]!=0&&istouched==false){
        tempmin=matrix[secondcity][i];
      }   
    }
    theomin=theomin+tempmin;
    tempmin=0; 
    
    for(int i=1; i<matrix.length; i++){
      boolean istouched=route.contains(i);
      if(i!=startcity&&i!=secondcity&&istouched==false){
        tempmin=0;
        for(int j=0; j<matrix[i].length; j++){
          boolean jistouched=route.contains(j);
          if(tempmin==0&&j!=secondcity&&matrix[i][j]!=0&&jistouched==false){
            tempmin=matrix[i][j];
          }
          else if(tempmin!=0&&j!=secondcity&&matrix[i][j]!=0&& matrix[i][j]<tempmin&&jistouched==false){
            tempmin=matrix[i][j];
          }
        }
        theomin=theomin+tempmin;
      }
    }
    
    return theomin;
  }
  
  
  
  
  int smartStartRepeated(int[][]matrix){
    int min=0;
    for(int i=0; i<matrix.length; i++){
      genmin=0;
      ArrayList<Integer>citiestouched=new ArrayList<Integer>();
      citiestouched.add(i);
      int current=getSmartStartingMin(matrix, citiestouched, i, false);
      
      
      if(min==0){
        min=current;
      }
      else if(current<min){
        min=current;
      }
      
      //System.out.println(min);
    }
    return min;
  }
  
  
  
  
  
  int getSmartStartingMin(int[][]matrix, ArrayList<Integer>citiestouched, int city, boolean lastcity){
    int currentmin=0;
    int currentcityindex=0;
    
    if(lastcity==true){
      int backhome=matrix[city][citiestouched.get(0)];
      genmin=genmin+backhome;
      return genmin;
    }
    
    if(lastcity==false){
      for(int i=0; i<matrix.length; i++){
        if((currentmin==0)&&(!avoidCity(citiestouched, i))){
          currentmin=matrix[city][i];
          currentcityindex=i;
          //System.out.println(currentmin);
        }
        else if((currentmin!=0)&&(!avoidCity(citiestouched,i))){
          if(matrix[city][i]<currentmin){
            currentmin=matrix[city][i];
            currentcityindex=i;
            //System.out.println(currentmin);
          }
        }
      }
      //System.out.println(genmin);
      genmin=genmin+currentmin;
      citiestouched.add(currentcityindex);
      //System.out.println(citiestouched.size());
      if(citiestouched.size()==matrix.length){
        lastcity=true;
        genmin=getSmartStartingMin(matrix, citiestouched, currentcityindex, lastcity);
      }
      else{
        lastcity=false;
        genmin=getSmartStartingMin(matrix, citiestouched, currentcityindex, lastcity);
      }
    }
    
    //System.out.println(genmin);
    return genmin;
  }
  
  
  boolean avoidCity(ArrayList<Integer>citiestouched, int city){
    for(int i=0; i<citiestouched.size(); i++){
      if(city==citiestouched.get(i)){
        return true;
      }
    }
    return false;
  }
  
  
  public int[][] removeColumn(int[][]matrix, int col)
  {
    
    int rows=matrix.length;
    int columns=matrix[0].length;
    int destinationarr[][] = new int[rows][columns-1];
    
    int REMOVE_COLUMN = col;
    int p = 0;
    for( int i = 0; i < rows; i++)
    {
      
      int q = 0;
      for( int j = 0; j < columns; ++j)
      {
        if ( j == col){
          continue;
        }
        
        destinationarr[p][q] = matrix[i][j];
        ++q;
      }
      
      ++p;
    }
    return destinationarr;  
  }
  
  public int[][] nullColumn(int[][]matrix, int col)
  {
    
    int rows=matrix.length;
    int columns=matrix[0].length;
    int destinationarr[][] = new int[rows][columns];
    
    int p = 0;
    for( int i = 0; i < rows; i++)
    {
      
      int q = 0;
      for( int j = 0; j < columns; ++j)
      {
        if ( j == col){
          destinationarr[p][q]=0;
          q++;
          continue;
        }
        
        destinationarr[p][q] = matrix[i][j];
        ++q;
      }
      
      ++p;
    }
    return destinationarr;  
  }
  
  int countOptions(int[][] matrix, int index){
    int counter=0;
    for(int i=0; i<matrix[index].length; i++){
      if(matrix[index][i]!=0)
        counter++;
    }
    return counter;
  }
  
  public static ArrayList<Integer> cloneList(ArrayList<Integer> list) {
    ArrayList <Integer> clone = new ArrayList<Integer>(list.size());
    for (int i =0; i<list.size(); i++) clone.add(list.get(i));
    return clone;
}
  
  
}