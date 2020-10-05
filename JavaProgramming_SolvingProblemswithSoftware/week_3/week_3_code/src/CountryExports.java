import edu.duke.*;
import org.apache.commons.csv.*;

import javax.activation.FileDataSource;
import java.io.File;
import java.io.FileReader;

public class CountryExports{
    public void listExporters(CSVParser parser,String exportOfInterest){
        int count = 0;
        for (CSVRecord record:parser){
            String exports = record.get("Exports");
            if (exports.contains(exportOfInterest)){
//                String country = record.get("Country");
//                System.out.println(country);
                count ++;
            }
        }
        System.out.println(count);
    }

    public void tester(String file_name){
        FileResource fr = new FileResource(file_name);
        CSVParser parser = fr.getCSVParser();
//        System.out.println(countryInfo(parser,"Nauru"));
//        parser = fr.getCSVParser();
//        listExporters(parser,"cocoa");
//        listExportsTwoProducts(parser,"cotton", "flowers");
//        parser = fr.getCSVParser();
        bigExports(parser,"$999,999,999,999");
    }

    public String countryInfo(CSVParser parser,String Country){
        String output = "";
        for (CSVRecord record:parser){
            String country = record.get("Country");
            if (country.equalsIgnoreCase(Country)){
                output += country+": ";
                output += record.get("Exports")+": ";
                output += record.get("Value (dollars)");
                break;
            }
        }
        return output;
    }

    public void listExportsTwoProducts(CSVParser parser, String exportItem1, String exportItem2){
        for (CSVRecord record:parser){
            String exportList = record.get("Exports");
            if (exportList.contains(exportItem1) && exportList.contains(exportItem2)){
                System.out.println(record.get("Country"));
            }
        }
    }

    public void bigExports(CSVParser parser,String amount){
        for (CSVRecord record:parser){
            String value = record.get("Value (dollars)");
            if (value.length() > amount.length()){
                System.out.print(record.get("Country")+" ");
                System.out.println(value);
            }
        }
    }


}
