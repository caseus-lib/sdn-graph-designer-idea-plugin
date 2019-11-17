package caseus.sdn.graph.traverse.annotation;

import caseus.sdn.graph.traverse.RelationshipDirection;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class RelationshipAnnotation {

    private String type;
    private RelationshipDirection direction;

}
