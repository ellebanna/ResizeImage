/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package resizeimage;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author belciñaan_sd2081
 */
public class ResizeImage {

    /**
     * Resizes an image to a absolute width and height (the image may not be
     * proportional)
     *
     * @param inputImagePath Path of the original image
     * @param outputImagePath Path to save the resized image
     * @param scaledWidth absolute width in pixels
     * @param scaledHeight absolute height in pixels
     */
    public static void resize(String inputImagePath,String outputImagePath,int scaledWidth,int scaledHeight) {
        try {
            // reads input image
            File inputFile = new File(inputImagePath);
            BufferedImage inputImage = ImageIO.read(inputFile);
            
            // creates output image
            BufferedImage outputImage = new BufferedImage(scaledWidth,
                    scaledHeight, inputImage.getType());
            
            // scales the input image to the output image
            Graphics2D g2d = outputImage.createGraphics();
            g2d.drawImage(inputImage, 0, 0, scaledWidth, scaledHeight, null);
            g2d.dispose();
            
            // extracts extension of output file
            String formatName = outputImagePath.substring(outputImagePath
                    .lastIndexOf(".") + 1);
            
            // writes to output file
            ImageIO.write(outputImage, formatName, new File(outputImagePath));
        } catch (IOException ex) {
            Logger.getLogger(ResizeImage.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Resizes an image by a percentage of original size (proportional).
     *
     * @param inputImagePath Path of the original image
     * @param outputImagePath Path to save the resized image
     * @param percent a double number specifies percentage of the output image
     * over the input image.
     * @throws IOException
     */
    public static void resize(String inputImagePath,
            String outputImagePath, double percent) throws IOException {
        File inputFile = new File(inputImagePath);

        BufferedImage inputImage = ImageIO.read(inputFile);

        int scaledWidth = (int) (inputImage.getWidth() * percent);

        int scaledHeight = (int) (inputImage.getHeight() * percent);

        resize(inputImagePath, outputImagePath, scaledWidth, scaledHeight);
    }

    public static void main(String[] args) {
        String inputImagePath = "Z:/Photos/Puppy.jpg";
        String outputImagePath1 = "Z:/Photos/Puppy_Fixed.jpg";
        String outputImagePath2 = "Z:/Photos/Puppy_Smaller.jpg";
        String outputImagePath3 = "Z:/Photos/Puppy_Bigger.jpg";

        try {
            // resize to a fixed width (not proportional)
            int scaledWidth = 1024;
            int scaledHeight = 768;
            ResizeImage.resize(inputImagePath, outputImagePath1, scaledWidth, scaledHeight);

            // resize smaller by 50%
            double percent = 0.5;
            ResizeImage.resize(inputImagePath, outputImagePath2, percent);

            // resize bigger by 50%
            percent = 1.5;
            ResizeImage.resize(inputImagePath, outputImagePath3, percent);

        } catch (IOException ex) {
            System.out.println("Error resizing the image.");
            ex.printStackTrace();
        }
    }// TODO code application logic here

}
