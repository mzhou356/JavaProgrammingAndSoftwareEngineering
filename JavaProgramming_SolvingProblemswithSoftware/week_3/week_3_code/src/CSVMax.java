import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;

public class CSVMax {

    public CSVRecord hottestHourInFile(CSVParser parser){
        CSVRecord largestSoFar = null;
        for (CSVRecord currentRow: parser){
            largestSoFar = getLargestOfTwo(currentRow,largestSoFar);
        }
        return largestSoFar;
    }

    public void testHottestHourInFile(){
        FileResource fr = new FileResource("nc_weather/2012/weather-2012-07-31.csv");
        CSVParser parser = fr.getCSVParser();
        CSVRecord largest = hottestHourInFile(parser);
        System.out.println("hottest temperature was "+ largest.get("TemperatureF")+" at "+ largest.get("TimeEDT"));
    }

    public CSVRecord getLargestOfTwo(CSVRecord currentRow, CSVRecord largestSoFar){
            if (largestSoFar == null){
                largestSoFar = currentRow;
            }
            else {
                double currentTemp = Double.parseDouble(currentRow.get("TemperatureF"));
                double largestTemp = Double.parseDouble(largestSoFar.get("TemperatureF"));
                if (currentTemp > largestTemp){
                    largestSoFar = currentRow;
                }
            }
            return largestSoFar;
        }

        public CSVRecord HottestInManyDays() {
        CSVRecord largestSoFar = null;
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            CSVRecord currentRow = hottestHourInFile(fr.getCSVParser());
           largestSoFar = getLargestOfTwo(currentRow,largestSoFar);
        }
        return largestSoFar;
    }

    public void testHottestInManyDays(){
        CSVRecord largest = HottestInManyDays();
        System.out.println("hottest temperature was "+ largest.get("TemperatureF")+" at "+ largest.get("DateUTC"));
    }

}
