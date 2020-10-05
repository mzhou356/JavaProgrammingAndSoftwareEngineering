/*
Week2 of Java Programming Course: String Class Practice
 */
public class FindGeneSimpleAndTest {
    public String findGeneSimple(String dna){
        // Start codon is "ATG"
        // Stop codon is "TAA";
        String result = "";
        int startInd = dna.indexOf("ATG");
        int endInd = dna.indexOf("TAA",startInd+3)+3;
        if (startInd == -1 || endInd == 2||(endInd-startInd)%3!=0){
            return result;
        }
        result = dna.substring(startInd,endInd);
        return result;
    }

    public void testFindGeneSimple(){
        String dna = "AATGCGTAGTATGGT";
        System.out.println("DNA strand is "+dna);
        String gene = findGeneSimple(dna);
        System.out.println("Gene is "+gene);

        dna = "AATGCTAGGTAATATGGT";
        System.out.println("DNA strand is "+dna);
        gene = findGeneSimple(dna);
        System.out.println("Gene is "+gene);

        dna = "ATCCTATGCTTCGGCTGCTCTAATATGGT";
        System.out.println("DNA strand is "+dna);
        gene = findGeneSimple(dna);
        System.out.println("Gene is "+gene);

        dna = "ATGTAA";
        System.out.println("DNA strand is "+dna);
        gene = findGeneSimple(dna);
        System.out.println("Gene is "+gene);

        dna = "ATCTAA";
        System.out.println("DNA strand is "+dna);
        gene = findGeneSimple(dna);
        System.out.println("Gene is "+gene);
    }
}
