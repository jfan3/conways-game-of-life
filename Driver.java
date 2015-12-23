//The driver for the whole program, contains the overall JFrame with two tabs: the intro tab and game tab
//Written by Fiona Fan &&Cassandra
import javax.swing.JFrame;
import javax.swing.JTabbedPane;


public class Driver extends JFrame {

    public Driver() {
         pack();
    }

  
  public static void main(String[] args){
    //set up the frame and defaul close operation
    JFrame frame = new JFrame ("Conway's Game of Life Project");
    frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
    
    
    //add the tabbed panes
    JTabbedPane tp = new JTabbedPane();
    tp.addTab ("Intro",new IntroPanel());
    tp.addTab ("Conway's Game of Life", new FreePropagationPanel());
    
    //set up frames
    frame.getContentPane().add(tp);
    frame.pack();
    frame.setVisible(true);
  }
}
