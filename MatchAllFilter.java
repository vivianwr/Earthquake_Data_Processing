
/**
 * Write a description of MatchAllFilter here.
 * 
 * @author Vivian
 * @version 1.0
 */
import java.util.*;
public class MatchAllFilter implements Filter {
    private ArrayList<Filter> filter;
    
    public MatchAllFilter() {
        filter = new ArrayList<Filter>();
    }
    
    public void addFilter(Filter f) {
        filter.add(f);
    }
    
    public boolean satisfies(QuakeEntry qe) {
        for (Filter f : filter) {
            if (f.satisfies(qe)) continue;
            else return false;
        }
        return true;
    }

}
