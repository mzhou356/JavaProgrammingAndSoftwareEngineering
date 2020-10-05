
/**
 * Write a description of Part2 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part2 {
    public String findSimpleGene(String dna, String startCodon,String endCodon){
        String result = "";
        int start = dna.indexOf(startCodon);
        int end = dna.indexOf(endCodon,start+3)+3;
        if (start == -1 || end == -1 || (end-start)%3!=0){
            return result;
        }
        result = dna.substring(start,end);
        return result.toLowerCase();
    }
    
    public void testSimpleGene(){
        String dna = "TAAATGACCGTATAG";
        System.out.println("The DNA strand is "+dna);
        System.out.println("The gene is "+findSimpleGene(dna,"ATG","TAA"));
        
        dna = "TAAATCACCGTATTAG";
        System.out.println("The DNA strand is "+dna);
        System.out.println("The gene is "+findSimpleGene(dna,"ATG","TAA"));
        
        dna = "TAAATGACCGTAATAAG";
        System.out.println("The DNA strand is "+dna);
        System.out.println("The gene is "+findSimpleGene(dna,"ATG","TAA"));
        
        dna = "TAAATGACCGTATAAG";
        System.out.println("The DNA strand is "+dna);
        System.out.println("The gene is "+findSimpleGene(dna,"ATG","TAA"));
        
        dna = "TAAATGACCCCCGTATAAG";
        System.out.println("The DNA strand is "+dna);
        System.out.println("The gene is "+findSimpleGene(dna,"ATG","TAA"));
        
    }
}
