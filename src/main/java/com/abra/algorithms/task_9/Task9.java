package com.abra.algorithms.task_9;

import lombok.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

@SuppressWarnings("all")
//https://en.wikipedia.org/wiki/Dijkstra%27s_algorithm
public class Task9 {

    private static Vertex[] graph;
    private static Integer verticesCount;
    private static Integer edgesCount;

    public static void main(String[] args) throws Exception {
        readDataFromFile("src/main/resources/Task9/input.txt");

        graph[0].setDistance(0);

        Vertex vertexWithMinDistance = findVertexWithMinDistance();

        while (vertexWithMinDistance != null) {
            relaxDistances(vertexWithMinDistance);

            vertexWithMinDistance = findVertexWithMinDistance();
        }

        writeResultInFile("src/main/resources/Task9/output.txt");
    }

    private static void readDataFromFile(String filename) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(filename));
        verticesCount = Integer.valueOf(scanner.nextLine());

        graph = new Vertex[verticesCount];

        for (int i = 0; i < graph.length; i++) {
            graph[i] = new Vertex(i);
        }

        edgesCount = Integer.valueOf(scanner.nextLine());

        for (int i = 0; i < edgesCount; i++) {
            final String[] splitted = scanner.nextLine().split("\\s");
            Integer inputVertex = Integer.valueOf(splitted[0]);
            Integer outputVertex = Integer.valueOf(splitted[1]);
            Integer weight = Integer.valueOf(splitted[2]);

            graph[inputVertex].getOutVertices().put(graph[outputVertex], weight);
        }

        scanner.close();
    }

    private static Vertex findVertexWithMinDistance() {
        Integer minDistance = Integer.MAX_VALUE;
        Vertex result = null;

        for (int i = 0; i < verticesCount; i++) {
            Vertex currentVertex = graph[i];
            if (!currentVertex.isVisited() && currentVertex.getDistance() <= minDistance) {
                minDistance = currentVertex.getDistance();
                result = currentVertex;
            }
        }

        return result;
    }

    private static void relaxDistances(Vertex u) {
        u.setVisited(true);

        for (Vertex v : u.getOutVertices().keySet()) {
            Integer alt = u.getDistance() + Optional.of(u.getOutVertices().get(v)).orElse(0);
            if (alt < v.getDistance()) {
                v.setDistance(alt);
            }
        }
    }

    private static void writeResultInFile(String filename) throws IOException {
        try (PrintWriter out = new PrintWriter(filename)) {
            for (int i = 0; i < graph.length; i++) {
                Integer distance = graph[i].getDistance();

                if (distance == Integer.MAX_VALUE) {
                    out.println("inf");
                } else {
                    out.println(distance);
                }
            }
        }
    }
}

@Data
@ToString(exclude = {"outVertices"})
@RequiredArgsConstructor()
@EqualsAndHashCode(exclude = {"outVertices", "visited", "distance"})
class Vertex {

    @NonNull
    private int index;
    private Map<Vertex, Integer> outVertices = new HashMap<>();
    private boolean visited = false;
    private Integer distance = Integer.MAX_VALUE;
}
