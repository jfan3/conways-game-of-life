/* CS 230 Final project: All Cells Must Die - Game of Life
 * Author: Cassandra Zheng, Fiona Fan, Candice Gong
 * File: Oscillator.java
 * Written together by Candice
 * 
 * This file is for oscillators
 */

import java.lang.Exception.*;
import java.util.*;

public class Oscillator extends Shape
{
  //constructors
  public Oscillator (String name, boolean[][] shape, int width, int height)
  {
    super(name, shape, width, height);
    //automatically generate all of the oscillator's morphisms
    setFullPeriod();
    ifOscillator=true;
  }
  
  public Oscillator (boolean[][] shape, int width, int height)
  {
    super(shape, width, height);
    setFullPeriod();
    ifOscillator=true;
  }
  
  //add the next period to the oscillator's periods
  public void addPeriod(boolean[][] nextPeriod){
    periods.add(nextPeriod);
  }
  
  //add the next period to the oscillator's periods, with the index of which period it is
  public void addPeriod(int index, boolean[][] period){
    periods.add(index, period);
  }
  
  //set all periods of the oscilator, @return a linked list of full boolean arrays
  public LinkedList<boolean[][]> fullPeriod() throws InputNotOscillatorException {
    LinkedList<boolean[][]> result = new LinkedList<boolean[][]>();
    //add the current shape to the result
    result.add(shape);
    boolean[][] nextPeriod;
    int i = 1;
    do{
      if( i == STOP_CYCLE_INDEX ) { // if after a number of times the shape is still not in its original form, then it's not an oscillator
        throw new InputNotOscillatorException ("An oscillator should repeat its first period");
      }
      //get next period pattern
      nextPeriod = generatePeriod(result.getLast());
      //add it to linked list
      result.add(nextPeriod);
      i++;
      
    }while(!Arrays.deepEquals(nextPeriod, result.getFirst()));
    result.removeLast();
    return result;
  }
  
  public void setFullPeriod(){
    periods = fullPeriod();
  }
  
  
  //generate the next period
  public static boolean[][] generatePeriod(boolean[][] currentPeriod){
    
    boolean[][] result = new boolean[currentPeriod.length][currentPeriod.length];

    for (int i = 0; i < currentPeriod.length; i ++) {
      for (int j = 0; j < currentPeriod.length; j ++) {
        result[i][j] = Helper.statusInNextGen(i, j, currentPeriod);
      }
    }
    
    return result;
    
  }
  
  //set the shape to be the next generation morphism
  public void nextGeneration(){
    shape = generatePeriod(shape);
    
  }
  
  //get the next period morphism
  public void nextPeriod(){
    int currentIndex = super.getCurrentPeriodNum();
    try{
      shape = periods.get(currentIndex+1);
    }catch(IndexOutOfBoundsException e){
      shape = periods.getFirst();
    }
  }
  
  //toString method
  public String toString(){
    String result = "";//NAME + ": ";
    //System.out.println("The current shape:\n" + booleanArrayToString(shape));
    int startingPoint = super.getCurrentPeriodNum();
    
    //System.out.println("the starting point is: " + startingPoint);
    
    do
    {
      result += ("(Period " + (getCurrentPeriodNum()+1) + ") " + super.toString());
      nextPeriod();
    }
    while (getCurrentPeriodNum() != startingPoint);
    return result;
  }
  
}