package caseus.sdn.graph.traverse;

import caseus.sdn.graph.RelationType;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class GraphRelation {

    private String name;
    private String nodeClassTo;
    private RelationType relationType;
    private RelationshipDirection direction;

}
