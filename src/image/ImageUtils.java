package image;

//import com.sun.image.codec.jpeg.JPEGCodec;
//import com.sun.image.codec.jpeg.JPEGImageEncoder;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * User:  maktub
 * Date:   16/3/14 上午11:19
 */
public class ImageUtils {

    private String fileName;

    private Image img;

    private int width;

    private int height;

    public ImageUtils(String fileName) {

        this.fileName = fileName;
        File file = new File(fileName);

        try {
            img = ImageIO.read(file);
            width = img.getWidth(null);
            height = img.getHeight(null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public File resizeFix(int w, int h) {

        if (width / height > w / h) {
            return resizeByWidth(w);
        }else {
            return resizeByHeight(h);
        }
    }

    private File resizeByWidth(int w) {
        int h = (int) (height * w / width);
        return resize(w, h);
    }

    private File resizeByHeight(int h) {
        int w = (int) (width * h / height);
        return resize(w, h);
    }

    private File resize(int w, int h) {
        BufferedImage image = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
        image.getGraphics().drawImage(img, 0, 0, w, h, null);
        File destFile = new File(fileName + "_" + w + "*" + h + "." + getImageSuffix());
        try {
            FileOutputStream out = new FileOutputStream(destFile);
            ImageIO.write(image, getImageSuffix(), out);
//            JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
//            encoder.encode(image);
            out.close();
            return destFile;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private String getImageSuffix() {
        return this.fileName.substring(fileName.lastIndexOf(".") + 1);
    }

}
