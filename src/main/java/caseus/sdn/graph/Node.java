package caseus.sdn.graph;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class Node {

    private final String id;
    private final String simpleName;

}
