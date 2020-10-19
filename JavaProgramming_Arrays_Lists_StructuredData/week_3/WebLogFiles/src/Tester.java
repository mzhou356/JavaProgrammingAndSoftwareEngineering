
import java.util.*;

public class Tester {
    public void testLogEntry() {
        LogEntry le = new LogEntry("1.2.3.4", new Date(), "example request", 200, 500);
        System.out.println(le);
        LogEntry le2 = new LogEntry("1.2.100.4", new Date(), "example request 2", 300, 400);
        System.out.println(le2);
    }

    public void testLogAnalyzer() {
        LogAnalyzer testLA = new LogAnalyzer();
        testLA.readFile("weblog2_log");
        HashMap<String, Integer> counts = testLA.countVisitsPerIP();
//        System.out.println(testLA.countUniqueIPs());
//        System.out.println(testLA.uniqueIPVisitsOnDay("Sep 27").size());
//        System.out.println(testLA.countUniqueIPsInRange(400,499));
//        System.out.println(testLA.mostNumberVisitsByIP(counts));
//        System.out.println(testLA.iPsMostVisits(counts));
        HashMap<String, ArrayList<String>> ipDay = testLA.iPsForDays();
//        System.out.println(testLA.mostNumberVisitsByIP(counts));
//        System.out.println(testLA.dayWithMostIPVisits(ipDay));
        System.out.println(testLA.iPsWithMostVisitOnDay(ipDay,"Sep 29"));
    }

}
