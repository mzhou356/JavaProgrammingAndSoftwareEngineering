public class StringOccurences {
    public int howMany(String a,String b) {
        int currInd = 0;
        int total = 0;
        while (true) {
            int ind = b.indexOf(a,currInd);
            if (ind == -1){
                break;
            }
            total += 1;
            currInd = ind+a.length();
        }
        return total;
    }

    public void testHowMany(){
        String a = "ab";
        String b = "abcccababab";
        System.out.println(howMany(a,b));
        a = "aaaa";
        b = "aaaaaaaaaaaaccaaaccc";
        System.out.println(howMany(a,b));
        a="GAA";
        b = "ATGAACGAATTGAATC";
        System.out.println(howMany(a,b));
        a="AA";
        b = "ATAAAA";
        System.out.println(howMany(a,b));
    }

    public void findAbc(String input) {
        int index = input.indexOf("abc");

        while (true) {
            System.out.println(index);
            if (index == -1) {
                break;
            }
            String found = input.substring(index+1, index+4);
            System.out.println(found);
            index = input.indexOf("abc", index+3);
        }
    }


}
