package caseus.sdn.graph.traverse;

import lombok.Builder;
import lombok.Singular;
import lombok.Value;

import java.util.List;

@Value
@Builder
public class GraphNode {

    private String nodeClass;
    private String name;
    @Singular
    private List<GraphRelation> relations;

}
