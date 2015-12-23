/*
 This is the main panel for conway's game of life. It has two modes, Free propagation mode and examine
 pattern mode, mutually exclusive. Written by Fiona Fan
 */

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Hashtable;
import java.util.LinkedList;
import javax.imageio.ImageIO;
import javax.swing.*;



public class FreePropagationPanel extends JPanel implements ActionListener, MouseListener, MouseMotionListener{
  private GameOfLife game;    
  private boolean fpMode; //boolean for when free propagation mode is activated
  private boolean epMode;//boolean for when examine pattern mode is activated
  public final int GRID_SIZE = 100; //a 100*100 grid
  
  
  ShapeCollection sc = new ShapeCollection ();
  
  
  LinkedList<int[]> clicked=new LinkedList<int[]>(); //a linked list to keep track of the location of the clicked cell, in ep MODE

  private Canvas display;  // Displays the game to the user..  White squares are alive; black squares are dead.
//    private Canvas select;
  
  private Timer timer;  // Drives the game when the user presses the "Start" button.

  private ButtonGroup RadioButtonGroup1;
  private JButton clearButton;
  private JRadioButton epRButton;
  private JTextArea epText;
  private JRadioButton fpRButton;
//    private JPanel grid;
  private JTextArea introText;
  private JPanel pct_pane;
  private JPanel intro_pane;
  private JPanel ep_pane;
  private JPanel select_pane;
  private JPanel panel_for_grid;
  private JPanel panel_for_update;
  private JButton nextButton;
  private JTextField pct_field;
  private JTextArea pct_text;
  private JButton quitButton;
  private JButton randomButton;
  private JButton selectButton;
  
  private JTextArea selectText;
  private JButton stopGoButton;
  private JTextArea update_text;
  private JButton selectNewButton;
  
