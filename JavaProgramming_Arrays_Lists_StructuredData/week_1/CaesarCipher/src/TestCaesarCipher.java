import edu.duke.*;
public class TestCaesarCipher {
    public int[] countLetters(String encrypted){
        int[] counts = new int[26];
        String alpha = "abcdefghijklmnopqrstuvwxyz";
        for (int i = 0; i < encrypted.length(); i++){
            char ch = encrypted.charAt(i);
            int index = alpha.indexOf(Character.toLowerCase(ch));
            if (index != -1){
                counts[index]++;
            }
        }
        return counts;
    }

    public int maxIndex(int[] vals){
        int maxDex = 0;
        for (int k=0; k < vals.length; k++){
            if (vals[k] > vals[maxDex]){
                maxDex = k;
            }
        }
        return maxDex;
    }

    private String breakCaesarCipher (String input){
        int[] freqs = countLetters(input);
        int maxDex = maxIndex(freqs);
        int dkey = maxDex - 4; // assume e has the highest frequency (index 4, 4+(26-dkey) = maxDex
        dkey = (26 - (4-maxDex))%26;
        System.out.println(dkey);
        CaesarCipher cc = new CaesarCipher(dkey);
        return cc.decrypt(input);
    }

    public void simpleTests(){
        String resource = new FileResource().asString();
        CaesarCipher cc1 = new CaesarCipher(18);
        String encrypted = cc1.encrypt(resource);
        System.out.println("Encrypted message is "+encrypted);
        String decrypted = cc1.decrypt(encrypted);
        System.out.println("Decrypted message is "+decrypted);
        System.out.println("Using breakcasarcipher: "+breakCaesarCipher(encrypted));
    }

}
