import edu.duke.*;

public class MarkovRunner {
    public void runMarkovZero(){
        String txt = new FileResource().asString();
        txt.replace('\n',' ');
        MarkovZero markov = new MarkovZero();
        markov.setTraining(txt);
        for (int k=0; k < 3; k++){
            String text = markov.getRandomText(500,0);
            printOut(text);
        }
    }

    public void runMarkov(){
        String txt = new FileResource().asString();
        txt.replace('\n',' ');
//        System.out.println(txt.length());
        MarkovZero markov = new MarkovOne();
        markov.setTraining(txt);
        for (int k=0; k < 3; k++){
            String text = markov.getRandomText(500,3);
            printOut(text);
        }
    }

    public void printOut(String s){
        String [] words = s.split("\\s+");
        int psize = 0;
        System.out.println("----------------------------------");
        for (int k=0; k < words.length; k++){
            System.out.print(words[k]+" ");
            psize += words[k].length()+1;
            if (psize > 60){
                System.out.println();
                psize = 0;
            }
        }
        System.out.println("\n----------------------------------");
    }
}
