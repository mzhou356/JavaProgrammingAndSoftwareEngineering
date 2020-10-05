import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;



public class BabyBirths {
    public int countNames(String gender){
        FileResource fr = new FileResource("birth_data/yob1900.csv");
        int count = 0;
        for (CSVRecord rec:fr.getCSVParser(false)){
            if (rec.get(1).equalsIgnoreCase(gender)){
                count ++;
            }
        }
        return count;
    }

    public int totalBirths (FileResource fr,String gender){
        int totalBirths = 0;
        int totalBoys = 0;
        int totalGirls = 0;
        for (CSVRecord rec: fr.getCSVParser(false)){
            int numBorn = Integer.parseInt(rec.get(2));
            totalBirths +=numBorn;
            if (rec.get(1).equalsIgnoreCase("M")){
                totalBoys += numBorn;
            }
            else{
                totalGirls += numBorn;
            }
        }
        System.out.println("total births = "+totalBirths);
        System.out.println("total boys = "+totalBoys);
        System.out.println("total girls = "+totalGirls);
        if (gender.equalsIgnoreCase("M")){
            return totalBoys;
        }
        return totalGirls;
    }

    public void testTotalBirths(){
        FileResource fr = new FileResource("birth_data/yob1905.csv");
        System.out.println(totalBirths(fr,"M"));
    }

    public int getRank(int year, String name, String gender){
        FileResource fr = new FileResource("birth_data/yob"+year+".csv");
        int rank = 0;
        for (CSVRecord rec: fr.getCSVParser(false)){
            if (rec.get(1).equalsIgnoreCase(gender)) {
                rank ++;
                if (rec.get(0).equalsIgnoreCase(name)){
                    return rank;
                }
            }
            if (gender.equalsIgnoreCase("F") && rec.get(1).equalsIgnoreCase("M")){
                return -1;
            }
        }
        return -1;
    }

    public void testGetRank(){
        System.out.println(getRank(1960,"Emily","F"));
        System.out.println(getRank(1971,"Frank","M"));
    }

    public String getName(int year,int rank,String gender){
        FileResource fr = new FileResource("birth_data/yob"+year+".csv");
        int count = 0;
        for (CSVRecord rec: fr.getCSVParser(false)){
            if (rec.get(1).equalsIgnoreCase(gender)) {
                count ++;
                if (count == rank){
                    return rec.get(0);
                }
            }
            if (gender.equalsIgnoreCase("F") && rec.get(1).equalsIgnoreCase("M")){
                return "NO NAME";
            }
        }
        return "NO NAME";
    }

    public void testGetName(){
        System.out.println(getName(1980,350,"F"));
        System.out.println(getName(1982,450,"M"));
    }

    public void whatIsNameInYear(String name,int year,int newYear,String gender){
        int rank = getRank(year,name,gender);
        System.out.println(rank);
        String NewName = getName(newYear,rank,gender);
        System.out.println(NewName);
        String pronoun = null;
        if (gender.equalsIgnoreCase("F")){
            pronoun = "she";
        } else{
            pronoun = "he";
        }
        System.out.println(name+" born in "+year+" would be "+NewName+" if "+pronoun+" was born in "+newYear+".");
    }

    public int yearOfHighestRank(String name,String gender){
        DirectoryResource dr = new DirectoryResource();
        int rank = 0;
        int currRank = 0;
        int year = 0;
        int currYear = 0;
        for (File f: dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            currYear = Integer.parseInt(f.getName().substring(3, 7));
            currRank = getRank(currYear, name, gender);
            if ((currRank > 0 && rank == 0) || (currRank > 0 && rank > currRank)) {
                rank = currRank;
                year = currYear;
            }
        }
        if (year == 0){
            return -1;
        }
        return year;
    }

    public double getAverageRank(String name,String gender){
        DirectoryResource dr = new DirectoryResource();
        int total = 0;
        int count = 0;
        int currYear = 0;
        int currRank = 0;
        for (File f: dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            count ++;
            currYear = Integer.parseInt(f.getName().substring(3, 7));
            currRank = getRank(currYear, name, gender);
            if (currRank > 0){
                total += currRank;
            }
            }
        if (total == 0){
            return -1.0;
        }
        return ((double) (total))/count;
        }

     public int getTotalBirthsRankedHigher(int year,String name,String gender){
        FileResource fr = new FileResource("birth_data/yob"+year+".csv");
        int count = 1;
        int rank = getRank(year,name,gender);
        if (rank == -1) {return totalBirths(fr,gender);}
        int total = 0;
        for (CSVRecord rec: fr.getCSVParser(false)){
            if (count < rank && rec.get(1).equalsIgnoreCase(gender)){
                total += Integer.parseInt(rec.get(2));
                count ++;
            } else if (count >= rank && rec.get(1).equalsIgnoreCase(gender)){
                break;
            }
        }
        return total;
     }
}