  //constructor
  public FreePropagationPanel() {
    game=new GameOfLife(); //creates a new game
    
    int cellSize = 700/GRID_SIZE; 
    display = new Canvas(GRID_SIZE,GRID_SIZE,cellSize,cellSize);
    fpMode=false;
    epMode=false;
    
    this.paintComponents(null);
    this.setBackground(Color.WHITE);
    
    RadioButtonGroup1 = new ButtonGroup(); //to make the two radio buttons mututally exclusive for two modes
    pct_field = new JTextField();
    panel_for_grid = new JPanel();
    panel_for_update= new JPanel();
    pct_pane = new JPanel();
    pct_text = new JTextArea();
    randomButton = new JButton();
    stopGoButton = new JButton();
    nextButton = new JButton();
    clearButton = new JButton();
    quitButton = new JButton();
    fpRButton = new JRadioButton();
    epRButton = new JRadioButton();
    intro_pane = new JPanel();
    introText = new JTextArea();
    selectButton = new JButton();
    ep_pane = new JPanel();
    epText = new JTextArea();
    update_text = new JTextArea();
    select_pane = new JPanel();
    selectText = new JTextArea();
    selectNewButton=new JButton();
    
    pct_field.setText("");
    
    
    panel_for_grid.add(display);
    panel_for_update.add(update_text);
    panel_for_grid.setBackground(Color.white);
    panel_for_update.setBackground(Color.white);
    pct_pane.setBackground(Color.white);
    intro_pane.setBackground(Color.white);
    ep_pane.setBackground(Color.white);
    
    pct_text.setColumns(20);
    pct_text.setFont(new java.awt.Font("Baskerville", 3, 14)); 
    pct_text.setLineWrap(true);
    pct_text.setRows(5);
    pct_text.setText("Please enter the percentage\nof cells you would like to \nrandomly place on grid.\n");
    pct_text.setAutoscrolls(false);
    pct_pane.add(pct_text);
    
    randomButton.setFont(new java.awt.Font("Abadi MT Condensed Extra Bold", 0, 14)); 
    randomButton.setText("Place!");
    
    
    stopGoButton.setFont(new java.awt.Font("Abadi MT Condensed Extra Bold", 0, 18)); 
    stopGoButton.setForeground(new java.awt.Color(255, 102, 102));
    stopGoButton.setText("Start");
    
    nextButton.setFont(new java.awt.Font("Abadi MT Condensed Extra Bold", 0, 18)); 
    nextButton.setForeground(new java.awt.Color(255, 102, 102));
    nextButton.setText("Next Gen");
    nextButton.setToolTipText("");
    
    clearButton.setFont(new java.awt.Font("Abadi MT Condensed Extra Bold", 0, 18)); 
    clearButton.setForeground(new java.awt.Color(255, 102, 102));
    clearButton.setText("Clear all");
    
    quitButton.setFont(new java.awt.Font("Abadi MT Condensed Extra Bold", 0, 18)); 
    quitButton.setForeground(new java.awt.Color(255, 102, 102));
    quitButton.setText("Quit");
    
    fpRButton.setFont(new java.awt.Font("Abadi MT Condensed Extra Bold", 0, 18)); 
    fpRButton.setForeground(new java.awt.Color(102, 0, 102));
    fpRButton.setText("Free Propagation Mode");
    
    
    epRButton.setFont(new java.awt.Font("Abadi MT Condensed Extra Bold", 0, 18)); 
    epRButton.setForeground(new java.awt.Color(102, 0, 102));
    epRButton.setText("Examine Pattern Mode");
    
    selectNewButton.setFont(new java.awt.Font("Abadi MT Condensed Extra Bold", 0, 18)); 
    selectNewButton.setForeground(new java.awt.Color(255, 102, 102));
    selectNewButton.setText("Select new pattern");
    
    RadioButtonGroup1.add(epRButton);
    RadioButtonGroup1.add(fpRButton);
    
    introText.setColumns(20);
    introText.setFont(new java.awt.Font("Baskerville", 3, 14)); 
    introText.setRows(5);
    introText.setText("There are two modes:\nSelect the \"Free Propagation\" mode \nstart a round of game of life.\nWhen you see patterns emerging, \nyou can hit stop and select the \n\"Examine Pattern\" mode.\nSelect the upperleft and down-right\ncorners of the patter and hit select.\nThe information about the pattern \nwill emerge in the update panel.\n\n\n");
    intro_pane.add(introText);
    
    selectButton.setFont(new java.awt.Font("Abadi MT Condensed Extra Bold", 0, 18)); 
    selectButton.setForeground(new java.awt.Color(255, 102, 102));
    selectButton.setText("Select");
    
    
    epText.setColumns(20);
    epText.setRows(5);
    epText.setFont(new java.awt.Font("Baskerville", 3, 14));
    epText.setText("Please click on two points to \nselect an area. \nIf you click on more than two points,\nan error will appear and you'd have to \nselect again.");
    ep_pane.add(epText);
    
    update_text.setColumns(20);
    update_text.setFont(new java.awt.Font("Baskerville", 3, 14)); 
    update_text.setLineWrap(true);
    update_text.setRows(5);
    update_text.setAutoscrolls(false);
    update_text.setText("To be updated.");
    
    selectText.setColumns(20);
    selectText.setRows(5);
    selectText.setFont(new java.awt.Font("Baskerville", 3, 14));
    selectText.setText("Information about\nthe pattern will be\nshown on the left pane.");
    select_pane.add(selectText);
    
    //create layout with horizontal and vertical groups
    GroupLayout layout = new GroupLayout(this);
    this.setLayout(layout);
    layout.setHorizontalGroup(
                              layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                            .addContainerGap()
                                            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                        .addGroup(layout.createSequentialGroup()
                                                                    .addComponent(panel_for_grid)
                                                                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                                                .addGroup(layout.createSequentialGroup()
                                                                                            .addGap(30, 30, 30)
                                                                                            .addComponent(fpRButton))
                                                                                .addGroup(layout.createSequentialGroup()
                                                                                            .addGap(18, 18, 18)
                                                                                            .addComponent(intro_pane, GroupLayout.PREFERRED_SIZE, 256, GroupLayout.PREFERRED_SIZE))
                                                                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                                                                            .addGroup(layout.createSequentialGroup()
                                                                                                        .addGap(36, 36, 36)
                                                                                                        .addComponent(pct_field, GroupLayout.PREFERRED_SIZE, 133, GroupLayout.PREFERRED_SIZE)
                                                                                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                                                                        .addComponent(randomButton, GroupLayout.PREFERRED_SIZE, 93, GroupLayout.PREFERRED_SIZE))
                                                                                            .addGroup(GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                                                                                        .addGap(39, 39, 39)
                                                                                                        .addComponent(pct_pane, GroupLayout.PREFERRED_SIZE, 229, GroupLayout.PREFERRED_SIZE)))
                                                                                .addGroup(layout.createSequentialGroup()
                                                                                            .addGap(36, 36, 36)
                                                                                            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                                                                        .addComponent(epRButton, GroupLayout.PREFERRED_SIZE, 220, GroupLayout.PREFERRED_SIZE)
                                                                                                        .addGroup(layout.createSequentialGroup()
                                                                                                                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                                                                                                .addGroup(layout.createSequentialGroup()
                                                                                                                                            .addComponent(stopGoButton, GroupLayout.PREFERRED_SIZE, 114, GroupLayout.PREFERRED_SIZE)
                                                                                                                                            .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED))
                                                                                                                                .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                                                                                                            .addComponent(clearButton, GroupLayout.PREFERRED_SIZE, 114, GroupLayout.PREFERRED_SIZE)
                                                                                                                                            .addGap(24, 24, 24)))
                                                                                                                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                                                                                                                .addComponent(nextButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                                                                                .addComponent(quitButton, GroupLayout.PREFERRED_SIZE, 110, GroupLayout.PREFERRED_SIZE)))))
                                                                                .addGroup(layout.createSequentialGroup()
                                                                                            .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                                                                            .addComponent(ep_pane, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
                                                        .addGroup(layout.createSequentialGroup()
                                                                    .addComponent(panel_for_update, GroupLayout.DEFAULT_SIZE, 1107, Short.MAX_VALUE)
                                                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                                                                .addComponent(selectNewButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                                .addComponent(selectButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                                    .addGap(20, 20, 20)
                                                                    .addComponent(select_pane, GroupLayout.PREFERRED_SIZE, 156, GroupLayout.PREFERRED_SIZE)))
                                            .addGap(26, 26, 26))
                             );
    layout.setVerticalGroup(
                            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                              .addGroup(layout.createSequentialGroup()
                                          .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                      .addComponent(panel_for_grid, GroupLayout.PREFERRED_SIZE, 594, GroupLayout.PREFERRED_SIZE)
                                                      .addGroup(layout.createSequentialGroup()
                                                                  .addComponent(intro_pane, GroupLayout.PREFERRED_SIZE, 199, GroupLayout.PREFERRED_SIZE)
                                                                  .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                                                  .addComponent(fpRButton)
                                                                  .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                                  .addComponent(pct_pane, GroupLayout.PREFERRED_SIZE, 66, GroupLayout.PREFERRED_SIZE)
                                                                  .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                                                  .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                                              .addComponent(pct_field, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
                                                                              .addComponent(randomButton, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE))
                                                                  .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                                  .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                                              .addComponent(stopGoButton, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
                                                                              .addComponent(nextButton, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE))
                                                                  .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                                                  .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                                              .addComponent(clearButton, GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE)
                                                                              .addComponent(quitButton, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE))
                                                                  .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                                  .addComponent(epRButton)
                                                                  .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                  .addComponent(ep_pane, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
                                          .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                      .addGroup(layout.createSequentialGroup()
                                                                  .addGap(18, 18, 18)
                                                                  .addComponent(panel_for_update, GroupLayout.PREFERRED_SIZE, 92, GroupLayout.PREFERRED_SIZE)
                                                                  .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                      .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                                  .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                  .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                                                              .addComponent(selectNewButton, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
                                                                              .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                                                          .addComponent(select_pane, GroupLayout.PREFERRED_SIZE, 72, GroupLayout.PREFERRED_SIZE)
                                                                                          .addComponent(selectButton, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)))
                                                                  .addGap(72, 72, 72))))
                           );
    
    quitButton.getAccessibleContext().setAccessibleName("Quit");
    
    
    fpRButton.addActionListener(this);
    randomButton.addActionListener(this);
    stopGoButton.addActionListener(this);
    nextButton.addActionListener(this);
    clearButton.addActionListener(this);
    quitButton.addActionListener(this);
    epRButton.addActionListener(this);
    selectButton.addActionListener(this);
    display.addMouseListener(this);
    display.addMouseMotionListener(this);
    selectNewButton.addActionListener(this);
    timer = new Timer(50,this);
  }
  
  //repaint the grid with current living cells
  private void showBoard() {
    for (int r = 0; r < GRID_SIZE; r++) {
      for (int c = 0; c < GRID_SIZE; c++) {
        if ((game.getAlive())[r][c])
          display.setColor(r,c,Color.black);
        else
          display.setColor(r,c,null);  // Shows the background color, black.
      }
    }
  }
  
  //monitor mouse clicks
  public void actionPerformed(ActionEvent e) {
    Object src = e.getSource();  // Even source
    if (src == quitButton) { // Exit.
      System.exit(0);
    }
    else if (src==fpRButton){
      fpMode=true;
      epMode=false;
//            epRButton.setEnabled(false);
      selectButton.setEnabled(false);
      selectNewButton.setEnabled(false);
      randomButton.setEnabled(true);
      pct_field.setEditable(true);
      clearButton.setEnabled(true);
      nextButton.setEnabled(true);
      quitButton.setEnabled(true);
      stopGoButton.setEnabled(true);
      
    }
    
    else if (src == clearButton) {  // Clear the board.
      if (fpMode&&!epMode){
        game.clear();
        display.clear();
      }
    }
    else if (src == nextButton) {  // Compute and display the next generation.
      if (fpMode&&!epMode){
        game.nextGen();
        showBoard();
      }
    }
    else if (src == stopGoButton) {  // Start or stop the game, depending on whether or not it is currenty running.
      if (fpMode&&!epMode){  
        if (timer.isRunning()) {  // If the game is currently running, stop it.
          timer.stop();  
          clearButton.setEnabled(true);  // Some buttons are disabled while the game is running.
          randomButton.setEnabled(true);
          nextButton.setEnabled(true);
          stopGoButton.setText("Start");  // Change text of button to "Start"
        }
        else {  // If the game is not currently running, start it.
          timer.start();  
          clearButton.setEnabled(false);  // Buttons that modify the board are disabled while the game is running.
          randomButton.setEnabled(false);
          nextButton.setEnabled(false);
          stopGoButton.setText("Stop"); // Change text of button to "Stop", since it can be used to stop the game.
          epRButton.setEnabled(true);
        }
      }
    }
    else if (src == randomButton) { // Fill the board randomly.
      if (fpMode&&!epMode){ 
        try{
          int pct= Integer.parseInt(pct_field.getText());
          //                System.out.println(pct);
          game.autoGen(pct);
          showBoard();
        }catch(NumberFormatException error){
          JOptionPane.showMessageDialog(null, "Please enter an integer between 1 to 100.", "Please enter an integer between 1 to 100.", JOptionPane.ERROR_MESSAGE);
        }catch(PctException error){
          JOptionPane.showMessageDialog(null, error, "Error", JOptionPane.ERROR_MESSAGE);
        }
        
      }
    }
    else if (src == timer) {  // Each time the timer fires, a new frame is computed and displayed.
      if (fpMode&&!epMode){ 
        game.nextGen();
        showBoard();
      }
    }
    else if (src==epRButton){
      epMode=true;//if the ep radio button is selected ,change the mode to EPmode and disable some other buttons
      fpMode=false;
      selectButton.setEnabled(true);
      selectNewButton.setEnabled(true);
      randomButton.setEnabled(false);
      pct_field.setEditable(false);
      clearButton.setEnabled(false);
      nextButton.setEnabled(false);
      quitButton.setEnabled(false);
      stopGoButton.setEnabled(false);
    }
    else if (src==selectButton){
      try {
        if (clicked.size()!=2) {//make sure only two cells are clicked to examine the pattern
          
          clicked.clear();
          display.clearRed();
          throw new SelectionException("Please select two cells."); //show error message
        }
        //get the shape 
        Shape selectedShape=game.selectArea(clicked.getFirst()[0], clicked.getFirst()[1],clicked.getLast()[0], clicked.getLast()[1]);
        
        //get the shape's properties
        String s="The shape selected is a(an) "+sc.getName(selectedShape).toLowerCase()+"\n";
        if (sc.getName(selectedShape)!="not contained."){ // if the shape is incluede in our dataset then....
          s+="The shape is a(an) "+((sc.getChar(selectedShape))?"oscillator":"stabilizer")+".\n";  //show if it is an oscillator or stabilizer
          if (sc.getChar(selectedShape)){//if it is an oscillator then...
            s+="It has "+sc.getPeriodInfo(selectedShape)[0]+" total periods.\n"; //show its total periods
          }
        }
        
        //Update the label
        update_text.setText(s);
        
      } catch (SelectionException ex) {
        JOptionPane.showMessageDialog(null, ex, "Error", JOptionPane.ERROR_MESSAGE);
      }
      catch (NullPointerException ex) {
        JOptionPane.showMessageDialog(null, "The pattern is not in our reportoire.", "Error", JOptionPane.ERROR_MESSAGE);
      }
      
    }
    else if (src==selectNewButton){
      try {
        if(fpMode&&!epMode){
          throw new SelectionException ("Please select 'Examine pattern' mode first."); // if not in epmode, show error message
        }
        
        if (epMode&&!fpMode){// clear all red cells on canvas
          display.clearRed();
          clicked.clear();
        }
      } catch (SelectionException ex) {
        JOptionPane.showMessageDialog(null, ex, "Error", JOptionPane.ERROR_MESSAGE);
        
      }
      
    }
  }
  
  public void mousePressed(MouseEvent e) {
    
    if (fpMode&&!epMode){
      //if the game is running, do nothing 
      if (timer.isRunning())
        return;
      int row = display.yCoordToRowNumber(e.getY());
      int col = display.yCoordToRowNumber(e.getX());
      
      if (row >= 0 && row < display.getRowCount() && col >= 0 && col < display.getColumnCount()) {
        if (e.isMetaDown() || e.isControlDown()) {
          display.setColor(row,col,null);
          game.letDie(row, col);
        }
        else {
          
          game.changeLivingStatus(row, col);
          if (game.getAlive()[row][col])
            display.setColor(row,col,Color.BLACK);
          else {display.setColor(row,col,null);}
        }
      }
    }
    else if (epMode&&!fpMode){
//      try {
//        if (clicked.size()>2)
//          throw new SelectionException ("Can't select more than two cells for reference. Please select again.");
//        display.clearRed(); 
//      } catch (SelectionException ex) {
//        JOptionPane.showMessageDialog(null, ex, "Error", JOptionPane.ERROR_MESSAGE);
//      }catch (NullPointerException ex){
//        JOptionPane.showMessageDialog(null, "Something is wrong.", "Error", JOptionPane.ERROR_MESSAGE);
//      }
      
      int row = display.yCoordToRowNumber(e.getY());
      int col = display.yCoordToRowNumber(e.getX());
      if (row >= 0 && row < display.getRowCount() && col >= 0 && col < display.getColumnCount()) { //make sure the clicked area is within the pane containg the grid
        //clicking right kills the cell
//        if (e.isMetaDown() || e.isControlDown()) {
//          display.setColor(row,col,null);
//          game.letDie(row, col);
//        }
//        else {
          try {
            if (game.getAlive()[row][col]) 
//                        clicked.clear();
              throw new SelectionException ("Can't override living cell as selection. Please reselect.");
          } catch (SelectionException ex) {
            JOptionPane.showMessageDialog(null, ex, "Error", JOptionPane.ERROR_MESSAGE);
          }
          display.setColor(row,col,Color.RED);
          game.letDie(row, col);
          int [] position ={row,col};
          clicked.addLast(position);
//                    System.out.println(clicked.getLast()[0]+"   "+clicked.getLast()[1]);
        
      }
    }
  }
  public void mouseDragged(MouseEvent e) {// Makes dragging the mouse across square have the same effect as clicking in that square.
    //but only when it's in freepropagation mode
    if (fpMode)
      mousePressed(e);  
  }
  
  // Require to implement MouseListener and MouseMotionListener interfaces.
  public void mouseClicked(MouseEvent e) { } 
  public void mouseEntered(MouseEvent e) { }
  public void mouseExited(MouseEvent e) { }
  public void mouseReleased(MouseEvent e) { }
  public void mouseMoved(MouseEvent e) { }
  
//    public static void main(String[] args) {
//        JFrame f = new JFrame("Life");
//        JPanel p = new FreePropagationPanel();
//        f.setContentPane(p);
//        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        f.pack();
//        f.setLocation(100,50);
//        f.setVisible(true);
//    }
}
