package caseus.sdn.graph.traverse;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class RelationshipAnnotationDefinition {

    private String type;
    private RelationshipDirection direction;

}
