import edu.duke.ResourceException;
import edu.duke.*;

import javax.jnlp.DownloadService2;
import java.awt.image.RescaleOp;
import java.io.File;

public class FindManyGenes {

    public String findGene(String dna,int currIndex){
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
    public int findStopCodon(String dna,int startIndex,String stopCodon) {
        int stopIndex = dna.indexOf(stopCodon, startIndex + 3);
        while (stopIndex != -1) {
            if ((stopIndex - startIndex) % 3 == 0) {
                return stopIndex;
            }
            stopIndex = dna.indexOf(stopCodon, stopIndex + 1);
        }
        return -1;
    }

    public StorageResource getAllGenes(String dna){
        StorageResource geneList = new StorageResource();
        int startIndex = 0;
        while (true){
            String gene = findGene(dna,startIndex);
            if (gene.isEmpty()){
                break;
            }
            geneList.add(gene);
            startIndex = dna.indexOf(gene,startIndex)+gene.length();
        }
        return geneList;
    }

    public int CountGenes(String dna){
        int startIndex = 0;
        int total = 0;
        while (true){
            String gene = findGene(dna,startIndex);
            if (gene.isEmpty()){
                break;
            }
            System.out.println(total);
            total ++;
            startIndex = dna.indexOf(gene,startIndex)+gene.length();
        }
        return total;
    }

    public int howMany(String a,String b) {
        int currInd = 0;
        int total = 0;
        while (true) {
            int ind = b.indexOf(a,currInd);
            if (ind == -1){
                break;
            }
            total ++;
            currInd = ind+1;
        }
        return total;
    }


    public double cgRatio(String dna){
        int cCount = howMany("C",dna);
        int gCount = howMany("G",dna);
        int total = (gCount + cCount);
        return ((double) total)/dna.length();
    }

    public int countCTG(String dna){
        int count = 0;
        int start_ind = 0;
        while(true){
            int ind = dna.indexOf("CTG",start_ind);
            if (ind == -1){
                break;
            }
            count ++;
            start_ind = ind+3;
        }
        return count;
    }

    public void processGenes(StorageResource sr){
        int count1 = 0;
        int count2 = 0;
        int longest = 0;
        for (String gene:sr.data()){
            if(gene.length() > longest){
                longest = gene.length();
            }
            if (gene.length() > 60) {
                count1++;
                System.out.println(gene);
            }
            if (cgRatio(gene)>0.35){
                count2++;
                System.out.println(gene);
                System.out.println(cgRatio(gene));
            }
        }
        System.out.println("num of Strings longer than 9: "+count1);
        System.out.println("num of Strings contan > 0.35: "+count2);
        System.out.println("Longest gene is "+longest);
    }

    public void testProcessGenes(){
        FileResource fr = new FileResource("GRch38dnapart.fa");
        String dna = fr.asString().toUpperCase();
//        String dna = "ACAAGTTTGTACAAAAAAGCAGAAGGGCCGTCAAGGCCCACCATGCCTATTGGATCCAAAGAGAGGCCAACATTTTTTGAAATTTTTAAGACACGCTGCAACAAAGCAGATTTAGGACCAATAAGTCTTAATTGGTTTGAAGAACTTTCT";
        System.out.println(dna);
        StorageResource geneList = getAllGenes(dna);
        processGenes(geneList);
        System.out.println("ctg count: " +countCTG(dna));
    }

    public void testFindStopCodon(){
        // NO result
        String dna = "AAATGGGGAATAAGGG";
        System.out.println(findStopCodon(dna,2,"TAA"));
        System.out.println(findStopCodon(dna,2,"TAG"));
        System.out.println(findStopCodon(dna,2,"TGA"));
        // 15
        dna ="TGAATGTTTAAAGGGTGATAGTAA";
        System.out.println(findStopCodon(dna,3,"TGA"));
        //15
        dna = "AAATGAAAAGGTTGATGTGAAAAA";
        System.out.println(findStopCodon(dna,2, "TGA"));
    }
    public void testFindGene(){
        String dna = "AAATGGGGAATAAGGG";
        System.out.println(dna + "  " + findGene(dna,0));

        dna ="TGAATGTTTAAAGGGTGATAGTAA";
        System.out.println(dna + "  " + findGene(dna,0));

        dna = "AAATGAAAAGGTTGATGTGAAAAA";
        System.out.println(dna + "  " + findGene(dna,0));
    }

    public void testGetAllGene(){
//        FileResource fr = new FileResource("brca1line.fa");
//        String dna = fr.asString().toUpperCase();
        String dna = "ACAAGTTTGTACAAAAAAGCAGAAGGGCCGTCAAGGCCCACCATGCCTATTGGATCCAAAGAGAGGCCAACATTTTTTGAAATTTTTAAGACACGCTGCAACAAAGCAGATTTAGGACCAATAAGTCTTAATTGGTTTGAAGAACTTTCT";
        System.out.println(dna);
        StorageResource geneList = getAllGenes(dna);
        for (String g: geneList.data()){
            System.out.println(g);
        }
    }

    public void testCountGenes(){
        FileResource fr = new FileResource("GRch38dnapart.fa");
        String dna = fr.asString().toUpperCase();
//        String dna = "ACAAGTTTGTACAAAAAAGCAGAAGGGCCGTCAAGGCCCACCATGCCTATTGGATCCAAAGAGAGGCCAACATTTTTTGAAATTTTTAAGACACGCTGCAACAAAGCAGATTTAGGACCAATAAGTCTTAATTGGTTTGAAGAACTTTCT";
        System.out.println(dna);
        System.out.println(CountGenes(dna));
    }
    }

