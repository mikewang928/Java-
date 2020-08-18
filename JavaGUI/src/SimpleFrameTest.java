
import javax.swing.*;
import java.awt.*;


/**
 * this application shows how to create a simple JFrame
 */

public class SimpleFrameTest {
    public static void main(String[] args){
        EventQueue.invokeLater(() -> {
            SimpleFrame frame = new SimpleFrame();
            //show the frame with out close icon and any other decolations
            //frame.setUndecorated(true);

            // maximize the frame size
            // in frame.setExtendedState(int state):
            // Frame.NORMAL; Frame.ICONIFIED; Frame.MAXINIZED_HORIZ; Frame.MAXINIZED_VERT; Frame.MAXIMIZED_BOTH
            frame.setExtendedState(Frame.NORMAL);

            // terminate application when close the windows
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);


        });
    }
}

class SimpleFrame extends JFrame{
    // Toolkit class can give us the screen size of the monitor
    // Toolkit.getDfaultTookit will bive us a object of Toolkit
    Toolkit kit = Toolkit.getDefaultToolkit();
    Dimension screenSize = kit.getScreenSize();
    double screenWidth = screenSize.width;
    double screenHeight = screenSize.height;
    private final int DEFAULT_WIDTH = (int)(0.6*screenWidth);
    private final int DEFAULT_HEIGHT = (int)(0.6*screenHeight);

    public String s;
    public SimpleFrame(){
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        setTitle("Simple Frame Test");
        // set frame size to be resizable
        setResizable(true);
        setLocation((int)(0.2*screenWidth),(int)(0.2*screenHeight));
        // the platform will decided where the frame should be placed
        setLocationByPlatform(true);
        // configure the image icon
        Image img = new ImageIcon("icon.gif").getImage();
        setIconImage(img);
    }

}