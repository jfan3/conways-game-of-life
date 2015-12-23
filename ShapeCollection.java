/* CS 230 Final project: All Cells Must Die - Game of Life
 * Author: Cassandra Zheng, Fiona Fan, Candice Gong
 * File: ShapeCollection.java
 * Written by Cassandra Zheng
 */

import java.util.LinkedList;
import java.util.Hashtable;

public class ShapeCollection
{
  private static  Stabilizer BLOCK;
  private static  Stabilizer TUB;
  private static  Stabilizer BOAT1,BOAT2,BOAT3,BOAT4;
  private static  Stabilizer BEEHIVE1,BEEHIVE2,BEEHIVE3,BEEHIVE4;
  private static  Stabilizer EATER1,EATER2,EATER3,EATER4;
  private static  Stabilizer POND;
  
  private static Oscillator BLINKER;
  private static Oscillator TOAD1,TOAD2;
  private static Oscillator BEACON1,BEACON2;
  private static Oscillator PULSAR;
  
  public static Hashtable<Integer, LinkedList<Shape>> hash;
  
  //public static final Shape[] COLLECTION;
  
  public ShapeCollection ()  {
    BLOCK = new Stabilizer("Block", new boolean[][] {{true, true},{true, true}}, 2, 2 );
    TUB = new Stabilizer("Tub", new boolean[][] {{false, true, false}, {true, false, true}, {false, true, false}}, 3, 3);
    //boat*4 directions
    BOAT1 = new Stabilizer("Boat1", new boolean[][] {{true, true, false}, {true, false, true}, {false, true, false}}, 3, 3);
    BOAT2 = new Stabilizer("Boat2", new boolean[][] {{false, true, true}, {true, false, true}, {false, true, false}}, 3, 3);
    BOAT3 = new Stabilizer("Boat3", new boolean[][] {{false, true, false}, {true, false, true}, {false, true, true}}, 3, 3);
    BOAT4 = new Stabilizer("Boat", new boolean[][] {{false, true, false}, {true, false, true}, {true, true, false}}, 3, 3);
    
    //beehive*4 directions
    BEEHIVE1 = new Stabilizer("Beehive1", new boolean[][] {{false, true, true, false}, {true, false, false, true}, {false, true, true, false},{false,false,false,false}}, 4, 4);
    BEEHIVE2= new Stabilizer("Beehive2", new boolean[][] {{false, false, true, false}, {false,true, false, true}, {false, true, false,true},{false,false,true,false}}, 4, 4);
    BEEHIVE3 = new Stabilizer("Beehive3", new boolean[][] {{false,false,false,false},{false, true, true, false}, {true, false, false, true}, {false, true, true, false}}, 4, 4);
    BEEHIVE4 = new Stabilizer("Beehive4", new boolean[][] {{false, true, false, false}, {true, false, true,false}, {true,false, true, false},{false,true,false,true}}, 4, 4);
    
    //eater * 4 directions
    EATER1 = new Stabilizer("Eater", new boolean[][] {{true, true, false, false}, {true, false, true, false}, {false, false, true, false}, {false, false, true, true}}, 4, 4);
    EATER2 = new Stabilizer("Eater", new boolean[][] {{false, false,true, true}, {false,false,false,true}, {true,true, true, false}, {true,false,false,false}}, 4, 4);
    EATER3 = new Stabilizer("Eater", new boolean[][] {{true, true, false, false}, {false,true,false,false}, {false, true,false,true}, {false, false, true, true}}, 4, 4);
    EATER4 = new Stabilizer("Eater", new boolean[][] {{false,false,false,true}, {false,true,true,true}, {true,false,false,false}, {true,true,false,false}}, 4, 4);
       
    
    
    
    POND = new Stabilizer("Pond", new boolean[][] {{false, true, true, false}, {true, false, false, true}, {true, false, false, true}, {false, true, true, false}}, 4, 4);
    
    
    
    //oscillator
    BLINKER = new Oscillator("Blinker", new boolean[][] {{false, false, false}, {true, true, true}, {false, false, false}}, 3, 3);
    BLINKER.setFullPeriod();
    //BLINKER.addPeriod(new boolean[][] {{false, true, false}, {false, true, false}, {false, true, false}});
    
    //toad * 2 directions
    TOAD1 = new Oscillator("Toad1", new boolean[][]{{false, false, false, false}, {false, true, true, true}, {true, true, true, false}, {false, false, false, false}}, 4, 4);
    TOAD1.setFullPeriod();
    TOAD2 = new Oscillator("Toad2", new boolean[][]{{false, true, false, false}, {false, true, true, false}, {false, true, true, false}, {false, false, true, false}}, 4, 4);
    TOAD2.setFullPeriod();
    
    //TOAD.addPeriod(new boolean[][]{{false, false, true, false},{true, false, false, true},{true, false, false, true},{false, true, false, false}});
    BEACON1 = new Oscillator("Beacon", new boolean[][] {{true, true, false, false}, {true, false, false, false}, {false, false, false, true}, {false, false, true, true}}, 4, 4);
    BEACON1.setFullPeriod();
    BEACON2 = new Oscillator("Beacon", new boolean[][] {{false, false,true,true}, {false, false, false, true}, {true, false, false, false}, {true, true,false, false}}, 4, 4);
    BEACON2.setFullPeriod();
    //BEACON.addPeriod(new boolean[][] {{true, true, false, false}, {true, true, false, false}, {false, false, true, true}, {false, false, true, true}});
    PULSAR = new Oscillator("Pulsar", new boolean[][] {
      {false, false, false, false, false, false, false, false, false, false, false, false, false, false, false}, 
      {false, false, false, true, true, true, false, false, false, true, true, true, false, false, false}, 
      {false, false, false, false, false, false, false, false, false, false, false, false, false, false, false}, 
      {false, true, false, false, false, false, true, false, true, false, false, false, false, true, false}, 
      {false, true, false, false, false, false, true, false, true, false, false, false, false, true, false}, 
      {false, true, false, false, false, false, true, false, true, false, false, false, false, true, false}, 
      {false, false, false, true, true, true, false, false, false, true, true, true, false, false, false}, 
      {false, false, false, false, false, false, false, false, false, false, false, false, false, false, false}, 
      {false, false, false, true, true, true, false, false, false, true, true, true, false, false, false}, 
      {false, true, false, false, false, false, true, false, true, false, false, false, false, true, false}, 
      {false, true, false, false, false, false, true, false, true, false, false, false, false, true, false}, 
      {false, true, false, false, false, false, true, false, true, false, false, false, false, true, false}, 
      {false, false, false, false, false, false, false, false, false, false, false, false, false, false, false}, 
      {false, false, false, true, true, true, false, false, false, true, true, true, false, false, false},
      {false, false, false, false, false, false, false, false, false, false, false, false, false, false, false}
    }, 15, 15);
    
    PULSAR.setFullPeriod();
    hash = new Hashtable<Integer, LinkedList<Shape>>();
    
    LinkedList list2 = new LinkedList<Shape>();
    list2.add(BLOCK);
    
    hash.put(2, list2);
    LinkedList list3 = new LinkedList<Shape>();
    list3.add(TUB);
    list3.add(BOAT1);
    list3.add(BOAT2);
    list3.add(BOAT3);
    list3.add(BOAT4);
    
    list3.add(BLINKER);
   

    hash.put(3, list3);
    
    
    LinkedList list4 = new LinkedList<Shape>();
    list4.add(BEEHIVE1);
    list4.add(BEEHIVE2);
    list4.add(BEEHIVE3);
    list4.add(BEEHIVE4);
    list4.add(EATER1);
    list4.add(EATER2);
    list4.add(EATER3);
    list4.add(EATER4);
    list4.add(POND);
    list4.add(TOAD1);
    list4.add(TOAD2);
    list4.add(BEACON1);
    list4.add(BEACON2);
    hash.put(4, list4);
    
    LinkedList list15 = new LinkedList<Shape>();
    list15.add(PULSAR);
    
    hash.put(15, list15);
    
    //COLLECTION = new Shape[] {BLOCK, TUB, BOAT1, BOAT2, BOAT3, BOAT4, BEEHIVE1, BEEHIVE2, BEEHIVE3, BEEHIVE4, EATER1, EATER2, EATER3, EATER4, POND, BLINKER, TOAD1, TOAD2, BEACON1, BEACON2, PULSAR};
  }
  
  
  
