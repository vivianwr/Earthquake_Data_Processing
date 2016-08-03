
/**
 * Write a description of PhraseFilter here.
 * 
 * @author Vivian
 * @version 1.0
 */
public class PhraseFilter implements Filter {

    private String requestType;
    private String phraseTitle;
    
    public PhraseFilter (String type, String phrase) {
        requestType = type;
        phraseTitle = phrase;
    }
    
    public boolean satisfies (QuakeEntry qe) {
        String title = qe.getInfo();
        int lp = phraseTitle.length();
        int lt = title.length();
        if (requestType == "start" && lp <= lt) {
            return title.substring(0, lp) == phraseTitle;
        }
        if (requestType == "end" && lp <= lt) {
            return title.substring(lt - lp, lt) == phraseTitle;
        }
        if (requestType == "any" && lp <= lt) {
            return title.contains(phraseTitle);
        }
        return false;
    }
}
