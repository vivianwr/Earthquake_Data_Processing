# Earthquake_Data_Processing
Java - Analyse and filter live earthquake data to find useful information

     Visualize earthquake data.
1. Plot recent earthquakes on a map to highlight potential disaster zones.
2. read earthquake data from a live RSS feed (an online source of frequently updated data).
3. Plot markers in the locations where earthquakes of magnitude 4.0-, 4.0 - 4.9, 5.0+ have occurred in the last week.
4. Organize our data into features and markers. A feature is a class of objects which stores one or more locations, its type, and any additional data properties associated with it. In our case, we will need a List of features which store the location of each earthquake in the past week, and additional properties associated with it (magnitude, age, elevation, title).
5. A marker is a class of objects that give us a visual representation of a feature, which at the simplest level is a dot at the corresponding latitude. A marker does not, however, have to be associated with a feature as all we need is a location to draw it.


     Set Earthquake Markers
* Organize a complex application by using several different classes.
* Build a class hierarchy to reflect the different kinds of markers for different categories of earthquakes.
* Write code to override methods in base classes.
* Write an abstract class that implements some methods and leaves others abstract.
* Use PGraphics methods to draw shapes.
