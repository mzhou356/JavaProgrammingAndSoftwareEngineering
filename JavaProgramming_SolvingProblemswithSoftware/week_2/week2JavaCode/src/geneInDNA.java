/*
Client class
 */


import edu.duke.*;

public class geneInDNA {
    public static void main(String[] args){
        FindManyGenes test1 = new FindManyGenes();
//        FileResource fr = new FileResource("GRch38dnapart.fa");
//
//        String dna = fr.asString().toUpperCase();
//        System.out.println(dna.length());
//        System.out.println(findStopCodon(dna,dna.indexOf("ATG"),"TAA"));
//        System.out.println(findStopCodon(dna,dna.indexOf("ATG"),"TAG"));
//        System.out.println(findStopCodon(dna,dna.indexOf("ATG"),"TGA"));
//        System.out.println(findGene(dna,0));
//        FindManyGenes test1 = new FindManyGenes();
////        test1.testFindStopCodon();
        test1.testProcessGenes();
//        test1.testCountGenes();
//        StringOccurences test2 = new StringOccurences();
//        test2.findAbc("abcdkfjsksioehgjfhsdjfhksdfhuwabcabcajfieowj");
//        test2.testHowMany();


        }

    public static int findStopCodon(String dna,int startIndex,String stopCodon) {
        int stopIndex = dna.indexOf(stopCodon, startIndex + 3);
        while (stopIndex != -1) {
            if ((stopIndex - startIndex) % 3 == 0) {
                return stopIndex;
            }
            stopIndex = dna.indexOf(stopCodon, stopIndex + 1);
        }
        return -1;
    }

    public static String findGene(String dna,int currIndex){
        int firstIndex = dna.indexOf("ATG",currIndex);
        if(firstIndex == -1) return "";
        int ind1 = findStopCodon(dna,firstIndex,"TAA");
        int ind2 = findStopCodon(dna,firstIndex,"TAG");
        int ind3 = findStopCodon(dna,firstIndex,"TGA");
        int minInd = -1;
        int temp = -1;
        if (Math.min(ind1,ind2)!=-1) temp = Math.min(ind1,ind2);
        else if (Math.max(ind1,ind2)!=-1) temp = Math.max(ind1,ind2);
        if (Math.min(temp,ind3)!=-1) minInd = Math.min(temp,ind3);
        else if (Math.max(temp,ind3)!=-1) minInd = Math.max(temp,ind3);
        if (minInd == -1){
            return "";
        }

        return dna.substring(firstIndex, minInd + 3);

    }

}
