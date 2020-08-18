import java.awt.*;
import javax.swing.*;

/**
 *  this program will spread the icon image on the frame created
 */

public class ImageTest {
    // remember the correct format here is public static void main(String[] args){}
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            ImageFrame frame = new ImageFrame();
            frame.setTitle("ImageTest");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });
    }
}



/**
 * this class creates an frame with an image component
 */

class ImageFrame extends JFrame {
    public ImageFrame(){
        add(new ImageComponent());
        pack();
    }
}



/**
 * this class creates an component which displays a tile image
 */

class ImageComponent extends JComponent{
    Toolkit kit = Toolkit.getDefaultToolkit();
    Dimension Screen_Size = kit.getScreenSize();
    double screenWidth = Screen_Size.width;
    double screenHeight = Screen_Size.height;

    int Default_width = (int)(screenWidth*0.6);
    int Default_Height = (int)(screenHeight*0.6);

    private Image image;

    // load the image to the program
    public ImageComponent(){
        image = new ImageIcon("blue-ball.gif").getImage();
    }

    // this method will run automaticlly after the program runs
    public void paintCompant(Graphics g){
        if(image == null) return;
        // get dimensions of the image
        int imageWidth = image.getWidth(this);
        int imageHeight = image.getHeight(this);
        //draw the first image
        g.drawImage(image, 0, 0, null);
        // draw the image tile column by column
        for (int i=0;i*imageWidth<=Default_width;i++){
            for(int j=0; j*imageHeight<=Default_Height;j++){
                if(i+j>0){
                    // copy the image located at the (0,0) position to new (i*imageWidth,j*imageHeight) with
                    //  with dimensions of (imageWidth,imageHeight)
                    g.copyArea(0,0,imageWidth,imageHeight,i*imageWidth,j*imageHeight);
                }
            }
        }
    }

    // overwrite the first choose dimension
    public Dimension getPreferredSize(){
        return new Dimension(Default_width,Default_Height);
    }
}