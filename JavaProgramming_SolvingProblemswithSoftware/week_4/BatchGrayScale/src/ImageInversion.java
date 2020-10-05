import edu.duke.*;

import java.io.File;

public class ImageInversion {
    public ImageResource ImgInvert(ImageResource input){
        ImageResource output = new ImageResource(input.getWidth(),input.getHeight());
        for (Pixel p: output.pixels()){
            Pixel inP = input.getPixel(p.getX(),p.getY());
            p.setRed(255 - inP.getRed());
            p.setGreen(255 - inP.getGreen());
            p.setBlue(255 - inP.getBlue());
        }
        return output;
    }

    public void testImgInvert(){
        ImageResource input = new ImageResource();
        input.draw();
        ImageResource output = ImgInvert(input);
        output.draw();
    }

    public void BatchImgInvert(){
        DirectoryResource dir = new DirectoryResource();
        for (File f: dir.selectedFiles()){
            ImageResource input = new ImageResource(f);
            input.draw();
            String name = input.getFileName();
            ImageResource output = ImgInvert(input);
            output.setFileName("inverted-"+name);
            output.save();
            output.draw();
        }
    }
}
