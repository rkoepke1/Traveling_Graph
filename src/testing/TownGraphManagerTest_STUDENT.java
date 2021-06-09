package testing;



import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import data_manager.TownGraphManager;
import interfaces.TownGraphManagerInterface;


public class TownGraphManagerTest_STUDENT {
	private TownGraphManagerInterface graph;
	private String[] town;
	  
	@Before
	public void setUp() throws Exception {
		  graph = new TownGraphManager();
		  town = new String[12];
		  
		  for (int i = 1; i < 7; i++) {
			  town[i] = "Town_" + i;
			  graph.addTown(town[i]);
		  }
		  
		  graph.addRoad("Manitowoc", "Sheboygan", 15, "I-43");
		  graph.addRoad("Reedsville", "Appleton", 17, "WI-57");
		  graph.addRoad("Madison", "Two Rivers", 19, "Spruce dr");
		  graph.addRoad("Fondulac", "Shoto", 14, "I-26");

		 
	}

	@After
	public void tearDown() throws Exception {
		graph = null;
	}

	@Test
	public void testAddRoad() {
		ArrayList<String> roads = graph.allRoads();
		assertEquals("I-26", roads.get(0));
		assertEquals("I-43", roads.get(1));
		assertEquals("Spruce dr", roads.get(2));
		assertEquals("WI-57", roads.get(3));
		graph.addRoad("Reedsville", "Appleton", 1,"test");
		roads = graph.allRoads();
		assertEquals("I-26", roads.get(0));
		assertEquals("I-43", roads.get(1));
		assertEquals("Spruce dr", roads.get(2));
		assertEquals("WI-57", roads.get(3));

		
	}

	@Test
	public void testGetRoad() {
		assertEquals("I-43", graph.getRoad("Manitowoc", "Sheboygan"));
		assertEquals("WI-57", graph.getRoad("Reedsville", "Appleton"));
	}

	@Test
	public void testAddTown() {
		assertEquals(false, graph.containsTown("Goerge"));
		graph.addTown("Goerge");
		assertEquals(true, graph.containsTown("Goerge"));
	}
	
	@Test
	public void testDisjointGraph() {
		assertEquals(false, graph.containsTown("George"));
		graph.addTown("George");
		ArrayList<String> path = graph.getPath(town[1],"George");
		assertFalse(path.size() > 0);
	}

	@Test
	public void testContainsTown() {
		assertEquals(true, graph.containsTown("Appleton"));
		assertEquals(false, graph.containsTown("Goerge"));
	}

	@Test
	public void testContainsRoadConnection() {
		assertEquals(true, graph.containsRoadConnection("Manitowoc", "Sheboygan"));
		assertEquals(false, graph.containsRoadConnection("Goerge", "Appleton"));
	}

	@Test
	public void testAllRoads() {
		ArrayList<String> roads = graph.allRoads();
		assertEquals("I-26", roads.get(0));
		assertEquals("I-43", roads.get(1));
		assertEquals("Spruce dr", roads.get(2));
		assertEquals("WI-57", roads.get(3));
		
	}

	@Test
	public void testDeleteRoadConnection() {
		assertEquals(true, graph.containsRoadConnection("Manitowoc", "Sheboygan"));
		graph.deleteRoadConnection("Manitowoc", "Sheboygan", "I-43");
		assertEquals(false, graph.containsRoadConnection("Manitowoc", "Sheboygan"));
	}

	@Test
	public void testDeleteTown() {
		assertEquals(true, graph.containsTown("Manitowoc"));
		graph.deleteTown("Manitowoc");
		assertEquals(false, graph.containsTown("Manitowoc"));
	}
	
	@Test
	public void testAllTowns() {
		ArrayList<String> roads = graph.allTowns();
		assertEquals("Appleton", roads.get(0));
		assertEquals("Fondulac", roads.get(1));
		assertEquals("Madison", roads.get(2));
		assertEquals("Sheboygan", roads.get(5));
		assertEquals("Town_1", roads.get(7));

	}

	@Test
	public void testGetPath() {
		ArrayList<String> path = graph.getPath("Manitowoc", "Sheboygan");
		  assertNotNull(path);
		  assertTrue(path.size() > 0);
		  assertEquals("Manitowoc via I-43 to Sheboygan 15 mi",path.get(0).trim());

	}


}
