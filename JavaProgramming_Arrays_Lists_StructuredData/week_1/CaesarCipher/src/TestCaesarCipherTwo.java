import edu.duke.*;

public class TestCaesarCipherTwo extends TestCaesarCipher{
    private String halfOfString(String message, int start){
        StringBuilder half = new StringBuilder();
        for (int i = start; i < message.length(); i+=2){
            half.append(message.charAt(i));
        }
        return half.toString();
    }

    private String breakCaesarCipher(String input){
        int[] freqs1 = countLetters(halfOfString(input,0));
        int[] freqs2 = countLetters(halfOfString(input,1));
        int maxDex1 = maxIndex(freqs1);
        int maxDex2 = maxIndex(freqs2);
        int dkey1 = (26-(4-maxDex1))%26;
        int dkey2 = (26-(4-maxDex2))%26;
        System.out.println(dkey1+" "+dkey2);
        CaesarCipherTwo ccT = new CaesarCipherTwo(dkey1,dkey2);
        return ccT.decrypt(input);
    }

    public void SimpleTests(){
        String resource = new FileResource().asString();
        System.out.println(resource);
//        CaesarCipherTwo ccT = new CaesarCipherTwo(17,3);
//        String encryptM = ccT.encrypt(resource);
//        String decryptM = ccT.decrypt(encryptM);
//        System.out.println("Encrypted message is "+encryptM);
//        System.out.println("Decrypted message is "+decryptM);
        System.out.println("Using breaking caesarCipher: \n\n"+breakCaesarCipher(resource));
    }
}
