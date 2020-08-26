package EventProcess;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.util.*;
import javax.swing.*;

public class Mouse {
    public static void main(String[] args){
        mouseFrame frame = new mouseFrame();
        frame.setTitle("MouseEvent test");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}

/**
 * A frame containing a panel for testing mouse operation
 */

class mouseFrame extends JFrame{
    public mouseFrame(){
        add(new MouseComponent());
        pack();
    }
}

/**
 * A component with mouse operations for adding and removing squares
 */

class MouseComponent extends JComponent{
    Toolkit kit = Toolkit.getDefaultToolkit();
    Dimension screenSize = kit.getScreenSize();
    private final int DEFAULT_WIDTH = (int)(screenSize.getWidth()*0.6);
    private final int DEFAULT_HEIGHT = (int)(screenSize.getHeight()*0.6);
    private final int SIDE_LENGTH = 10;

    private ArrayList<Rectangle2D> squares;
    private Rectangle2D current;// the square containing the mouse cursor

    public MouseComponent(){
        squares = new ArrayList<>();
        current = null;

        // MousePressed & MouseClicked
        addMouseListener(new MouseHandler());
        // MouseMoved & MouseDragged
        addMouseMotionListener(new MouseMotionHandler());
    }

    public Dimension getPreferredSize(){
        return new Dimension(DEFAULT_WIDTH,DEFAULT_HEIGHT);
    }

    public void paintComponent (Graphics g){
        Graphics2D g2 = (Graphics2D) g;
        // draw all squares
        for (Rectangle2D r: squares)
            g2.draw(r);
    }

    /**
     * Finds the first square containing a point
     * @param p a point
     * @return the first square that contains p
     */

    public Rectangle2D find(Point2D p){
        for (Rectangle2D r: squares){
            if (r.contains(p)) return r;
        }
        return null;
    }

    /**
     * Adds a square to the collection.
     * @param p the center of the square
     */
    public void add(Point2D p){
        double x = p.getX();
        double y = p.getY();

        current = new Rectangle2D.Double(x - SIDE_LENGTH /2, y-SIDE_LENGTH/2, SIDE_LENGTH,SIDE_LENGTH);
        squares.add(current);
        repaint();
    }

    /**
     * Removes a square from the collection.
     * @param s the square to remove
     */
    public void remove(Rectangle2D s){
        if (s==null) return;
        if (s==current) current = null;
        squares.remove(s);
        repaint();
    }

    /**
     * defines mousePressed and mouseClicked
     * MouseHandler extends MouseAdapter
     * Mouse Adapter implements Mouse listener interface
     */
    private class MouseHandler extends MouseAdapter{
        public void mousePressed(MouseEvent event){
            // add a new square of the cursor isn;t inside a square
            current = find(event.getPoint());
            if(current == null) add(event.getPoint());
        }
        public void mouseClicked(MouseEvent event){
            // remove the current square if double clicked
            current = find(event.getPoint());
            if(current != null && event.getClickCount() >= 2) remove(current);
        }
    }

    /**
     * defines what the cursor would look like and how to handle dragged situation
     * mouseMotionListener is an interface
     */
    // getDefaultCursor will return a default cursor
    private class MouseMotionHandler implements MouseMotionListener{
        public void mouseMoved(MouseEvent event){
            // set the mouse cursor to hand if it is inside a rectangle
            // set the mouse cursoe to cross hair if it is outside a rectangle
            // this method will be performed only if the cursor is located inside the compouent
            if(find(event.getPoint()) == null) setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
            else setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        }
            //this method will still be invoked even if the cursor is out side the window
        public void mouseDragged(MouseEvent event){
            if(current != null){
                int x = event.getX();
                int y = event.getY();

                //drag the current rectangle to center it at (x,y)
                current.setFrame(x-SIDE_LENGTH/2,y-SIDE_LENGTH/2,SIDE_LENGTH,SIDE_LENGTH);
                repaint();
            }
        }
    }
}