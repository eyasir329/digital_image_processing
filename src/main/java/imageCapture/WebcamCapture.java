package imageCapture;

import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamPanel;
import com.github.sarxos.webcam.WebcamResolution;
import com.github.sarxos.webcam.util.ImageUtils;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

 class WebcamCapture {
    Webcam webcam = Webcam.getDefault();
    public void webCamera() throws IOException {
        webcam.setViewSize(new Dimension(640,480));
        webcam.open();
        BufferedImage image = webcam.getImage();
        ImageIO.write(image, ImageUtils.FORMAT_JPG, new File("data/test/0/testImage.jpg"));
        
//        WebcamPanel panel = new WebcamPanel(webcam);
//        panel.setImageSizeDisplayed(true);
//
//        JFrame window = new JFrame("Webcam");
//        window.add(panel);
//        window.setResizable(true);
//        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        window.pack();
//        window.setVisible(true);

    }
    
    public static void main(String args[]) throws IOException {
    	WebcamCapture webCam = new WebcamCapture();
        webCam.webCamera();
        System.out.println("Capturing Test Image Successfull");
    }
   
}