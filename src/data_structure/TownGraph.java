package data_structure;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.PriorityQueue;
import java.util.Set;

import data_element.Road;
import data_element.Town;
import interfaces.GraphInterface;
/**
 * 
 * @author Ryan Koepke
 * 
 * The root interface in the graph hierarchy. A mathematical graph-theory graph
 * object G(V,E) contains a set V of vertices and a set
 * E of edges. Each edge e=(v1,v2) in E connects vertex v1 to vertex v2.
 *
 * Through generics, a graph can be typed to specific classes for vertices
 * V and edges E<T>. Such a graph can contain
 * vertices of type V and all sub-types and Edges of type
 * E and all sub-types.
 */
public class TownGraph implements GraphInterface<Town, Road>{
	private Set<Town> towns;
	private Set<Road> roads;
	private ArrayList<String> paths = new ArrayList<String>();

	 public TownGraph(){
		 this.towns = new HashSet<Town>();
		 this.roads = new HashSet<Road>();
	   }
	 /**
     * Returns an edge connecting source vertex to target vertex if such
     * vertices and such edge exist in this graph. Otherwise returns
     * null. If any of the specified vertices is null
     * returns null
     *
     * In undirected graphs, the returned edge may have its source and target
     * vertices in the opposite order.
     *
     * @param sourceVertex source vertex of the edge.
     * @param destinationVertex target vertex of the edge.
     *
     * @return an edge connecting source vertex to target vertex.
     */
	@Override
	public Road getEdge(Town sourceVertex, Town destinationVertex) {
		if (!(containsEdge(sourceVertex, destinationVertex)) || (sourceVertex == null || destinationVertex == null)) {
			return null;
		}else {
			Iterator<Road> itr = roads.iterator();
			while(itr.hasNext()) {
				Road r = itr.next();
				if((r.getSource().compareTo(sourceVertex) == 0) && (r.getDestination().compareTo(destinationVertex) == 0)) {
					return r;
				}
			}
		}
		return null;
	}
	
	/**
     * Creates a new edge in this graph, going from the source vertex to the
     * target vertex, and returns the created edge. 
     * 
     * The source and target vertices must already be contained in this
     * graph. If they are not found in graph IllegalArgumentException is
     * thrown.
     * @param sourceVertex source vertex of the edge.
     * @param destinationVertex target vertex of the edge.
     * @param weight weight of the edge
     * @param description description for edge
     *
     * @return The newly created edge if added to the graph, otherwise null.
     *
     * @throws IllegalArgumentException if source or target vertices are not
     * found in the graph.
     * @throws NullPointerException if any of the specified vertices is null.
     */
	@Override
	public Road addEdge(Town sourceVertex, Town destinationVertex, int weight, String description) {
		if(!(containsVertex(sourceVertex) && containsVertex(destinationVertex))){
			throw new IllegalArgumentException();
		}
		if(destinationVertex==null||sourceVertex==null){
			throw new NullPointerException();
		}
		if(!(containsEdge(sourceVertex, destinationVertex))){
			Road edgeTo = new Road (sourceVertex, destinationVertex, weight, description);
			Road edgeFrom = new Road (destinationVertex, sourceVertex, weight, description);
			roads.add(edgeTo);
			roads.add(edgeFrom);
			return edgeTo;
		}
		return null;
	}
	
