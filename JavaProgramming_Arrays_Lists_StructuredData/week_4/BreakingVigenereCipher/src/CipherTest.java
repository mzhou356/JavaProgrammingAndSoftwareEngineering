import edu.duke.*;


import java.util.*;
public class CipherTest {
    public static void main(String[] args) {
//        String text1 = new FileResource("VigenereTestData/athens_keyflute.txt").asString();
//        CaesarCipher test1 = new CaesarCipher(3);
//        String encrypt = test1.encrypt(text1);
//        System.out.println(encrypt);
//        System.out.println(test1.decrypt(encrypt));
//        System.out.println(test1.decrypt("a"));
//        int num = 'e' - 'a';
//        System.out.println(num);
//        CaesarCracker test2 = new CaesarCracker('a');
//        System.out.println(test2.decrypt(text1));
//        System.out.println(test2.getKey(text1));
//        int[] key = {17,14,12,4};
//        VigenereCipher test3 = new VigenereCipher(key);
//        System.out.println(test3.encrypt(text1));
        VigenereBreaker test4 = new VigenereBreaker();
//        System.out.println(test4.sliceString("abcdefghijklm", 4, 5));
//        System.out.println(Arrays.toString(test4.tryKeyLength(text1,5,'e')));
//        System.out.println(test4.breakVigenere(new FileResource().asString(), 5, 'e'));
//        HashSet<String> dict = test4.readDictionary(new FileResource());
//        System.out.println(test4.breakForLanguage(new FileResource().asString(),dict));
//        String message = test4.breakForLanguage(new FileResource().asString(), dict);
        test4.breakVigenere();
//        System.out.println(test4.countWords(message, dict));
//        System.out.println(test4.mostCommonCharIn(dict));
//        String m2 = new FileResource().asString();
//        int[] keys = test4.tryKeyLength(m2, 38, 'e');
//        VigenereCipher test = new VigenereCipher(keys);
//        String testM = test.decrypt(m2);
//        System.out.println(test4.countWords(testM, dict));



    }
}
