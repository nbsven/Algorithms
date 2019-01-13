package com.abra.algorithms.task_10;

import lombok.Data;
import lombok.ToString;

import java.io.*;
import java.util.*;

//https://en.wikipedia.org/wiki/Kosaraju%27s_algorithm
public class Task10 {

    private static Vertex[] graph;
    private static Integer verticesCount;
    private static Integer edgesCount;

    private static Stack<Vertex> L = new Stack<>();

    private static List<List<Vertex>> components = new ArrayList<>();


    public static void main(String[] args) throws Exception {
        readDataFromFile("src/main/resources/task10/input.txt");

        for (Vertex u : graph) {
            visit(u);
        }

        while (!L.isEmpty()) {
            Vertex u = L.pop();
            assign(u, u);
        }

        writeResultInFile("src/main/resources/task10/output.txt");
    }

    private static void readDataFromFile(String filename) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(filename));
        verticesCount = Integer.valueOf(scanner.nextLine());

        graph = new Vertex[verticesCount];

        for (int i = 0; i < graph.length; i++) {
            graph[i] = new Vertex();
        }

        edgesCount = Integer.valueOf(scanner.nextLine());

        for (int i = 0; i < edgesCount; i++) {
            final String[] splitted = scanner.nextLine().split("\\s");
            Integer inputVertex = Integer.valueOf(splitted[0]);
            Integer outputVertex = Integer.valueOf(splitted[1]);

            graph[inputVertex].getOutVertices().add(graph[outputVertex]);
            graph[outputVertex].getInVertices().add(graph[inputVertex]);
        }

        scanner.close();
    }

    private static void visit(Vertex u) {
        if (!u.isVisited()) {
            u.setVisited(true);
            for (Vertex v : u.getOutVertices()) {
                visit(v);
            }
            L.push(u);
        }
    }

    private static void assign(Vertex u, Vertex root) {
        if (u.getComponentNumber() == null) {
            Integer componentNumber = root.getComponentNumber();
            if (componentNumber == null) {
                componentNumber = components.size();
                components.add(new ArrayList<>());
            }
            u.setComponentNumber(componentNumber);
            components.get(componentNumber).add(u);

            for (Vertex v : u.getInVertices()) {
                assign(v, root);
            }
        }
    }

    private static void writeResultInFile(String filename) throws IOException {
        try (PrintWriter out = new PrintWriter(filename)) {
            out.println(components.size());
            components.sort(Comparator.comparingInt(List::size));
            for (List<Vertex> component : components) {
                out.println(component.size());
            }
        }
    }

}

@Data
@ToString(exclude = {"inVertices", "outVertices"})
class Vertex {
    private List<Vertex> inVertices = new ArrayList<>();
    private List<Vertex> outVertices = new ArrayList<>();
    private boolean visited = false;
    private Integer componentNumber;
}
