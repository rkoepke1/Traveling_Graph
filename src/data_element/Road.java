package data_element;
/**
 * The class Road that can represent the edges of a Graph of Towns.
 * @author Ryan Koepke
 *
 */
public class Road implements Comparable<Road>{
	private Town source, destination;
	private int weight;
	private String name;
	/**
	 * Constructor to initialize a new Road.
	 * 
	 * @param source The input source Town.
	 * @param destination The input destination Town.
	 * @param weight The distance of the road in miles.
	 * @param name The name of the road.
	 */
	public Road(Town source, Town destination, int weight, String name) {
		this.source = source;
		this.destination =  destination;
		this.weight = weight;
		this.name = name;
	}
	/**
	 * A constructor that does not need a distance for input.
	 * 
	 * @param source The input source Town.
	 * @param destination The input destination Town.
	 * @param name The name of the road.
	 */
	public Road(Town source, Town destination, String name) {
		this.source = source;
		this.destination =  destination;
		this.name = name;
		this.weight = 1;
	}
	/**
	 * Check if the road contains an input town.
	 * 
	 * @param town Town that is input from graph.
	 * @return True if the road contains the town and false if not.
	 */
	public boolean contains(Town town) {
		if(source.equals(town) || destination.equals(town)) {
			return true;
		}
		return false;
	}
	
	/**
	 * Returns the source town of the current road.
	 * 
	 * @return The source Town.
	 */
	public Town getSource() {
		return source;
	}
	
	/**
	 * Returns the destination town of the current road.
	 * 
	 * @return The destination Town.
	 */
	public Town getDestination() {
		return destination;
	}
	/**
	 * Get the distance of the current road in miles.
	 * 
	 * @return The distance of the current road.
	 */
	public int getWeight() {
		return weight;
	}
	
	/**
	 * Get the name of the current road.
	 * 
	 * @return The name of the current road.
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Get the hashCode of the the Road based on name.
	 */
	public int hashCode() {
		return (source.getName() + destination.getName()).hashCode();
	}
	/**
	 * Compares the one road with current road based on name.
	 */
	public int compareTo(Road o) {
		return this.name.compareTo(o.getName());
	}
	
	/**
	 * Checks if one road is equal to another road based on the Towns it contains.
	 * 
	 * @param o Road object that is input.
	 * @return True if road equals current road and false if not.
	 */
	public boolean equals(Object o) {
		Road r = (Road) o;
		if((r.getSource().equals(this.source)) && (r.getDestination().equals(this.destination))
				|| ((r.getSource().equals(this.destination)) && r.getDestination().equals(this.source))){
			return true;
		}else {
			return false;
		}
	}
	
	/**
	 * Display the string to print as "town1" via "road" to "town 2".
	 */
	public String toString() {
		return source.getName() + " via " + this.name + " to " + destination.getName() + " " + weight + " mi";
	}
}
