package caseus.sdn.graph.traverse;

import lombok.Value;

import java.util.HashSet;
import java.util.Set;

@Value
public class ClassGraph {

    private Set<GraphNode> nodes = new HashSet<>();

    public void add(GraphNode node) {
        nodes.add(node);
    }

}
