package digital_image_processing;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class Image {
    //import image file
    BufferedImage img = null;
    File file = null;

    void fileOut(String imgFilePath, int k) throws IOException{
        System.out.println(imgFilePath);
        // read image
        try {
            file = new File(imgFilePath);
            img = ImageIO.read(file);
            System.out.println("Successfully Image Imported");
        } catch (IOException ep) {
            System.out.println(ep);
        }
        grayScale(img,imgFilePath,k);
    }
    // grayscale conversion
    public void grayScale(BufferedImage img,String path,int k) throws IOException {
        
        // image width, height
        int width = img.getWidth();
        int height = img.getHeight();
        // for saving gray scale value we have to declare array
        int[][] imgArr = new int[height][width];

        // grayscale conversion

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                // method weighted/luminosity
                Color c = new Color(img.getRGB(x, y));
                int red = (int) (c.getRed() * 0.299);
                int green = (int) (c.getGreen() * 0.587);
                int blue = (int) (c.getBlue() * 0.114);
                int sum = red + green + blue;
                Color newColor = new Color(sum, sum, sum);
                img.setRGB(x, y, newColor.getRGB());
                // input value in array
                imgArr[y][x] = sum;
            }
        }

        // // write image
        // String graY = "data/grayScale/" + mainFolderName + "/" + mainFileName + ".jpg";
        // try {
        //     file = new File(graY);
        //     ImageIO.write(img, "jpg", file);
        //     System.out.println("GrayScale Completed");
        // } catch (IOException ep) {
        //     System.out.println(ep);
        // }
        Algorithms a = new Algorithms();
        a.lbp(imgArr,k);
        //return imgArr;
    }


    // lbp to img convert
    /*
    public void binaryImage(int[][] lbpArr,int k,int fileN) throws IOException {
        //Algorithms al = new Algorithms();
        //int[][] lbpArr = al.lbp();
        int height = lbpArr.length;// rows
        int width = lbpArr[0].length;// cols
        int threshold = 128;
        // convert lbp to binary array using threshold
        int[][] binaryArray = new int[height][width];
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if (lbpArr[y][x] >= threshold) {
                    binaryArray[y][x] = 1;
                } else {
                    binaryArray[y][x] = 0;
                }
            }
        }

        // create buffered image to represent the binary image
        BufferedImage binaryImage = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_BINARY);

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if (binaryArray[y][x] == 1) {
                    binaryImage.setRGB(x, y, 0xFFFFFF);
                } else {
                    binaryImage.setRGB(x, y, 0);
                }
            }
        }

        // write image
        File imageOut = null;
        String binarY = "data/binaryImage/" + fileN + "/" +k+".jpg";
        try {
            imageOut = new File(binarY);
            ImageIO.write(binaryImage, "png", imageOut);
            System.out.println("BinaryImage Completed");
        } catch (IOException e) {
            System.out.println("Error" + e);
        }

    }*/
   
}

