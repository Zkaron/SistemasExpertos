package Parcial1.Proyecto;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by Erik on 7/30/2017.
 * File Manager class that opens a File and returns it as
 * an Image
 *
 * COMMENT SPECIALLY FOR MARCUS OR CURRENT READER THAT KNOWS ENGLISH
 * For the path of the image it's useful to use the current methods
 *
 * 1. Save the user current directory:
 *  If you are using an IDE is the Path to project directory,
 *  if you don't know where it is you can debug the program
 *  after assigning the string path variable.
 *
 *  private final String usrDir = System.getProperty("user.dir");
 *
 * 2. It's also useful to store in a variable the system separator
 * property, just for multiplatform purposes.
 *
 *  private final String fileSeparator = System.getProperty("file.separator");
 *
 *  This is my example:
 *
 *       private final String fileSeparator = System.getProperty("file.separator");
         private final String currDir = System.getProperty("user.dir") + fileSeparator + "src" + fileSeparator + "Parcial1.Proyecto";
         private final String BLANK = currDir + fileSeparator + "res" + fileSeparator + "blank-image.jpg";
 */
public class OpenImageManager {
    //private BufferedReader buffReader;
    private final String fileSeparator = System.getProperty("file.separator");   //

    public OpenImageManager() {
    }

    /**
     * Used to "automatically" open files, without using
     * JFileChooser, useful when you know which images
     * to load beforehand.
     *
     * @param fileNamePath the file to open
     * @return  A BufferedImage with the image to use
     * @throws IOException  In case of not loading an image
     */
    public BufferedImage openFile(String fileNamePath) {
        File inFile = new File(fileNamePath);
        BufferedImage bufferedImage = null;
        try {
            bufferedImage = ImageIO.read(inFile);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return bufferedImage;
    }

    /**
     * Opens a JFileChooser in the pictures folder of the user to open
     * an image..
     * Use this method if you don't know which images to
     * load beforehand.
     * @return the selected file
     */
    public BufferedImage openFile() {
        BufferedImage bufferedImage = null;
        JFileChooser chooser = new JFileChooser(System.getProperty("user.home")
                + fileSeparator + "Pictures");
        FileNameExtensionFilter filter = new FileNameExtensionFilter("JPG & PNG files", "jpg", "png", "jpeg");
        chooser.setFileFilter(filter);
        int reply = chooser.showOpenDialog(null);
        try {
            if (reply == JFileChooser.APPROVE_OPTION) {
                File image = chooser.getSelectedFile();
                bufferedImage = ImageIO.read(image);
            }

        } catch (Exception e) {
            //e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Select File");
        }
        return bufferedImage;
    }
}
