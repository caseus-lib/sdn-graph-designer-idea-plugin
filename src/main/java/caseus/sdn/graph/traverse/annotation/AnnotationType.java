package caseus.sdn.graph.traverse.annotation;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum AnnotationType {

    RELATIONSHIP("org.neo4j.ogm.annotation.Relationship"),
    RELATIONSHIP_ENTITY("org.neo4j.ogm.annotation.RelationshipEntity"),
    NODE_ENTITY("org.neo4j.ogm.annotation.NodeEntity"),
    START_NODE("org.neo4j.ogm.annotation.StartNode"),
    END_NODE("org.neo4j.ogm.annotation.EndNode");

    private final String className;

}