	/**
     * Adds the specified vertex to this graph if not already present. More
     * formally, adds the specified vertex, v, to this graph if
     * this graph contains no vertex u such that
     * u.equals(v). If this graph already contains such vertex, the call
     * leaves this graph unchanged and returns false. In combination
     * with the restriction on constructors, this ensures that graphs never
     * contain duplicate vertices.
     *
     * @param v vertex to be added to this graph.
     *
     * @return true if this graph did not already contain the specified
     * vertex.
     *
     * @throws NullPointerException if the specified vertex is null.
     */
	@Override
	public boolean addVertex(Town v) {
		if(v == null) {
			throw new NullPointerException();
		}
		if(containsVertex(v)) {
			return false;
		}else {
			towns.add(v);
			return true;
		}
	}
	/**
     * Returns true if and only if this graph contains an edge going
     * from the source vertex to the target vertex. In undirected graphs the
     * same result is obtained when source and target are inverted. If any of
     * the specified vertices does not exist in the graph, or if is
     * null, returns false.
     *
     * @param sourceVertex source vertex of the edge.
     * @param destinationVertex target vertex of the edge.
     *
     * @return true if this graph contains the specified edge.
     */
	@Override
	public boolean containsEdge(Town sourceVertex, Town destinationVertex) {
		Iterator<Road> itr = roads.iterator();
		while(itr.hasNext()) {
			Road r = itr.next();
			if(sourceVertex == null || destinationVertex == null) {
				return false;
			}if((r.getSource().equals(sourceVertex)) && (r.getDestination().equals(destinationVertex))) {
				return true;	
			}
		}
		return false;
	}
	/**
     * Returns true if this graph contains the specified vertex. More
     * formally, returns true if and only if this graph contains a
     * vertex u such that u.equals(v). If the
     * specified vertex is null returns false.
     *
     * @param v vertex whose presence in this graph is to be tested.
     *
     * @return true if this graph contains the specified vertex.
     */
	@Override
	public boolean containsVertex(Town v) {
		if(v == null) {
			return false;
		}
		if(towns.contains(v)) {
			return true;
		}else {
			return false;
		}
	}
	
	/**
     * Returns a set of the edges contained in this graph. The set is backed by
     * the graph, so changes to the graph are reflected in the set. If the graph
     * is modified while an iteration over the set is in progress, the results
     * of the iteration are undefined.
     *
     *
     * @return a set of the edges contained in this graph.
     */
	@Override
	public Set<Road> edgeSet() {
		Set<Road> rds = new HashSet<Road>();
		Iterator<Road> itr = roads.iterator();
		HashMap<String, Road> hm = new HashMap<String, Road>();
		while(itr.hasNext()) {
			Road rd = itr.next();
			hm.put(rd.getName(), rd);
		}
		Set<String> keys = hm.keySet();
		Iterator<String> i = keys.iterator();
		while(i.hasNext()) {
			String key = i.next();
			Road entryRoad = hm.get(key);
			rds.add(entryRoad);
		}
		return rds;
	}
	
	/**
     * Returns a set of all edges touching the specified vertex (also
     * referred to as adjacent vertices). If no edges are
     * touching the specified vertex returns an empty set.
     *
     * @param vertex the vertex for which a set of touching edges is to be
     * returned.
     *
     * @return a set of all edges touching the specified vertex.
     *
     * @throws IllegalArgumentException if vertex is not found in the graph.
     * @throws NullPointerException if vertex is null.
     */
	@Override
	public Set<Road> edgesOf(Town vertex) {
		if(vertex == null) {
			throw new NullPointerException();
		}
		Set<Road> r = new HashSet<Road>();
		if(containsVertex(vertex)) {
			Iterator<Town> itr = towns.iterator();
			while(itr.hasNext()) {
				Town t = itr.next();
				if(containsEdge(vertex, t)) {
					r.add(getEdge(vertex, t));
				}
			}
		}else {
			throw new IllegalArgumentException();
		}
		return r;
	}
	/**
     * Removes an edge going from source vertex to target vertex, if such
     * vertices and such edge exist in this graph. 
     * 
     * If weight >- 1 it must be checked
     * If description != null, it must be checked 
     * 
     * Returns the edge if removed
     * or null otherwise.
     *
     * @param sourceVertex source vertex of the edge.
     * @param destinationVertex target vertex of the edge.
     * @param weight weight of the edge
     * @param description description of the edge
     *
     * @return The removed edge, or null if no edge removed.
     */
	@Override
	public Road removeEdge(Town sourceVertex, Town destinationVertex, int weight, String description) {
		Road r = null;
		if((weight > -1 && description != null) && containsEdge(sourceVertex, destinationVertex)) {
			r = new Road(sourceVertex, destinationVertex, weight, description);
			roads.remove(r);
		}
		return r;
	}
	/**
     * Removes the specified vertex from this graph including all its touching
     * edges if present. More formally, if the graph contains a vertex 
     * u such that u.equals(v), the call removes all edges
     * that touch u and then removes u itself. If no
     * such u is found, the call leaves the graph unchanged.
     * Returns true if the graph contained the specified vertex. (The
     * graph will not contain the specified vertex once the call returns).
     *
     * If the specified vertex is null returns false.
     *
     * @param v vertex to be removed from this graph, if present.
     *
     * @return true if the graph contained the specified vertex;
     * false otherwise.
     */
	@Override
	public boolean removeVertex(Town v) {
		if(v == null) {
			return false;
		}
		if(towns.contains(v)) {
			towns.remove(v);
			return true;
		}else {
			return false;
		}
	}
	
