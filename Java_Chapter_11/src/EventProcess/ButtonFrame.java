package EventProcess;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * This class implemented a simple reaction to a button related action
 * There are three buttons on one frame, each button have it's own listener
 * When the buttons are pushed the background color will be changed
 */

/**
 * ButtonTest contains the main method
 */
class ButtonTest{
    public static void main(String[] args){
        EventQueue.invokeLater(() ->{
            ButtonFrame frame = new ButtonFrame();
            frame.setTitle("ButtonTest");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });
    }
}

/**
 * This class created a JFrame object which contains three buttons
 */
public class ButtonFrame extends JFrame{
    private JPanel buttonPanel;
    Toolkit kit = Toolkit.getDefaultToolkit();
    Dimension screenSize = kit.getScreenSize();
    double ScreenWidth = screenSize.width;
    double ScreenHeight = screenSize.height;

    int Default_Width = (int)(ScreenWidth*0.6);
    int Default_Height = (int)(ScreenHeight*0.6);

    // the set up starts here
    public ButtonFrame() {

        //step #1: set Frame Sizes
        setSize(Default_Width,Default_Height);
//        setSize(100,100);

        //Step #2: Create buttons
        JButton yellowButton = new JButton("Yellow");
        JButton blueButton = new JButton("Blue");
        JButton redButton = new JButton("Red");
        JButton ResetButton = new JButton("Reset");

        //Step #3: Create panels and add buttons to this panel
        buttonPanel = new JPanel();

        buttonPanel.add(yellowButton);
        buttonPanel.add(blueButton);
        buttonPanel.add(redButton);
        buttonPanel.add(ResetButton);

        add(buttonPanel);

        //Step #4: define Actions
        ColorAction yellowAction = new ColorAction(Color.yellow);
        ColorAction blueAction = new ColorAction(Color.blue);
        ColorAction redAction = new ColorAction(Color.red);
        ColorAction whiteAction = new ColorAction(Color.white);

        //Step #5: add actionListeners
        yellowButton.addActionListener(yellowAction);
        blueButton.addActionListener(blueAction);
        redButton.addActionListener(redAction);
        ResetButton.addActionListener(whiteAction);
    }

    /**
     * in this class we will define the action taken after the event
     */
    class ColorAction implements ActionListener{
        private Color backgroundColor;
        public ColorAction(Color c){
            backgroundColor=c;
        }
        // here actionPerformed will handle the event
        public void actionPerformed(ActionEvent event){
            buttonPanel.setBackground(backgroundColor);
        }
    }
}


