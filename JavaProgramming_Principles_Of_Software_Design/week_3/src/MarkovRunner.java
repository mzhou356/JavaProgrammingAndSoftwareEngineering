
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
        markov.setRandom(1024);
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

    public void runMarkovWord(){
        FileResource fr = new FileResource();
        String st = fr.asString();
        st = st.replace('\n', ' ');
        MarkovWord mkw = new MarkovWord(5);
        mkw.setTraining(st);
        mkw.setRandom(844);
        String text = mkw.getRandomText(120);
        printOut(text);
    }

    public void testHashMap(){
//        String st = "this is a test yes this is really a test yes a test this is wow";
        FileResource fr = new FileResource();
        String st = fr.asString();
        st = st.replace('\n', ' ');
        EfficientMarkovModel mkm = new EfficientMarkovModel(5);
        mkm.setTraining(st);
        mkm.setRandom(531);
        mkm.printHashMapInfo();
//        EfficientMarkovWord mkw = new EfficientMarkovWord(2);
//        mkw.setTraining(st);
//        mkw.setRandom(65);
//        mkw.printHashMapInfo();
//        String text = mkw.getRandomText(50);
//        printOut(text);
    }

    public void compareMethods(){
        FileResource fr = new FileResource();
        String st = fr.asString();
        st = st.replace('\n', ' ');
        MarkovWord mkw = new MarkovWord(2);
        mkw.setTraining(st);
        mkw.setRandom(42);
        Long start = System.nanoTime();
        String text = mkw.getRandomText(100);
        System.out.println("Regular model took "+(System.nanoTime()-start)+ "nano seconds");
        EfficientMarkovWord mkwE = new EfficientMarkovWord(2);
        mkwE.setTraining(st);
        mkwE.setRandom(42);
        start = System.nanoTime();
        text = mkwE.getRandomText(100);
        System.out.println("Efficient model took "+(System.nanoTime()-start)+ "nano seconds");
    }

    public void runMarkov(){
//        FileResource fr = new FileResource();
//        String st = fr.asString();


        String st = new FileResource().asString();
        st = st.replace('\n', ' ');
//        MarkovWordOne mw1 = new MarkovWordOne();
////        System.out.println("Runing with "+mw1);
//        mw1.setTraining(st);
//        mw1.setRandom(365);
//        String text = mw1.getRandomText(120);
//        printOut(text);


//        MarkovWordTwo mw1 = new MarkovWordTwo();
//        System.out.println("Runing with "+mw1);
//        mw1.setTraining(st);
//        mw1.setRandom(832);
//        String text = mw1.getRandomText(120);
//        printOut(text);


//        MarkovOne markov = new MarkovOne();
//        markov.setTraining(st);
//        markov.setRandom(365);
//        String text = markov.getRandomText(500);
//        printOut(text);
//        MarkovFour markov = new MarkovFour();
//        markov.setTraining(st);
//        markov.setRandom(715);
//        String text = markov.getRandomText(500);
//        printOut(text);

        MarkovModel markov = new MarkovModel(7);
        markov.setTraining(st);
        markov.setRandom(953);
//        runModel(markov, st, 500);
        String text = markov.getRandomText(500);
        printOut(text);
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
