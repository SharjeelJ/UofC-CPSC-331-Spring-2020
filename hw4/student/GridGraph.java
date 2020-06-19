package hw4.student;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Scanner;

/**
 * Class to represent a Weighted Grid Graph. Methods are
 * provided to build a graph from a file and to find
 * shortest paths.
 */
public class GridGraph implements GridGraphInterface
{
    // Creates an ArrayList that will contain all the vertices of the graph (alongside the vertex's value, distance to start vertex and predecessor vertex for Dijkstra's Algorithm)
    private ArrayList<Vertex> vertices = new ArrayList();

    // Creates an ArrayList that will contain all the edges of the graph (will contain the origin and destination vertices alongside the weight of the edge)
    private ArrayList<Edge> edges = new ArrayList();

    /**
     * Default constructor
     */
    public GridGraph()
    {
    }

    /**
     * Builds a grid graph from a specified file. It is assumed
     * that the input file is formatted correctly.
     *
     * @param filename
     */
    public void buildGraph(String filename) throws FileNotFoundException
    {
        // Creates a new file object with the provided filepath
        File file = new File(filename);

        // Creates a scanner object that will iterate through each line of the file
        Scanner fileReader = new Scanner(file);

        // Stores the n value that defines the graph's dimensions (grid of n * n vertices)
        int gridDimensionsN = fileReader.nextInt();

        // Iterates through all the lines in the provided file and populates the graph data ArrayLists (vertices & edges)
        while (fileReader.hasNextLine() && fileReader.hasNextInt())
        {
            // Creates a new origin vertex for the first integer value on the current line
            Vertex originVertex = new Vertex(fileReader.nextInt());

            // Creates a new destination vertex for the second integer value on the current line
            Vertex destinationVertex = new Vertex(fileReader.nextInt());

            // Stores the weight of the edge between the origin and destination vertex (third integer value on the current line)
            int edgeWeight = fileReader.nextInt();

            // Checks to see if the current origin vertex has previously been added to the vertices ArrayList and adds it if it is missing
            if (!vertices.contains(originVertex)) vertices.add(originVertex);

            // Checks to see if the current destination vertex has previously been added to the vertices ArrayList and adds it if it is missing
            if (!vertices.contains(destinationVertex)) vertices.add(destinationVertex);

            // Grabs the updated origin vertex from the vertices ArrayList (this is performed to prevent creating a new vertex rather than linking to an existing one)
            Vertex vertex1 = getVertex(originVertex.value);

            // Grabs the updated destination vertex from the vertices ArrayList (this is performed to prevent creating a new vertex rather than linking to an existing one)
            Vertex vertex2 = getVertex(destinationVertex.value);

            // Adds a new edge to the edges ArrayList consisting of the origin and destination vertex alongside the weight of the edge
            edges.add(new Edge(vertex1, vertex2, edgeWeight));
        }
    }

