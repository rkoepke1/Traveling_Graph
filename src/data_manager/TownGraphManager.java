package data_manager;
import data_element.Road;
import data_element.Town;
import data_structure.TownGraph;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

import interfaces.TownGraphManagerInterface;
/**
 * Graph manager that implments the TownGraph and used for the town graph and JavaFX driver.
 * @author Ryan Koepke
 *
 */
public class TownGraphManager implements TownGraphManagerInterface{
	
	private TownGraph graph = new TownGraph();
	/**
	 * Adds a road with 2 towns and a road name
	 * @param town1 name of town 1 (lastname, firstname)
	 * @param town2 name of town 2 (lastname, firstname)
	 * @param roadName name of road
	 * @return true if the road was added successfully
	 */
	@Override
	public boolean addRoad(String town1, String town2, int weight, String roadName) {
		addTown(town1);
		addTown(town2);
		this.graph.addEdge(getTown(town1), getTown(town2), weight, roadName);
		return this.graph.containsEdge(getTown(town1), getTown(town2));
	}
	
	/**
	 * Returns the name of the road that both towns are connected through
	 * @param town1 name of town 1 (lastname, firstname)
	 * @param town2 name of town 2 (lastname, firstname)
	 * @return name of road if town 1 and town2 are in the same road, returns null if not
	 */
	@Override
	public String getRoad(String town1, String town2) {
		Town t1 = getTown(town1);
		Town t2 = getTown(town2);
		if(graph.containsEdge(t1,t2)) {
			Road road = graph.getEdge(t1,  t2);
			return road.getName();
		}
		return null;
	}
	
	/**
	 * Adds a town to the graph
	 * @param v the town's name  (lastname, firstname)
	 * @return true if the town was successfully added, false if not
	 */
	@Override
	public boolean addTown(String v) {
		Town t = new Town(v);
		return this.graph.addVertex(t);
	}
	
	/**
	 * Gets a town with a given name
	 * @param name the town's name 
	 * @return the Town specified by the name, or null if town does not exist
	 */
	@Override
	public Town getTown(String name) {
		Town t = new Town(name);
		Set<Town> towns = graph.vertexSet();
		Iterator<Town> itr = towns.iterator();
		if(graph.containsVertex(t)) {
			while(itr.hasNext()) {
				Town i = itr.next();
				if(i.equals(t)) {
					return i;
				}
			}
		}
		return null;
	}
	
	/**
	 * Determines if a town is already in the graph
	 * @param v the town's name 
	 * @return true if the town is in the graph, false if not
	 */
	@Override
	public boolean containsTown(String v) {
		Town t = getTown(v);
		if(graph.containsVertex(t)) {
			return true;
		}
		return false;
	}
	
	/**
	 * Determines if a road is in the graph
	 * @param town1 name of town 1 (lastname, firstname)
	 * @param town2 name of town 2 (lastname, firstname)
	 * @return true if the road is in the graph, false if not
	 */
	@Override
	public boolean containsRoadConnection(String town1, String town2) {
		Town t1 = getTown(town1);
		Town t2 = getTown(town2);
		return this.graph.containsEdge(t1, t2);
	}
	
	/**
	 * Creates an arraylist of all road titles in sorted order by road name
	 * @return an arraylist of all road titles in sorted order by road name
	 */
	@Override
	public ArrayList<String> allRoads() {
		ArrayList<String> allR = new ArrayList<String>();
		Set<Road> rds = graph.edgeSet();
		Iterator<Road> itr= rds.iterator();
		while(itr.hasNext()) {
			Road r = itr.next();
			allR.add(r.getName());
		}
		Collections.sort(allR);
		return allR;
	}
	
	/**
	 * Deletes a road from the graph
	 * @param town1 name of town 1 (lastname, firstname)
	 * @param town2 name of town 2 (lastname, firstname)
	 * @param roadName the road name
	 * @return true if the road was successfully deleted, false if not
	 */
	@Override
	public boolean deleteRoadConnection(String town1, String town2, String road) {
		Town t1 = getTown(town1);
		Town t2 = getTown(town2);
		Road r = graph.getEdge(t1, t2);
		graph.removeEdge(r.getSource(), r.getDestination(), r.getWeight(), r.getName());
		return graph.containsEdge(t1, t2);
	}
	
	/**
	 * Deletes a town from the graph
	 * @param v name of town (lastname, firstname)
	 * @return true if the town was successfully deleted, false if not
	 */
	@Override
	public boolean deleteTown(String v) {
		Town t = getTown(v);
		return graph.removeVertex(t);
	}
	
	/**
	 * Creates an arraylist of all towns in alphabetical order (last name, first name)
	 * @return an arraylist of all towns in alphabetical order (last name, first name)
	 */
	@Override
	public ArrayList<String> allTowns() {
		ArrayList<String> allT = new ArrayList<String>();
		Set<Town> towns = graph.vertexSet();
		Iterator<Town> itr= towns.iterator();
		while(itr.hasNext()) {
			Town t = itr.next();
			allT.add(t.getName());
		}
		Collections.sort(allT);
		return allT;
	}
	
	/**
	 * Returns the shortest path from town 1 to town 2
	 * @param town1 name of town 1 (lastname, firstname)
	 * @param town2 name of town 2 (lastname, firstname)
	 * @return an Arraylist of roads connecting the two towns together, null if the
	 * towns have no path to connect them.
	 */
	@Override
	public ArrayList<String> getPath(String town1, String town2) {
		ArrayList<String> paths = new ArrayList<String>();
		if(containsTown(town1) && containsTown(town2)) {
			Town t1 = getTown(town1);
			Town t2 = getTown(town2);
			paths = graph.shortestPath(t1,  t2);
		}else {
			return paths;
		}
		return paths;
	}
	
	/**
	 * Populates a graph from a file input.
	 * 
	 * @param file File that is input into method.
	 * @throws FileNotFoundException
	 */
	public void populateTownGraph(File file) throws FileNotFoundException {
		Scanner infile = new Scanner(file);
		String st = "";
		while(infile.hasNextLine()) {
			String info = infile.nextLine();
			System.out.println(info);
			st += info + "\n";
		}
		infile.close();
		String[] infoArr = st.split("\n");
		for(int i = 0; i < infoArr.length; i ++) {
			String[] data = infoArr[i].split(";");
			addTown(data[1]);
			addTown(data[2]);
			String[] road = data[0].split(",");
			System.out.println(road[1]);
			addRoad(data[1], data[2], Integer.valueOf(road[1]), road[0]);
		}
	}

}
