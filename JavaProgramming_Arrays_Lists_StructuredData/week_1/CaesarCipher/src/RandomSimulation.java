import java.util.Random;

public class RandomSimulation {
    public void imulate(int rolls){
        Random rand = new Random();
        int[] counts = new int [13]; // not need for 0 and 1

        for (int k = 0; k < rolls; k++){
            int d1 = rand.nextInt(6)+1;
            int d2 = rand.nextInt(6)+1;
            counts[d1+d2]++;
        }
        for (int i=2; i < counts.length; i++) {
            System.out.println(i+"'s =\t" + counts[i]+"\t"+100.0*counts[i]/rolls);
        }
    }
}
