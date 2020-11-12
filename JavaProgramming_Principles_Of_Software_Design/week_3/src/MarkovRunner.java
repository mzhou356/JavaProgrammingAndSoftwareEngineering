
/**
 * Write a description of class MarkovRunner here.
 *
 * @author Duke Software
 * @version 1.0
 */

import edu.duke.*;

public class MarkovRunner {
    public void runMarkovZero() {
        FileResource fr = new FileResource();
        String st = fr.asString();
        st = st.replace('\n', ' ');
        MarkovZero markov = new MarkovZero();
        markov.setTraining(st);
        markov.setRandom(88);
        for(int k=0; k < 3; k++){
            String text = markov.getRandomText(500);
            printOut(text);
        }
    }

    public void runModel(IMarkovModel markov, String text, int size){
        markov.setTraining(text);
        System.out.println("running with "+markov);
        for (int k=0; k<3; k++){
            String st = markov.getRandomText(size);
            printOut(st);
        }
    }

    public void runMarkov(){
//        FileResource fr = new FileResource();
//        String st = fr.asString();
        String st = new FileResource().asString();
        st = st.replace('\n', ' ');
        /*
        MarkovOne markov = new MarkovOne();
        markov.setTraining(st);
        markov.setRandom(42);
        String text = markov.getRandomText(500);
        printOut(text);
        MarkovFour markov = new MarkovFour();
        markov.setTraining(st);
        markov.setRandom(25);
        */
        MarkovModel markov = new MarkovModel(6);
//        markov.setTraining(st);
        markov.setRandom(38);
        runModel(markov, st, 500);
//        String text = markov.getRandomText(500);
//        printOut(text);
    }

    private void printOut(String s){
        String[] words = s.split("\\s+");
        int psize = 0;
        System.out.println("----------------------------------");
        for(int k=0; k < words.length; k++){
            System.out.print(words[k]+ " ");
            psize += words[k].length() + 1;
            if (psize > 60) {
                System.out.println();
                psize = 0;
            }
        }
        System.out.println("\n----------------------------------");
    }

}
