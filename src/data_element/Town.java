package data_element;
/**
 * Represents a town as a node of a graph.
 * 
 * @author Ryan Koepke
 *
 */

public class Town implements Comparable<Town>{
	private String name;
	private int distance = 9999999;
	private Town pred;
	
	/**
	 * Constructor that takes in a name and initializes a town.
	 * @param name Name of the town.
	 */
	public Town (String name) {
		this.name = name;
	}
	/**
	 * Copy constructor.
	 * 
	 * @param town Town to be copied.
	 */
	public Town (Town town) {
		this.name = town.getName();
	}
	/**
	 * CompareTo method override.
	 * @param o The town that is input.
	 */
	@Override
	public int compareTo(Town o) {
		return o.getName().compareTo(this.name);
	}
	
	/**
	 * HashCode of the towns name.
	 */
	public int hashCode() {
		return name.hashCode();
	}
	
	/**
	 * Get the name of the town.
	 * @return The name of the town.
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Set the distance from the source Town.
	 * @param d Distance.
	 */
	public void setDistance(int d) {
		this.distance = d;
	}
	
	/**
	 * Get the distance from the source Town.
	 * 
	 * @return The distance value.
	 */
	public int getDistance() {
		return this.distance;
	}
	
	/**
	 * Set the predecessor town that has the shortest path.
	 * @param t
	 */
	public void setPred(Town t) {
		this.pred = t;
	}
	
	/**
	 * Get the predecessor town.
	 * @return
	 */
	public Town getPred() {
		return this.pred;	
	}
	
	/**
	 * Check if the input town equals current town.
	 * @param o Object that is input.
	 */
	public boolean equals(Object o) {
		Town town = (Town) o;
        return this.hashCode() == town.hashCode();
	}
}
