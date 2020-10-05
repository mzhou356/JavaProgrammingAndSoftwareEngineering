import edu.duke.*;

public class CaesarCipher {
    // fields
    private String alphabet;
    private String shiftedAlphabet;
    private int mainKey;
    // constructor
    public CaesarCipher(int key){
        alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        shiftedAlphabet= alphabet.substring(key)+alphabet.substring(0,key);
        mainKey = key;
    }
    //methods
    public String encrypt(String input){
        StringBuilder encrypted = new StringBuilder(input);
        for (int i = 0; i < encrypted.length(); i++){
            char currChar = encrypted.charAt(i);
            int ind = alphabet.indexOf(Character.toUpperCase(currChar));
            if (ind != -1){
                char newChar = shiftedAlphabet.charAt(ind);
                if (Character.isLowerCase(currChar)){
                    newChar = Character.toLowerCase(newChar);
                }
                encrypted.setCharAt(i,newChar);
            }
        }
        return encrypted.toString();
    }

    public String decrypt(String input){
        CaesarCipher cc = new CaesarCipher(26-mainKey);
        return cc.encrypt(input);
    }

}
