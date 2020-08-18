import jdk.jfr.Event;

import java.awt.*;
import javax.swing.*;
import java.awt.font.*;
import java.awt.geom.*;

/**
 * This application creates a frame contains a string written as "Hello World" in customized font and customized size
 *
 */

public  class FontTest {
    public static void main(String[] args){
        EventQueue.invokeLater(() ->
        {
            FontFrame frame = new FontFrame();
            frame.setTitle("FontTest");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });
    }
    /**
     * FontFrame add an JComponent FontComponent()
     * With set screensize
     */

    public static class FontFrame extends JFrame{
        public FontFrame(){
            add(new FontComponent());
            pack();
        }
    }
}



/**
 * This class defines a JComponent with customized font and customized size
 *
 */

 class  FontComponent extends JComponent{
    Toolkit kit = Toolkit.getDefaultToolkit();
    Dimension Screen_Size = kit.getScreenSize();
    double ScreenWidth=Screen_Size.width;
    double ScreenHeight=Screen_Size.height;
    private final int DEFAULT_WIDTH = (int)(ScreenWidth*0.6);
    private final int DEFAULT_HEIGHT = (int)(ScreenHeight*0.6);

    public  void paintComponent(Graphics g){
        Graphics2D g2 = (Graphics2D) g;
        String message = "Hello, World!";

        Font f = new Font("Serif", Font.BOLD, 36);
        g2.setFont(f);
        String i;
        i = f.getFontName();
        System.out.println(i);




        // get the screen font identiity
        FontRenderContext context = g2.getFontRenderContext();
        // getStringBounds will return a width of the horizontal edge to edge distance in pixel
        //      a height contains ascent + descent + leading (baseline to baseline)
        //      the top left conner of the rectangle has a negative y cooridinate
        LineMetrics M1 = f.getLineMetrics(message, context);
        Rectangle2D bounds = f.getStringBounds(message, context);

        // using lineMetrics
        double a = M1.getAscent();
        double d = M1.getDescent();
        double l = M1.getLeading();
        double h = M1.getHeight();

        System.out.println("Ascent is: "+a);
        System.out.println("Descent is: "+d);
        System.out.println("Leading is: "+l);
        System.out.println("Height is: "+h);


        // getWidth() will return the width of the current component
        // bounds.getWidth() will return the width of the line of text
        double x = (getWidth() - bounds.getWidth())/2;
        double y = (getHeight() - bounds.getHeight())/2;

        double ascent = -bounds.getY();
        // the coorinate system starts from the top left conner therefore if we want to move a coordinate downward
        //      we should add poistive number to it's existing coordinate
        double baseY = y + ascent;

        // draw the string at x and y position with "Hello, World!" and colour LIGHT_GRAY
        g2.drawString(message, (int)x, (int) baseY);
        g2.setPaint(Color.LIGHT_GRAY);

        // draw baseline
        g2.draw(new Line2D.Double(x, baseY, x+bounds.getWidth(), baseY));
        // draw the boundry rectangle 
        Rectangle2D rect = new Rectangle2D.Double(x,y,bounds.getWidth(),bounds.getHeight());
        g2.draw(rect);
    }

    // to return a first choose dimension
    public Dimension getPreferredSize(){
        return new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT);
    }



}