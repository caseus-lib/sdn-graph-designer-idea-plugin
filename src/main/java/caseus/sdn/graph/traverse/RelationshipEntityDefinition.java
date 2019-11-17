package caseus.sdn.graph.traverse;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class RelationshipEntityDefinition {

    private String className;
    private String name;
    private String classFrom;
    private String classTo;

}
