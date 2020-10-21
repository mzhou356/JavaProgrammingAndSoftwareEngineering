import java.util.*;
import edu.duke.*;
import java.io.File;

public class VigenereBreaker {
    public String sliceString(String message, int whichSlice, int totalSlices) {
        StringBuilder slice = new StringBuilder();
        for (int i=whichSlice; i<message.length(); i+=totalSlices){
            char c = message.charAt(i);
            slice.append(c);
        }
        return slice.toString();
    }

    public int[] tryKeyLength(String encrypted, int klength, char mostCommon) {
        int[] key = new int[klength];
        for (int i=0; i<klength; i++){
            String slice = sliceString(encrypted,i,klength);
            CaesarCracker cracker = new CaesarCracker(mostCommon);
            key[i] = cracker.getKey(slice);
        }
        return key;
    }

    public void breakVigenere () {
        HashMap<String, HashSet<String>> LangMap = new HashMap<String, HashSet<String>>();
        DirectoryResource dir = new DirectoryResource();
        for (File f:dir.selectedFiles()){
            String fn = f.getName();
            FileResource fr = new FileResource("dictionaries/"+fn);
            HashSet<String> dict = readDictionary(fr);
            LangMap.put(fn, dict);
        }
        String encrypted = new FileResource().asString();
        String decrypted = breakForAllLangs(encrypted,LangMap);
        System.out.println(decrypted);
    }

    public HashSet<String> readDictionary(FileResource fr){
        HashSet<String> dict = new HashSet<String>();
        for (String word: fr.words()){
            dict.add(word.toLowerCase());
        }
        return dict;
    }

    public int countWords(String message, HashSet<String> dictionary){
        String[] words = message.split("\\W+");
        int valid = 0;
        for (String word: words){
            if (dictionary.contains(word.toLowerCase())){
                valid++;
            }
        }
        return valid;
    }

    public String breakForLanguage(String encrypted, HashSet<String> dictionary, char mostCommon){
        String  decrypt = "";
        int keylen = 0;
        int mostword = 0;
        for (int i=1; i<101; i++){
            int[] keys = tryKeyLength(encrypted, i, mostCommon);
            VigenereCipher vc = new VigenereCipher(keys);
            String message = vc.decrypt(encrypted);
            int valid = countWords(message, dictionary);
            if (valid > mostword){
                mostword = valid;
                decrypt = message;
                keylen = keys.length;
            }
        }
        System.out.println(keylen);
        return decrypt;
    }

    public char mostCommonCharIn(HashSet<String> dictionary){
        HashMap<Character, Integer>  countMap = new HashMap<Character, Integer>();
        for (String word: dictionary){
            for (char letter:word.toCharArray()){
                if (countMap.containsKey(letter)){
                    countMap.put(letter, countMap.get(letter)+1);
                } else{
                    countMap.put(letter,1);
                }
            }
        }
        int count = 0;
        char mostCommon = ' ';
        for (char letter: countMap.keySet()){
            int currCount = countMap.get(letter);
            if (currCount > count){
                count = currCount;
                mostCommon = letter;
            }
        }

        return mostCommon;
    }

    public String breakForAllLangs(String encrypted, HashMap<String, HashSet<String>> LangMap){
        String decrypted = "";
        int most = 0;
        String lan = "";
        for (String lang: LangMap.keySet()){
            HashSet<String> currDir = LangMap.get(lang);
            char mostCommon = mostCommonCharIn(currDir);
            System.out.println(lang);
            String currDecrypt = breakForLanguage(encrypted, currDir,mostCommon );
            int currCount = countWords(currDecrypt, currDir);
            if (currCount > most){
                most = currCount;
                decrypted = currDecrypt;
                lan = lang;
            }
        }
        System.out.println(lan);
        return decrypted;
    }


    
}