    /**
     * Finds the shortest path between a source vertex and a target vertex
     * using Dijkstra's algorithm.
     *
     * @param s        Source vertex (one based index)
     * @param t        Target vertex (one based index)
     * @param weighted Whether edge weights should be used or not.
     * @return A String encoding the shortest path. Vertices are
     * separated by whitespace.
     */
    public String findShortestPath(int s, int t, boolean weighted)
    {
        // Creates a string that will contain the shortest path from the source vertex to the destination (target) vertex
        String shortestPathString = "";

        // Grabs the corresponding vertex tied to the passed in source value
        Vertex startVertex = getVertex(s);

        // Grabs the corresponding vertex tied to the passed in destination value
        Vertex endVertex = getVertex(t);

        // Creates an ArrayList that will store vertices in ascending order based on distance from the start vertex
        ArrayList<Vertex> ascendingDistancesFromStartVertex = new ArrayList();

        // Creates a priority queue based on the distance to source vertex value tied to each vertex
        PriorityQueue<Vertex> priorityQueue = new PriorityQueue();

        // Loops through all the vertices in the graph and adds them to the priority queue
        for (Vertex currentVertex : vertices)
        {
            // Adds the source vertex with its distance being 0 (this will set its priority to 0 and make it the head of the priority queue)
            if (currentVertex == startVertex) currentVertex.distance = 0;

            // Adds the current vertex in the vertices ArrayList to the priority queue
            priorityQueue.add(currentVertex);
        }

        // Loops through through the entire priority queue to determine the shortest path from each vertex to the start vertex
        while (!priorityQueue.isEmpty())
        {
            // Grabs the current head of the priority queue (vertex with the smallest distance value but if there is a a tie, then the smallest vertex value)
            Vertex minimalDistanceVertex = priorityQueue.poll();

            // Adds the current vertex to the ArrayList keeping track of vertices closest to the source vertex
            ascendingDistancesFromStartVertex.add(minimalDistanceVertex);

            // Loops through all the edges in the graph (done to grab the current vertex's adjacent vertices)
            for (Edge currentEdge : edges)
            {
                // Checks to see if the current edge being passed has the current vertex as its origin vertex (this means that there is an adjacent vertex being the destination vertex of the edge)
                if (minimalDistanceVertex == currentEdge.originVertex)
                {
                    // If we are dealing with a weighted graph, will use the actual edge weight values otherwise use a value of 1 for every edge
                    if (weighted)
                    {
                        // Checks to see if the adjacent vertex's distance is larger than the current vertex's distance + the weight of the edge (meaning a shorter path exists)
                        if (currentEdge.destinationVertex.distance > minimalDistanceVertex.distance + currentEdge.edgeWeight)
                        {
                            // Updates the adjacent vertex's distance value to be the newly calculated shorter distance via the edge through the current vertex
                            currentEdge.destinationVertex.distance = minimalDistanceVertex.distance + currentEdge.edgeWeight;

                            // Updates the adjacent vertex to now reflect the path to the current vertex as being shorter (makes the current vertex its predecessor, meaning its the adjacent vertex with smallest distance to it)
                            currentEdge.destinationVertex.predecessor = minimalDistanceVertex;

                            // Removes the modified adjacent vertex from the priority queue (this is necessary to update its priority in the queue)
                            priorityQueue.remove(currentEdge.destinationVertex);

                            // Adds the modified adjacent vertex back to the priority queue (this will determine its new priority and insert it appropriately)
                            priorityQueue.add(currentEdge.destinationVertex);
                        }
                    } else
                    {
                        // Checks to see if the adjacent vertex's distance is larger than the current vertex's distance + the weight of the edge (meaning a shorter path exists)
                        if (currentEdge.destinationVertex.distance > minimalDistanceVertex.distance + 1)
                        {
                            // Updates the adjacent vertex's distance value to be the newly calculated shorter distance via the edge through the current vertex
                            currentEdge.destinationVertex.distance = minimalDistanceVertex.distance + 1;

                            // Updates the adjacent vertex to now reflect the path to the current vertex as being shorter (makes the current vertex its predecessor, meaning its the adjacent vertex with smallest distance to it)
                            currentEdge.destinationVertex.predecessor = minimalDistanceVertex;

                            // Removes the modified adjacent vertex from the priority queue (this is necessary to update its priority in the queue)
                            priorityQueue.remove(currentEdge.destinationVertex);

                            // Adds the modified adjacent vertex back to the priority queue (this will determine its new priority and insert it appropriately)
                            priorityQueue.add(currentEdge.destinationVertex);
                        }
                    }
                }
            }
        }

        // Stores the predecessor vertex to the current vertex (will be used to generate the shortest path between the start and end vertices)
        Vertex predecessorVertex = endVertex;

        // Creates a boolean to determine if a valid path between the start and end vertices exists
        boolean shortestPathExists = false;

        // Loops through the predecessors of the end vertex (traversing up the vertex links) to get the shortest path to the start vertex and stops when either all predecessor links have been exhausted
        while (predecessorVertex != null)
        {
            // Adds the current predecessor vertex's value to the string
            shortestPathString = predecessorVertex.value + " " + shortestPathString;

            // Sets the predecessor vertex to be the current predecessor vertex's predecessor (moves up a vertex link)
            predecessorVertex = predecessorVertex.predecessor;

            // Checks to see if the start vertex has been encountered (will determine if there is a shortest path between the start and end vertices or not)
            if (predecessorVertex == startVertex) shortestPathExists = true;
        }

        // If there was a valid shortest distance path between the start and end vertices, returns it otherwise returns a blank string
        if (shortestPathExists) return shortestPathString.trim();
        else return "";
    }

