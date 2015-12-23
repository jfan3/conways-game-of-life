 /**This is the game infrastructure (boolean [][]) underlying the canvas (Color[][])
   *Written by Fiona Fan 
   */

import java.util.Random;
import java.lang.*;

public class GameOfLife{
  private final int GRID_SIZE = 100;
  final int cellSize = 1000/GRID_SIZE;  //each cell has is 10*10
  private boolean[][] alive;
  private int generations;

  
  
  public GameOfLife() 
  {
    
    alive = new boolean[GRID_SIZE][GRID_SIZE];
    generations = 0;

  }
  //propagate a generation
  protected void nextGen(){
      boolean[][] newboard = new boolean[GRID_SIZE][GRID_SIZE];
        for ( int r = 0; r < GRID_SIZE; r++ ) {
            int above, below; // rows considered above and below row number r
            int left, right;  // columns considered left and right of column c
            above = r > 0 ? r-1 : GRID_SIZE-1;
            below = r < GRID_SIZE-1 ? r+1 : 0;
            for ( int c = 0; c < GRID_SIZE; c++ ) {
                left =  c > 0 ? c-1 : GRID_SIZE-1;
                right = c < GRID_SIZE-1 ? c+1 : 0;
                int n = 0; // number of alive cells in the 8 neighboring cells
                if (alive[above][left])
                    n++;
                if (alive[above][c])
                    n++;
                if (alive[above][right])
                    n++;
                if (alive[r][left])
                    n++;
                if (alive[r][right])
                    n++;
                if (alive[below][left])
                    n++;
                if (alive[below][c])
                    n++;
                if (alive[below][right])
                    n++;
                if (n == 3 || (alive[r][c] && n == 2))
                    newboard[r][c] = true;
                else
                    newboard[r][c] = false;
            }
        }
        alive = newboard;
        generations++;
  }
  
  //automaticallt generate a percentage of cells on the canvas
  protected void autoGen(int num) throws PctException{
    if (num<0||num>100) {throw new PctException("Percentage out of bound. Should be from 1 to 100.");}
    double a = Double.parseDouble("0."+num);
//    System.out.println(a);
    
    for (int r = 0; r < GRID_SIZE; r++) {
        for (int c = 0; c < GRID_SIZE; c++)
            
            alive[r][c] = (Math.random() < a);  
    }
    
  }
  
  //kill all cells
  protected void clear(){
      alive=new boolean[GRID_SIZE][GRID_SIZE];
  }
  //getters
  protected boolean[][] getAlive(){return alive;}
  protected void changeLivingStatus(int row, int col){
       alive[row][col]=(alive[row][col])?false:true;
  }
  //setters
  protected void letDie(int row, int col){
       alive[row][col]=false;
  }
  protected void letLive(int row, int col){
       alive[row][col]=true;
  }
  
  
  //helper method turning boolean to array, only for testing purposes
  public static String booleanArrayToString (boolean[][] b) {
    String result = "";
    for (int row = 0; row < b.length; row ++){
      for (int col = 0; col < b[row].length; col ++){
        if (b[row][col]) result += (" x ");
        else result += " - ";
      }
      result += "\n";
    }
    return result;
    
  }
  
  //turn the living cells to string for the game
  public String toString(){
    String result = "the grid is" + ":\n";
    result += booleanArrayToString (alive);
    return result;
  }
  
  //return the shape contained in the selected area. Must be square
  protected Shape selectArea(int row_l, int col_l, int row_r, int col_r) throws SelectionException{
      if (Math.abs(row_l-row_r)<=1||Math.abs(col_r-col_l)<=1) throw new SelectionException("No area selected");
      if (Math.abs(row_l-row_r)!=Math.abs(col_r-col_l)) throw new SelectionException("Not a square");
      boolean[][] newBoard = new boolean[Math.abs(row_r-row_l)-1][Math.abs(col_l-col_r)-1];
//      System.out.println(Math.abs(row_r-row_l)-1);
//      System.out.println(Math.abs(col_l-col_r)-1);
      for (int c=0;c<Math.abs(row_r-row_l)-1;c++){
          for (int d=0;d<Math.abs(col_l-col_r)-1;d++){
              newBoard[c][d]=alive[Math.min(row_r, row_l)+c+1][Math.min(col_l, col_r)+d+1];
              
          }
      }
      int size = Math.max(Math.abs(row_l-row_r)-1,Math.abs(col_r-col_l)-1);
      boolean hor=(size==Math.abs(col_l-col_r)-1)?true:false;
//      boolean[][] square=new boolean[size][size];
//      for (int e=0;e<Math.abs(row_r-row_l)-1;e++)
//          for (int f=0;f<Math.abs(col_r-col_l)-1;f++)
//              square [e+1][f+1]=newBoard[e][f];
      return new Shape(newBoard,Math.abs(row_r-row_l)-1,Math.abs(col_l-col_r)-1);
  }
  
//  public static void main (String[] args){
//      GameOfLife a = new GameOfLife();
//      a.letLive(1,1);
//      a.letLive(2,1);
//      a.letLive(3,1);
//      a.letLive(1,2);
//      a.letLive(1,3);
//      System.out.println(a.toString());
//      try{
//      System.out.println(booleanArrayToString(a.selectArea(0,0,2,3)));
//      }catch (SelectionException e){System.out.println(e);}
//  }
  
}














