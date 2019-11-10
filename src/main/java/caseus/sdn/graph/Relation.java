package caseus.sdn.graph;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class Relation {

    private final String name;
    private final String nodeFrom;
    private final String nodeTo;
    private final RelationType relationType;

}