    /**
     * Returns the vertex corresponding to the provided value (key) from the graph
     *
     * @param value Value (key) to grab the corresponding node for from the graph
     * @return Node tied to the passed in value (key)
     */
    private Vertex getVertex(int value)
    {
        // Loops through all the vertices in the graph
        for (Vertex currentVertex : vertices)
            // If the current vertex has the same value (key) as the provided one then returns it
            if (currentVertex.value == value) return currentVertex;
        // Returns null if no such vertex corresponding to the provided value (key) exists
        return null;
    }

    /**
     * Class to represent a custom Vertex datatype. Vertex will have data tied to it that correlates to what is necessary for Dijkstra's shortest path algorithm
     */
    private class Vertex implements Comparable<Vertex>
    {
        // Stores the value (key) of the vertex as an integer
        public int value;

        // Stores the distance of the vertex to the start vertex for Dijkstra's shortest path algorithm as an integer where the default value is the max Integer value possible divided by 2 (as incrementing the max value would cause unintended side effects)
        public int distance = Integer.MAX_VALUE / 2;

        // Stores the vertex that will be its predecessor for Dijkstra's shortest path algorithm (will serve as a link) where the default value is null (meaning node is isolated or is the start vertex)
        public Vertex predecessor;

        /**
         * Constructor to create a Vertex object used to create a weighted or unweighted graph
         *
         * @param value Value (key) to assign to the vertex
         */
        public Vertex(int value)
        {
            // Stores the passed in value
            this.value = value;
        }

        /**
         * Overrides the default equals method to instead check whether or not the current vertex's value equals that of the passed in vertex. This is important as it allows the Vertex ArrayList to determine if a vertex tied to the key being parsed from the input file exists already
         *
         * @param vertex Vertex to check against and see if it has the same value as the current vertex
         * @return Boolean value corresponding to whether or not the current vertex's value equals the passed in vertex's value
         */
        @Override
        public boolean equals(Object vertex)
        {
            // Returns the result of whether the value of the current vertex is equal to the passed in vertex
            return this.value == ((Vertex) vertex).value;
        }


        /**
         * Overrides the default compareTo method to instead check the distance between the current and passed in vertex while relying on the values of the two vertices if the distances were equal. This is important as it allows the Vertex PriorityQueue to appropriate assign priority to vertices
         *
         * @param vertex Vertex to check against and see if the current vertex has a larger or shorter distance while relying on the value of the two vertices if the distances were equal
         * @return Integer value corresponding to whether or not the current vertex is "larger" (so lower priority) / "smaller" (so higher priority) / equal (triggers a check against the values of the two vertices to get a definite result) than the passed in vertex
         */
        @Override
        public int compareTo(Vertex vertex)
        {
            // Checks to see if the current vertex has a smaller distance value than the passed in vertex and returns -1 (will define a higher priority in the priority queue)
            if (this.distance < vertex.distance) return -1;
                // Checks to see if the current vertex has a larger distance value than the passed in vertex and returns 1 (will define a lower priority in the priority queue)
            else if (this.distance > vertex.distance) return 1;
                // Check performed if the distance values of both vertices are equal
            else
            {
                // Checks to see if the current vertex has a larger value than the passed in vertex and returns 1 if it does (will define a lower priority in the priority queue) otherwise -1 if it does not (will define a higher priority in the priority queue)
                return Integer.compare(this.value, vertex.value);
            }
        }
    }

    /**
     * Class to represent a custom Edge datatype. Edge will have data tied to it that correlates to what is necessary for building a graph
     */
    private class Edge
    {
        // Stores the origin vertex (will serve as a link)
        public Vertex originVertex;

        // Stores the destination vertex (will serve as a link)
        public Vertex destinationVertex;

        // Stores the weight of the edge between the origin and destination vertices as an integer
        public int edgeWeight;

        /**
         * Constructor to create an Edge object used to create a weighted or unweighted graph
         *
         * @param origin      Vertex where the edge originates from
         * @param destination Vertex where the edge terminates to
         * @param weight      Weight of the edge between the origin and destination vertices
         */
        Edge(Vertex origin, Vertex destination, int weight)
        {
            // Stores the passed in origin vertex
            this.originVertex = origin;

            // Stores the passed in destination vertex
            this.destinationVertex = destination;

            // Stores the passed in weight of the edge
            this.edgeWeight = weight;
        }
    }
}
