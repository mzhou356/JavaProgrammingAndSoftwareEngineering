
/**
 * Write a description of Part1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part1 {
    public String findSimpleGene(String dna){
        String result = "";
        int start = dna.indexOf("ATG");
        int end = dna.indexOf("TAA",start+3)+3;
        if (start == -1 || end == -1 || (end-start)%3!=0){
            return result;
        }
        result = dna.substring(start,end);
        return result;
    }
    
    public void testSimpleGene(){
        String dna = "TAAATGACCGTATAG";
        System.out.println("The DNA strand is "+dna);
        System.out.println("The gene is "+findSimpleGene(dna));
        
        dna = "TAAATCACCGTATTAG";
        System.out.println("The DNA strand is "+dna);
        System.out.println("The gene is "+findSimpleGene(dna));
        
        dna = "TAAATGACCGTAATAAG";
        System.out.println("The DNA strand is "+dna);
        System.out.println("The gene is "+findSimpleGene(dna));
        
        dna = "TAAATGACCGTATAAG";
        System.out.println("The DNA strand is "+dna);
        System.out.println("The gene is "+findSimpleGene(dna));
        
        dna = "TAAATGACCCCCGTATAAG";
        System.out.println("The DNA strand is "+dna);
        System.out.println("The gene is "+findSimpleGene(dna));
        
        dna = "TAAATGACCTTTGTAATAAG";
        System.out.println("The DNA strand is "+dna);
        System.out.println("The gene is "+findSimpleGene(dna));
        
    }
}
