import edu.duke.*;

import java.awt.*;
import java.io.File;
public class BatchGrayscale {
    public ImageResource GrayScaleProcess(ImageResource input){
        ImageResource output = new ImageResource(input.getWidth(),input.getHeight());
        for (Pixel p: output.pixels()){
            Pixel inputP = input.getPixel(p.getX(),p.getY());
            int grayValue = (inputP.getRed()+inputP.getGreen()+inputP.getBlue())/3;
            p.setRed(grayValue);
            p.setGreen(grayValue);
            p.setBlue(grayValue);
        }
        return output;
    }

    public void testGrayScaleProcess(){
        ImageResource input = new ImageResource();
        input.draw();
        ImageResource output = GrayScaleProcess(input);
        output.draw();
    }

    public void BatchGrayScaleProcess(){
        DirectoryResource dir = new DirectoryResource();
        for (File f:dir.selectedFiles()){
            ImageResource input = new ImageResource(f);
            String file_name = input.getFileName();
            input.draw();
            ImageResource output = GrayScaleProcess(input);
            output.setFileName("gray-"+file_name);
            output.save();
            output.draw();
        }
    }
}
