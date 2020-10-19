import java.util.*;
import edu.duke.*;

public class LogAnalyzer {
    private ArrayList<LogEntry> records;

    public LogAnalyzer(){
        records = new ArrayList<LogEntry>();
    }

    public void readFile(String filename){
        FileResource fr = new FileResource(filename);
        for (String line: fr.lines()){
            records.add( WebLogParser.parseEntry(line));
        }
    }

    public HashMap<String, Integer> countVisitsPerIP() {
        HashMap<String, Integer> counts = new HashMap<String, Integer>();
        for (LogEntry le : records) {
            String ip = le.getIpAddress();
            if (!counts.containsKey(ip)) {
                counts.put(ip, 1);
            } else {
                counts.put(ip, counts.get(ip) + 1);
            }
        }
        return counts;
    }

    public int mostNumberVisitsByIP(HashMap<String,Integer> counts){
        int most = 0;
        for (int value: counts.values()){
            if (value > most){
                most = value;
            }
        }
        return most;
    }


    public ArrayList<String> iPsMostVisits(HashMap<String,Integer> counts){
        ArrayList<String> ips = new ArrayList<String>();
        int most = mostNumberVisitsByIP(counts);
        for (String key: counts.keySet()){
            if (counts.get(key) == most && !ips.contains(key)){
                ips.add(key);
            }
        }
        return ips;
    }

    public HashMap<String, ArrayList<String>> iPsForDays(){
        HashMap<String, ArrayList<String>> ipsD = new HashMap<String, ArrayList<String>>();
        for (LogEntry le: records){
            String ip = le.getIpAddress();
            String date = le.getAccessTime().toString().substring(4,10);
            if (!ipsD.containsKey(date)){
                ArrayList<String> ips = new ArrayList<String>();
                ips.add(ip);
                ipsD.put(date, ips);
            } else{
                ipsD.get(date).add(ip);
            }
        }
        return ipsD;
    }

    public String dayWithMostIPVisits(HashMap<String, ArrayList<String>> dateIps){
        int most = 0;
        String day = "";
        for (String date: dateIps.keySet()){
            int num= dateIps.get(date).size();
            if (num > most){
                most = num;
                day = date;
            }
        }
        return day;
    }

    public ArrayList<String> iPsWithMostVisitOnDay(HashMap<String, ArrayList<String>> dayIps, String day){
        ArrayList<String> ips = dayIps.get(day);
        HashMap <String, Integer> ipCount = new HashMap<String, Integer>();
        for (String ip: ips){
            if (!ipCount.containsKey(ip)){
                ipCount.put(ip,1);
            } else{
                ipCount.put(ip, ipCount.get(ip)+1);
            }
        }
        ArrayList<String> most = iPsMostVisits(ipCount);
        return most;
    }


    public void printAll(){
        for (LogEntry le: records){
            System.out.println(le);
        }
    }

    public int countUniqueIPs(){
        assert (!records.isEmpty()):"run readFile first to add in records!";
        ArrayList<String> uniqueIPs = new ArrayList<String>();
        for (LogEntry le: records){
            String ip = le.getIpAddress();
            if (!uniqueIPs.contains(ip)){
                uniqueIPs.add(ip);
            }
        }
        return uniqueIPs.size();
    }

    public void printAllHigherThanNum(int num){
        assert(!records.isEmpty()):"run readFile first to add in records!";
        for (LogEntry le: records){
            int status = le.getStatusCode();
            if (status > num){
                System.out.println(le);
            }
        }
    }

    public ArrayList<String> uniqueIPVisitsOnDay(String someday){
        assert(!records.isEmpty()):"run readFile first to add in records";
        ArrayList<String> visits = new ArrayList<String>();
        for (LogEntry le: records){
            String date = le.getAccessTime().toString().substring(4,10);
            String ip = le.getIpAddress();
            if (date.equalsIgnoreCase(someday) && (!visits.contains(ip))){
                visits.add(ip);
            }
        }
        return visits;
    }

    public int countUniqueIPsInRange(int low, int high){
        assert(!records.isEmpty());
        ArrayList<String> ips = new ArrayList<String>();
        for (LogEntry le: records){
            int status = le.getStatusCode();
            String ip = le.getIpAddress();
            if ((status>=low) && (status<=high) && !(ips.contains(ip))){
                ips.add(ip);
            }
        }
        return ips.size();
    }



}
