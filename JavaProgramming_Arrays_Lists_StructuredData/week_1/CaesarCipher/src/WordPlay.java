import edu.duke.FileResource;

public class WordPlay {
    public boolean isVowel(char ch){
        String vowels = "aeiou";
        if (vowels.indexOf(Character.toLowerCase(ch)) == -1){
            return false;
        }
        return true;
    }

    public void testIsVowel(char ch){
        System.out.println(ch+" vowel status: "+ isVowel(ch));
    }

    public String replaceVowels(String phrase,char ch){
        StringBuilder changed = new StringBuilder(phrase);
        for (int i = 0; i < changed.length(); i++){
            char currChar = changed.charAt(i);
            if (isVowel(currChar)){
                changed.setCharAt(i,ch);
            }
        }
        return changed.toString();
    }

    public void testReplaceVowels(){
        FileResource file = new FileResource();
        String phrase = file.asString();
        System.out.println(replaceVowels(phrase,'-'));
    }

    public String emphasize(String phrase,char ch){
        StringBuilder changed = new StringBuilder(phrase);
        for (int i = 0; i < changed.length(); i++){
            char currCh = changed.charAt(i);
            if (Character.toLowerCase(currCh) == Character.toLowerCase(ch)) {
                if (i % 2 == 0) {
                    changed.setCharAt(i, '*');
                } else {
                    changed.setCharAt(i, '+');
                }
            }
        }
        return changed.toString();
    }

    public void testEmphasize(){
        FileResource file = new FileResource();
        String phrase = file.asString();
        System.out.println(emphasize(phrase,'e'));
    }


}
