public class PhraseFilter implements Filter {
    private String where; // start, end any
    private String phrase;

    public PhraseFilter(String type, String pattern){
        where = type;
        phrase = pattern;
    }

    public boolean satisfies(QuakeEntry qe){
        String info = qe.getInfo();
        if (where.equalsIgnoreCase("start")){
            return info.startsWith(phrase);
        } else if (where.equalsIgnoreCase("end")){
            return info.endsWith(phrase);
        } else if (where.equalsIgnoreCase("any")) {
            return info.matches(".*"+phrase+".*");
        }
        return false;
    }

    public String getName(){
        return "phrase filter";
    }
}
