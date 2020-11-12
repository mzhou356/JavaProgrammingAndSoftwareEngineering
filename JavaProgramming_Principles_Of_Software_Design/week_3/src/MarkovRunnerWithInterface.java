import edu.duke.*;

public class MarkovRunnerWithInterface {
    public void runModel(IMarkovModel markov, String text, int size, int seed) {
//        System.out.println(text);
        markov.setTraining(text);
        markov.setRandom(seed);
        System.out.println("running with " + markov);
        for(int k=0; k < 3; k++){
            String st= markov.getRandomText(size);
            printOut(st);
        }
    }

    public void runMarkov() {
        FileResource fr = new FileResource();
        String st = fr.asString();
        st = st.replace('\n', ' ');
        int size = 200;
        int seed = 42;

        MarkovZero mz = new MarkovZero();
        runModel(mz, st, size,seed );

        MarkovOne mOne = new MarkovOne();
        runModel(mOne, st, size, seed);

        MarkovModel mThree = new MarkovModel(3);
        runModel(mThree, st, size, seed);

        MarkovFour mFour = new MarkovFour();
        runModel(mFour, st, size, seed);

    }

    public void testHashMap() {
        FileResource fr = new FileResource();
        String st = fr.asString();
        st = st.replace('\n', ' ');
//        String st = "yes-this-is-a-thin-pretty-pink-thistle";
//        System.out.println(st.length());
//        System.out.println(st.trim());
        int size = 500;
        int seed = 615;
        EfficientMarkovModel mz = new EfficientMarkovModel(5);
//        mz.setTraining(st);
        runModel(mz,st,size,seed);
//        runModel(mz, st, size, seed);
        mz.printHashMapInfo();
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

    public void compareMethods(){
        FileResource fr = new FileResource();
        String st = fr.asString();
        st = st.replace('\n', ' ');
        int size = 1000;
        int seed = 42;

        MarkovModel mz1 = new MarkovModel(2);
        long start = System.nanoTime();
        runModel(mz1, st, size,seed );
        System.out.println("Regular mz took: "+ (System.nanoTime()-start)/1000000000.0 + " secs");

        EfficientMarkovModel mz2 = new EfficientMarkovModel(2);
        start = System.nanoTime();
        runModel(mz2, st, size, seed);
        System.out.println("Efficient mz took: "+ (System.nanoTime()-start)/1000000000.0 + " secs");

    }

}
