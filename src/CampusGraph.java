import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/**
 * Models the university campus as an undirected graph using an adjacency list.
 * Owned by: Member 4 (Graph + Campus Route Management)
 *
 * Vertices are campus location names (e.g. "Library").
 * Edges are roads/paths connecting two locations. A LinkedHashMap is used so
 * locations display in the order they were added, which makes output easier
 * to follow during demonstration.
 *
 * Note: a separate CampusLocation class was intentionally not created.
 * Location names (Strings) are sufficient to satisfy the vertex/edge
 * requirements of the assignment, and keeping the graph itself simple
 * makes it easier for Member 4 to explain during the demo.
 */
/**
 * Author: N.M. Askan (Member 4)
 * Student ID: (his student ID)
 * Description: Campus graph (adjacency list) with BFS traversal
 */
public class CampusGraph {

    private final Map<String, List<String>> adjacencyList;

    public CampusGraph() {
        adjacencyList = new LinkedHashMap<>();
    }

    /** Adds a new vertex (location). Returns false if it already exists. */
    public boolean addLocation(String location) {
        if (adjacencyList.containsKey(location)) {
            return false;
        }
        adjacencyList.put(location, new LinkedList<>());
        return true;
    }

    /** Removes a vertex and cleans up any edges pointing to it. */
    public boolean removeLocation(String location) {
        if (!adjacencyList.containsKey(location)) {
            return false;
        }
        adjacencyList.remove(location);
        for (List<String> neighbours : adjacencyList.values()) {
            neighbours.remove(location);
        }
        return true;
    }

    public boolean hasLocation(String location) {
        return adjacencyList.containsKey(location);
    }

    /** Adds an undirected edge (road) between two existing locations. */
    public boolean addConnection(String from, String to) {
        if (!hasLocation(from) || !hasLocation(to)) {
            return false;
        }
        if (adjacencyList.get(from).contains(to)) {
            return false; // duplicate road
        }
        adjacencyList.get(from).add(to);
        adjacencyList.get(to).add(from);
        return true;
    }

    /** Removes the undirected edge between two locations. */
    public boolean removeConnection(String from, String to) {
        if (!hasLocation(from) || !hasLocation(to)) {
            return false;
        }
        boolean removedFrom = adjacencyList.get(from).remove(to);
        boolean removedTo = adjacencyList.get(to).remove(from);
        return removedFrom || removedTo;
    }

    public void displayConnections() {
        if (adjacencyList.isEmpty()) {
            System.out.println("No campus locations added yet.");
            return;
        }
        System.out.println("---- Campus Connections (Adjacency List) ----");
        for (Map.Entry<String, List<String>> entry : adjacencyList.entrySet()) {
            System.out.println(entry.getKey() + " -> " + entry.getValue());
        }
    }

    /**
     * Breadth-First Search traversal starting from the given location.
     * A queue drives BFS: visit a vertex, enqueue its unvisited neighbours,
     * then repeat until the queue is empty, guaranteeing locations are
     * visited in order of increasing distance (number of roads) from start.
     */
    public void bfsTraversal(String start) {
        if (!hasLocation(start)) {
            System.out.println("Starting location not found in the graph.");
            return;
        }
        Set<String> visited = new LinkedHashSet<>();
        Queue<String> queue = new LinkedList<>();

        visited.add(start);
        queue.add(start);

        System.out.println("---- BFS Traversal from " + start + " ----");
        StringBuilder order = new StringBuilder();
        while (!queue.isEmpty()) {
            String current = queue.poll();
            order.append(current).append(" ");
            for (String neighbour : adjacencyList.get(current)) {
                if (!visited.contains(neighbour)) {
                    visited.add(neighbour);
                    queue.add(neighbour);
                }
            }
        }
        System.out.println(order.toString().trim());
    }

    public boolean isEmpty() {
        return adjacencyList.isEmpty();
    }
}