import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;


public class CSVWeather {
    public CSVRecord coldestHourInFile(CSVParser parser){
        CSVRecord coldestSoFar = null;
        for (CSVRecord currentRow:parser){
            coldestSoFar = lowestOfTwo(currentRow,coldestSoFar,"TemperatureF");
        }
        return coldestSoFar;
    }

    public String fileWithColdestTemperature(){
        DirectoryResource dr = new DirectoryResource();
        String file = null;
        CSVRecord prevSoFar = null;
        CSVRecord coldestSoFar = null;
        for (File f: dr.selectedFiles()){
            FileResource fr = new FileResource(f);
            CSVRecord currentRow = coldestHourInFile(fr.getCSVParser());
            coldestSoFar = lowestOfTwo(currentRow,coldestSoFar,"TemperatureF");
            if (prevSoFar == null && Double.parseDouble(coldestSoFar.get("TemperatureF")) == Double.parseDouble(coldestSoFar.get("TemperatureF")) ||
                Double.parseDouble(prevSoFar.get("TemperatureF")) != Double.parseDouble(coldestSoFar.get("TemperatureF"))){
                prevSoFar = coldestSoFar;
                file = f.getName();
            }
        }
        return file;
    }

    public CSVRecord lowestOfTwo(CSVRecord currentRow,CSVRecord lowestSoFar,String colName){
        String current = currentRow.get(colName);
        if (current.equalsIgnoreCase("N/A")) return lowestSoFar;
        double currentValue = Double.parseDouble(current);
        if (lowestSoFar == null && currentValue != -9999){
            lowestSoFar = currentRow;
        }
        else {
            double lowestValue = Double.parseDouble(lowestSoFar.get(colName));
            if(currentValue < lowestValue && currentValue !=-9999){
                lowestSoFar = currentRow;
            }
        }
        return lowestSoFar;
    }

    public void testColdestHourInFile(String filename){
        FileResource fr = new FileResource("nc_weather/2013/"+filename);
        CSVRecord coldest = coldestHourInFile(fr.getCSVParser());
        System.out.println("Coldest Temp was "+coldest.get("TemperatureF")+" at "+coldest.get("DateUTC"));
        for (CSVRecord currentRow:fr.getCSVParser()){
            System.out.println(currentRow.get("DateUTC")+": "+currentRow.get("TemperatureF"));
        }
    }

    public void testFileWithColdestTemperature(){
        String filename = fileWithColdestTemperature();
        System.out.println("Coldest day was in file "+filename);
        testColdestHourInFile(filename);

    }

    public CSVRecord lowestHumidityInFile(CSVParser parser){
        CSVRecord lowestSoFar = null;
        for (CSVRecord currentRow:parser){
            lowestSoFar = lowestOfTwo(currentRow,lowestSoFar,"Humidity");
        }
        return lowestSoFar;
    }

    public void testLowestHumidityInFile(String filename){
        FileResource fr = new FileResource("nc_weather/2014/"+filename);
        CSVParser parser = fr.getCSVParser();
        CSVRecord record = lowestHumidityInFile(parser);
        System.out.println("Lowest Humidity was "+record.get("Humidity")+" at "+record.get("DateUTC"));
    }

    public CSVRecord lowestHumidityInManyFiles(){
        DirectoryResource dir = new DirectoryResource();
        CSVRecord lowestSoFar = null;
        for (File f:dir.selectedFiles()){
            FileResource fr = new FileResource(f);
            CSVRecord current = lowestHumidityInFile(fr.getCSVParser());
            lowestSoFar = lowestOfTwo(current,lowestSoFar, "Humidity");
        }
        return lowestSoFar;
    }

    public void testLowestHumidityInManyFiles(){
        CSVRecord lowest = lowestHumidityInManyFiles();
        System.out.println("Lowest Humidity was "+lowest.get("Humidity")+" at "+lowest.get("DateUTC"));
    }

    public double averageTemperatureInFile(CSVParser parser){
        int count = 0;
        double total = 0;
        for (CSVRecord currentRow:parser){
            count ++;
            double currTemp = Double.parseDouble(currentRow.get("TemperatureF"));
            if (currTemp != -9999) {
                total += currTemp;
            }
        }
        return total/count;
    }

    public void testAverageTemperatureInFile(String filename){
        FileResource fr = new FileResource("nc_weather/2013/"+filename);
        double avgTemp = averageTemperatureInFile(fr.getCSVParser());
        System.out.println("Average temperature in file is "+avgTemp);
    }

    public double averageTemperatureWithHumidityInFile(CSVParser parser,int value){
        int count = 0;
        double total = 0;
        for (CSVRecord currentRow:parser){
            int currHumidity = Integer.parseInt(currentRow.get("Humidity"));
            if (currHumidity >= value){
                count ++;
                double currTemp = Double.parseDouble(currentRow.get("TemperatureF"));
                if (currTemp!=-9999){
                    total += currTemp;
                }
            }
        }
        if (count > 0){
            return total/count;
        }
        else{
            return -0.0;
        }
    }
    public void testAverageTemperatureWithHumidityInFile(String filename,int value){
        FileResource fr = new FileResource("nc_weather/2013/"+filename);
        double avgTemp = averageTemperatureWithHumidityInFile(fr.getCSVParser(),value);
        if (avgTemp == -0.0){
            System.out.println("No temperature with that humidity");
        }
        else{
            System.out.println("Average Temp when high humidty is "+avgTemp);
        }
    }
}

