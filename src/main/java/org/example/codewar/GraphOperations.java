package org.example.codewar;

import java.util.Set;
import java.util.stream.Collectors;

public class GraphOperations {

    public static Set<Vertex> getNeighbours(Graph graph, Vertex vertex) {
        return graph.getEdges().stream()
                .filter(x -> x.getV1().equals(vertex) || x.getV2().equals(vertex))
                .map(x -> (x.getV1().equals(vertex)) ? x.getV2() : x.getV1())
                .collect(Collectors.toSet());
    }
}
