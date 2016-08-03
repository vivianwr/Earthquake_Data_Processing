import java.util.*;
import edu.duke.*;

public class EarthQuakeClient {
    public EarthQuakeClient() {
        // TODO Auto-generated constructor stub
    }

    public ArrayList<QuakeEntry> filterByMagnitude(ArrayList<QuakeEntry> quakeData,
    double magMin) {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        // TODO
        for (QuakeEntry qe : quakeData) {
            if (qe.getMagnitude() > magMin) answer.add(qe);
        }

        return answer;
    }
    
    public ArrayList<QuakeEntry> filterByDepth(ArrayList<QuakeEntry> quakeData, 
    double minDepth, double maxDepth) {
        ArrayList<QuakeEntry> answer = new ArrayList<>();
        for (QuakeEntry qe : quakeData) {
            double depth = qe.getDepth();
            if (depth > minDepth && depth < maxDepth) answer.add(qe);
        }
        return answer;
    }
    
    public ArrayList<QuakeEntry> filterByPhrase(ArrayList<QuakeEntry> quakeData, 
    String where, String phrase) {
        ArrayList<QuakeEntry> answer = new ArrayList<>();
        for (QuakeEntry qe : quakeData) {
            String s = qe.getInfo().toString();
            if (s.length() >= phrase.length() && where == "start") {
                if (s.substring(0, phrase.length()) == phrase)
                  answer.add(qe);
            }
            if (s.length() >= phrase.length() && where == "end") {
                if (s.substring(s.length() - phrase.length(), s.length()) == phrase)
                  answer.add(qe);
            }
            if (s.length() >= phrase.length() && where == "any") {
                if (s.contains(phrase)) answer.add(qe);
            }
        }
        return answer;
    }
    
    public void quakesByPhrase() {
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedatasmall.atom";
        ArrayList<QuakeEntry> list = parser.read(source);
        ArrayList<QuakeEntry> rlist = filterByPhrase(list, "end", "California");
        for (QuakeEntry qe : rlist) {
            
        }
    }
    
    public void quakesOfDepth() {
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedatasmall.atom";
        ArrayList<QuakeEntry> list = parser.read(source);
        ArrayList<QuakeEntry> rlist = filterByDepth(list, -10000.0, -5000.0);
        System.out.println("read data for " + list.size() + " quakes \n");
        System.out.println("Find quakes with depth between -10000.0 and -5000.0 \n");
        int num = 0;
        for (QuakeEntry qe : rlist) {
            System.out.println("("+qe.getLocation().getLatitude()+"), "+qe.getLocation().getLongitude()
            +", mag = " + qe.getMagnitude()+", depth = "+qe.getDepth()+", title = "+
            qe.getInfo());
            num++;
        }
        System.out.println("Found " + num + " quakes that match that criteria");
    }

    public ArrayList<QuakeEntry> filterByDistanceFrom(ArrayList<QuakeEntry> quakeData,
    double distMax,
    Location from) {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        // TODO
        for (QuakeEntry qe : quakeData) {
            if (qe.getLocation().distanceTo(from) < distMax) {
                answer.add(qe);
            }
        }
        return answer;
    }

    public void dumpCSV(ArrayList<QuakeEntry> list){
        System.out.println("Latitude,Longitude,Magnitude,Info");
        for(QuakeEntry qe : list){
            System.out.printf("%4.2f,%4.2f,%4.2f,%s\n",
                qe.getLocation().getLatitude(),
                qe.getLocation().getLongitude(),
                qe.getMagnitude(),
                qe.getInfo());
        }

    }

    public void bigQuakes() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedatasmall.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size()+" quakes");
        int num = 0;
        for (QuakeEntry qe : list) {
            if (qe.getMagnitude() > 5.0) {
                System.out.println("(" + qe.getLocation().getLatitude() + ", " + qe.getLocation().getLongitude() + "), mag = " + qe.getMagnitude() + ", depth = " 
                + qe.getDepth() + ", title = " + qe.getInfo());
                num++;
            }
        }
        System.out.println("Found " + num + " quakes that match that criteria");
    }

    public void closeToMe(){
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedatasmall.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size()+" quakes");

        // This location is Durham, NC
        //Location city = new Location(35.988, -78.907);

        // This location is Bridgeport, CA
         Location city =  new Location(38.17, -118.82);

        // TODO
        ArrayList<QuakeEntry> rlist = filterByDistanceFrom(list, 1000.0, city);
        int num = 0;
        for (QuakeEntry qe : rlist) {
            num++;
            System.out.println(qe.getLocation().getSpeed() + "/t" + qe.getLocation().distanceTo(city) + "km " + qe.getInfo());
        }
        System.out.println("Found " + num + " quakes that match that criteria");
    }

    public void createCSV(){
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedatasmall.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        dumpCSV(list);
        System.out.println("# quakes read: " + list.size());
        for (QuakeEntry qe : list) {
            System.out.println(qe);
        }
    }
    
}
