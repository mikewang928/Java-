
import java.awt.*;
import javax.swing.*;

/**
 * this applications shows us how to show a line of text on a frame
 */

// main()
class NotHelloWorld{
    public static void main(String[] args){
        EventQueue.invokeLater(() ->
        {
            NotHelloWorldFrame frame = new NotHelloWorldFrame();
            frame.setTitle("Not Hello World");
            frame.setExtendedState(Frame.NORMAL);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });
    }
}

/**
 * This class defined a component of the the JFrame NotHelloWorldFrame
 * some thing need to be noticed here:
 *      1, do not call the paintComponent() method manually, when ever the application requires a repaint, the system
 *          will deal it automatically.
 *      2, if we want to force repaint the frame, we need to call the repaint() method
 *      3, the base line of the text in JComponent is 100 pixel from the (0,0) of the frame
 */
// this class created a component which contains a line of text
class NotHelloWorldComponent extends JComponent{
    Toolkit kit = Toolkit.getDefaultToolkit();
    Dimension screenSize = kit.getScreenSize();
    double Screen_Width = screenSize.width;
    double Screen_Height = screenSize.height;
    private final int DEFAULT_WIDTH = (int)(0.6*Screen_Width);
    private final int DEFAULT_HEIGHT = (int)(0.6*Screen_Height);

    // why does (int)(0.3*Screen_Width) not working
    public final int MESSAGE_X = (int)(0.4*DEFAULT_WIDTH);
    public final int MESSAGE_Y = (int)(0.5*DEFAULT_HEIGHT);

    // Here we are covering the existing method of getPrefrredSize() to return
    // a width and height object under dimension class
    public Dimension getPreferredSize(){
        return new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT);
    }
    // we define this to cover the existing paintComponent() method
    // paintComponent(Graphics g) g is a Graphics varible

    public void paintComponent(Graphics g){
        g.drawString("Not a Hello World program", MESSAGE_X, MESSAGE_Y);
    }
}

/**
 * this class is an alternative of the previous class instead of extending the JComponent
 *  we are extending the JFrame
 */

class NotHelloWorldPanel extends JPanel{
    Toolkit kit = Toolkit.getDefaultToolkit();
    Dimension screenSize = kit.getScreenSize();
    double Screen_Width = screenSize.width;
    double Screen_Height = screenSize.height;
    private final int DEFAULT_WIDTH = (int)(0.6*Screen_Width);
    private final int DEFAULT_HEIGHT = (int)(0.6*Screen_Height);

    // why does (int)(0.3*Screen_Width) not working
    public final int MESSAGE_X = (int)(0.4*DEFAULT_WIDTH);
    public final int MESSAGE_Y = (int)(0.5*DEFAULT_HEIGHT);

    // Here we are covering the existing method of getPrefrredSize() to return
    // a width and height object under dimension class
    public Dimension getPreferredSize(){
        return new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT);
    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawString("Not a Hello World program", MESSAGE_X, MESSAGE_Y);
    }

}

/**
 * this class we are setting a JFrame which contains the JComponent we just defined above
 */

class NotHelloWorldFrame extends JFrame{
    public NotHelloWorldFrame(){
        // here we r adding a component to a JFrame
        add(new NotHelloWorldPanel());
        // if your want to use the preferred size as we have defined above,
        // you should instead of using setSize(), use pack()
        pack();
        // if we want to redefine the frame size we should use setSize()
        //setSize(1000,1000);
    }
}