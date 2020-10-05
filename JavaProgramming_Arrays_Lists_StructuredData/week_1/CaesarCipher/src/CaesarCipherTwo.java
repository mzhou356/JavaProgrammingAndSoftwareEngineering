public class CaesarCipherTwo {
    private String alphabet;
    private String shiftedAlphabet1;
    private String shiftedAlphabet2;
    private int key1;
    private int key2;

    public CaesarCipherTwo(int k1,int k2){
        key1 = k1;
        key2 = k2;
        alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        shiftedAlphabet1= alphabet.substring(k1)+alphabet.substring(0,k1);
        shiftedAlphabet2= alphabet.substring(k2)+alphabet.substring(0,k2);
    }

    public String encrypt(String input){
        StringBuilder encrypted = new StringBuilder(input);
        for (int i = 0; i < encrypted.length(); i++){
            char currChar = encrypted.charAt(i);
            int ind = alphabet.indexOf(Character.toUpperCase(currChar));
            if (ind !=-1){
                char replace;
                if (i % 2 == 0){
                    replace = shiftedAlphabet1.charAt(ind);
                } else {
                    replace = shiftedAlphabet2.charAt(ind);
                }
                if (Character.isLowerCase(currChar)){
                    replace = Character.toLowerCase(replace);
                }
                encrypted.setCharAt(i, replace);
            }
        }
        return encrypted.toString();
    }

    public String decrypt(String input){
        CaesarCipherTwo cct = new CaesarCipherTwo(26-key1,26-key2);
        return cct.encrypt(input);
    }
}