  public  boolean contains(Shape s){
    int key = s.getWidth();
    
    LinkedList<Shape> resultList = hash.get(key);
    
    for (Shape shp : resultList) {
      if (shp.isIdentical(s)) return true;
      
    }
    
    return false;
    
  }
  
  public  String getName(Shape s){
    int key = s.getWidth();
    
    LinkedList<Shape> resultList = hash.get(key);
    
    for (Shape shp : resultList) {
      if (shp.isIdentical(s)) return shp.getName();
      
    }
    
    return "not contained.";
  }
  public boolean getChar(Shape s){
     boolean result=false;
    int key = s.getWidth();
    
    LinkedList<Shape> resultList = hash.get(key);
    
    for (Shape shp : resultList) {
      if (shp.isIdentical(s)) result=shp.getChar();
      
    }
    return result;
  }
  
  public int[] getPeriodInfo(Shape s){
    int[] result=new int[2];
    int key = s.getWidth();
    
    LinkedList<Shape> resultList = hash.get(key);
    
    for (Shape shp : resultList) {
      if (shp.isIdentical(s)) {
          result[0]=shp.getTotalPeriodNum();
          result[1]=shp.getCurrentPeriodNum();
      }
      
    }
    return result;
  }
  public static Hashtable<Integer, LinkedList<Shape>> getHash(){
    return hash;
  }
  
  
  
  
}