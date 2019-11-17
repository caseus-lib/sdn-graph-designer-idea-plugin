package caseus.sdn.graph;

import caseus.sdn.graph.traverse.ClassGraph;
import caseus.sdn.graph.traverse.GraphNode;
import caseus.sdn.graph.traverse.GraphRelation;
import caseus.sdn.graph.traverse.RelationshipDirection;
import lombok.Builder;
import lombok.Value;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class GraphBuilderImpl implements GraphBuilder {

    @Override
    public Graph build(ClassGraph classGraph) {
        List<NodeRelationTuple> tuples = classGraph.getNodes()
                                                   .stream()
                                                   .map(graphNode -> build(classGraph, graphNode))
                                                   .collect(Collectors.toList());
        return Graph.builder()
                    .nodes(tuples.stream()
                                 .map(NodeRelationTuple::getNode)
                                 .collect(Collectors.toList()))
                    .relations(tuples.stream()
                                     .map(NodeRelationTuple::getRelations)
                                     .flatMap(Collection::stream)
                                     .collect(Collectors.toList()))
                    .build();
    }

    private NodeRelationTuple build(ClassGraph graph, GraphNode graphNode) {
        Node node = Node.builder()
                        .id(graphNode.getNodeClass())
                        .simpleName(graphNode.getName())
                        .build();
        return NodeRelationTuple.builder()
                                .node(node)
                                .relations(graphNode.getRelations()
                                                    .stream()
                                                    .filter(relation -> relation.getDirection() == RelationshipDirection.OUTGOING)
                                                    .map(relation -> build(graph, graphNode, relation))
                                                    .collect(Collectors.toList()))
                                .build();
    }

    private Relation build(ClassGraph graph, GraphNode graphNode, GraphRelation graphRelation) {
        return Relation.builder()
                       .name(graphRelation.getName())
                       .nodeFrom(graphNode.getNodeClass())
                       .nodeTo(define(graph, graphRelation.getNodeClassTo()))
                       .relationType(graphRelation.getRelationType())
                       .build();
    }

    private String define(ClassGraph graph, String nodeClass) {
        if (graph.getRelationshipEntities().containsKey(nodeClass)) {
            return graph.getRelationshipEntities().get(nodeClass).getClassTo();
        }
        return nodeClass;
    }

    @Builder
    @Value
    private static final class NodeRelationTuple {

        private Node node;
        private List<Relation> relations;

    }

}
