import edu.duke.ImageResource;

public class BatchImageProcessor {
    public static void main(String[] args){
//        BatchGrayscale gS = new BatchGrayscale();
//        gS.BatchGrayScaleProcess();
        ImageInversion Iver = new ImageInversion();
        Iver.BatchImgInvert();
    }
}
