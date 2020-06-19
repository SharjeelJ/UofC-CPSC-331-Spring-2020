package hw4.student;

import java.io.FileNotFoundException;

public class Tester
{
    public static void main(String args[]) throws FileNotFoundException
    {
        GridGraph graph = new GridGraph();
        System.out.println("Start Gradescope Tests");
        graph.buildGraph("/Users/sharjeel/OneDrive/Computer Science/IntelliJ/Java Workspace/UofC CPSC 331 Spring 2020/src/hw4/student/RandomMaze.txt");
        System.out.println(graph.findShortestPath(55, 55, false));
        graph = new GridGraph();
        graph.buildGraph("/Users/sharjeel/OneDrive/Computer Science/IntelliJ/Java Workspace/UofC CPSC 331 Spring 2020/src/hw4/student/RandomMaze.txt");
        System.out.println(graph.findShortestPath(53, 53, true));
        graph = new GridGraph();
        graph.buildGraph("/Users/sharjeel/OneDrive/Computer Science/IntelliJ/Java Workspace/UofC CPSC 331 Spring 2020/src/hw4/student/RandomMaze.txt");
        System.out.println(graph.findShortestPath(59, 92, false));
        graph = new GridGraph();
        graph.buildGraph("/Users/sharjeel/OneDrive/Computer Science/IntelliJ/Java Workspace/UofC CPSC 331 Spring 2020/src/hw4/student/RandomMaze.txt");
        System.out.println(graph.findShortestPath(92, 59, false));
        graph = new GridGraph();
        graph.buildGraph("/Users/sharjeel/OneDrive/Computer Science/IntelliJ/Java Workspace/UofC CPSC 331 Spring 2020/src/hw4/student/RandomMaze.txt");
        System.out.println(graph.findShortestPath(59, 92, true));
        graph = new GridGraph();
        graph.buildGraph("/Users/sharjeel/OneDrive/Computer Science/IntelliJ/Java Workspace/UofC CPSC 331 Spring 2020/src/hw4/student/RandomMaze.txt");
        System.out.println(graph.findShortestPath(92, 59, true));
        graph = new GridGraph();
        graph.buildGraph("/Users/sharjeel/OneDrive/Computer Science/IntelliJ/Java Workspace/UofC CPSC 331 Spring 2020/src/hw4/student/RandomMaze.txt");
        System.out.println(graph.findShortestPath(1, 72, false));
        graph = new GridGraph();
        graph.buildGraph("/Users/sharjeel/OneDrive/Computer Science/IntelliJ/Java Workspace/UofC CPSC 331 Spring 2020/src/hw4/student/RandomMaze.txt");
        System.out.println(graph.findShortestPath(1, 65, true));
        graph = new GridGraph();
        graph.buildGraph("/Users/sharjeel/OneDrive/Computer Science/IntelliJ/Java Workspace/UofC CPSC 331 Spring 2020/src/hw4/student/RandomMaze.txt");
        System.out.println(graph.findShortestPath(25, 49, false));
        graph = new GridGraph();
        graph.buildGraph("/Users/sharjeel/OneDrive/Computer Science/IntelliJ/Java Workspace/UofC CPSC 331 Spring 2020/src/hw4/student/RandomMaze.txt");
        System.out.println(graph.findShortestPath(35, 36, true));
        System.out.println("End Gradescope Tests");

        System.out.println();
        graph = new GridGraph();
        graph.buildGraph("/Users/sharjeel/OneDrive/Computer Science/IntelliJ/Java Workspace/UofC CPSC 331 Spring 2020/src/hw4/student/HilbertMaze.txt");
        System.out.println(graph.findShortestPath(1, 57, true));
        graph = new GridGraph();
        graph.buildGraph("/Users/sharjeel/OneDrive/Computer Science/IntelliJ/Java Workspace/UofC CPSC 331 Spring 2020/src/hw4/student/HilbertMaze.txt");
        System.out.println(graph.findShortestPath(1, 57, false));
        System.out.println();

        graph = new GridGraph();
        graph.buildGraph("/Users/sharjeel/OneDrive/Computer Science/IntelliJ/Java Workspace/UofC CPSC 331 Spring 2020/src/hw4/student/maze.txt");
        System.out.println(graph.findShortestPath(1, 12, false));
        System.out.println(graph.findShortestPath(1, 12, true));
    }
}
