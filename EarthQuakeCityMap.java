package module3;

//Java utilities libraries
import java.util.ArrayList;
//import java.util.Collections;
//import java.util.Comparator;
import java.util.List;


//Processing library
import processing.core.PApplet;

import processing.core.PShape;
//Unfolding libraries
import de.fhpotsdam.unfolding.UnfoldingMap;
import de.fhpotsdam.unfolding.marker.Marker;
import de.fhpotsdam.unfolding.data.PointFeature;
import de.fhpotsdam.unfolding.marker.SimplePointMarker;
import de.fhpotsdam.unfolding.providers.Google;
import de.fhpotsdam.unfolding.providers.MBTilesMapProvider;
import de.fhpotsdam.unfolding.utils.MapUtils;

//Parsing library
import parsing.ParseFeed;

/** EarthquakeCityMap
 * An application with an interactive map displaying earthquake data.
 * @author Vivian
 * Date: 08/05/2016
 * */
public class EarthquakeCityMap extends PApplet {

	// You can ignore this.  It's to keep eclipse from generating a warning.
	private static final long serialVersionUID = 1L;

	// IF YOU ARE WORKING OFFLINE, change the value of this variable to true
	private static final boolean offline = false;
	
	// Less than this threshold is a light earthquake
	public static final float THRESHOLD_MODERATE = 5;
	// Less than this threshold is a minor earthquake
	public static final float THRESHOLD_LIGHT = 4;

	/** This is where to find the local tiles, for working without an Internet connection */
	public static String mbTilesString = "blankLight-1-3.mbtiles";
	
	// The map
	private UnfoldingMap map;
	
	//feed with magnitude 2.5+ Earthquakes
	private String earthquakesURL = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/2.5_week.atom";

	
	public void setup() {
		size(950, 600, OPENGL);

		if (offline) {
		    map = new UnfoldingMap(this, 200, 50, 700, 500, new MBTilesMapProvider(mbTilesString));
		    earthquakesURL = "2.5_week.atom"; 	// Same feed, saved Aug 7, 2015, for working offline
		}
		else {
			map = new UnfoldingMap(this, 200, 50, 700, 500, new Google.GoogleMapProvider());
			// IF YOU WANT TO TEST WITH A LOCAL FILE, uncomment the next line
			//earthquakesURL = "2.5_week.atom";
		}
		
	    map.zoomToLevel(2);
	    MapUtils.createDefaultEventDispatcher(this, map);	
			
	    // The List you will populate with new SimplePointMarkers
	    List<Marker> markers = new ArrayList<Marker>();
	    
	    //Use provided parser to collect properties for each earthquake
	    //PointFeatures have a getLocation method
	    List<PointFeature> earthquakes = ParseFeed.parseEarthquake(this, earthquakesURL);
	    
	    // These print statements show you (1) all of the relevant properties 
	    // in the features, and (2) how to get one property and use it
	    if (earthquakes.size() > 0) {
	    	PointFeature f = earthquakes.get(0);
	    	System.out.println(f.getProperties());
	    	Object magObj = f.getProperty("magnitude");
	    	float mag = Float.parseFloat(magObj.toString());
	    	// PointFeatures also have a getLocation method
	    }
	    
	    // Here is an example of how to use Processing's color method to generate 
	    // an int that represents the color yellow.  
	    int yellow = color(255, 255, 0); //4.0-4.9
	    int blue = color(0,0,255); //<4.0
	    int red = color(255, 0, 0); //>=5.0
	    for (PointFeature eq : earthquakes) {
	    	Object magO = eq.getProperty("magnitude");
	    	float mag_O = Float.parseFloat(magO.toString());
	    	if ( mag_O < 4.0) {
	    		
	    		SimplePointMarker m = new SimplePointMarker(eq.getLocation(), eq.getProperties());
	    		m.setColor(blue);
	    		markers.add(m);
	    	}
	    	else if (mag_O >= 5.0) {
	    		SimplePointMarker m = new SimplePointMarker(eq.getLocation(), eq.getProperties());
	    		m.setColor(red);
	    		markers.add(m);
	    	}
	    	else {
	    		SimplePointMarker m = new SimplePointMarker(eq.getLocation(), eq.getProperties());
	    		m.setColor(yellow);
	    		markers.add(m);
	    	}
	    	//SimplePointMarker m = new SimplePointMarker(eq.getLocation(), eq.getProperties());
	    	//markers.add(new SimplePointMarker(eq.getLocation(), eq.getProperties()));
	    	//m.setColor(yellow);
	    	//markers.add(m);
	    }
	    //TODO: Add code here as appropriate
	    
	    
	    map.addMarkers(markers);
	    
	    
	}
		
	// A suggested helper method that takes in an earthquake feature and 
	// returns a SimplePointMarker for that earthquake
	// TODO: Implement this method and call it from setUp, if it helps
	private SimplePointMarker createMarker(PointFeature feature)
	{
		// finish implementing and use this method, if it helps.
		return new SimplePointMarker(feature.getLocation());
	}
	
	public void draw() {
	    background(10);
	    map.draw();
	    addKey();
	}


	// helper method to draw key in GUI
	// TODO: Implement this method to draw the key
	private void addKey() 
	{	
		// Remember you can use Processing's graphics methods here
	
		fill(230, 230, 200);
    	quad(20, 50, 180, 50, 180, 300, 20, 300);
		String s = "Earthquake Key";
		fill(0, 102, 153);
		text(s, 50, 80, 150, 80);
		String s1 = "5.0+ Magnitude";
		text(s1, 70, 145);
		String s2 = "4.0+ Magnitude";
		text(s2, 70, 205);
		String s3 = "Below 4.0";
		text(s3, 70, 265);
		fill(255, 0, 0);
		ellipse(50, 140, 15, 15);
		fill(255, 255, 0);
		ellipse(50, 200, 10, 10);
		fill(0, 0, 255);
		ellipse(50, 260, 5, 5);
		
	}
}
