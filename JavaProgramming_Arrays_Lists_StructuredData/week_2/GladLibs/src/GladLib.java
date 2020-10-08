import edu.duke.*;


import java.util.*;

public class GladLib {
    private HashMap<String,ArrayList<String>> wordMap;
    private Random myRandom;

    private static String dataSourceURL = "http://dukelearntoprogram.com/course3/data";
    private static String dataSourceDirectory = "GladLib/data";

    public GladLib(){
        wordMap = new HashMap<String,ArrayList<String>>();
        initializeFromSource(dataSourceDirectory);
        wordMap.put("used",new ArrayList<String>());
        wordMap.put("cate",new ArrayList<String>());
        myRandom = new Random();
    }

    public GladLib(String source){
        wordMap = new HashMap<String,ArrayList<String>>();
        initializeFromSource(source);
        wordMap.put("used",new ArrayList<String>());
        wordMap.put("cate",new ArrayList<String>());
        myRandom = new Random();
    }

    private void initializeFromSource(String source) {
        String[] labels = {"adjective","noun","color","country","name",
                "animal","timeframe","verb","fruit"};
        for (String label:labels) {
            ArrayList<String> list = readIt(source+label+".txt");
            wordMap.put(label,list);
        }
        }

    private String randomFrom(ArrayList<String> source){
        int index = myRandom.nextInt(source.size());
        return source.get(index);
    }

    private String getSubstitute(String label) {
        ArrayList<String> cateUsed = wordMap.get("cate");
        if (!cateUsed.contains(label) && !label.equalsIgnoreCase("number")){
            cateUsed.add(label);
        }
        if (label.equals("number")){
            return ""+myRandom.nextInt(50)+5;
        }
        return randomFrom(wordMap.get(label));
    }

    private String processWord(String w){
        int first = w.indexOf("<");
        int last = w.indexOf(">",first);
        if (first == -1 || last == -1){
            return w;
        }
        String prefix = w.substring(0,first);
        String suffix = w.substring(last+1);
        String sub = getSubstitute(w.substring(first+1,last));
        while(wordMap.get("used").contains(sub)){
            sub = getSubstitute(w.substring(first+1,last));
        }
        wordMap.get("used").add(sub);
        return prefix+sub+suffix;
    }

    private void printOut(String s, int lineWidth){
        int charsWritten = 0;
        for(String w : s.split("\\s+")){
            if (charsWritten + w.length() > lineWidth){
                System.out.println();
                charsWritten = 0;
            }
            System.out.print(w+" ");
            charsWritten += w.length() + 1;
        }
    }

    private String fromTemplate(String source){
        String story = "";
        int count = 0;
        if (source.startsWith("http")) {
            URLResource resource = new URLResource(source);
            for(String word : resource.words()){
                story = story + processWord(word) + " ";
                if (!word.equalsIgnoreCase(processWord(word))){
                    count++;
                }
            }
        }
        else {
            FileResource resource = new FileResource(source);
            for(String word : resource.words()){
                story = story + processWord(word) + " ";
                if (!word.equalsIgnoreCase(processWord(word))){
                    count++;
                }
            }
        }
        System.out.println("Replaced total "+count+" words.");
        return story;
    }

    private ArrayList<String> readIt(String source){
        ArrayList<String> list = new ArrayList<String>();
        if (source.startsWith("http")) {
            URLResource resource = new URLResource(source);
            for(String line : resource.lines()){
                list.add(line);
            }
        }
        else {
            FileResource resource = new FileResource(source);
            for(String line : resource.lines()){
                list.add(line);
            }
        }
        return list;
    }

    private int totalWordsInMap(){
        int total = 0;
        for (String w:wordMap.keySet()){
            if (w!="used" && w!="cate") {
                total += wordMap.get(w).size();
            }
        }
        return total;
    }

    private int totalWordsConsidered(){
        ArrayList<String> usedCate = wordMap.get("cate");
        System.out.println(usedCate);
        int total = 0;
        for (String w: usedCate){
            total += wordMap.get(w).size();
        }
        return total;
    }


    public void makeStory(){
        System.out.println("\n");
        wordMap.get("used").clear();
        String story = fromTemplate("GladLibData/data/madtemplate.txt");
        printOut(story, 60);
        System.out.println();
        System.out.println("There are total "+totalWordsInMap()+" word choices");
        System.out.println("There are total "+totalWordsConsidered()+" words considered");
    }
}

