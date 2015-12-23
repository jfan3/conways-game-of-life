/**
 This class is for painting a canvas (grid) for the game. It uses a 2D array of Color object to represent the grid. 
 Written jointly by all of us.
 */
import java.awt.*;
import javax.swing.*;
import java.awt.image.BufferedImage;

public class Canvas extends JPanel{

    private int rows;       // The number of rows for the grid.
    private int columns;    // The number of columns for the the grid.
    private Color defaultColor;   

    private Color[][] grid; 
    private BufferedImage img;  // The panel projected to the image, then the image 
//                                //is copied to the screen
    private Graphics graphic;  // Graphics context for drawing to img
    private boolean needsRedraw;   // This is set to true when a change has occurred that
                                   // changes the appearance of the panel.

/**Constructors*/

    public Canvas(int rows, int columns, int blockWidth, int blockHeight,  int borderWidth) {
        this.rows = rows;
        this.columns = columns;
        grid = new Color[rows][columns];
        //set default color and border color to be white and black respectively
        defaultColor = Color.white;
        Color borderColor=Color.black;
        setBackground(defaultColor);
        setOpaque(true);
        setBorder(BorderFactory.createLineBorder(borderColor,borderWidth)); //the outside border is black
        setPreferredSize(new Dimension(blockWidth*columns + 2*borderWidth, blockHeight*rows + 2*borderWidth)); //the overall size is the sum of total size + the size of borderlines
    }
    
    
    public Canvas(int rows, int columns, int blockWidth, int blockHeight) {
        this(rows, columns, blockWidth, blockHeight,1);
    }
    
//setters and getters 
    /**
     *  Return the defaultColor, which cannot be null.
     */
    public Color getDefaultColor() {
        return defaultColor;
    }


//Set up the grid
    public void setGridSize(int rows, int columns) {
        if (rows > 0 && columns > 0) {
            Color[][] newGrid = new Color[rows][columns];

            
            for (int r = 0; r < rows; r++)
                for (int c = 0; c < columns; c++)
                    newGrid[r][c] = grid[r][c];
            grid = newGrid;
            this.rows = rows;
            this.columns = columns;
            redraw();
        }
    }


    /**
     *  Return the number of rows of rectangles in the grid.
     */
    public int getRowCount() {
        return rows;
    }


    /**
     *  Return the number of columns of rectangles in the grid.
     */
    public int getColumnCount() {
        return columns;
    }   

    public Color getColor(int row, int col) {
        if (row >=0 && row < rows && col >= 0 && col < columns)
            return grid[row][col];
        else
            return null;
    }


    public void setColor(int row, int col, Color c) {
        if (row >=0 && row < rows && col >= 0 && col < columns) {
            grid[row][col] = c;
                drawSquare(row,col,true);
        }
    }



    // fill every square in the canvas with a color
    public void fill(Color c) {
        for (int i = 0; i < rows; i++)
            for (int j = 0; j < columns; j++)
                grid[i][j] = c;
        redraw();      
    }

    //clear 
    public void clear() {
        fill(null);
    }
    //clear only red squares
    public void clearRed(){
        for (int i = 0; i < rows; i++)
            for (int j = 0; j < columns; j++)
                if (grid[i][j] == Color.RED)
                        grid[i][j]=null;
        redraw();    
    }
    
    //repaint the cancas
    final public void redraw() {
        needsRedraw = true;
        repaint();
    }



    
    /**
     * Turn the x-coordinate of point on the panel into the position of x on the grid
     * @return -1 if the x coordinate is outside of the grid
     */

    public int xCoordToColumnNumber(int x) {
        //get the x,y coordinate of the JPanel (container)
        Insets insets = getInsets();
        if (x < insets.left)
            return -1; // outside of the container
        double colWidth = (double)(getWidth()-insets.left-insets.right) / columns; //the width of a column corresponding to the container
        int col = (int)( (x-insets.left) / colWidth); //the corresponding col num of the point on the
                                                       //Color[][] grid converted from a position on the container
        if (col >= columns)
            return columns; //if outside of COlor[][] grid, return the righter most column number
        else
            return col;
    }

     /**
     * Turn the y-coordinate of point on the panel into the position of x on the grid
     * @return -1 if the y coordinate is outside of the grid
     */
    public int yCoordToRowNumber(int y) {
        Insets insets = getInsets();
        if (y < insets.top)
            return -1;// outside of the container
        double rowHeight = (double)(getHeight()-insets.top-insets.bottom) / rows;//the eight of a row corresponding to the container
        int row = (int)( (y-insets.top) / rowHeight);//the corresponding row num of the point on the
                                                       //Color[][] grid converted from a position on the container
        if (row >= rows)
            return rows;//if outside of COlor[][] grid, return the bottom row number
        else
            return row;
    }


    
    //overwritten from Java API to make the methods work
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        synchronized(this) {
            if ( (img == null) || img.getWidth() != getWidth() || img.getHeight() != getHeight() ) {
                img = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);
                graphic = img.getGraphics();
                needsRedraw = true;
            }
        }
        if (needsRedraw) {
            for (int r = 0; r < rows; r++)
                for (int c = 0; c < columns; c++)
                    drawSquare(r,c,false);
            needsRedraw = false;
        }
        g.drawImage(img,0,0,null);
    }

    //overwritten from Java API to make the methods work
    synchronized private void drawSquare(int row, int col, boolean callRepaint) {

        Insets insets = getInsets();
        double rowHeight = (double)(getHeight()-insets.left-insets.right) / rows;
        double colWidth = (double)(getWidth()-insets.top-insets.bottom) / columns;
        int xOffset = insets.left;
        int yOffset = insets.top; 
        int y = yOffset + (int)Math.round(rowHeight*row);
        int h = Math.max(1, (int)Math.round(rowHeight*(row+1))+yOffset - y);
        int x = xOffset + (int)Math.round(colWidth*col);
        int w = Math.max(1, (int)Math.round(colWidth*(col+1))+xOffset - x);
        Color c = grid[row][col];
        graphic.setColor( (c == null)? defaultColor : c );
        graphic.fillRect(x,y,w,h);
        if (callRepaint)
            repaint(x,y,w,h);
    }


} 