	/**
     * Returns a set of the vertices contained in this graph. The set is backed
     * by the graph, so changes to the graph are reflected in the set. If the
     * graph is modified while an iteration over the set is in progress, the
     * results of the iteration are undefined.
     *
     *
     * @return a set view of the vertices contained in this graph.
     */
	@Override
	public Set<Town> vertexSet() {
		Set<Town> townCopy = new HashSet<Town>();
		Iterator<Town> itr= towns.iterator();
		while(itr.hasNext()) {
			townCopy.add(itr.next());
		}
		return townCopy;
	}
	/**
     * Find the shortest path from the sourceVertex to the destinationVertex
     * call the dijkstraShortestPath with the sourceVertex
     * @param sourceVertex starting vertex
     * @param destinationVertex ending vertex
     * @return An arraylist of Strings that describe the path from sourceVertex
     * to destinationVertex
     */  
	
	@Override
	public ArrayList<String> shortestPath(Town sourceVertex, Town destinationVertex) {
		dijkstraShortestPath(sourceVertex);
		Town destination = null;
		Iterator<Town> itr = towns.iterator();
		if(!(containsVertex(sourceVertex) || containsVertex(destinationVertex))){
			return paths;
		}
		while(itr.hasNext()) {
			Town t = itr.next();
			if(t.equals(destinationVertex)) {
				destination = t;
			}
		}
		if(!(destination.equals(sourceVertex)) ){
			Road path = getEdge(destination.getPred(), destination);
			if(path != null) {
				paths.add(path.toString());
				shortestPath(sourceVertex, destination.getPred());
			}
		}else {
			if(paths.size() > 0) {
				Collections.reverse(paths);
			}
		}
		return paths;
	}
	
	/**
     * Dijkstra's Shortest Path Method.  Internal structures are built which
     * hold the ability to retrieve the path, shortest distance from the
     * sourceVertex to all the other vertices in the graph, etc.
     * @param sourceVertex the vertex to find shortest path from
     * 
     */
	@Override
	public void dijkstraShortestPath(Town sourceVertex) {
		sourceVertex.setDistance(0);
		PriorityQueue<Town> priorityQueue = new PriorityQueue<>();
		priorityQueue.add(sourceVertex);
		while(!priorityQueue.isEmpty() ){
			Town source = priorityQueue.poll();
			Set<Road> roadList = edgesOf(source);
			Iterator<Road> itr = roadList.iterator();
			while(itr.hasNext()) {
				Road r = itr.next();
				Town destination = r.getDestination();
				int distance = source.getDistance() + r.getWeight();
				if(distance < destination.getDistance()) {
					priorityQueue.remove(source);
					destination.setDistance(distance);
					destination.setPred(source);
					priorityQueue.add(destination);
				}
			}
		}
	}
}