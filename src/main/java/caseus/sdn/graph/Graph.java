package caseus.sdn.graph;

import lombok.Builder;
import lombok.Value;

import java.util.List;

@Value
@Builder
public class Graph {

    private List<Node> nodes;
    private List<Relation> relations;

}
