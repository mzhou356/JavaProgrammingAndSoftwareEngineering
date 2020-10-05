import edu.duke.*;
import org.apache.commons.csv.*;

public class FoodsCSV {
    public void readFood(){
        FileResource fr = new FileResource("foods.csv");
        CSVParser parser = fr.getCSVParser();
        for (CSVRecord record: parser){
            System.out.print(record.get("Name")+" ");
            System.out.print(record.get("Favorite Color")+" ");
            System.out.println(record.get("favorite Food"));
        }
    }
}
