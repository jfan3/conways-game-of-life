/* CS 230 Final project: All Cells Must Die - Game of Life
 * File: Shape.java
 * Written together by Candice 
 */
import java.util.LinkedList;
import java.util.Hashtable;

public class Shape
{
  final protected String NAME;
  final protected int STOP_CYCLE_INDEX = 100;
  protected boolean[][] shape;
  protected int width;
  protected int height;
  protected LinkedList<boolean[][]> periods;
  protected boolean ifOscillator;
  
  //constructor, input with name, shape and the sizes
  public Shape(String name, boolean[][] shape, int width, int height)
  {
    this.NAME = name;
    this.shape = shape;
    this.width = width;
    this.height = height;
    periods = new LinkedList<boolean[][]>();
    periods.add(shape);
  }
   //constructor, input with, shape and the sizes, used for search
  public Shape(boolean[][] shape, int width, int height)
  {
    this.NAME = "";
    this.shape = shape;
    this.width = width;
    this.height = height;
    periods = new LinkedList<boolean[][]>();
    periods.add(shape);
  }
  
  //getters and setters
  public String getName(){
    return NAME;
  }
  
  public int getWidth(){
    return width;
  }
  public boolean getChar(){
      return ifOscillator;
  }
  
  public int getTotalPeriodNum(){
    return periods.size();
  }
  
  public int getCurrentPeriodNum(){
    return periods.indexOf(shape);
  }
  
  
  public int getAliveCellNum() {
    int count = 0;
    for (int row = 0; row < shape.length; row ++){
      for (int col = 0; col < shape[row].length; col ++){
        if (shape[row][col]) count ++;
        
      }
      
    }
    return count;
  }
  
  public boolean[][] getCurrentShape(){
    return shape;
  }
  
  //generate next generation
  public void nextGeneration(){
    shape = Helper.generatePeriod(shape);
  }
  
  //a recursive method to check if the input shape match one in periods
  public boolean isIdentical(Shape s){
    //System.out.println(NAME);
    for (boolean[][] b : periods) {
      if (Helper.isIdentical(b, s.getCurrentShape())) return true;
    }
    return false;
  }
  
  //helper method
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
  
  //toString method
  public String toString() {
    String result = NAME + ":\n";
    result += booleanArrayToString (shape);
//    for (int row = 0; row < shape.length; row ++){
//      for (int col = 0; col < shape[row].length; col ++){
//        if (shape[row][col]) result += (" x ");
//        else result += " - ";
//      }
//      result += "\n";
//    }
    return result;
  }
  
  //for testing purposes
  public static void main (String[] args){
    boolean[][] shapeA = new boolean[2][2];
    shapeA[0][0] = true;
    shapeA[0][1] = true;
    shapeA[1][0] = true;
    shapeA[1][1] = true;
    //Shape a = new Shape("Block", shapeA, 2, 2);
    //System.out.println(a.toString());
    //Shape b = new Shape("Test", new boolean[][] {{true, false},{true, true}}, 2, 2);
    //System.out.println(b.toString());
    
    Shape BLOCK = new Stabilizer("Block", new boolean[][] {{true, true},{true, true}}, 2, 2 );
    Shape TUB = new Stabilizer("Tub", new boolean[][] {{false, true, false}, {true, false, true}, {false, true, false}}, 3, 3);
    Shape BOAT = new Stabilizer("Boat", new boolean[][] {{true, true, false}, {true, false, true}, {false, true, false}}, 3, 3);
    Shape BEEHIVE = new Stabilizer("Beehive", new boolean[][] {{false, true, true, false}, {true, false, false, true}, {false, true, true, false}}, 4, 4);
    Shape EATER = new Stabilizer("Eater", new boolean[][] {{true, true, false, false}, {true, false, true, false}, {false, false, true, false}, {false, false, true, true}}, 4, 4);
    Shape POND = new Stabilizer("Pond", new boolean[][] {{false, true, true, false}, {true, false, false, true}, {true, false, false, true}, {false, true, true, false}}, 4, 4);
    
//    System.out.println(BLOCK.toString());
//    System.out.println(TUB.toString());
//    System.out.println(BOAT.toString());
//    System.out.println(BEEHIVE.toString());
//    System.out.println(EATER.toString());
//    System.out.println(POND.toString());
//    System.out.println(ShapeCollection.COLLECTION[6]);
//    System.out.println(ShapeCollection.COLLECTION[8]);
//    System.out.println(ShapeCollection.COLLECTION[9]);
    //for(int i = 0; i < ShapeCollection.COLLECTION.length; i++){
      //System.out.println(ShapeCollection.COLLECTION[i].toString());
    //}
    ShapeCollection sc = new ShapeCollection ();
    Hashtable<Integer, LinkedList<Shape>> hasher  = ShapeCollection.getHash();
    LinkedList<Shape> list  = ShapeCollection.hash.get(4);
    boolean[][] blinker2ndStage = { {false, true, false}, {false, true, false}, {false, true, false}};
    boolean[][] blinker1stStage = { {false, false, false}, {true, true, true}, {false, false, false}};
    Shape test = new Shape (blinker1stStage, 3, 3);
//    System.out.println(ShapeCollection.contains(test));
//    System.out.println(ShapeCollection.getName(test));
  }
  
  
}