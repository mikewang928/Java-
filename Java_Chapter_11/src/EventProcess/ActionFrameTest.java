package EventProcess;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * this program will demonstrates a color change actions with designed short cut:
 *  ctrl+R : turn the screen to Red
 *  ctrl+B : turn the screen to Blue
 *  ctrl+Y : turn the screen to Yellow
 */

public class ActionFrameTest {
    public static void main(String[] args){
        EventQueue.invokeLater(() ->{
            ActionFrame frame = new ActionFrame();
            frame.setTitle("Action Frame test");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);

        });
    }
}


class ActionFrame extends JFrame{
    private JPanel buttonPanel;
    Toolkit kit = Toolkit.getDefaultToolkit();
    Dimension screenSize = kit.getScreenSize();
    private final int DEFAULT_WIDTH=(int)(screenSize.getWidth()*0.6);
    private final int DEFAULT_HEIGHT=(int)(screenSize.getHeight()*0.6);

    public ActionFrame(){
        setSize(DEFAULT_WIDTH,DEFAULT_HEIGHT);
        buttonPanel = new JPanel();

        // define ColorActions objects
        Action yellowAction = new ColorAction("Yellow", new ImageIcon("yellow-ball.gif"),Color.YELLOW);
        Action blueAction = new ColorAction("Blue", new ImageIcon("blue-ball.gif"),Color.BLUE);
        Action redAction = new ColorAction("Red", new ImageIcon("red-ball.gif"),Color.RED);
        Action whiteAction = new ColorAction("white", new ImageIcon("while-ball.gif"), Color.WHITE);
        Action greenAction = new ColorAction("Green",new ImageIcon("green-ball.gif"), Color.GREEN);

        // add buttons for these actions on panel
        buttonPanel.add(new JButton(yellowAction));
        buttonPanel.add(new JButton(blueAction));
        buttonPanel.add(new JButton(redAction));
        buttonPanel.add(new JButton(whiteAction));
        buttonPanel.add(new JButton(greenAction));

        // add panel to frame
        add(buttonPanel);

        // associate the Y, B, R keys with names
        // to avoid focus problem we need to set the conditions as follows:
        /**
         * WHEN_ANCESTOR_OF_FOCUSED_COMPONENT
         *      * Constant used for <code>registerKeyboardAction</code> that
         *      * means that the command should be invoked when the receiving
         *      * component is an ancestor of the focused component or is
         *      * itself the focused component.
         */
        /**
         * WHEN_FOCUSED(default setting)
         *      * Constant used for <code>registerKeyboardAction</code> that
         *      * means that the command should be invoked when
         *      * the component has the focus.
         */
        /**
         * WHEN_IN_FOCUSED_WINDOW
         *      * Constant used for <code>registerKeyboardAction</code> that
         *      * means that the command should be invoked when
         *      * the receiving component is in the window that has the focus
         *      * or is itself the focused component.
         */

        InputMap imap= buttonPanel.getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
//        InputMap imap= buttonPanel.getInputMap(JComponent.WHEN_FOCUSED);
//        InputMap imap= buttonPanel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        imap.put(KeyStroke.getKeyStroke("ctrl Y"), "panel.yellow");
        imap.put(KeyStroke.getKeyStroke("ctrl B"), "panel.blue");
        imap.put(KeyStroke.getKeyStroke("ctrl R"), "panel.red");
        imap.put(KeyStroke.getKeyStroke("ctrl G"),"panel.green");
        imap.put(KeyStroke.getKeyStroke("ctrl W"),"panel.white");

        // associate the names with actions
        ActionMap amap = buttonPanel.getActionMap();
        amap.put("panel.yellow",yellowAction);
        amap.put("panel.blue", blueAction);
        amap.put("panel.red", redAction);
        amap.put("panel.white",whiteAction);
        amap.put("panel.green",greenAction);

    }

    /**
     * This is a template class to create an ColorAction
     * ColorAction extends AbstractAction
     * AbstractAction implement action interface
     */
    public class ColorAction extends AbstractAction{
        /**
         * Constructs a color action
         * @param name action name
         * @param icon action icon
         * @param c color
         */
        public ColorAction(String name, Icon icon, Color c){
            putValue(Action.NAME, name);
            putValue(Action.SMALL_ICON, icon);
            putValue(Action.SHORT_DESCRIPTION, "set panel color to " +name.toLowerCase());
            putValue("color", c);
        }

        // implementing the actionPerformed method
        public void actionPerformed (ActionEvent event){
            Color c = (Color) getValue("color");
            buttonPanel.setBackground(c);
        }
    }
}

