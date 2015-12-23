//Helper methods for testing purposes, written by Candice Gong

import java.util.Random;

public class Helper 
{
  public static boolean[][] generatePeriod(boolean[][] currentPeriod){
    
    boolean[][] result = new boolean[currentPeriod.length][currentPeriod.length];

    for (int i = 0; i < currentPeriod.length; i ++) {
      for (int j = 0; j < currentPeriod.length; j ++) {
        result[i][j] = statusInNextGen(i, j, currentPeriod);
      }
    }
    return result; 
  }
  
  //populate for a generation within a given area
  public static boolean statusInNextGen(int row, int col, boolean[][] currentGen){
    //System.out.print("\nThe current shape: \n" + booleanArrayToString(currentGen));
    //System.out.println("\nPosition: (" + row + ", " + col + ")");
    int i = -1;
    int liveNeighborCount = 0;
    while (i<2) {
      int j = -1;
      while (j<2) {
        try{
          if ( i == 0 && j == 0 ){
            //System.out.println("Skip the same point");
          }else{
            //System.out.println("Neighbor: (" + (row+i) + ", " + (col+j) + ") alive?: " + currentGen[row+i][col+j]);
            if(currentGen[row+i][col+j]) {
              
              liveNeighborCount ++;
            }
          }
          
        }catch(IndexOutOfBoundsException e){
          //System.out.println("(" + (row+i) + ", " + (col+j) + ")" + " Out of Bounds");
        }
        j += 1;
        //System.out.println("j increase to " + j);
      }
      i += 1;
    }
    //System.out.println("livingNeighbor: " + liveNeighborCount);
    //System.out.print("the current shape when start to return: \n" + booleanArrayToString(shape));
    if ( currentGen[row][col] ) {
      if ( liveNeighborCount < 2 ) return false;
      if ( liveNeighborCount < 4 ) return true;
      return false;
    }
    
    if ( liveNeighborCount == 3 ) return true;
    
    return false;
  }
  
  //put a randome number of cells within the array
  public static boolean[][] autoGenBooleanArry (int height, int width, int numGen){
    boolean[][] result = new boolean[height][width];
    
    Random rand = new Random();
    
    
    int index = 0;
    while( index < numGen ){
      int x = rand.nextInt(height);
      int y = rand.nextInt(width);
      if ( !result[x][y] ) {
        result[x][y] = true;
        index ++;
      }
    }
    
    return result;
    
    
  }
  
  //test if two patterns are the same
  public static boolean isIdentical(boolean[][]mat1,boolean[][] mat2){
    if (mat1.length == mat2.length&&mat1[0].length == mat2[0].length){
      
      for (int i =0; i<mat1.length;i++){
        for (int j = 0; j < mat1[0].length; j++){
          if (mat1[i][j]!=mat2[i][j])
            return false;
        }
      }
      return true;
    }
    return false;
  }
  
  //for testing
  public static void main (String args[]){
    Helper aw=new Helper();
    boolean[][] a= {{true, true, true,false},{false, false, false,false}};
    boolean[][] b= {{true, true, true,true},{false, false, false,false}};
    System.out.println(aw.isIdentical(a,b));
  }
  
}