package caseus.sdn.graph.traverse;

import lombok.Value;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Value
public class ClassGraph {

    private Set<GraphNode> nodes = new HashSet<>();
    private Map<String, RelationshipEntityDefinition> relationshipEntities = new HashMap<>();

    public void add(GraphNode node) {
        nodes.add(node);
    }

    public void add(RelationshipEntityDefinition definition) {
        relationshipEntities.put(definition.getClassName(), definition);
    }

}
