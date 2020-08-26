
package EventProcess;

import javax.swing.*;
import java.awt.*;

/**
 * This application allows you to switch it's appearance in different style after you push a button
 *
 * issues: i still couldn't set the frame size
 */

public class ButtonTest_1_2 {
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            PlafFrame frame = new PlafFrame();
            frame.setTitle("change appearance");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
            frame.setResizable(false);

        });
    }


    public static class PlafFrame extends JFrame {
        private JPanel buttonPanel;
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        int Default_Width = (int) (screenSize.width * 0.6);
        int Default_Height = (int) (screenSize.height * 0.6);

        public PlafFrame() {
            setSize(Default_Width,Default_Height);
            buttonPanel = new JPanel();
            //get a list of installed look-and-feel object
            UIManager.LookAndFeelInfo[] infos = UIManager.getInstalledLookAndFeels();
            //getName() return an appearance object name
            //getClassName() return an look-and-feel object name
            for (UIManager.LookAndFeelInfo info : infos)
                makeButton(info.getName(), info.getClassName());
            add(buttonPanel);
            pack();
        }

        /**
         * Makes a button to change the pluggable look and feel
         *
         * @param name      the button name
         * @param className the name of the look-and-feel class
         */
        private void makeButton(String name, String className) {

            JButton button = new JButton(name);

            buttonPanel.add(button);

            button.addActionListener(event -> {
                try {
                    // both UIManager.setLookAndFeel() and SwingUtilities.updateComponentTreeUI() are static method
                    UIManager.setLookAndFeel(className);
                    // since we are using lambda "this" are according to the external object
                    SwingUtilities.updateComponentTreeUI(this);
                    pack();
                } catch (Exception e) {
                    e.printStackTrace(); //?
                }
            });

        }
    }

//    class Terminator extends WindowAdapter{
//        public void widowClosing(WindowEvent e){
//            if(useragrees)
//                system.exit(0);
//        }
//    }
}

