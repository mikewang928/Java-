import java.awt.*;

public class FontExtraction {
    private static int i=0;
    private static int n=0;
    public static void main(String[] args){
        String[] fontNames = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
        // extract print the elements in the lists one by one
        /**
         * similar as in python
         * for fontName in fonNames:
         *      print(fontName)
         */
        for (String fontName : fontNames)
            System.out.println(fontName);

        // this is an alternative method for extracting elements in fontNames[] array
        /**
        n = fontNames.length;
        for(i=0;i<n;i++){
            System.out.println(fontNames[i]);
        }
         */
    }
}
